package jp.co.tcc.ecs.e_asproLogin.CC900PostalCheck;
import java.util.Map;
import java.util.Vector;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;

public class PostalCheckBean extends BaseBean{
	// “o˜^‰æ–Êˆê——ƒf[ƒ^ƒŠƒXƒg
	private Vector<Map<String, String>> list = null;
	
	/** ZŠ */
	private String addr = "";
	
	private String trans_co_kbn = "";
	
	private String tdk_zip1 = "";
	private String tdk_zip2 = "";
	
	private String processType = "";

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Vector<Map<String, String>> getList() {
		return list;
	}

	public void setList(Vector<Map<String, String>> list) {
		this.list = list;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getTdk_zip1() {
		return tdk_zip1;
	}

	public void setTdk_zip1(String tdk_zip1) {
		this.tdk_zip1 = tdk_zip1;
	}

	public String getTdk_zip2() {
		return tdk_zip2;
	}

	public void setTdk_zip2(String tdk_zip2) {
		this.tdk_zip2 = tdk_zip2;
	}

	public String getTrans_co_kbn() {
		return trans_co_kbn;
	}

	public void setTrans_co_kbn(String trans_co_kbn) {
		this.trans_co_kbn = trans_co_kbn;
	}

}
