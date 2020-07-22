package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeptDAO;

public class DeptAddLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String deptID = request.getParameter("deptID");
		if (!deptID.matches("[1-9]|[1-9][0-9]")) {
			request.setAttribute("errorMessage", "����ID��1~99�œ��͂��Ă�������");
			return "error.jsp";
		}
		String deptName = request.getParameter("deptName");
		if (deptName.equals("")) {
			request.setAttribute("errorMessage", "�������͋�œo�^�ł��܂���");
			return "error.jsp";
		}
		DeptDAO deptDao = new DeptDAO();
		if (deptDao.addDept(deptID, deptName) == false) {
			request.setAttribute("errorMessage", "�f�[�^�x�[�X�ւ̓o�^�Ɏ��s���܂���");
			return "error.jsp";
		}
		request.setAttribute("message", "�f�[�^�x�[�X�ւ̓o�^�ɐ������܂���");
		return "success.jsp";
	}
}