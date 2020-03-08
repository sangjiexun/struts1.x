package jp.co.tcc.ecs.e_asproShip.SY110PrintList;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [�T �v]:�o�׎w���f�[�^�@DAO<br>
 *�@[�� ��]�F�o�ז���<br>
 * [�� �l]:<br>
 */
public class PrintListDAO {
	
	/**
	 * [�T�v]:�ꗗ����<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */ 
	
	
	
	
	public static void main(String[] args) {
		down();
	}
	public static void down(){
	
		java.sql.Connection conn = DBHelper.getConnection();
		try {
			CallableStatement proc = conn.prepareCall("{call SY110.check_login(?,?,?)}");
			int matches = 0 ;
			proc.setString(1,"sang");
			proc.setString(2, "123456");
			proc.setInt(3, matches);
			proc.execute();
			if (matches>0) {
				System.out.println("true");
			} else {
				System.out.println("false");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DBHelper.getConnection();
		
	
	
	}

	
	
	
	
	
	
	
	public PrintListBean search(PrintListBean bean) throws Exception{
		
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call SY110.search(?,?,?,?,?,?,?,?,?,?)}";
		// ��Дԍ�
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCoNo()));
		// �o�׎w���m����iFrom�j
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukkaOkYmdFrom()));
		// �o�׎w���m����iTo�j
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukkaOkYmdTo()));
		// �q��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSouKoCd()));
		// �o�͏�
		listParams.add(DBHelper.createSQLParamInteger(bean.getDownFlg()));
		
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

	
	/**
	 * �o�׎w����� �ꗗ
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	//�����ԍ�

	//������CD
	
	//�����於
	
	//�X�֔ԍ�
	
	//�Z��
	
	//�d�b�ԍ�
	
	//���i�R�[�h
	
	//���i��
	
	//������
	
	//�P��
	
	//�o�א�
	
	//���
	
	//�����N
	
	//NO
	
	//�o�b�`NO
	
	//�x�ЃR�[�h
	
	//�����敪
	
	
	
	/**
	 * �o�׎w���m��ꗗ
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	//�����ԍ�
	
	//������CD
	
	//�����於
	
	//�X�֔ԍ�
	
	//�Z��
		
	//�d�b�ԍ�
	
	//���i�R�[�h
	
	//���i��
	
	//������
	
	//�P��
	
	//�o�א�
	
	//���
	
	//�����N
	
	//NO
	
	//�o�b�`NO
	
	//�x�ЃR�[�h
	
	//�����敪
	
	
	
	
	
	
	/**
	 * �o�׎w���f�[�^CSV�f�����[�h���C�A�E�g�ꗗ
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	//�����ԍ�
	
	
	//������CD
	
	//�����於
	
	//�X�֔ԍ�
	
	//�Z��
	
	
	//�d�b�ԍ�
	
	//���i�R�[�h
	
	//���i��
	
	//������
	
	//�P��
	
	//�o�א�
	
	//���
	
	//�����N
	
	//NO
	
	//�o�b�`NO
	
	//�x�ЃR�[�h
	
	//�����敪
	
	
	
	
	
	/**
	 * [�T �v]:CSV�_�E�����[�h���ڎ擾<br>
	 * [�� ��]:CSV���C�A�E�g�}�X�^����_�E�����[�h���ڂ��擾<br>
	 * [�� �l]:
	 * @param NyukaListEntryBean
	 * @return HashMap<String, Vector<Map<String, String>>>
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap<String, Vector<Map<String, String>>> getCsvItem(PrintListBean bean) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCoNo()));		//���No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSearchKbn()));		//�_�E�����[�h�敪(����:40)
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("CNT"));
		String strCallProc = CommonService.createCallableStatement("SY110.getCsvItem", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		String StrCnt = (String)result.get("CNT");
		int itemCnt = 0;
		if (!StringUtil.isNull(StrCnt)){
			itemCnt = Integer.parseInt(StrCnt);
		}
		bean.setItemCount(itemCnt);
		return result;
	}
	/**
	 * [�T �v]:CSV�_�E�����[�h �w�b�_�[�L���擾<br>
	 * [�� ��]:CSV���C�A�E�g�̃w�b�_�[�t���O���擾<br>
	 * [�� �l]:
	 * @param NyukaListEntryBean
	 * @return int : 0=���^1=�L
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public int getHederFlg(PrintListBean bean) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCoNo()));		//���No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDownKbn()));		//�_�E�����[�h�敪(����:40)
		listParams.add(DBHelper.createSQLOutParamInteger("FLG"));
		String strCallProc = CommonService.createCallableStatement("SY110.getHederFlg", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		int flg = Integer.parseInt((String)result.get("FLG"));
		return flg;
	}
	/**
	 * [�T �v]:�o�׎w���f�[�^ CSV<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 * @param PrintListBean
	 * @return PrintListBean
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PrintListBean download(PrintListBean bean,int currentPageNo) throws Exception{
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCoNo()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuNm()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuAddr()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuTel()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCd()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSitenCd()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuRankType()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTaikaiFlg()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMakeDateFrom()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMakeDateTo()));
		//2014/03/19 ariga �폜�ς݃t���O�ǉ��̂���
	//	listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsvOutFlg()));
	//	listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsvDownKbn()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getPattern_no()));
//		if(bean.getScKbn() == null){
//			bean.setScKbn(new String[]{"-1"});
//		}
//		listParams.add(DBHelper.createSQLParamVarcharArray(bean.getScKbn()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getScKbnType()));
     	listParams.add(DBHelper.createSQLParamInteger(currentPageNo));
     	listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("TOTALCNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("TOTALPAGENO"));
		String strCallProc = CommonService.createCallableStatement("SY110.download", listParams.size());

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		List<Map<String, String>> list = (List<Map<String,String>>)result.get("LIST");
		String strTotalPageNo = (String)result.get("TOTALPAGENO");
		int totalPageNo = 0;
		if (!StringUtil.isNull(strTotalPageNo)){
			totalPageNo = Integer.parseInt(strTotalPageNo);
		}
		bean.setList(list);
		bean.setIntPageNo(totalPageNo);
		return bean;
	}


	public int getItemCnt(String coNo, String cSV_LIST_DOWN, int i) {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * [�T �v]:Bean�̓��e��list�ɃZ�b�g<br>
	 * [�� ��]:search,download���ʕ������Z�b�g<br>
	 * [�� �l]:
	 * @param bean
	 * @param currentPageNo
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public List<SQLParamType> setListParams(PrintListBean bean, String s) throws Exception {

		List<SQLParamType> listParams = new ArrayList<SQLParamType>();
		// ���NO
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		// �f�t�H���g�w���from
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDef_ymd_s()));
		// �f�t�H���g�w���To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDef_ymd_e()));
		// ������From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_s()));
		// ������To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_e()));
		// �����ԍ�
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_cd()));
		// �x��CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisya_cd()));
		// �x�XCD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSiten_cd()));
		// ��t�XCD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_cd()));
		// ��t�X��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_nm()));
		// ��t�X�Z��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_add()));
		// ��t�X�d�b
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_tel()));
		// ���n�XCD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_cd()));
		// ���n�X��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_nm()));
		// ���n�X�Z��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_addr()));
		// �q��CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWhCd()));
		// �o�ח\���From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_s()));
		// �o�ח\���To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_e()));
		// �z�B�w���From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_s()));
		// �z�B�w���To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_e()));
		// ������
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyori_jyoukyou()));
		// �o�^��From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMake_date_s()));
		// �o�^��To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMake_date_e()));
		// �o�^��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMake_user()));
		// ���iCDFrom
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_s()));
		// ���iCDTo
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_e()));
		// ���i��
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_nm1()));
		// �L�����Z�����O
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCancel_flg()));
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
		// CSV�_�E�����[�h�敪
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsv_down_kbn()));
		// �A�b�v���[�hNO
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpload_no()));
		// �ŏI�X�V��Start
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpdate_ymd_s()));
		// �ŏI�X�V��End
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpdate_ymd_e()));
		// �ŏI�X�V����Start
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpdate_hms_s()));
		// �ŏI�X�V����Start
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpdate_hms_e()));

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
