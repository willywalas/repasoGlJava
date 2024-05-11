package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Student;
import interfaces.AuthInterface;
import util.MySqlConexion;

public class AuthModel implements AuthInterface {

	@Override
	public Student verificarInicioSesion(String codigo, String clave) {
		// TODO Auto-generated method stub
		
		Student student= null;
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlConexion.getConexion();
			String mysql = "SELECT * FROM STUDENT WHERE CODE=? AND PASSWORD=?";
			psmt = cn.prepareStatement(mysql);
			psmt.setString(1, codigo);
			psmt.setString(2, clave);
			rs = psmt.executeQuery();
			if(rs.next()) {
				student = new Student();
				student.setName(rs.getString("name"));
				student.setLastname(rs.getString("lastname"));
				student.setCode(rs.getString("code"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) rs.close();
				if (cn != null) rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return student;
	}

}
