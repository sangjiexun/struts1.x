package jp.co.tcc.ecs.e_asproShip.SY010ShijiEntry;

import java.util.List;
import java.util.Map;

import jp.co.tcc.ecs.e_asproComm.common.BaseBean;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class SY010ShijiEntryBean extends BaseBean {
	/** o‰×w¦SEQ_NO   */
	private String seq_no = "";

	/** ˆ—‹æ•ª */
	private String processType = "";

	/** ’•¶CD */
	private String order_cd = "";
	/** ’•¶“ú•ª */
	private String order_ymd_hms = "";
	/** xĞ */
	private String sisya_name = "";
	/** ‘qŒÉ */
	private String wh_nm = "";
	/** o‰×‹æ•ª */
	private String ship_kbn_nm = "";
	/** ˆ—ó‹µ */
	private String syoriKbnNm = "";

	/** ó•t“XCD */
	private String kokyaku_cd = "";
	/** ó•t“X–¼‚P */
	private String kokyaku_nm1 = "";
	/** ó•t“X–¼‚Q */
	private String kokyaku_nm2 = "";
	/** ó•t“XƒJƒi‚P */
	private String kokyaku_kana1 = "";
	/** ó•t“XƒJƒi‚Q */
	private String kokyaku_kana2 = "";
	/** ó•t“XE—X•Ö”Ô† */
	private String kokyaku_zip = "";
	/** ó•t“XEZŠ‚P */
	private String kokyaku_addr1 = "";
	/** ó•t“XEZŠ‚Q */
	private String kokyaku_addr2 = "";
	/** ó•t“XEZŠ‚R */
	private String kokyaku_addr3 = "";
	/** ó•t“XE“d˜b”Ô†‚P */
	private String kokyaku_tel1 = "";
	/** ó•t“XE“d˜b”Ô†‚Q */
	private String kokyaku_tel2 = "";
	/** ó•t“XEFAX */
	private String kokyaku_fax = "";
	/** ó•t“XEƒ[ƒ‹‚P */
	private String kokyaku_mail1 = "";
	/** ó•t“XEƒ[ƒ‹‚Q */
	private String kokyaku_mail2 = "";
	/** ó•t“XEƒ[ƒ‹‚R */
	private String kokyaku_mail3 = "";
	/** ˆø“n“XCD */
	private String tdk_cd = "";
	/** ˆø“n“X–¼‚P */
	private String tdk_nm1 = "";
	/** ˆø“n“X–¼‚Q */
	private String tdk_nm2 = "";
	/** ˆø“n“XƒJƒi‚P */
	private String tdk_kana1 = "";
	/** ˆø“n“XƒJƒi‚Q */
	private String tdk_kana2 = "";
	/** ˆø“n“XE—X•Ö”Ô† */
	private String tdk_zip = "";
	/** ˆø“n“XEZŠ‚P */
	private String tdk_addr1 = "";
	/** ˆø“n“XEZŠ‚Q */
	private String tdk_addr2 = "";
	/** ˆø“n“XEZŠ‚R */
	private String tdk_addr3 = "";
	/** ˆø“n“XE“d˜b”Ô†‚P */
	private String tdk_tel1 = "";
	/** ˆø“n“XE“d˜b”Ô†‚Q */
	private String tdk_tel2 = "";

	/** ”z’Bw’è“ú */
	private String tdk_ymd = "";
	/** ‚¨“Í‚¯ŠÔ‘Ñ‹æ•ªNM */

	private String hstm_kbn_nm = "";
	/** o‰×w¦”Ô†    */
	private String sksj_cd = "";
	/** o‰×—\’è“ú  */
	private String syukka_yotei_ymd = "";
	/** ŒÂŒû   */
	private String kosu = "";
	/** –â‡‚¹NO   */
	private String okurino = "";
	/** ì‹Æ“Á‹L   */
	private String order_biko = "";

	/** “o˜^“ú */
	private String make_date = "";
	/** XV“ú */
	private String rep_date = "";
	/** XVÒ–¼ */
	private String rep_user_nm = "";

	/** ¤•i¬Œv”—Ê */
	private String qty_cnt = "";
	/** ¿‹‹àŠziÅ”²‚«j */
	private String seikyuu_kin = "";
	/** Á”ïÅ */
	private String seikyuu_zei_kin = "";
	/** ¿‹‹àŠziÅ‚İj */
	private String seikyuu_zei_k_kin = "";

	/** Head list */
	private List<Map<String, String>> lstHead = null;
	/** Detail list */
	private List<Map<String, String>> lstDetail = null;

	public String getOrder_cd() {
		return order_cd;
	}
	public void setOrder_cd(String order_cd) {
		this.order_cd = order_cd;
	}
	public String getSisya_name() {
		return sisya_name;
	}
	public void setSisya_name(String sisya_name) {
		this.sisya_name = sisya_name;
	}
	public String getWh_nm() {
		return wh_nm;
	}
	public void setWh_nm(String wh_nm) {
		this.wh_nm = wh_nm;
	}
	public String getShip_kbn_nm() {
		return ship_kbn_nm;
	}
	public void setShip_kbn_nm(String ship_kbn_nm) {
		this.ship_kbn_nm = ship_kbn_nm;
	}
	public String getKokyaku_cd() {
		return kokyaku_cd;
	}
	public void setKokyaku_cd(String kokyaku_cd) {
		this.kokyaku_cd = kokyaku_cd;
	}
	public String getKokyaku_nm1() {
		return kokyaku_nm1;
	}
	public void setKokyaku_nm1(String kokyaku_nm1) {
		this.kokyaku_nm1 = kokyaku_nm1;
	}
	public String getKokyaku_nm2() {
		return kokyaku_nm2;
	}
	public void setKokyaku_nm2(String kokyaku_nm2) {
		this.kokyaku_nm2 = kokyaku_nm2;
	}
	public String getKokyaku_kana1() {
		return kokyaku_kana1;
	}
	public void setKokyaku_kana1(String kokyaku_kana1) {
		this.kokyaku_kana1 = kokyaku_kana1;
	}
	public String getKokyaku_kana2() {
		return kokyaku_kana2;
	}
	public void setKokyaku_kana2(String kokyaku_kana2) {
		this.kokyaku_kana2 = kokyaku_kana2;
	}
	public String getKokyaku_zip() {
		return kokyaku_zip;
	}
	public void setKokyaku_zip(String kokyaku_zip) {
		this.kokyaku_zip = kokyaku_zip;
	}
	public String getKokyaku_addr1() {
		return kokyaku_addr1;
	}
	public void setKokyaku_addr1(String kokyaku_addr1) {
		this.kokyaku_addr1 = kokyaku_addr1;
	}
	public String getKokyaku_addr2() {
		return kokyaku_addr2;
	}
	public void setKokyaku_addr2(String kokyaku_addr2) {
		this.kokyaku_addr2 = kokyaku_addr2;
	}
	public String getKokyaku_addr3() {
		return kokyaku_addr3;
	}
	public void setKokyaku_addr3(String kokyaku_addr3) {
		this.kokyaku_addr3 = kokyaku_addr3;
	}
	public String getKokyaku_tel1() {
		return kokyaku_tel1;
	}
	public void setKokyaku_tel1(String kokyaku_tel1) {
		this.kokyaku_tel1 = kokyaku_tel1;
	}
	public String getKokyaku_tel2() {
		return kokyaku_tel2;
	}
	public void setKokyaku_tel2(String kokyaku_tel2) {
		this.kokyaku_tel2 = kokyaku_tel2;
	}
	public String getKokyaku_fax() {
		return kokyaku_fax;
	}
	public void setKokyaku_fax(String kokyaku_fax) {
		this.kokyaku_fax = kokyaku_fax;
	}
	public String getKokyaku_mail1() {
		return kokyaku_mail1;
	}
	public void setKokyaku_mail1(String kokyaku_mail1) {
		this.kokyaku_mail1 = kokyaku_mail1;
	}
	public String getKokyaku_mail2() {
		return kokyaku_mail2;
	}
	public void setKokyaku_mail2(String kokyaku_mail2) {
		this.kokyaku_mail2 = kokyaku_mail2;
	}
	public String getTdk_cd() {
		return tdk_cd;
	}
	public void setTdk_cd(String tdk_cd) {
		this.tdk_cd = tdk_cd;
	}
	public String getTdk_nm1() {
		return tdk_nm1;
	}
	public void setTdk_nm1(String tdk_nm1) {
		this.tdk_nm1 = tdk_nm1;
	}
	public String getTdk_nm2() {
		return tdk_nm2;
	}
	public void setTdk_nm2(String tdk_nm2) {
		this.tdk_nm2 = tdk_nm2;
	}
	public String getTdk_kana1() {
		return tdk_kana1;
	}
	public void setTdk_kana1(String tdk_kana1) {
		this.tdk_kana1 = tdk_kana1;
	}
	public String getTdk_kana2() {
		return tdk_kana2;
	}
	public void setTdk_kana2(String tdk_kana2) {
		this.tdk_kana2 = tdk_kana2;
	}
	public String getTdk_zip() {
		return tdk_zip;
	}
	public void setTdk_zip(String tdk_zip) {
		this.tdk_zip = tdk_zip;
	}
	public String getTdk_addr1() {
		return tdk_addr1;
	}
	public void setTdk_addr1(String tdk_addr1) {
		this.tdk_addr1 = tdk_addr1;
	}
	public String getTdk_addr2() {
		return tdk_addr2;
	}
	public void setTdk_addr2(String tdk_addr2) {
		this.tdk_addr2 = tdk_addr2;
	}
	public String getTdk_addr3() {
		return tdk_addr3;
	}
	public void setTdk_addr3(String tdk_addr3) {
		this.tdk_addr3 = tdk_addr3;
	}
	public String getTdk_tel1() {
		return tdk_tel1;
	}
	public void setTdk_tel1(String tdk_tel1) {
		this.tdk_tel1 = tdk_tel1;
	}
	public String getTdk_tel2() {
		return tdk_tel2;
	}
	public void setTdk_tel2(String tdk_tel2) {
		this.tdk_tel2 = tdk_tel2;
	}
	public String getTdk_ymd() {
		return tdk_ymd;
	}
	public void setTdk_ymd(String tdk_ymd) {
		this.tdk_ymd = tdk_ymd;
	}
	public String getIns_kbn() {
		return "1";
	}
	public String getMake_date() {
		return make_date;
	}
	public void setMake_date(String make_date) {
		this.make_date = make_date;
	}
	public String getRep_date() {
		return rep_date;
	}
	public void setRep_date(String rep_date) {
		this.rep_date = rep_date;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getSyoriKbnNm() {
		return syoriKbnNm;
	}
	public void setSyoriKbnNm(String syoriKbnNm) {
		this.syoriKbnNm = syoriKbnNm;
	}
	public String getHstm_kbn_nm() {
		return hstm_kbn_nm;
	}
	public void setHstm_kbn_nm(String hstm_kbn_nm) {
		this.hstm_kbn_nm = hstm_kbn_nm;
	}
	public String getOrder_ymd_hms() {
		return order_ymd_hms;
	}
	public void setOrder_ymd_hms(String order_ymd_hms) {
		this.order_ymd_hms = order_ymd_hms;
	}
	public String getRep_user_nm() {
		return rep_user_nm;
	}
	public void setRep_user_nm(String rep_user_nm) {
		this.rep_user_nm = rep_user_nm;
	}
	public String getSksj_cd() {
		return sksj_cd;
	}
	public void setSksj_cd(String sksj_cd) {
		this.sksj_cd = sksj_cd;
	}
	public String getSyukka_yotei_ymd() {
		return syukka_yotei_ymd;
	}
	public void setSyukka_yotei_ymd(String syukka_yotei_ymd) {
		this.syukka_yotei_ymd = syukka_yotei_ymd;
	}
	public String getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}
	public String getKosu() {
		return StringUtil.nvl(kosu).replaceAll(",", "");
	}
	public void setKosu(String kosu) {
		this.kosu = kosu;
	}
	public String getOkurino() {
		return okurino;
	}
	public void setOkurino(String okurino) {
		this.okurino = okurino;
	}
	public String getOrder_biko() {
		return order_biko;
	}
	public void setOrder_biko(String order_biko) {
		this.order_biko = order_biko;
	}
	public String getKokyaku_mail3() {
		return kokyaku_mail3;
	}
	public void setKokyaku_mail3(String kokyakuMail3) {
		kokyaku_mail3 = kokyakuMail3;
	}
	public String getQty_cnt() {
		return qty_cnt;
	}
	public void setQty_cnt(String qty_cnt) {
		this.qty_cnt = qty_cnt;
	}
	public String getSeikyuu_kin() {
		return seikyuu_kin;
	}
	public void setSeikyuu_kin(String seikyuu_kin) {
		this.seikyuu_kin = seikyuu_kin;
	}
	public String getSeikyuu_zei_kin() {
		return seikyuu_zei_kin;
	}
	public void setSeikyuu_zei_kin(String seikyuu_zei_kin) {
		this.seikyuu_zei_kin = seikyuu_zei_kin;
	}
	public String getSeikyuu_zei_k_kin() {
		return seikyuu_zei_k_kin;
	}
	public void setSeikyuu_zei_k_kin(String seikyuu_zei_k_kin) {
		this.seikyuu_zei_k_kin = seikyuu_zei_k_kin;
	}

	public List<Map<String, String>> getLstHead() {
		return lstHead;
	}
	public void setLstHead(List<Map<String, String>> lstHead) {
		this.lstHead = lstHead;
	}
	public List<Map<String, String>> getLstDetail() {
		return lstDetail;
	}
	public void setLstDetail(List<Map<String, String>> lstDetail) {
		this.lstDetail = lstDetail;
	}

}
