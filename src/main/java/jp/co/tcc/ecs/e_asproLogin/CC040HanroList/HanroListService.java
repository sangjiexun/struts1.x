package jp.co.tcc.ecs.e_asproLogin.CC040HanroList;

import java.sql.SQLException;

import jp.co.tcc.ecsolution.framework.exception.FrameworkException;

/**
 * [概 要]:販路詳細Service<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 */

public class HanroListService {

	
	/**
	 * [概 要]:検索<br>
	 * [説 明]:<br>
	 * [備 考]:<br>
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
