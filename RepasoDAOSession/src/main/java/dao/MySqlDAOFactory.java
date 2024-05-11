package dao;

import interfaces.AuthInterface;
import interfaces.SubjectInterface;
import modelo.AuthModel;
import modelo.SubjectModel;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public SubjectInterface getSubject() {
		// TODO Auto-generated method stub
		return new SubjectModel();
	}

	@Override
	public AuthInterface getAuth() {
		// TODO Auto-generated method stub
		return new AuthModel();
	}

}
