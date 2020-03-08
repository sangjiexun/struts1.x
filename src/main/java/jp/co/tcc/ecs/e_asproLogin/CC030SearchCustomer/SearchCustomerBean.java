package jp.co.tcc.ecs.e_asproLogin.CC030SearchCustomer;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class SearchCustomerBean extends BaseBean{
	// 起動区分
	private String processType = "";
	// 会社No
	private String co_no = "";

	/**検索条件*/
	//拠点コード
	private String kokyakuCdSearch = "";
	//支社名
	private String sisyaNm = "";
	//支社コード(引数)
	private String sisyaCd = "";
	//支社コード
	private String sisyaCdSearch = "";
	//注文者名
	private String kokyakuNmSearch = "";
	//注文者住所
	private String kokyakuAddrSearch = "";
	//電話番号
	private String kokyakuTelSearch = "";
	//FAX
	private String kokyakuFaxSearch = "";

	//検索区分
	private String searchKbn = "";
	/**リスト**/
	private List<Map<String, String>> list = null;
	//支社リスト
	private List<Map<String, String>> sisyaList = null;


	// 呼び出し画面の戻る関数
	private String callsSrciptFuncName = "";
	// 行番号
	private String index = "";

	// 返回用参数Map
	protected void setSearchParamMap() {
		super.setSearchParamMap();
		this.addParamMap("processType", this.processType);
		this.addParamMap("co_no", this.co_no);
		this.addParamMap("kokyakuCdSearch", this.kokyakuCdSearch);
		this.addParamMap("sisyaNm", this.sisyaNm);
		this.addParamMap("sisyaCd", this.sisyaCd);
		this.addParamMap("sisyaCdSearch", this.sisyaCdSearch);
		this.addParamMap("kokyakuNmSearch", this.kokyakuNmSearch);
		this.addParamMap("kokyakuAddrSearch", this.kokyakuAddrSearch);
		this.addParamMap("kokyakuTelSearch", this.kokyakuTelSearch);
		this.addParamMap("kokyakuFaxSearch", this.kokyakuFaxSearch);
		this.addParamMap("searchKbn", this.searchKbn);
		this.addParamMap("callsSrciptFuncName", this.callsSrciptFuncName);
		this.addParamMap("index", this.index);
	}

	public String getCallsSrciptFuncName() {
		return callsSrciptFuncName;
	}

	public void setCallsSrciptFuncName(String callsSrciptFuncName) {
		this.callsSrciptFuncName = callsSrciptFuncName;
	}

	public String getCo_no() {
		return co_no;
	}

	public void setCo_no(String co_no) {
		this.co_no = co_no;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getKokyakuAddrSearch() {
		return kokyakuAddrSearch;
	}

	public void setKokyakuAddrSearch(String kokyakuAddrSearch) {
		this.kokyakuAddrSearch = kokyakuAddrSearch;
	}

	public String getKokyakuCdSearch() {
		return kokyakuCdSearch;
	}

	public void setKokyakuCdSearch(String kokyakuCdSearch) {
		this.kokyakuCdSearch = kokyakuCdSearch;
	}

	public String getKokyakuFaxSearch() {
		return kokyakuFaxSearch;
	}

	public void setKokyakuFaxSearch(String kokyakuFaxSearch) {
		this.kokyakuFaxSearch = kokyakuFaxSearch;
	}

	public String getKokyakuNmSearch() {
		return kokyakuNmSearch;
	}

	public void setKokyakuNmSearch(String kokyakuNmSearch) {
		this.kokyakuNmSearch = kokyakuNmSearch;
	}

	public String getKokyakuTelSearch() {
		return kokyakuTelSearch;
	}

	public void setKokyakuTelSearch(String kokyakuTelSearch) {
		this.kokyakuTelSearch = kokyakuTelSearch;
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

	public String getSisyaCdSearch() {
		return sisyaCdSearch;
	}

	public void setSisyaCdSearch(String sisyaCdSearch) {
		this.sisyaCdSearch = sisyaCdSearch;
	}

	public String getSisyaNm() {
		return sisyaNm;
	}

	public void setSisyaNm(String sisyaNm) {
		this.sisyaNm = sisyaNm;
	}

	public String getSisyaCd() {
		return sisyaCd;
	}

	public void setSisyaCd(String sisyaCd) {
		this.sisyaCd = sisyaCd;
	}

	public List<Map<String, String>> getSisyaList() {
		return sisyaList;
	}

	public void setSisyaList(List<Map<String, String>> sisyaList) {
		this.sisyaList = sisyaList;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

}