package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

/**
 * [�T �v]:�g�b�v���j���[�N���X<br>
 * [�� ��]:�g�b�v���j���[�̃f�[�^�N���X<br>
 * ���쌠:   Copyright (c) 2008<br>
 * @author Toukei Computer Company 
 * @author Happy
 * @version 1.0
 * @since 1.0
 */
public class TopMenu implements java.io.Serializable {
	
	private static final long serialVersionUID = -7707537771473599831L;

	/**
	 * [�T�v]:���j���[��<br>
	 */
	private String MenuName;
	/**
	 * [�T�v]:���j���[No<br>
	 */
	private String MenuNo;
	/**
	 * [�T�v]:�e���j���[No<br>
	 */
	private String OyaMenuNo;
	/**
	 * [�T�v]:���j���[URL<br>
	 */
	private String MenuActionUrl;
	/**
	 * [�T�v]:���C���C���[�W<br>
	 */
	private String MainImageUrl;
	
	
	public String getMainImageUrl() {
		return MainImageUrl;
	}
	public void setMainImageUrl(String mainImageUrl) {
		MainImageUrl = mainImageUrl;
	}
	public String getMenuActionUrl() {
		return MenuActionUrl;
	}
	public void setMenuActionUrl(String menuActionUrl) {
		MenuActionUrl = menuActionUrl;
	}
	public String getMenuName() {
		return MenuName;
	}
	public void setMenuName(String menuName) {
		MenuName = menuName;
	}
	public String getMenuNo() {
		return MenuNo;
	}
	public void setMenuNo(String menuNo) {
		MenuNo = menuNo;
	}
	public String getOyaMenuNo() {
		return OyaMenuNo;
	}
	public void setOyaMenuNo(String oyaMenuNo) {
		OyaMenuNo = oyaMenuNo;
	}

	
	
}
