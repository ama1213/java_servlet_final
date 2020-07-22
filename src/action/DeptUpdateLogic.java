package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeptDAO;

public class DeptUpdateLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String deptID = request.getParameter("deptID");
		String deptName = request.getParameter("deptName");
		if (deptName.equals("")) {
			request.setAttribute("errorMessage", "�������͋�œo�^�ł��܂���");
			return "error.jsp";
		}
		DeptDAO deptDao = new DeptDAO();
		if (deptDao.updateDept(deptID, deptName) == false) {
			request.setAttribute("errorMessage", "�f�[�^�x�[�X�ւ̓o�^�Ɏ��s���܂���");
			return "error.jsp";
		}
		request.setAttribute("message", "�f�[�^�x�[�X�ւ̓o�^�ɐ������܂���");
		return "success.jsp";
	}
}