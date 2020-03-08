package jp.co.tcc.ecs.e_asproLogin.Login;

import static jp.co.tcc.ecsolution.framework.otherUtils.StringUtil.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecs.e_asproUser.User.UserDAO;
import jp.co.tcc.ecsolution.framework.Constants;
import jp.co.tcc.ecsolution.framework.configUtils.FrameworkConfig;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.exception.FrameworkException;
import jp.co.tcc.ecsolution.framework.logUtils.SuperLogger;
import jp.co.tcc.ecsolution.framework.otherUtils.DateUtil;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概 要]:権限ツールクラス<br>
 * [説 明]:権限を判断する関数を提供する<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2008<br>
 * @author Toukei Computer Company
 * @author 李歓
 * @version 1.0
 * @since 1.0
 */
public class AuthHelper {

	/**
	 * [概要]:ユーザーは権限を使う<br>
	 */
	public static final int PERMISSION_CHECK_OK = 1;

	/**
	 * [概要]:ユーザーは権限を使わない<br>
	 */
	public static final int PERMISSION_CHECK_NG = -1;

	/**
	 * [概要]:ユーザー権限の期間と時間は有効期間と有効時間ではない。<br>
	 */
	public static final int PERMISSION_CHECK_TIMEOUT = -2;

    /**
     * [概要]:MD5クラス<br>
     */
    private static MessageDigest MD5;

    static{
        try {
            MD5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
//        	String uuid = UUIDUtil.getUUID();
			SuperLogger.fatal(Constants.LOG_CONFIG_SPLIT
					+Constants.FREAMWORK_ERROR_USER
					+Constants.LOG_CONFIG_SPLIT
					+"AuthHelper"
					+Constants.LOG_CONFIG_SPLIT
					+"md5 init error." + "message is \n"+e.getMessage()
					);
        }
    }
    /**
     * [概 要]:MD5暗号化<br>
     * [説 明]:MD5で暗号化する<br>
     * [備 考]:JavaのMD5とOracleのMD5の暗号化の結果は違う。
     * @param str 暗号化の必要がある文字
     * @return 暗号化した文字
     */
    public static String getJavaMD5(String str){
    	String temp = StringUtil.nvl(str);
    	byte[] b_data = MD5.digest(temp.getBytes());
        StringBuffer buffer = new StringBuffer();
        Formatter FMT = new Formatter(buffer);
        buffer.setLength(0);
        for (byte b : b_data) {
            FMT.format("%02X", b);
        }
    	return buffer.toString();
    }


	/**
	 * [概 要]:ユーザー権限の判断<br>
	 * [説 明]:Actionコードより、ユーザーが権限を使う機能があるか判断する。<br>
	 * @param pgId Actionコード
	 * @param javaSession java session
	 * @return
	 *  PERMISSION_CHECK_OK  権限の判断は成功<br>
	 *  PERMISSION_CHECK_TIMEOUT 権限の時間は有効な時間ではない<br>
	 *  PERMISSION_CHECK_NG 無権限<br>
	 */
	public static int userPermissionCheck(String pgId,Map<Object,Object> javaSession) {

		if (StringUtil.isNull(pgId)) {
			//OK
			return PERMISSION_CHECK_OK;
		}

		User user = (User) javaSession.get(Constants.SESSION_USER);

		if(user.getPatternMenu().containsKey(pgId)){
			return PERMISSION_CHECK_OK;
		}else{
			return PERMISSION_CHECK_NG;
		}
	}

	/**
	 * [概 要]:ユーザーログインのロック判断<br>
	 * @param javaSession Java Session
	 * @return true:ユーザーをロックする false:ロックしない
	 */
	public static boolean userLockCheck(Map<Object,Object> javaSession) {
		User user = (User) javaSession.get(Constants.SESSION_USER);
		if(user == null){
			return true;
		}
		if (StringUtil.stringEquals(user.getLoginLockFlag(),Constants.AUTH_NO_LOCK)) {
			return false;
		}
		return true;
	}

	/**
	 * [概 要]:ユーザーがシステムをログインしたか判断する<br>
	 * @param javaSession Java Seesion
	 * @return true:登録したシステム false:未登録
	 */
	public static boolean userLoginCheck(Map<Object,Object> javaSession) {
		if (javaSession.containsKey(Constants.SESSION_USER) == true) {
			if(javaSession.get(Constants.SESSION_USER) != null){
				return true;
			}
		}
		return false;
	}

	/**
	 * [概 要]:ユーザーの有効登録期間であるか判断する<br>
	 * @param javaSession Java Session
	 * @return true:OK false:NG
	 * @throws FrameworkException 日付の転換はエラー
	 */
	public static boolean userValidPeriodCheck(Map<Object,Object> javaSession) throws FrameworkException {
		User user = (User) javaSession.get(Constants.SESSION_USER);
		if(user == null){
			return false;
		}
		try {
			if (DateUtil.between(DateUtil.getSystemYMD(),
								 StringUtil.nvl(user.getValidYMDBegin(), Constants.AUTH_DEFAULT_VALID_BEGIN),
								 StringUtil.nvl(user.getValidYMDEnd(),Constants.AUTH_DEFAULT_VALID_END)) == true) {
				return true;
			}
		} catch (ParseException e) {
			throw new FrameworkException("userValidPeriodCheck.userValidPeriodCheck date check process error." +
					  "message is \n"+e.getMessage());
		}
		return false;
	}

	/**
	 * [概 要]:ユーザーログインの有効期間であるか判断する<br>
	 * @param javaSession Java Session
	 * @return true:OK false:NG
	 */
	public static boolean userLoginTimeScopeCheck(Map<Object,Object> javaSession) {
		User user = (User) javaSession.get(Constants.SESSION_USER);
		if(user == null){
			return false;
		}
		String currentTime = DateUtil.getSystemHM();
		if (DateUtil.timeBetween(currentTime,
								 StringUtil.nvl(user.getUseValidTimeBegin(),Constants.AUTH_DEFAULT_VALID_TIME_BEGIN),
								 StringUtil.nvl(user.getUseValidTimeEnd(),Constants.AUTH_DEFAULT_VALID_TIME_END))) {
			return true;
		}
		return false;
	}

	/**
	 * [概 要]:ロックユーザー<br>
	 * [説 明]:ユーザーをロック、ログインできない処理。<br>
	 * @param companyNo 会社No
	 * @param userCd ユーザー番号
	 * @param lockType パスワードエラーのロックまたは管理者のロック
	 * @param currentUserNo current user no
	 * @throws FrameworkException
	 */
	public static void lockUser(String companyNo,
								String userCd,
								String lockType,
								int currentUserNo
								) throws FrameworkException{
		UserDAO userDao = new UserDAO();
		userDao.lockUser(companyNo, userCd, lockType, currentUserNo);
	}

	/**
	 * [概 要]:ユーザーパスワードをチェックする<br>
	 * [説 明]:業務メソッド、ユーザーパスワードは正しいか判断する。<br>
	 * [備 考]:パスワードエラー回数は最大の制限に達する時に、該当ユーザーがロックされることができる。
	 * @return true：チェック成功 false：ユーザー名またはパスワードエラー
	 * @throws FrameworkException
	 */
	public static boolean userPasswordCheck(String companyNo,
											String userCd,
											String password,
											HttpSession httpsession) throws FrameworkException{
		try {
			if(StringUtil.isNull(userCd)){
				return false;
			}
			//ユーザー情報を取得する

			UserDAO userDao = new UserDAO();
			User user = userDao.getUser(companyNo, userCd);
			//ユーザーは存在しなければ、戻り値はｆａｌｓｅ
			if(user == null){
				SuperLogger.debug("user not found. " +
								" userCd is -> "+userCd+
								" companyNo is -> "+companyNo
								);
				return false;
			}
			//協力会社サート
			if (StringUtil.stringEquals(companyNo, "3")){
				user.setWhCd("");
			}
			httpsession.setAttribute(Constants.SESSION_USER, user);
			//大村紙業営業所コード
			httpsession.setAttribute(jp.co.tcc.ecs.e_asproComm.common.Constants.LOGIN_WH_CD, user.getWhCd());

			//ユーザーパスワードの暗号化の方式を取得する。
			String md5Provider = FrameworkConfig.getFrameworkConfig(Constants.AUTH_PASSWORD_PROCESS_PROVIDER);
			//暗号化の方式は空とすれば、未暗号化の状況より比較を行う。
			if(StringUtil.stringEquals(Constants.AUTH_PASSWORD_ENCODE_JAVA, md5Provider) == true){
				//Java MD5
				if(StringUtil.stringEquals(user.getLoginPassword(),getJavaMD5(nvl(password)))==true){
					userDao.updateOKTempNum5(companyNo, user.getUserNo());//add 2011.07.01 sakakibara
					return true;
				}
			}else if(StringUtil.stringEquals(Constants.AUTH_PASSWORD_ENCODE_ORACLE, md5Provider) == true){
				//DB MD5
				if(StringUtil.stringEquals(user.getLoginPassword(),userDao.getDBMD5(nvl(password)))==true){
					userDao.updateOKTempNum5(companyNo, user.getUserNo()); //add 2011.07.01 sakakibara
					return true;
				}
			}else{
				if(StringUtil.stringEquals(user.getLoginPassword(),nvl(password))==true){
					userDao.updateOKTempNum5(companyNo, user.getUserNo()); //add 2011.07.01 sakakibara
					return true;
				}
			}

			//ユーザーパスワードのログインエラーの回数
			if(user.getRepeadErrorCount() > 0){
				//chg 2011.07.01 k.sakakibara
				int retryCount = user.getNgCnt();

				retryCount++;

				//chg 2011.07.01 k.sakakibara
				//httpsession.setAttribute(Constants.SESSION_PASSWORD_ERROR_RETRY_COUNT,retryCount);
				userDao.updateNgTempNum5(companyNo, user.getUserNo());


				if(retryCount >= user.getRepeadErrorCount()
					&& !StringUtil.stringEquals(user.getLoginLockFlag(), Constants.AUTH_NO_LOCK)){

					lockUser(companyNo,userCd,Constants.AUTH_USER_LOCK_TYPE_ERROR,Constants.SYS_USER_NO);
					DBHelper.commit();
				}
			}
			return false;

		} catch (FrameworkException e) {
			DBHelper.rollbackNoExcetpion();
			throw e;
		}finally{

		}
	}

	/**
	 * [概 要]:セッションＤＢへ格納<br>
	 * [説 明]:セッション情報を保持する為にＤＢへ格納する。<br>
	 * [備 考]:
	 * @return true：成功 false：失敗
	 * @throws FrameworkException
	 */
	public static boolean insertSessionDB(String sessionId, String accessKey, User user,String remote_host) throws FrameworkException{
		try {
			if(StringUtil.isNull(sessionId)){
				return false;
			}

			StringBuffer prepareSql = new StringBuffer();
			prepareSql.append(" insert into W_EA000_SESSION( ");
			prepareSql.append("  SESSION_ID, ");
			prepareSql.append("  ACCESS_KEY, ");
			prepareSql.append("  FIRST_REQ_DATE, ");
			prepareSql.append("  LAST_REQ_DATE, ");
			prepareSql.append("  KANRI_SITE_FLG, ");
			prepareSql.append("  CO_NO, ");
			prepareSql.append("  USER_NO, ");
			prepareSql.append("  KOKYAKU_NO, ");
			//add 2011.09.12 K.sakakibara IP
			prepareSql.append("  REMOTE_HOST ");
			prepareSql.append(" )values( ");
			prepareSql.append("  ?, ");
			prepareSql.append("  ?, ");
			prepareSql.append("  sysdate, ");
			prepareSql.append("  sysdate, ");
			prepareSql.append("  1, ");
			prepareSql.append("  ?, ");
			prepareSql.append("  ?, ");
			prepareSql.append("  0, ");
			//add 2011.09.12 sakakibara IP
			prepareSql.append("  ? )");

			// Add params
			List<SQLParamType> listParams = new ArrayList<SQLParamType>();
			listParams.add(DBHelper.createSQLParamVarchar2(sessionId));
			listParams.add(DBHelper.createSQLParamVarchar2(accessKey));
			listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));
			listParams.add(DBHelper.createSQLParamInteger(user.getUserNo()));
			listParams.add(DBHelper.createSQLParamVarchar2(remote_host));//add 2011.09.12
			try{
				DBHelper.execUpdateWithPreparedStatement(prepareSql.toString(), listParams);
				//DWHセッション作成前にcommit 2015/04/09
				DBHelper.commit();
			} catch (SQLException e){
				throw new FrameworkException("AuthHelper.insertSessionDB sql error." +
						  "message is \n"+e.getMessage());
			}
			return true;
		} catch (FrameworkException e) {
			DBHelper.rollbackNoExcetpion();
			throw e;
		}finally{

		}
	}
	/**
	 * クライアントIPチェック
	 * add 2011.09.12 K.sakakiabra
	 * @param coNo
	 * @param userCd
	 * @param remote_host
	 * @return
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public static boolean checkRemoteHost(String coNo,String userCd,String remote_host) throws Exception{

		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc ="{call SESSION_INFO.check_remote_host(?,?,?,?)}";

		listParams.add(DBHelper.createSQLParamVarchar2(coNo));
		listParams.add(DBHelper.createSQLParamVarchar2(userCd));
		listParams.add(DBHelper.createSQLParamVarchar2(remote_host));
		listParams.add(DBHelper.createSQLOutParamVarchar2("ERRFLG"));
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		String errFlg = (String)result.get("ERRFLG");
		if ("1".equals(errFlg)){
			return false;
		}
		return true;
	}

}
