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
			request.setAttribute("errorMessage", "ID�Ɩ��O�͕K�{�ł��B");
			return "error.jsp";
		}
		String empIDstr = request.getParameter("empID");
		if (!empIDstr.matches("[0-9]{1,5}")) {
			request.setAttribute("errorMessage", "�Ј�ID��1~5���̐����œ��͂��Ă��������B");
			return "error.jsp";
		}
		String age = request.getParameter("age");
		if (!(age.matches("[0-9]{1,3}") || age.equals(""))) {
			request.setAttribute("errorMessage", "�N���1�`2���̐����œ��͂��Ă��������B(�󗓂���)");
			return "error.jsp";
		}
		String zipcode = request.getParameter("zipcode");
		if (!(zipcode.matches("^[0-9]{3}-[0-9]{4}$") || zipcode.equals(""))) {
			request.setAttribute("errorMessage", "�X�֔ԍ��̓n�C�t�������7���̗X�֔ԍ��œ��͂��Ă��������B<br>��F123-4567<br>(�󗓂���)");
			return "error.jsp";
		}
		String dateEntering = request.getParameter("dateEntering");
		if (!(dateEntering.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") || dateEntering.equals(""))) {
			request.setAttribute("errorMessage", "���Г��̓n�C�t���ŋ�؂����N�����œ��͂��Ă��������B<br>��F2001-01-01<br>(�󗓂���)");
			return "error.jsp";
		}
		String dateRetired = request.getParameter("dateRetired");
		if (!(dateRetired.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") || dateRetired.equals(""))) {
			request.setAttribute("errorMessage", "�ގГ��̓n�C�t���ŋ�؂����N�����œ��͂��Ă��������B<br>��F2001-01-01<br>(�󗓂���)");
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
			request.setAttribute("errorMessage", "�f�[�^�x�[�X�ւ̓o�^�Ɏ��s���܂����B(EMP)");
			return "error.jsp";
		}
		Part pict = null;
		try {
			pict = request.getPart("picture");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ServletException e1) {
			e1.printStackTrace();
		}
		String pictureSTR = request.getParameter("pictureSTR");
		InputStream is = null;
		if(pict != null || !pict.equals("")) {
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
					request.setAttribute("errorMessage", "�f�[�^�x�[�X�ւ̓o�^�Ɏ��s���܂����B�i�摜�f�[�^add�j");
					return "error.jsp";
				}
			} else if(!pictureSTR.equals("")) {
				//�������Ȃ�
			} else {
				if (imageDAO.updateImage(pictID, is) == false) {
					request.setAttribute("errorMessage", "�f�[�^�x�[�X�ւ̓o�^�Ɏ��s���܂����B�i�摜�f�[�^update�j");
					return "error.jsp";
				}
			}
		}

		request.setAttribute("message", "�f�[�^�x�[�X�ւ̓o�^�ɐ������܂����B");
		return "success.jsp";
	}
}