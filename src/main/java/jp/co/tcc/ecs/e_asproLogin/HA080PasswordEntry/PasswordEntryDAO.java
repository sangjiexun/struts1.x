package jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;

/**
 * [概 要]:パスワード変更画面用DAO<br>
 * [説 明]:パスワード変更画面で使用する。<br>
 * 著作権: Copyright (c) 2015<br>
 * 
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public class PasswordEntryDAO {

	/** PLからの戻りのキー */
	private static final String RESULT_NAME = "result";

	/** 会社No、ユーザCDをもとにUSER_NO、最新のSESSION_IDを取得する */
	private static final String CALL_HA080_GET_SESSION_DATA = "{call HA080.getUserSessionData(?,?,?)}";
	/** ユーザNoのカラム名 */
	static final String SESSION_DATA_COLUMN_NAME_USER_NO = "USER_NO";
	/** セッションIDのカラム名 */
	static final String SESSION_DATA_COLUMN_NAME_SESSION_ID = "SESSION_ID";

	/** 会社No、ユーザNoをもとに現在のパスワード、排他用カウンタを取得 */
	private static final String CALL_MS210_GET_USER_INFO = "{call MS210.getUserInfo(?,?,?)}";
	/** パスワードのカラム名 */
	static final String MS210_COLUMN_NAME_LOGIN_PASSWD = "LOGIN_PASSWD";
	/** 排他用カウンタのカラム名 */
	static final String MS210_COLUMN_NAME_EXCL_CNT = "EXCL_CNT";

	/** 排他チェック対象テーブル */
	private static final String EXCL_TABLE_NAME = "M010_USER";
	/** 会社Noのカラム名 */
	private static final String EXCL_COLUMN_NAME_CO_NO = "CO_NO";
	/** ユーザNoのカラム名 */
	private static final String EXCL_COLUMN_NAME_USER_NO = "USER_NO";

	/** パスワード更新 */
	private static final String CALL_MS210_UPDATE_DATA = "{call MS210.updateData(?,?,?)}";

	/**
	 * [概 要]:ユーザのセッション情報取得。<br>
	 * [説 明]:会社No、ユーザCDをもとにユーザNo、最新のセッションIDを取得する。<br>
	 * [備 考]:戻り値からの値の取得には、SESSION_DATA_COLUMN_NAME_USER_NO、
	 * SESSION_DATA_COLUMN_NAME_SESSION_IDを使用する。
	 * 
	 * @param bean
	 * @return　取得できない場合、nullを返却。
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

	// TODO CALL_HA080_GET_SESSION_DATAに統合できる。既存を使わないのであれば。
	/**
	 * [概 要]:現在データ取得。<br>
	 * [説 明]:会社No、ユーザNoをもとに現在のパスワード、排他用カウンタを取得。<br>
	 * [備 考]:戻り値からの値の取得には、MS210_COLUMN_NAME_LOGIN_PASSWD、
	 * MS210_COLUMN_NAME_EXCL_CNTを使用する。
	 * 
	 * @param bean
	 * @return　取得できない場合、nullを返却
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
	 * [概 要]:排他チェック<br>
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
	 * [概 要]:更新<br>
	 * [説 明]:新しいパスワードに更新。
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
