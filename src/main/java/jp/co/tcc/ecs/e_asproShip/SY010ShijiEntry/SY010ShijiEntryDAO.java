package jp.co.tcc.ecs.e_asproShip.SY010ShijiEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;


public class SY010ShijiEntryDAO {
	/**
	 * 出荷データ検索
	 * @param co_no
	 * @param seq_no
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, List<Map<String, String>>> search(String co_no, String seq_no) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(co_no));
		listParams.add(DBHelper.createSQLParamVarchar2(seq_no));
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamCursor("LIST_DETAIL"));

		String strCallProc = CommonService.createCallableStatement("SY010.select_info", listParams.size());
		HashMap<String, List<Map<String, String>>> result =
				(HashMap<String, List<Map<String, String>>>)DBHelper.executeCallableStatement(strCallProc, listParams);
		return result;
	}
}

