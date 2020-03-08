package jp.co.tcc.ecs.e_asproLogin.CC031SearchZaiko;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class SearchZaikoBean extends BaseBean{
	// 起動区分
	private String processType = "";
	// 会社No
	private String co_no = "";

	/**検索条件*/
	//支社コード
	private String sisyaCdSearch = "";
	//支店コード
	private String sitenCdSearch = "";
	//在庫区分
	private String zaikoKbnSearch = "";
	//倉庫コード
	private String whCdSearch = "";
	//商品コード
	private String itemCdSearch = "";
	//在庫商品名
	private String itemNmSearch = "";


	/**引数にて渡された値を格納*/
	//支社
	private String sisyaCd = "";
	//支店
	private String sitenCd = "";
	//在庫区分
	private String zaikoKbn = "";
	//倉庫
	private String whCd = "";

	/**画面表示用変数*/
	//支社名
	private String sisyaNm = "";
	//在庫区分名
	private String zaikoKbnNm = "";
	//倉庫名
	private String whNm = "";

	/**画面表示時の検索条件*/

	//検索区分
	private String searchKbn = "";
	/**リスト**/
	private List<Map<String, String>> list = null;
	//支社リスト
	private List<Map<String, String>> sisyaList = null;
	//支店リスト
	private List<Map<String, String>> sitenList = null;
	//倉庫リスト
	private List<Map<String, String>> whList = null;
	//在庫区分リスト
	private List<Map<String, String>> zaikoKbnList = null;

	// 呼び出し画面の戻る関数
	private String callsSrciptFuncName = "";
	// 行番号
	private String index = "";

	// 返回用参数Map
	protected void setSearchParamMap() {
		super.setSearchParamMap();
		this.addParamMap("processType", this.processType);
		this.addParamMap("co_no", this.co_no);
		this.addParamMap("sisyaCdSearch", this.sisyaCdSearch);
		this.addParamMap("sitenCdSearch", this.sitenCdSearch);
		this.addParamMap("zaikoKbnSearch", this.zaikoKbnSearch);
		this.addParamMap("whCdSearch", this.whCdSearch);
		this.addParamMap("itemCdSearch", this.itemCdSearch);
		this.addParamMap("itemNmSearch", this.itemNmSearch);
		this.addParamMap("sisyaCd", this.sisyaCd);
		this.addParamMap("sitenCd", this.sitenCd);
		this.addParamMap("zaikoKbn", this.zaikoKbn);
		this.addParamMap("whCd", this.whCd);
		this.addParamMap("sisyaNm", this.sisyaNm);
		this.addParamMap("zaikoKbnNm", this.zaikoKbnNm);
		this.addParamMap("whNm", this.whNm);
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

	public String getWhCdSearch() {
		return whCdSearch;
	}

	public void setWhCdSearch(String whCdSearch) {
		this.whCdSearch = whCdSearch;
	}

	public String getWhCd() {
		return whCd;
	}

	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}

	public String getSisyaCdSearch() {
		return sisyaCdSearch;
	}

	public void setSisyaCdSearch(String sisyaCdSearch) {
		this.sisyaCdSearch = sisyaCdSearch;
	}

	public String getSitenCdSearch() {
		return sitenCdSearch;
	}

	public void setSitenCdSearch(String sitenCdSearch) {
		this.sitenCdSearch = sitenCdSearch;
	}

	public String getZaikoKbnSearch() {
		return zaikoKbnSearch;
	}

	public void setZaikoKbnSearch(String zaikoKbnSearch) {
		this.zaikoKbnSearch = zaikoKbnSearch;
	}

	public String getItemCdSearch() {
		return itemCdSearch;
	}

	public void setItemCdSearch(String itemCdSearch) {
		this.itemCdSearch = itemCdSearch;
	}

	public String getItemNmSearch() {
		return itemNmSearch;
	}

	public void setItemNmSearch(String itemNmSearch) {
		this.itemNmSearch = itemNmSearch;
	}

	public String getSisyaCd() {
		return sisyaCd;
	}

	public void setSisyaCd(String sisyaCd) {
		this.sisyaCd = sisyaCd;
	}

	public String getSitenCd() {
		return sitenCd;
	}

	public void setSitenCd(String sitenCd) {
		this.sitenCd = sitenCd;
	}

	public String getZaikoKbn() {
		return zaikoKbn;
	}

	public void setZaikoKbn(String zaikoKbn) {
		this.zaikoKbn = zaikoKbn;
	}

	public String getSisyaNm() {
		return sisyaNm;
	}

	public void setSisyaNm(String sisyaNm) {
		this.sisyaNm = sisyaNm;
	}

	public String getZaikoKbnNm() {
		return zaikoKbnNm;
	}

	public void setZaikoKbnNm(String zaikoKbnNm) {
		this.zaikoKbnNm = zaikoKbnNm;
	}

	public String getWhNm() {
		return whNm;
	}

	public void setWhNm(String whNm) {
		this.whNm = whNm;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

	public List<Map<String, String>> getSisyaList() {
		return sisyaList;
	}

	public void setSisyaList(List<Map<String, String>> sisyaList) {
		this.sisyaList = sisyaList;
	}

	public List<Map<String, String>> getSitenList() {
		return sitenList;
	}

	public void setSitenList(List<Map<String, String>> sitenList) {
		this.sitenList = sitenList;
	}

	public List<Map<String, String>> getWhList() {
		return whList;
	}

	public void setWhList(List<Map<String, String>> whList) {
		this.whList = whList;
	}

	public List<Map<String, String>> getZaikoKbnList() {
		return zaikoKbnList;
	}

	public void setZaikoKbnList(List<Map<String, String>> zaikoKbnList) {
		this.zaikoKbnList = zaikoKbnList;
	}
}