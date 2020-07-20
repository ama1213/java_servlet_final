package action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.EmployeeDAO;
import dao.ImageDAO;

public class EmpUpdateLogic implements CommonLogic {

	@SuppressWarnings({ "null", "unlikely-arg-type" })
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		boolean empIDisNull = request.getParameter("empID").equals("");
		boolean empNameisNull = request.getParameter("empName").equals("");
		if(empIDisNull || empNameisNull) {
			request.setAttribute("errorMessage", "IDと名前は必須です。");
			return "error.jsp";
		}
		String empIDstr = request.getParameter("empID");
		if (!empIDstr.matches("[0-9]{1,5}")) {
			request.setAttribute("errorMessage", "社員IDは1~5桁の数字で入力してください。");
			return "error.jsp";
		}
		String age = request.getParameter("age");
		if (!(age.matches("^[0-9]{1,3}") || age.equals(""))) {
			request.setAttribute("errorMessage", "年齢は1~2桁の数字で入力してください。");
			return "error.jsp";
		}
		String zipcode = request.getParameter("zipcode");
		if (!(zipcode.matches("^[0-9]{3}-[0-9]{4}$") || zipcode.equals(""))) {
			request.setAttribute("errorMessage", "郵便番号はハイフンありの７桁の郵便番号で入力してください。");
			return "error.jsp";
		}
		String dateEntering = request.getParameter("dateRerired");
		if (!(dateEntering.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") || dateEntering.equals(""))) {
			request.setAttribute("errorMessage", "入社日はハイフンで区切った年月日で入力してください。");
			return "error.jsp";
		}
		String dateRetired = request.getParameter("dateEntering");
		if (!(dateEntering.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") || dateEntering.equals(""))) {
			request.setAttribute("errorMessage", "退社日はハイフンで区切った年月日で入力してください。");
			return "error.jsp";
		}
		int pictID = Integer.parseInt(empIDstr);
		List<String> empParams = new ArrayList<String>();
		empParams.add(request.getParameter("empName"));
		empParams.add(age);
		empParams.add(request.getParameter("gender"));
		empParams.add(String.valueOf(pictID));
		empParams.add(zipcode);
		empParams.add(request.getParameter("prefecture"));
		empParams.add(request.getParameter("address"));
		empParams.add(request.getParameter("deptID"));
		empParams.add(dateEntering);
		empParams.add(dateRetired);
		empParams.add(empIDstr);
		EmployeeDAO empDao = new EmployeeDAO();
		if (empDao.updateEmp(empParams) == false) {
			request.setAttribute("errorMessage", "データベース登録に失敗しました。");
			return "error.jsp";
		}
		Part pict = null;
		try {
			pict = request.getPart("picture");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		String pictureSTR = request.getParameter("pictureSTR");
		InputStream is = null;
		if (pict != null || !pict.equals("")) {
			try {
				is = pict.getInputStream();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ImageDAO imageDAO = new ImageDAO();
			if(pictureSTR == null) {
				if (imageDAO.addImage(pictID, is) == false) {
					request.setAttribute("errorMessage", "データベース登録に失敗しました。");
					return "error.jsp";
				}
			} else {
				if (imageDAO.updateImage(pictID, is) == false) {
					request.setAttribute("errorMessage", "データベース登録に失敗しました。");
					return "error.jsp";
				}
			}
		}
		request.setAttribute("message", "データベース登録に成功しました。");
		return "success.jsp";
	}
}