package sodabase.services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SodaService {

	private DatabaseConnectionService dbService = null;
	
	public SodaService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean addSoda(String sodaName, String manf) {
		try {
			CallableStatement c = dbService.getConnection().prepareCall("{ ? = call AddSoda(?,?)}");
			c.registerOutParameter(1, Types.INTEGER);
			c.setString(2, sodaName);
			c.setString(3, manf);
			c.execute();
			int toreturn = c.getInt(1);
			if (toreturn == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Soda name cannot be null or empty.");
				return false;
			}
			if (toreturn == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Soda name already exists.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Add Soda failed.");
			return false;
		}
		return true;
	}
	
	public ArrayList<String> getSodas() {
		//TODO: Task 2
		ArrayList<String> sodas = new ArrayList<String>();
		Statement stmt = null;
		String sodaName;
		String query = "Select name From Soda";
		try{
			stmt = dbService.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				sodaName = rs.getString("name");
				sodas.add(sodaName);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return sodas;
	}
}
