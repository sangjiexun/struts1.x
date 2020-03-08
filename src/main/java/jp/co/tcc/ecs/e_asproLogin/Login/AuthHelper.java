package jp.co.tcc.ecs.e_asproLogin.Login;

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

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import static jp.co.tcc.ecsolution.framework.otherUtils.StringUtil.nvl;

/**
 * [�T �v]:�����c�[���N���X<br>
 * [�� ��]:�����𔻒f����֐���񋟂���<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2008<br>
 * @author Toukei Computer Company
 * @author ����
 * @version 1.0
 * @since 1.0
 */
public class AuthHelper {

	/**
	 * [�T�v]:���[�U�[�͌������g��<br>
	 */
	public static final int PERMISSION_CHECK_OK = 1;

	/**
	 * [�T�v]:���[�U�[�͌������g��Ȃ�<br>
	 */
	public static final int PERMISSION_CHECK_NG = -1;

	/**
	 * [�T�v]:���[�U�[�����̊��ԂƎ��Ԃ͗L�����ԂƗL�����Ԃł͂Ȃ��B<br>
	 */
	public static final int PERMISSION_CHECK_TIMEOUT = -2;

    /**
     * [�T�v]:MD5�N���X<br>
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
     * [�T �v]:MD5�Í���<br>
     * [�� ��]:MD5�ňÍ�������<br>
     * [�� �l]:Java��MD5��Oracle��MD5�̈Í����̌��ʂ͈Ⴄ�B
     * @param str �Í����̕K�v�����镶��
     * @return �Í�����������
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
	 * [�T �v]:���[�U�[�����̔��f<br>
	 * [�� ��]:Action�R�[�h���A���[�U�[���������g���@�\�����邩���f����B<br>
	 * @param pgId Action�R�[�h
	 * @param javaSession java session
	 * @return
	 *  PERMISSION_CHECK_OK  �����̔��f�͐���<br>
	 *  PERMISSION_CHECK_TIMEOUT �����̎��Ԃ͗L���Ȏ��Ԃł͂Ȃ�<br>
	 *  PERMISSION_CHECK_NG ������<br>
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
	 * [�T �v]:���[�U�[���O�C���̃��b�N���f<br>
	 * @param javaSession Java Session
	 * @return true:���[�U�[�����b�N���� false:���b�N���Ȃ�
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
	 * [�T �v]:���[�U�[���V�X�e�������O�C�����������f����<br>
	 * @param javaSession Java Seesion
	 * @return true:�o�^�����V�X�e�� false:���o�^
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
	 * [�T �v]:���[�U�[�̗L���o�^���Ԃł��邩���f����<br>
	 * @param javaSession Java Session
	 * @return true:OK false:NG
	 * @throws FrameworkException ���t�̓]���̓G���[
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
	 * [�T �v]:���[�U�[���O�C���̗L�����Ԃł��邩���f����<br>
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
	 * [�T �v]:���b�N���[�U�[<br>
	 * [�� ��]:���[�U�[�����b�N�A���O�C���ł��Ȃ������B<br>
	 * @param companyNo ���No
	 * @param userCd ���[�U�[�ԍ�
	 * @param lockType �p�X���[�h�G���[�̃��b�N�܂��͊Ǘ��҂̃��b�N
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
	 * [�T �v]:���[�U�[�p�X���[�h���`�F�b�N����<br>
	 * [�� ��]:�Ɩ����\�b�h�A���[�U�[�p�X���[�h�͐����������f����B<br>
	 * [�� �l]:�p�X���[�h�G���[�񐔂͍ő�̐����ɒB���鎞�ɁA�Y�����[�U�[�����b�N����邱�Ƃ��ł���B
	 * @return true�F�`�F�b�N���� false�F���[�U�[���܂��̓p�X���[�h�G���[
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
			//���[�U�[�����擾����

			UserDAO userDao = new UserDAO();
			User user = userDao.getUser(companyNo, userCd);
			//���[�U�[�͑��݂��Ȃ���΁A�߂�l�͂���������
			if(user == null){
				SuperLogger.debug("user not found. " +
								" userCd is -> "+userCd+
								" companyNo is -> "+companyNo
								);
				return false;
			}
			//���͉�ЃT�[�g
			if (StringUtil.stringEquals(companyNo, "3")){
				user.setWhCd("");
			}
			httpsession.setAttribute(Constants.SESSION_USER, user);
			//�呺���Ɖc�Ə��R�[�h
			httpsession.setAttribute(jp.co.tcc.ecs.e_asproComm.common.Constants.LOGIN_WH_CD, user.getWhCd());

			//���[�U�[�p�X���[�h�̈Í����̕������擾����B
			String md5Provider = FrameworkConfig.getFrameworkConfig(Constants.AUTH_PASSWORD_PROCESS_PROVIDER);
			//�Í����̕����͋�Ƃ���΁A���Í����̏󋵂���r���s���B
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

			//���[�U�[�p�X���[�h�̃��O�C���G���[�̉�
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
	 * [�T �v]:�Z�b�V�����c�a�֊i�[<br>
	 * [�� ��]:�Z�b�V��������ێ�����ׂɂc�a�֊i�[����B<br>
	 * [�� �l]:
	 * @return true�F���� false�F���s
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
				//DWH�Z�b�V�����쐬�O��commit 2015/04/09
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
	 * �N���C�A���gIP�`�F�b�N
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
