package jp.co.tcc.ecs.e_asproShip.SY160ShipList;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.csv.CsvDownloader;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概 要]:出荷一覧Service<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */
public class SY160ShipListService {
	SY160ShipListDAO dao = new SY160ShipListDAO();
	/**
	 * [概 要]:出荷一覧検索<br>
	 * [説 明]:<br>
	 * [備 考]:
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public void search(SY160ShipListBean bean) throws Exception {
		dao.search(bean, "0");
	}

	/**
	 * [概 要]:出荷一覧csv出力処理<br>
	 * [説 明]:<br>
	 * [備 考]:
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
			//ヘッダー
			fileName = "ShipList_Header";
		}else if (bean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
			// 明細CSV出力
			fileName = "ShipList_Detail";
		}else if (bean.CSV_OKURINO_DOWN.equals(bean.getCsv_down_kbn())) {
			// 問合せNoCSV出力
			fileName = "ShipList_Toi";
		}else if (bean.CSV_ITEM_DOWN.equals(bean.getCsv_down_kbn())) {
			// 商品別出荷数CSV出力
			fileName = "ShipList_Item";
		}
		// パラメータセット
		bean.setIntPageNo(1);
		bean.setIntPageSize(999999999);
		List<SQLParamType> listParams = dao.setListParams(bean, "1");

		// CSV出力
		String csvDownKbn = bean.getCsv_down_kbn();
		CsvDownloader downloader = new CsvDownloader(response, fileName, String.valueOf(bean.getLoginUser().getCoNo()), csvDownKbn, StringUtil.nvl(bean.getPattern_no(),"1"));
		if (!downloader.download("SY160.search", listParams, "LIST", true)) {
			errM = downloader.getErrorMessage();
		}
		return errM;
	}

}
