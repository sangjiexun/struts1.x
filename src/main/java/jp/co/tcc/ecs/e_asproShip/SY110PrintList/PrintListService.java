package jp.co.tcc.ecs.e_asproShip.SY110PrintList;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CSVUtil;
import jp.co.tcc.ecs.e_asproComm.common.csv.CsvDownloader;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;
/**
 * [概 要]:出荷指示データ　Service<br>
 * [説 明]:<br>
 * [備 考]:<br>
 */
public class PrintListService {
	PrintListDAO dao = new PrintListDAO();
	/**
	 * [概 要]:ドロップダウンリストの初期化を行う。<br>
	 * [説 明]:ドロップダウンリストの初期化を行う。<br>
	 * [備 考]:
	 *
	 * @param bean
	 */ 
	public void initDropDownList(PrintListBean bean) throws Exception {
		
		// 出力状況リスト
		List<Map<String, String>> downFlgList = new ArrayList<Map<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("KEY", "0");
		map.put("VALUE", "");
		downFlgList.add(map);
		
		map = new HashMap<String, String>();
		map.put("KEY", "1");
		map.put("VALUE", "未出力");
		downFlgList.add(map);
		
		map = new HashMap<String, String>();
		map.put("KEY", "2");
		map.put("VALUE", "出力済");
		downFlgList.add(map);
		bean.setDownFlgList(downFlgList);
		
	}
	

	public PrintListBean search(PrintListBean bean) throws Exception{
		
		bean = dao.search(bean);
		return bean;
	}


	
	/**
	 * [概 要]:出荷指示CSV出力。<br>
	 * @function downloadCsv
	 * @param response
	 * @param bean
	 * @return String
	 * @throws Exception
	 */
	public PrintListBean downLoadCSV(User user, PrintListBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/** 
	 * [概要]:出荷一覧<br>
	 * [説 明]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */

	public PrintListBean showCSV(User user, PrintListBean bean) {
		// TODO Auto-generated method stub
		return bean;
	}
	
	
	
	/**
	 * 出荷指示状態
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	
	
	
	/**
	 * 出荷指示確定
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	
	public PrintListBean updateCSV(PrintListBean bean) {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("static-access")
	public String downLoad(HttpServletResponse response, PrintListBean bean) throws Exception {
		String errM = "";
		// ヘッダCSV出力
		String fileName = "";
		if (bean.CSV_HEAD_DOWN.equals(bean.getCsv_down_kbn())) {
			fileName = "OrdersList_Header";
		}
		// 明細CSV出力
		if (bean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
			fileName = "OrdersList_Detail";
		}
		// パラメータセット
		bean.setIntPageNo(1);
		bean.setIntPageSize(999999999);
		List<SQLParamType> listParams = dao.setListParams(bean, "1");

		// CSV出力
		String csvDownKbn = bean.getCsv_down_kbn();
		CsvDownloader downloader = new CsvDownloader(response, fileName, String.valueOf(bean.getLoginUser().getCoNo()), csvDownKbn, StringUtil.nvl(bean.getPattern_no(),"1"));
		if (!downloader.download("SY110.search", listParams, "LIST", true)) {
			errM = downloader.getErrorMessage();
		}
		return errM;
	}
	
	/**
	 * [概 要]:CSVデータ作成。<br>
	 * [説 明]:指定されたFileにCSVデータを作っていきます。<br>
	 * [備 考]:
	 *
	 * @param List
	 * @param header_flg
	 * @param itemCnt
	 * @param item_name
	 * @param out
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void makeCSV2(PrintListBean bean, int header_flg, int pageCount, FileOutputStream out) throws Exception {

		List<Map<String, String>> List = bean.getListCsv();
		if (List.size() > 0) {
			// ヘッダー出力判定
			if (header_flg == 1) {
				// ヘッダーデータ出力
				String title = "No,ロケーション,在庫商品コード,JANコード,在庫商品名,数量,単位\r\n";
				out.write(title.toString().getBytes("Windows-31J"));
			}

			// 切り分け分までNoを進める。
			int noCnt = bean.getIntPageSize() * (pageCount - 1);

			for (int i = 0; i < List.size(); i++) {
				StringBuilder sbText = new StringBuilder();

				// No
				sbText.append(CSVUtil.varchar(StringUtil.nvl(noCnt + i + 1), true) + ",");
				// ロケーション
				sbText.append(CSVUtil.varchar(StringUtil.nvl(List.get(i).get("LOCATION_CD")), true)).append(",");
				// 在庫商品コード
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("ZAIKO_ITEM_NO")))).append(",");
				// JANコード
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("JAN_CD")))).append(",");
				// 在庫商品名
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("ZAIKO_ITEM_NM1")))).append(",");
				// 数量
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("QTY")))).append(",");
				// 単位
				sbText.append(CSVUtil.number(StringUtil.nvl(List.get(i).get("MARK_NM")))).append("\r\n");

				out.write(String.valueOf(sbText.toString()).getBytes("Windows-31J"));
			}
		}
	}
	
	
	
	
	
}
