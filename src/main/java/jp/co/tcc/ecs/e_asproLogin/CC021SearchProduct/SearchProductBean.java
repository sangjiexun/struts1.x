package jp.co.tcc.ecs.e_asproLogin.CC021SearchProduct;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class SearchProductBean extends BaseBean{
	// �N���敪
	private String processType = "";
	// ���No
	private String co_no = "";

	/**��������*/
	//���i��
	private String productNmSearch = "";
	//���i�R�[�h
	private String productCdSearch = "";

	//�����敪
	private String searchKbn = "";

	// �Ăяo����ʂ̖߂�֐�
	private String callsSrciptFuncName = "";
	// �s�ԍ�
	private String index = "";
	//���O�C�����[�U�[�̑q��
	private String login_wh_cd = "";
	private String login_wh_nm ="";

	//���ʏ��i�\���t���O
	private String kyotuDispFlg = "";

	//�x�ЃR�[�h
	private String sisyaCd = "";

	//���ʏ��i�̂ݕ\��
	private String kyotuOnlyFlg = "";
	//�x�Ж�
	private String sisyaNm = "";

	//�����F�x�ЃR�[�h
	private String sendSisyaCd = "";

	/**���X�g**/
	private List<Map<String, String>> list = null;

	//�x�Ѓ��X�g
	private List<Map<String, String>> sisyaList = null;

	// �ԉ�p�Q��Map
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