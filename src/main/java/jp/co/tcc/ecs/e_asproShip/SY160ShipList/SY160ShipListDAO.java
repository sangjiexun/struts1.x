package jp.co.tcc.ecs.e_asproShip.SY160ShipList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.PageFeedService;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.exception.FrameworkException;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [�T �v]:�o�׈ꗗDAO<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */
public class SY160ShipListDAO {
	/**
	 * [�T �v]:�o�׈ꗗ�擾�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param SY160ShipListBean
	 * @param String
	 * @return SY160ShipListBean
	 * @throws Exception
	 */
	public SY160ShipListBean search(SY160ShipListBean bean, String s) throws Exception {

		List<SQLParamType> listParams = setListParams(bean, "0");

		PageFeedService pageFeedService = new PageFeedService();
		pageFeedService.search("SY160.search", listParams, "LIST");
		bean.setIntPageCount(pageFeedService.getPageCnt());
		bean.setIntRecordCount(pageFeedService.getRecordCount());
		bean.setList(pageFeedService.getList());

		return bean;
	}

	/**
	 * [�T �v]:CSV�o�͗p CSV���ڐ��擾<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 *
	 * @param co_no
	 * @param csv_down_kbn
	 * @param pattern_no
	 * @return
	 * @throws FrameworkException
	 * @throws SQLException
	 */
	public int getItemCnt(String co_no, String csv_down_kbn, int pattern_no) throws FrameworkException, SQLException {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(co_no));
		listParams.add(DBHelper.createSQLParamVarchar2(csv_down_kbn));
		listParams.add(DBHelper.createSQLParamInteger(pattern_no));
		listParams.add(DBHelper.createSQLOutParamInteger("OUT_CNT"));

		String strCallProc = CommonService.createCallableStatement("COMMON.getItemCnt", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		String item_cnt = StringUtil.nvl(result.get("OUT_CNT"));

		return Integer.parseInt(item_cnt);
	}

	/**
	 * [�T �v]:Bean�̓��e��list�ɃZ�b�g<br>
	 * [�� ��]:search�Adownload���ʕ������Z�b�g<br>
	 * [�� �l]:
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<SQLParamType> setListParams(SY160ShipListBean bean, String s) throws Exception {
		List<SQLParamType> listParams = new ArrayList<SQLParamType>();
		// ���NO
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		// �f�t�H���g�w���from
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDef_ymd_s()));
		// �f�t�H���g�w���To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDef_ymd_e()));
		// �o�ח\���From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_s()));
		// �o�ח\���To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_e()));
		// ������From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_s()));
		// ������To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_e()));
		// �����ԍ�
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_cd()));
		// �o�׎w���ԍ�
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSksj_cd()));
		// �x��CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisya_cd()));
		// �x�XCD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSiten_cd()));
		// ��t�XCD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_cd()));
		// ��t�X��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_nm()));
		// ���n�XCD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_cd()));
		// ���n�X��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_nm()));
		// ���n�X�Z��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_addr()));
		// �z�B�w���From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_s()));
		// �z�B�w���To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_e()));
		// �q��CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWh_cd()));
		// �⍇��No From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOkurino_s()));
		// �⍇��No To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOkurino_e()));
		// �w���m�� From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_ok_ymd_s()));
		// �w���m�� To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_ok_ymd_e()));
		// ���ъm�� From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getToiout_ymd_s()));
		// ���ъm�� To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getToiout_ymd_e()));
		// ������
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyori_jyoukyou()));
		// ���iCDFrom
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_s()));
		// ���iCDTo
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_e()));
		// ���i��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_nm1()));
		// �o�׋敪
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getShipKbn()));
		// �ʁ^��ďo��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrderKbn()));
		// �ߋ������t���O
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getPast_flg()));

		// CSV�o�͋敪
		listParams.add(DBHelper.createSQLParamVarchar2(s));
		// �p�^�[��NO
		listParams.add(DBHelper.createSQLParamInteger(bean.getPattern_no()));
		// �_�E�����[�h�敪
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsv_down_kbn()));
		// �A�b�v���[�hNO
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpload_no()));
		// �o�׎w���m��ID
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_id()));
		// �y�[�WNo
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageNo()));
		// �y�[�W�T�C�Y
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));

		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("TOTALCNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("TOTALPAGENO"));

		return listParams;
	}

}
