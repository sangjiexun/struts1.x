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
	 * [�T�v]:�ڋq����<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchCustomerBean searchCustomer(User user,SearchCustomerBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//���No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCdSearch()));		//�O���R�[�h
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuCdSearch()));		//���q�l�R�[�h
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuNmSearch()));		//�����Җ�
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuAddrSearch()));	//�Z��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuTelSearch()));	//�d�b�ԍ�
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
	 * [�T�v]:�x�Ж�����<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SearchCustomerBean getSisyaNm(User user,SearchCustomerBean bean) throws Exception {

		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));				//���No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCd()));			//�x�ЃR�[�h


		listParams.add(DBHelper.createSQLOutParamVarchar2("SISYA_NM"));

		String strCallProc = CommonService.createCallableStatement("CC030.getSisyaNm", listParams.size());
		HashMap<String, List<Map<String, String>>> result = (HashMap<String, List<Map<String, String>>>) DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setSisyaNm(StringUtil.nvl(result.get("SISYA_NM")));

		return bean;
	}
}