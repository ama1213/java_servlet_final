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
			request.setAttribute("errorMessage", "レコードの削除に失敗しました。");
			return "error.jsp";
		}
		request.setAttribute("message", "レコードの削除に成功しました。");
		return "success.jsp";
	}
}