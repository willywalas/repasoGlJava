package dao;

import interfaces.AuthInterface;
import interfaces.SubjectInterface;

public abstract class DAOFactory {
	
	public static final int MYSQL=1;
	public static final int SQLSERVER=2;
	public static final int ORACLE=3;
	
	//esto varia
	//public abstract StudentInterface getStudent();
	public abstract SubjectInterface getSubject();
	public abstract AuthInterface getAuth();
	
	
	public static DAOFactory getDaoFactory(int tipo) {
		switch (tipo) {
		case MYSQL:
			return new MySqlDAOFactory();
			
		case SQLSERVER:
			return null;
			
		case ORACLE:
			return null;
		default:
			return null;
		}
	}
	
}
