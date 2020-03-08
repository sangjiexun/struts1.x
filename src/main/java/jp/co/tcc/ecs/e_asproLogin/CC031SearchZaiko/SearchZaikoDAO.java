package jp.co.tcc.ecs.e_asproLogin.CC031SearchZaiko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class SearchZaikoDAO {
	/**
	 * [概要]:在庫商品検索<br>
	 * [説 明]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchZaikoBean searchZaiko(User user,SearchZaikoBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));					//会社No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCdSearch()));		//支社コード
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSitenCdSearch()));		//支店コード
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWhCdSearch()));			//倉庫コード
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getZaikoKbnSearch()));		//在庫区分
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItemCdSearch()));	//商品コード
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItemNmSearch()));		//商品名

		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageNo()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));

		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGECNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGENO"));

		String strCallProc = CommonService.createCallableStatement("CC031.search", listParams.size());
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
	 * [概要]:倉庫名 DropDownList取得<br>
	 * [説 明]:<br>
	 * @param
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getWhList(SearchZaikoBean bean) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call CC031.getWhList(?,?)}";

		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCo_no()));		//会社No
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);

		List<Map<String, String>> list = (List<Map<String,String>>)result.get("LIST");

		return list;
	}

	/**
	 * [概要]:支社名検索<br>
	 * [説 明]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchZaikoBean getSisyaNm(User user,SearchZaikoBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//会社No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCd()));			//支社コード
		listParams.add(DBHelper.createSQLOutParamVarchar2("SISYA_NM"));

		String strCallProc = CommonService.createCallableStatement("CC031.getSisyaNm", listParams.size());
		HashMap<String, List<Map<String, String>>> result = (HashMap<String, List<Map<String, String>>>) DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setSisyaNm(StringUtil.nvl(result.get("SISYA_NM")));

		return bean;
	}

	/**
	 * [概要]:倉庫名検索<br>
	 * [説 明]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchZaikoBean searchWh(User user,SearchZaikoBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//会社No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWhCd()));			//倉庫コード
		listParams.add(DBHelper.createSQLOutParamVarchar2("WH_NM"));

		String strCallProc = CommonService.createCallableStatement("CC031.getWhNm", listParams.size());
		HashMap<String, List<Map<String, String>>> result = (HashMap<String, List<Map<String, String>>>) DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setWhNm(StringUtil.nvl(result.get("WH_NM")));

		return bean;
	}

	/**
	 * [概要]:在庫区分名検索<br>
	 * [説 明]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchZaikoBean getZaikoKbnNm(User user,SearchZaikoBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//会社No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getZaikoKbn()));			//在庫区分
		listParams.add(DBHelper.createSQLOutParamVarchar2("ZAIKO_KBN_NM"));

		String strCallProc = CommonService.createCallableStatement("CC031.getZaikoKbnNm", listParams.size());
		HashMap<String, List<Map<String, String>>> result = (HashMap<String, List<Map<String, String>>>) DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setZaikoKbnNm(StringUtil.nvl(result.get("ZAIKO_KBN_NM")));

		return bean;
	}

}