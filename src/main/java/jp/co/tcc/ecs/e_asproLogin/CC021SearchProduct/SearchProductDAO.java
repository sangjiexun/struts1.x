package jp.co.tcc.ecs.e_asproLogin.CC021SearchProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class SearchProductDAO {
	/**
	 * [�T�v]:���i����<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchProductBean searchProduct(User user,SearchProductBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc ="";

		strCallProc = "{call CC021.search(?,?,?,?,?,?,?,?,?,?,?)}";


		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCo_no()));				//���No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCd()));				//�x�ЃR�[�h
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKyotuDispFlg()));		//���ʕ\���t���O
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKyotuOnlyFlg()));		//���ʂ̂݃t���O
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getProductNmSearch()));		//���i��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getProductCdSearch()));		//���i�R�[�h
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageNo()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));

		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGECNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGENO"));

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);

		Vector<Map<String, String>> list = (Vector<Map<String,String>>)result.get("LIST");
		String strPageCnt = (String)result.get("PAGECNT");
		String strPageNo = (String)result.get("PAGENO");
		int pageCnt = 0;
		int pageNo = 0;
		if (!StringUtil.isNull(strPageCnt)){
			pageCnt = Integer.parseInt(strPageCnt);
		}
		if (!StringUtil.isNull(strPageNo)){
			pageNo = Integer.parseInt(strPageNo);
		}

		bean.setList(list);
		bean.setIntRecordCount(pageCnt);
		bean.setIntPageCount(pageNo);

		return bean;
	}
}