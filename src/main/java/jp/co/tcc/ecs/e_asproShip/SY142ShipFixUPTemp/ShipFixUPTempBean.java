package jp.co.tcc.ecs.e_asproShip.SY142ShipFixUPTemp;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class ShipFixUPTempBean  extends BaseBean {

	/**起動区分*/
	private String processType = "";
	/**排他カウンタ*/
	private String exclCnt = "";
	/**検索区分*/
	private String searchKbn = "";
	/**エラーメッセージ*/
	private String strErrMsg = "";

	// ↓↓↓ 前画面から渡される値 ↓↓↓
	/**CSVアップロード区分 **/
	private String csvupKbn = "";
	/**パターンNO*/
	private String patternNo ="";
	/**倉庫コード*/
	private String whCd = "";
	/**uuid*/
	private String uuid = "";
	/**アップロードNO*/
	private int uploadNo = -1;
	/**ファイル名*/
	private String fileNm = "";
	// ↑↑↑ 前画面から渡される値 ↑↑↑

	// 検索チェックフラグ
	private String searchStatus = "0";
	// エラー件数
	private int errorCnt = 0;

	// 一覧リスト
	private List<Map<String, String>> list = null;
	// 一覧用ヘッダー項目リスト
	private List<Map<String, String>> header_list = null;

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	// 返回用参数Map
	protected void setSearchParamMap() {
		super.setSearchParamMap();

		this.addParamMap("processType", this.processType);
		this.addParamMap("searchKbn", this.searchKbn);

		this.addParamMap("csvupKbn", this.csvupKbn);
		this.addParamMap("patternNo", this.patternNo);
		this.addParamMap("whCd", this.whCd);
		this.addParamMap("uuid", this.uuid);
		this.addParamMap("uploadNo", this.uploadNo);
		this.addParamMap("fileNm", this.fileNm);
		this.addParamMap("searchStatus", this.searchStatus);
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

	public String getSearchKbn() {
		return searchKbn;
	}

	public void setSearchKbn(String searchKbn) {
		this.searchKbn = searchKbn;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getUploadNo() {
		return uploadNo;
	}

	public void setUploadNo(int uploadNo) {
		this.uploadNo = uploadNo;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getPatternNo() {
		return patternNo;
	}

	public void setPatternNo(String patternNo) {
		this.patternNo = patternNo;
	}

	public String getCsvupKbn() {
		return csvupKbn;
	}

	public void setCsvupKbn(String csvupKbn) {
		this.csvupKbn = csvupKbn;
	}

	public String getWhCd() {
		return whCd;
	}

	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public int getErrorCnt() {
		return errorCnt;
	}

	public void setErrorCnt(int errorCnt) {
		this.errorCnt = errorCnt;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

	public List<Map<String, String>> getHeader_list() {
		return header_list;
	}

	public void setHeader_list(List<Map<String, String>> header_list) {
		this.header_list = header_list;
	}
}
