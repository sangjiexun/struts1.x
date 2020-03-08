/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package action;

import form.UserLoginForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * MyEclipse Struts
 * Creation date: 02-27-2020
 * 
 * XDoclet definition:
 * @struts.action path="/userLogin" name="userLoginForm" input="/Login.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/userLoginSuccess.jsp"
 * @struts.action-forward name="failure" path="/index.jsp"
 */
public class UserLoginAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
//		UserLoginForm userLoginForm = (UserLoginForm) form;// TODO Auto-generated method stub
//		return null;
		
			        UserLoginForm userLoginForm = (UserLoginForm)form;
			            if(userLoginForm.getUserName().equals("struts")
			             &&
			             userLoginForm.getPassword().equals("struts"))
			               { 
			                request.setAttribute("userName",
			                userLoginForm.getUserName()); 
			                return
			                mapping.findForward("success"); 
			               }         
			 
			               return mapping.findForward("failure"); 
			              }
	
}