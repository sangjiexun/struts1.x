package jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;

/**
 * [�T �v]:�p�X���[�h�ύX��ʗpDAO<br>
 * [�� ��]:�p�X���[�h�ύX��ʂŎg�p����B<br>
 * ���쌠: Copyright (c) 2015<br>
 * 
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public class PasswordEntryDAO {

	/** PL����̖߂�̃L�[ */
	private static final String RESULT_NAME = "result";

	/** ���No�A���[�UCD�����Ƃ�USER_NO�A�ŐV��SESSION_ID���擾���� */
	private static final String CALL_HA080_GET_SESSION_DATA = "{call HA080.getUserSessionData(?,?,?)}";
	/** ���[�UNo�̃J������ */
	static final String SESSION_DATA_COLUMN_NAME_USER_NO = "USER_NO";
	/** �Z�b�V����ID�̃J������ */
	static final String SESSION_DATA_COLUMN_NAME_SESSION_ID = "SESSION_ID";

	/** ���No�A���[�UNo�����ƂɌ��݂̃p�X���[�h�A�r���p�J�E���^���擾 */
	private static final String CALL_MS210_GET_USER_INFO = "{call MS210.getUserInfo(?,?,?)}";
	/** �p�X���[�h�̃J������ */
	static final String MS210_COLUMN_NAME_LOGIN_PASSWD = "LOGIN_PASSWD";
	/** �r���p�J�E���^�̃J������ */
	static final String MS210_COLUMN_NAME_EXCL_CNT = "EXCL_CNT";

	/** �r���`�F�b�N�Ώۃe�[�u�� */
	private static final String EXCL_TABLE_NAME = "M010_USER";
	/** ���No�̃J������ */
	private static final String EXCL_COLUMN_NAME_CO_NO = "CO_NO";
	/** ���[�UNo�̃J������ */
	private static final String EXCL_COLUMN_NAME_USER_NO = "USER_NO";

	/** �p�X���[�h�X�V */
	private static final String CALL_MS210_UPDATE_DATA = "{call MS210.updateData(?,?,?)}";

	/**
	 * [�T �v]:���[�U�̃Z�b�V�������擾�B<br>
	 * [�� ��]:���No�A���[�UCD�����ƂɃ��[�UNo�A�ŐV�̃Z�b�V����ID���擾����B<br>
	 * [�� �l]:�߂�l����̒l�̎擾�ɂ́ASESSION_DATA_COLUMN_NAME_USER_NO�A
	 * SESSION_DATA_COLUMN_NAME_SESSION_ID���g�p����B
	 * 
	 * @param bean
	 * @return�@�擾�ł��Ȃ��ꍇ�Anull��ԋp�B
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> searchUserSessionData(PasswordEntryBean bean) {
		List<Object> params = new ArrayList<Object>();
		params.add(DBHelper.createSQLParamInteger(NumberUtils.toInt(bean.getCoNo(), -1)));
		params.add(DBHelper.createSQLParamChar(bean.getUserCd()));
		params.add(DBHelper.createSQLOutParamCursor(RESULT_NAME));

		Map<String, List<Map<String, String>>> map;
		try {
			map = DBHelper.executeCallableStatement(CALL_HA080_GET_SESSION_DATA, params);
		} catch (Exception e) {
			return null;
		}
		List<Map<String, String>> list = map.get(RESULT_NAME);
		return list.isEmpty() ? null : list.get(0);
	}

	// TODO CALL_HA080_GET_SESSION_DATA�ɓ����ł���B�������g��Ȃ��̂ł���΁B
	/**
	 * [�T �v]:���݃f�[�^�擾�B<br>
	 * [�� ��]:���No�A���[�UNo�����ƂɌ��݂̃p�X���[�h�A�r���p�J�E���^���擾�B<br>
	 * [�� �l]:�߂�l����̒l�̎擾�ɂ́AMS210_COLUMN_NAME_LOGIN_PASSWD�A
	 * MS210_COLUMN_NAME_EXCL_CNT���g�p����B
	 * 
	 * @param bean
	 * @return�@�擾�ł��Ȃ��ꍇ�Anull��ԋp
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> searchNowData(PasswordEntryBean bean) {
		List<Object> params = new ArrayList<Object>();
		params.add(DBHelper.createSQLParamInteger(NumberUtils.toInt(bean.getUserNo(), -1)));
		params.add(DBHelper.createSQLOutParamCursor(RESULT_NAME));
		params.add(DBHelper.createSQLParamInteger(NumberUtils.toInt(bean.getCoNo(), -1)));

		Map<String, List<Map<String, String>>> map;
		try {
			map = DBHelper.executeCallableStatement(CALL_MS210_GET_USER_INFO, params);
		} catch (Exception e) {
			return null;
		}
		List<Map<String, String>> list = map.get(RESULT_NAME);
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * [�T �v]:�r���`�F�b�N<br>
	 * 
	 * @param bean
	 * @return
	 */
	public boolean checkExcl(PasswordEntryBean bean) {
		List<String> columns = new ArrayList<String>();
		columns.add(EXCL_COLUMN_NAME_CO_NO);
		columns.add(EXCL_COLUMN_NAME_USER_NO);

		List<SQLParamType> params = new ArrayList<SQLParamType>();
		params.add(DBHelper.createSQLParamInteger(NumberUtils.toInt(bean.getCoNo(), -1)));
		params.add(DBHelper.createSQLParamInteger(NumberUtils.toInt(bean.getUserNo(), -1)));

		try {
			return DBHelper.twoFaseCommitCheck(EXCL_TABLE_NAME, NumberUtils.toInt(bean.getExclCnt(), -1), columns, params);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * [�T �v]:�X�V<br>
	 * [�� ��]:�V�����p�X���[�h�ɍX�V�B
	 * 
	 * @param bean
	 * @param encryptedPassword
	 * @throws Exception
	 */
	public void update(PasswordEntryBean bean, String encryptedPassword) throws Exception {
		List<Object> params = new ArrayList<Object>();
		params.add(DBHelper.createSQLParamInteger(bean.getCoNo()));
		params.add(DBHelper.createSQLParamInteger(bean.getUserNo()));
		params.add(DBHelper.createSQLParamVarchar(encryptedPassword));

		DBHelper.executeCallableStatement(CALL_MS210_UPDATE_DATA, params);
	}
}
