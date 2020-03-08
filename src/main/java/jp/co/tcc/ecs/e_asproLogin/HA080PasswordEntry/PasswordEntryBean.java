package jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

/**
 * [�T �v]:�p�X���[�h�ύX��ʗpBean<br>
 * ���쌠: Copyright (c) 2015<br>
 * 
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public class PasswordEntryBean extends BaseBean implements PasswordEntryBeanInterface {
	/** ���No */
	private String coNo;
	/** ��Ј��� */
	private String compString;
	/** ���[�UCD */
	private String userCd;
	/** ���[�UNo */
	private String userNo;
	/** �r���p�J�E���^ */
	private String exclCnt;
	/** �Z�b�V����ID */
	private String sessionId;
	/** ���݂̎��ۂ̃p�X���[�h */
	private String actualPassword;
	/** ���͒l�F���݂̃p�X���[�h */
	private String nowPassword;
	/** ���͒l�F�V�����p�X���[�h */
	private String newPassword;
	/** ���͒l�F�m�F�p�p�X���[�h */
	private String checkPassword;
	/** ���b�Z�[�W */
	private String message;

	/** ���No */
	@Override
	public String getCoNo() {
		return coNo;
	}

	/** ���No */
	public void setCoNo(String coNo) {
		this.coNo = coNo;
	}

	/** ��Ј��� */
	@Override
	public String getCompString() {
		return compString;
	}

	/** ��Ј��� */
	public void setCompString(String compString) {
		this.compString = compString;
	}

	/** ���[�UCD */
	@Override
	public String getUserCd() {
		return userCd;
	}

	/** ���[�UCD */
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	/** ���[�UNo */
	public String getUserNo() {
		return userNo;
	}

	/** ���[�UNo */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/** �r���p�J�E���^ */
	public String getExclCnt() {
		return exclCnt;
	}

	/** �r���p�J�E���^ */
	public void setExclCnt(String exclCnt) {
		this.exclCnt = exclCnt;
	}

	/** �Z�b�V����ID */
	@Override
	public String getSessionId() {
		return sessionId;
	}

	/** �Z�b�V����ID */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/** ���݂̎��ۂ̃p�X���[�h */
	public String getActualPassword() {
		return actualPassword;
	}

	/** ���݂̎��ۂ̃p�X���[�h */
	public void setActualPassword(String actualPassword) {
		this.actualPassword = actualPassword;
	}

	/** ���͒l�F���݂̃p�X���[�h */
	public String getNowPassword() {
		return nowPassword;
	}

	/** ���͒l�F���݂̃p�X���[�h */
	public void setNowPassword(String nowPassword) {
		this.nowPassword = nowPassword;
	}

	/** ���͒l�F�V�����p�X���[�h */
	public String getNewPassword() {
		return newPassword;
	}

	/** ���͒l�F�V�����p�X���[�h */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/** ���͒l�F�m�F�p�p�X���[�h */
	public String getCheckPassword() {
		return checkPassword;
	}

	/** ���͒l�F�m�F�p�p�X���[�h */
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	/** ���b�Z�[�W */
	public String getMessage() {
		return message;
	}

	/** ���b�Z�[�W */
	public void setMessage(String message) {
		this.message = message;
	}

	/** ���b�Z�[�W */
	@Override
	public String getStrErrMsg() {
		return getMessage();
	}
}
