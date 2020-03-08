package jp.co.tcc.ecs.e_asproShip.SY050ShijiFixEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class ShijiFixEntryBean extends BaseBean {
	public static final String PAGE_KOBETU = "8";
	public static final String PAGE_IKKATU = "9";
	/** Base Id */
	public final String BASE_ID = "SY050";
	/** CSV�o�͋敪 */
	public final String CSV_DOWN_KBN = "80";
	/** ����p�^�[���敪 */
	public final String PATTERN_NO = "1";
	// �N���敪
	private String processType = "";
	// �����敪
	private String searchKbn = "";

	// ������From
	private String order_ymd_s = "";
	// ������To
	private String order_ymd_e = "";
	// �����ԍ�
	private String order_cd = "";
	// �o�הԍ�
	private String sksj_cd = "";
	// �A�b�v���[�hNO
	private String upload_no = "";
	// �x��CD
	private String sisya_cd = "";
	// �x�XCD
	private String siten_cd = "";
	// ��t�XCD
	private String kokyaku_cd = "";
	// ��t�X��
	private String kokyaku_nm = "";
	// ���n�XCD
	private String tdk_cd = "";
	// �o�ח\���From
	private String syukka_yotei_ymd_s = "";
	// �o�ח\���To
	private String syukka_yotei_ymd_e = "";
	// �z�B�w���
	private String tdk_ymd_s = "";
	private String tdk_ymd_e = "";
	// �q�ɖ�
	private String wh_cd = "";
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
	// �G���[���Z�b�W
	private String strErrMsg = "";
	private String strExclMsg = "";
	// ���j���[�N���t���O
	private String _fromMenu = "";
	// CSV�o�͋敪
	private String csvDownKbn = "";

	private int intRecordCountExcl = 0;

	// �x�X���X�g
	private List<Map<String, String>> siten_list = null;

	// �o�׎w���m��f�[�^���X�g
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private String seq_no[] = {};

	// �ԉ�p�Q��Map
	protected void setSearchParamMap() {
		super.setSearchParamMap();
		this.addParamMap("searchKbn", this.searchKbn);
		// ������From
		this.addParamMap("order_ymd_s", this.order_ymd_s);
		// ������To
		this.addParamMap("order_ymd_e", this.order_ymd_e);
		// �����ԍ�
		this.addParamMap("order_cd", this.order_cd);
		// �o�הԍ�
		this.addParamMap("sksj_cd", this.sksj_cd);
		// �A�b�v���[�hNO
		this.addParamMap("upload_no", this.upload_no);
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
		// �o�ח\���From
		this.addParamMap("syukka_yotei_ymd_s", this.syukka_yotei_ymd_s);
		// �o�ח\���To
		this.addParamMap("syukka_yotei_ymd_e", this.syukka_yotei_ymd_e);
		// �z�B�w���
		this.addParamMap("tdk_ymd_s", this.tdk_ymd_s);
		this.addParamMap("tdk_ymd_e", this.tdk_ymd_e);
		// �q�ɖ�
		this.addParamMap("wh_cd", this.wh_cd);
		// ���iCD From
		this.addParamMap("item_cd1_s", this.item_cd1_s);
		// ���iCD To
		this.addParamMap("item_cd1_e", this.item_cd1_e);
		// ���i��
		this.addParamMap("item_nm1", this.item_nm1);
		// ���j���[�N���t���O
		this.addParamMap("_fromMenu", this._fromMenu);
		// �o�׋敪
		this.addParamMap("shipKbn", this.shipKbn);
		// ��/��ďo��
		this.addParamMap("orderKbn", this.orderKbn);
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

	public String getOrder_ymd_s() {
		return order_ymd_s;
	}

	public void setOrder_ymd_s(String order_ymd_s) {
		this.order_ymd_s = order_ymd_s;
	}

	public String getOrder_ymd_e() {
		return order_ymd_e;
	}

	public void setOrder_ymd_e(String order_ymd_e) {
		this.order_ymd_e = order_ymd_e;
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

	public String getUpload_no() {
		return upload_no;
	}

	public void setUpload_no(String upload_no) {
		this.upload_no = upload_no;
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

	public String[] getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(String[] seq_no) {
		this.seq_no = seq_no;
	}

	public String getStrExclMsg() {
		return strExclMsg;
	}

	public void setStrExclMsg(String strExclMsg) {
		this.strExclMsg = strExclMsg;
	}

	public int getIntRecordCountExcl() {
		return intRecordCountExcl;
	}

	public void setIntRecordCountExcl(int intRecordCountExcl) {
		this.intRecordCountExcl = intRecordCountExcl;
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

	public String getTdk_cd() {
		return tdk_cd;
	}

	public void setTdk_cd(String tdkCd) {
		tdk_cd = tdkCd;
	}

	public String getCsvDownKbn() {
		return csvDownKbn;
	}

	public void setCsvDownKbn(String csvDownKbn) {
		this.csvDownKbn = csvDownKbn;
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

	public List<Map<String, String>> getSiten_list() {
		return siten_list;
	}

	public void setSiten_list(List<Map<String, String>> siten_list) {
		this.siten_list = siten_list;
	}

}