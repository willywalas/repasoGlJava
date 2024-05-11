package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Subject;
import interfaces.AuthInterface;
import interfaces.SubjectInterface;
import modelo.SubjectModel;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/SubjectServlet")
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String type=request.getParameter("type");
		System.out.println("Este es el parametro : "+type);
		switch(type) {
		case "list": listSubject(request,response);break;
		case "register": registerSubject(request,response);break;
		case "info": getSubject(request,response);break;
		case "edit": editSubject(request,response);break;
		case "delete": deleteSubject(request,response);break;
		default:
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("listSubject.jsp").forward(request, response);
		}

	}

	private void listSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		AuthInterface dao = daoFactory.getAuth();
		
		SubjectInterface subjectInterface = daoFactory.getSubject();
		List<Subject> data=subjectInterface.listSubject();
		request.setAttribute("data", data);
		request.getRequestDispatcher("listSubject.jsp").forward(request, response);
	}

	private void deleteSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id =request.getParameter("id");
		System.out.println("Imprime subject : "+id);
		SubjectModel subjectModel = new SubjectModel();
		int value = subjectModel.deleteSubject(id);
		
		if(value == 0) {
			System.out.println("Entró al list subject");
			listSubject(request,response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("subject.jsp");
		}
	}

	private void editSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Entradas
		String code =request.getParameter("txtCode");
		String name =request.getParameter("txtName");
		String level =request.getParameter("txtLevel");
		String teacher =request.getParameter("txtTeacher");
		String id =request.getParameter("idSubject");
		
		System.out.println("Imprime code : "+code);
		System.out.println("Imprime name : "+name);
		System.out.println("Imprime level : "+level);
		System.out.println("Imprime teacher : "+teacher);
		System.out.println("Imprime id : "+id);
		
		//Creamos objeto
		Subject subject = new Subject();
		subject.setCode(code);
		subject.setName(name);
		subject.setLevel(level);
		subject.setTeacher(teacher);
		subject.setIdSubject(id);
		
		//Procesos
		SubjectModel model = new SubjectModel();
		int value = model.updateSubject(subject);
		
		if(value == 0) {
			System.out.println("Entró al list subject");
			listSubject(request,response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("subject.jsp");
		}
		
	}

	private void getSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String idSubject=request.getParameter("id");
		SubjectModel subjectModel = new SubjectModel();
		
		Subject subject = subjectModel.getSubject(idSubject);
		List<Subject> data = subjectModel.listSubject();

		System.out.println("Imprime subject : "+subject.idSubject);
		request.setAttribute("data", subject);
		request.getRequestDispatcher("subjectInfo.jsp").forward(request, response);
	}

	private void registerSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Entradas
		String code =request.getParameter("txtCode");
		String name =request.getParameter("txtName");
		String level =request.getParameter("txtLevel");
		String teacher =request.getParameter("txtTeacher");
		
		//Creamos objeto
		Subject subject = new Subject();
		subject.setCode(code);
		subject.setName(name);
		subject.setLevel(level);
		subject.setTeacher(teacher);
		
		//Procesos
		SubjectModel model = new SubjectModel();
		int value = model.createSubject(subject);
		
		if(value == 0) {
			listSubject(request,response);
		}else {
			request.setAttribute("mensaje", "Ocurrió un problema");
			request.getRequestDispatcher("listSubject.jsp");
		}
		
	}

}
