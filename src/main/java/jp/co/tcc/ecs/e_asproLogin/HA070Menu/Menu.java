package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

/**
 * [�T �v]:���j���[�N���X<br>
 * [�� ��]:���j���[�̃f�[�^�N���X<br>
 * ���쌠:   Copyright (c) 2008<br>
 * @author Toukei Computer Company 
 * @author Happy
 * @version 1.0
 * @since 1.0
 */
public class Menu implements java.io.Serializable {
	
	private static final long serialVersionUID = -7707537771473599831L;

	/**
	 * [�T�v]:�e���j���[��<br>
	 */
	private String topMenuName;
	/**
	 * [�T�v]:�e���j���[NO<br>
	 */
	private String topMenuNo;
	
	/**
	 * [�T�v]:���j���[��<br>
	 */
	private String subMenuName;
	
	/**
	 * [�T�v]:�A�N�V����URL<br>
	 */
	private String subNenuActionUrl;
	
	/**
	 * [�T�v]:�e���j���[NO<br>
	 */
	private String oyaMenuNo;
	
	/**
	 * [�T�v]:�A�C�R���C���[�W<br>
	 */
	private String iconImageUrl;
	
	/**
	 * [�T�v]:<br>
	 */
	private String titleMenuName;
	
	/**
	 * [�T�v]:<br>
	 */
	private String subMenuCnt;
	
	/**
	 * [�T�v]:<br>
	 */
	private String topMenuCnt;

	public String getTopMenuName() {
		return topMenuName;
	}

	public void setTopMenuName(String topMenuName) {
		this.topMenuName = topMenuName;
	}

	public String getTopMenuNo() {
		return topMenuNo;
	}

	public void setTopMenuNo(String topMenuNo) {
		this.topMenuNo = topMenuNo;
	}

	public String getSubMenuName() {
		return subMenuName;
	}

	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}

	public String getSubNenuActionUrl() {
		return subNenuActionUrl;
	}

	public void setSubNenuActionUrl(String subNenuActionUrl) {
		this.subNenuActionUrl = subNenuActionUrl;
	}

	public String getOyaMenuNo() {
		return oyaMenuNo;
	}

	public void setOyaMenuNo(String oyaMenuNo) {
		this.oyaMenuNo = oyaMenuNo;
	}

	public String getIconImageUrl() {
		return iconImageUrl;
	}

	public void setIconImageUrl(String iconImageUrl) {
		this.iconImageUrl = iconImageUrl;
	}

	public String getTitleMenuName() {
		return titleMenuName;
	}

	public void setTitleMenuName(String titleMenuName) {
		this.titleMenuName = titleMenuName;
	}

	public String getSubMenuCnt() {
		return subMenuCnt;
	}

	public void setSubMenuCnt(String subMenuCnt) {
		this.subMenuCnt = subMenuCnt;
	}

	public String getTopMenuCnt() {
		return topMenuCnt;
	}

	public void setTopMenuCnt(String topMenuCnt) {
		this.topMenuCnt = topMenuCnt;
	}

}
