package jp.co.tcc.ecs.e_asproShip.SY141ShipFixUP;

import jp.co.tcc.ecs.e_asproComm.common.InputChecker;
import jp.co.tcc.ecs.e_asproComm.common.Properties;
import jp.co.tcc.ecs.e_asproComm.common.csv.CommonCSVChk;
import jp.co.tcc.ecs.e_asproComm.common.csv.CsvChkRstBean;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概 要]:出荷実績CSV登録<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */

public class ShipFixUPService{
	ShipFixUPDAO dao = new ShipFixUPDAO();

	/**
	 * [概 要]:出荷実績CSV登録。<br>
	 * [説 明]:CSV登録した履歴データを検索する。<br>
	 * [備 考]:<br>
	 * @param bean ShipFixUPBean
	 * @return bean ShipFixUPBean
	 * @throws Exception
	*/
	public ShipFixUPBean search(ShipFixUPBean bean) throws Exception {
		bean = dao.search(bean);
		return bean;
	}

	/**
	 * [概 要]:エラーチェック。<br>
	 * [説 明]:<br>
	 * [備 考]:<br>
	 * @param ShipFixUPBean
	 * @return String
	 * @throws Exception
	*/
	public String validator(ShipFixUPBean bean) throws Exception{
		StringBuffer sb = new StringBuffer();

		// パターン選択 必須チェック
		if (InputChecker.MustCheck(bean.getPatternNo())) {
			sb.append(Properties.getMsg("ERROR.HA.001", new String[] {"パターン選択"}, true));
		}

		// CSVファイルの必須チェック、拡張子チェックを行なう（CSV形式のみ許可→第2引数＝1）
		sb.append(CommonCSVChk.checkCsvOutside(bean.getCsvFile(), 1));

		// 項目数、レイアウトチェック
		sb.append(CommonCSVChk.checkCsvInside(bean.getCsvFilePath(), ShipFixUPBean.CSV_UP_KBN, bean.getPatternNo(), bean.getLoginUser(), true));

		return sb.toString();
	}

	/**
	 * [概 要]:登録ボタンを押下により、取込ワークを更新<br>
	 * [説 明]:<br>
	 * [備 考]:<br>
	 * @param ShipFixUPBean
	 * @throws Exception
	 */
	public void updateW_EA052(ShipFixUPBean bean) throws Exception {

		// CSVデータ登録の共通呼び出し
		CsvChkRstBean rstBean = CommonCSVChk.insertWork(bean.getCsvFilePath(), ShipFixUPBean.CSV_UP_KBN, bean.getPatternNo(), bean.getLoginUser(), true);
		bean.setUuid(rstBean.getUuid());
		bean.setUploadNo(StringUtil.nvl(rstBean.getUploadNo()));

		dao.updateW_EA052(bean);
	}

	/**
	 * [概 要]:CSVワーク削除<br>
	 * [説 明]:<br>
	 * @param String uuid
	 * @throws Exception
	 */
	public void deleteWork(String uuid) throws Exception {
		dao.deleteWork(uuid);
	}

}

