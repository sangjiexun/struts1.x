package jp.co.tcc.ecs.e_asproLogin.CC030SearchCustomer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;
import jp.co.tcc.ecs.e_asproLogin.common.CommonServlet;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概要]顧客選択ウィンドウクラス<br>
 * @version 1.0<br>
 * @since 1.0<br>
 */

public class SearchCustomerServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;

	String mapKey = "CC030_PAGEFEED";
	SearchCustomerService service = new SearchCustomerService();
	private static final String SUC_SearchProduct_JSP = "/search/CC030SearchCustomer.jsp";

	public String doAction(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SearchCustomerBean bean = new SearchCustomerBean();
		setRequest2Bean(request,bean);
		String returnStr = SUC_SearchProduct_JSP;
		User user = (User) request.getSession().getAttribute("_user");
		String sisyaCd = request.getParameter("sisyaCd");

		//引数にて支社コードがある場合格納
		if (!"".equals(StringUtil.nvl(sisyaCd))) {
			bean.setSisyaCd(StringUtil.nvl(sisyaCd));
			service.getSisyaNm(user,bean);
		}

		//初期表示
		if ("".equals(bean.getProcessType()) == true){
			returnStr = doInit(request, response, bean);
		//検索
		} else if ("1".equals(bean.getProcessType()) == true){
			returnStr = doSubmit(request, response, bean);
			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL("CC030","/e_asproLogin/CC030SearchCustomer", bean.getSearchParamMap());
			manageUtils.setReturnURL2Request(request);
		}
		request.setAttribute("bean", bean);
		return returnStr;
	}

	/**
	 * 初期表示
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String doInit(HttpServletRequest request,HttpServletResponse response, SearchCustomerBean bean) throws Exception {
		User user = (User) request.getSession().getAttribute("_user");
		String strCo_no = StringUtil.nvl(user.getCoNo(),"0");
		bean.setCo_no(strCo_no);
		String userAuth = user.getUserAuth();
		String userNo = "";
		//本部以外の場合userNoの設定
		if(!Constants.USER_KBN_HONBU.equals(userAuth)){
			userNo = StringUtil.nvl(user.getUserNo(),"");
		}
		bean.setSisyaList(CommonService.getSisyaList(strCo_no,userNo,"",true,false,false));

		return SUC_SearchProduct_JSP;
	}

	/**
	 * 検索ボタン
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request,HttpServletResponse response,SearchCustomerBean bean) throws Exception {
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		bean = service.searchCustomer(user,bean);
		bean.setSearchKbn("2");
		return SUC_SearchProduct_JSP;
	}
}