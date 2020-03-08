package jp.co.tcc.ecs.e_asproLogin.CC030SearchCustomer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class SearchCustomerDAO {
	/**
	 * [概要]:顧客検索<br>
	 * [説 明]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchCustomerBean searchCustomer(User user,SearchCustomerBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//会社No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCdSearch()));		//外部コード
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuCdSearch()));		//お客様コード
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuNmSearch()));		//注文者名
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuAddrSearch()));	//住所
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuTelSearch()));	//電話番号
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuFaxSearch()));	//FAX

		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageNo()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));

		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGECNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGENO"));

		String strCallProc = CommonService.createCallableStatement("CC030.search", listParams.size());
		HashMap<String, List<Map<String, String>>> result = (HashMap<String, List<Map<String, String>>>) DBHelper.executeCallableStatement(strCallProc, listParams);

		List<Map<String, String>> list = (List<Map<String,String>>)result.get("LIST");
		String strPageCnt = StringUtil.nvl(result.get("PAGECNT"));
		String strPageNo = StringUtil.nvl(result.get("PAGENO"));
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

	/**
	 * [概要]:支社名検索<br>
	 * [説 明]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchCustomerBean getSisyaNm(User user,SearchCustomerBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//会社No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCd()));			//支社コード


		listParams.add(DBHelper.createSQLOutParamVarchar2("SISYA_NM"));

		String strCallProc = CommonService.createCallableStatement("CC030.getSisyaNm", listParams.size());
		HashMap<String, List<Map<String, String>>> result = (HashMap<String, List<Map<String, String>>>) DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setSisyaNm(StringUtil.nvl(result.get("SISYA_NM")));

		return bean;
	}
}