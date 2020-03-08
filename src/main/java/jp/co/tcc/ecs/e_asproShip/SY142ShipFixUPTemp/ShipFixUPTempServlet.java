package jp.co.tcc.ecs.e_asproShip.SY142ShipFixUPTemp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class ShipFixUPTempServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;
	public static final String RETURN_JSP = "ship/SY142ShipFixUPTemp.jsp";
	public static final String RETURN_URL = "/e_asproShip/SY142ShipFixUPTemp";
	public static final String FORWARD_URL = "e_asproShip/SY141ShipFixUP";
	ShipFixUPTempService service = new ShipFixUPTempService();

	public String doAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ShipFixUPTempBean bean = new ShipFixUPTempBean();
		setRequest2Bean(request, bean);
		setLoginUser(request, bean);
		ManageUtils manageUtils = bean.getManageUtils(request);

		String returnStr = "";

		//初期処理
		if(StringUtil.isNull(bean.getProcessType()) || Constants.PAGE_SEARCH.equals(bean.getProcessType())){
			manageUtils.addListReturnURL(RETURN_URL, bean.getSearchParamMap());
			returnStr = doInit(bean);
		//登録
		} else if (Constants.PAGE_INSERT.equals(bean.getProcessType())) {
			returnStr = doUpdate(bean);
		//エラーデータのCSVダウンロード
		} else if (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())) {
			returnStr = doDownLoad(response, bean);
		}
		request.setAttribute("bean", bean);

		return returnStr;
	}

	/**
	 * [概 要]:CSV登録（確認） 初期化<br>
	 * [説 明]:ワークテーブルからアップロード＋エラーチェックした結果を一覧で表示<br>
	 * [備 考]:
	 * @param ShipFixUPTempBean
	 * @return String
	 * @throws Exception
	*/
	public String doInit(ShipFixUPTempBean bean) throws Exception {

		bean.setSearchKbn("2");
		bean = service.search(bean);
		bean = service.search_header(bean);

		return RETURN_JSP;
	}

	/**
	 * [概 要]:CSV登録（確認） 登録<br>
	 * [説 明]:ワークテーブルから本テーブルに更新<br>
	 * [備 考]:
	 * @param ShipFixUPTempBean
	 * @return String
	 * @throws Exception
	*/
	public String doUpdate(ShipFixUPTempBean bean) throws Exception {

		service.update(bean);
		return CommonServlet.setRedirectUrl(FORWARD_URL);
	}
	/**
	 * [概 要]:CSV登録（確認） CSV出力<br>
	 * [説 明]:エラーデータをCSV出力する<br>
	 * [備 考]:
	 * @param HttpServletResponse
	 * @param ShipFixUPTempBean
	 * @return String
	 * @throws Exception
	 */
	public String doDownLoad(HttpServletResponse response, ShipFixUPTempBean bean) throws Exception {

		String errM = service.download(response,bean);

		if (!"".equals(errM)) {
			bean.setStrErrMsg(errM);
			return RETURN_JSP;
		}
		return Constants.AJAX;
	}

}
