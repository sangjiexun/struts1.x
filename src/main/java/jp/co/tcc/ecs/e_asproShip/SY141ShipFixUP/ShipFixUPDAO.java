package jp.co.tcc.ecs.e_asproShip.SY141ShipFixUP;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.exception.FrameworkException;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概 要]:出荷実績CSV登録<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */

public class ShipFixUPDAO {

	/**
	 * [概 要]:出荷実績CSV登録。<br>
	 * [説 明]:CSV登録したデータを検索する。<br>
	 * [備 考]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	*/
	@SuppressWarnings("unchecked")
	public ShipFixUPBean search(ShipFixUPBean bean) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		listParams.add(DBHelper.createSQLParamVarchar2(ShipFixUPBean.CSV_UP_KBN));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWhCd()));
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));

		String strCallProc = CommonService.createCallableStatement("SY141.search", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setList((Vector<Map<String,String>>)result.get("LIST"));

		return bean;
	}

	/**
	 * [概 要]:CSVファイルワークエラーメッセジー更新。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 * @param bean
	 * @return エラー有無 true:有る/false：無い
	 * @throws FrameworkException
	 * @throws SQLException
	 */
	public boolean updateW_EA052(ShipFixUPBean bean) throws FrameworkException, SQLException {
		boolean f_rlt = true;
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUuid()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		listParams.add(DBHelper.createSQLParamVarchar2(ShipFixUPBean.CSV_UP_KBN));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWhCd()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getPatternNo()));
		listParams.add(DBHelper.createSQLOutParamInteger("ERR_CNT"));

		String strCallProc = CommonService.createCallableStatement("SY141.updateW_EA052", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);

		String err_cnt = StringUtil.nvl(result.get("ERR_CNT"), "0");
		if ("0".equals(err_cnt)) {
			f_rlt = true;
		} else {
			f_rlt = false;
		}
		return f_rlt;
	}

	/**
	 * [概 要]:CSVワーク削除。<br>
	 * [説 明]:キャンセルした場合CSVワークを削除します。<br>
	 * [備 考]:
	 * @param String uuid
	 * @throws Exception
	 */
	public void deleteWork(String uuid) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(uuid));
		String strCallProc = CommonService.createCallableStatement("SY141.deleteWork", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
	}
}
