package sodabase.services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sodabase.ui.SodaByRestaurant;

public class SodasByRestaurantService {

	private DatabaseConnectionService dbService = null;

	public SodasByRestaurantService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public boolean addSodaByRestaurant(String rest, String soda, double price) {
		try {
			CallableStatement c = dbService.getConnection().prepareCall("{ ? = call AddSells(?,?,?)}");
			c.registerOutParameter(1, Types.INTEGER);
			c.setString(2, soda);
			c.setString(3, rest);
			c.setDouble(4, price);
			c.execute();
			int toreturn = c.getInt(1);
			if (toreturn == 1) {
				JOptionPane.showMessageDialog(null, "ERROR:" + toreturn + " Soda name cannot be null or empty.");
				return false;
			}
			if (toreturn == 2) {
				JOptionPane.showMessageDialog(null, "ERROR:" + toreturn + " Rest name cannot be null or empty.");
				return false;
			}
			if (toreturn == 3) {
				JOptionPane.showMessageDialog(null, "ERROR: " + toreturn + "Price cannot be null or empty.");
				return false;
			}
			if (toreturn == 4) {
				JOptionPane.showMessageDialog(null, "ERROR: " + toreturn + "Given soda does not exist.");
				return false;
			}
			if (toreturn == 5) {
				JOptionPane.showMessageDialog(null, "ERROR: " + toreturn + "Given restaurant name does not exist.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Add Sell failed.");
			return false;
		}
		return true;
	}

	public ArrayList<SodaByRestaurant> getSodasByRestaurants(String rest, String soda, String price,
			boolean useGreaterThanEqual) {
		//TODO: Task 3 and Task 4
		Double p = null;
		int i=1;
		if (price == null || price.equals("")) {
			p = null;
		} else {
			p = new Double(price);
		}
		String query = buildParameterizedSqlStatementString(rest, soda, p, useGreaterThanEqual);
		try {
			PreparedStatement stmt = this.dbService.getConnection().prepareStatement(query);
			stmt = this.dbService.getConnection().prepareStatement(query);
			if (rest != null)
				stmt.setString(i++, rest);
			if (soda != null)
				stmt.setString(i++, soda);
			if (p != null && !price.isEmpty())
				stmt.setDouble(i++, p);
			ResultSet rs = stmt.executeQuery();
			return parseResults(rs);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Failed to retrieve sodas by restaurant.");
			ex.printStackTrace();
			return new ArrayList<SodaByRestaurant>();
		}
//		Double p = null;
//		if (price == null || price.equals("")) {
//			p = null;
//		} else {
//			p = new Double(price);
//		}
//		String query = buildParameterizedSqlStatementString(rest, soda, p, useGreaterThanEqual);
//		try {
//			PreparedStatement stmt = this.dbService.getConnection().prepareStatement(query);
//			stmt = this.dbService.getConnection().prepareStatement(query);
//
//			if (p != null && !price.isEmpty())
//				stmt.setDouble(1, p);
//			ResultSet rs = stmt.executeQuery();
//			return parseResults(rs);
//		} catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Failed to retrieve sodas by restaurant.");
//			ex.printStackTrace();
//			return new ArrayList<SodaByRestaurant>();
//		}
//		PreparedStatement stmt = null;
//		String query = "SELECT Restaurant, Soda, Manufacturer, RestaurantContact, Price \nFROM SodasByRestaurant\n";
//		if(price != null && !price.equals("")) {
//			query += "WHERE price = ?";
//		}
//		try {
//			stmt = dbService.getConnection().prepareStatement(query);
//			if(price != null && !price.equals("")) {
//				stmt.setString(1, price);
//			}
//			ResultSet rs = stmt.executeQuery();
//			return parseResults(rs);
//		}
//		catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Failed to retrieve sodas by restaurant.");
//			ex.printStackTrace();
//			return new ArrayList<SodaByRestaurant>();
//		}
//		PreparedStatement stmt = null;
//		String query = "SELECT Restaurant, Soda, Manufacturer, RestaurantContact, Price \nFROM SodasByRestaurant\n";
//		if( !price.isEmpty() && price != null) {
//			query += "WHERE price = ?";
//		}
//		try {
//			stmt = this.dbService.getConnection().prepareStatement(query);
//			if(!price.isEmpty() && price != null ) {
//				stmt.setString(1, price);
//			}
//			ResultSet rs = stmt.executeQuery();
//			return parseResults(rs);
//		}
//		catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Failed to retrieve sodas by restaurant.");
//			ex.printStackTrace();
//			return new ArrayList<SodaByRestaurant>();
//		}
	}
	
	/**
	 * Creates a string containing ? in the correct places in the SQL statement based on the filter information provided.
	 * 
	 * @param rest - The restaurant to match
	 * @param soda - The soda to match
	 * @param price - The price to compare to
	 * @param useGreaterThanEqual - If true, the prices returned should be greater than or equal to what's given, otherwise less than or equal.
	 * @return A string representing the SQL statement to be executed 
	 */
	private String buildParameterizedSqlStatementString(String rest, String soda, Double price, boolean useGreaterThanEqual) {
		String sqlStatement = "SELECT Restaurant, Soda, Manufacturer, RestaurantContact, Price \nFROM SodasByRestaurant\n";
		ArrayList<String> wheresToAdd = new ArrayList<String>();

		if (rest != null) {
			wheresToAdd.add("Restaurant = ?");
		}
		if (soda != null) {
			wheresToAdd.add("Soda = ?");
		}
		if (price != null) {
			if (useGreaterThanEqual) {
				wheresToAdd.add("Price >= ?");
			} else {
				wheresToAdd.add("Price <= ?");
			}
		}
		boolean isFirst = true;
		while (wheresToAdd.size() > 0) {
			if (isFirst) {
				sqlStatement = sqlStatement + " WHERE " + wheresToAdd.remove(0);
				isFirst = false;
			} else {
				sqlStatement = sqlStatement + " AND " + wheresToAdd.remove(0);
			}
		}
		return sqlStatement;
	}

	private ArrayList<SodaByRestaurant> parseResults(ResultSet rs) {
		try {
			ArrayList<SodaByRestaurant> sodasByRestaurants = new ArrayList<SodaByRestaurant>();
			int restNameIndex = rs.findColumn("Restaurant");
			int sodaNameIndex = rs.findColumn("Soda");
			int manfIndex = rs.findColumn("Manufacturer");
			int restContactIndex = rs.findColumn("RestaurantContact");
			int priceIndex = rs.findColumn("Price");
			while (rs.next()) {
				sodasByRestaurants.add(new SodaByRestaurant(rs.getString(restNameIndex), rs.getString(sodaNameIndex),
						rs.getString(manfIndex), rs.getString(restContactIndex), rs.getDouble(priceIndex)));
			}
			System.out.println(sodasByRestaurants.size());
			return sodasByRestaurants;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"An error ocurred while retrieving sodas by restaurants. See printed stack trace.");
			ex.printStackTrace();
			return new ArrayList<SodaByRestaurant>();
		}

	}


}
