package jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproLogin.common.CommonServlet;
import jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry.PasswordEntryService.ResultCode;

/**
 * [�T �v]:�p�X���[�h�ύX��ʗpServlet<br>
 * [�� ��]:passwordEntry.jsp����Ă΂��B<br>
 * ���쌠: Copyright (c) 2015<br>
 * 
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public class PasswordEntryServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;

	/** �p�X���[�h�ύX��ʁB���͓��e�m�F�ň������������ꍇ�A�ēx�̓��͂𑣂��B */
	public static final String FORWARD_THIS = "passwordEntry.jsp";
	/** ���O�C����ʂ֑J�ځBgetForwardLoginString()����Ďg�p����B */
	private static final String FORWARD_LOGIN = "login?compString=%s";
	/** ���N�G�X�g�ɓn��Bean�̃L�[ */
	static final String ATTRIBUTE_KEY_BEAN = "bean";

	/**
	 * [�T �v]:�p�X���[�h�ύX��ʂ�Action�B<br>
	 * [�� ��]:�T�[�r�X�̊e���\�b�h�Ŏ��s���s��Ԃ����ꍇ�A���͒l�̕s���̏ꍇ�͓�����ʂōēx�̓��͂𑣂��B<br>
	 * ����ȊO�̏ꍇ�̓��O�C����ʂɖ߂��A���O�C������̂�蒼���悤�ɑ����B<br>
	 * [�� �l]:�X�V�������A�X�V��̗��p���ԁi�I���j ���Z�b�V������user�Ɋi�[�B
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @see jp.co.tcc.ecs.e_asproLogin.common.CommonServlet#doAction(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PasswordEntryService service = new PasswordEntryService();
		PasswordEntryBean bean = new PasswordEntryBean();
		setRequest2Bean(request, bean);

		if (ResultCode.FATAL == service.searchUserSessionData(bean)) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		if (ResultCode.FATAL == service.searchNowData(bean)) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		ResultCode validateResult = service.validate(bean);
		if (ResultCode.FAIL == validateResult) {
			request.setAttribute(ATTRIBUTE_KEY_BEAN, bean);
			return FORWARD_THIS;
		} else if (ResultCode.FATAL == validateResult) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		if (ResultCode.FATAL == service.checkExcl(bean)) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		if (ResultCode.FATAL == service.update(bean)) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		service.logout(request, response, bean, PasswordEntryService.MSG_CD_SUCCSESS);
		return getForwardLoginString(bean);
	}

	/**
	 * [�T �v]:���O�C����ʂ֑J�ڂ��镶������擾�B<br>
	 * [�� ��]:compString�����N�G�X�g�p�����[�^�ɐݒ肵�����̂�ԋp�B<br>
	 * 
	 * @param bean
	 * @return
	 */
	private String getForwardLoginString(PasswordEntryBean bean) {
		return String.format(FORWARD_LOGIN, bean.getCompString());
	}
}
