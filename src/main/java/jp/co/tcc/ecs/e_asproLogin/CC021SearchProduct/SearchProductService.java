package jp.co.tcc.ecs.e_asproLogin.CC021SearchProduct;

import jp.co.tcc.ecs.e_asproUser.User.User;


public class SearchProductService {
	/** 
	 * [äTóv]:åüçı<br>
	 * [ê‡ ñæ]:<br>
	 * @param bean
	 * @return bean
	 * @throws Exception
	 */
	public SearchProductBean searchProduct(User user,SearchProductBean bean) throws Exception{
		SearchProductDAO dao = new SearchProductDAO();
		bean = dao.searchProduct(user,bean);
		return bean;
	}
}
