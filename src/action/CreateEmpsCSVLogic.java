package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Employee;

public class CreateEmpsCSVLogic implements CommonLogic {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Employee> empAllList = (List<Employee>) session.getAttribute("empAllList");
		if (empAllList == null) {
			request.setAttribute("message", "社員の登録データがありません。");
		}

		response.setContentType("text/csv;charset=UTF8");
		String fileName = null;
		try {
			fileName = new String("employeeList.csv".getBytes("Shift_JIS"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			PrintWriter outputResult = response.getWriter();
			outputResult.write(
					"emp_id,emp_name,age,gender,image_id,zipcode,prefecture,address,dept_id,date_entering,date_retired");
			outputResult.write(String.format("%n"));
			for (Employee emp : empAllList) {
				outputResult.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
						emp.getEmpID(), emp.getEmpName(), emp.getAge(), emp.getGender().toString(), emp.getPictID(),
						emp.getZipcode(), emp.getPref().getFullText(), emp.getAddress(), emp.getDeptID(),
						emp.getDateEntering(),
						emp.getDateRetired()));
			}
			outputResult.close();
		} catch (IOException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "CSVファイルの作成に失敗しました。");
			return "error.jsp";
		}
		request.setAttribute("message", "社員リストのCSVファイルをダウンロードしました。");
		return "success.jsp";
	}
}