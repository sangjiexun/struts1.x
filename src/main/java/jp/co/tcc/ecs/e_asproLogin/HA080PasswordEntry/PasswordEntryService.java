package jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.common.InputChecker;
import jp.co.tcc.ecs.e_asproComm.common.Properties;
import jp.co.tcc.ecs.e_asproLogin.Login.LoginBean;
import jp.co.tcc.ecs.e_asproLogin.Login.LoginService;
import jp.co.tcc.ecs.e_asproUser.User.UserDAO;
import jp.co.tcc.ecsolution.framework.configUtils.FrameworkConfig;

import org.apache.commons.lang.StringUtils;

/**
 * [�T �v]:�p�X���[�h�ύX��ʗpService<br>
 * [�� ��]:�p�X���[�h�ύX��ʂŎg�p����B<br>
 * ���쌠: Copyright (c) 2015<br>
 * 
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public class PasswordEntryService {
	/**
	 * [�T �v]:���s���ʁB<br>
	 * [�� ��]:�e�l���Q�ƁB<br>
	 * ���쌠: Copyright (c) 2015<br>
	 * 
	 * @author Toukei Computer Company
	 * @author okuda
	 * @version 1.0
	 * @since 1.0
	 */
	static enum ResultCode {
		/** ���s���� */
		SUCCESS,
		/** ���s���s */
		FAIL,
		/** �p���s�\ */
		FATAL,
	}

	/** ���p�`�F�b�N�G���[ */
	static final String MSG_CD_HANKAKU_ERROR = "HA.ERROR.003";
	/** ���p�`�F�b�N�G���[�̒u���̒l */
	static final String[] MSG_VALUES_HANKAKU_ERROR = {
		"�p�X���[�h"
	};
	/** �ύX�O�p�X���[�h�̔�r�G���[ */
	static final String MSG_CD_OLD_PASSWORD_ERROR = "HA.ERROR.027";

	/** �ύX�O�p�X���[�h�ƕύX��p�X���[�h�̔�r�G���[ */
	static final String MSG_CD_SAME_PASSWORD_ERROR = "HA.ERROR.126";

	/** �V�p�X���[�h�ƍē��̓p�X���[�h�̔�r�G���[ */
	static final String MSG_CD_NEW_PASSWORD_ERROR = "HA.ERROR.028";
	/** �V�p�X���[�h�����G���[ */
	static final String MSG_CD_LENGTH_ERROR = "HA.ERROR.020";
	/** �V�p�X���[�h�����G���[�̒u���̒l */
	static final String[] MSG_VALUES_LENGTH_ERROR = {
		"8"
	};

	/** �p���s�\�A�ŏ����� */
	static final String MSG_CD_ERROR_BACK_TO_FIRST = "ERROR.Login.004";
	/** �p�X���[�h�ύX���� */
	static final String MSG_CD_SUCCSESS = "HA.ERROR.029";

	/** DAO */
	private PasswordEntryDAO dao = new PasswordEntryDAO();

	/**
	 * [�T �v]:���[�U�Z�b�V�������擾�B<br>
	 * [�� ��]:���No�A���[�UCD�����ƂɃ��[�UNo�A�ŐV�̃Z�b�V����ID���擾����B<br>
	 * 
	 * @param bean
	 * @return �擾�ł��Ȃ������ꍇ�A�������͎擾�����Z�b�V����ID����̏ꍇ�A�p���s�\�B
	 */
	ResultCode searchUserSessionData(PasswordEntryBean bean) {
		Map<String, String> map = dao.searchUserSessionData(bean);
		if (map == null) {
			return ResultCode.FATAL;
		}

		String sessionId = map.get(PasswordEntryDAO.SESSION_DATA_COLUMN_NAME_SESSION_ID);
		if (!StringUtils.isEmpty(sessionId) && StringUtils.equals(sessionId, bean.getSessionId())) {
			bean.setUserNo(map.get(PasswordEntryDAO.SESSION_DATA_COLUMN_NAME_USER_NO));
			return ResultCode.SUCCESS;
		} else {
			return ResultCode.FATAL;
		}
	}

	/**
	 * [�T �v]:���݃f�[�^�擾�B<br>
	 * [�� ��]:���No�A���[�UNo�����ƂɌ��݂̃p�X���[�h�A�r���p�J�E���^���擾�B<br>
	 * 
	 * @param bean
	 * @return �擾�ł��Ȃ������ꍇ�A�p���s�\�B
	 */
	ResultCode searchNowData(PasswordEntryBean bean) {
		Map<String, String> map = dao.searchNowData(bean);
		if (map == null) {
			return ResultCode.FATAL;
		}

		bean.setActualPassword(map.get(PasswordEntryDAO.MS210_COLUMN_NAME_LOGIN_PASSWD));
		bean.setExclCnt(map.get(PasswordEntryDAO.MS210_COLUMN_NAME_EXCL_CNT));
		return ResultCode.SUCCESS;
	}

	/**
	 * [�T �v]:���͒l�m�F�B<br>
	 * [�� ��]:�ȉ��������m�F�B<br>
	 * �E���͂��ꂽ���݂̃p�X���[�h�A�V�����p�X���[�h�A�m�F�p�p�X���[�h�̔��p�`�F�b�N�B<br>
	 * �E�Í�����̌��݂̃p�X���[�h�ƌ��݂̎��ۂ̃p�X���[�h�̔�r�B<br>
	 * �E�V�����p�X���[�h�Ɗm�F�p�p�X���[�h�̔�r�B<br>
	 * �E�Í�����̐V�����p�X���[�h�ƌ��݂̎��ۂ̃p�X���[�h�̔�r�B�i���ύX�����e���Ȃ��j<br>
	 * �E�V�����p�X���[�h�̒����m�F�B<br>
	 * [�� �l]:�s�����������ꍇ�A�Ή����郁�b�Z�[�W��Bean�Ɋi�[�B
	 * 
	 * @param bean
	 * @return ���݂̃p�X���[�h�̈Í����̊m�F�Ɏ��s�����ꍇ�A�p���s�\�B<br>
	 *         ����ȊO�̃`�F�b�N�ŕs���̏ꍇ�A���s���s�B
	 */
	ResultCode validate(PasswordEntryBean bean) {
		try {
			if (!InputChecker.isAllHalf(bean.getNowPassword()) || !InputChecker.isAllHalf(bean.getNewPassword()) || !InputChecker.isAllHalf(bean.getCheckPassword())) {
				bean.setMessage(Properties.getMsg(MSG_CD_HANKAKU_ERROR, MSG_VALUES_HANKAKU_ERROR, true));
				return ResultCode.FAIL;
			}

			String nowPassword = passwordEncrypt(bean.getNowPassword());
			if (!StringUtils.equals(nowPassword, bean.getActualPassword())) {
				bean.setMessage(Properties.getMsg(MSG_CD_OLD_PASSWORD_ERROR, true));
				return ResultCode.FAIL;
			}

			if (StringUtils.equals(bean.getNewPassword(), bean.getNowPassword())) {
				bean.setMessage(Properties.getMsg(MSG_CD_SAME_PASSWORD_ERROR, true));
				return ResultCode.FAIL;
			}

			if (!StringUtils.equals(bean.getNewPassword(), bean.getCheckPassword())) {
				bean.setMessage(Properties.getMsg(MSG_CD_NEW_PASSWORD_ERROR, true));
				return ResultCode.FAIL;
			}

			if (InputChecker.BytesCheck(bean.getNewPassword(), 8) || InputChecker.LessCheck(bean.getNewPassword(), 4)) {
				bean.setMessage(Properties.getMsg(MSG_CD_LENGTH_ERROR, MSG_VALUES_LENGTH_ERROR, true));
				return ResultCode.FAIL;
			}
			return ResultCode.SUCCESS;

		} catch (Exception e) {
			bean.setMessage(Properties.getMsg(Constants.EXCL_ERROR, true));
			return ResultCode.FATAL;
		}
	}

	/**
	 * [�T �v]:�r���`�F�b�N�B<br>
	 * 
	 * @param bean
	 * @return�@�s���̏ꍇ�A�p���s�\�B
	 */
	ResultCode checkExcl(PasswordEntryBean bean) {
		if (!dao.checkExcl(bean)) {
			bean.setMessage(Properties.getMsg(Constants.EXCL_ERROR, true));
			return ResultCode.FATAL;
		}
		return ResultCode.SUCCESS;
	}

	/**
	 * [�T �v]:�X�V�B<br>
	 * [�� ��]:�p�X���[�h�X�V�B
	 * 
	 * @param bean
	 * @return�@���s�����ꍇ�A�p���s�\�B
	 */
	ResultCode update(PasswordEntryBean bean) {
		try {
			dao.update(bean, passwordEncrypt(bean.getNewPassword()));
		} catch (Exception e) {
			return ResultCode.FATAL;
		}
		return ResultCode.SUCCESS;
	}

	/**
	 * [�T �v]:���O�A�E�g�B<br>
	 * [�� ��]:���O�A�E�g�����B�����A���s�ɂ�����炸���b�Z�[�W��\������B<br>
	 * 
	 * @param bean
	 * @param request
	 * @param msgCd
	 */
	void logout(HttpServletRequest request, HttpServletResponse response, PasswordEntryBean bean, String msgCd) {
		HttpSession session = request.getSession();
		session.removeAttribute(Constants.LOGIN_USER);

		LoginBean loginBean = new LoginBean();
		loginBean.setStrErrMsg(Properties.getMsg(msgCd, true));
		loginBean.setCompString(bean.getCompString());
		new LoginService().removeAllCookies(response);
		request.setAttribute(PasswordEntryServlet.ATTRIBUTE_KEY_BEAN, loginBean);
	}

	/**
	 * [�T �v]:�p�X���[�h�Í����B<br>
	 * 
	 * @param password
	 * @return �Í����Ɏ��s�����ꍇ�A�󕶎���ԋp�B
	 * @throws Exception
	 */
	private String passwordEncrypt(String password) throws Exception {
		String result = "";
		if (StringUtils.isEmpty(password)) {
			return result;
		}

		String config = FrameworkConfig.getFrameworkConfig(Constants.AUTH_PASSWORD_PROCESS_PROVIDER);
		if (StringUtils.equals(config, Constants.AUTH_PASSWORD_ENCODE_JAVA)) {
			result = CommonService.getJavaMD5(password);
		} else if (StringUtils.equals(config, Constants.AUTH_PASSWORD_ENCODE_ORACLE)) {
			result = new UserDAO().getDBMD5(password);
		}
		return result;
	}
}
