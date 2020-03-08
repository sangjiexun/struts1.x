package jp.co.tcc.ecs.e_asproLogin.Login;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

/**
 * [概　要]：ログイン処理を実行。<br>
 * [説　明]：ログイン処理を実行。<br>
 * [備　考]：Hyper専用クラスです。<br>
 *　著作権:Toukei Computer Company Copyright (c) 2008<br>
 * @author DHK
 * @version 1.0
 * @since 1.0
 */
public class LoginDAO {
	/** 
	 * [概 要]:ログイン画面データ検索。<br>
	 * [説 明]:ログイン画面データに合うデータを検索する。<br>
	 * [備 考]:<br>
	 * @param bean ログイン画面オブジェクト
	 * @return bean ログイン画面ブジェクト
	 * @throws Exception
	 * @see
	 */
	public LoginBean search(LoginBean bean) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<SQLParamType> listParams = new ArrayList<SQLParamType>();
		Vector<Map<String, String>> result = null;
		//検索用SQL
		sql.append(" select ");
		sql.append("    A.CO_NO,");  	 	//会社NO
		sql.append("    A.COMP_NM AS TITLE_NAME, "); 	//会社構成名
		sql.append("    A.COLOR1,");       	//色１
		sql.append("    A.IMG1");         	//画像１
		sql.append(" from");
		sql.append("    M_EA001_COMP A");  //使用済商品マスタ
		sql.append(" where 1=1 ");
		sql.append(" and  A.COMP_STRING = ?");
		String strCmp = bean.getCompString();

		listParams.add(DBHelper.createSQLParamVarchar2(strCmp));

		
		result = DBHelper.execSelectWithPreparedStatement(sql.toString(), listParams);
		bean.setList(result);
		return bean;
	}

}
