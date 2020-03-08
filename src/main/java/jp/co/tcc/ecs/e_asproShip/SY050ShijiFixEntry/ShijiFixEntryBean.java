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
	/** CSV出力区分 */
	public final String CSV_DOWN_KBN = "80";
	/** 印刷パターン区分 */
	public final String PATTERN_NO = "1";
	// 起動区分
	private String processType = "";
	// 検索区分
	private String searchKbn = "";

	// 注文日From
	private String order_ymd_s = "";
	// 注文日To
	private String order_ymd_e = "";
	// 注文番号
	private String order_cd = "";
	// 出荷番号
	private String sksj_cd = "";
	// アップロードNO
	private String upload_no = "";
	// 支社CD
	private String sisya_cd = "";
	// 支店CD
	private String siten_cd = "";
	// 受付店CD
	private String kokyaku_cd = "";
	// 受付店名
	private String kokyaku_nm = "";
	// 引渡店CD
	private String tdk_cd = "";
	// 出荷予定日From
	private String syukka_yotei_ymd_s = "";
	// 出荷予定日To
	private String syukka_yotei_ymd_e = "";
	// 配達指定日
	private String tdk_ymd_s = "";
	private String tdk_ymd_e = "";
	// 倉庫名
	private String wh_cd = "";
	// 商品CD From
	private String item_cd1_s = "";
	// 商品CD To
	private String item_cd1_e = "";
	// 商品名
	private String item_nm1 = "";
	// 出荷区分
	private String shipKbn = "";
	// 個別/一斉出荷
	private String orderKbn = "";
	// エラーメセッジ
	private String strErrMsg = "";
	private String strExclMsg = "";
	// メニュー起動フラグ
	private String _fromMenu = "";
	// CSV出力区分
	private String csvDownKbn = "";

	private int intRecordCountExcl = 0;

	// 支店リスト
	private List<Map<String, String>> siten_list = null;

	// 出荷指示確定データリスト
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private String seq_no[] = {};

	// 返回用参数Map
	protected void setSearchParamMap() {
		super.setSearchParamMap();
		this.addParamMap("searchKbn", this.searchKbn);
		// 注文日From
		this.addParamMap("order_ymd_s", this.order_ymd_s);
		// 注文日To
		this.addParamMap("order_ymd_e", this.order_ymd_e);
		// 注文番号
		this.addParamMap("order_cd", this.order_cd);
		// 出荷番号
		this.addParamMap("sksj_cd", this.sksj_cd);
		// アップロードNO
		this.addParamMap("upload_no", this.upload_no);
		// 支社CD
		this.addParamMap("sisya_cd", this.sisya_cd);
		// 支店CD
		this.addParamMap("siten_cd", this.siten_cd);
		// 受付店CD
		this.addParamMap("kokyaku_cd", this.kokyaku_cd);
		// 受付店名
		this.addParamMap("kokyaku_nm", this.kokyaku_nm);
		// 引渡店CD
		this.addParamMap("tdk_cd", this.tdk_cd);
		// 出荷予定日From
		this.addParamMap("syukka_yotei_ymd_s", this.syukka_yotei_ymd_s);
		// 出荷予定日To
		this.addParamMap("syukka_yotei_ymd_e", this.syukka_yotei_ymd_e);
		// 配達指定日
		this.addParamMap("tdk_ymd_s", this.tdk_ymd_s);
		this.addParamMap("tdk_ymd_e", this.tdk_ymd_e);
		// 倉庫名
		this.addParamMap("wh_cd", this.wh_cd);
		// 商品CD From
		this.addParamMap("item_cd1_s", this.item_cd1_s);
		// 商品CD To
		this.addParamMap("item_cd1_e", this.item_cd1_e);
		// 商品名
		this.addParamMap("item_nm1", this.item_nm1);
		// メニュー起動フラグ
		this.addParamMap("_fromMenu", this._fromMenu);
		// 出荷区分
		this.addParamMap("shipKbn", this.shipKbn);
		// 個別/一斉出荷
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