package jp.co.tcc.ecs.e_asproShip.SY142ShipFixUPTemp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class ShipFixUPTempDAO {

	/**
	 * [�T �v]:Bean�̓��e��list�ɃZ�b�g<br>
	 * [�� ��]:search,download���ʕ������Z�b�g<br>
	 * [�� �l]:
	 * @param ShipFixUPTempBean
	 * @return List
	 * @throws Exception
	 */
	public List<SQLParamType> setListParams(ShipFixUPTempBean bean) throws Exception {
		List<SQLParamType> listParams = new ArrayList<SQLParamType>();
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUuid()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsvupKbn()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getPatternNo()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSearchStatus()));

		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageNo()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));

		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGE_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGE_NO"));
		listParams.add(DBHelper.createSQLOutParamInteger("ERROR_CNT"));
		return listParams;
	}

	/**
	 * [�T �v]:�o�׎���CSV�o�^�i�m�F�j�B<br>
	 * [�� ��]:�o�׎���CSV�o�^�����f�[�^����������B<br>
	 * [�� �l]:<br>
	 * @param bean ShipFixUPTempBean
	 * @return bean ShipFixUPTempBean
	 * @throws Exception
	*/
	@SuppressWarnings("unchecked")
	public ShipFixUPTempBean search(ShipFixUPTempBean bean) throws Exception {
		// �������ڂ͋��ʂ̏����ŃZ�b�g
		List<SQLParamType> listParams = setListParams(bean);

		String strCallProc = CommonService.createCallableStatement("SY142.search", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);

		bean.setList((Vector<Map<String,String>>)result.get("LIST"));

		bean.setIntRecordCount(Integer.parseInt(StringUtil.nvl(result.get("PAGE_CNT"),"0")));
		bean.setIntPageCount(Integer.parseInt(StringUtil.nvl(result.get("PAGE_NO"),"0")));
		bean.setErrorCnt(Integer.parseInt(StringUtil.nvl(result.get("ERROR_CNT"),"0")));

		return bean;
	}

	/**
	 * [�T �v]:�o�׎���CSV�o�^�i�m�F�j�B<br>
	 * [�� ��]:�o�׎w���w�b�_�[�A�o�׏�ԁA�����NO�e�[�u����UPDATE<br>
	 * [�� �l]:<br>
	 * @param bean ShipFixUPTempBean
	 * @return
	 * @throws Exception
	*/
	public void update(ShipFixUPTempBean bean) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUuid()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsvupKbn()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getPatternNo()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWhCd()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getUploadNo()));
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getFileNm()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getUserNo()));

		String strCallProc = CommonService.createCallableStatement("SY142.updateData", listParams.size());
		DBHelper.executeCallableStatement(strCallProc, listParams);
	}
}
