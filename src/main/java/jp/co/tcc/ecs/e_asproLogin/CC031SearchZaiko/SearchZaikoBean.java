package jp.co.tcc.ecs.e_asproLogin.CC031SearchZaiko;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class SearchZaikoBean extends BaseBean{
	// N®æª
	private String processType = "";
	// ïÐNo
	private String co_no = "";

	/**õð*/
	//xÐR[h
	private String sisyaCdSearch = "";
	//xXR[h
	private String sitenCdSearch = "";
	//ÝÉæª
	private String zaikoKbnSearch = "";
	//qÉR[h
	private String whCdSearch = "";
	//¤iR[h
	private String itemCdSearch = "";
	//ÝÉ¤i¼
	private String itemNmSearch = "";


	/**øÉÄn³ê½lði[*/
	//xÐ
	private String sisyaCd = "";
	//xX
	private String sitenCd = "";
	//ÝÉæª
	private String zaikoKbn = "";
	//qÉ
	private String whCd = "";

	/**æÊ\¦pÏ*/
	//xÐ¼
	private String sisyaNm = "";
	//ÝÉæª¼
	private String zaikoKbnNm = "";
	//qÉ¼
	private String whNm = "";

	/**æÊ\¦Ìõð*/

	//õæª
	private String searchKbn = "";
	/**Xg**/
	private List<Map<String, String>> list = null;
	//xÐXg
	private List<Map<String, String>> sisyaList = null;
	//xXXg
	private List<Map<String, String>> sitenList = null;
	//qÉXg
	private List<Map<String, String>> whList = null;
	//ÝÉæªXg
	private List<Map<String, String>> zaikoKbnList = null;

	// ÄÑoµæÊÌßéÖ
	private String callsSrciptFuncName = "";
	// sÔ
	private String index = "";

	// ÔñpQMap
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