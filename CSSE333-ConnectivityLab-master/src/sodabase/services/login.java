package sodabase.services;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class login {


	private DatabaseConnectionService dbService = null;
	
	public login(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public boolean tologin(String email, int username, String pw) {
		try {
			CallableStatement c = dbService.getConnection().prepareCall("{ ? = call (?,?,?)}");
			c.registerOutParameter(1, Types.INTEGER);
			c.setString(2, email);
			c.setInt(3, username);
			c.setString(4, pw);
			c.execute();
			int toreturn = c.getInt(1);
			if (toreturn == 1) {
				JOptionPane.showMessageDialog(null, "ERROR:" + toreturn + " Username cannot be null or empty.");
				return false;
			}
			if (toreturn == 2) {
				JOptionPane.showMessageDialog(null, "ERROR:" + toreturn + " Username already exists.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Add user failed.");
			return false;
		}
		return true;
	}
	
}