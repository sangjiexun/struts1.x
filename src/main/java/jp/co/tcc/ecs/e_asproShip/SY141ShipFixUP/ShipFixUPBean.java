package jp.co.tcc.ecs.e_asproShip.SY141ShipFixUP;

import java.util.Map;
import java.util.Vector;

import org.apache.commons.fileupload.disk.DiskFileItem;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

/**
 * [概 要]:出荷実績CSV登録<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */

public class ShipFixUPBean extends BaseBean {

	// ↓↓↓ 定数 ↓↓↓
	/** CSVアップロード区分 */
	public static final String CSV_UP_KBN = "21";
	/** 画面遷移Flag アップロード後ワーク登録 */
	public static final String PAGE_WAKU_INSERT = "12";
	// ↑↑↑ 定数 ↑↑↑

	/**起動区分*/
	private String processType = "";
	/**検索区分*/
	private String searchKbn = "";
	/**エラーメッセージ*/
	private String strErrMsg = "";

	/**パターン選択*/
	private String patternNo = "";
	/**パターン選択(プルダウンリスト)*/
	private Vector<Map<String, String>> patternNoList = null;

	// 履歴一覧
	private Vector<Map<String, String>> list = null;

	// 倉庫コード
	private String whCd = "";
	// CSVファイル
	private DiskFileItem csvFile = null;
	// ファイルパス
	private String csvFilePath = "";
	// UUID
	private String uuid = "";
	// アップロードNO
	private String uploadNo = "-1";

	// キャンセル区分（確認画面からキャンセルで戻ってきた場合に1）
	private String cancelKbn = "";

	protected void setSearchParamMap() {
		super.setSearchParamMap();
		this.addParamMap("searchKbn", this.searchKbn);
		this.addParamMap("whCd", this.whCd);
		this.addParamMap("patternNo", this.patternNo);
	}

	public Vector<Map<String, String>> getList() {
		return list;
	}
	public void setList(Vector<Map<String, String>> list) {
		this.list = list;
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

	public String getWhCd() {
		return whCd;
	}

	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}

	public DiskFileItem getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(DiskFileItem csvFile) {
		this.csvFile = csvFile;
	}

	public String getCsvFilePath() {
		return csvFilePath;
	}

	public void setCsvFilePath(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUploadNo() {
		return uploadNo;
	}

	public void setUploadNo(String uploadNo) {
		this.uploadNo = uploadNo;
	}

	public String getPatternNo() {
		return patternNo;
	}

	public void setPatternNo(String patternNo) {
		this.patternNo = patternNo;
	}

	public Vector<Map<String, String>> getPatternNoList() {
		return patternNoList;
	}

	public void setPatternNoList(Vector<Map<String, String>> patternNoList) {
		this.patternNoList = patternNoList;
	}

	public String getCancelKbn() {
		return cancelKbn;
	}

	public void setCancelKbn(String cancelKbn) {
		this.cancelKbn = cancelKbn;
	}
}
