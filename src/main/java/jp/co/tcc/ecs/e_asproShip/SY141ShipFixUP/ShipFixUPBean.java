package jp.co.tcc.ecs.e_asproShip.SY141ShipFixUP;

import java.util.Map;
import java.util.Vector;

import org.apache.commons.fileupload.disk.DiskFileItem;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

/**
 * [�T �v]:�o�׎���CSV�o�^<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */

public class ShipFixUPBean extends BaseBean {

	// ������ �萔 ������
	/** CSV�A�b�v���[�h�敪 */
	public static final String CSV_UP_KBN = "21";
	/** ��ʑJ��Flag �A�b�v���[�h�ハ�[�N�o�^ */
	public static final String PAGE_WAKU_INSERT = "12";
	// ������ �萔 ������

	/**�N���敪*/
	private String processType = "";
	/**�����敪*/
	private String searchKbn = "";
	/**�G���[���b�Z�[�W*/
	private String strErrMsg = "";

	/**�p�^�[���I��*/
	private String patternNo = "";
	/**�p�^�[���I��(�v���_�E�����X�g)*/
	private Vector<Map<String, String>> patternNoList = null;

	// �����ꗗ
	private Vector<Map<String, String>> list = null;

	// �q�ɃR�[�h
	private String whCd = "";
	// CSV�t�@�C��
	private DiskFileItem csvFile = null;
	// �t�@�C���p�X
	private String csvFilePath = "";
	// UUID
	private String uuid = "";
	// �A�b�v���[�hNO
	private String uploadNo = "-1";

	// �L�����Z���敪�i�m�F��ʂ���L�����Z���Ŗ߂��Ă����ꍇ��1�j
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
