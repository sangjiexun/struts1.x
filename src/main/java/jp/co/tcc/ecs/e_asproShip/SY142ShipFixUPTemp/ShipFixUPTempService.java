package jp.co.tcc.ecs.e_asproShip.SY142ShipFixUPTemp;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.csv.AbsCSVClass;
import jp.co.tcc.ecs.e_asproComm.common.csv.CsvErrDownloader;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;

public class ShipFixUPTempService {

	/**
	 * [概 要]:CSV登録（確認） データ取得<br>
	 * [説 明]:ワークテーブルからアップロード＋エラーチェックした結果を取得<br>
	 * [備 考]:<br>
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
	 * [概 要]:CSV登録（確認） ヘッダー項目取得<br>
	 * [説 明]:一覧表示用のヘッダー項目を取得
	 * [備 考]:
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
	 * [概 要]:CSV登録（確認） 登録<br>
	 * [説 明]:ワークテーブルから本テーブルに更新<br>
	 * [備 考]:<br>
	 * @param bean ShipFixUPTempBean
	 * @return
	 * @throws Exception
	*/
	public void update(ShipFixUPTempBean bean) throws Exception {
		ShipFixUPTempDAO dao = new ShipFixUPTempDAO();
		dao.update(bean);
	}
	/**
	 * [概 要]:CSV登録（確認） CSV出力<br>
	 * [説 明]:エラーデータをCSV出力する<br>
	 * [備 考]:<br>
	 *
	 * @param HttpServletResponse response
	 * @param ShipFixUPTempBean bean
	 * @return String
	 * @throws Exception
	 */
	public String download(HttpServletResponse response, ShipFixUPTempBean bean) throws Exception {
		String fileName = "Error_ShipFixUp";
		//エラーありで検索している状態にする
		bean.setSearchStatus("-1");
		// パラメータセット
		bean.setIntPageNo(1);
		bean.setIntPageSize(999999999);

		ShipFixUPTempDAO dao = new ShipFixUPTempDAO();
		List<SQLParamType> parameterList = dao.setListParams(bean);

		CsvErrDownloader downloader = new CsvErrDownloader(response, fileName, String.valueOf(bean.getLoginUser().getCoNo()), bean.getCsvupKbn(), bean.getPatternNo());
		//実行プロシージャは検索と一緒
		if (!downloader.download("SY142.search", parameterList, "LIST", true)) {
			return downloader.getErrorMessage();
		}
		return "";
	}

}
