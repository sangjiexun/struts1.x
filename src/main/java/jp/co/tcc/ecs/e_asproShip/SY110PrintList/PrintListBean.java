package jp.co.tcc.ecs.e_asproShip.SY110PrintList;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

/**
 * [�T �v]:�o�׎w���f�[�^ Bean<br>
 * [�� ��]�F�o�ז���<br>
 * [�� �l]:<br>
 */
public class PrintListBean extends BaseBean {
	public static String CSV_HEAD_DOWN = null;
	public static String CSV_LIST_DOWN = null;
	/** �J�ڐ�URL */
	public static String FORWARD_URL = "ship/SY110PrintList.jsp";
	/** �J�ڐ�URL */
	public static String PACKAGE_NAME = "/e_asproShip/SY110PrintList";
	// �o�׎w���w�b�_�[
	private static String csv_down_kbn = "1";
	// --------�敪���쉺�L----------------------------
	// �N�� 
	private String processType = "1";
	// �p�^�[��NO
	private int pattern_no = 0;
	// �r���J�E���^
	private String exclCnt = "";
	
	// �����敪
	private String searchKbn = "1";
	// �o�׎w����ԋ敪
	private String downloadBeginKbn = "1";
	// �o�׎w���m��敪
	private String downloadOkKbn = "1";
	// CSV�o�͋敪
	private String csvDownLoadKbn = "1";
	// �_�E�����[�h�敪(����:40)
	private String downKbn = "";

	// ���j���[�N���t���O
	private String _fromMenu = "";

	// �w�b�_CSV�o��
	private String header_csv_out = "";
	// ����CSV�o��
	private String list_csv_out = "";
	// �w�b�_CSV�o�͕\��Flg
	private String header_csv_out_flg = "";
	// ����CSV�o�͕\��Flg
	private String list_csv_out_flg = "";
	// ---------------------------------------------------

	// �x�X���X�g
	private List<Map<String, String>> siten_list = null;
	// �x��CD
	private String sisya_cd = "";

	// ���NO
	private String coNo = "";
	// ���[�UNO
	private String userNo = "";

	// �o�׎w���m����iFrom�j
	private String syukkaOkYmdFrom = "";
	// �o�׎w���m����iTo�j
	private String syukkaOkYmdTo = "";
	// �q�ɖ�
	private String souKoCd = "";
	// �o�͏�
	private String downFlg = "";

	// ��������
	private List<Map<String, String>> list = null;
	// �o�͏�
	private List<Map<String, String>> downFlgList = null;
	// �q��CD
	private String whCd = "";
	private String login_wh_cd = "";
	private String login_wh_nm = "";
	// ����No
	private String nyukaCd = "";
	// �G���[���b�Z�[�W
	private String strErrMsg = "";
	// �ڐ�
	@SuppressWarnings("unused")
	private int itemCount=0;
	private String order_cd;
	private String order_ymd_e;
	private String order_ymd_s;
	private String syukka_yotei_ymd_s;
	private String syukka_yotei_ymd_e;
	private String tdk_ymd_s;
	private String tdk_ymd_e;
	private String make_date_s;
	private String make_date_e;
	private String update_ymd_s;
	private String update_hms_s;
	private String update_ymd_e;
	private String update_hms_e;
    private String def_ymd_e;
    private String def_ymd_s;
    
    
//    
//	/** CSV�o�͋敪 */
//	public final String CSV_HEAD_DOWN = "4";
//	public final String CSV_LIST_DOWN = "5";
//
//	/** --�ŏI�X�V��Start */
//	private String update_ymd_s = "";
	/** --�ŏI�X�V��End */
//	private String update_ymd_e = "";
//	/** --�ŏI�X�V�������� */
//	private String update_hms_s = "";
//	/** --�ŏI�X�V�������� */
//	private String update_hms_e = "";
//	// �N���敪
//	private String processType = "";
//	// �����敪
//	private String searchKbn = "";
//	// �f�t�H���g�w���From
//	private String def_ymd_s = "";
//	// �f�t�H���g�w���To
//	private String def_ymd_e = "";
//	// ������From
//	private String order_ymd_s = "";
//	// ������To
//	private String order_ymd_e = "";
//	// �����ԍ�
//	private String order_cd = "";
//	// �x��CD
//	private String sisya_cd = "";
	// �x�XCD
	private String siten_cd = "";
	// ��t�XCD
	private String kokyaku_cd = "";
	// ��t�X��
	private String kokyaku_nm = "";
	// ��t�X�Z��
	private String kokyaku_add = "";
	// ��t�X�d�b
	private String kokyaku_tel = "";
	// ���n�XCD
	private String tdk_cd = "";
	// ���n�X��
	private String tdk_nm = "";
	// ���n�X�Z��
	private String tdk_addr = "";
//	// �q�ɃR�[�h
//	private String whCd = "";
//	// �o�ח\���From
//	private String syukka_yotei_ymd_s = "";
	// �o�ח\���To
//	private String syukka_yotei_ymd_e = "";
//	// �z�B�w���From
//	private String tdk_ymd_s = "";
//	// �z�B�w���To
//	private String tdk_ymd_e = "";
	// ������
	private String syori_jyoukyou = "";
//	// �o�^��From
//	private String make_date_s = "";
//	// �o�^��To
//	private String make_date_e = "";
	// �o�^��
	private String make_user = "";
	// ���iCD From
	private String item_cd1_s = "";
	// ���iCD To
	private String item_cd1_e = "";
	// ���i��
	private String item_nm1 = "";
	// �L�����Z��
	private String cancel_flg = "0";
	// �o�׋敪
	private String shipKbn = "";
	// ��/��ďo��
	private String orderKbn = "";
	// �ߋ��f�[�^����������t���O
	private String past_flg = "";

//	// �w�b�_CSV�o��
//	private String header_csv_out = "";
//	// �p�^�[��NO
//	private int pattern_no = 0;
//	// CSV�_�E�����[�h�敪
//	private String csv_down_kbn = "";
	// �A�b�v���[�hNO
	private String upload_no = "";
	// ����CSV�o��
//	private String list_csv_out = "";
//	// �G���[���b�Z�[�W
//	private String strErrMsg = "";
//	// ���j���[�N���t���O
//	private String _fromMenu = "";
//	// �w�b�_CSV�o�͕\��Flg
//	private String header_csv_out_flg = "";
//	// ����CSV�o�͕\��Flg
//	private String list_csv_out_flg = "";

public String getItem_cd1_s() {
		return item_cd1_s;
	}



	public String getSiten_cd() {
	return siten_cd;
}



public void setSiten_cd(String siten_cd) {
	this.siten_cd = siten_cd;
}



public String getKokyaku_cd() {
	return kokyaku_cd;
}



public void setKokyaku_cd(String kokyaku_cd) {
	this.kokyaku_cd = kokyaku_cd;
}



public String getKokyaku_nm() {
	return kokyaku_nm;
}



public void setKokyaku_nm(String kokyaku_nm) {
	this.kokyaku_nm = kokyaku_nm;
}



public String getKokyaku_add() {
	return kokyaku_add;
}



public void setKokyaku_add(String kokyaku_add) {
	this.kokyaku_add = kokyaku_add;
}



public String getKokyaku_tel() {
	return kokyaku_tel;
}



public void setKokyaku_tel(String kokyaku_tel) {
	this.kokyaku_tel = kokyaku_tel;
}



	public String getTdk_cd() {
	return tdk_cd;
}



public void setTdk_cd(String tdk_cd) {
	this.tdk_cd = tdk_cd;
}



public String getTdk_nm() {
	return tdk_nm;
}



public void setTdk_nm(String tdk_nm) {
	this.tdk_nm = tdk_nm;
}



public String getTdk_addr() {
	return tdk_addr;
}



public void setTdk_addr(String tdk_addr) {
	this.tdk_addr = tdk_addr;
}



public String getSyori_jyoukyou() {
	return syori_jyoukyou;
}



public void setSyori_jyoukyou(String syori_jyoukyou) {
	this.syori_jyoukyou = syori_jyoukyou;
}



public String getMake_user() {
	return make_user;
}



public void setMake_user(String make_user) {
	this.make_user = make_user;
}



public String getWhCd() {
	return whCd;
}



	public void setItem_cd1_s(String item_cd1_s) {
		this.item_cd1_s = item_cd1_s;
	}



	public String getItem_cd1_e() {
		return item_cd1_e;
	}



	public void setItem_cd1_e(String item_cd1_e) {
		this.item_cd1_e = item_cd1_e;
	}



	public String getItem_nm1() {
		return item_nm1;
	}



	public void setItem_nm1(String item_nm1) {
		this.item_nm1 = item_nm1;
	}



	public String getCancel_flg() {
		return cancel_flg;
	}



	public void setCancel_flg(String cancel_flg) {
		this.cancel_flg = cancel_flg;
	}



	public String getShipKbn() {
		return shipKbn;
	}



	public void setShipKbn(String shipKbn) {
		this.shipKbn = shipKbn;
	}



	public String getOrderKbn() {
		return orderKbn;
	}



	public void setOrderKbn(String orderKbn) {
		this.orderKbn = orderKbn;
	}



	public String getPast_flg() {
		return past_flg;
	}



	public void setPast_flg(String past_flg) {
		this.past_flg = past_flg;
	}



	public String getUpload_no() {
		return upload_no;
	}



	public void setUpload_no(String upload_no) {
		this.upload_no = upload_no;
	}
	//	// �x�X���X�g
//	private List<Map<String, String>> siten_list = null;
//
//	// �󒍈ꗗ�f�[�^���X�g
//	private List<Map<String, String>> list = null;
//	// �󒍈ꗗ�f�[�^��CSV���X�g
	private List<Map<String, String>> listCsv = null;

	// �ԉ�p�Q��Map
	protected void setSearchParamMap�P() {
		super.setSearchParamMap();

		// �N���敪
		this.addParamMap("processType", this.processType);
		// �f�t�H���g�w���from
		this.addParamMap("def_ymd_s", this.def_ymd_s);
		// �f�t�H���g�w���To
		this.addParamMap("def_ymd_e", this.def_ymd_e);
		// ������From
		this.addParamMap("order_ymd_s", this.order_ymd_s);
		// ������To
		this.addParamMap("order_ymd_e", this.order_ymd_e);
		// �����ԍ�
		this.addParamMap("order_cd", this.order_cd);
		// �x��CD
		this.addParamMap("sisya_cd", this.sisya_cd);
		// �x�XCD
		this.addParamMap("siten_cd", this.siten_cd);
		// ��t�XCD
		this.addParamMap("kokyaku_cd", this.kokyaku_cd);
		// ��t�X��
		this.addParamMap("kokyaku_nm", this.kokyaku_nm);
		// ��t�X�Z��
		this.addParamMap("kokyaku_add", this.kokyaku_add);
		// ��t�X�d�b
		this.addParamMap("kokyaku_tel", this.kokyaku_tel);
		// ���n�XCD
		this.addParamMap("tdk_cd", this.tdk_cd);
		// ���n�X��
		this.addParamMap("tdk_nm", this.tdk_nm);
		// �q��CD
		this.addParamMap("whCd", this.whCd);
		// ���n�X�Z��
		this.addParamMap("tdk_addr", this.tdk_addr);
		// �o�ח\���From
		this.addParamMap("syukka_yotei_ymd_s", this.syukka_yotei_ymd_s);
		// �o�ח\���To
		this.addParamMap("syukka_yotei_ymd_e", this.syukka_yotei_ymd_e);
		// �z�B�w���From
		this.addParamMap("tdk_ymd_s", this.tdk_ymd_s);
		// �z�B�w���To
		this.addParamMap("tdk_ymd_e", this.tdk_ymd_e);
		// ������
		this.addParamMap("syori_jyoukyou", this.syori_jyoukyou);
		// �o�^��From
		this.addParamMap("make_date_s", this.make_date_s);
		// �o�^��To
		this.addParamMap("make_date_e", this.make_date_e);
		// �o�^��
		this.addParamMap("make_user", this.make_user);
		// ���iCD From
		this.addParamMap("item_cd1_s", this.item_cd1_s);
		// ���iCD To
		this.addParamMap("item_cd1_e", this.item_cd1_e);
		// ���i��
		this.addParamMap("item_nm1", this.item_nm1);
		// �L�����Z��
		this.addParamMap("cancel_flg", this.cancel_flg);
		// �A�b�v���[�hNO
		this.addParamMap("upload_no", this.upload_no);
		// ���j���[�N���t���O
		this.addParamMap("_fromMenu", this._fromMenu);
		// �ŏI�X�V��Start
		this.addParamMap("update_ymd_s", this.update_ymd_s);
		// �ŏI�X�V��End
		this.addParamMap("update_ymd_e", this.update_ymd_e);
		// �ŏI�X�V����
		this.addParamMap("update_hms_s", this.update_hms_s);
		// �ŏI�X�V����
		this.addParamMap("update_hms_e", this.update_hms_e);
		// �o�׋敪
		this.addParamMap("shipKbn", this.shipKbn);
		// ��/��ďo��
		this.addParamMap("orderKbn", this.orderKbn);
		// �ߋ������t���O
		this.addParamMap("past_flg", this.past_flg);
	}
    
    
    
// // �󒍈ꗗ�f�[�^��CSV���X�g
// 	private List<Map<String, String>> listCsv = null;
//	
	
	protected void setSearchParamMap() {
		super.setSearchParamMap();
		this.addParamMap("processType", this.processType);
		this.addParamMap("syukkaOkYmdFrom", this.syukkaOkYmdFrom);
		this.addParamMap("syukkaOkYmdTo", this.syukkaOkYmdTo);
		this.addParamMap("searchKbn", this.searchKbn);
		this.addParamMap("downFlg", this.downFlg);
		this.addParamMap("whCd", this.whCd);
		this.addParamMap("souKoCd", this.souKoCd);
		// �x��CD
		this.addParamMap("sisya_cd", this.sisya_cd);
		this.addParamMap("coNo", this.coNo);
	}
	// ------------- getter �� setter--------------------------

	public String getNyukaCd() {
		return nyukaCd;
	}

	public String getDownloadBeginKbn() {
		return downloadBeginKbn;
	}

	public void setDownloadBeginKbn(String downloadBeginKbn) {
		this.downloadBeginKbn = downloadBeginKbn;
	}

	public String getDownloadOkKbn() {
		return downloadOkKbn;
	}

	public void setDownloadOkKbn(String downloadOkKbn) {
		this.downloadOkKbn = downloadOkKbn;
	}

	public String getCsvDownLoadKbn() {
		return csvDownLoadKbn;
	}

	public void setCsvDownLoadKbn(String csvDownLoadKbn) {
		this.csvDownLoadKbn = csvDownLoadKbn;
	}

	public void setNyukaCd(String nyukaCd) {
		this.nyukaCd = nyukaCd;
	}

	public String getDownKbn() {
		return downKbn;
	}

	public void setDownKbn(String downKbn) {
		this.downKbn = downKbn;
	}

	public String getLogin_wh_cd() {
		return login_wh_cd;
	}

	public void setLogin_wh_cd(String login_wh_cd) {
		this.login_wh_cd = login_wh_cd;
	}

	public String getLogin_wh_nm() {
		return login_wh_nm;
	}

	public void setLogin_wh_nm(String login_wh_nm) {
		this.login_wh_nm = login_wh_nm;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getExclCnt() {
		return exclCnt;
	}

	public void setExclCnt(String exclCnt) {
		this.exclCnt = exclCnt;
	}

	public String getCoNo() {
		return coNo;
	}

	public void setCoNo(String coNo) {
		this.coNo = coNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

	public String getSyukkaOkYmdFrom() {
		return syukkaOkYmdFrom;
	}

	public void setSyukkaOkYmdFrom(String syukkaOkYmdFrom) {
		this.syukkaOkYmdFrom = syukkaOkYmdFrom;
	}

	public String getSyukkaOkYmdTo() {
		return syukkaOkYmdTo;
	}

	public void setSyukkaOkYmdTo(String syukkaOkYmdTo) {
		this.syukkaOkYmdTo = syukkaOkYmdTo;
	}

	public String getDownFlg() {
		return downFlg;
	}

	public void setDownFlg(String downFlg) {
		this.downFlg = downFlg;
	}

	public String getSouKoCd() {
		return souKoCd;
	}

	public void setSouKoCd(String souKoCd) {
		this.souKoCd = souKoCd;
	}

	public String getSearchKbn() {
		return searchKbn;
	}

	public void setSearchKbn(String searchKbn) {
		this.searchKbn = searchKbn;
	}

	public List<Map<String, String>> getDownFlgList() {
		return downFlgList;
	}

	public void setDownFlgList(List<Map<String, String>> downFlgList) {
		this.downFlgList = downFlgList;
	}

	public int getItemCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPattern_no() {
		return pattern_no;
	}

	public void setPattern_no(int pattern_no) {
		this.pattern_no = pattern_no;
	}

	public void setLoginKengenPtn(String strKengen) {
		// TODO Auto-generated method stub

	}

	public void setWhCd(String whCd2) {
		this.souKoCd = whCd2;

	}

	public String get_fromMenu() {
		return _fromMenu;
	}

	public void set_fromMenu(String _fromMenu) {
		this._fromMenu = _fromMenu;
	}

	public List<Map<String, String>> getSiten_list() {
		return siten_list;
	}

	public void setSiten_list(List<Map<String, String>> siten_list) {
		this.siten_list = siten_list;
	}

	public String getSisya_cd() {
		return sisya_cd;
	}

	public void setSisya_cd(String sisya_cd) {
		this.sisya_cd = sisya_cd;
	}

	public String getCsv_down_kbn() {
		return csv_down_kbn;
	}

	public static void setCsv_down_kbn(String csv_down_kbn) {
		PrintListBean.csv_down_kbn = csv_down_kbn;
	}

	public String getOrder_cd() {
		// TODO Auto-generated method stub
		return order_cd;
	}

	public String getOrder_ymd_e() {
		// TODO Auto-generated method stub
		return order_ymd_e;
	}

	public String getOrder_ymd_s() {
		// TODO Auto-generated method stub
		return order_ymd_s;
	}

	public String getSyukka_yotei_ymd_s() {
		// TODO Auto-generated method stub
		return syukka_yotei_ymd_s;
	}

	public String getSyukka_yotei_ymd_e() {
		// TODO Auto-generated method stub
		return syukka_yotei_ymd_e;
	}

	public String getTdk_ymd_s() {
		// TODO Auto-generated method stub
		return tdk_ymd_s;
	}

	public String getTdk_ymd_e() {
		// TODO Auto-generated method stub
		return tdk_ymd_e;
	}

	public String getMake_date_s() {
		// TODO Auto-generated method stub
		return make_date_s;
	}

	public String getMake_date_e() {
		// TODO Auto-generated method stub
		return make_date_e;
	}

	public String getUpdate_ymd_s() {
		// TODO Auto-generated method stub
		return update_ymd_s;
	}

	public String getUpdate_hms_s() {
		// TODO Auto-generated method stub
		return update_hms_s;
	}

	public String getUpdate_ymd_e() {
		// TODO Auto-generated method stub
		return update_ymd_e;
	}

	public static String getCSV_HEAD_DOWN() {
		return CSV_HEAD_DOWN;
	}

	public static void setCSV_HEAD_DOWN(String cSV_HEAD_DOWN) {
		CSV_HEAD_DOWN = cSV_HEAD_DOWN;
	}

	public static String getCSV_LIST_DOWN() {
		return CSV_LIST_DOWN;
	}

	public static void setCSV_LIST_DOWN(String cSV_LIST_DOWN) {
		CSV_LIST_DOWN = cSV_LIST_DOWN;
	}

	public String getHeader_csv_out() {
		return header_csv_out;
	}

	public void setHeader_csv_out(String header_csv_out) {
		this.header_csv_out = header_csv_out;
	}

	public String getList_csv_out() {
		return list_csv_out;
	}

	public void setList_csv_out(String list_csv_out) {
		this.list_csv_out = list_csv_out;
	}

	public String getHeader_csv_out_flg() {
		return header_csv_out_flg;
	}

	public void setHeaderCsvOutFlg(String header_csv_out_flg) {
		this.header_csv_out_flg = header_csv_out_flg;
	}
	public String getList_csv_out_flg() {
		return list_csv_out_flg;
	}
	public void setListCsvOutFlg(String list_csv_out_flg) {
		this.list_csv_out_flg = list_csv_out_flg;
	}

	public String getUpdate_hms_e() {
		// TODO Auto-generated method stub
		return update_hms_e;
	}

	public String getDef_ymd_e() {
		return def_ymd_e;
	}

	public void setDef_ymd_e(String def_ymd_e) {
		this.def_ymd_e = def_ymd_e;
	}

	public String getDef_ymd_s() {
		return def_ymd_s;
	}

	public void setDef_ymd_s(String def_ymd_s) {
		this.def_ymd_s = def_ymd_s;
	}

	public List<Map<String, String>> getListCsv() {
		return listCsv;
	}
	public void setListCsv(List<Map<String, String>> listCsv) {
		this.listCsv = listCsv;
	}
}
