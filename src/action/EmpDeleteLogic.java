package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Picture;
import dao.EmployeeDAO;
import dao.ImageDAO;

public class EmpDeleteLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String empID = request.getParameter("empID");
		EmployeeDAO empDao = new EmployeeDAO();
		if (empDao.deleteEmp(empID) == true) {
			ImageDAO imageDao = new ImageDAO();
			List<Picture> imageAllList = imageDao.findAllImage();
			session.setAttribute("imageAllList", imageAllList);
			int pictID = Integer.parseInt(empID);
			if (isImageContain(imageAllList, pictID)) {
				if (imageDao.deleteImage(pictID) == false) {
					request.setAttribute("errorMessage", "レコード削除に失敗しました。");
					return "error.jsp";
				}
			}
		} else if (empDao.deleteEmp(empID) == false) {
			request.setAttribute("errorMessage", "レコード削除に失敗しました。");
			return "error.jsp";
		}
		request.setAttribute("message", "レコードの削除に成功しました。");
		return "success.jsp";
	}
	boolean isImageContain(List<Picture> imageAllList, int pictID) {
		for(Picture pict : imageAllList) {
			if(pict.getPictID() == pictID) {
				return true;
			}
		}
		return false;
	}
}