package jp.co.tcc.ecs.e_asproShip.SY160ShipList;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.common.InputChecker;
import jp.co.tcc.ecs.e_asproComm.common.Properties;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;
import jp.co.tcc.ecs.e_asproComm.framework.tag.SelectTag.Option;
import jp.co.tcc.ecsolution.framework.otherUtils.DateUtil;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概 要]:出荷一覧Servlet<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 *
 * @author Toukei Computer Company
 * @version 1.0
 * @since 1.0
 */
public class SY160ShipListServlet extends CommonServlet {

	private static final long serialVersionUID = -6272298983410752854L;
	private static final String RETRUN_JSP = "ship/SY160ShipList.jsp";
	private static final String RETRUN_URL = "/e_asproShip/SY160ShipList";
	SY160ShipListService service = new SY160ShipListService();

	public String doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		SY160ShipListBean bean = new SY160ShipListBean();
		setRequest2Bean(request, bean);
		setLoginUser(request, bean);

		// 倉庫ユーザのため
		if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {
			bean.setWh_cd(bean.getLoginUser().getWhCd());
		}
		// 支社、支店ユーザ
		if (Constants.USER_KBN_SISYA.equals(bean.getLoginUser().getUserAuth())||Constants.USER_KBN_SITEN.equals(bean.getLoginUser().getUserAuth())) {
			bean.setSisya_cd(bean.getLoginUser().getSisyaCd());
		}
		String returnStr = "";

		if ("".equals(bean.getProcessType()) == true) {
			ManageUtils manageUtils = bean.getManageUtils(request);
			// メニューから遷移の判断
			if ("1".equals(bean.get_fromMenu())) {
				// 別画面戻るを削除
				manageUtils.clearRetrunURL();
				manageUtils.setReturnURL2Request(request);
			} else {
				manageUtils.setReturnURL2Request(request, "SY160");
			}
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doSubmit(request, response, bean);

		} else if (Constants.PAGE_SEARCH.equals(bean.getProcessType())) {

			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doSubmit(request, response, bean);

		} else if (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())) {
			returnStr = this.doDownLoad(request, response, bean);

		}else if (Constants.PAGE_AJAX.equals(bean.getProcessType())) {
			//Ajax連動
			doAjax(request, response, bean);
			return Constants.AJAX;
		}

		request.setAttribute("bean", bean);
		return returnStr;
	}
	/**
	 * [概 要]:初期化<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String doInit(HttpServletRequest request, HttpServletResponse response, SY160ShipListBean bean)
			throws Exception {

		this.initDropDownList(request,bean);
		return RETRUN_JSP;
	}

	/**
	 * [概 要]:検索<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request, HttpServletResponse response, SY160ShipListBean bean)
			throws Exception {

		/* チェック */
		String errMsg = searchValidator(bean);
		if (!"".equals(errMsg)) {
			bean.setStrErrMsg(errMsg);
			this.initDropDownList(request,bean);
			return RETRUN_JSP;
		}

		// 検索処理
		service.search(bean);
		bean.setSearchKbn("2");
		this.initDropDownList(request,bean);

		return RETRUN_JSP;
	}
	/**
	 * [概 要]:DropDownList初期設定。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param request
	 * @throws Exception
	 */
	public void initDropDownList(HttpServletRequest request, SY160ShipListBean bean) throws Exception {
		// 会社NO
		String coNo = StringUtil.nvl(bean.getLoginUser().getCoNo());

		// 支社が選択されていたら支店リストを取得する
		if (!"".equals(bean.getSisya_cd())) {
			List<Map<String, String>> sitenList = CommonService.getSitenList(String.valueOf(bean.getLoginUser().getCoNo()), bean.getSisya_cd(), true);
			bean.setSiten_list(sitenList);
		}

		// 倉庫名
		List<Option> resultWhNm = CommonService.getSoukoOptionList(coNo, true);
		request.setAttribute("resultWhNm", resultWhNm);

		// 処理状況
		List<Map<String, String>> resultSyoriJyoukyou = new ArrayList<Map<String, String>>();
		Map<String, String> mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "");
		mapSyoriJyoukyou.put("VALUE", "");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "1");
		mapSyoriJyoukyou.put("VALUE", "受注確定");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "3");
		mapSyoriJyoukyou.put("VALUE", "出荷指示確定");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "5");
		mapSyoriJyoukyou.put("VALUE", "出荷データ出力");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "7");
		mapSyoriJyoukyou.put("VALUE", "出荷実績確定");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		request.setAttribute("resultSyoriJyoukyou", resultSyoriJyoukyou);

		// ヘッダCSV出力
		List<Map<String, String>> resultHeaderCSVList = CommonService.getPatternNameList(coNo, bean.CSV_HEAD_DOWN, true);
		request.setAttribute("resultHeaderCSVList", resultHeaderCSVList);

		// 明細CSV出力
		List<Map<String, String>> resultMeisaiCSVList = CommonService.getPatternNameList(coNo, bean.CSV_LIST_DOWN, true);
		request.setAttribute("resultMeisaiCSVList", resultMeisaiCSVList);

		// 問合せNoCSV出力
		List<Map<String, String>> resultToiCSVList = CommonService.getPatternNameList(coNo, bean.CSV_OKURINO_DOWN, true);
		request.setAttribute("resultToiCSVList", resultToiCSVList);

		SY160ShipListDAO dao = new SY160ShipListDAO();
		// CSVダウンロード項目数を先に取得
		int itemHeaderCnt = dao.getItemCnt(coNo, bean.CSV_HEAD_DOWN, 0);
		if (itemHeaderCnt > 0 && resultHeaderCSVList.size() > 1) {
			bean.setHeaderCsvOutFlg("show");
		} else {
			bean.setHeaderCsvOutFlg("");
		}
		int itemListCnt = dao.getItemCnt(coNo, bean.CSV_LIST_DOWN, 0);
		if (itemListCnt > 0 && resultMeisaiCSVList.size() > 1) {
			bean.setListCsvOutFlg("show");
		} else {
			bean.setListCsvOutFlg("");
		}
		int itemToiCnt = dao.getItemCnt(coNo, bean.CSV_OKURINO_DOWN, 0);
		if (itemToiCnt > 0 && resultToiCSVList.size() > 1) {
			bean.setToi_csv_out_flg("show");
		} else {
			bean.setToi_csv_out_flg("");
		}

	}

	/**
	 * [概 要]:CSV出力。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String doDownLoad(HttpServletRequest request, HttpServletResponse response, SY160ShipListBean bean) throws Exception {
		// CSVヘッダ出力
		if (bean.CSV_HEAD_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getHeader_csv_out(), "0")));
		}
		// 明細CSV出力
		if (bean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getList_csv_out(), "0")));
		}
		// 問合せNoCSV出力
		if (bean.CSV_OKURINO_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getToi_csv_out(), "0")));
		}
		// 商品別出荷数CSV出力
		if (bean.CSV_ITEM_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getItem_csv_out(), "0")));
		}

		String errM = searchValidator(bean);
		if ("".equals(errM)) {
			errM = service.downLoad(response, bean);
		}
		if (!"".equals(errM)) {
			bean.setStrErrMsg(errM);
			bean.setSearchKbn("1");
			initDropDownList(request,bean);
			return RETRUN_JSP;
		}
		return Constants.AJAX;
	}

	/**
	 * [概 要]:AJAX<br>
	 * [説 明]:支社変更時支店プルダウン取得<br>
	 * [備 考]:
	 * @param request
	 * @param response
	 * @param bean
	 * @return ajax
	 * @throws Exception
	 */
	public void doAjax(HttpServletRequest request,HttpServletResponse response, SY160ShipListBean bean) throws Exception {
		response.setContentType(Constants.AJAX_CONTENTTYPE);
		StringBuffer sb = new StringBuffer();

		List<Map<String, String>> sitenList = CommonService.getSitenList(String.valueOf(bean.getLoginUser().getCoNo()), bean.getSisya_cd(), true);
		bean.setSiten_list(sitenList);
		if (bean.getSiten_list() != null){
			for (Map<String,String> item : bean.getSiten_list()) {
				//SELECT 作成Scpipt
				sb.append("$('#siten_cd').append($('<option>').html('"+ StringUtil.nvl(item.get("VALUE")) + "').val('" + StringUtil.nvl(item.get("KEY")) + "')); ");
			}
		}

		PrintWriter pw = response.getWriter();
		pw.write(sb.toString());
		pw.close();
	}

	/**
	 * [概 要]:検索項目チェック<br>
	 * [説 明]:検索条件の必須はなし。検索条件がない場合は受注確定日の直近1か月分を表示<br>
	 * [備 考]:
	 *
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public String searchValidator(SY160ShipListBean bean) throws Exception {

		StringBuilder sb = new StringBuilder();
		try {

			// 受注確定日
			CommonService.compareYmd(bean.getSksj_ymd_s(), bean.getSksj_ymd_e(), "受注確定日", sb);
			// 注文日チェック
			CommonService.compareYmd(bean.getOrder_ymd_s(), bean.getOrder_ymd_e(), "注文日", sb);
			// 注文番号チェック
			if (!StringUtil.isNull(bean.getOrder_cd())) {
				if (!InputChecker.isAllHalf(bean.getOrder_cd())) {
					// 半角チェック
					sb.append(Properties.getMsg("HA.ERROR.003", new String[]{"注文番号"}, true));
				} else if (InputChecker.BytesCheck(bean.getOrder_cd(), 30)) {
					// 桁数チェック
					sb.append(Properties.getMsg("HA.ERROR.101", new String[]{"注文番号", "30"}, true));
				}
			}

			// 出荷予定日
			CommonService.compareYmd(bean.getSyukka_yotei_ymd_s(), bean.getSyukka_yotei_ymd_e(), "\u51fa\u8377\u4e88\u5b9a\u65e5", sb);

			// 配達指定日
			CommonService.compareYmd(bean.getTdk_ymd_s(), bean.getTdk_ymd_e(), "配達指定日", sb);

			// 指示確定チェック
			CommonService.compareYmd(bean.getSyukka_ok_ymd_s(), bean.getSyukka_ok_ymd_e(), "指示確定", sb);

			// 実績確定チェック
			CommonService.compareYmd(bean.getToiout_ymd_s(), bean.getToiout_ymd_e(), "実績確定", sb);

			if (bean.CSV_HEAD_DOWN.equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getHeader_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[]{"ヘッダCSV出力"}, true));
					bean.setProcessType("");
				}
			}
			if (bean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getList_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[]{"明細CSV出力"}, true));
					bean.setProcessType("");
				}
			}
			if (bean.CSV_OKURINO_DOWN.equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getToi_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[]{"問合せNoCSV出力"}, true));
					bean.setProcessType("");
				}
			}
			/**
			 * 検索条件指定チェック
			 * 注文番号、出荷指示番号はXX桁以上の指定があればそれだけでOK
			 * それ以外の場合はいずれかの日付範囲指定必須
			*/
			boolean isSitei = false;	//false：指定なし  true：指定あり
			if (!InputChecker.MustCheck(bean.getOrder_cd()) && bean.getOrder_cd().length() >= 7){
				//注文番号は7桁以上指定があればOK
				isSitei = true;
			}else if(!InputChecker.MustCheck(bean.getSksj_cd()) && bean.getSksj_cd().length() >= 9){
				//出荷指示番号は9桁以上指定があればOK
				isSitei = true;
			}else{
				// 日付範囲チェック
				boolean isStartSitei = false;
				boolean isEndSitei = false;
				ArrayList<Date> chkYmds = new ArrayList<Date>();
				ArrayList<Date> chkYmde = new ArrayList<Date>();
				//いずれかの開始日が入っていて かつ、 いずれかの終了日が入っていればOK
				if(!InputChecker.MustCheck(bean.getSksj_ymd_s())||!InputChecker.MustCheck(bean.getOrder_ymd_s())
					||!InputChecker.MustCheck(bean.getSyukka_yotei_ymd_s())||!InputChecker.MustCheck(bean.getTdk_ymd_s())
					||!InputChecker.MustCheck(bean.getSyukka_ok_ymd_s())||!InputChecker.MustCheck(bean.getToiout_ymd_s())){
					// 開始日
					isStartSitei = true;
					String defYmd = "1900/01/01";
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSksj_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getOrder_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_yotei_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getTdk_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_ok_ymd_s(),defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getToiout_ymd_s(),defYmd)));
				}
				if(!InputChecker.MustCheck(bean.getSksj_ymd_e())||!InputChecker.MustCheck(bean.getOrder_ymd_e())
						||!InputChecker.MustCheck(bean.getSyukka_yotei_ymd_e())||!InputChecker.MustCheck(bean.getTdk_ymd_e())
						||!InputChecker.MustCheck(bean.getSyukka_ok_ymd_e())||!InputChecker.MustCheck(bean.getToiout_ymd_e())){
					// 終了日
					isEndSitei = true;
					String defYmd = "2100/12/31";
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSksj_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getOrder_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_yotei_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getTdk_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_ok_ymd_e(),defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getToiout_ymd_e(),defYmd)));
				}
				if (isStartSitei && isEndSitei) {
					isSitei = true;
					// 範囲チェック
					Date startYmd = Collections.max(chkYmds);
					Date endYmd = Collections.min(chkYmde);
					// 検索は6か月以内、CSVは1か月以内
					int intervalMonths = (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType()))?1:6;
					// 開始日の6か月後より終了日の指定が前にならない場合はエラー
					if (!InputChecker.DateCheck(DateUtil.formatYMD(endYmd),DateUtil.formatYMD(DateUtil.addMonths(startYmd, intervalMonths)), true)) {
						String msg = (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType()))?"CSV出力する場合は、":"";
						sb.append(Properties.getMsg("ERROR.SY160.001", new String[]{msg, Integer.toString(intervalMonths)}, true));
					}
				}
			}

			// 指定判定 指定がない場合は 直近1か月分
			if (!isSitei) {
				bean.setDef_ymd_e(DateUtil.getSystemYMD());
				bean.setDef_ymd_s(DateUtil.formatYMD(DateUtil.addMonths(DateUtil.getSysdate(), -1)));
			}
			bean.setProcessType(Constants.PAGE_SEARCH); // 検索にもどしておく

		} catch (Exception e) {
			throw e;
		}
		return sb.toString();
	}
}
