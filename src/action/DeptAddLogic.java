package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeptDAO;

public class DeptAddLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String deptID = request.getParameter("deptID");
		if (!deptID.matches("[1-9][1-9][1-9]")) {
			request.setAttribute("errorMessage", "部署IDは1~99で入力してください。");
			return "error.jsp";
		}
		String deptName = request.getParameter("deptName");
		if (deptName.equals("")) {
			request.setAttribute("errorMessage", "部署名は殻で登録できません。");
			return "error.jsp";
		}
		DeptDAO deptDao = new DeptDAO();
		if (deptDao.addDept(deptID, deptName) == false) {
			request.setAttribute("errorMessage", "データベース登録に失敗しました。");
			return "error.jsp";
		}
		request.setAttribute("message", "データベース登録に成功しました。");
		return "success.jsp";
	}
}