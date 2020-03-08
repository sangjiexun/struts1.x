package jp.co.tcc.ecs.e_asproLogin.CC900PostalCheck;

import java.util.Map;
import java.util.Vector;


public class PostalCheckService {
	
	public Vector<Map<String, String>> get_addr(String tdk_zip1, String tdk_zip2) throws Exception{
		PostalCheckDAO dao = new PostalCheckDAO();
		Vector<Map<String, String>> result=dao.get_addr(tdk_zip1, tdk_zip2);
		return result;
	}

	public int get_ken_cd(String addr) throws Exception{
		PostalCheckDAO dao = new PostalCheckDAO();
		int result=dao.get_ken_cd(addr);
		return result;
	}
	
	
	public Vector<Map<String, String>> search(String tdk_zip1, String tdk_zip2) throws Exception{
		PostalCheckDAO dao = new PostalCheckDAO();
		Vector<Map<String, String>> result=dao.search(tdk_zip1, tdk_zip2);
		return result;
	}

}
