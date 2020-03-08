package jp.co.tcc.ecs.e_asproShip.SY160ShipList;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.common.InputChecker;
import jp.co.tcc.ecs.e_asproComm.common.Properties;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;
import jp.co.tcc.ecs.e_asproComm.framework.tag.SelectTag.Option;
import jp.co.tcc.ecsolution.framework.otherUtils.DateUtil;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [�T �v]:�o�׈ꗗServlet<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */
public class SY160ShipListServlet extends CommonServlet {

	private static final long serialVersionUID = -6272298983410752854L;
	private static final String RETRUN_JSP = "ship/SY160ShipList.jsp";
	private static final String RETRUN_URL = "/e_asproShip/SY160ShipList";
	SY160ShipListService service = new SY160ShipListService();

	public String doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		SY160ShipListBean bean = new SY160ShipListBean();
		setRequest2Bean(request, bean);
		setLoginUser(request, bean);

		// �q�Ƀ��[�U�̂���
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
			// ���j���[����J�ڂ̔��f
			if ("1".equals(bean.get_fromMenu())) {
				// �ʉ�ʖ߂���폜
				manageUtils.clearRetrunURL();
				manageUtils.setReturnURL2Request(request);
			} else {
				manageUtils.setReturnURL2Request(request, "SY160");
			}
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doSubmit(request, response, bean);

		} else if (Constants.PAGE_SEARCH.equals(bean.getProcessType())) {

			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doSubmit(request, response, bean);

		} else if (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())) {
			returnStr = this.doDownLoad(request, response, bean);

		}else if (Constants.PAGE_AJAX.equals(bean.getProcessType())) {
			//Ajax�A��
			doAjax(request, response, bean);
			return Constants.AJAX;
		}

		request.setAttribute("bean", bean);
		return returnStr;
	}
	/**
	 * [�T �v]:������<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String doInit(HttpServletRequest request, HttpServletResponse response, SY160ShipListBean bean)
			throws Exception {

		this.initDropDownList(request,bean);
		return RETRUN_JSP;
	}

	/**
	 * [�T �v]:����<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request, HttpServletResponse response, SY160ShipListBean bean)
			throws Exception {

		/* �`�F�b�N */
		String errMsg = searchValidator(bean);
		if (!"".equals(errMsg)) {
			bean.setStrErrMsg(errMsg);
			this.initDropDownList(request,bean);
			return RETRUN_JSP;
		}

		// ��������
		service.search(bean);
		bean.setSearchKbn("2");
		this.initDropDownList(request,bean);

		return RETRUN_JSP;
	}
	/**
	 * [�T �v]:DropDownList�����ݒ�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param request
	 * @throws Exception
	 */
	public void initDropDownList(HttpServletRequest request, SY160ShipListBean bean) throws Exception {
		// ���NO
		String coNo = StringUtil.nvl(bean.getLoginUser().getCoNo());

		// �x�Ђ��I������Ă�����x�X���X�g���擾����
		if (!"".equals(bean.getSisya_cd())) {
			List<Map<String, String>> sitenList = CommonService.getSitenList(String.valueOf(bean.getLoginUser().getCoNo()), bean.getSisya_cd(), true);
			bean.setSiten_list(sitenList);
		}

		// �q�ɖ�
		List<Option> resultWhNm = CommonService.getSoukoOptionList(coNo, true);
		request.setAttribute("resultWhNm", resultWhNm);

		// ������
		List<Map<String, String>> resultSyoriJyoukyou = new ArrayList<Map<String, String>>();
		Map<String, String> mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "");
		mapSyoriJyoukyou.put("VALUE", "");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "1");
		mapSyoriJyoukyou.put("VALUE", "�󒍊m��");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "3");
		mapSyoriJyoukyou.put("VALUE", "�o�׎w���m��");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "5");
		mapSyoriJyoukyou.put("VALUE", "�o�׃f�[�^�o��");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "7");
		mapSyoriJyoukyou.put("VALUE", "�o�׎��ъm��");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		request.setAttribute("resultSyoriJyoukyou", resultSyoriJyoukyou);

		// �w�b�_CSV�o��
		List<Map<String, String>> resultHeaderCSVList = CommonService.getPatternNameList(coNo, bean.CSV_HEAD_DOWN, true);
		request.setAttribute("resultHeaderCSVList", resultHeaderCSVList);

		// ����CSV�o��
		List<Map<String, String>> resultMeisaiCSVList = CommonService.getPatternNameList(coNo, bean.CSV_LIST_DOWN, true);
		request.setAttribute("resultMeisaiCSVList", resultMeisaiCSVList);

		// �⍇��NoCSV�o��
		List<Map<String, String>> resultToiCSVList = CommonService.getPatternNameList(coNo, bean.CSV_OKURINO_DOWN, true);
		request.setAttribute("resultToiCSVList", resultToiCSVList);

		SY160ShipListDAO dao = new SY160ShipListDAO();
		// CSV�_�E�����[�h���ڐ����Ɏ擾
		int itemHeaderCnt = dao.getItemCnt(coNo, bean.CSV_HEAD_DOWN, 0);
		if (itemHeaderCnt > 0 && resultHeaderCSVList.size() > 1) {
			bean.setHeaderCsvOutFlg("show");
		} else {
			bean.setHeaderCsvOutFlg("");
		}
		int itemListCnt = dao.getItemCnt(coNo, bean.CSV_LIST_DOWN, 0);
		if (itemListCnt > 0 && resultMeisaiCSVList.size() > 1) {
			bean.setListCsvOutFlg("show");
		} else {
			bean.setListCsvOutFlg("");
		}
		int itemToiCnt = dao.getItemCnt(coNo, bean.CSV_OKURINO_DOWN, 0);
		if (itemToiCnt > 0 && resultToiCSVList.size() > 1) {
			bean.setToi_csv_out_flg("show");
		} else {
			bean.setToi_csv_out_flg("");
		}

	}

	/**
	 * [�T �v]:CSV�o�́B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String doDownLoad(HttpServletRequest request, HttpServletResponse response, SY160ShipListBean bean) throws Exception {
		// CSV�w�b�_�o��
		if (bean.CSV_HEAD_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getHeader_csv_out(), "0")));
		}
		// ����CSV�o��
		if (bean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getList_csv_out(), "0")));
		}
		// �⍇��NoCSV�o��
		if (bean.CSV_OKURINO_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getToi_csv_out(), "0")));
		}
		// ���i�ʏo�א�CSV�o��
		if (bean.CSV_ITEM_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getItem_csv_out(), "0")));
		}

		String errM = searchValidator(bean);
		if ("".equals(errM)) {
			errM = service.downLoad(response, bean);
		}
		if (!"".equals(errM)) {
			bean.setStrErrMsg(errM);
			bean.setSearchKbn("1");
			initDropDownList(request,bean);
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
	public void doAjax(HttpServletRequest request,HttpServletResponse response, SY160ShipListBean bean) throws Exception {
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

	/**
	 * [�T �v]:�������ڃ`�F�b�N<br>
	 * [�� ��]:���������̕K�{�͂Ȃ��B�����������Ȃ��ꍇ�͎󒍊m����̒���1��������\��<br>
	 * [�� �l]:
	 *
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String searchValidator(SY160ShipListBean bean) throws Exception {

		StringBuilder sb = new StringBuilder();
		try {

			// �󒍊m���
			CommonService.compareYmd(bean.getSksj_ymd_s(), bean.getSksj_ymd_e(), "�󒍊m���", sb);
			// �������`�F�b�N
			CommonService.compareYmd(bean.getOrder_ymd_s(), bean.getOrder_ymd_e(), "������", sb);
			// �����ԍ��`�F�b�N
			if (!StringUtil.isNull(bean.getOrder_cd())) {
				if (!InputChecker.isAllHalf(bean.getOrder_cd())) {
					// ���p�`�F�b�N
					sb.append(Properties.getMsg("HA.ERROR.003", new String[]{"�����ԍ�"}, true));
				} else if (InputChecker.BytesCheck(bean.getOrder_cd(), 30)) {
					// �����`�F�b�N
					sb.append(Properties.getMsg("HA.ERROR.101", new String[]{"�����ԍ�", "30"}, true));
				}
			}

			// �o�ח\���
			CommonService.compareYmd(bean.getSyukka_yotei_ymd_s(), bean.getSyukka_yotei_ymd_e(), "\u51fa\u8377\u4e88\u5b9a\u65e5", sb);

			// �z�B�w���
			CommonService.compareYmd(bean.getTdk_ymd_s(), bean.getTdk_ymd_e(), "�z�B�w���", sb);

			// �w���m��`�F�b�N
			CommonService.compareYmd(bean.getSyukka_ok_ymd_s(), bean.getSyukka_ok_ymd_e(), "�w���m��", sb);

			// ���ъm��`�F�b�N
			CommonService.compareYmd(bean.getToiout_ymd_s(), bean.getToiout_ymd_e(), "���ъm��", sb);

			if (bean.CSV_HEAD_DOWN.equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getHeader_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[]{"�w�b�_CSV�o��"}, true));
					bean.setProcessType("");
				}
			}
			if (bean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getList_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[]{"����CSV�o��"}, true));
					bean.setProcessType("");
				}
			}
			if (bean.CSV_OKURINO_DOWN.equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getToi_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[]{"�⍇��NoCSV�o��"}, true));
					bean.setProcessType("");
				}
			}
			/**
			 * ���������w��`�F�b�N
			 * �����ԍ��A�o�׎w���ԍ���XX���ȏ�̎w�肪����΂��ꂾ����OK
			 * ����ȊO�̏ꍇ�͂����ꂩ�̓��t�͈͎w��K�{
			*/
			boolean isSitei = false;	//false�F�w��Ȃ�  true�F�w�肠��
			if (!InputChecker.MustCheck(bean.getOrder_cd()) && bean.getOrder_cd().length() >= 7){
				//�����ԍ���7���ȏ�w�肪�����OK
				isSitei = true;
			}else if(!InputChecker.MustCheck(bean.getSksj_cd()) && bean.getSksj_cd().length() >= 9){
				//�o�׎w���ԍ���9���ȏ�w�肪�����OK
				isSitei = true;
			}else{
				// ���t�͈̓`�F�b�N
				boolean isStartSitei = false;
				boolean isEndSitei = false;
				ArrayList<Date> chkYmds = new ArrayList<Date>();
				ArrayList<Date> chkYmde = new ArrayList<Date>();
				//�����ꂩ�̊J�n���������Ă��� ���A �����ꂩ�̏I�����������Ă����OK
				if(!InputChecker.MustCheck(bean.getSksj_ymd_s())||!InputChecker.MustCheck(bean.getOrder_ymd_s())
					||!InputChecker.MustCheck(bean.getSyukka_yotei_ymd_s())||!InputChecker.MustCheck(bean.getTdk_ymd_s())
					||!InputChecker.MustCheck(bean.getSyukka_ok_ymd_s())||!InputChecker.MustCheck(bean.getToiout_ymd_s())){
					// �J�n��
					isStartSitei = true;
					String defYmd = "1900/01/01";
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSksj_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getOrder_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_yotei_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getTdk_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_ok_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getToiout_ymd_s(),defYmd)));
				}
				if(!InputChecker.MustCheck(bean.getSksj_ymd_e())||!InputChecker.MustCheck(bean.getOrder_ymd_e())
						||!InputChecker.MustCheck(bean.getSyukka_yotei_ymd_e())||!InputChecker.MustCheck(bean.getTdk_ymd_e())
						||!InputChecker.MustCheck(bean.getSyukka_ok_ymd_e())||!InputChecker.MustCheck(bean.getToiout_ymd_e())){
					// �I����
					isEndSitei = true;
					String defYmd = "2100/12/31";
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSksj_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getOrder_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_yotei_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getTdk_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_ok_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getToiout_ymd_e(),defYmd)));
				}
				if (isStartSitei && isEndSitei) {
					isSitei = true;
					// �͈̓`�F�b�N
					Date startYmd = Collections.max(chkYmds);
					Date endYmd = Collections.min(chkYmde);
					// ������6�����ȓ��ACSV��1�����ȓ�
					int intervalMonths = (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType()))?1:6;
					// �J�n����6��������I�����̎w�肪�O�ɂȂ�Ȃ��ꍇ�̓G���[
					if (!InputChecker.DateCheck(DateUtil.formatYMD(endYmd),DateUtil.formatYMD(DateUtil.addMonths(startYmd, intervalMonths)), true)) {
						String msg = (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType()))?"CSV�o�͂���ꍇ�́A":"";
						sb.append(Properties.getMsg("ERROR.SY160.001", new String[]{msg, Integer.toString(intervalMonths)}, true));
					}
				}
			}

			// �w�蔻�� �w�肪�Ȃ��ꍇ�� ����1������
			if (!isSitei) {
				bean.setDef_ymd_e(DateUtil.getSystemYMD());
				bean.setDef_ymd_s(DateUtil.formatYMD(DateUtil.addMonths(DateUtil.getSysdate(), -1)));
			}
			bean.setProcessType(Constants.PAGE_SEARCH); // �����ɂ��ǂ��Ă���

		} catch (Exception e) {
			throw e;
		}
		return sb.toString();
	}
}
