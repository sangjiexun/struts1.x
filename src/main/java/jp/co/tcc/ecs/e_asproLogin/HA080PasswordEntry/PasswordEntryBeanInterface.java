package jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry;

/**
 * [�T �v]:�p�X���[�h�ύX��ʗpBean�C���^�[�t�F�[�X<br>
 * [�� ��]:�p�X���[�h�ύX��ʂ֑J�ڂ����ʂ�Bean�͂�����������邱�ƁB<br>
 * [�� �l]:�p�X���[�h�ύX��ʂ֑J�ڂ���ہA���b�Z�[�W�ȊO�̒l�͕K�{�B<br>
 * ���쌠: Copyright (c) 2015<br>
 * 
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public interface PasswordEntryBeanInterface {
	/** ��Ј��� */
	public String getCompString();

	/** ���No */
	public String getCoNo();

	/** ���[�UCD */
	public String getUserCd();

	/** �Z�b�V����ID */
	public String getSessionId();

	/** ���b�Z�[�W */
	public String getStrErrMsg();
}
