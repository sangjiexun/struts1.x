package jp.co.tcc.ecs.e_asproShip.SY010ShijiEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class SY010ShijiEntryService {

	/**
	 * �o�ׂ���������
	 *
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public SY010ShijiEntryBean search(SY010ShijiEntryBean bean) throws Exception {
		SY010ShijiEntryDAO dao = new SY010ShijiEntryDAO();
		HashMap<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map = dao.search(StringUtil.nvl(bean.getLoginUser().getCoNo()), bean.getSeq_no());
		bean.setLstHead((List<Map<String, String>>) map.get("LIST"));
		bean.setLstDetail((List<Map<String, String>>) map.get("LIST_DETAIL"));

		List<Map<String, String>> headInfo = bean.getLstHead();

		if (headInfo.size() > 0) {
			Map<String, String> row = headInfo.get(0);

			bean.setOrder_cd(StringUtil.nvl(row.get("ORDER_CD"))); // ����CD
			bean.setOrder_ymd_hms(StringUtil.nvl(row.get("ORDER_YMD_HM")));	//��������
			bean.setSisya_name(StringUtil.nvl(row.get("SISYA_NAME"))); // �x��
			bean.setWh_nm(StringUtil.nvl(row.get("WH_NM"))); //  �q��
			bean.setShip_kbn_nm(StringUtil.nvl(row.get("SHIP_KBN_NM"))); // �o�׋敪
			bean.setSyoriKbnNm(StringUtil.nvl(row.get("JOUTAI"))); // ������

			bean.setKokyaku_cd(StringUtil.nvl(row.get("KOKYAKU_CD"))); // ��t�XCD
			bean.setKokyaku_nm1(StringUtil.nvl(row.get("KOKYAKU_NM1"))); // ��t�X���P
			bean.setKokyaku_nm2(StringUtil.nvl(row.get("KOKYAKU_NM2"))); // ��t�X���Q
			bean.setKokyaku_kana1(StringUtil.nvl(row.get("KOKYAKU_KANA1"))); // ��t�X�J�i�P
			bean.setKokyaku_kana2(StringUtil.nvl(row.get("KOKYAKU_KANA2"))); // ��t�X�J�i�Q
			bean.setKokyaku_zip(StringUtil.nvl(row.get("KOKYAKU_ZIP"))); // ��t�X�E�X�֔ԍ�
			bean.setKokyaku_addr1(StringUtil.nvl(row.get("KOKYAKU_ADDR1"))); // ��t�X�E�Z���P
			bean.setKokyaku_addr2(StringUtil.nvl(row.get("KOKYAKU_ADDR2"))); // ��t�X�E�Z���Q
			bean.setKokyaku_addr3(StringUtil.nvl(row.get("KOKYAKU_ADDR3"))); // ��t�X�E�Z���R
			bean.setKokyaku_tel1(StringUtil.nvl(row.get("KOKYAKU_TEL1"))); // ��t�X�E�d�b�ԍ��P
			bean.setKokyaku_tel2(StringUtil.nvl(row.get("KOKYAKU_TEL2"))); // ��t�X�E�d�b�ԍ��Q
			bean.setKokyaku_fax(StringUtil.nvl(row.get("KOKYAKU_FAX"))); // ��t�X�EFAX
			bean.setKokyaku_mail1(StringUtil.nvl(row.get("KOKYAKU_MAIL1"))); // ��t�X�E���[���P
			bean.setKokyaku_mail2(StringUtil.nvl(row.get("KOKYAKU_MAIL2"))); // ��t�X�E���[���Q
			bean.setKokyaku_mail3(StringUtil.nvl(row.get("KOKYAKU_MAIL3"))); // ��t�X�E���[���R
			bean.setTdk_cd(StringUtil.nvl(row.get("TDK_CD"))); // ����XCD
			bean.setTdk_nm1(StringUtil.nvl(row.get("TDK_NM1"))); // ����X���P
			bean.setTdk_nm2(StringUtil.nvl(row.get("TDK_NM2"))); // ����X���Q
			bean.setTdk_kana1(StringUtil.nvl(row.get("TDK_KANA1"))); // ����X�J�i�P
			bean.setTdk_kana2(StringUtil.nvl(row.get("TDK_KANA2"))); // ����X�J�i�Q
			bean.setTdk_zip(StringUtil.nvl(row.get("TDK_ZIP"))); // ����X�E�X�֔ԍ�
			bean.setTdk_addr1(StringUtil.nvl(row.get("TDK_ADDR1"))); // ����X�E�Z���P
			bean.setTdk_addr2(StringUtil.nvl(row.get("TDK_ADDR2"))); // ����X�E�Z���Q
			bean.setTdk_addr3(StringUtil.nvl(row.get("TDK_ADDR3"))); // ����X�E�Z���R
			bean.setTdk_tel1(StringUtil.nvl(row.get("TDK_TEL1"))); // ����X�E�d�b�ԍ��P
			bean.setTdk_tel2(StringUtil.nvl(row.get("TDK_TEL2"))); // ����X�E�d�b�ԍ��Q

			bean.setTdk_ymd(StringUtil.nvl(row.get("TDK_YMD"))); // �z�B�w���
			bean.setHstm_kbn_nm(StringUtil.nvl(row.get("HSTM_KBN_NM"))); // ���͂����ԑы敪

			bean.setSksj_cd(StringUtil.nvl(row.get("SKSJ_CD"))); // �o�׎w���ԍ�
			bean.setSyukka_yotei_ymd(StringUtil.nvl(row.get("SYUKKA_YOTEI_YMD"))); // �o�ח\���
			bean.setKosu(StringUtil.nvl(row.get("KOSU"))); // ��
			bean.setOkurino(StringUtil.nvl(row.get("OKURINO"))); // �⍇��No
			bean.setOrder_biko(StringUtil.nvl(row.get("ORDER_BIKO"))); // �⍇��No

			bean.setMake_date(StringUtil.nvl(row.get("MAKE_DATE"))); // �o�^��
			bean.setRep_date(StringUtil.nvl(row.get("REP_DATE"))); // �X�V��
			bean.setRep_user_nm(StringUtil.nvl(row.get("REP_USER_NM"))); // �X�V��

			bean.setQty_cnt(StringUtil.nvl(row.get("QTY_CNT"))); // ���א��ʍ��v
			bean.setSeikyuu_kin(StringUtil.nvl(row.get("SEIKYUU_KIN"))); // �������z�i�Ŕ����j
			bean.setSeikyuu_zei_kin(StringUtil.nvl(row.get("SEIKYUU_ZEI_KIN"))); // �����
			bean.setSeikyuu_zei_k_kin(StringUtil.nvl(row.get("SEIKYUU_ZEI_K_KIN"))); // �������z�i�ō��݁j
		}
		return bean;
	}

}
