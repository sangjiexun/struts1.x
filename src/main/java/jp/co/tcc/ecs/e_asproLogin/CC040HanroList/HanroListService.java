package jp.co.tcc.ecs.e_asproLogin.CC040HanroList;

import java.sql.SQLException;

import jp.co.tcc.ecsolution.framework.exception.FrameworkException;

/**
 * [�T �v]:�̘H�ڍ�Service<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 */

public class HanroListService {

	
	/**
	 * [�T �v]:����<br>
	 * [�� ��]:<br>
	 * [�� �l]:<br>
	 * @param bean
	 * @throws SQLException 
	 * @throws FrameworkException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HanroListBean search(HanroListBean bean) throws Exception{
		HanroListDAO dao = new HanroListDAO();
		return dao.search(bean);
	}
}
