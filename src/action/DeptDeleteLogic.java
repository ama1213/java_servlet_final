  
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeptDAO;

public class DeptDeleteLogic implements CommonLogic {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String deptID = request.getParameter("deptID");
		DeptDAO deptDao = new DeptDAO();
		if (deptDao.deleteDept(deptID) == false) {
			request.setAttribute("errorMessage", "���R�[�h�̍폜�Ɏ��s���܂����B");
			return "error.jsp";
		}
		request.setAttribute("message", "���R�[�h�̍폜�ɐ������܂����B");
		return "success.jsp";
	}
}