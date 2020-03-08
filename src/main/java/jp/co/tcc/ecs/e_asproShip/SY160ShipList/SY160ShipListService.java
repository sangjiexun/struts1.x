package jp.co.tcc.ecs.e_asproShip.SY160ShipList;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.csv.CsvDownloader;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [�T �v]:�o�׈ꗗService<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */
public class SY160ShipListService {
	SY160ShipListDAO dao = new SY160ShipListDAO();
	/**
	 * [�T �v]:�o�׈ꗗ����<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public void search(SY160ShipListBean bean) throws Exception {
		dao.search(bean, "0");
	}

	/**
	 * [�T �v]:�o�׈ꗗcsv�o�͏���<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 * @param response
	 * @param bean
	 * @param userNo
	 * @return
	 * @throws Exception
	 */
	public String downLoad(HttpServletResponse response, SY160ShipListBean bean) throws Exception {

		String errM = "";
		String fileName = "";
		if (bean.CSV_HEAD_DOWN.equals(bean.getCsv_down_kbn())) {
			//�w�b�_�[
			fileName = "ShipList_Header";
		}else if (bean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
			// ����CSV�o��
			fileName = "ShipList_Detail";
		}else if (bean.CSV_OKURINO_DOWN.equals(bean.getCsv_down_kbn())) {
			// �⍇��NoCSV�o��
			fileName = "ShipList_Toi";
		}else if (bean.CSV_ITEM_DOWN.equals(bean.getCsv_down_kbn())) {
			// ���i�ʏo�א�CSV�o��
			fileName = "ShipList_Item";
		}
		// �p�����[�^�Z�b�g
		bean.setIntPageNo(1);
		bean.setIntPageSize(999999999);
		List<SQLParamType> listParams = dao.setListParams(bean, "1");

		// CSV�o��
		String csvDownKbn = bean.getCsv_down_kbn();
		CsvDownloader downloader = new CsvDownloader(response, fileName, String.valueOf(bean.getLoginUser().getCoNo()), csvDownKbn, StringUtil.nvl(bean.getPattern_no(),"1"));
		if (!downloader.download("SY160.search", listParams, "LIST", true)) {
			errM = downloader.getErrorMessage();
		}
		return errM;
	}

}
