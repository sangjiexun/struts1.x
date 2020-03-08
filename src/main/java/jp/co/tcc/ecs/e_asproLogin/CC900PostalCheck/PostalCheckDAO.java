package jp.co.tcc.ecs.e_asproLogin.CC900PostalCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class PostalCheckDAO {
	/** 
	 * [�T �v]:�Z���擾<br>
	 * [�� ��]:�X�֔ԍ�����Z�����擾����<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Vector<Map<String, String>> get_addr(String tdk_zip1, String tdk_zip2) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call CC900.get_addr(?,?)}";
		
		listParams.add(DBHelper.createSQLParamVarchar2(tdk_zip1+tdk_zip2));		//�X�֔ԍ�
		listParams.add(DBHelper.createSQLOutParamCursor("ADDR"));				//�Z��

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		Vector<Map<String, String>> addr = (Vector<Map<String,String>>)result.get("ADDR");

		return addr;
	}

	/** 
	 * [�T �v]:��CD�擾<br>
	 * [�� ��]:�X�֔ԍ����猧CD���擾����<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public int get_ken_cd(String addr) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call CC900.get_ken_cd(?,?)}";
		
		listParams.add(DBHelper.createSQLParamVarchar2(addr));					//�Z��
		listParams.add(DBHelper.createSQLOutParamInteger("KEN_CD"));			//��CD

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		String strKenCd = (String)result.get("KEN_CD");
		int kenCd = 0;
		if (!StringUtil.isNull(strKenCd)){
			kenCd = Integer.parseInt(strKenCd);
		}

		return kenCd;
	
	}
	
	
	/** 
	 * [�T �v]:�Z������<br>
	 * [�� ��]:�X�֔ԍ�����Z������������<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Vector<Map<String, String>> search(String tdk_zip1, String tdk_zip2) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call CC900.search(?,?)}";

		listParams.add(DBHelper.createSQLParamVarchar2(tdk_zip1+tdk_zip2));		//�X�֔ԍ�
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		Vector<Map<String, String>> list = (Vector<Map<String,String>>)result.get("LIST");
		return list;
	
	}

}
