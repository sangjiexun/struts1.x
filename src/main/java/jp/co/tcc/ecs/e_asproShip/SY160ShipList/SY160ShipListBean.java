package jp.co.tcc.ecs.e_asproShip.SY160ShipList;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

/**
 * [概 要]:出荷一覧Bean<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */
public class SY160ShipListBean extends BaseBean {
	/** CSV出力区分 */
	public final String CSV_HEAD_DOWN = "6";
	public final String CSV_LIST_DOWN = "7";
	public final String CSV_OKURINO_DOWN = "71";
	public final String CSV_ITEM_DOWN = "72";

	// 起動区分
	private String processType = "";
	// 検索区分
	private String searchKbn = "";
	// 受注確定日From
	private String sksj_ymd_s = "";
	// 受注確定日To
	private String sksj_ymd_e = "";
	// 注文日From
	private String order_ymd_s = "";
	// 注文日To
	private String order_ymd_e = "";
	// 注文番号
	private String order_cd = "";
	// 出荷指示番号
	private String sksj_cd = "";
	// 支社CD
	private String sisya_cd = "";
	// 支店CD
	private String siten_cd = "";
	// 受付店
	private String kokyaku_cd = "";
	// 受付店名
	private String kokyaku_nm = "";
	// 引渡店CD
	private String tdk_cd = "";
	// 引渡名
	private String tdk_nm = "";
	// 引渡店住所
	private String tdk_addr = "";
	// デフォルト指定日From
	private String def_ymd_s = "";
	// デフォルト指定日To
	private String def_ymd_e = "";
	// 出荷予定日From
	private String syukka_yotei_ymd_s = "";
	// 出荷予定日To
	private String syukka_yotei_ymd_e = "";
	// 配達指定日From
	private String tdk_ymd_s = "";
	// 配達指定日To
	private String tdk_ymd_e = "";
	// 倉庫CD
	private String wh_cd = "";
	// 問合せNo From
	private String okurino_s = "";
	// 問合せNo To
	private String okurino_e = "";
	// 指示確定 From
	private String syukka_ok_ymd_s = "";
	// 指示確定 To
	private String syukka_ok_ymd_e = "";
	// 実績確定 From
	private String toiout_ymd_s = "";
	// 実績確定 To
	private String toiout_ymd_e = "";
	// 処理状況
	private String syori_jyoukyou = "";
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
	// 過去検索フラグ
	private String past_flg = "";

	// ヘッダCSV出力
	private String header_csv_out = "";
	// 明細CSV出力
	private String list_csv_out = "";
	// 問合せNoCSV出力
	private String toi_csv_out = "";
	// 商品別出荷数CSV出力
	private String item_csv_out = "";

	// パターンNO
	private int pattern_no = 0;
	// CSVダウンロード区分
	private String csv_down_kbn = "";
	// アップロードNO
	private String upload_no = "";
	// 出荷指示確定ID
	private String syukka_id = "";
	// エラーメセッジ
	private String strErrMsg = "";
	// メニュー起動フラグ
	private String _fromMenu = "";
	// ヘッダCSV出力表示フラグ
	private String header_csv_out_flg = "";
	// 明細CSV出力表示フラグ
	private String list_csv_out_flg = "";
	// 問合せNoCSV出力表示Flg
	private String toi_csv_out_flg = "";

	// 支店リスト
	private List<Map<String, String>> siten_list = null;

	// 出荷一覧データリスト
	private List<Map<String, String>> list = null;

	// 返回用参数Map
	protected void setSearchParamMap() {
		super.setSearchParamMap();
		// デフォルト指定日from
		this.addParamMap("def_ymd_s", this.def_ymd_s);
		// デフォルト指定日To
		this.addParamMap("def_ymd_e", this.def_ymd_e);
		// 受注確定日From
		this.addParamMap("sksj_ymd_s", this.sksj_ymd_s);
		// 受注確定日To
		this.addParamMap("sksj_ymd_e", this.sksj_ymd_e);
		// 注文日From
		this.addParamMap("order_ymd_s", this.order_ymd_s);
		// 注文日To
		this.addParamMap("order_ymd_e", this.order_ymd_e);
		// 注文番号
		this.addParamMap("order_cd", this.order_cd);
		// 出荷指示番号
		this.addParamMap("sksj_cd", this.sksj_cd);
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
		// 引渡店名
		this.addParamMap("tdk_nm", this.tdk_nm);
		// 引渡店住所
		this.addParamMap("tdk_addr", this.tdk_addr);
		// 出荷予定日From
		this.addParamMap("syukka_yotei_ymd_s", this.syukka_yotei_ymd_s);
		// 出荷予定日To
		this.addParamMap("syukka_yotei_ymd_e", this.syukka_yotei_ymd_e);
		// 配達指定日From
		this.addParamMap("tdk_ymd_s", this.tdk_ymd_s);
		// 配達指定日To
		this.addParamMap("tdk_ymd_e", this.tdk_ymd_e);
		// 処理状況
		this.addParamMap("syori_jyoukyou", this.syori_jyoukyou);
		// 倉庫CD
		this.addParamMap("wh_cd", this.wh_cd);
		// 問合せNo From
		this.addParamMap("okurino_s", this.okurino_s);
		// 問合せNo To
		this.addParamMap("okurino_e", this.okurino_e);
		// 指示確定 From
		this.addParamMap("syukka_ok_ymd_s", this.syukka_ok_ymd_s);
		// 指示確定 To
		this.addParamMap("syukka_ok_ymd_e", this.syukka_ok_ymd_e);
		// 実績確定 From
		this.addParamMap("toiout_ymd_s", this.toiout_ymd_s);
		// 実績確定 To
		this.addParamMap("toiout_ymd_e", this.toiout_ymd_e);
		// 商品CD From
		this.addParamMap("item_cd1_s", this.item_cd1_s);
		// 商品CD To
		this.addParamMap("item_cd1_e", this.item_cd1_e);
		// 商品名
		this.addParamMap("item_nm1", this.item_nm1);
		// 出荷区分
		this.addParamMap("shipKbn", this.shipKbn);
		// 個別/一斉出荷
		this.addParamMap("orderKbn", this.orderKbn);
		// 過去検索フラグ
		this.addParamMap("past_flg", this.past_flg);

		// メニュー起動フラグ
		this.addParamMap("_fromMenu", this._fromMenu);
		// アップロードNO
		this.addParamMap("upload_no", this.upload_no);
		// 出荷指示確定ID
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