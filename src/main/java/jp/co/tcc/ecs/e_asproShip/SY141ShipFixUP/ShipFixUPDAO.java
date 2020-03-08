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
 * [�T �v]:�o�׎���CSV�o�^<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */

public class ShipFixUPDAO {

	/**
	 * [�T �v]:�o�׎���CSV�o�^�B<br>
	 * [�� ��]:CSV�o�^�����f�[�^����������B<br>
	 * [�� �l]:<br>
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
	 * [�T �v]:CSV�t�@�C�����[�N�G���[���b�Z�W�[�X�V�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 * @param bean
	 * @return �G���[�L�� true:�L��/false�F����
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
	 * [�T �v]:CSV���[�N�폜�B<br>
	 * [�� ��]:�L�����Z�������ꍇCSV���[�N���폜���܂��B<br>
	 * [�� �l]:
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
