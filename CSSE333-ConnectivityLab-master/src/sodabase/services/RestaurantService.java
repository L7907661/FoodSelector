package sodabase.services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RestaurantService {

	private DatabaseConnectionService dbService = null;
	
	public RestaurantService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public boolean addResturant(String restName, String addr, String contact) {
		//TODO: Task 5
//		JOptionPane.showMessageDialog(null, "Add Restaurant not implemented.");
		try {
			CallableStatement c = dbService.getConnection().prepareCall("{ ? = call AddRestaurant(?,?,?)}");
			c.registerOutParameter(1, Types.INTEGER);
			c.setString(2, restName);
			c.setString(3, addr);
			c.setString(4, contact);
			c.execute();
			int toreturn = c.getInt(1);
			if (toreturn == 1) {
				JOptionPane.showMessageDialog(null, "ERROR:" + toreturn + " Restaurant name cannot be null or empty.");
				return false;
			}
			if (toreturn == 2) {
				JOptionPane.showMessageDialog(null, "ERROR:" + toreturn + " Restaurant name already exists.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Add Restaurant failed.");
			return false;
		}
		return true;
	}
	

	public ArrayList<String> getRestaurants() {	
		//TODO: Task 2 
		ArrayList<String> rests = new ArrayList<String>();
		Statement stmt = null;
		String restName;
		String query = "Select [Name] From Rest";
		try {
			stmt = dbService.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				restName = rs.getString("Name");
				rests.add(restName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rests;
	}
}
