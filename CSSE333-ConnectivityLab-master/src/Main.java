import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sodabase.services.LoginGUI;
import sodabase.services.Registration;
import sodabase.services.UserService;
import sodabase.ui.ApplicationRunner;

public class Main {

	public static void main(String[] args) {
//		ApplicationRunner appRunner = new ApplicationRunner();
//		appRunner.runApplication(args);
		try {
			Connection connection2 = DriverManager.getConnection("jdbc:sqlserver://golem.csse.rose-hulman.edu;databaseName=FoodSelector;user=food19;password={123P}");
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginGUI frame = new LoginGUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
