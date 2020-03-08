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
 * [概 要]:パスワード変更画面用Service<br>
 * [説 明]:パスワード変更画面で使用する。<br>
 * 著作権: Copyright (c) 2015<br>
 * 
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public class PasswordEntryService {
	/**
	 * [概 要]:実行結果。<br>
	 * [説 明]:各値を参照。<br>
	 * 著作権: Copyright (c) 2015<br>
	 * 
	 * @author Toukei Computer Company
	 * @author okuda
	 * @version 1.0
	 * @since 1.0
	 */
	static enum ResultCode {
		/** 実行成功 */
		SUCCESS,
		/** 実行失敗 */
		FAIL,
		/** 継続不可能 */
		FATAL,
	}

	/** 半角チェックエラー */
	static final String MSG_CD_HANKAKU_ERROR = "HA.ERROR.003";
	/** 半角チェックエラーの置換の値 */
	static final String[] MSG_VALUES_HANKAKU_ERROR = {
		"パスワード"
	};
	/** 変更前パスワードの比較エラー */
	static final String MSG_CD_OLD_PASSWORD_ERROR = "HA.ERROR.027";

	/** 変更前パスワードと変更後パスワードの比較エラー */
	static final String MSG_CD_SAME_PASSWORD_ERROR = "HA.ERROR.126";

	/** 新パスワードと再入力パスワードの比較エラー */
	static final String MSG_CD_NEW_PASSWORD_ERROR = "HA.ERROR.028";
	/** 新パスワード長さエラー */
	static final String MSG_CD_LENGTH_ERROR = "HA.ERROR.020";
	/** 新パスワード長さエラーの置換の値 */
	static final String[] MSG_VALUES_LENGTH_ERROR = {
		"8"
	};

	/** 継続不可能、最初から */
	static final String MSG_CD_ERROR_BACK_TO_FIRST = "ERROR.Login.004";
	/** パスワード変更完了 */
	static final String MSG_CD_SUCCSESS = "HA.ERROR.029";

	/** DAO */
	private PasswordEntryDAO dao = new PasswordEntryDAO();

	/**
	 * [概 要]:ユーザセッション情報取得。<br>
	 * [説 明]:会社No、ユーザCDをもとにユーザNo、最新のセッションIDを取得する。<br>
	 * 
	 * @param bean
	 * @return 取得できなかった場合、もしくは取得したセッションIDが空の場合、継続不可能。
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
	 * [概 要]:現在データ取得。<br>
	 * [説 明]:会社No、ユーザNoをもとに現在のパスワード、排他用カウンタを取得。<br>
	 * 
	 * @param bean
	 * @return 取得できなかった場合、継続不可能。
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
	 * [概 要]:入力値確認。<br>
	 * [説 明]:以下事項を確認。<br>
	 * ・入力された現在のパスワード、新しいパスワード、確認用パスワードの半角チェック。<br>
	 * ・暗号化後の現在のパスワードと現在の実際のパスワードの比較。<br>
	 * ・新しいパスワードと確認用パスワードの比較。<br>
	 * ・暗号化後の新しいパスワードと現在の実際のパスワードの比較。（未変更を許容しない）<br>
	 * ・新しいパスワードの長さ確認。<br>
	 * [備 考]:不正があった場合、対応するメッセージをBeanに格納。
	 * 
	 * @param bean
	 * @return 現在のパスワードの暗号化の確認に失敗した場合、継続不可能。<br>
	 *         それ以外のチェックで不正の場合、実行失敗。
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
	 * [概 要]:排他チェック。<br>
	 * 
	 * @param bean
	 * @return　不正の場合、継続不可能。
	 */
	ResultCode checkExcl(PasswordEntryBean bean) {
		if (!dao.checkExcl(bean)) {
			bean.setMessage(Properties.getMsg(Constants.EXCL_ERROR, true));
			return ResultCode.FATAL;
		}
		return ResultCode.SUCCESS;
	}

	/**
	 * [概 要]:更新。<br>
	 * [説 明]:パスワード更新。
	 * 
	 * @param bean
	 * @return　失敗した場合、継続不可能。
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
	 * [概 要]:ログアウト。<br>
	 * [説 明]:ログアウト処理。成功、失敗にかかわらずメッセージを表示する。<br>
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
	 * [概 要]:パスワード暗号化。<br>
	 * 
	 * @param password
	 * @return 暗号化に失敗した場合、空文字を返却。
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
