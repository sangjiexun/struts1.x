package jp.co.tcc.ecs.e_asproShip.SY110PrintList;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.DateUtil;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概 要]:出荷指示データ Servlet<br>
 * [説 明]:<br>
 * [備 考]:<br>
 */
public class PrintListServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;
	private static final String RETRUN_URL = "ship/SY110PrintList.jsp";

	private static int CODE = 0;
 
	// service api
	PrintListService service = new PrintListService();
	CommonService commonService = new CommonService();

	/**
	 * [概 要]:初期化<br>
	 * [説 明]:<br>
	 * [備 考]:
	 */
	public String doInit(HttpServletRequest request, HttpServletResponse response, PrintListBean bean)
			throws Exception {
		User user = (User) request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
		bean.setCoNo(Integer.toString(user.getCoNo()));
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		// 出荷指示確定日（From）
		bean.setSyukkaOkYmdFrom(df.format(new Date(d.getTime() - 7 * 24 * 60 * 60 * 1000)));
		// 出荷指示確定日（To）
		bean.setSyukkaOkYmdTo(df.format(d));
		bean.setStrFromPageId("SY110");
		// ドロップダウンリストの初期化を行う
		service.initDropDownList(bean);
		return PrintListBean.FORWARD_URL;
	}

	public String doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintListBean bean = new PrintListBean();
		setRequest2Bean(request, bean);
		setLoginUser(request, bean);
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
		String strCo_no = Integer.toString(user.getCoNo());
		// 主に番号
		bean.setCoNo(strCo_no);
		System.out.println(bean);
		// 倉庫ユーザ
		if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {
			bean.setWhCd(bean.getLoginUser().getWhCd());
		}
		// 支社、支店ユーザ
		if (Constants.USER_KBN_SISYA.equals(bean.getLoginUser().getUserAuth())
				|| Constants.USER_KBN_SITEN.equals(bean.getLoginUser().getUserAuth())) {
			bean.setSisya_cd(bean.getLoginUser().getSisyaCd());
		}

		String returnStr = null;
		if ("".equals(bean.getProcessType()) == true) {
			ManageUtils manageUtils = bean.getManageUtils(request);
			// メニューから遷移の判断
			if ("1".equals(bean.get_fromMenu())) {

				// 別画面戻るを削除
				manageUtils.clearRetrunURL();
				manageUtils.setReturnURL2Request(request);

			} else {
				manageUtils.setReturnURL2Request(request, "SY110");
			}
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doSubmit(request, response, bean);

		} else if (Constants.PAGE_SEARCH.equals(bean.getProcessType())) {

			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL(RETRUN_URL, bean.getSearchParamMap());
			returnStr = doSubmit(request, response, bean);

		} else if (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())) {
			returnStr = this.doDownLoad(request, response, bean);

		} else if (Constants.PAGE_AJAX.equals(bean.getProcessType())) {
			// Ajax連動
			doAjax(request, response, bean);
			return Constants.AJAX;
		}

		request.setAttribute("bean", bean);
		return returnStr;
	}

	// /**
	// * [概 要]:検索処理<br>
	// * [説 明]:<br>
	// * [備 考]:
	// */
	// public String doSubmit(HttpServletRequest request,HttpServletResponse
	// response,PrintListBean bean) throws Exception {
	// User user = (User)
	// request.getSession().getAttribute(Constants.SESSION_USER);
	// bean.setUserNo(StringUtil.nvl(user.getUserNo()));
	// bean.setCoNo(Integer.toString(user.getCoNo()));
	// //bean = service.getSyozokuList(bean);
	// bean = service.search(user, bean);
	// //ドロップダウンリストの初期化を行う
	// service.initDropDownList(bean);
	// bean.setStrFromPageId("SY110");
	// return PrintListBean.FORWARD_URL;
	//
	//
	//
	//
	//
	// }
	/**
	 * [概 要]検索。<br>
	 * [説 明]:検索を行う。<br>
	 * [備 考]:
	 *
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @param OD070OrdersListBean
	 *            bean
	 * @return String
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request, HttpServletResponse response, PrintListBean bean)
			throws Exception {
		/* チェック */
		String errMsg = searchValidator(bean);
		if (!"".equals(errMsg)) {
			bean.setStrErrMsg(errMsg);
			this.initDropDownList(request, bean);

			return RETRUN_URL;
		}

		// 検索処理
		service.search(null);
		bean.setSearchKbn("2");
		this.initDropDownList(request, bean);

		return RETRUN_URL;
	}

	/**
	 * [概 要]:DropDownList初期設定。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param HttpServletRequest
	 *            request
	 * @throws Exception
	 */
	public void initDropDownList(HttpServletRequest request, PrintListBean bean) throws Exception {
		// 会社NO
		String coNo = StringUtil.nvl(bean.getLoginUser().getCoNo());

		// 支社が選択されていたら支店リストを取得する
		if (!"".equals(bean.getSisya_cd())) {
			List<Map<String, String>> sitenList = CommonService
					.getSitenList(String.valueOf(bean.getLoginUser().getCoNo()), bean.getSisya_cd(), true);
			bean.setSiten_list(sitenList);
		}

		// 最終更新時間
		List<Map<String, String>> resultUpdate_hm = CommonService.getHHMM24List();
		request.setAttribute("resultUpdateHmList", resultUpdate_hm);

		// 登録者
		List<Map<String, String>> resultMakeUserList = CommonService.getUserNameList(coNo, true);
		request.setAttribute("resultMakeUserList", resultMakeUserList);

		// 処理状況
		List<Map<String, String>> resultSyoriJyoukyou = new ArrayList<Map<String, String>>();
		Map<String, String> mapSyoriJyoukyou = new HashMap<String, String>();

		mapSyoriJyoukyou.put("KEY", "");
		mapSyoriJyoukyou.put("VALUE", "");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		mapSyoriJyoukyou = new HashMap<String, String>();
		mapSyoriJyoukyou.put("KEY", "0");
		mapSyoriJyoukyou.put("VALUE", "受注未確定");
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
		mapSyoriJyoukyou.put("KEY", "7");
		mapSyoriJyoukyou.put("VALUE", "出荷実績確定");
		resultSyoriJyoukyou.add(mapSyoriJyoukyou);

		request.setAttribute("resultSyoriJyoukyou", resultSyoriJyoukyou);

		// キャンセル
		List<Map<String, String>> resultCancelFlg = new ArrayList<Map<String, String>>();
		Map<String, String> mapCancelFlg = new HashMap<String, String>();

		mapCancelFlg.put("KEY", "");
		mapCancelFlg.put("VALUE", "");
		resultCancelFlg.add(mapCancelFlg);

		mapCancelFlg = new HashMap<String, String>();
		mapCancelFlg.put("KEY", "0");
		mapCancelFlg.put("VALUE", "通常");
		resultCancelFlg.add(mapCancelFlg);

		mapCancelFlg = new HashMap<String, String>();
		mapCancelFlg.put("KEY", "1");
		mapCancelFlg.put("VALUE", "キャンセル	");
		resultCancelFlg.add(mapCancelFlg);

		request.setAttribute("resultCancelFlg", resultCancelFlg);

		// ヘッダCSV出力
		List<Map<String, String>> resultHeaderCSVList = CommonService.getPatternNameList(coNo, "4", true);
		request.setAttribute("resultHeaderCSVList", resultHeaderCSVList);

		// 明細CSV出力
		List<Map<String, String>> resultMeisaiCSVList = CommonService.getPatternNameList(coNo, "5", true);
		request.setAttribute("resultMeisaiCSVList", resultMeisaiCSVList);

		PrintListDAO dao = new PrintListDAO();
		// CSVダウンロード項目数を先に取得
		int itemHeaderCnt = dao.getItemCnt(coNo, PrintListBean.CSV_HEAD_DOWN, 0);
		if (itemHeaderCnt > 0 && resultHeaderCSVList.size() > 1) {
			bean.setHeaderCsvOutFlg("show");
		} else {
			bean.setHeaderCsvOutFlg("");
		}
		int itemListCnt = dao.getItemCnt(coNo, PrintListBean.CSV_LIST_DOWN, 0);
		if (itemListCnt > 0 && resultMeisaiCSVList.size() > 1) {
			bean.setListCsvOutFlg("show");
		} else {
			bean.setListCsvOutFlg("");
		}
	}

	/**
	 * 出荷指示データCSVデンロードレイアウト一覧
	 * 
	 * @function update
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	public String doCSVShow(HttpServletRequest request, HttpServletResponse response, PrintListBean bean)
			throws Exception {
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
		bean.setUserNo(StringUtil.nvl(user.getUserNo()));
		bean.setCoNo(Integer.toString(user.getCoNo()));
		bean = service.showCSV(user, bean);
		request.setAttribute("bean", bean);

		// begin
		updateDownloadBegin(bean);
		return PrintListBean.FORWARD_URL;

	}

	// return Constants.AJAX;

	/**
	 * 出荷指示状態・
	 * 
	 * @function update
	 * @param response
	 * @param bean
	 * @throws Exception
	 */
	public int updateDownloadBegin(PrintListBean bean) {
		PrintListBean.setCsv_down_kbn("82");
		// doDownLoad(null, null, bean);
		// bean = service.downLoadCsv(bean);
		// request.setAttribute("bean", bean);
		//
		// return PrintListBean.FORWARD_URL;
		//
		// if ("1".equals(CODE)) {
		// System.out.println("--begin-");
		// do {
		//
		// } while (condition);
		//
		// } else {
		// System.out.println("申し訳ございません");
		// }

		return CODE;
	}

	/**
	 * 出荷指示確定・
	 * 
	 * @param response
	 * @param request
	 * @function update
	 * @param response
	 * @param bean
	 * @return String
	 * @throws Exception
	 */

	// bean.setCsv_down_kbn("83");
	// bean = service.updateCSV(bean);

	/**
	 * [概 要]:検索項目チェック。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 *
	 * @param OD070OrdersListBean
	 *            bean
	 * @return String
	 * @throws Exception
	 */
	public String searchValidator(PrintListBean bean) throws Exception {
		StringBuilder sb = new StringBuilder();

		try {

			// 注文日
			CommonService.compareYmd(bean.getOrder_ymd_s(), bean.getOrder_ymd_e(), "注文日", sb);

			// 注文番号チェック
			if (!StringUtil.isNull(bean.getOrder_cd())) {
				if (!InputChecker.isAllHalf(bean.getOrder_cd())) {
					// 半角チェック
					sb.append(Properties.getMsg("HA.ERROR.003", new String[] { "注文番号" }, true));
				} else if (InputChecker.BytesCheck(bean.getOrder_cd(), 30)) {
					// 桁数チェック
					sb.append(Properties.getMsg("HA.ERROR.101", new String[] { "注文番号", "30" }, true));
				}
			}

			// 注文番号

			// 発送先CD

			// 発送先名

			// 郵便番号

			// 住所

			// 電話番号

			// 商品コード

			// 商品名

			// 注文数

			// 単位

			// 出荷数

			// 種別

			// ランク

			// NO

			// バッチNO

			// 支社コード

			// 発送区分

			// 出荷予定日
			CommonService.compareYmd(bean.getSyukka_yotei_ymd_s(), bean.getSyukka_yotei_ymd_e(), "出荷予定日", sb);

			// 配達指定日
			CommonService.compareYmd(bean.getTdk_ymd_s(), bean.getTdk_ymd_e(), "配達指定日", sb);

			// 登録日
			CommonService.compareYmd(bean.getMake_date_s(), bean.getMake_date_e(), "登録日", sb);

			// 最終更新日
			CommonService.compareYmd(bean.getUpdate_ymd_s() + bean.getUpdate_hms_s(),
					bean.getUpdate_ymd_e() + bean.getUpdate_hms_e(), "最終更新日", sb);

			if (PrintListBean.getCSV_HEAD_DOWN().equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getHeader_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[] { "ヘッダCSV出力" }, true));
					bean.setProcessType("");
				}
			}
			if (PrintListBean.getCSV_LIST_DOWN().equals(bean.getCsv_down_kbn())) {
				if ("0".equals(bean.getList_csv_out())) {
					sb.append(Properties.getMsg("HA.ERROR.001", new String[] { "明細CSV出力" }, true));
					bean.setProcessType("");
				}
			}

			/**
			 * 検索条件指定チェック 注文番号、出荷指示番号はXX桁以上の指定があればそれだけでOK それ以外の場合はいずれかの日付範囲指定必須
			 */
			boolean isSitei = false; // false：指定なし true：指定あり
			if (!InputChecker.MustCheck(bean.getOrder_cd()) && bean.getOrder_cd().length() >= 7) {
				// 注文番号は7桁以上指定があればOK
				isSitei = true;
			} else {
				// 日付範囲チェック
				boolean isStartSitei = false;
				boolean isEndSitei = false;
				ArrayList<Date> chkYmds = new ArrayList<Date>();
				ArrayList<Date> chkYmde = new ArrayList<Date>();
				// いずれかの開始日が入っていて かつ、 いずれかの終了日が入っていればOK
				if (!InputChecker.MustCheck(bean.getOrder_ymd_s())
						|| !InputChecker.MustCheck(bean.getSyukka_yotei_ymd_s())
						|| !InputChecker.MustCheck(bean.getTdk_ymd_s())
						|| !InputChecker.MustCheck(bean.getMake_date_s())
						|| !InputChecker.MustCheck(bean.getUpdate_ymd_s())) {
					// 開始日
					isStartSitei = true;
					String defYmd = "1900/01/01";
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getOrder_ymd_s(), defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_yotei_ymd_s(), defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getTdk_ymd_s(), defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getMake_date_s(), defYmd)));
					chkYmds.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getUpdate_ymd_s(), defYmd)));
				}
				if (!InputChecker.MustCheck(bean.getOrder_ymd_e())
						|| !InputChecker.MustCheck(bean.getSyukka_yotei_ymd_e())
						|| !InputChecker.MustCheck(bean.getTdk_ymd_e())
						|| !InputChecker.MustCheck(bean.getMake_date_e())
						|| !InputChecker.MustCheck(bean.getUpdate_ymd_e())) {
					// 終了日
					isEndSitei = true;
					String defYmd = "2100/12/31";
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getOrder_ymd_e(), defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getSyukka_yotei_ymd_e(), defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getTdk_ymd_e(), defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getMake_date_e(), defYmd)));
					chkYmde.add(DateUtil.parseDateNoException(StringUtil.nvl(bean.getUpdate_ymd_e(), defYmd)));
				}
				if (isStartSitei && isEndSitei) {
					isSitei = true;
					// 範囲チェック
					Date startYmd = Collections.max(chkYmds);
					Date endYmd = Collections.min(chkYmde);
					// 検索は6か月以内、CSVは1か月以内
					int intervalMonths = (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())
							|| "8".equals(bean.getProcessType())) ? 1 : 6;
					// 開始日の6か月後より終了日の指定が前にならない場合はエラー
					if (!InputChecker.DateCheck(DateUtil.formatYMD(endYmd),
							DateUtil.formatYMD(DateUtil.addMonths(startYmd, intervalMonths)), true)) {
						String msg = (Constants.PAGE_DOWNLOAD.equals(bean.getProcessType())) ? "CSV出力する場合は、" : "";
						sb.append(Properties.getMsg("ERROR.OD071.001",
								new String[] { msg, Integer.toString(intervalMonths) }, true));
					}
				}
			}

			// 指定判定 指定がない場合は 直近1か月分
			if (!isSitei) {
				bean.setDef_ymd_e(DateUtil.getSystemYMD());
				bean.setDef_ymd_s(DateUtil.formatYMD(DateUtil.addMonths(DateUtil.getSysdate(), -1)));
			}

		} catch (Exception e) {
			throw e;
		}
		return sb.toString();
	}

	/**
	 * [概 要]:CSV出力。<br>
	 * [説 明]:受注一覧CSV出力を行う。<br>
	 * [備 考]:
	 *
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @param OD070OrdersListBean
	 *            bean
	 * @return String
	 * @throws Exception
	 */
	public String doDownLoad(HttpServletRequest request, HttpServletResponse response, PrintListBean bean)
			throws Exception {
		// CSVヘッダ出力
		if (PrintListBean.CSV_HEAD_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getHeader_csv_out(), "0")));
		}
		// 明細CSV出力
		if (PrintListBean.CSV_LIST_DOWN.equals(bean.getCsv_down_kbn())) {
			bean.setPattern_no(Integer.parseInt(StringUtil.nvl(bean.getList_csv_out(), "0")));
		}

		String errM = searchValidator(bean);
		if ("".equals(errM)) {
			errM = service.downLoad(response, bean);
		}
		if (!"".equals(errM)) {
			bean.setStrErrMsg(errM);
			bean.setSearchKbn("1");
			this.initDropDownList(request, bean);
			return RETRUN_URL;
		}
		return Constants.AJAX;
	}

	/**
	 * [概 要]:AJAX<br>
	 * [説 明]:支社変更時支店プルダウン取得<br>
	 * [備 考]:
	 * 
	 * @param request
	 * @param response
	 * @param bean
	 * @return ajax
	 * @throws Exception
	 */
	public void doAjax(HttpServletRequest request, HttpServletResponse response, PrintListBean bean) throws Exception {
		response.setContentType(Constants.AJAX_CONTENTTYPE);
		StringBuffer sb = new StringBuffer();

		List<Map<String, String>> sitenList = CommonService.getSitenList(String.valueOf(bean.getLoginUser().getCoNo()),
				bean.getSisya_cd(), true);
		bean.setSiten_list(sitenList);
		if (bean.getSiten_list() != null) {
			for (Map<String, String> item : bean.getSiten_list()) {
				// SELECT 作成Scpipt
				sb.append("$('#siten_cd').append($('<option>').html('" + StringUtil.nvl(item.get("VALUE")) + "').val('"
						+ StringUtil.nvl(item.get("KEY")) + "')); ");
			}
		}

		PrintWriter pw = response.getWriter();
		pw.write(sb.toString());
		pw.close();
	}

}
