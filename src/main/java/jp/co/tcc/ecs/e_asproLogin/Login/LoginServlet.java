/* COPYRIGHT ...
* ...
*/
package jp.co.tcc.ecs.e_asproLogin.Login;

import jp.co.tcc.ecs.e_asproComm.common.*;
import jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry.PasswordEntryServlet;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.DateUtil;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Vector;

/**
 * [�T�@�v]�F���O�C����ʂ̃A�N�V�����̃��\�b�h���`����N���X.<br>
 * [���@��]�F�A�N�V�����̃��\�b�h���`����B<br>
 * [���@�l]�F<br>
 *�@���쌠:Toukei Computer Company Copyright (c) 2008<br>
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
	/** ���N�G�X�g�ɓn���p�X���[�h�L�����Ԑ؂�̃A���[�g���b�Z�[�W�̃L�[ */
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
			//���O�C����ʂɂ�Submit�����ꍇ�̏���
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
			//service.search(bean);
			request.setAttribute("bean", bean);
			if(StringUtil.isNull(bean.getCoNo())){
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}
		
		return returnStr;
	}

	/**
	 * ���O�C����ʂ̏����\������
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public String doInit(HttpServletRequest request,
			HttpServletResponse response,LoginBean bean) throws Exception {
		request.getSession().invalidate();
		setRequest2Bean(request,bean);

		//service.search(bean);
		//��Ѝ\���}�X�^���ǂ߂Ȃ��ꍇ�A�܂��͉��NO����L�ȊO�͗�O(404 notFound��ʕ\���֑J�ځj
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
	 * ���O�C����ʂ�logout
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public String doLogout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginBean bean = new LoginBean();

		// �����\��
		session.removeAttribute(Constants.LOGIN_USER);
		
		request.setAttribute("bean", bean);
		return SUC_LOGIN_JSP;
	}
	
	/**
	 * ���O�C����ʂɂ�Submit�{�^���������ꂽ�ꍇ�̏���
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public String doLoginSubmit(HttpServletRequest request,
			HttpServletResponse response,LoginBean bean) throws Exception {
//		bean = (LoginBean)request.getAttribute("bean");
//		setRequest2Bean(request,bean);
		// �P���ړ��̓`�F�b�N
		String strERR =  CheckUtil(bean);
		if (Constants.EMPTY.equals(strERR) == false) {
			bean.setStrErrMsg(strERR);
			request.setAttribute("bean", bean);
			return SUC_LOGIN_JSP;
		}
		//UID��PWD���F�؂��s���A�ő̎��ʔԍ��͓o�^���Ȃ�			
		return doUserCdCert(request,response,false, bean);
	}
	
	/**
	 * USERCD��PWD���F�؂��s����
	 * @param bMobileUdp<br>
	 *  True:�ő̎��ʔԍ����X�V����<br>
	 *  False:�ő̎��ʔԍ����X�V���Ȃ�  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */		
	public String doUserCdCert(HttpServletRequest request,
			HttpServletResponse response,boolean bMobileUdp,LoginBean bean) throws Exception {
		HttpSession session = request.getSession();
		
		////�N���C�A���g��IP�`�F�b�N���ێ��@add 2011.09.12 k.sakakibara�@begin
		String remote_host = request.getRemoteAddr();//�N���C�A���gIP�A�h���X  
		if(!AuthHelper.checkRemoteHost(bean.getCoNo(),bean.getUserCd(),remote_host)){
			bean.setStrErrMsg(Properties.getMsg("ERROR.Login.002"));
			return SUC_LOGIN_JSP;
		}
		////// add 2011.09.12 k.sakakibara�@end
		
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

			//�Z�b�V����DB�̍쐬�i�i�[�j
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
			//2011.07.01 add K.sakakibara ���b�N���ꂽ���[�U�[
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
			// ������URL��NULL�̏ꍇ�A���j���[��ʂ�\������
			if (bean.getStrURL() == null || Constants.EMPTY.equals(bean.getStrURL()) == true) {
				return SUC_SEARCH_ACT;
			} else {
				// ������URL��NULL�ł͂Ȃ��ꍇ�A������URL�֑J�ڂ���
				return bean.getStrURL();
			}
		} else {
			// �f�[�^���Ȃ��ꍇ�̓��O�C����ʂ֖߂�A�G���[���b�Z�[�W��\�����A�������I������
			request.setAttribute("bean", bean);
			return SUC_LOGIN_JSP;
		}
	}	

	/** 
	 * [�T �v]:�`�F�b�N�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 * @param bean LoginBean
	 * @return String
	 */
	private String CheckUtil(LoginBean bean) {
		// ���[�U�[ID
		String loginUser = bean.getUserCd();
		if (InputChecker.MustCheck(loginUser) == true) {
			return Properties.getMsg("login.MSG004");
		} else {
			if (InputChecker.isAllHalf(loginUser) == false) {
				return Properties.getMsg("login.MSG005");
			}
		}
		// �p�X���[�h
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
