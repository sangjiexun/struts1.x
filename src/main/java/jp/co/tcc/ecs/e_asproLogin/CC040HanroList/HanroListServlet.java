package jp.co.tcc.ecs.e_asproLogin.CC040HanroList;
/**
 * [概 要]:販路詳細Servlet<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概 要]:数量ヒアリング登録Servlet<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 */
public class HanroListServlet extends CommonServlet{

	private static final long serialVersionUID = 1L;
	private HanroListService service = new HanroListService();
	/**
	 * [概 要]:処理判定<br>
	 * [説 明]:<br>
	 * [備 考]:<br>
	 */
	public String doAction(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//beanに画面値をバインドする
		HanroListBean bean = new HanroListBean();
		setRequest2Bean(request,bean);
		
		//beanにログインユーザーをバインドする
		User user = (User) request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
		bean.setLoginUser(user);
		
		//処理判定
		String returnStr = "";
		if (Constants.PAGE_INIT.equals(StringUtil.nvl(bean.getProcessType(),Constants.PAGE_INIT))){
			//初期化
			returnStr = doInit(request,response,bean);
			
		}
		request.setAttribute("bean", bean);
		return returnStr;
	}
	
	/**
	 * [概 要]:初期化<br>
	 * [説 明]:<br>
	 * [備 考]:<br>
	 */
	public String doInit(HttpServletRequest request,HttpServletResponse response, HanroListBean bean) throws Exception {
		// 検索データ
		service.search(bean);
		
		return HanroListBean.FORWARD_URL;
	}

}
