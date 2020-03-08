package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

import java.util.Map;

public class MenuService {
	/**
	 * [概 要]TOPのmain部分表示用<br>
	 * [説 明]:DWHユーザの場合は取込情報を表示する。<br>
	 * [備 考]:JSPから直接呼出し
	 * @param patternNo
	 * @return
	 * @throws Exception
	 */
	static public Map<String,String> getDwhStatus(int patternNo) throws Exception{
		Map<String, String> map = MenuDAO.getDwhStatus(patternNo);
		return map;
	}
}
