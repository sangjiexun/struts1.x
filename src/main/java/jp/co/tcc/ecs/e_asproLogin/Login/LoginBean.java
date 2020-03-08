package jp.co.tcc.ecs.e_asproLogin.Login;

import java.util.Map;
import java.util.Vector;

import jp.co.tcc.ecs.e_asproLogin.HA080PasswordEntry.PasswordEntryBeanInterface;

/**
 * [�T �v]�F��Ѝ\���}�X�^JavaBean�B<br>
 * [�� ��]�F��Ѝ\���}�X�^JavaBean�B<br>
 * [�� �l]�F<br>
 * ���쌠:Toukei Computer Company Copyright (c) 2008<br>
 * @author DHK
 * @version 1.0
 * @since 1.0
 */
// added by K.Okuda 2015/04/27 interface��ǉ��B�Ώۃ��\�b�h��@Override��ǉ��B
public class LoginBean implements PasswordEntryBeanInterface {
	// ���NO
	private String coNo="";
	// ��Ѝ\����COMP_CONF_NM
	private String compConfNm="";
	// ��Ј���
	private String compString="";
	// �F�P
	private String color1="";
	// �摜�P
	private String img1="";
	// �����pList
	private Vector list;
	// ��������
	private String listsize="";
	private String userCd="";
	private String password="";
	//url
	private String strURL = "";
	private String strErrMsg = "";
	private String clientcd = "";

// added by K.Okuda 2015/04/27 start
	/** �Z�b�V����ID */
	private String sessionId = "";
// added by K.Okuda 2015/04/27 end

	@Override
	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getCoNo() {
		return coNo;
	}

	public void setCoNo(String coNo) {
		this.coNo = coNo;
	}

	public String getCompConfNm() {
		return compConfNm;
	}

	public void setCompConfNm(String compConfNm) {
		this.compConfNm = compConfNm;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public Vector<Map<String, String>> getList() {
		return list;
	}

	public void setList(Vector<Map<String, String>> list) {
		this.list = list;
	}

	public String getListsize() {
		return listsize;
	}

	public void setListsize(String listsize) {
		this.listsize = listsize;
	}

	public String getStrURL() {
		return strURL;
	}

	public void setStrURL(String strURL) {
		this.strURL = strURL;
	}

	@Override
	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public String getClientcd() {
		return clientcd;
	}

	public void setClientcd(String clientcd) {
		this.clientcd = clientcd;
	}

	@Override
	public String getCompString() {
		return compString;
	}

	public void setCompString(String compString) {
		this.compString = compString;
	}

// added by K.Okuda 2015/04/27 start
	/** �Z�b�V����ID */
	@Override
	public String getSessionId() {
		return sessionId;
	}

	/** �Z�b�V����ID */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
// added by K.Okuda 2015/04/27 end
}
