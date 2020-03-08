package jp.co.tcc.ecs.e_asproShip.SY010ShijiEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;

/**
 * [概 要]:出荷指示詳細servlet<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権:   Copyright (c) 2019<br>
 * @author Toukei Computer Company
 * @author S.Toi
 * @version 1.0
 * @since 1.0
 */
public class SY010ShijiEntryServlet extends CommonServlet {
	private static final String RETRUN_URL = "ship/SY010ShijiEntry.jsp";
	private static final long serialVersionUID = 1L;
	SY010ShijiEntryService service = new SY010ShijiEntryService();

	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SY010ShijiEntryBean bean = new SY010ShijiEntryBean();
		setRequest2Bean(request, bean);
		setLoginUser(request, bean);
		String returnStr = "";

		ManageUtils manageUtils = bean.getManageUtils(request);
		manageUtils.setReturnURL2Request(request);

		if ("".equals(bean.getProcessType()) == true) {
			returnStr = doInit(request, response, bean);
		}

		request.setAttribute("bean", bean);
		return returnStr;
	}
	/**
	 * [概 要]:初期表示。<br>
	 * [説 明]:出荷データ表示<br>
	 * [備 考]:
	 *
	 * @returnString
	 * @throws Exception
	 */
	public String doInit(HttpServletRequest request, HttpServletResponse response, SY010ShijiEntryBean bean) throws Exception {
		bean = service.search(bean);
		return RETRUN_URL;
	}

}
