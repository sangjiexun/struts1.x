package jp.co.tcc.ecs.e_asproLogin.CC031SearchZaiko;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;
import jp.co.tcc.ecs.e_asproLogin.common.CommonServlet;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概要]在庫商品選択ウィンドウクラス<br>
 * @version 1.0<br>
 * @since 1.0<br>
 */

public class SearchZaikoServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;

	String mapKey = "CC031_PAGEFEED";
	SearchZaikoService service = new SearchZaikoService();
	private static final String SUC_SearchProduct_JSP = "/search/CC031SearchZaiko.jsp";

	public String doAction(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SearchZaikoBean bean = new SearchZaikoBean();
		setRequest2Bean(request,bean);
		String returnStr = SUC_SearchProduct_JSP;
		User user = (User) request.getSession().getAttribute("_user");
		String strCo_no = Integer.toString(user.getCoNo());
		bean.setCo_no(strCo_no);

		//初期表示
		if ("".equals(bean.getProcessType()) == true){
			returnStr = doInit(request, response, bean);
		//検索
		} else if ("1".equals(bean.getProcessType()) == true){
			returnStr = doSubmit(request, response, bean);
			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL("CC031","/e_asproLogin/CC031SearchZaiko", bean.getSearchParamMap());
			manageUtils.setReturnURL2Request(request);
		} else if (Constants.PAGE_AJAX.equals(bean.getProcessType())) {
			//Ajax連動
			service.doAjax(request, response, bean);
			return Constants.AJAX;
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
	public String doInit(HttpServletRequest request,HttpServletResponse response, SearchZaikoBean bean) throws Exception {
		User user = (User) request.getSession().getAttribute("_user");
		String sisyaCd = request.getParameter("sisyaCd");
		String sitenCd = request.getParameter("sitenCd");
		String whCd = request.getParameter("whCd");
		String zaikoKbn = request.getParameter("zaikoKbn");
		String index = request.getParameter("index");

		//引数にて支社コードがある場合格納
		if (!"".equals(StringUtil.nvl(sisyaCd))) {
			bean.setSisyaCd(StringUtil.nvl(sisyaCd));
			service.getSisyaNm(user,bean);
		}

		//引数にて支店コードがある場合格納
		if (!"".equals(StringUtil.nvl(sitenCd))) {
			bean.setSitenCd(StringUtil.nvl(sitenCd));
		}

		//引数にて倉庫コードがある場合格納
		if (!"".equals(StringUtil.nvl(whCd))) {
			bean.setWhCd(StringUtil.nvl(whCd));
			service.getWhNm(user,bean);
		}

		//引数にて在庫区分コードがある場合格納
		if (!"".equals(StringUtil.nvl(zaikoKbn))) {
			bean.setZaikoKbn(StringUtil.nvl(zaikoKbn));
			service.getZaikoKbnNm(user,bean);
		}

		//引数にて行番号がある場合格納
		if (!"".equals(StringUtil.nvl(index))) {
			bean.setIndex(StringUtil.nvl(index));
		}

		initDropDownList(request, bean);

		return SUC_SearchProduct_JSP;
	}

	/**
	 * 検索ボタン
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request,HttpServletResponse response,SearchZaikoBean bean) throws Exception {
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		bean = service.searchZaiko(user,bean);
		initDropDownList(request, bean);
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
	public void initDropDownList(HttpServletRequest request, SearchZaikoBean bean) throws Exception {
		// 倉庫名
		bean.setWhList(CommonService.getSoukoList(bean.getCo_no(), true, bean.getSisyaCd()));
		// 支社リスト
		bean.setSisyaList(CommonService.getSisyaList(bean.getCo_no(), "",bean.getSisyaCd(), true, false, false));
		// 支店リスト
		bean.setSitenList(CommonService.getSitenList(bean.getCo_no(), bean.getSisyaCd(), bean.getSitenCd(), true));
		// 在庫区分
		bean.setZaikoKbnList(CommonService.getKubunList("ZAIKO_KBN", true));
	}

}