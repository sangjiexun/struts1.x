package jp.co.tcc.ecs.e_asproLogin.CC040HanroList;
/**
 * [概 要]:販路詳細Bean<br>
 * [説 明]:<br>
 * [備 考]:<br>
 * 著作権: Copyright (c) 2019<br>
 */

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class HanroListBean extends BaseBean{
	/** 遷移先URL */
	public static final String FORWARD_URL = "search/CC040HanroList.jsp";
	/** Servlet URL */
	public static final String ACTION_URL = "e_asproMaster/CC040HanroList";
			
	// 処理区分
	private String processType = "";
	// エラーメッセージ
	private String strErrMsg = "";
	
	// 商品コード
	private String itemNo = "";
	
	//一覧検索結果
	private List<Map<String, String>> dataList = null;
	
	//getter setter
	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public List<Map<String, String>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, String>> dataList) {
		this.dataList = dataList;
	}

	
}
