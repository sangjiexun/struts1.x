package jp.co.tcc.ecs.e_asproLogin.CC031SearchZaiko;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;
import jp.co.tcc.ecs.e_asproLogin.common.CommonServlet;
import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [�T�v]�݌ɏ��i�I���E�B���h�E�N���X<br>
 * @version 1.0<br>
 * @since 1.0<br>
 */

public class SearchZaikoServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;

	String mapKey = "CC031_PAGEFEED";
	SearchZaikoService service = new SearchZaikoService();
	private static final String SUC_SearchProduct_JSP = "/search/CC031SearchZaiko.jsp";

	public String doAction(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SearchZaikoBean bean = new SearchZaikoBean();
		setRequest2Bean(request,bean);
		String returnStr = SUC_SearchProduct_JSP;
		User user = (User) request.getSession().getAttribute("_user");
		String strCo_no = Integer.toString(user.getCoNo());
		bean.setCo_no(strCo_no);

		//�����\��
		if ("".equals(bean.getProcessType()) == true){
			returnStr = doInit(request, response, bean);
		//����
		} else if ("1".equals(bean.getProcessType()) == true){
			returnStr = doSubmit(request, response, bean);
			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL("CC031","/e_asproLogin/CC031SearchZaiko", bean.getSearchParamMap());
			manageUtils.setReturnURL2Request(request);
		} else if (Constants.PAGE_AJAX.equals(bean.getProcessType())) {
			//Ajax�A��
			service.doAjax(request, response, bean);
			return Constants.AJAX;
		}
		request.setAttribute("bean", bean);
		return returnStr;
	}

	/**
	 * �����\��
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String doInit(HttpServletRequest request,HttpServletResponse response, SearchZaikoBean bean) throws Exception {
		User user = (User) request.getSession().getAttribute("_user");
		String sisyaCd = request.getParameter("sisyaCd");
		String sitenCd = request.getParameter("sitenCd");
		String whCd = request.getParameter("whCd");
		String zaikoKbn = request.getParameter("zaikoKbn");
		String index = request.getParameter("index");

		//�����ɂĎx�ЃR�[�h������ꍇ�i�[
		if (!"".equals(StringUtil.nvl(sisyaCd))) {
			bean.setSisyaCd(StringUtil.nvl(sisyaCd));
			service.getSisyaNm(user,bean);
		}

		//�����ɂĎx�X�R�[�h������ꍇ�i�[
		if (!"".equals(StringUtil.nvl(sitenCd))) {
			bean.setSitenCd(StringUtil.nvl(sitenCd));
		}

		//�����ɂđq�ɃR�[�h������ꍇ�i�[
		if (!"".equals(StringUtil.nvl(whCd))) {
			bean.setWhCd(StringUtil.nvl(whCd));
			service.getWhNm(user,bean);
		}

		//�����ɂč݌ɋ敪�R�[�h������ꍇ�i�[
		if (!"".equals(StringUtil.nvl(zaikoKbn))) {
			bean.setZaikoKbn(StringUtil.nvl(zaikoKbn));
			service.getZaikoKbnNm(user,bean);
		}

		//�����ɂčs�ԍ�������ꍇ�i�[
		if (!"".equals(StringUtil.nvl(index))) {
			bean.setIndex(StringUtil.nvl(index));
		}

		initDropDownList(request, bean);

		return SUC_SearchProduct_JSP;
	}

	/**
	 * �����{�^��
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request,HttpServletResponse response,SearchZaikoBean bean) throws Exception {
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		bean = service.searchZaiko(user,bean);
		initDropDownList(request, bean);
		bean.setSearchKbn("2");
		return SUC_SearchProduct_JSP;
	}

	/**
	 * DorpDownList������
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/**
	 * [�T �v]:DropDownList�����ݒ�<br>
	 * [�� ��]:<br>
	 * [�� �l]:
	 */
	public void initDropDownList(HttpServletRequest request, SearchZaikoBean bean) throws Exception {
		// �q�ɖ�
		bean.setWhList(CommonService.getSoukoList(bean.getCo_no(), true, bean.getSisyaCd()));
		// �x�Ѓ��X�g
		bean.setSisyaList(CommonService.getSisyaList(bean.getCo_no(), "",bean.getSisyaCd(), true, false, false));
		// �x�X���X�g
		bean.setSitenList(CommonService.getSitenList(bean.getCo_no(), bean.getSisyaCd(), bean.getSitenCd(), true));
		// �݌ɋ敪
		bean.setZaikoKbnList(CommonService.getKubunList("ZAIKO_KBN", true));
	}

}