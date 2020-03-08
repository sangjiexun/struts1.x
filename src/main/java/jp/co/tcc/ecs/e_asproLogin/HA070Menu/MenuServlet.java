package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproLogin.HA070Menu.MenuHelper;
import jp.co.tcc.ecs.e_asproLogin.Login.LoginService;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecs.e_asproLogin.common.CommonServlet;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;
import java.util.HashMap;
import java.util.Vector;

public class MenuServlet extends CommonServlet{

	private static final long serialVersionUID = 1L;
	private static final String SUC_MENU_JSP = "menu/menu.jsp";
	
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForwordFlg = StringUtil.nvl(request.getParameter("strForwordFlg"));
		String returnStr = "";
		if (Constants.EMPTY.equals(strForwordFlg) == true || Constants.LOGIN_INIT.equals(strForwordFlg) == true) {
			//ログイン画面の初期表示処理
			returnStr = menu(request, response);
		}
		else if(Constants.PAGE_AJAX.equals(strForwordFlg)){
			returnStr = doAjax(request, response);
		}
		else if(Constants.LOGIN_OUT.equals(strForwordFlg)){
// replaced by K.Okuda 2015/04/27 start
//			Cookie cok;
//			cok = new Cookie("sessionId", "");
//			cok.setMaxAge(0);
//			cok.setPath(Constants.URL_BASE);
//			response.addCookie(cok);
//			cok = new Cookie("accessKey", "");
//			cok.setMaxAge(0);
//			cok.setPath(Constants.URL_BASE);
//			response.addCookie(cok);
			new LoginService().removeAllCookies(response);
// replaced by K.Okuda 2015/04/27 end
			return "/onLogin.jsp";
		}
		return returnStr;
	}
	
	public String menu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//目前のSessionを取得する
		//Sessionからユーザー情報を取得する
		User user = (User)request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
		///ユーザーのメニュー列を取得し、Sessionに保存する。
		MenuHelper.getMenu(user,request.getSession());
		
		//件数取得
		request.getSession().setAttribute("countList", MenuHelper.getMenuHeaderCount(user));
		
		return SUC_MENU_JSP;
	}
	
	private String doAjax(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		User user = (User)request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
		String kbn = StringUtil.nvl(request.getParameter("ajaxKbn"));
		StringBuffer sb = new StringBuffer();
		PrintWriter pw = response.getWriter();
		
		if(StringUtil.stringEquals("1", kbn)){
			//件数取得
			Vector<HashMap<String,String>> vec = MenuHelper.getMenuHeaderCount(user);
//			request.getSession().setAttribute("countList", MenuHelper.getMenuHeaderCount(user));
			String cnt = "";
			for (int i=0;i<vec.size();i++){
				if(i>0){
					cnt = cnt+",";
				}
				cnt=cnt+vec.get(i).get("cnt");
			}
			
			sb.append("setCnt('"+cnt+"');");
			
		}else{
			String whCd = StringUtil.nvl(request.getParameter("whCd"));
			user.setWhCd(whCd);
			request.getSession().setAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER, user);
			response.setContentType(Constants.AJAX_CONTENTTYPE);
			sb.append("goto();");
		}
		pw.write(sb.toString());
		pw.close();
		return Constants.AJAX;
	}

}
