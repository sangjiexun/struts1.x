package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

import java.util.HashMap;
import java.util.Vector;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;
/** [概　要]：登録を表現するクラス。
 * [説　明]：オブジェクトの属性値を定議する。
 * [備　考]：
 *　著作権: Toukei Computer Company  Copyright (c) 2008
 * @author DHK
 */
public class MenuBean extends BaseBean{
	private Vector<HashMap<String, String>> leftMenu = new Vector<HashMap<String,String>>();

	public Vector<HashMap<String, String>> getLeftMenu() {
		return leftMenu;
	}

	public void setLeftMenu(Vector<HashMap<String, String>> leftMenu) {
		this.leftMenu = leftMenu;
	}
}
