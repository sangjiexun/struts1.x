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
	 * [Tv]:ÝÉ¤iõ<br>
	 * [à ¾]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchZaikoBean searchZaiko(User user,SearchZaikoBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));					//ïÐNo
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCdSearch()));		//xÐR[h
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSitenCdSearch()));		//xXR[h
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWhCdSearch()));			//qÉR[h
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getZaikoKbnSearch()));		//ÝÉæª
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItemCdSearch()));	//¤iR[h
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItemNmSearch()));		//¤i¼

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
	 * [Tv]:qÉ¼ DropDownListæ¾<br>
	 * [à ¾]:<br>
	 * @param
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getWhList(SearchZaikoBean bean) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call CC031.getWhList(?,?)}";

		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCo_no()));		//ïÐNo
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);

		List<Map<String, String>> list = (List<Map<String,String>>)result.get("LIST");

		return list;
	}

	/**
	 * [Tv]:xÐ¼õ<br>
	 * [à ¾]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchZaikoBean getSisyaNm(User user,SearchZaikoBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//ïÐNo
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCd()));			//xÐR[h
		listParams.add(DBHelper.createSQLOutParamVarchar2("SISYA_NM"));

		String strCallProc = CommonService.createCallableStatement("CC031.getSisyaNm", listParams.size());
		HashMap<String, List<Map<String, String>>> result = (HashMap<String, List<Map<String, String>>>) DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setSisyaNm(StringUtil.nvl(result.get("SISYA_NM")));

		return bean;
	}

	/**
	 * [Tv]:qÉ¼õ<br>
	 * [à ¾]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchZaikoBean searchWh(User user,SearchZaikoBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//ïÐNo
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWhCd()));			//qÉR[h
		listParams.add(DBHelper.createSQLOutParamVarchar2("WH_NM"));

		String strCallProc = CommonService.createCallableStatement("CC031.getWhNm", listParams.size());
		HashMap<String, List<Map<String, String>>> result = (HashMap<String, List<Map<String, String>>>) DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setWhNm(StringUtil.nvl(result.get("WH_NM")));

		return bean;
	}

	/**
	 * [Tv]:ÝÉæª¼õ<br>
	 * [à ¾]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchZaikoBean getZaikoKbnNm(User user,SearchZaikoBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//ïÐNo
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getZaikoKbn()));			//ÝÉæª
		listParams.add(DBHelper.createSQLOutParamVarchar2("ZAIKO_KBN_NM"));

		String strCallProc = CommonService.createCallableStatement("CC031.getZaikoKbnNm", listParams.size());
		HashMap<String, List<Map<String, String>>> result = (HashMap<String, List<Map<String, String>>>) DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setZaikoKbnNm(StringUtil.nvl(result.get("ZAIKO_KBN_NM")));

		return bean;
	}

}