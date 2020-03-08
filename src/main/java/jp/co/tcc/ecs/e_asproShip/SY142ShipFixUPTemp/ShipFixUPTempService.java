package jp.co.tcc.ecs.e_asproShip.SY142ShipFixUPTemp;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.csv.AbsCSVClass;
import jp.co.tcc.ecs.e_asproComm.common.csv.CsvErrDownloader;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;

public class ShipFixUPTempService {

	/**
	 * [�T �v]:CSV�o�^�i�m�F�j �f�[�^�擾<br>
	 * [�� ��]:���[�N�e�[�u������A�b�v���[�h�{�G���[�`�F�b�N�������ʂ��擾<br>
	 * [�� �l]:<br>
	 * @param bean ShipFixUPTempBean
	 * @return bean ShipFixUPTempBean
	 * @throws Exception
	*/
	public ShipFixUPTempBean search(ShipFixUPTempBean bean) throws Exception{
		ShipFixUPTempDAO dao = new ShipFixUPTempDAO();
		bean = dao.search(bean);
		return bean;
	}
	/**
	 * [�T �v]:CSV�o�^�i�m�F�j �w�b�_�[���ڎ擾<br>
	 * [�� ��]:�ꗗ�\���p�̃w�b�_�[���ڂ��擾
	 * [�� �l]:
	 * @param bean ShipFixUPTempBean
	 * @return bean ShipFixUPTempBean
	 * @throws Exception
	 */
	public ShipFixUPTempBean search_header(ShipFixUPTempBean bean) throws Exception {
		AbsCSVClass csvClass = new AbsCSVClass();
		bean.setHeader_list(csvClass.getColNameList(String.valueOf(bean.getLoginUser().getCoNo()), bean.getCsvupKbn(), bean.getPatternNo()));
		return bean;
	}

	/**
	 * [�T �v]:CSV�o�^�i�m�F�j �o�^<br>
	 * [�� ��]:���[�N�e�[�u������{�e�[�u���ɍX�V<br>
	 * [�� �l]:<br>
	 * @param bean ShipFixUPTempBean
	 * @return
	 * @throws Exception
	*/
	public void update(ShipFixUPTempBean bean) throws Exception {
		ShipFixUPTempDAO dao = new ShipFixUPTempDAO();
		dao.update(bean);
	}
	/**
	 * [�T �v]:CSV�o�^�i�m�F�j CSV�o��<br>
	 * [�� ��]:�G���[�f�[�^��CSV�o�͂���<br>
	 * [�� �l]:<br>
	 *
	 * @param HttpServletResponse response
	 * @param ShipFixUPTempBean bean
	 * @return String
	 * @throws Exception
	 */
	public String download(HttpServletResponse response, ShipFixUPTempBean bean) throws Exception {
		String fileName = "Error_ShipFixUp";
		//�G���[����Ō������Ă����Ԃɂ���
		bean.setSearchStatus("-1");
		// �p�����[�^�Z�b�g
		bean.setIntPageNo(1);
		bean.setIntPageSize(999999999);

		ShipFixUPTempDAO dao = new ShipFixUPTempDAO();
		List<SQLParamType> parameterList = dao.setListParams(bean);

		CsvErrDownloader downloader = new CsvErrDownloader(response, fileName, String.valueOf(bean.getLoginUser().getCoNo()), bean.getCsvupKbn(), bean.getPatternNo());
		//���s�v���V�[�W���͌����ƈꏏ
		if (!downloader.download("SY142.search", parameterList, "LIST", true)) {
			return downloader.getErrorMessage();
		}
		return "";
	}

}
