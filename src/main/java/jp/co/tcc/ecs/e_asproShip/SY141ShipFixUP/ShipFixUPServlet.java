package jp.co.tcc.ecs.e_asproShip.SY141ShipFixUP;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;
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

public class ShipFixUPServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;
	public static final String RETURN_JSP = "ship/SY141ShipFixUP.jsp";
	public static final String RETURN_URL = "/e_asproShip/SY141ShipFixUP";
	public static final String FORWARD_URL = "e_asproShip/SY142ShipFixUPTemp";
	ShipFixUPService service = new ShipFixUPService();

	public String doAction(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String returnStr = "";
		ShipFixUPBean bean = new ShipFixUPBean();
		setLoginUser(request, bean);
		ManageUtils manageUtils = bean.getManageUtils(request);

		//リクエスト内容をBEANへ格納　※アップロード時はMultiPartの処理利用。
		String contentType = request.getContentType();
		if (contentType != null && contentType.toLowerCase().startsWith("multipart/form-data")){
			setMultipartRequest2Bean(request, bean);
		}else{
			setRequest2Bean(request, bean);
		}
		//倉庫ユーザ
		if (Constants.USER_KBN_SOUKO.equals(bean.getLoginUser().getUserAuth())) {
			bean.setWhCd(bean.getLoginUser().getWhCd());
		}

		// キャンセルから戻った時
		if ("1".equals(bean.getCancelKbn())) {
			// ワーク削除
			service.deleteWork(bean.getUuid());
		}

		//初期化
		if(StringUtil.isNull(bean.getProcessType())){
			manageUtils.addListReturnURL(RETURN_URL, bean.getSearchParamMap());
			returnStr = doInit(bean);
		// 登録するボタン押下（アップロード処理＋ワークINSERT＋エラーチェック）
		} else if (Constants.PAGE_UPLOAD.equals(bean.getProcessType())) {
			manageUtils.addListReturnURL(RETURN_URL, bean.getSearchParamMap());
			returnStr = doUpload(bean);
		}

		initDropDownList(bean);

		request.setAttribute("bean", bean);
		return returnStr;
	}

	/**
	 * [概 要]:DropDownList初期設定。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 * @param ShipFixUPBean
	 * @throws Exception
	 */
	public void initDropDownList(ShipFixUPBean bean) throws Exception {
		//会社NO
		String coNo = String.valueOf(bean.getLoginUser().getCoNo());
		//パターン選択
		bean.setPatternNoList(CommonService.getPatternNoList(coNo, ShipFixUPBean.CSV_UP_KBN, true));
		//該当のパターンが1件しかない場合はそのパターンNOを初期表示する。
		if (bean.getPatternNoList().size() == 2) {
			bean.setPatternNo(bean.getPatternNoList().get(1).get("KEY"));
		}
	}

	/**
	 * [概 要]出荷実績CSV登録 初期化。<br>
	 * [説 明]:メニューから初期化を行う。<br>
	 * [備 考]:
	 * @param ShipFixUPBean
	 * @return String
	 * @throws Exception
	*/
	public String doInit(ShipFixUPBean bean) throws Exception {

		bean.setSearchKbn("2");
		bean = service.search(bean);

		return RETURN_JSP;
	}

	/**
	 * [概 要]出荷実績CSV登録 アップロード。<br>
	 * [説 明]:アップロードを行う。<br>
	 * [備 考]:
	 * @param ShipFixUPBean
	 * @return String
	 * @throws Exception
	*/
	public String doUpload(ShipFixUPBean bean) throws Exception {
		// CSVファイルパス
		if (bean.getCsvFile() != null) {
			bean.setCsvFilePath(fileUpload(bean.getCsvFile()));
		}
		//エラーチェック
		String strErrMsg = service.validator(bean);
		if (!StringUtil.isNull(strErrMsg)) {
			bean.setStrErrMsg(strErrMsg);
			doInit(bean);
			return RETURN_JSP;
		}
		// SQLでエラーチェック
		service.updateW_EA052(bean);

		//CSVファイル名称（パスを抜く）
		int csvLastIndex = bean.getCsvFile().getName().lastIndexOf("\\");
		String fileNm = bean.getCsvFile().getName().substring(csvLastIndex+1);

		StringBuilder url = new StringBuilder();
		url.append(FORWARD_URL);
		url.append("?");
		url.append("uuid=");
		url.append(bean.getUuid());
		url.append("&csvupKbn=");
		url.append(ShipFixUPBean.CSV_UP_KBN);
		url.append("&whCd=");
		url.append(bean.getWhCd());
		url.append("&patternNo=");
		url.append(bean.getPatternNo());
		url.append("&uploadNo=");
		url.append(bean.getUploadNo());
		url.append("&fileNm=");
		url.append(URLEncoder.encode(fileNm, "SHIFT_JIS"));
		// 確認画面遷移
		return CommonServlet.setRedirectUrl(url.toString());
	}

}