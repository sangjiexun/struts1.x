package jp.co.tcc.ecs.e_asproLogin.CC021SearchProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonService;
import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;
import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecs.e_asproComm.framework.base.ManageUtils;

import jp.co.tcc.ecs.e_asproUser.User.User;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [�T�v]�ڋq�I���E�B���h�E�N���X<br>
 * @version 1.0<br>
 * @since 1.0<br>
 */

public class SearchProductServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;

	String mapKey = "CC021_PAGEFEED";
	SearchProductService service = new SearchProductService();
	private static final String SUC_SearchProduct_JSP = "/search/CC021SearchProduct.jsp";

	public String doAction(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SearchProductBean bean = new SearchProductBean();
		setRequest2Bean(request,bean);
		String returnStr = SUC_SearchProduct_JSP;

		User user = (User) request.getSession().getAttribute("_user");
		String strCo_no = Integer.toString(user.getCoNo());
		bean.setCo_no(strCo_no);

		//�����\��
		if ("".equals(bean.getProcessType())){
			returnStr = doInit(request, response, bean);
		//����
		} else if ("1".equals(bean.getProcessType())){
			returnStr = doSubmit(request, response, bean);
			ManageUtils manageUtils = bean.getManageUtils(request);
			manageUtils.addListReturnURL("CC021","/e_asproLogin/CC021SearchProduct", bean.getSearchParamMap());
			manageUtils.setReturnURL2Request(request);
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
	public String doInit(HttpServletRequest request,HttpServletResponse response, SearchProductBean bean) throws Exception {
		//�����F�x�ЃR�[�h
		String sisyaCd = request.getParameter("sisyaCd");
		//�����F���ʏ��i�\���t���O
		String kyotuDispFlg = request.getParameter("kyotuDispFlg");
		//�����F���ʏ��i�̂ݕ\���t���O
		String kyotuOnlyFlg = request.getParameter("kyotuOnlyFlg");

		//�����ɂĎx�ЃR�[�h������ꍇ�i�[
		if (!"".equals(StringUtil.nvl(sisyaCd))) {
			bean.setSisyaCd(StringUtil.nvl(sisyaCd));
			bean.setSendSisyaCd(StringUtil.nvl(sisyaCd));
			bean.setSisyaNm(CommonService.getSisyaNm(bean.getCo_no(),bean.getSisyaCd()));
		}

		//�����ɂċ��ʏ��i�̂ݕ\���t���O������ꍇ�i�[
		if (!"".equals(StringUtil.nvl(kyotuOnlyFlg))) {
			bean.setSisyaCd(StringUtil.nvl(Constants.KYOTU_SISYA_CD));
			bean.setKyotuOnlyFlg(kyotuOnlyFlg);
			bean.setSisyaNm(CommonService.getSisyaNm(bean.getCo_no(),bean.getSisyaCd()));
		}

		//�����ɂċ��ʏ��i�\���t���O������ꍇ�i�[
		if (!"".equals(StringUtil.nvl(kyotuDispFlg))) {
			bean.setKyotuDispFlg(StringUtil.nvl(kyotuDispFlg));
		}

		//�v���_�E���擾
		initDropDownList(request,bean);

		return SUC_SearchProduct_JSP;
	}

	/**
	 * �����{�^��
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String doSubmit(HttpServletRequest request,HttpServletResponse response,SearchProductBean bean) throws Exception {
		User user = (User)request.getSession().getAttribute(Constants.SESSION_USER);
		bean = service.searchProduct(user,bean);
		//�v���_�E���擾
		initDropDownList(request,bean);
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
	public void initDropDownList(HttpServletRequest request, SearchProductBean bean) throws Exception {

		boolean kyotuDispFlg = true;
		if("".equals(bean.getKyotuDispFlg())){
			kyotuDispFlg = false;
		}

		// �x�Ѓ��X�g
		bean.setSisyaList(CommonService.getSisyaList(bean.getCo_no(), "",bean.getSendSisyaCd(), true, kyotuDispFlg, false));
	}

}