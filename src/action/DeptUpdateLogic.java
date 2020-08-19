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
			request.setAttribute("errorMessage", "部署名は空で登録できません。");
			return "error.jsp";
		}
		DeptDAO deptDao = new DeptDAO();
		if (deptDao.updateDept(deptID, deptName) == false) {
			request.setAttribute("errorMessage", "データベースへの登録に失敗しました。");
			return "error.jsp";
		}
		request.setAttribute("message", "データベースへの登録に成功しました。");
		return "success.jsp";
	}
}