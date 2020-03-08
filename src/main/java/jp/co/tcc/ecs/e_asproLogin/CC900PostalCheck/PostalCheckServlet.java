package jp.co.tcc.ecs.e_asproLogin.CC900PostalCheck;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.tcc.ecs.e_asproComm.common.CommonServlet;

import jp.co.tcc.ecs.e_asproComm.common.Constants;
import jp.co.tcc.ecsolution.framework.otherUtils.StringUtil;

public class PostalCheckServlet extends CommonServlet {
	
	private static final long serialVersionUID = 1L;
	
	PostalCheckService service = new PostalCheckService();
	
	public String doAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PostalCheckBean bean = new PostalCheckBean();
		setRequest2Bean(request,bean);
		if (StringUtil.stringEquals(bean.getProcessType(), Constants.PAGE_AJAX)){
			doAjax(request, response);
		}else{
			doSearch(request, response, bean);
			return "search/CC900PostalCheck.jsp";
		}
		
		return Constants.AJAX;
	}

	/**
	 * [äT óv]:ÉAÉhÉåÉXéÊìæ<br>
	 * [ê‡ ñæ]:óXï÷î‘çÜÇ©ÇÁèZèäÇÇÌÇËÇæÇ∑<br>
	 * [îı çl]:
	 * @return
	 * @throws Exception
	 */
	public void doAjax(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		String tdk_zip1 = request.getParameter("tdk_zip1");
		String tdk_zip2 = request.getParameter("tdk_zip2");
		String index	= request.getParameter("idx");
		
		response.setContentType(Constants.AJAX_CONTENTTYPE);
		StringBuffer sb = new StringBuffer();

		Vector<Map<String, String>> result = service.get_addr(tdk_zip1,tdk_zip2);
		if (result.size() > 0){
			//do nothing
			if(result.size() == 1){
				String addr1 = StringUtil.nvl(result.get(0).get("KEN_NM"))+StringUtil.nvl(result.get(0).get("ADDR1"));
				String addr2 = StringUtil.nvl(result.get(0).get("ADDR2"));
				String addr3 = StringUtil.nvl(result.get(0).get("ADDR3"));
				String allAddr = addr1+addr2+addr3;
				int ken_cd = service.get_ken_cd(allAddr);
				addr1 = "";
				addr2 = "";
				addr3 = "";
				if(allAddr.length() > 40){
					addr1 = allAddr.substring(0, 20);
					addr2 = allAddr.substring(20, 40);
					addr3 = allAddr.substring(40);
				}else if(allAddr.length() > 20){
					addr1 = allAddr.substring(0, 20);
					addr2 = allAddr.substring(20);
					addr3 = "";
				}else{
					addr1 = allAddr;
					addr2 = "";
					addr3 = "";
				}
				
				sb.append("setAddr('"+addr1+"','"+addr2+"','"+addr3+"','"+index+"','"+ken_cd+"')");
				
			}else{
				sb.append("open_sub('"+tdk_zip1+"','"+tdk_zip2+"','"+index+"')");
			}
		}
		PrintWriter pw = response.getWriter();
		pw.write(sb.toString());
		pw.close();
	}

	/**
	 * [äT óv]:èZèäåüçı<br>
	 * [ê‡ ñæ]:<br>
	 * [îı çl]:
	 * @return
	 * @throws Exception
	 */
	public void doSearch(HttpServletRequest request,HttpServletResponse response, PostalCheckBean bean) throws Exception {
		String tdk_zip1 = request.getParameter("tdk_zip1");
		String tdk_zip2 = request.getParameter("tdk_zip2");
		String index	= request.getParameter("idx");
		
		Vector<Map<String, String>> result = service.search(tdk_zip1,tdk_zip2);
		request.getSession().setAttribute("list", result);
		request.getSession().setAttribute("index", index);
		request.getSession().setAttribute("kenCd", result.get(0).get("KEN_CD"));
	}

}
