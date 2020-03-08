package jp.co.tcc.ecs.e_asproShip.SY160ShipList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.PageFeedService;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.exception.FrameworkException;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概 要]:出荷一覧DAO<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */
public class SY160ShipListDAO {
	/**
	 * [概 要]:出荷一覧取得。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param SY160ShipListBean
	 * @param String
	 * @return SY160ShipListBean
	 * @throws Exception
	 */
	public SY160ShipListBean search(SY160ShipListBean bean, String s) throws Exception {

		List<SQLParamType> listParams = setListParams(bean, "0");

		PageFeedService pageFeedService = new PageFeedService();
		pageFeedService.search("SY160.search", listParams, "LIST");
		bean.setIntPageCount(pageFeedService.getPageCnt());
		bean.setIntRecordCount(pageFeedService.getRecordCount());
		bean.setList(pageFeedService.getList());

		return bean;
	}

	/**
	 * [概 要]:CSV出力用 CSV項目数取得<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param co_no
	 * @param csv_down_kbn
	 * @param pattern_no
	 * @return
	 * @throws FrameworkException
	 * @throws SQLException
	 */
	public int getItemCnt(String co_no, String csv_down_kbn, int pattern_no) throws FrameworkException, SQLException {
		ArrayList<Object> listParams = new ArrayList<Object>();
		listParams.add(DBHelper.createSQLParamVarchar2(co_no));
		listParams.add(DBHelper.createSQLParamVarchar2(csv_down_kbn));
		listParams.add(DBHelper.createSQLParamInteger(pattern_no));
		listParams.add(DBHelper.createSQLOutParamInteger("OUT_CNT"));

		String strCallProc = CommonService.createCallableStatement("COMMON.getItemCnt", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		String item_cnt = StringUtil.nvl(result.get("OUT_CNT"));

		return Integer.parseInt(item_cnt);
	}

	/**
	 * [概 要]:Beanの内容をlistにセット<br>
	 * [説 明]:search、download共通部分をセット<br>
	 * [備 考]:
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<SQLParamType> setListParams(SY160ShipListBean bean, String s) throws Exception {
		List<SQLParamType> listParams = new ArrayList<SQLParamType>();
		// 会社NO
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		// デフォルト指定日from
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDef_ymd_s()));
		// デフォルト指定日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getDef_ymd_e()));
		// 出荷予定日From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_s()));
		// 出荷予定日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_e()));
		// 注文日From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_s()));
		// 注文日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_e()));
		// 注文番号
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_cd()));
		// 出荷指示番号
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSksj_cd()));
		// 支社CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisya_cd()));
		// 支店CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSiten_cd()));
		// 受付店CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_cd()));
		// 受付店名
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_nm()));
		// 引渡店CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_cd()));
		// 引渡店名
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_nm()));
		// 引渡店住所
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_addr()));
		// 配達指定日From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_s()));
		// 配達指定日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_e()));
		// 倉庫CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWh_cd()));
		// 問合せNo From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOkurino_s()));
		// 問合せNo To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOkurino_e()));
		// 指示確定 From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_ok_ymd_s()));
		// 指示確定 To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_ok_ymd_e()));
		// 実績確定 From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getToiout_ymd_s()));
		// 実績確定 To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getToiout_ymd_e()));
		// 処理状況
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyori_jyoukyou()));
		// 商品CDFrom
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_s()));
		// 商品CDTo
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_e()));
		// 商品名
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_nm1()));
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
		// ダウンロード区分
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getCsv_down_kbn()));
		// アップロードNO
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpload_no()));
		// 出荷指示確定ID
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_id()));
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
