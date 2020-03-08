package jp.co.tcc.ecs.e_asproLogin.Login;

import jp.co.tcc.ecsolution.framework.dbUtils.DBHelper;
import jp.co.tcc.ecsolution.framework.dbUtils.SQLParamType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * [�T�@�v]�F���O�C�����������s�B<br>
 * [���@��]�F���O�C�����������s�B<br>
 * [���@�l]�FHyper��p�N���X�ł��B<br>
 *�@���쌠:Toukei Computer Company Copyright (c) 2008<br>
 * @author DHK
 * @version 1.0
 * @since 1.0
 */
public class LoginDAO {
	/** 
	 * [�T �v]:���O�C����ʃf�[�^�����B<br>
	 * [�� ��]:���O�C����ʃf�[�^�ɍ����f�[�^����������B<br>
	 * [�� �l]:<br>
	 * @param bean ���O�C����ʃI�u�W�F�N�g
	 * @return bean ���O�C����ʃu�W�F�N�g
	 * @throws Exception
	 * @see
	 */
	public LoginBean search(LoginBean bean) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<SQLParamType> listParams = new ArrayList<SQLParamType>();
		Vector<Map<String, String>> result = null;
		//�����pSQL
		sql.append(" select ");
		sql.append("    A.CO_NO,");  	 	//���NO
		sql.append("    A.COMP_NM AS TITLE_NAME, "); 	//��Ѝ\����
		sql.append("    A.COLOR1,");       	//�F�P
		sql.append("    A.IMG1");         	//�摜�P
		sql.append(" from");
		sql.append("    M_EA001_COMP A");  //�g�p�Ϗ��i�}�X�^
		sql.append(" where 1=1 ");
		sql.append(" and  A.COMP_STRING = ?");
		String strCmp = bean.getCompString();

		listParams.add(DBHelper.createSQLParamVarchar2(strCmp));

		
		result = DBHelper.execSelectWithPreparedStatement(sql.toString(), listParams);
		bean.setList(result);
		return bean;
	}

}
