package jp.co.tcc.ecs.e_asproShip.SY141ShipFixUP;

import jp.co.tcc.ecs.e_asproComm.common.InputChecker;
import jp.co.tcc.ecs.e_asproComm.common.Properties;
import jp.co.tcc.ecs.e_asproComm.common.csv.CommonCSVChk;
import jp.co.tcc.ecs.e_asproComm.common.csv.CsvChkRstBean;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

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

public class ShipFixUPService{
	ShipFixUPDAO dao = new ShipFixUPDAO();

	/**
	 * [�T �v]:�o�׎���CSV�o�^�B<br>
	 * [�� ��]:CSV�o�^���������f�[�^����������B<br>
	 * [�� �l]:<br>
	 * @param bean ShipFixUPBean
	 * @return bean ShipFixUPBean
	 * @throws Exception
	*/
	public ShipFixUPBean search(ShipFixUPBean bean) throws Exception {
		bean = dao.search(bean);
		return bean;
	}

	/**
	 * [�T �v]:�G���[�`�F�b�N�B<br>
	 * [�� ��]:<br>
	 * [�� �l]:<br>
	 * @param ShipFixUPBean
	 * @return String
	 * @throws Exception
	*/
	public String validator(ShipFixUPBean bean) throws Exception{
		StringBuffer sb = new StringBuffer();

		// �p�^�[���I�� �K�{�`�F�b�N
		if (InputChecker.MustCheck(bean.getPatternNo())) {
			sb.append(Properties.getMsg("ERROR.HA.001", new String[] {"�p�^�[���I��"}, true));
		}

		// CSV�t�@�C���̕K�{�`�F�b�N�A�g���q�`�F�b�N���s�Ȃ��iCSV�`���̂݋�����2������1�j
		sb.append(CommonCSVChk.checkCsvOutside(bean.getCsvFile(), 1));

		// ���ڐ��A���C�A�E�g�`�F�b�N
		sb.append(CommonCSVChk.checkCsvInside(bean.getCsvFilePath(), ShipFixUPBean.CSV_UP_KBN, bean.getPatternNo(), bean.getLoginUser(), true));

		return sb.toString();
	}

	/**
	 * [�T �v]:�o�^�{�^���������ɂ��A�捞���[�N���X�V<br>
	 * [�� ��]:<br>
	 * [�� �l]:<br>
	 * @param ShipFixUPBean
	 * @throws Exception
	 */
	public void updateW_EA052(ShipFixUPBean bean) throws Exception {

		// CSV�f�[�^�o�^�̋��ʌĂяo��
		CsvChkRstBean rstBean = CommonCSVChk.insertWork(bean.getCsvFilePath(), ShipFixUPBean.CSV_UP_KBN, bean.getPatternNo(), bean.getLoginUser(), true);
		bean.setUuid(rstBean.getUuid());
		bean.setUploadNo(StringUtil.nvl(rstBean.getUploadNo()));

		dao.updateW_EA052(bean);
	}

	/**
	 * [�T �v]:CSV���[�N�폜<br>
	 * [�� ��]:<br>
	 * @param String uuid
	 * @throws Exception
	 */
	public void deleteWork(String uuid) throws Exception {
		dao.deleteWork(uuid);
	}

}

