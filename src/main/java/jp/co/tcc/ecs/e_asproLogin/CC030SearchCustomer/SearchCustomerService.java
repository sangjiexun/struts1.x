package jp.co.tcc.ecs.e_asproLogin.CC030SearchCustomer;

import jp.co.tcc.ecs.e_asproUser.User.User;


public class SearchCustomerService {
	/**
	 * [�T�v]:����<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public SearchCustomerBean searchCustomer(User user,SearchCustomerBean bean) throws Exception{
		SearchCustomerDAO dao = new SearchCustomerDAO();
		bean = dao.searchCustomer(user,bean);
		return bean;
	}

	/**
	 * [�T�v]:����<br>
	 * [�� ��]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public SearchCustomerBean getSisyaNm(User user,SearchCustomerBean bean) throws Exception{
		SearchCustomerDAO dao = new SearchCustomerDAO();
		bean = dao.getSisyaNm(user,bean);
		return bean;
	}
}
