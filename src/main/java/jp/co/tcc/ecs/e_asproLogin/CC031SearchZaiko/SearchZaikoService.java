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
	 * [概要]:在庫商品検索<br>
	 * [説 明]:<br>
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
	 * [概要]:倉庫名DropDownList取得<br>
	 * [説 明]:<br>
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
	 * [概要]:支社名検索<br>
	 * [説 明]:<br>
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
	 * [概要]:倉庫名検索<br>
	 * [説 明]:<br>
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
	 * [概要]:在庫区分名検索<br>
	 * [説 明]:<br>
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
			//支社変更時倉庫プルダウン取得

			String sisyaCd = StringUtil.nvl(request.getParameter("sisyaCd"));
			String sitenCd = StringUtil.nvl(request.getParameter("sitenCd"));
			List<Map<String, String>> sitenList = CommonService.getSitenList(StringUtil.nvl(user.getCoNo()),sisyaCd,sitenCd,true);
			bean.setSitenList(sitenList);
			if (bean.getSitenList() != null){
				for (Map<String,String> item : bean.getSitenList()) {
					//SELECT 作成Scpipt
					sb.append("$('#sitenCdSearch').append($('<option>').html('"+ StringUtil.nvl(item.get("VALUE")) + "').val('" + StringUtil.nvl(item.get("KEY")) + "')); ");
				}
			}
		}

		PrintWriter pw = response.getWriter();
		pw.write(sb.toString());
		pw.close();
	}


}
