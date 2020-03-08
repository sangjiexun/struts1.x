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

		//��������
		if(StringUtil.isNull(bean.getProcessType()) || Constants.PAGE_SEARCH.equals(bean.getProcessType())){
			manageUtils.addListReturnURL(RETURN_URL, bean.getSearchParamMap());
			returnStr = doInit(bean);
		//�o�^
		} else if (Constants.PAGE_INSERT.equals(bean.getProcessType())) {
			returnStr = doUpdate(bean);
		//�G���[�f�[�^��CSV�_�E�����[�h
		} else if (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())) {
			returnStr = doDownLoad(response, bean);
		}
		request.setAttribute("bean", bean);

		return returnStr;
	}

	/**
	 * [�T �v]:CSV�o�^�i�m�F�j ������<br>
	 * [�� ��]:���[�N�e�[�u������A�b�v���[�h�{�G���[�`�F�b�N�������ʂ��ꗗ�ŕ\��<br>
	 * [�� �l]:
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
	 * [�T �v]:CSV�o�^�i�m�F�j �o�^<br>
	 * [�� ��]:���[�N�e�[�u������{�e�[�u���ɍX�V<br>
	 * [�� �l]:
	 * @param ShipFixUPTempBean
	 * @return String
	 * @throws Exception
	*/
	public String doUpdate(ShipFixUPTempBean bean) throws Exception {

		service.update(bean);
		return CommonServlet.setRedirectUrl(FORWARD_URL);
	}
	/**
	 * [�T �v]:CSV�o�^�i�m�F�j CSV�o��<br>
	 * [�� ��]:�G���[�f�[�^��CSV�o�͂���<br>
	 * [�� �l]:
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
