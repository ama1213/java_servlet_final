package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Dept;
import bean.Employee;
import bean.Picture;
import dao.DeptDAO;
import dao.EmployeeDAO;
import dao.ImageDAO;

public class EmpListLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		EmployeeDAO empDAO = new EmployeeDAO();
		List<Employee>empAllList = empDAO.findAllEmp();
		session.setAttribute("empAllList", empAllList);
		if (empAllList == null) {
			request.setAttribute("message", "社員の登録データがありません");
		}
		DeptDAO deptDAO = new DeptDAO();
		List<Dept> deptAllList = deptDAO.findAllDept();
		session.setAttribute("deptAllList", deptAllList);
		if (deptAllList == null) {
			request.setAttribute("message", "部署の登録データがありません");
		}
		ImageDAO imageDAO = new ImageDAO();
		List<Picture> imageAllList = imageDAO.findAllImage();
		session.setAttribute("imageAllList", imageAllList);
		if (imageAllList == null) {
			request.setAttribute("message", "画像の登録データがありません");
		}
		return "emp_list.jsp";
	}
}