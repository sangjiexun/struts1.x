package jp.co.tcc.ecs.e_asproLogin.HA070Menu;

import java.util.Map;

public class MenuService {
	/**
	 * [�T �v]TOP��main�����\���p<br>
	 * [�� ��]:DWH���[�U�̏ꍇ�͎捞����\������B<br>
	 * [�� �l]:JSP���璼�ڌďo��
	 * @param patternNo
	 * @return
	 * @throws Exception
	 */
	static public Map<String,String> getDwhStatus(int patternNo) throws Exception{
		Map<String, String> map = MenuDAO.getDwhStatus(patternNo);
		return map;
	}
}
