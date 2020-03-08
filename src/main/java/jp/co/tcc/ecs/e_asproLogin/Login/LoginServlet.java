/* COPYRIGHT ...
* ...
*/
package jp.co.tcc.ecs.e_asproLogin.Login;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.tcc.ecs.e_asproComm.common.CommonDAO;
import jp.co.tcc.ecs.e_asproLogin.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.InputChecker;
import jp.co.tcc.ecs.e_asproComm.common.Properties;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.common.UUIDUtil;
import jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry.PasswordEntryServlet;
import jp.co.tcc.ecsolution.framework.otherUtils.DateUtil;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

import jp.co.tcc.ecs.e_asproUser.User.User;

/**
 * [概　要]：ログイン画面のアクションのメソッドを定義するクラス.<br>
 * [説　明]：アクションのメソッドを定義する。<br>
 * [備　考]：<br>
 *　著作権:Toukei Computer Company Copyright (c) 2008<br>
 * @author DHK<br>
 * @version 1.0<br>
 * @since 1.0<br>
 */
public class LoginServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;
	
	LoginService service = new LoginService();
	Vector<Map<String, String>> vec = null;
	private static final String SUC_LOGIN_JSP = "login.jsp";
	private static final String TIME_OUT_JSP = "timeOut.jsp";
	private static final String OK_LOGIN_JSP = "OK";
	private static final String SUC_SEARCH_ACT = "menu?strForwordFlg=1";

// added by K.Okuda 2015/04/27 start
	/** リクエストに渡すパスワード有効期間切れのアラートメッセージのキー */
	public static final String ATTRIBUTE_KEY_PASSWORD_ALERT_MSG = "passwordAlertMsg";
// added by K.Okuda 2015/04/27 end

	public String doAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginBean bean = new LoginBean();

// added by K.Okuda 2015/04/27 start
		LoginBean before = (LoginBean) request.getAttribute("bean");
		if(before != null){
			bean.setStrErrMsg(before.getStrErrMsg());
		}
// added by K.Okuda 2015/04/27 end
		
		String strForwordFlg = StringUtil.nvl(request.getParameter("strForwordFlg"));
		String returnStr = SUC_LOGIN_JSP;
		if (Constants.LOGIN_SUBMIT.equals(strForwordFlg) == true) {
			//ログイン画面にてSubmitした場合の処理
			returnStr = doInit(request, response, bean);	
			if (OK_LOGIN_JSP.equals(returnStr)){
				returnStr = doLoginSubmit(request, response, bean);
			}		
		} else if (Constants.LOGIN_OUT.equals(strForwordFlg) == true) {
			//LOGIN_OUT
			returnStr = doLogout(request, response);
		}
		else{
			setRequest2Bean(request, bean);
			service.search(bean);
			request.setAttribute("bean", bean);
			if(StringUtil.isNull(bean.getCoNo())){
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}
		
		return returnStr;
	}

	/**
	 * ログイン画面の初期表示処理
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public String doInit(HttpServletRequest request,
			HttpServletResponse response,LoginBean bean) throws Exception {
		request.getSession().invalidate();
		setRequest2Bean(request,bean);

		service.search(bean);
		//会社構成マスタが読めない場合、または会社NOが上記以外は例外(404 notFound画面表示へ遷移）
		if(StringUtil.isNull(bean.getCoNo())){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		request.getSession().setAttribute("_menuColor", bean.getColor1());
		request.getSession().setAttribute("_compString", bean.getCompString());
		request.setAttribute("bean", bean);
		return OK_LOGIN_JSP;
	}
	
	/**
	 * ログイン画面のlogout
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public String doLogout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginBean bean = new LoginBean();

		// 初期表示
		session.removeAttribute(Constants.LOGIN_USER);
		
		request.setAttribute("bean", bean);
		return SUC_LOGIN_JSP;
	}
	
	/**
	 * ログイン画面にてSubmitボタンが押された場合の処理
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public String doLoginSubmit(HttpServletRequest request,
			HttpServletResponse response,LoginBean bean) throws Exception {
//		bean = (LoginBean)request.getAttribute("bean");
//		setRequest2Bean(request,bean);
		// 単項目入力チェック
		String strERR = this.CheckUtil(bean);
		if (Constants.EMPTY.equals(strERR) == false) {
			bean.setStrErrMsg(strERR);
			request.setAttribute("bean", bean);
			return SUC_LOGIN_JSP;
		}
		//UIDとPWDより認証を行う、固体識別番号は登録しない			
		return doUserCdCert(request,response,false, bean);
	}
	
	/**
	 * USERCDとPWDより認証を行いう
	 * @param bMobileUdp<br>
	 *  True:固体識別番号を更新する<br>
	 *  False:固体識別番号を更新しない  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */		
	public String doUserCdCert(HttpServletRequest request,
			HttpServletResponse response,boolean bMobileUdp,LoginBean bean) throws Exception {
		HttpSession session = request.getSession();
		
		////クライアントのIPチェック＆保持　add 2011.09.12 k.sakakibara　begin
		String remote_host = request.getRemoteAddr();//クライアントIPアドレス  
		if(!AuthHelper.checkRemoteHost(bean.getCoNo(),bean.getUserCd(),remote_host)){
			bean.setStrErrMsg(Properties.getMsg("ERROR.Login.002"));
			return SUC_LOGIN_JSP;
		}
		////// add 2011.09.12 k.sakakibara　end
		
		Boolean result = AuthHelper.userPasswordCheck(bean.getCoNo(), 
				bean.getUserCd(), 
				bean.getPassword(), 
				session);
		if(!result){
			bean.setStrErrMsg(Properties.getMsg("login.MSG001"));
		} else {
			bean.setStrErrMsg("");
		}
		String strErr = StringUtil.nvl(bean.getStrErrMsg());
		if (Constants.EMPTY.equals(strErr) == true) {

			session.setAttribute(Constants.LOGIN_USER, bean.getUserCd());
			
			User user = (User) request.getSession().getAttribute(
					jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);

			//セッションDBの作成（格納）
			String sessionId = (String)request.getSession().getId();
			String accessKey = UUIDUtil.getUUID();

			if(false == AuthHelper.insertSessionDB(sessionId,accessKey,user,remote_host)){
				return TIME_OUT_JSP;
			}else{
// replaced by K.Okuda 2015/04/27 start
//				Cookie cookie;
//				cookie = new Cookie("sessionId", sessionId);
//				cookie.setMaxAge(-1);
//				cookie.setPath(Constants.URL_BASE);
//				response.addCookie(cookie);
//				cookie = new Cookie("accessKey", accessKey);
//				cookie.setMaxAge(-1);
//				cookie.setPath(Constants.URL_BASE);
//				response.addCookie(cookie);
				service.addCookie(response, LoginService.CookieKey.sessionId, sessionId);
				service.addCookie(response, LoginService.CookieKey.accessKey, accessKey);
// replaced by K.Okuda 2015/04/27 end
			}
			//2011.07.01 add K.sakakibara ロックされたユーザー
			if (Constants.AUTH_USER_LOCK_TYPE_ERROR.equals(user.getLoginLockFlag())){
				request.setAttribute("bean", bean);
				bean.setStrErrMsg(Properties.getMsg("ERROR.Login.001"));
				return SUC_LOGIN_JSP;
			}		
			if (!DateUtil.between(DateUtil.getSystemYMD(), 
					 StringUtil.nvl(user.getValidYMDBegin(), Constants.AUTH_DEFAULT_VALID_BEGIN),
					 StringUtil.nvl(user.getValidYMDEnd(),Constants.AUTH_DEFAULT_VALID_END)) == true) {
				request.setAttribute("bean", bean);
// replaced by K.Okuda 2015/04/27 start
				// bean.setStrErrMsg(Properties.getMsg("login.MSG010"));
				// return SUC_LOGIN_JSP;
				bean.setSessionId(sessionId);
				session.removeAttribute(Constants.LOGIN_USER);
				service.removeAllCookies(response);
				return PasswordEntryServlet.FORWARD_THIS;
// replaced by K.Okuda 2015/04/27 end
			}

// added by K.Okuda 2015/04/27 start
			request.getSession().setAttribute(ATTRIBUTE_KEY_PASSWORD_ALERT_MSG, service.getPasswordAlertMsg(user.getValidYMDEnd()));
// added by K.Okuda 2015/04/27 end

			if (!DateUtil.timeBetween(CommonDAO.getSysdate().substring(11, 16),
					 StringUtil.nvl(user.getUseValidTimeBegin(),Constants.AUTH_DEFAULT_VALID_TIME_BEGIN), 
					 StringUtil.nvl(user.getUseValidTimeEnd(),Constants.AUTH_DEFAULT_VALID_TIME_END))) {
				return TIME_OUT_JSP;
			}
			// 引数のURLがNULLの場合、メニュー画面を表示する
			if (bean.getStrURL() == null || Constants.EMPTY.equals(bean.getStrURL()) == true) {
				return SUC_SEARCH_ACT;
			} else {
				// 引数のURLがNULLではない場合、引数のURLへ遷移する
				return bean.getStrURL();
			}
		} else {
			// データがない場合はログイン画面へ戻り、エラーメッセージを表示し、処理を終了する
			request.setAttribute("bean", bean);
			return SUC_LOGIN_JSP;
		}
	}	

	/** 
	 * [概 要]:チェック。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 * @param bean LoginBean
	 * @return String
	 */
	private String CheckUtil(LoginBean bean) {
		// ユーザーID
		String loginUser = bean.getUserCd();
		if (InputChecker.MustCheck(loginUser) == true) {
			return Properties.getMsg("login.MSG004");
		} else {
			if (InputChecker.isAllHalf(loginUser) == false) {
				return Properties.getMsg("login.MSG005");
			}
		}
		// パスワード
		String loginPass = bean.getPassword();
		if (InputChecker.MustCheck(loginPass) == true) {
			return Properties.getMsg("login.MSG006");
		} else {
			if (InputChecker.isAllHalf(loginPass) == false) {
				return Properties.getMsg("login.MSG007");
			}
		}
		return "";
	}
	
	public static String Rtrim(String s_value){
		if (!StringUtil.isNull(s_value)){
			while (s_value.endsWith(" ")){
				s_value = s_value.substring(0,s_value.length()-1);
			}
		}
		return s_value;
	}
	
		
}
