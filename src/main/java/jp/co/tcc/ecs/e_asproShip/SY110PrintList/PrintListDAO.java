package jp.co.tcc.ecs.e_asproShip.SY110PrintList;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概 要]:出荷指示データ　DAO<br>
 *　[説 明]：出荷明細<br>
 * [備 考]:<br>
 */
public class PrintListDAO {
	
	/**
	 * [概要]:一覧検索<br>
	 * [説 明]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */ 
	
	
	
	
	public static void main(String[] args) {
		down();
	}
	public static void down(){
	
		java.sql.Connection conn = DBHelper.getConnection();
		try {
			CallableStatement proc = conn.prepareCall("{call SY110.check_login(?,?,?)}");
			int matches = 0 ;
			proc.setString(1,"sang");
			proc.setString(2, "123456");
			proc.setInt(3, matches);
			proc.execute();
			if (matches>0) {
				System.out.println("true");
			} else {
				System.out.println("false");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DBHelper.getConnection();
		
	
	
	}

	
	
	
	
	
	
	
	public PrintListBean search(PrintListBean bean) throws Exception{
		
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call SY110.search(?,?,?,?,?,?,?,?,?,?)}";
		// 会社番号
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCoNo()));
		// 出荷指示確定日（From）
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukkaOkYmdFrom()));
		// 出荷指示確定日（To）
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukkaOkYmdTo()));
		// 倉庫
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSouKoCd()));
		// 出力状況
		listParams.add(DBHelper.createSQLParamInteger(bean.getDownFlg()));
		
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageNo()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGECNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGENO"));
		
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		Vector<Map<String, String>> list = (Vector<Map<String,String>>)result.get("LIST");
		String strPageCnt = (String)result.get("PAGECNT");
		String strPageNo = (String)result.get("PAGENO");
		int pageCnt = 0;
		int pageNo = 0;
		if (!StringUtil.isNull(strPageCnt)){
			pageCnt = Integer.parseInt(strPageCnt);
		}
		if (!StringUtil.isNull(strPageNo)){
			pageNo = Integer.parseInt(strPageNo);
		}

		bean.setList(list);
		bean.setIntRecordCount(pageCnt);
		bean.setIntPageCount(pageNo);

		return bean;
	}

	
	/**
	 * 出荷指示状態 一覧
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	//注文番号

	//発送先CD
	
	//発送先名
	
	//郵便番号
	
	//住所
	
	//電話番号
	
	//商品コード
	
	//商品名
	
	//注文数
	
	//単位
	
	//出荷数
	
	//種別
	
	//ランク
	
	//NO
	
	//バッチNO
	
	//支社コード
	
	//発送区分
	
	
	
	/**
	 * 出荷指示確定一覧
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	//注文番号
	
	//発送先CD
	
	//発送先名
	
	//郵便番号
	
	//住所
		
	//電話番号
	
	//商品コード
	
	//商品名
	
	//注文数
	
	//単位
	
	//出荷数
	
	//種別
	
	//ランク
	
	//NO
	
	//バッチNO
	
	//支社コード
	
	//発送区分
	
	
	
	
	
	
	/**
	 * 出荷指示データCSVデンロードレイアウト一覧
	 * @function update
	 * @param  response
	 * @param  bean
	 * @return String
	 * @throws Exception
	 */
	//注文番号
	
	
	//発送先CD
	
	//発送先名
	
	//郵便番号
	
	//住所
	
	
	//電話番号
	
	//商品コード
	
	//商品名
	
	//注文数
	
	//単位
	
	//出荷数
	
	//種別
	
	//ランク
	
	//NO
	
	//バッチNO
	
	//支社コード
	
	//発送区分
	
	
	
	
	
	/**
	 * [概 要]:CSVダウンロード項目取得<br>
	 * [説 明]:CSVレイアウトマスタからダウンロード項目を取得<br>
	 * [備 考]:
	 * @param NyukaListEntryBean
	 * @return HashMap<String, Vector<Map<String, String>>>
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap<String, Vector<Map<String, String>>> getCsvItem(PrintListBean bean) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCoNo()));		//会社No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSearchKbn()));		//ダウンロード区分(入荷:40)
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("CNT"));
		String strCallProc = CommonService.createCallableStatement("SY110.getCsvItem", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		String StrCnt = (String)result.get("CNT");
		int itemCnt = 0;
		if (!StringUtil.isNull(StrCnt)){
			itemCnt = Integer.parseInt(StrCnt);
		}
		bean.setItemCount(itemCnt);
		return result;
	}
	/**
	 * [概 要]:CSVダウンロード ヘッダー有無取得<br>
	 * [説 明]:CSVレイアウトのヘッダーフラグを取得<br>
	 * [備 考]:
	 * @param NyukaListEntryBean
	 * @return int : 0=無／1=有
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public int getHederFlg(PrintListBean bean) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCoNo()));		//会社No
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDownKbn()));		//ダウンロード区分(入荷:40)
		listParams.add(DBHelper.createSQLOutParamInteger("FLG"));
		String strCallProc = CommonService.createCallableStatement("SY110.getHederFlg", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		int flg = Integer.parseInt((String)result.get("FLG"));
		return flg;
	}
	/**
	 * [概 要]:出荷指示データ CSV<br>
	 * [説 明]:<br>
	 * [備 考]:
	 * @param PrintListBean
	 * @return PrintListBean
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PrintListBean download(PrintListBean bean,int currentPageNo) throws Exception{
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCoNo()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuNm()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuAddr()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuTel()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisyaCd()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSitenCd()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyakuRankType()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTaikaiFlg()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMakeDateFrom()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMakeDateTo()));
		//2014/03/19 ariga 削除済みフラグ追加のため
	//	listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsvOutFlg()));
	//	listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsvDownKbn()));
		listParams.add(DBHelper.createSQLParamInteger(bean.getPattern_no()));
//		if(bean.getScKbn() == null){
//			bean.setScKbn(new String[]{"-1"});
//		}
//		listParams.add(DBHelper.createSQLParamVarcharArray(bean.getScKbn()));
//		listParams.add(DBHelper.createSQLParamVarchar2(bean.getScKbnType()));
     	listParams.add(DBHelper.createSQLParamInteger(currentPageNo));
     	listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("TOTALCNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("TOTALPAGENO"));
		String strCallProc = CommonService.createCallableStatement("SY110.download", listParams.size());

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		List<Map<String, String>> list = (List<Map<String,String>>)result.get("LIST");
		String strTotalPageNo = (String)result.get("TOTALPAGENO");
		int totalPageNo = 0;
		if (!StringUtil.isNull(strTotalPageNo)){
			totalPageNo = Integer.parseInt(strTotalPageNo);
		}
		bean.setList(list);
		bean.setIntPageNo(totalPageNo);
		return bean;
	}


	public int getItemCnt(String coNo, String cSV_LIST_DOWN, int i) {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * [概 要]:Beanの内容をlistにセット<br>
	 * [説 明]:search,download共通部分をセット<br>
	 * [備 考]:
	 * @param bean
	 * @param currentPageNo
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public List<SQLParamType> setListParams(PrintListBean bean, String s) throws Exception {

		List<SQLParamType> listParams = new ArrayList<SQLParamType>();
		// 会社NO
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		// デフォルト指定日from
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDef_ymd_s()));
		// デフォルト指定日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDef_ymd_e()));
		// 注文日From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_s()));
		// 注文日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_e()));
		// 注文番号
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_cd()));
		// 支社CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisya_cd()));
		// 支店CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSiten_cd()));
		// 受付店CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_cd()));
		// 受付店名
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_nm()));
		// 受付店住所
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_add()));
		// 受付店電話
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_tel()));
		// 引渡店CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_cd()));
		// 引渡店名
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_nm()));
		// 引渡店住所
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_addr()));
		// 倉庫CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWhCd()));
		// 出荷予定日From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_s()));
		// 出荷予定日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_e()));
		// 配達指定日From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_s()));
		// 配達指定日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_e()));
		// 処理状況
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyori_jyoukyou()));
		// 登録日From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMake_date_s()));
		// 登録日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMake_date_e()));
		// 登録者
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getMake_user()));
		// 商品CDFrom
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_s()));
		// 商品CDTo
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_e()));
		// 商品名
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_nm1()));
		// キャンセルラグ
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCancel_flg()));
		// 出荷区分
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getShipKbn()));
		// 個別／一斉出荷
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrderKbn()));
		// 過去検索フラグ
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getPast_flg()));

		// CSV出力区分
		listParams.add(DBHelper.createSQLParamVarchar2(s));
		// パターンNO
		listParams.add(DBHelper.createSQLParamInteger(bean.getPattern_no()));
		// CSVダウンロード区分
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsv_down_kbn()));
		// アップロードNO
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpload_no()));
		// 最終更新日Start
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpdate_ymd_s()));
		// 最終更新日End
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpdate_ymd_e()));
		// 最終更新時間Start
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpdate_hms_s()));
		// 最終更新時間Start
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpdate_hms_e()));

		// ページNo
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageNo()));
		// ページサイズ
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));

		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("TOTALCNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("TOTALPAGENO"));

		return listParams;
	}
	
	
	
	


}
