package interfaces;

import entidades.Student;

public interface AuthInterface {
	public Student verificarInicioSesion(String codigo, String clave);
}
