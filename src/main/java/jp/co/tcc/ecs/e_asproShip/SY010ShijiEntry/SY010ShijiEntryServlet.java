package jp.co.tcc.ecs.e_asproShip.SY010ShijiEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;

/**
 * [�T �v]:�o�׎w���ڍ�servlet<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠:   Copyright (c) 2019<br>
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
	 * [�T �v]:�����\���B<br>
	 * [�� ��]:�o�׃f�[�^�\��<br>
	 * [�� �l]:
	 *
	 * @returnString
	 * @throws Exception
	 */
	public String doInit(HttpServletRequest request, HttpServletResponse response, SY010ShijiEntryBean bean) throws Exception {
		bean = service.search(bean);
		return RETRUN_URL;
	}

}
