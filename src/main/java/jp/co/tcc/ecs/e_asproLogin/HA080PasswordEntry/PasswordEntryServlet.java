package jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;

import jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry.PasswordEntryService.ResultCode;

/**
 * [概 要]:パスワード変更画面用Servlet<br>
 * [説 明]:passwordEntry.jspから呼ばれる。<br>
 * 著作権: Copyright (c) 2015<br>
 *
 * @author Toukei Computer Company
 * @author okuda
 * @version 1.0
 * @since 1.0
 */
public class PasswordEntryServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;

	/** パスワード変更画面。入力内容確認で引っかかった場合、再度の入力を促す。 */
	public static final String FORWARD_THIS = "passwordEntry.jsp";
	/** ログイン画面へ遷移。getForwardLoginString()を介して使用する。 */
	private static final String FORWARD_LOGIN = "login?compString=%s";
	/** リクエストに渡すBeanのキー */
	static final String ATTRIBUTE_KEY_BEAN = "bean";

	/**
	 * [概 要]:パスワード変更画面のAction。<br>
	 * [説 明]:サービスの各メソッドで実行失敗を返した場合、入力値の不正の場合は同じ画面で再度の入力を促す。<br>
	 * それ以外の場合はログイン画面に戻し、ログインからのやり直すように促す。<br>
	 * [備 考]:更新成功時、更新後の利用期間（終了） をセッションのuserに格納。
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @see //jp.co.tcc.ecs.e_asproLogin.common.CommonServlet#doAction(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PasswordEntryService service = new PasswordEntryService();
		PasswordEntryBean bean = new PasswordEntryBean();
		setRequest2Bean(request, bean);

		if (ResultCode.FATAL == service.searchUserSessionData(bean)) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		if (ResultCode.FATAL == service.searchNowData(bean)) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		ResultCode validateResult = service.validate(bean);
		if (ResultCode.FAIL == validateResult) {
			request.setAttribute(ATTRIBUTE_KEY_BEAN, bean);
			return FORWARD_THIS;
		} else if (ResultCode.FATAL == validateResult) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		if (ResultCode.FATAL == service.checkExcl(bean)) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		if (ResultCode.FATAL == service.update(bean)) {
			service.logout(request, response, bean, PasswordEntryService.MSG_CD_ERROR_BACK_TO_FIRST);
			return getForwardLoginString(bean);
		}

		service.logout(request, response, bean, PasswordEntryService.MSG_CD_SUCCSESS);
		return getForwardLoginString(bean);
	}

	/**
	 * [概 要]:ログイン画面へ遷移する文字列を取得。<br>
	 * [説 明]:compStringをリクエストパラメータに設定したものを返却。<br>
	 *
	 * @param bean
	 * @return
	 */
	private String getForwardLoginString(PasswordEntryBean bean) {
		return String.format(FORWARD_LOGIN, bean.getCompString());
	}
}
