package jp.co.tcc.ecs.e_asproLogin.Login;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;

import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.common.Properties;
import jp.co.tcc.ecs.e_asproComm.common.ms.MKanriBean;
import jp.co.tcc.ecs.e_asproComm.common.ms.MasterHelper;
import jp.co.tcc.ecsolution.framework.otherUtils.DateUtil;

/**
 * [概　要]：ログイン処理を実行。<br>
 * [説　明]：ログイン処理を実行。<br>
 * [備　考]：Hyper専用クラスです。
 *　著作権:Toukei Computer Company Copyright (c) 2008<br>
 * @author DHK
 * @version 1.0
 * @since 1.0
 */
public class LoginService {
// added by K.Okuda 2015/04/27 start
	/** パスワード有効期間切れアラート表示日数。 */
	private static final String KANRI_NO_PASSWORD_ALERT_DATE = "811";
	/** パスワード有効期間切れアラート表示日数に該当する場合のメッセージ。引数に日数 */
	private static final String MSG_CD_PASSWORD_ALERT = "ERROR.Login.003";

	/** 使用しているCookieのキー */
	public static enum CookieKey {
		/** セッションID */
		sessionId,
		/** アクセスキー */
		accessKey,
	};
// added by K.Okuda 2015/04/27 end

	/**
	 * [概 要]:ログイン画面検索。<br>
	 * [説 明]:<br>
	 * [備 考]:<br>
	 * @param CompanyConfigEntryBean
	 * @return LoginHyperBean
	 * @throws Exception
	 */
	public void search(LoginBean bean) throws Exception{
		

		LoginDAO dao = new LoginDAO();
		bean = dao.search(bean);
		if(bean.getList()!=null && bean.getList().size()==1){
			Map<String, String> row = bean.getList().get(0);
			bean.setColor1(row.get("COLOR1"));
			bean.setCoNo(row.get("CO_NO"));
			bean.setImg1(row.get("IMG1"));
			bean.setCompConfNm(row.get("TITLE_NAME"));
		} else {
			bean.setColor1("");
			bean.setCoNo("");
			bean.setImg1("");
		}
		
	}

// added by K.Okuda 2015/04/27 start
	/**
	 * [概 要]:Cookie追加。<br>
	 * [説 明]:引数の内容でresponseにCookieを追加する。<br>
	 * [備 考]:
	 * 
	 * @param response
	 * @param key
	 * @param parameter
	 * @param maxAge
	 */
	public void addCookie(HttpServletResponse response, CookieKey key, String parameter, int maxAge) {
		Cookie cookie = new Cookie(key.toString(), parameter);
		cookie.setMaxAge(maxAge);
		cookie.setPath(Constants.URL_BASE);
		response.addCookie(cookie);
	}

	/**
	 * [概 要]:Cookie追加。<br>
	 * [説 明]:引数の内容でresponseにCookieを追加する。<br>
	 * [備 考]:maxAgeは -1
	 * 
	 * @param response
	 * @param key
	 * @param parameter
	 */
	public void addCookie(HttpServletResponse response, CookieKey key, String parameter) {
		addCookie(response, key, parameter, -1);
	}

	/**
	 * [概 要]:Cookie削除。<br>
	 * [説 明]:引数のキーのCookieを削除する。<br>
	 * 
	 * @param response
	 * @param key
	 */
	public void removeCookie(HttpServletResponse response, CookieKey key) {
		addCookie(response, key, "", 0);
	}

	/**
	 * [概 要]:Cookie削除。<br>
	 * [説 明]:使用しているCookieをすべて削除。<br>
	 * 
	 * @param response
	 */
	public void removeAllCookies(HttpServletResponse response) {
		for (CookieKey key : CookieKey.values()) {
			removeCookie(response, key);
		}
	}

	/**
	 * [概 要]:パスワード有効期間切れのアラートメッセージ取得。<br>
	 * [説 明]:管理マスタからパスワード有効期間切れのアラート期間を取得。<br>
	 * （現在日時 + パスワード有効期間切れのアラート期間） - パスワード有効期限でアラート有無を判定。<br>
	 * [備考]:メッセージの日数はパスワード有効期限 - 現在日時 。<br>
	 * 
	 * @param coNo
	 * @param userNo
	 * @return アラート有りの場合、エラーメッセージを作成し返却。<br>
	 *         アラート無しの場合、取得に失敗した場合は空文字を返却。
	 */
	public String getPasswordAlertMsg(String validYMDEnd) {
		String result = "";
		try {
			MKanriBean bean = MasterHelper.getMKanriBean(KANRI_NO_PASSWORD_ALERT_DATE);
			int days = Integer.parseInt(bean.getKanriNumData());

			long validDate = DateUtil.parseDate(validYMDEnd).getTime();
			long alertDate = DateUtil.addSystemDays(days).getTime();
			if (alertDate > validDate) {
				long nowDate = DateUtils.truncate(DateUtil.getSysdate(), Calendar.DAY_OF_MONTH).getTime();
				int day = (int) ((validDate - nowDate) / 1000 / 60 / 60 / 24);
				result = Properties.getMsg(MSG_CD_PASSWORD_ALERT, new String[] {
					String.valueOf(day)
				}, false);
			}
		} catch (Exception e) {
			result = "";
		}
		return result;
	}
// added by K.Okuda 2015/04/27 end
}
