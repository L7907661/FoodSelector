package sodabase.services;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SearchService {
	
	private DatabaseConnectionService dbService = null;
	
	public SearchService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public ArrayList<String> searchFood(String foodname) {
		try {
			CallableStatement c = dbService.getConnection().prepareCall("{ ? = call Get_Ingredient_by_Food(?)}");
			c.registerOutParameter(1, Types.INTEGER);
			c.setString(2, foodname);
			c.execute();
			int toreturn = c.getInt(1);
			if (toreturn == 1) {
				JOptionPane.showMessageDialog(null, "ERROR:" + toreturn + " Invalid parameters");
				return null;
			}
			ResultSet rs1 = c.executeQuery();
			ArrayList<String> ingredient = new ArrayList<String>();
			String IngName;
			while(rs1.next()){
				IngName = rs1.getString("Name");
				ingredient.add(IngName);
			}
			return ingredient;
			
//			String query = "Select * From Ingredient";
//			ArrayList<String> ingredient = new ArrayList<String>();
//			String IngName;
//			Statement stmt = dbService.getConnection().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while(rs.next()){
//				IngName = rs.getString("Name");
//				ingredient.add(IngName);
//			}
//			return ingredient;

			
			
//			
//			String query = "Select * From Food";
//			ArrayList<String> foods = new ArrayList<String>();
//			String FoodName;
//			Statement stmt = dbService.getConnection().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while(rs.next()){
//				FoodName = rs.getString("Name");
//				foods.add(FoodName);
//			}
//			return foods;

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Search failed.");
		}
		return null;
	}
	
}