package jp.co.tcc.ecs.e_asproShip.SY110PrintList;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.DateUtil;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [�T �v]:�o�׎w���f�[�^ Servlet<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 */
public class PrintListServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;
	private static final String RETRUN_URL = "ship/SY110PrintList.jsp";

	private static int CODE = 0;
 
	// service api
	PrintListService service = new PrintListService();
	CommonService commonService = new CommonService();

	/**
	 * [�T �v]:������<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 */
	public String doInit(HttpServletRequest request, HttpServletResponse response, PrintListBean bean)
			throws Exception {
		User user = (User) request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
		bean.setCoNo(Integer.toString(user.getCoNo()));
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		// �o�׎w���m����iFrom�j
		bean.setSyukkaOkYmdFrom(df.format(new Date(d.getTime() - 7 * 24 * 60 * 60 * 1000)));
		// �o�׎w���m����iTo�j
		bean.setSyukkaOkYmdTo(df.format(d));
		bean.setStrFromPageId("SY110");
		// �h���b�v�_�E�����X�g�̏��������s��
		service.initDropDownList(bean);
		return PrintListBean.FORWARD_URL;
	}

	public String doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintListBean bean = new PrintListBean();
		setRequest2Bean(request, bean);
		setLoginUser(request, bean);
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
		String strCo_no = Integer.toString(user.getCoNo());
		// ��ɔԍ�
		bean.setCoNo(strCo_no);
		System.out.println(bean);
		// �q�Ƀ��[�U
		if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {
			bean.setWhCd(bean.getLoginUser().getWhCd());
		}
		// �x�ЁA�x�X���[�U
		if (Constants.USER_KBN_SISYA.equals(bean.getLoginUser().getUserAuth())
				|| Constants.USER_KBN_SITEN.equals(bean.getLoginUser().getUserAuth())) {
			bean.setSisya_cd(bean.getLoginUser().getSisyaCd());
		}

		String returnStr = null;
		if ("".equals(bean.getProcessType()) == true) {
			ManageUtils manageUtils = bean.getManageUtils(request);
			// ���j���[����J�ڂ̔��f
			if ("1".equals(bean.get_fromMenu())) {

				// �ʉ�ʖ߂���폜
				manageUtils.clearRetrunURL();
				manageUtils.setReturnURL2Request(request);

			} else {
				manageUtils.setReturnURL2Request(request, "SY110");
			}
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doSubmit(request, response, bean);

		} else if (Constants.PAGE_SEARCH.equals(bean.getProcessType())) {

			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doSubmit(request, response, bean);

		} else if (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())) {
			returnStr = this.doDownLoad(request, response, bean);

		} else if (Constants.PAGE_AJAX.equals(bean.getProcessType())) {
			// Ajax�A��
			doAjax(request, response, bean);
			return Constants.AJAX;
		}

		request.setAttribute("bean", bean);
		return returnStr;
	}

	// /**
	// * [�T �v]:��������<br>
	// * [�� ��]:<br>
	// * [�� �l]:
	// */
	// public String doSubmit(HttpServletRequest request,HttpServletResponse
	// response,PrintListBean bean) throws Exception {
	// User user = (User)
	// request.getSession().getAttribute(Constants.SESSION_USER);
	// bean.setUserNo(StringUtil.nvl(user.getUserNo()));
	// bean.setCoNo(Integer.toString(user.getCoNo()));
	// //bean = service.getSyozokuList(bean);
	// bean = service.search(user, bean);
	// //�h���b�v�_�E�����X�g�̏��������s��
	// service.initDropDownList(bean);
	// bean.setStrFromPageId("SY110");
	// return PrintListBean.FORWARD_URL;
	//
	//
	//
	//
	//
	// }
	/**
	 * [�T �v]�����B<br>
	 * [�� ��]:�������s���B<br>
	 * [�� �l]:
	 *
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @param OD070OrdersListBean
	 *            bean
	 * @return String
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request, HttpServletResponse response, PrintListBean bean)
			throws Exception {
		/* �`�F�b�N */
		String errMsg = searchValidator(bean);
		if (!"".equals(errMsg)) {
			bean.setStrErrMsg(errMsg);
			this.initDropDownList(request, bean);

			return RETRUN_URL;
		}

		// ��������
		service.search(null);
		bean.setSearchKbn("2");
		this.initDropDownList(request, bean);

		return RETRUN_URL;
	}

	/**
	 * [�T �v]:DropDownList�����ݒ�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param HttpServletRequest
	 *            request
	 * @throws Exception
	 */
	public void initDropDownList(HttpServletRequest request, PrintListBean bean) throws Exception {
		// ���NO
		String coNo = StringUtil.nvl(bean.getLoginUser().getCoNo());

		// �x�Ђ��I������Ă�����x�X���X�g���擾����
		if (!"".equals(bean.getSisya_cd())) {
			List<Map<String, String>> sitenList = CommonService
					.getSitenList(String.valueOf(bean.getLoginUser().getCoNo()), bean.getSisya_cd(), true);
			bean.setSiten_list(sitenList);
		}

		// �ŏI�X�V����
		List<Map<String, String>> resultUpdate_hm = CommonService.getHHMM24List();
		request.setAttribute("resultUpdateHmList", resultUpdate_hm);

		// �o�^��
		List<Map<String, String>> resultMakeUserList = CommonService.getUserNameList(coNo, true);
		request.setAttribute("resultMakeUserList", resultMakeUserList);

		// ������
		List<Map<String, String>> resultSyoriJyoukyou = new ArrayList<Map<String, String>>();
		Map<String, String> mapSyoriJyoukyou = new HashMap<String, String>();

		mapSyoriJyoukyou.put("KEY", "");
		mapSyoriJyoukyou.put("VALUE", "");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "0");
		mapSyoriJyoukyou.put("VALUE", "�󒍖��m��");
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
		mapSyoriJyoukyou.put("KEY", "7");
		mapSyoriJyoukyou.put("VALUE", "�o�׎��ъm��");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		request.setAttribute("resultSyoriJyoukyou", resultSyoriJyoukyou);

		// �L�����Z��
		List<Map<String, String>> resultCancelFlg = new ArrayList<Map<String, String>>();
		Map<String, String> mapCancelFlg = new HashMap<String, String>();

		mapCancelFlg.put("KEY", "");
		mapCancelFlg.put("VALUE", "");
		resultCancelFlg.add(mapCancelFlg);

		mapCancelFlg = new HashMap<String, String>();
		mapCancelFlg.put("KEY", "0");
		mapCancelFlg.put("VALUE", "�ʏ�");
		resultCancelFlg.add(mapCancelFlg);

		mapCancelFlg = new HashMap<String, String>();
		mapCancelFlg.put("KEY", "1");
		mapCancelFlg.put("VALUE", "�L�����Z��	");
		resultCancelFlg.add(mapCancelFlg);

		request.setAttribute("resultCancelFlg", resultCancelFlg);

		// �w�b�_CSV�o��
		List<Map<String, String>> resultHeaderCSVList = CommonService.getPatternNameList(coNo, "4", true);
		request.setAttribute("resultHeaderCSVList", resultHeaderCSVList);

		// ����CSV�o��
		List<Map<String, String>> resultMeisaiCSVList = CommonService.getPatternNameList(coNo, "5", true);
		request.setAttribute("resultMeisaiCSVList", resultMeisaiCSVList);

		PrintListDAO dao = new PrintListDAO();
		// CSV�_�E�����[�h���ڐ����Ɏ擾
		int itemHeaderCnt = dao.getItemCnt(coNo, PrintListBean.CSV_HEAD_DOWN, 0);
		if (itemHeaderCnt > 0 && resultHeaderCSVList.size() > 1) {
			bean.setHeaderCsvOutFlg("show");
		} else {
			bean.setHeaderCsvOutFlg("");
		}
		int itemListCnt = dao.getItemCnt(coNo, PrintListBean.CSV_LIST_DOWN, 0);
		if (itemListCnt > 0 && resultMeisaiCSVList.size() > 1) {
			bean.setListCsvOutFlg("show");
		} else {
			bean.setListCsvOutFlg("");
		}
	}

	/**
	 * �o�׎w���f�[�^CSV�f�����[�h���C�A�E�g�ꗗ
	 * 
	 * @function update
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	public String doCSVShow(HttpServletRequest request, HttpServletResponse response, PrintListBean bean)
			throws Exception {
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
		bean.setUserNo(StringUtil.nvl(user.getUserNo()));
		bean.setCoNo(Integer.toString(user.getCoNo()));
		bean = service.showCSV(user, bean);
		request.setAttribute("bean", bean);

		// begin
		updateDownloadBegin(bean);
		return PrintListBean.FORWARD_URL;

	}

	// return Constants.AJAX;

	/**
	 * �o�׎w����ԁE
	 * 
	 * @function update
	 * @param response
	 * @param bean
	 * @throws Exception
	 */
	public int updateDownloadBegin(PrintListBean bean) {
		PrintListBean.setCsv_down_kbn("82");
		// doDownLoad(null, null, bean);
		// bean = service.downLoadCsv(bean);
		// request.setAttribute("bean", bean);
		//
		// return PrintListBean.FORWARD_URL;
		//
		// if ("1".equals(CODE)) {
		// System.out.println("--begin-");
		// do {
		//
		// } while (condition);
		//
		// } else {
		// System.out.println("�\���󂲂����܂���");
		// }

		return CODE;
	}

	/**
	 * �o�׎w���m��E
	 * 
	 * @param response
	 * @param request
	 * @function update
	 * @param response
	 * @param bean
	 * @return String
	 * @throws Exception
	 */

	// bean.setCsv_down_kbn("83");
	// bean = service.updateCSV(bean);

	/**
	 * [�T �v]:�������ڃ`�F�b�N�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param OD070OrdersListBean
	 *            bean
	 * @return String
	 * @throws Exception
	 */
	public String searchValidator(PrintListBean bean) throws Exception {
		StringBuilder sb = new StringBuilder();

		try {

			// ������
			CommonService.compareYmd(bean.getOrder_ymd_s(), bean.getOrder_ymd_e(), "������", sb);

			// �����ԍ��`�F�b�N
			if (!StringUtil.isNull(bean.getOrder_cd())) {
				if (!InputChecker.isAllHalf(bean.getOrder_cd())) {
					// ���p�`�F�b�N
					sb.append(Properties.getMsg("HA.ERROR.003", new String[] { "�����ԍ�" }, true));
				} else if (InputChecker.BytesCheck(bean.getOrder_cd(), 30)) {
					// �����`�F�b�N
					sb.append(Properties.getMsg("HA.ERROR.101", new String[] { "�����ԍ�", "30" }, true));
				}
			}

			// �����ԍ�

			// ������CD

			// �����於

			// �X�֔ԍ�

			// �Z��

			// �d�b�ԍ�

			// ���i�R�[�h

			// ���i��

			// ������

			// �P��

			// �o�א�

			// ���

			// �����N

			// NO

			// �o�b�`NO

			// �x�ЃR�[�h

			// �����敪

			// �o�ח\���
			CommonService.compareYmd(bean.getSyukka_yotei_ymd_s(), bean.getSyukka_yotei_ymd_e(), "�o�ח\���", sb);

			// �z�B�w���
			CommonService.compareYmd(bean.getTdk_ymd_s(), bean.getTdk_ymd_e(), "�z�B�w���", sb);

			// �o�^��
			CommonService.compareYmd(bean.getMake_date_s(), bean.getMake_date_e(), "�o�^��", sb);

			// �ŏI�X�V��
			CommonService.compareYmd(bean.getUpdate_ymd_s() + bean.getUpdate_hms_s(),
					bean.getUpdate_ymd_e() + bean.getUpdate_hms_e(), "�ŏI�X�V��", sb);

			if (PrintListBean.getCSV_HEAD_DOWN().equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getHeader_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[] { "�w�b�_CSV�o��" }, true));
					bean.setProcessType("");
				}
			}
			if (PrintListBean.getCSV_LIST_DOWN().equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getList_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[] { "����CSV�o��" }, true));
					bean.setProcessType("");
				}
			}

			/**
			 * ���������w��`�F�b�N �����ԍ��A�o�׎w���ԍ���XX���ȏ�̎w�肪����΂��ꂾ����OK ����ȊO�̏ꍇ�͂����ꂩ�̓��t�͈͎w��K�{
			 */
			boolean isSitei = false; // false�F�w��Ȃ� true�F�w�肠��
			if (!InputChecker.MustCheck(bean.getOrder_cd()) && bean.getOrder_cd().length() >= 7) {
				// �����ԍ���7���ȏ�w�肪�����OK
				isSitei = true;
			} else {
				// ���t�͈̓`�F�b�N
				boolean isStartSitei = false;
				boolean isEndSitei = false;
				ArrayList<Date> chkYmds = new ArrayList<Date>();
				ArrayList<Date> chkYmde = new ArrayList<Date>();
				// �����ꂩ�̊J�n���������Ă��� ���A �����ꂩ�̏I�����������Ă����OK
				if (!InputChecker.MustCheck(bean.getOrder_ymd_s())
						|| !InputChecker.MustCheck(bean.getSyukka_yotei_ymd_s())
						|| !InputChecker.MustCheck(bean.getTdk_ymd_s())
						|| !InputChecker.MustCheck(bean.getMake_date_s())
						|| !InputChecker.MustCheck(bean.getUpdate_ymd_s())) {
					// �J�n��
					isStartSitei = true;
					String defYmd = "1900/01/01";
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getOrder_ymd_s(), defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_yotei_ymd_s(), defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getTdk_ymd_s(), defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getMake_date_s(), defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getUpdate_ymd_s(), defYmd)));
				}
				if (!InputChecker.MustCheck(bean.getOrder_ymd_e())
						|| !InputChecker.MustCheck(bean.getSyukka_yotei_ymd_e())
						|| !InputChecker.MustCheck(bean.getTdk_ymd_e())
						|| !InputChecker.MustCheck(bean.getMake_date_e())
						|| !InputChecker.MustCheck(bean.getUpdate_ymd_e())) {
					// �I����
					isEndSitei = true;
					String defYmd = "2100/12/31";
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getOrder_ymd_e(), defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_yotei_ymd_e(), defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getTdk_ymd_e(), defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getMake_date_e(), defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getUpdate_ymd_e(), defYmd)));
				}
				if (isStartSitei && isEndSitei) {
					isSitei = true;
					// �͈̓`�F�b�N
					Date startYmd = Collections.max(chkYmds);
					Date endYmd = Collections.min(chkYmde);
					// ������6�����ȓ��ACSV��1�����ȓ�
					int intervalMonths = (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())
							|| "8".equals(bean.getProcessType())) ? 1 : 6;
					// �J�n����6��������I�����̎w�肪�O�ɂȂ�Ȃ��ꍇ�̓G���[
					if (!InputChecker.DateCheck(DateUtil.formatYMD(endYmd),
							DateUtil.formatYMD(DateUtil.addMonths(startYmd, intervalMonths)), true)) {
						String msg = (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())) ? "CSV�o�͂���ꍇ�́A" : "";
						sb.append(Properties.getMsg("ERROR.OD071.001",
								new String[] { msg, Integer.toString(intervalMonths) }, true));
					}
				}
			}

			// �w�蔻�� �w�肪�Ȃ��ꍇ�� ����1������
			if (!isSitei) {
				bean.setDef_ymd_e(DateUtil.getSystemYMD());
				bean.setDef_ymd_s(DateUtil.formatYMD(DateUtil.addMonths(DateUtil.getSysdate(), -1)));
			}

		} catch (Exception e) {
			throw e;
		}
		return sb.toString();
	}

	/**
	 * [�T �v]:CSV�o�́B<br>
	 * [�� ��]:�󒍈ꗗCSV�o�͂��s���B<br>
	 * [�� �l]:
	 *
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @param OD070OrdersListBean
	 *            bean
	 * @return String
	 * @throws Exception
	 */
	public String doDownLoad(HttpServletRequest request, HttpServletResponse response, PrintListBean bean)
			throws Exception {
		// CSV�w�b�_�o��
		if (PrintListBean.CSV_HEAD_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getHeader_csv_out(), "0")));
		}
		// ����CSV�o��
		if (PrintListBean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getList_csv_out(), "0")));
		}

		String errM = searchValidator(bean);
		if ("".equals(errM)) {
			errM = service.downLoad(response, bean);
		}
		if (!"".equals(errM)) {
			bean.setStrErrMsg(errM);
			bean.setSearchKbn("1");
			this.initDropDownList(request, bean);
			return RETRUN_URL;
		}
		return Constants.AJAX;
	}

	/**
	 * [�T �v]:AJAX<br>
	 * [�� ��]:�x�ЕύX���x�X�v���_�E���擾<br>
	 * [�� �l]:
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return ajax
	 * @throws Exception
	 */
	public void doAjax(HttpServletRequest request, HttpServletResponse response, PrintListBean bean) throws Exception {
		response.setContentType(Constants.AJAX_CONTENTTYPE);
		StringBuffer sb = new StringBuffer();

		List<Map<String, String>> sitenList = CommonService.getSitenList(String.valueOf(bean.getLoginUser().getCoNo()),
				bean.getSisya_cd(), true);
		bean.setSiten_list(sitenList);
		if (bean.getSiten_list() != null) {
			for (Map<String, String> item : bean.getSiten_list()) {
				// SELECT �쐬Scpipt
				sb.append("$('#siten_cd').append($('<option>').html('" + StringUtil.nvl(item.get("VALUE")) + "').val('"
						+ StringUtil.nvl(item.get("KEY")) + "')); ");
			}
		}

		PrintWriter pw = response.getWriter();
		pw.write(sb.toString());
		pw.close();
	}

}
