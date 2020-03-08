package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

import java.util.*;
import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.DateUtil;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class MenuDAO {
	public List<Menu> getLeftMenu(User user) throws Exception{
		
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call HA070.getLeftMenu(?,?,?,?)}";	
		
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));			//会社No
		listParams.add(DBHelper.createSQLParamInteger(user.getPatternNo()));	//権限No
		listParams.add(DBHelper.createSQLParamInteger(user.getUserNo()));		//ユーザNo
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		Vector<HashMap<String, String>> menuData = (Vector<HashMap<String, String>>)result.get("LIST");
		
		List<Menu> menuList = new ArrayList<Menu>();
		if (menuData.size() == 0) {
			return menuList;
		}
		
		for (Map<String, String> row : menuData) {
			Menu menu = new Menu();
			
			menu.setTopMenuName(StringUtil.nvl(row.get("TOP_MENU_NAME")));
			menu.setTopMenuNo(StringUtil.nvl(row.get("TOP_MENU_NO")));
			menu.setSubMenuName(StringUtil.nvl(row.get("SUB_MENU_NAME")));
			menu.setSubNenuActionUrl(StringUtil.nvl(row.get("SUB_MENU_ACTION_URL")));
			menu.setOyaMenuNo(StringUtil.nvl(row.get("OYA_MENU_NO")));
			menu.setIconImageUrl(StringUtil.nvl(row.get("ICON_IMAGE_URL")));
			menu.setTitleMenuName(StringUtil.nvl(row.get("TITLE_MENU_NAME")));
			menu.setSubMenuCnt(StringUtil.nvl(row.get("SUB_MENU_CNT")));
			menu.setTopMenuCnt(StringUtil.nvl(row.get("TOP_MENU_CNT")));
			menuList.add(menu);
		}
		return menuList;
	}

	public List<TopMenu> getTopMenu(User user) throws Exception{
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call HA070.getTopMenu(?,?,?)}";	
		
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));			//会社No
		listParams.add(DBHelper.createSQLParamInteger(user.getPatternNo()));	//権限No
		listParams.add(DBHelper.createSQLOutParamCursor("LIST"));

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		Vector<HashMap<String, String>> menuData = (Vector<HashMap<String, String>>)result.get("LIST");
		
		List<TopMenu> topMenuList = new ArrayList<TopMenu>();
		if (menuData.size() == 0) {
			return topMenuList;
		}
		
		for (Map<String, String> row : menuData) {
			TopMenu topMenu = new TopMenu();
			
			topMenu.setMenuNo(StringUtil.nvl(row.get("MENU_NO")));
			topMenu.setMenuName(StringUtil.nvl(row.get("MENU_NAME")));
			topMenu.setMenuActionUrl(StringUtil.nvl(row.get("MENU_ACTION_URL")));
			topMenu.setOyaMenuNo(StringUtil.nvl(row.get("OYA_MENU_NO")));
			topMenu.setMainImageUrl(StringUtil.nvl(row.get("MAIN_IMAGE_URL")));
			topMenuList.add(topMenu);
		}

		return topMenuList;
	}

	/** 
	 * [概要]:件数リンク設定<br>
	 * [説 明]:<br>
	 * @param 
	 * @return Vector
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Vector<HashMap<String, String>> getMenuHeaderCount(User user) throws Exception {
		String coNo = StringUtil.nvl(user.getCoNo(),"0");
		
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call HA070.getMenuHeaderCount(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";	
		
		listParams.add(DBHelper.createSQLParamVarchar2(coNo));		//会社No
		listParams.add(DBHelper.createSQLParamVarchar2(user.getWhCd()));//会社No
		listParams.add(DBHelper.createSQLOutParamInteger("JU_HORYU_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("JU_MIKAKU_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("SYU_HORYU_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("SYU_MIKAKU_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("MIINSATU_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("SYU_JI_MIKAKU_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("NYU_JI_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("HEN_JI_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("HEN_MITANA_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("SYU_KIGEN_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("KIN_ZEN_CNT"));
		//listParams.add(DBHelper.createSQLOutParamInteger("KIN_MI_CNT"));
		listParams.add(DBHelper.createSQLOutParamInteger("HOJU_CNT"));	//2014/03/24 追加

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		
		String ju_horyu = (String)result.get("JU_HORYU_CNT");
		String ju_mikaku = (String)result.get("JU_MIKAKU_CNT");
		String syu_horyu = (String)result.get("SYU_HORYU_CNT");
		String syu_mikaku = (String)result.get("SYU_MIKAKU_CNT");
		String mi_print = (String)result.get("MIINSATU_CNT");
		String syu_ji_mikaku = (String)result.get("SYU_JI_MIKAKU_CNT");
		String nyu_ji = (String)result.get("NYU_JI_CNT");
		//String hen_ji = (String)result.get("HEN_JI_CNT");
		//String hen_mitana = (String)result.get("HEN_MITANA_CNT");
		//String syu_kigen = (String)result.get("SYU_KIGEN_CNT");
		//String kin_zen = (String)result.get("KIN_ZEN_CNT");
		//String kin_mi = (String)result.get("KIN_MI_CNT");
		String hoju_cnt = (String)result.get("HOJU_CNT");
		
		int[] int_menu_no = {104,
							105,
							84,
							85,
							92,
							97,
							73,
							88	//2014/03/24 要補充 追加
							//72,2014/03/24コメントアウト
							//71, 2014/03/24コメントアウト
							//75, 2014/03/24コメントアウト
							//63, 2014/03/24コメントアウト
							//66 2014/03/24コメントアウト
							};
		
		String[] name = { 	"受注保留",
							"受注未確",
							"出荷保留",
							"出荷未確",
							"納未印刷",
							"実績未確",
							"入荷実績",
							"要補充"	//2014/03/24追加
							//"返品実績", 2014/03/24コメントアウト
							//"返品未棚", 2014/03/24コメントアウト
							//"出荷期限", 2014/03/24コメントアウト
							//"金不一致", 2014/03/24コメントアウト
							//"長期未金", 2014/03/24コメントアウト
							};
		//1ヶ月前日付
		//Date fromDt = DateUtil.addMonths(CommonService.getSysdate(), -1);
		//String fromYmd = DateUtil.formatYMD(fromDt);
		
		String[] url = { "e_asproOrder/OD050OrdersAdjEntry",
						"e_asproOrder/OD060OrdersFixEntry", 
						"e_asproShip/SY040StopEntry", 
						"e_asproShip/SY050ShijiFixEntry",
						//"e_asproShip/SY110PrintList?printkbn=0&printsize=A4",
						"",
						"e_asproShip/SY140ShipFixEntry", 
						"e_asproNyuka/IN010NyukaList?processType=2&nyukaYmdFromSearch="+DateUtil.getSystemYMD()+"&nyukaYmdToSearch="+DateUtil.getSystemYMD(),
						"e_asproShip/SY070BatEntry?printsize=A4"	//2014/03/24
						//"e_asproNyuka/IN060HenpinJisEntry?&processType=2&henpinYmdSearchFrom="+DateUtil.getSystemYMD(), 
						//"e_asproNyuka/IN050HenpinTanaEntry", 
						//"e_asproZaiko/ZI010ZaikoList?&group_level=1&search_syukka_kigen_ymd_e="+DateUtil.getSystemYMD(), 
						//"e_asproSales/SL041ReceiptManageList2?chk_flg=0&make_date_s=&make_date_e=&processType=2",
						//"e_asproSales/SL090SalesNoList?chk_flg=0&sales_ymd_s=&sales_ymd_e="+fromYmd+"&processType=2"
					};

		String[] count = {ju_horyu, ju_mikaku, syu_horyu, syu_mikaku, mi_print, syu_ji_mikaku,
				nyu_ji, hoju_cnt 
				//hen_ji, hen_mitana, syu_kigen, kin_zen, kin_mi
				};
		
		HashMap<String, String> hm;
		Vector<HashMap<String, String>> vec = new Vector<HashMap<String, String>>();
		
		for (int i = 0; i < name.length; i++) {	
			if (checkUnuse(user,int_menu_no[i])){
				hm = new HashMap<String, String>();
				hm.put("name", name[i]);
				hm.put("url", url[i]);
				hm.put("cnt", count[i]);
				vec.add(hm);
			}
		}
		
		return vec;
	}

	/**
	 * [概 要]:未使用メニュー。<br>
	 * [説 明]:<br>
	 * [備 考]:
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean checkUnuse(User user, int menu_no) throws Exception {
		ArrayList<Object> listParams = new ArrayList<Object>();
		String strCallProc = "{call HA070.getMenuUnuseCnt(?,?,?,?)}";	
		
		listParams.add(DBHelper.createSQLParamInteger(user.getCoNo()));		//会社No
		listParams.add(DBHelper.createSQLParamInteger(user.getUserNo()));	//ユーザNo
		listParams.add(DBHelper.createSQLParamInteger(menu_no));			//メニューNo
		listParams.add(DBHelper.createSQLOutParamInteger("CNT"));

		HashMap result = DBHelper.executeCallableStatement(strCallProc, listParams);
		int cnt = Integer.parseInt(StringUtil.nvl(result.get("CNT"),"0"));
		
		if (cnt == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * [概 要]TOPのmain部分表示用<br>
	 * [説 明]:DWHユーザの場合は取込情報を表示する。<br>
	 * [備 考]:
	 * @param patternNo
	 * @return
	 * @throws Exception
	 */
	static public Map<String,String> getDwhStatus(int patternNo) throws Exception{
		List<SQLParamType> parameterList = new ArrayList<SQLParamType>();
		parameterList.add(DBHelper.createSQLParamInteger(patternNo));
		parameterList.add(DBHelper.createSQLOutParamCursor("ret"));
		String statement = CommonService.createCallableStatement("HA070.getDwhStatus", parameterList.size());
		Map<String, ?> result = (Map<String,?>) DBHelper.executeCallableStatement(statement, parameterList);
		Map<String,String> map = null;
		if(result != null && result.size() > 0) {
			List<Map<String, String>> list = (List<Map<String, String>>)result.get("ret");
			if (list != null && list.size() > 0) {
				map = (Map<String, String>)list.get(0);
			}
		}
		return map;
	}
}
