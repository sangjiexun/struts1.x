package jp.co.tcc.ecs.e_asproLogin.CC040HanroList;
/**
 * [�T �v]:�̘H�ڍ�Servlet<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [�T �v]:���ʃq�A�����O�o�^Servlet<br>
 * [�� ��]:<br>
 * [�� �l]:<br>
 * ���쌠: Copyright (c) 2019<br>
 */
public class HanroListServlet extends CommonServlet{

	private static final long serialVersionUID = 1L;
	private HanroListService service = new HanroListService();
	/**
	 * [�T �v]:��������<br>
	 * [�� ��]:<br>
	 * [�� �l]:<br>
	 */
	public String doAction(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//bean�ɉ�ʒl���o�C���h����
		HanroListBean bean = new HanroListBean();
		setRequest2Bean(request,bean);
		
		//bean�Ƀ��O�C�����[�U�[���o�C���h����
		User user = (User) request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
		bean.setLoginUser(user);
		
		//��������
		String returnStr = "";
		if (Constants.PAGE_INIT.equals(StringUtil.nvl(bean.getProcessType(),Constants.PAGE_INIT))){
			//������
			returnStr = doInit(request,response,bean);
			
		}
		request.setAttribute("bean", bean);
		return returnStr;
	}
	
	/**
	 * [�T �v]:������<br>
	 * [�� ��]:<br>
	 * [�� �l]:<br>
	 */
	public String doInit(HttpServletRequest request,HttpServletResponse response, HanroListBean bean) throws Exception {
		// �����f�[�^
		service.search(bean);
		
		return HanroListBean.FORWARD_URL;
	}

}
