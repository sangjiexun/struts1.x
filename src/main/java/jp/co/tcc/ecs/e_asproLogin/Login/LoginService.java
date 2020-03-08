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
 * [�T�@�v]�F���O�C�����������s�B<br>
 * [���@��]�F���O�C�����������s�B<br>
 * [���@�l]�FHyper��p�N���X�ł��B
 *�@���쌠:Toukei Computer Company Copyright (c) 2008<br>
 * @author DHK
 * @version 1.0
 * @since 1.0
 */
public class LoginService {
// added by K.Okuda 2015/04/27 start
	/** �p�X���[�h�L�����Ԑ؂�A���[�g�\�������B */
	private static final String KANRI_NO_PASSWORD_ALERT_DATE = "811";
	/** �p�X���[�h�L�����Ԑ؂�A���[�g�\�������ɊY������ꍇ�̃��b�Z�[�W�B�����ɓ��� */
	private static final String MSG_CD_PASSWORD_ALERT = "ERROR.Login.003";

	/** �g�p���Ă���Cookie�̃L�[ */
	public static enum CookieKey {
		/** �Z�b�V����ID */
		sessionId,
		/** �A�N�Z�X�L�[ */
		accessKey,
	};
// added by K.Okuda 2015/04/27 end

	/**
	 * [�T �v]:���O�C����ʌ����B<br>
	 * [�� ��]:<br>
	 * [�� �l]:<br>
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
	 * [�T �v]:Cookie�ǉ��B<br>
	 * [�� ��]:�����̓��e��response��Cookie��ǉ�����B<br>
	 * [�� �l]:
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
	 * [�T �v]:Cookie�ǉ��B<br>
	 * [�� ��]:�����̓��e��response��Cookie��ǉ�����B<br>
	 * [�� �l]:maxAge�� -1
	 * 
	 * @param response
	 * @param key
	 * @param parameter
	 */
	public void addCookie(HttpServletResponse response, CookieKey key, String parameter) {
		addCookie(response, key, parameter, -1);
	}

	/**
	 * [�T �v]:Cookie�폜�B<br>
	 * [�� ��]:�����̃L�[��Cookie���폜����B<br>
	 * 
	 * @param response
	 * @param key
	 */
	public void removeCookie(HttpServletResponse response, CookieKey key) {
		addCookie(response, key, "", 0);
	}

	/**
	 * [�T �v]:Cookie�폜�B<br>
	 * [�� ��]:�g�p���Ă���Cookie�����ׂč폜�B<br>
	 * 
	 * @param response
	 */
	public void removeAllCookies(HttpServletResponse response) {
		for (CookieKey key : CookieKey.values()) {
			removeCookie(response, key);
		}
	}

	/**
	 * [�T �v]:�p�X���[�h�L�����Ԑ؂�̃A���[�g���b�Z�[�W�擾�B<br>
	 * [�� ��]:�Ǘ��}�X�^����p�X���[�h�L�����Ԑ؂�̃A���[�g���Ԃ��擾�B<br>
	 * �i���ݓ��� + �p�X���[�h�L�����Ԑ؂�̃A���[�g���ԁj - �p�X���[�h�L�������ŃA���[�g�L���𔻒�B<br>
	 * [���l]:���b�Z�[�W�̓����̓p�X���[�h�L������ - ���ݓ��� �B<br>
	 * 
	 * @param coNo
	 * @param userNo
	 * @return �A���[�g�L��̏ꍇ�A�G���[���b�Z�[�W���쐬���ԋp�B<br>
	 *         �A���[�g�����̏ꍇ�A�擾�Ɏ��s�����ꍇ�͋󕶎���ԋp�B
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
