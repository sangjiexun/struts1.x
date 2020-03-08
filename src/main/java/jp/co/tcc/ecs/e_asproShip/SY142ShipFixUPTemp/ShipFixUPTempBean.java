package jp.co.tcc.ecs.e_asproShip.SY142ShipFixUPTemp;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class ShipFixUPTempBean  extends BaseBean {

	/**�N���敪*/
	private String processType = "";
	/**�r���J�E���^*/
	private String exclCnt = "";
	/**�����敪*/
	private String searchKbn = "";
	/**�G���[���b�Z�[�W*/
	private String strErrMsg = "";

	// ������ �O��ʂ���n�����l ������
	/**CSV�A�b�v���[�h�敪 **/
	private String csvupKbn = "";
	/**�p�^�[��NO*/
	private String patternNo ="";
	/**�q�ɃR�[�h*/
	private String whCd = "";
	/**uuid*/
	private String uuid = "";
	/**�A�b�v���[�hNO*/
	private int uploadNo = -1;
	/**�t�@�C����*/
	private String fileNm = "";
	// ������ �O��ʂ���n�����l ������

	// �����`�F�b�N�t���O
	private String searchStatus = "0";
	// �G���[����
	private int errorCnt = 0;

	// �ꗗ���X�g
	private List<Map<String, String>> list = null;
	// �ꗗ�p�w�b�_�[���ڃ��X�g
	private List<Map<String, String>> header_list = null;

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	// �ԉ�p�Q��Map
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
