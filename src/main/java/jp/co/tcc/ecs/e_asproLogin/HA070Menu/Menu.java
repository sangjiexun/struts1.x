package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

/**
 * [概 要]:メニュークラス<br>
 * [説 明]:メニューのデータクラス<br>
 * 著作権:   Copyright (c) 2008<br>
 * @author Toukei Computer Company 
 * @author Happy
 * @version 1.0
 * @since 1.0
 */
public class Menu implements java.io.Serializable {
	
	private static final long serialVersionUID = -7707537771473599831L;

	/**
	 * [概要]:親メニュー名<br>
	 */
	private String topMenuName;
	/**
	 * [概要]:親メニューNO<br>
	 */
	private String topMenuNo;
	
	/**
	 * [概要]:メニュー名<br>
	 */
	private String subMenuName;
	
	/**
	 * [概要]:アクションURL<br>
	 */
	private String subNenuActionUrl;
	
	/**
	 * [概要]:親メニューNO<br>
	 */
	private String oyaMenuNo;
	
	/**
	 * [概要]:アイコンイメージ<br>
	 */
	private String iconImageUrl;
	
	/**
	 * [概要]:<br>
	 */
	private String titleMenuName;
	
	/**
	 * [概要]:<br>
	 */
	private String subMenuCnt;
	
	/**
	 * [概要]:<br>
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
