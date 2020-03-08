package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

import java.util.List;
import java.util.Vector;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.Constants;
import jp.co.tcc.ecsolution.framework.exception.FrameworkException;

/**
 * [�T �v]:���[�U�[���j���[�c�[���N���X<br>
 * [�� ��]:���[�U�[��胁�j���[��\�����郊�X�g���擾����B<br>
 * ���쌠:   Copyright (c) 2008<br>
 * @author Toukei Computer Company 
 * @author ����
 * @version 1.0
 * @since 1.0
 */
public class MenuHelper {
	
	/**
	 * [�T �v]:�ڑO�̃��[�U�[���擾���A���j���[���X�g��\������<br>
	 * @param user ���[�U�[
	 * @return ���j���[���X�g
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
	 * [�T �v]:�ڑO�̃��[�U�[���擾���A���j���[���X�g��\������<br>
	 * @param user ���[�U�[
	 * @return ���j���[���X�g
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
	 * [�T �v]:�ڑO�̃��[�U�[���擾���A���j���[���X�g��\����Java Session�ɕۑ�����<br>
	 * [�� �l]:���j���[���X�g��Java Session�͑��݂���΁A���j���[���X�g�ɒ��ږ߂�ADB��������s���Ȃ��B
	 * @param user ���[�U�[�v���W�F�N�g
	 * @param javaSession Java Session
	 * @return ���j���[���X�g
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
	 * [�T �v]:�����ꗗ�����B<br>
	 * [�� ��]:
	 * [�� �l]:
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
