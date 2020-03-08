package jp.co.tcc.ecs.e_asproShip.SY050ShijiFixEntry;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class ShijiFixEntryServlet extends CommonServlet {

	private static final long serialVersionUID = -6272298983410752854L;
	private static final String RETRUN_JSP = "ship/SY050ShijiFixEntry.jsp";
	private static final String RETRUN_URL = "/e_asproShip/SY050ShijiFixEntry";
	ShijiFixEntryService service = new ShijiFixEntryService();

	public String doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ShijiFixEntryBean bean = new ShijiFixEntryBean();
		setRequest2Bean(request, bean);
		setLoginUser(request, bean);
		// �q�Ƀ��[�U
		if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {
			bean.setWh_cd(bean.getLoginUser().getWhCd());
		}
		// �x�ЁA�x�X���[�U
		if (Constants.USER_KBN_SISYA.equals(bean.getLoginUser().getUserAuth())||Constants.USER_KBN_SITEN.equals(bean.getLoginUser().getUserAuth())) {
			bean.setSisya_cd(bean.getLoginUser().getSisyaCd());
		}

		String returnStr = "";
		if ("".equals(bean.getProcessType()) == true) {

			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doInit(request, response, bean);
			returnStr = doSubmit(request, response, bean);

		} else if (Constants.PAGE_SEARCH.equals(bean.getProcessType())) {
			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doSubmit(request, response, bean);
		}else if (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())) {
			//CSV�o�͏���
			returnStr = doDownLoad(request, response, bean);
		}else if (Constants.PAGE_AJAX.equals(bean.getProcessType())) {
			//Ajax�A��
			doAjax(request, response, bean);
			return Constants.AJAX;
		}else {
			// �I��or�ꊇ�m��
			returnStr = callServiceSyori(request, response, bean);
		}

		request.setAttribute("bean", bean);
		return returnStr;
	}

	/**
	 * [�T �v]:�󒍊m�菈��<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param ShijiFixEntryBean bean
	 * @return String
	 * @throws Exception
	 */
	public String callServiceSyori(HttpServletRequest request, HttpServletResponse response, ShijiFixEntryBean bean) throws Exception {

		String returnStr = "";
		String sWait = "";

		if ((ShijiFixEntryBean.PAGE_KOBETU.equals(bean.getProcessType())) || (ShijiFixEntryBean.PAGE_IKKATU.equals(bean.getProcessType()))) {
			sWait = "e_asproShip/ship/SY051Wait.jsp"
					+ "?searchKbn=" + (String) StringUtil.nvl(bean.getSearchKbn())
					+ "&intRecordCountExcl=" + (String) StringUtil.nvl(bean.getIntRecordCountExcl())
					+ "&syukka_yotei_ymd_s=" + (String) StringUtil.nvl(bean.getSyukka_yotei_ymd_s())
					+ "&syukka_yotei_ymd_e=" + (String) StringUtil.nvl(bean.getSyukka_yotei_ymd_e())
					+ "&order_cd=" + (String) StringUtil.nvl(bean.getOrder_cd())
					+ "&sksj_cd=" + (String) StringUtil.nvl(bean.getSksj_cd())
					+ "&upload_no=" + (String) StringUtil.nvl(bean.getUpload_no())
					+ "&sisya_cd=" + (String) StringUtil.nvl(bean.getSisya_cd())
					+ "&siten_cd=" + (String) StringUtil.nvl(bean.getSiten_cd())
					+ "&kokyaku_cd=" + (String) StringUtil.nvl(bean.getKokyaku_cd())
					+ "&kokyaku_nm=" + URLEncoder.encode((String) StringUtil.nvl(bean.getKokyaku_nm()), "SHIFT_JIS")
					+ "&tdk_cd=" + (String) StringUtil.nvl(bean.getTdk_cd())
					+ "&wh_cd=" + (String) StringUtil.nvl(bean.getWh_cd())
					+ "&order_ymd_s=" + (String) StringUtil.nvl(bean.getOrder_ymd_s())
					+ "&order_ymd_e=" + (String) StringUtil.nvl(bean.getOrder_ymd_e())
					+ "&tdk_ymd_s=" + (String) StringUtil.nvl(bean.getTdk_ymd_s())
					+ "&tdk_ymd_e=" + (String) StringUtil.nvl(bean.getTdk_ymd_e())
					+ "&item_cd1_s=" + (String) StringUtil.nvl(bean.getItem_cd1_s())
					+ "&item_cd1_e=" + (String) StringUtil.nvl(bean.getItem_cd1_e())
					+ "&item_nm1=" + URLEncoder.encode((String) StringUtil.nvl(bean.getItem_nm1()), "SHIFT_JIS")
					+ "&shipKbn=" + (String) StringUtil.nvl(bean.getShipKbn())
					+ "&orderKbn=" + (String) StringUtil.nvl(bean.getOrderKbn());

			String[] seq_no = bean.getSeq_no();
			if (ShijiFixEntryBean.PAGE_KOBETU.equals(bean.getProcessType()) && seq_no != null && seq_no.length > 0) {
				for (String ship : seq_no) {
					sWait = sWait + "&seq_no=" + ship;
				}
			}
		}

		// wait�y�[�W�J��
		if (ShijiFixEntryBean.PAGE_KOBETU.equals(bean.getProcessType())) {
			sWait = sWait + "&processType="+ShijiFixEntryBean.PAGE_KOBETU+"1";
			returnStr = CommonServlet.setRedirectUrl(sWait);
		} else if (ShijiFixEntryBean.PAGE_IKKATU.equals(bean.getProcessType())) {
			sWait = sWait + "&processType="+ShijiFixEntryBean.PAGE_IKKATU+"1";
			returnStr = CommonServlet.setRedirectUrl(sWait);
		}
		// �m�菈��
		else if ((ShijiFixEntryBean.PAGE_KOBETU+"1").equals(bean.getProcessType())) {
			returnStr = doKobetu(request, response, bean);
		} else if ((ShijiFixEntryBean.PAGE_IKKATU+"1").equals(bean.getProcessType())) {
			returnStr = doIkkatu(request, response, bean);
		}

		return returnStr;

	}
	/**
	 * [�T �v]:DropDownList�����ݒ�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param HttpServletRequest request
	 * @throws Exception
	 */
	public void initDropDownList(HttpServletRequest request, ShijiFixEntryBean bean) throws Exception {

		// �x�Ђ��I������Ă�����x�X���X�g���擾����
		if (!"".equals(bean.getSisya_cd())) {
			List<Map<String, String>> sitenList = CommonService.getSitenList(String.valueOf(bean.getLoginUser().getCoNo()), bean.getSisya_cd(), true);
			bean.setSiten_list(sitenList);
		}
		// ����ȊO�̃��X�g��jsp���Œ��ڎ擾���Ă܂�
	}

	/**
	 * [�T �v]:�������B<br>
	 * [�� ��]:���������s���B<br>
	 * [�� �l]:
	 *
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param ShijiFixEntryBean bean
	 * @return String
	 * @throws Exception
	 */
	public String doInit(HttpServletRequest request, HttpServletResponse response, ShijiFixEntryBean bean)throws Exception {
		this.initDropDownList(request,bean);
		return RETRUN_JSP;
	}

	/**
	 * [�T �v]�����B<br>
	 * [�� ��]:�������s���B<br>
	 * [�� �l]:
	 *
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param ShijiFixEntryBean bean
	 * @return String
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request, HttpServletResponse response, ShijiFixEntryBean bean) throws Exception {

		/* �`�F�b�N */
		String errMsg = updateValidator(bean);
		if (!"".equals(errMsg)) {
			bean.setStrErrMsg(errMsg);
			bean.setSearchKbn("2");
			this.initDropDownList(request,bean);
			return RETRUN_JSP;
		}

		// ��������
		bean = service.search(bean);
		bean.setSearchKbn("2");
		this.initDropDownList(request,bean);
		return RETRUN_JSP;
	}

	/**
	 * [�T �v]�I���m��B<br>
	 * [�� ��]:�I���m����s���B<br>
	 * [�� �l]:
	 *
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param ShijiFixEntryBean bean
	 * @return String
	 * @throws Exception
	 */
	public String doKobetu(HttpServletRequest request, HttpServletResponse response, ShijiFixEntryBean bean) throws Exception {

		/* �`�F�b�N */
		String errMsg = exclValidator(bean);
		if (bean.getSeq_no().length >0){
			int errFlg = service.kobetu(bean);

			if(errFlg > 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("SYS.ERROR.006");
				errMsg = sb.toString();
			}
		}

		// ��������
		bean = service.search(bean);
		bean.setSearchKbn("2");
		this.initDropDownList(request,bean);

		ManageUtils manageUtils = bean.getManageUtils(request);
		return setRedirectUrl(manageUtils.getUrl()+"&strExclMsg="+errMsg);
	}

	/**
	 * [�T �v]�ꊇ�m��B<br>
	 * [�� ��]:�ꊇ�m����s���B<br>
	 * [�� �l]:
	 *
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param ShijiFixEntryBean bean
	 * @return String
	 * @throws Exception
	 */
	public String doIkkatu(HttpServletRequest request, HttpServletResponse response, ShijiFixEntryBean bean) throws Exception {

		/* �`�F�b�N */
		String errMsg = updateValidator(bean);
		if (!"".equals(errMsg)) {
			bean.setStrErrMsg(errMsg);
			bean = service.search(bean);
			bean.setSearchKbn("2");
			this.initDropDownList(request,bean);
			return RETRUN_JSP;
		}

		String strExclMsg = "";
		int excl = service.ikkatu(bean);
		if(excl == -1) {
			strExclMsg ="SYS.ERROR.006" ;
		}
		else if(excl == -9) {
			strExclMsg ="ERROR.SY050.001" ;
		}
		else if(excl < bean.getIntRecordCountExcl()){
			strExclMsg ="SYS.ERROR.008" ;
		}

		// ��������
		bean = service.search(bean);
		bean.setSearchKbn("2");
		this.initDropDownList(request,bean);

		ManageUtils manageUtils = bean.getManageUtils(request);
		return setRedirectUrl(manageUtils.getUrl()+"&strExclMsg="+strExclMsg);
	}

	/**
	 * [�T �v]:�����`�F�b�N�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param ShijiFixEntryBean bean
	 * @return String
	 * @throws Exception
	 */
	public String updateValidator(ShijiFixEntryBean bean) throws Exception {

		StringBuffer sb = new StringBuffer();

		// ������
		CommonService.compareYmd(bean.getOrder_ymd_s(), bean.getOrder_ymd_e(),"������", sb);

		// �o�ח\���
		CommonService.compareYmd(bean.getSyukka_yotei_ymd_s(), bean.getSyukka_yotei_ymd_e(),"�o�ח\���", sb);

		// �z�B�w���
		CommonService.compareYmd(bean.getTdk_ymd_s(), bean.getTdk_ymd_e(),"�z�B�w���", sb);

		return sb.toString();
	}

	/**
	 * [�T �v]:�r���`�F�b�N�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param ShijiFixEntryBean bean
	 * @return String
	 * @throws Exception
	 */
	public String exclValidator(ShijiFixEntryBean bean) throws Exception {

		StringBuffer sb = new StringBuffer();
		// �r���`�F�b�N
		if (service.excl(bean) == false) {
			sb.append("SYS.ERROR.008");
		}

		return sb.toString();
	}

	/**
	 * [�T �v]:CSV�o�͏����B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 * @param request
	 * @param response
	 * @param bean
	 * @return ajax
	 * @throws Exception
	 */
	public String doDownLoad(HttpServletRequest request,
			HttpServletResponse response, ShijiFixEntryBean bean) throws Exception {

		String errMsg = "";

		errMsg = service.downLoad(response,bean);
		if (!StringUtil.isNull(errMsg)) {
			bean.setStrErrMsg(errMsg);
			bean.setSearchKbn("1");
			this.initDropDownList(request,bean);
			return RETRUN_JSP;
		}

		return Constants.AJAX;
	}

	/**
	 * [�T �v]:AJAX<br>
	 * [�� ��]:�x�ЕύX���x�X�v���_�E���擾<br>
	 * [�� �l]:
	 * @param request
	 * @param response
	 * @param bean
	 * @return ajax
	 * @throws Exception
	 */
	public void doAjax(HttpServletRequest request,HttpServletResponse response, ShijiFixEntryBean bean) throws Exception {
		response.setContentType(Constants.AJAX_CONTENTTYPE);
		StringBuffer sb = new StringBuffer();

		List<Map<String, String>> sitenList = CommonService.getSitenList(String.valueOf(bean.getLoginUser().getCoNo()), bean.getSisya_cd(), true);
		bean.setSiten_list(sitenList);
		if (bean.getSiten_list() != null){
			for (Map<String,String> item : bean.getSiten_list()) {
				//SELECT �쐬Scpipt
				sb.append("$('#siten_cd').append($('<option>').html('"+ StringUtil.nvl(item.get("VALUE")) + "').val('" + StringUtil.nvl(item.get("KEY")) + "')); ");
			}
		}

		PrintWriter pw = response.getWriter();
		pw.write(sb.toString());
		pw.close();
	}
}
