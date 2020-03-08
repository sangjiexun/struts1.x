package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

import java.util.List;
import java.util.Vector;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.Constants;
import jp.co.tcc.ecsolution.framework.exception.FrameworkException;

/**
 * [概 要]:ユーザーメニューツールクラス<br>
 * [説 明]:ユーザーよりメニューを表示するリストを取得する。<br>
 * 著作権:   Copyright (c) 2008<br>
 * @author Toukei Computer Company 
 * @author 李歓
 * @version 1.0
 * @since 1.0
 */
public class MenuHelper {
	
	/**
	 * [概 要]:目前のユーザーを取得し、メニューリストを表示する<br>
	 * @param user ユーザー
	 * @return メニューリスト
	 * @throws Exception 
	 */	
	public static List<Menu> getMenu(User user) throws Exception {
		List<Menu> menuList = null;
		List<TopMenu> topMenuList = null;
		try {
			
			MenuDAO menuDao = new MenuDAO();
			menuList = menuDao.getLeftMenu(user);
			topMenuList = menuDao.getTopMenu(user);
		} catch (FrameworkException e) {
			throw e;
		}finally{
			
		}
		return menuList;
	}
	/**
	 * [概 要]:目前のユーザーを取得し、メニューリストを表示する<br>
	 * @param user ユーザー
	 * @return メニューリスト
	 * @throws Exception 
	 */	
	public static List<TopMenu> getTopMenu(User user) throws Exception {
		List<TopMenu> topMenuList = null;
		try {
			
			MenuDAO menuDao = new MenuDAO();
			topMenuList = menuDao.getTopMenu(user);
		} catch (FrameworkException e) {
			throw e;
		}finally{
			
		}
		return topMenuList;
	}
	
	/**
	 * [概 要]:目前のユーザーを取得し、メニューリストを表示しJava Sessionに保存する<br>
	 * [備 考]:メニューリストにJava Sessionは存在すれば、メニューリストに直接戻り、DB操作を実行しない。
	 * @param user ユーザープロジェクト
	 * @param javaSession Java Session
	 * @return メニューリスト
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Menu> getMenu(User user,HttpSession v_session) throws Exception {
		List<Menu> menuList = null;
		List<TopMenu> topMenuList = null;
		
		if(v_session.getAttribute(Constants.SESSION_MENU) != null){
			menuList = (List<Menu>) v_session.getAttribute(Constants.SESSION_MENU);
			if(menuList != null){
				return menuList;
			}
		}
		menuList = getMenu(user);
		v_session.setAttribute(Constants.SESSION_MENU, menuList);
		topMenuList = getTopMenu(user);
		v_session.setAttribute("_topMenu", topMenuList);
		return menuList;
	}
	

	/**
	 * [概 要]:件数一覧検索。<br>
	 * [説 明]:
	 * [備 考]:
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public static Vector<HashMap<String, String>> getMenuHeaderCount(User user) throws Exception{
		MenuDAO menuDAO = new MenuDAO();
		Vector<HashMap<String, String>> result = menuDAO.getMenuHeaderCount(user);
		return result;
	}
	
}
