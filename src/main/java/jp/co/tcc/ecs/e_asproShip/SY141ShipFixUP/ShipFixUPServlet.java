package jp.co.tcc.ecs.e_asproShip.SY141ShipFixUP;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [�T �v]:�o�׎���CSV�o�^<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */

public class ShipFixUPServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;
	public static final String RETURN_JSP = "ship/SY141ShipFixUP.jsp";
	public static final String RETURN_URL = "/e_asproShip/SY141ShipFixUP";
	public static final String FORWARD_URL = "e_asproShip/SY142ShipFixUPTemp";
	ShipFixUPService service = new ShipFixUPService();

	public String doAction(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String returnStr = "";
		ShipFixUPBean bean = new ShipFixUPBean();
		setLoginUser(request, bean);
		ManageUtils manageUtils = bean.getManageUtils(request);

		//���N�G�X�g���e��BEAN�֊i�[�@���A�b�v���[�h����MultiPart�̏������p�B
		String contentType = request.getContentType();
		if (contentType != null && contentType.toLowerCase().startsWith("multipart/form-data")){
			setMultipartRequest2Bean(request, bean);
		}else{
			setRequest2Bean(request, bean);
		}
		//�q�Ƀ��[�U
		if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {
			bean.setWhCd(bean.getLoginUser().getWhCd());
		}

		// �L�����Z������߂�����
		if ("1".equals(bean.getCancelKbn())) {
			// ���[�N�폜
			service.deleteWork(bean.getUuid());
		}

		//������
		if(StringUtil.isNull(bean.getProcessType())){
			manageUtils.addListReturnURL(RETURN_URL, bean.getSearchParamMap());
			returnStr = doInit(bean);
		// �o�^����{�^�������i�A�b�v���[�h�����{���[�NINSERT�{�G���[�`�F�b�N�j
		} else if (Constants.PAGE_UPLOAD.equals(bean.getProcessType())) {
			manageUtils.addListReturnURL(RETURN_URL, bean.getSearchParamMap());
			returnStr = doUpload(bean);
		}

		initDropDownList(bean);

		request.setAttribute("bean", bean);
		return returnStr;
	}

	/**
	 * [�T �v]:DropDownList�����ݒ�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 * @param ShipFixUPBean
	 * @throws Exception
	 */
	public void initDropDownList(ShipFixUPBean bean) throws Exception {
		//���NO
		String coNo = String.valueOf(bean.getLoginUser().getCoNo());
		//�p�^�[���I��
		bean.setPatternNoList(CommonService.getPatternNoList(coNo, ShipFixUPBean.CSV_UP_KBN, true));
		//�Y���̃p�^�[����1�������Ȃ��ꍇ�͂��̃p�^�[��NO�������\������B
		if (bean.getPatternNoList().size() == 2) {
			bean.setPatternNo(bean.getPatternNoList().get(1).get("KEY"));
		}
	}

	/**
	 * [�T �v]�o�׎���CSV�o�^ �������B<br>
	 * [�� ��]:���j���[���珉�������s���B<br>
	 * [�� �l]:
	 * @param ShipFixUPBean
	 * @return String
	 * @throws Exception
	*/
	public String doInit(ShipFixUPBean bean) throws Exception {

		bean.setSearchKbn("2");
		bean = service.search(bean);

		return RETURN_JSP;
	}

	/**
	 * [�T �v]�o�׎���CSV�o�^ �A�b�v���[�h�B<br>
	 * [�� ��]:�A�b�v���[�h���s���B<br>
	 * [�� �l]:
	 * @param ShipFixUPBean
	 * @return String
	 * @throws Exception
	*/
	public String doUpload(ShipFixUPBean bean) throws Exception {
		// CSV�t�@�C���p�X
		if (bean.getCsvFile() != null) {
			bean.setCsvFilePath(fileUpload(bean.getCsvFile()));
		}
		//�G���[�`�F�b�N
		String strErrMsg = service.validator(bean);
		if (!StringUtil.isNull(strErrMsg)) {
			bean.setStrErrMsg(strErrMsg);
			doInit(bean);
			return RETURN_JSP;
		}
		// SQL�ŃG���[�`�F�b�N
		service.updateW_EA052(bean);

		//CSV�t�@�C�����́i�p�X�𔲂��j
		int csvLastIndex = bean.getCsvFile().getName().lastIndexOf("\\");
		String fileNm = bean.getCsvFile().getName().substring(csvLastIndex+1);

		StringBuilder url = new StringBuilder();
		url.append(FORWARD_URL);
		url.append("?");
		url.append("uuid=");
		url.append(bean.getUuid());
		url.append("&csvupKbn=");
		url.append(ShipFixUPBean.CSV_UP_KBN);
		url.append("&whCd=");
		url.append(bean.getWhCd());
		url.append("&patternNo=");
		url.append(bean.getPatternNo());
		url.append("&uploadNo=");
		url.append(bean.getUploadNo());
		url.append("&fileNm=");
		url.append(URLEncoder.encode(fileNm, "SHIFT_JIS"));
		// �m�F��ʑJ��
		return CommonServlet.setRedirectUrl(url.toString());
	}

}