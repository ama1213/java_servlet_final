package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Dept;

public class DeptEditLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Dept> deptAllList = (List<Dept>) session.getAttribute("deptAllList");
		int deptID = Integer.parseInt(request.getParameter("deptID"));
		for(Dept dept : deptAllList) {
			if(dept.getDeptID() == deptID) {
				request.setAttribute("deptEdited", dept);
			}
		}
		return "dept_edit.jsp";
	}
}