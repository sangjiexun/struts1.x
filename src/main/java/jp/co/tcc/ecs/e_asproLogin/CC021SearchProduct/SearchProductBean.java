package jp.co.tcc.ecs.e_asproLogin.CC021SearchProduct;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class SearchProductBean extends BaseBean{
	// 起動区分
	private String processType = "";
	// 会社No
	private String co_no = "";

	/**検索条件*/
	//商品名
	private String productNmSearch = "";
	//商品コード
	private String productCdSearch = "";

	//検索区分
	private String searchKbn = "";

	// 呼び出し画面の戻る関数
	private String callsSrciptFuncName = "";
	// 行番号
	private String index = "";
	//ログインユーザーの倉庫
	private String login_wh_cd = "";
	private String login_wh_nm ="";

	//共通商品表示フラグ
	private String kyotuDispFlg = "";

	//支社コード
	private String sisyaCd = "";

	//共通商品のみ表示
	private String kyotuOnlyFlg = "";
	//支社名
	private String sisyaNm = "";

	//引数：支社コード
	private String sendSisyaCd = "";

	/**リスト**/
	private List<Map<String, String>> list = null;

	//支社リスト
	private List<Map<String, String>> sisyaList = null;

	// 返回用参数Map
	protected void setSearchParamMap() {
		super.setSearchParamMap();
		this.addParamMap("processType", this.processType);
		this.addParamMap("co_no", this.co_no);
		this.addParamMap("productNmSearch", this.productNmSearch);
		this.addParamMap("productCdSearch", this.productCdSearch);
		this.addParamMap("kyotuOnlyFlg", this.kyotuOnlyFlg);
		this.addParamMap("kyotuDispFlg", this.kyotuDispFlg);
		this.addParamMap("sisyaCd", this.sisyaCd);
		this.addParamMap("sendSisyaCd", this.sendSisyaCd);
		this.addParamMap("sisyaNm", this.sisyaNm);
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

	public String getProductCdSearch() {
		return productCdSearch;
	}

	public void setProductCdSearch(String productCdSearch) {
		this.productCdSearch = productCdSearch;
	}

	public String getProductNmSearch() {
		return productNmSearch;
	}

	public void setProductNmSearch(String productNmSearch) {
		this.productNmSearch = productNmSearch;
	}

	public String getSearchKbn() {
		return searchKbn;
	}

	public void setSearchKbn(String searchKbn) {
		this.searchKbn = searchKbn;
	}

	public String getLogin_wh_cd() {
		return login_wh_cd;
	}

	public void setLogin_wh_cd(String loginWhCd) {
		login_wh_cd = loginWhCd;
	}

	public String getLogin_wh_nm() {
		return login_wh_nm;
	}

	public void setLogin_wh_nm(String loginWhNm) {
		login_wh_nm = loginWhNm;
	}

	public String getKyotuDispFlg() {
		return kyotuDispFlg;
	}

	public void setKyotuDispFlg(String kyotuDispFlg) {
		this.kyotuDispFlg = kyotuDispFlg;
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

	public String getKyotuOnlyFlg() {
		return kyotuOnlyFlg;
	}

	public void setKyotuOnlyFlg(String kyotuOnlyFlg) {
		this.kyotuOnlyFlg = kyotuOnlyFlg;
	}

	public String getSisyaNm() {
		return sisyaNm;
	}

	public void setSisyaNm(String sisyaNm) {
		this.sisyaNm = sisyaNm;
	}

	public String getSendSisyaCd() {
		return sendSisyaCd;
	}

	public void setSendSisyaCd(String sendSisyaCd) {
		this.sendSisyaCd = sendSisyaCd;
	}
}