package jp.co.tcc.ecs.e_asproLogin.CC900PostalCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class PostalCheckDAO {
	/** 
	 * [概 要]:住所取得<br>
	 * [説 明]:郵便番号から住所を取得する<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Vector<Map<String, String>> get_addr(String tdk_zip1, String tdk_zip2) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call CC900.get_addr(?,?)}";
		
		listParams.add(DBHelper.createSQLParamVarchar2(tdk_zip1+tdk_zip2));		//郵便番号
		listParams.add(DBHelper.createSQLOutParamCursor("ADDR"));				//住所

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		Vector<Map<String, String>> addr = (Vector<Map<String,String>>)result.get("ADDR");

		return addr;
	}

	/** 
	 * [概 要]:県CD取得<br>
	 * [説 明]:郵便番号から県CDを取得する<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public int get_ken_cd(String addr) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call CC900.get_ken_cd(?,?)}";
		
		listParams.add(DBHelper.createSQLParamVarchar2(addr));					//住所
		listParams.add(DBHelper.createSQLOutParamInteger("KEN_CD"));			//県CD

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		String strKenCd = (String)result.get("KEN_CD");
		int kenCd = 0;
		if (!StringUtil.isNull(strKenCd)){
			kenCd = Integer.parseInt(strKenCd);
		}

		return kenCd;
	
	}
	
	
	/** 
	 * [概 要]:住所検索<br>
	 * [説 明]:郵便番号から住所を検索する<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Vector<Map<String, String>> search(String tdk_zip1, String tdk_zip2) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call CC900.search(?,?)}";

		listParams.add(DBHelper.createSQLParamVarchar2(tdk_zip1+tdk_zip2));		//郵便番号
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		Vector<Map<String, String>> list = (Vector<Map<String,String>>)result.get("LIST");
		return list;
	
	}

}
