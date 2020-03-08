package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproLogin.HA070Menu.MenuHelper;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecs.e_asproLogin.common.CommonServlet;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class TopMenuServlet extends CommonServlet{

	private static final long serialVersionUID = 1L;
	private static final String SUC_MENU_JSP = "menu/top.jsp";
	
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForwordFlg = StringUtil.nvl(request.getParameter("strForwordFlg"));
		String returnStr = "";
		if (Constants.EMPTY.equals(strForwordFlg) == true || Constants.LOGIN_INIT.equals(strForwordFlg) == true) {
			//ログイン画面の初期表示処理
			returnStr = menu(request, response);
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
		
		//受注未確定件数取得
		request.getSession().setAttribute("countList", MenuHelper.getMenuHeaderCount(user));
		
		return SUC_MENU_JSP;
	}
}
