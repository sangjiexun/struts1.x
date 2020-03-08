package jp.co.tcc.ecs.e_asproLogin.CC021SearchProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;

import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概要]顧客選択ウィンドウクラス<br>
 * @version 1.0<br>
 * @since 1.0<br>
 */

public class SearchProductServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;

	String mapKey = "CC021_PAGEFEED";
	SearchProductService service = new SearchProductService();
	private static final String SUC_SearchProduct_JSP = "/search/CC021SearchProduct.jsp";

	public String doAction(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SearchProductBean bean = new SearchProductBean();
		setRequest2Bean(request,bean);
		String returnStr = SUC_SearchProduct_JSP;

		User user = (User) request.getSession().getAttribute("_user");
		String strCo_no = Integer.toString(user.getCoNo());
		bean.setCo_no(strCo_no);

		//初期表示
		if ("".equals(bean.getProcessType())){
			returnStr = doInit(request, response, bean);
		//検索
		} else if ("1".equals(bean.getProcessType())){
			returnStr = doSubmit(request, response, bean);
			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL("CC021","/e_asproLogin/CC021SearchProduct", bean.getSearchParamMap());
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
	public String doInit(HttpServletRequest request,HttpServletResponse response, SearchProductBean bean) throws Exception {
		//引数：支社コード
		String sisyaCd = request.getParameter("sisyaCd");
		//引数：共通商品表示フラグ
		String kyotuDispFlg = request.getParameter("kyotuDispFlg");
		//引数：共通商品のみ表示フラグ
		String kyotuOnlyFlg = request.getParameter("kyotuOnlyFlg");

		//引数にて支社コードがある場合格納
		if (!"".equals(StringUtil.nvl(sisyaCd))) {
			bean.setSisyaCd(StringUtil.nvl(sisyaCd));
			bean.setSendSisyaCd(StringUtil.nvl(sisyaCd));
			bean.setSisyaNm(CommonService.getSisyaNm(bean.getCo_no(),bean.getSisyaCd()));
		}

		//引数にて共通商品のみ表示フラグがある場合格納
		if (!"".equals(StringUtil.nvl(kyotuOnlyFlg))) {
			bean.setSisyaCd(StringUtil.nvl(Constants.KYOTU_SISYA_CD));
			bean.setKyotuOnlyFlg(kyotuOnlyFlg);
			bean.setSisyaNm(CommonService.getSisyaNm(bean.getCo_no(),bean.getSisyaCd()));
		}

		//引数にて共通商品表示フラグがある場合格納
		if (!"".equals(StringUtil.nvl(kyotuDispFlg))) {
			bean.setKyotuDispFlg(StringUtil.nvl(kyotuDispFlg));
		}

		//プルダウン取得
		initDropDownList(request,bean);

		return SUC_SearchProduct_JSP;
	}

	/**
	 * 検索ボタン
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request,HttpServletResponse response,SearchProductBean bean) throws Exception {
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		bean = service.searchProduct(user,bean);
		//プルダウン取得
		initDropDownList(request,bean);
		bean.setSearchKbn("2");
		return SUC_SearchProduct_JSP;
	}

	/**
	 * DorpDownList初期化
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/**
	 * [概 要]:DropDownList初期設定<br>
	 * [説 明]:<br>
	 * [備 考]:
	 */
	public void initDropDownList(HttpServletRequest request, SearchProductBean bean) throws Exception {

		boolean kyotuDispFlg = true;
		if("".equals(bean.getKyotuDispFlg())){
			kyotuDispFlg = false;
		}

		// 支社リスト
		bean.setSisyaList(CommonService.getSisyaList(bean.getCo_no(), "",bean.getSendSisyaCd(), true, kyotuDispFlg, false));
	}

}