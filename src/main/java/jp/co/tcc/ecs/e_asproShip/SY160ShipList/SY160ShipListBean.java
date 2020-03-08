package jp.co.tcc.ecs.e_asproShip.SY160ShipList;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

/**
 * [�T �v]:�o�׈ꗗBean<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */
public class SY160ShipListBean extends BaseBean {
	/** CSV�o�͋敪 */
	public final String CSV_HEAD_DOWN = "6";
	public final String CSV_LIST_DOWN = "7";
	public final String CSV_OKURINO_DOWN = "71";
	public final String CSV_ITEM_DOWN = "72";

	// �N���敪
	private String processType = "";
	// �����敪
	private String searchKbn = "";
	// �󒍊m���From
	private String sksj_ymd_s = "";
	// �󒍊m���To
	private String sksj_ymd_e = "";
	// ������From
	private String order_ymd_s = "";
	// ������To
	private String order_ymd_e = "";
	// �����ԍ�
	private String order_cd = "";
	// �o�׎w���ԍ�
	private String sksj_cd = "";
	// �x��CD
	private String sisya_cd = "";
	// �x�XCD
	private String siten_cd = "";
	// ��t�X
	private String kokyaku_cd = "";
	// ��t�X��
	private String kokyaku_nm = "";
	// ���n�XCD
	private String tdk_cd = "";
	// ���n��
	private String tdk_nm = "";
	// ���n�X�Z��
	private String tdk_addr = "";
	// �f�t�H���g�w���From
	private String def_ymd_s = "";
	// �f�t�H���g�w���To
	private String def_ymd_e = "";
	// �o�ח\���From
	private String syukka_yotei_ymd_s = "";
	// �o�ח\���To
	private String syukka_yotei_ymd_e = "";
	// �z�B�w���From
	private String tdk_ymd_s = "";
	// �z�B�w���To
	private String tdk_ymd_e = "";
	// �q��CD
	private String wh_cd = "";
	// �⍇��No From
	private String okurino_s = "";
	// �⍇��No To
	private String okurino_e = "";
	// �w���m�� From
	private String syukka_ok_ymd_s = "";
	// �w���m�� To
	private String syukka_ok_ymd_e = "";
	// ���ъm�� From
	private String toiout_ymd_s = "";
	// ���ъm�� To
	private String toiout_ymd_e = "";
	// ������
	private String syori_jyoukyou = "";
	// ���iCD From
	private String item_cd1_s = "";
	// ���iCD To
	private String item_cd1_e = "";
	// ���i��
	private String item_nm1 = "";
	// �o�׋敪
	private String shipKbn = "";
	// ��/��ďo��
	private String orderKbn = "";
	// �ߋ������t���O
	private String past_flg = "";

	// �w�b�_CSV�o��
	private String header_csv_out = "";
	// ����CSV�o��
	private String list_csv_out = "";
	// �⍇��NoCSV�o��
	private String toi_csv_out = "";
	// ���i�ʏo�א�CSV�o��
	private String item_csv_out = "";

	// �p�^�[��NO
	private int pattern_no = 0;
	// CSV�_�E�����[�h�敪
	private String csv_down_kbn = "";
	// �A�b�v���[�hNO
	private String upload_no = "";
	// �o�׎w���m��ID
	private String syukka_id = "";
	// �G���[���Z�b�W
	private String strErrMsg = "";
	// ���j���[�N���t���O
	private String _fromMenu = "";
	// �w�b�_CSV�o�͕\���t���O
	private String header_csv_out_flg = "";
	// ����CSV�o�͕\���t���O
	private String list_csv_out_flg = "";
	// �⍇��NoCSV�o�͕\��Flg
	private String toi_csv_out_flg = "";

	// �x�X���X�g
	private List<Map<String, String>> siten_list = null;

	// �o�׈ꗗ�f�[�^���X�g
	private List<Map<String, String>> list = null;

	// �ԉ�p�Q��Map
	protected void setSearchParamMap() {
		super.setSearchParamMap();
		// �f�t�H���g�w���from
		this.addParamMap("def_ymd_s", this.def_ymd_s);
		// �f�t�H���g�w���To
		this.addParamMap("def_ymd_e", this.def_ymd_e);
		// �󒍊m���From
		this.addParamMap("sksj_ymd_s", this.sksj_ymd_s);
		// �󒍊m���To
		this.addParamMap("sksj_ymd_e", this.sksj_ymd_e);
		// ������From
		this.addParamMap("order_ymd_s", this.order_ymd_s);
		// ������To
		this.addParamMap("order_ymd_e", this.order_ymd_e);
		// �����ԍ�
		this.addParamMap("order_cd", this.order_cd);
		// �o�׎w���ԍ�
		this.addParamMap("sksj_cd", this.sksj_cd);
		// �x��CD
		this.addParamMap("sisya_cd", this.sisya_cd);
		// �x�XCD
		this.addParamMap("siten_cd", this.siten_cd);
		// ��t�XCD
		this.addParamMap("kokyaku_cd", this.kokyaku_cd);
		// ��t�X��
		this.addParamMap("kokyaku_nm", this.kokyaku_nm);
		// ���n�XCD
		this.addParamMap("tdk_cd", this.tdk_cd);
		// ���n�X��
		this.addParamMap("tdk_nm", this.tdk_nm);
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
		// �q��CD
		this.addParamMap("wh_cd", this.wh_cd);
		// �⍇��No From
		this.addParamMap("okurino_s", this.okurino_s);
		// �⍇��No To
		this.addParamMap("okurino_e", this.okurino_e);
		// �w���m�� From
		this.addParamMap("syukka_ok_ymd_s", this.syukka_ok_ymd_s);
		// �w���m�� To
		this.addParamMap("syukka_ok_ymd_e", this.syukka_ok_ymd_e);
		// ���ъm�� From
		this.addParamMap("toiout_ymd_s", this.toiout_ymd_s);
		// ���ъm�� To
		this.addParamMap("toiout_ymd_e", this.toiout_ymd_e);
		// ���iCD From
		this.addParamMap("item_cd1_s", this.item_cd1_s);
		// ���iCD To
		this.addParamMap("item_cd1_e", this.item_cd1_e);
		// ���i��
		this.addParamMap("item_nm1", this.item_nm1);
		// �o�׋敪
		this.addParamMap("shipKbn", this.shipKbn);
		// ��/��ďo��
		this.addParamMap("orderKbn", this.orderKbn);
		// �ߋ������t���O
		this.addParamMap("past_flg", this.past_flg);

		// ���j���[�N���t���O
		this.addParamMap("_fromMenu", this._fromMenu);
		// �A�b�v���[�hNO
		this.addParamMap("upload_no", this.upload_no);
		// �o�׎w���m��ID
		this.addParamMap("syukka_id", this.syukka_id);
	}

	public String getHeaderCsvOutFlg() {
		return header_csv_out_flg;
	}

	public void setHeaderCsvOutFlg(String header_csv_out_flg) {
		this.header_csv_out_flg = header_csv_out_flg;
	}

	public String getListCsvOutFlg() {
		return list_csv_out_flg;
	}

	public void setListCsvOutFlg(String list_csv_out_flg) {
		this.list_csv_out_flg = list_csv_out_flg;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getSearchKbn() {
		return searchKbn;
	}

	public void setSearchKbn(String searchKbn) {
		this.searchKbn = searchKbn;
	}
	public String getSksj_ymd_s() {
		return sksj_ymd_s;
	}

	public void setSksj_ymd_s(String sksj_ymd_s) {
		this.sksj_ymd_s = sksj_ymd_s;
	}

	public String getSksj_ymd_e() {
		return sksj_ymd_e;
	}

	public void setSksj_ymd_e(String sksj_ymd_e) {
		this.sksj_ymd_e = sksj_ymd_e;
	}

	public String getOrder_cd() {
		return order_cd;
	}

	public void setOrder_cd(String order_cd) {
		this.order_cd = order_cd;
	}

	public String getSksj_cd() {
		return sksj_cd;
	}

	public void setSksj_cd(String sksj_cd) {
		this.sksj_cd = sksj_cd;
	}

	public String getSisya_cd() {
		return sisya_cd;
	}

	public void setSisya_cd(String sisya_cd) {
		this.sisya_cd = sisya_cd;
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

	public String getOkurino_s() {
		return okurino_s;
	}

	public void setOkurino_s(String okurino_s) {
		this.okurino_s = okurino_s;
	}

	public String getOkurino_e() {
		return okurino_e;
	}

	public void setOkurino_e(String okurino_e) {
		this.okurino_e = okurino_e;
	}

	public String getSyukka_ok_ymd_s() {
		return syukka_ok_ymd_s;
	}

	public void setSyukka_ok_ymd_s(String syukka_ok_ymd_s) {
		this.syukka_ok_ymd_s = syukka_ok_ymd_s;
	}

	public String getSyukka_ok_ymd_e() {
		return syukka_ok_ymd_e;
	}

	public void setSyukka_ok_ymd_e(String syukka_ok_ymd_e) {
		this.syukka_ok_ymd_e = syukka_ok_ymd_e;
	}

	public String getToiout_ymd_s() {
		return toiout_ymd_s;
	}

	public void setToiout_ymd_s(String toiout_ymd_s) {
		this.toiout_ymd_s = toiout_ymd_s;
	}

	public String getToiout_ymd_e() {
		return toiout_ymd_e;
	}

	public void setToiout_ymd_e(String toiout_ymd_e) {
		this.toiout_ymd_e = toiout_ymd_e;
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

	public String getHeader_csv_out() {
		return header_csv_out;
	}

	public void setHeader_csv_out(String header_csv_out) {
		this.header_csv_out = header_csv_out;
	}

	public int getPattern_no() {
		return pattern_no;
	}

	public void setPattern_no(int pattern_no) {
		this.pattern_no = pattern_no;
	}

	public String getCsv_down_kbn() {
		return csv_down_kbn;
	}

	public void setCsv_down_kbn(String csv_down_kbn) {
		this.csv_down_kbn = csv_down_kbn;
	}

	public String getList_csv_out() {
		return list_csv_out;
	}

	public void setList_csv_out(String list_csv_out) {
		this.list_csv_out = list_csv_out;
	}

	public String getWh_cd() {
		return wh_cd;
	}

	public void setWh_cd(String wh_cd) {
		this.wh_cd = wh_cd;
	}

	public String getItem_cd1_s() {
		return item_cd1_s;
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

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public String get_fromMenu() {
		return _fromMenu;
	}

	public void set_fromMenu(String menu) {
		_fromMenu = menu;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

	public String getUpload_no() {
		return upload_no;
	}

	public void setUpload_no(String upload_no) {
		this.upload_no = upload_no;
	}

	public String getSyukka_id() {
		return syukka_id;
	}

	public void setSyukka_id(String syukka_id) {
		this.syukka_id = syukka_id;
	}

	public String getSyukka_yotei_ymd_s() {
		return syukka_yotei_ymd_s;
	}

	public void setSyukka_yotei_ymd_s(String syukka_yotei_ymd_s) {
		this.syukka_yotei_ymd_s = syukka_yotei_ymd_s;
	}

	public String getSyukka_yotei_ymd_e() {
		return syukka_yotei_ymd_e;
	}

	public void setSyukka_yotei_ymd_e(String syukka_yotei_ymd_e) {
		this.syukka_yotei_ymd_e = syukka_yotei_ymd_e;
	}

	public String getTdk_ymd_s() {
		return tdk_ymd_s;
	}

	public void setTdk_ymd_s(String tdk_ymd_s) {
		this.tdk_ymd_s = tdk_ymd_s;
	}

	public String getTdk_ymd_e() {
		return tdk_ymd_e;
	}

	public void setTdk_ymd_e(String tdk_ymd_e) {
		this.tdk_ymd_e = tdk_ymd_e;
	}

	public String getToi_csv_out() {
		return toi_csv_out;
	}

	public void setToi_csv_out(String toi_csv_out) {
		this.toi_csv_out = toi_csv_out;
	}

	public String getHeader_csv_out_flg() {
		return header_csv_out_flg;
	}

	public void setHeader_csv_out_flg(String header_csv_out_flg) {
		this.header_csv_out_flg = header_csv_out_flg;
	}

	public String getList_csv_out_flg() {
		return list_csv_out_flg;
	}

	public void setList_csv_out_flg(String list_csv_out_flg) {
		this.list_csv_out_flg = list_csv_out_flg;
	}

	public String getToi_csv_out_flg() {
		return toi_csv_out_flg;
	}

	public void setToi_csv_out_flg(String toi_csv_out_flg) {
		this.toi_csv_out_flg = toi_csv_out_flg;
	}

	public String getItem_csv_out() {
		return item_csv_out;
	}

	public void setItem_csv_out(String item_csv_out) {
		this.item_csv_out = item_csv_out;
	}

	public String getOrder_ymd_s() {
		return order_ymd_s;
	}

	public void setOrder_ymd_s(String orderYmdS) {
		order_ymd_s = orderYmdS;
	}

	public String getOrder_ymd_e() {
		return order_ymd_e;
	}

	public void setOrder_ymd_e(String orderYmdE) {
		order_ymd_e = orderYmdE;
	}

	public String getTdk_cd() {
		return tdk_cd;
	}

	public void setTdk_cd(String tdkCd) {
		tdk_cd = tdkCd;
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

	public String getDef_ymd_s() {
		return def_ymd_s;
	}

	public void setDef_ymd_s(String def_ymd_s) {
		this.def_ymd_s = def_ymd_s;
	}

	public String getDef_ymd_e() {
		return def_ymd_e;
	}

	public void setDef_ymd_e(String def_ymd_e) {
		this.def_ymd_e = def_ymd_e;
	}

	public List<Map<String, String>> getSiten_list() {
		return siten_list;
	}

	public void setSiten_list(List<Map<String, String>> siten_list) {
		this.siten_list = siten_list;
	}

}