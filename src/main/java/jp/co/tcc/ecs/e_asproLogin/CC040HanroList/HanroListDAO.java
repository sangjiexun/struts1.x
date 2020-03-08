package jp.co.tcc.ecs.e_asproLogin.CC040HanroList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.exception.FrameworkException;

/**
 * [�T �v]:�̘H�ڍ�DAO<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 */

public class HanroListDAO {
	
	/**
	 * [�T �v]:����<br>
	 * [�� ��]:<br>
	 * [�� �l]:<br>
	 * @param bean
	 * @throws SQLException 
	 * @throws FrameworkException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HanroListBean search(HanroListBean bean) throws FrameworkException, SQLException{
		ArrayList<Object> listParams = new ArrayList<Object>();
		
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItemNo()));
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));

		String strCallProc = CommonService.createCallableStatement("CC040.search", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);

		List<Map<String, String>> list = (List<Map<String,String>>)result.get("LIST");
		bean.setDataList(list);;
		return bean;
	}
}
