package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import entidades.Student;
import entidades.Subject;
import interfaces.AuthInterface;
import interfaces.SubjectInterface;

/**
 * Servlet implementation class EstudianteServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if(type.equals("login")) {
			String code= request.getParameter("txtCode");
			String clave = request.getParameter("txtPass");
			
			DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
			AuthInterface dao = daoFactory.getAuth();
			
			Student student = dao.verificarInicioSesion(code, clave);
			if (student != null) {
				HttpSession session = request.getSession(true);
				String nombre= student.getName();
				String apellido=student.getLastname();
				String codigo=student.getCode();
				
				session.setAttribute("nombre", nombre);
				session.setAttribute("apellido", apellido);
				session.setAttribute("codigo", codigo);
				
				SubjectInterface subjectInterface = daoFactory.getSubject();
				List<Subject> data=subjectInterface.listSubject();
				request.setAttribute("data", data);
				request.getRequestDispatcher("listSubject.jsp").forward(request, response);
			}else {
				request.setAttribute("mensaje", "Error en usuario y/clave");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if(type.equals("logout")) {
			HttpSession session = request.getSession(true);
			session.removeAttribute("nombre");
			session.invalidate();
			
			response.sendRedirect("login.jsp");
		}
		
	}


}
