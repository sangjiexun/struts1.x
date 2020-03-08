package jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

/**
 * [概 要]:パスワード変更画面用Bean<br>
 * 著作権: Copyright (c) 2015<br>
 * 
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public class PasswordEntryBean extends BaseBean implements PasswordEntryBeanInterface {
	/** 会社No */
	private String coNo;
	/** 会社引数 */
	private String compString;
	/** ユーザCD */
	private String userCd;
	/** ユーザNo */
	private String userNo;
	/** 排他用カウンタ */
	private String exclCnt;
	/** セッションID */
	private String sessionId;
	/** 現在の実際のパスワード */
	private String actualPassword;
	/** 入力値：現在のパスワード */
	private String nowPassword;
	/** 入力値：新しいパスワード */
	private String newPassword;
	/** 入力値：確認用パスワード */
	private String checkPassword;
	/** メッセージ */
	private String message;

	/** 会社No */
	@Override
	public String getCoNo() {
		return coNo;
	}

	/** 会社No */
	public void setCoNo(String coNo) {
		this.coNo = coNo;
	}

	/** 会社引数 */
	@Override
	public String getCompString() {
		return compString;
	}

	/** 会社引数 */
	public void setCompString(String compString) {
		this.compString = compString;
	}

	/** ユーザCD */
	@Override
	public String getUserCd() {
		return userCd;
	}

	/** ユーザCD */
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	/** ユーザNo */
	public String getUserNo() {
		return userNo;
	}

	/** ユーザNo */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/** 排他用カウンタ */
	public String getExclCnt() {
		return exclCnt;
	}

	/** 排他用カウンタ */
	public void setExclCnt(String exclCnt) {
		this.exclCnt = exclCnt;
	}

	/** セッションID */
	@Override
	public String getSessionId() {
		return sessionId;
	}

	/** セッションID */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/** 現在の実際のパスワード */
	public String getActualPassword() {
		return actualPassword;
	}

	/** 現在の実際のパスワード */
	public void setActualPassword(String actualPassword) {
		this.actualPassword = actualPassword;
	}

	/** 入力値：現在のパスワード */
	public String getNowPassword() {
		return nowPassword;
	}

	/** 入力値：現在のパスワード */
	public void setNowPassword(String nowPassword) {
		this.nowPassword = nowPassword;
	}

	/** 入力値：新しいパスワード */
	public String getNewPassword() {
		return newPassword;
	}

	/** 入力値：新しいパスワード */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/** 入力値：確認用パスワード */
	public String getCheckPassword() {
		return checkPassword;
	}

	/** 入力値：確認用パスワード */
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	/** メッセージ */
	public String getMessage() {
		return message;
	}

	/** メッセージ */
	public void setMessage(String message) {
		this.message = message;
	}

	/** メッセージ */
	@Override
	public String getStrErrMsg() {
		return getMessage();
	}
}
