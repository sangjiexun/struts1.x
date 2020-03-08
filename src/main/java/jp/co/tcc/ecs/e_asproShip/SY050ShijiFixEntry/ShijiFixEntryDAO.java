package jp.co.tcc.ecs.e_asproShip.SY050ShijiFixEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class ShijiFixEntryDAO {

	/** csv用 */
	final String SY050_CSV_DOWNLOAD = "SY050.csvDownLoad";
	/** 結果取得用文字列 */
	final String RESULT = "result";

	/**
	 * [概 要]:Beanの内容をlistにセット<br>
	 * [説 明]:search,download共通部分をセット<br>
	 * [備 考]:
	 * @param ShijiFixEntryBean bean
	 * @return List
	 * @throws Exception
	 */
	public List<SQLParamType> setListParams(ShijiFixEntryBean bean) throws Exception {

		List<SQLParamType> listParams = new ArrayList<SQLParamType>();
		// 会社NO
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		//出荷予定日From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_s()));
		//出荷予定日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSyukka_yotei_ymd_e()));
		//注文番号
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_cd()));
		//出荷番号
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSksj_cd()));
		//アップロードNO
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getUpload_no()));
		//支社CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSisya_cd()));
		//支店CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getSiten_cd()));
		//受付店CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_cd()));
		//受付店名
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getKokyaku_nm()));
		//引渡店CD
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_cd()));
		//倉庫
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getWh_cd()));
		//注文日From
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_s()));
		//注文日To
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrder_ymd_e()));
		//配達指定日
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_s()));
		//配達指定日
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getTdk_ymd_e()));
		//商品CDFrom
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_s()));
		//商品CDTo
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_cd1_e()));
		//商品名
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getItem_nm1()));
		//出荷区分
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getShipKbn()));
		//個別／一斉出荷
		listParams.add(DBHelper.createSQLParamVarchar2(bean.getOrderKbn()));

		return listParams;
	}

	/**
	 * [概 要]:出荷指示確定取得。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param ShijiFixEntryBean bean
	 * @return ShijiFixEntryBean
	 * @throws Exception
	 */
	public ShijiFixEntryBean search(ShijiFixEntryBean bean) throws Exception {

		// 検索項目は共通の処理でセット
		List<SQLParamType> listParams = setListParams(bean);
		// ページNo
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageNo()));
		// ページサイズ
		listParams.add(DBHelper.createSQLParamInteger(bean.getIntPageSize()));

		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGECNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("PAGENO"));

		String strCallProc = CommonService.createCallableStatement("SY050.search", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc,
				listParams);

		List<Map<String, String>> list = (List<Map<String, String>>) result.get("LIST");
		String strPageCnt = (String) result.get("PAGECNT");
		String strPageNo = (String) result.get("PAGENO");

		int pageCnt = 0;
		int pageNo = 0;
		if (!StringUtil.isNull(strPageCnt)) {
			pageCnt = Integer.parseInt(strPageCnt);
		}
		if (!StringUtil.isNull(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}

		bean.setList(list);
		bean.setIntRecordCount(pageCnt);
		bean.setIntPageCount(pageNo);
		bean.setIntRecordCountExcl(pageCnt);

		return bean;
	}

	/**
	 * [概 要]:選択確定。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param ShijiFixEntryBean bean
	 * @return int
	 * @throws Exception
	 */
	public int kobetu(ShijiFixEntryBean bean) throws Exception {
		String seq_no[] = new String[bean.getSeq_no().length];
		for(int i=0;i<bean.getSeq_no().length;i++){
			seq_no[i] = bean.getSeq_no()[i].split(",")[0];
		}
		ArrayList<Object> listParams = new ArrayList<Object>();
		//会社NO
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getCoNo()));
		listParams.add(DBHelper.createSQLParamVarcharArray(seq_no));
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getUserNo()));
		listParams.add(DBHelper.createSQLOutParamInteger("ERR_FLG"));

		String strCallProc = CommonService.createCallableStatement("SY050.update_sksj_kobetu", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);

		return Integer.parseInt((String)result.get("ERR_FLG"));
	}

	/**
	 * [概 要]:一括確定。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param ShijiFixEntryBean bean
	 * @return int
	 * @throws Exception
	 */
	public int ikkatu(ShijiFixEntryBean bean) throws Exception {

		// 検索項目は共通の処理でセット
		List<SQLParamType> listParams = setListParams(bean);
		// ログインユーザ
		listParams.add(DBHelper.createSQLParamInteger(bean.getLoginUser().getUserNo()));
		listParams.add(DBHelper.createSQLOutParamInteger("EXCL"));
		listParams.add(DBHelper.createSQLOutParamInteger("ERR_FLG"));

		String strCallProc = CommonService.createCallableStatement("SY050.update_sksj_ikkatu", listParams.size());
		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);

		int errFlg = Integer.parseInt((String)result.get("ERR_FLG"));

		if(errFlg > 0) {
			return -1;
		} else {
			return Integer.valueOf((String)result.get("EXCL"));
		}
	}

	public boolean checkExcl(ShijiFixEntryBean bean) throws Exception{
		String tableName="F_EA021_SHEADER";
    	String tableName2="F_EA025_SJOUTAI";
		String co_no="CO_NO";
		String seq_no="SEQ_NO";
		boolean rs=true;
		boolean rsReturn=true;

		List<String> list = new ArrayList<String>();
		String newArr[];
		for (String strSeqNo : bean.getSeq_no()) {
			list.add(strSeqNo);
		}

		for (int i=0;i<bean.getSeq_no().length;i++){
			String strTemp = bean.getSeq_no()[i];
			String arrTemp[] = strTemp.split(",");

			ArrayList <String> tableKeyColumns=new  ArrayList <String> ();
			tableKeyColumns.add(0, co_no);
			tableKeyColumns.add(1, seq_no);

			List<SQLParamType> exclcntParams = new ArrayList<SQLParamType>();
			exclcntParams.add(DBHelper.createSQLParamVarchar2("0"));
			exclcntParams.add(DBHelper.createSQLParamVarchar2(arrTemp[0]));
			rs=DBHelper.twoFaseCommitCheck(tableName,Integer.parseInt(arrTemp[1]),tableKeyColumns, exclcntParams);

			if(rs==true ){
				rs=DBHelper.twoFaseCommitCheck(tableName2,Integer.parseInt(arrTemp[2]),tableKeyColumns, exclcntParams);
			}

			if (rs == false){
				list.remove(i);
				rsReturn = false;
			}
		}
		if (rsReturn==false){
			newArr = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				newArr[i] = list.get(i);
			}
			bean.setSeq_no(newArr);
		}
		return rsReturn;
	}
}
