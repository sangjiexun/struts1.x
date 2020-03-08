package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

/**
 * [概 要]:トップメニュークラス<br>
 * [説 明]:トップメニューのデータクラス<br>
 * 著作権:   Copyright (c) 2008<br>
 * @author Toukei Computer Company 
 * @author Happy
 * @version 1.0
 * @since 1.0
 */
public class TopMenu implements java.io.Serializable {
	
	private static final long serialVersionUID = -7707537771473599831L;

	/**
	 * [概要]:メニュー名<br>
	 */
	private String MenuName;
	/**
	 * [概要]:メニューNo<br>
	 */
	private String MenuNo;
	/**
	 * [概要]:親メニューNo<br>
	 */
	private String OyaMenuNo;
	/**
	 * [概要]:メニューURL<br>
	 */
	private String MenuActionUrl;
	/**
	 * [概要]:メインイメージ<br>
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
