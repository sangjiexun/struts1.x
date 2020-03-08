package jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry;

/**
 * [概 要]:パスワード変更画面用Beanインターフェース<br>
 * [説 明]:パスワード変更画面へ遷移する画面のBeanはこれを実装すること。<br>
 * [備 考]:パスワード変更画面へ遷移する際、メッセージ以外の値は必須。<br>
 * 著作権: Copyright (c) 2015<br>
 * 
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public interface PasswordEntryBeanInterface {
	/** 会社引数 */
	public String getCompString();

	/** 会社No */
	public String getCoNo();

	/** ユーザCD */
	public String getUserCd();

	/** セッションID */
	public String getSessionId();

	/** メッセージ */
	public String getStrErrMsg();
}
