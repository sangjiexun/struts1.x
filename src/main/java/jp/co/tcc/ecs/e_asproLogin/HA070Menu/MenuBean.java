package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

import java.util.HashMap;
import java.util.Vector;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;
/** [�T�@�v]�F�o�^��\������N���X�B
 * [���@��]�F�I�u�W�F�N�g�̑����l���c����B
 * [���@�l]�F
 *�@���쌠: Toukei Computer Company  Copyright (c) 2008
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
