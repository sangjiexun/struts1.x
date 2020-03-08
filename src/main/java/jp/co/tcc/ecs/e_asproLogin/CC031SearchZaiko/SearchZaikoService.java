package jp.co.tcc.ecs.e_asproLogin.CC031SearchZaiko;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;


public class SearchZaikoService {
	/**
	 * [�T�v]:�݌ɏ��i����<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public SearchZaikoBean searchZaiko(User user,SearchZaikoBean bean) throws Exception{
		SearchZaikoDAO dao = new SearchZaikoDAO();
		bean = dao.searchZaiko(user,bean);
		return bean;
	}
	/**
	 * [�T�v]:�q�ɖ�DropDownList�擾<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public List<Map<String,String>> getWhList(SearchZaikoBean bean) throws Exception{
		List<Map<String,String>> whList = null;
		SearchZaikoDAO dao = new SearchZaikoDAO();
		whList = dao.getWhList(bean);
		return whList;
	}

	/**
	 * [�T�v]:�x�Ж�����<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public SearchZaikoBean getSisyaNm(User user,SearchZaikoBean bean) throws Exception{
		SearchZaikoDAO dao = new SearchZaikoDAO();
		bean = dao.getSisyaNm(user,bean);
		return bean;
	}

	/**
	 * [�T�v]:�q�ɖ�����<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public SearchZaikoBean getWhNm(User user,SearchZaikoBean bean) throws Exception{
		SearchZaikoDAO dao = new SearchZaikoDAO();
		bean = dao.searchWh(user,bean);
		return bean;
	}

	/**
	 * [�T�v]:�݌ɋ敪������<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public SearchZaikoBean getZaikoKbnNm(User user,SearchZaikoBean bean) throws Exception{
		SearchZaikoDAO dao = new SearchZaikoDAO();
		bean = dao.getZaikoKbnNm(user,bean);
		return bean;
	}

	public void doAjax(HttpServletRequest request, HttpServletResponse response, SearchZaikoBean bean) throws Exception {
		User user = (User) request.getSession().getAttribute(jp.co.tcc.ecsolution.framework.Constants.SESSION_USER);
		response.setContentType(Constants.AJAX_CONTENTTYPE);
		StringBuffer sb = new StringBuffer();
		String kbn = request.getParameter("kbn");
		if ("1".equals(kbn)) {
			//�x�ЕύX���q�Ƀv���_�E���擾

			String sisyaCd = StringUtil.nvl(request.getParameter("sisyaCd"));
			String sitenCd = StringUtil.nvl(request.getParameter("sitenCd"));
			List<Map<String, String>> sitenList = CommonService.getSitenList(StringUtil.nvl(user.getCoNo()),sisyaCd,sitenCd,true);
			bean.setSitenList(sitenList);
			if (bean.getSitenList() != null){
				for (Map<String,String> item : bean.getSitenList()) {
					//SELECT �쐬Scpipt
					sb.append("$('#sitenCdSearch').append($('<option>').html('"+ StringUtil.nvl(item.get("VALUE")) + "').val('" + StringUtil.nvl(item.get("KEY")) + "')); ");
				}
			}
		}

		PrintWriter pw = response.getWriter();
		pw.write(sb.toString());
		pw.close();
	}


}
