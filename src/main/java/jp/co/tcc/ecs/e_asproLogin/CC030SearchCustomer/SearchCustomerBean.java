package jp.co.tcc.ecs.e_asproLogin.CC030SearchCustomer;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class SearchCustomerBean extends BaseBean{
	// �N���敪
	private String processType = "";
	// ���No
	private String co_no = "";

	/**��������*/
	//���_�R�[�h
	private String kokyakuCdSearch = "";
	//�x�Ж�
	private String sisyaNm = "";
	//�x�ЃR�[�h(����)
	private String sisyaCd = "";
	//�x�ЃR�[�h
	private String sisyaCdSearch = "";
	//�����Җ�
	private String kokyakuNmSearch = "";
	//�����ҏZ��
	private String kokyakuAddrSearch = "";
	//�d�b�ԍ�
	private String kokyakuTelSearch = "";
	//FAX
	private String kokyakuFaxSearch = "";

	//�����敪
	private String searchKbn = "";
	/**���X�g**/
	private List<Map<String, String>> list = null;
	//�x�Ѓ��X�g
	private List<Map<String, String>> sisyaList = null;


	// �Ăяo����ʂ̖߂�֐�
	private String callsSrciptFuncName = "";
	// �s�ԍ�
	private String index = "";

	// �ԉ�p�Q��Map
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