package sodabase.services;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.Color;

public class LoginGUI extends JFrame {

	public JPanel mainpanel;
	private JTextField email_text;
	private JTextField username_text;
	private JTextField pw_text;
	private JTextField search_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}
	public LoginGUI() {
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 701);
		mainpanel = new JPanel();
		mainpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainpanel);
		mainpanel.setLayout(null);
		mainpanel.setBackground(Color.WHITE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 834, 640);
		mainpanel.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel loginpanel = new JPanel();
		loginpanel.setBackground(Color.WHITE);
		layeredPane.add(loginpanel, "1");
		SpringLayout panel1 = new SpringLayout();
		loginpanel.setLayout(panel1);
		
		JLabel TitleLabel = new JLabel("Food Selector");
		panel1.putConstraint(SpringLayout.NORTH, TitleLabel, 83, SpringLayout.NORTH, loginpanel);
		panel1.putConstraint(SpringLayout.EAST, TitleLabel, -269, SpringLayout.EAST, loginpanel);
		loginpanel.add(TitleLabel);
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setFont(new Font("Lucida Handwriting", Font.BOLD, 40));
		
		JButton signupButton = new JButton("Sign up");
		panel1.putConstraint(SpringLayout.EAST, signupButton, 7, SpringLayout.EAST, TitleLabel);
		loginpanel.add(signupButton);
		signupButton.setFont(new Font("Sitka Display", Font.PLAIN, 18));
		
				
				JLabel usernameLabel = new JLabel("Username");
				panel1.putConstraint(SpringLayout.NORTH, usernameLabel, 169, SpringLayout.NORTH, loginpanel);
				panel1.putConstraint(SpringLayout.WEST, usernameLabel, 240, SpringLayout.WEST, loginpanel);
				panel1.putConstraint(SpringLayout.SOUTH, TitleLabel, -29, SpringLayout.NORTH, usernameLabel);
				panel1.putConstraint(SpringLayout.WEST, TitleLabel, 0, SpringLayout.WEST, usernameLabel);
				loginpanel.add(usernameLabel);
				usernameLabel.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
				
				JLabel passwordLabel = new JLabel("Password");
				panel1.putConstraint(SpringLayout.NORTH, passwordLabel, 216, SpringLayout.NORTH, loginpanel);
				panel1.putConstraint(SpringLayout.WEST, passwordLabel, 244, SpringLayout.WEST, loginpanel);
				panel1.putConstraint(SpringLayout.EAST, usernameLabel, 0, SpringLayout.EAST, passwordLabel);
				loginpanel.add(passwordLabel);
				passwordLabel.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
				
				JLabel emailLabel = new JLabel("E-mail");
				panel1.putConstraint(SpringLayout.NORTH, emailLabel, 261, SpringLayout.NORTH, loginpanel);
				panel1.putConstraint(SpringLayout.WEST, emailLabel, 275, SpringLayout.WEST, loginpanel);
				panel1.putConstraint(SpringLayout.SOUTH, passwordLabel, -22, SpringLayout.NORTH, emailLabel);
				loginpanel.add(emailLabel);
				emailLabel.setFont(new Font("Lucida Fax", Font.PLAIN, 18));
				
				email_text = new JTextField();
				panel1.putConstraint(SpringLayout.EAST, emailLabel, -42, SpringLayout.WEST, email_text);
				panel1.putConstraint(SpringLayout.WEST, email_text, 372, SpringLayout.WEST, loginpanel);
				panel1.putConstraint(SpringLayout.EAST, email_text, 0, SpringLayout.EAST, TitleLabel);
				loginpanel.add(email_text);
				email_text.setColumns(10);
				
				username_text = new JTextField();
				panel1.putConstraint(SpringLayout.NORTH, username_text, 173, SpringLayout.NORTH, loginpanel);
				panel1.putConstraint(SpringLayout.WEST, username_text, 47, SpringLayout.EAST, usernameLabel);
				panel1.putConstraint(SpringLayout.EAST, username_text, -269, SpringLayout.EAST, loginpanel);
				loginpanel.add(username_text);
				username_text.setColumns(10);
				
				JButton loginButton = new JButton("Log in");
				panel1.putConstraint(SpringLayout.NORTH, loginButton, 62, SpringLayout.SOUTH, emailLabel);
				panel1.putConstraint(SpringLayout.NORTH, signupButton, 0, SpringLayout.NORTH, loginButton);
				panel1.putConstraint(SpringLayout.WEST, signupButton, 122, SpringLayout.EAST, loginButton);
				panel1.putConstraint(SpringLayout.WEST, loginButton, 0, SpringLayout.WEST, emailLabel);
				panel1.putConstraint(SpringLayout.EAST, loginButton, -474, SpringLayout.EAST, loginpanel);
				loginpanel.add(loginButton);
				loginButton.setFont(new Font("Sitka Display", Font.PLAIN, 18));
				

				pw_text = new JTextField();
				panel1.putConstraint(SpringLayout.NORTH, pw_text, 220, SpringLayout.NORTH, loginpanel);
				panel1.putConstraint(SpringLayout.WEST, pw_text, 372, SpringLayout.WEST, loginpanel);
				panel1.putConstraint(SpringLayout.EAST, pw_text, -269, SpringLayout.EAST, loginpanel);
				panel1.putConstraint(SpringLayout.NORTH, email_text, 25, SpringLayout.SOUTH, pw_text);
				panel1.putConstraint(SpringLayout.EAST, passwordLabel, -42, SpringLayout.WEST, pw_text);
				loginpanel.add(pw_text);
				pw_text.setColumns(10);
				
				JPanel homepage = new JPanel();
				homepage.setBackground(Color.WHITE);
				layeredPane.add(homepage, "2");
				SpringLayout sl_panel_1 = new SpringLayout();
				homepage.setLayout(sl_panel_1);
				
				JLabel TitleLabel_1 = new JLabel("Food Selector");
				sl_panel_1.putConstraint(SpringLayout.NORTH, TitleLabel_1, 86, SpringLayout.NORTH, homepage);
				sl_panel_1.putConstraint(SpringLayout.WEST, TitleLabel_1, 240, SpringLayout.WEST, homepage);
				TitleLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				TitleLabel_1.setFont(new Font("Lucida Handwriting", Font.BOLD, 40));
				homepage.add(TitleLabel_1);
				
				search_text = new JTextField();
				sl_panel_1.putConstraint(SpringLayout.NORTH, search_text, 179, SpringLayout.NORTH, homepage);
				sl_panel_1.putConstraint(SpringLayout.WEST, search_text, 222, SpringLayout.WEST, homepage);
				sl_panel_1.putConstraint(SpringLayout.EAST, search_text, 513, SpringLayout.WEST, homepage);
				sl_panel_1.putConstraint(SpringLayout.SOUTH, TitleLabel_1, -37, SpringLayout.NORTH, search_text);
				homepage.add(search_text);
				search_text.setColumns(10);
				
				JButton btnSearch = new JButton("Search");
				sl_panel_1.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, search_text);
				sl_panel_1.putConstraint(SpringLayout.WEST, btnSearch, 29, SpringLayout.EAST, search_text);
				homepage.add(btnSearch);
				
//				String text = getFood().toString();

				JLabel RecFood = new JLabel("recommended food 1 with pic");
				
				sl_panel_1.putConstraint(SpringLayout.NORTH, RecFood, 105, SpringLayout.SOUTH, search_text);
				sl_panel_1.putConstraint(SpringLayout.WEST, RecFood, 165, SpringLayout.WEST, homepage);
				sl_panel_1.putConstraint(SpringLayout.SOUTH, RecFood, 147, SpringLayout.SOUTH, search_text);
				sl_panel_1.putConstraint(SpringLayout.EAST, RecFood, 357, SpringLayout.WEST, homepage);
				homepage.add(RecFood);
				
				JLabel lblRecommendedFood = new JLabel("recommended food 2 with pic");
				sl_panel_1.putConstraint(SpringLayout.NORTH, lblRecommendedFood, 121, SpringLayout.SOUTH, search_text);
				sl_panel_1.putConstraint(SpringLayout.WEST, lblRecommendedFood, 123, SpringLayout.EAST, RecFood);
				homepage.add(lblRecommendedFood);
				
				JLabel lblRecommendedFood_1 = new JLabel("recommended food 3 with pic");
				sl_panel_1.putConstraint(SpringLayout.NORTH, lblRecommendedFood_1, 155, SpringLayout.SOUTH, RecFood);
				sl_panel_1.putConstraint(SpringLayout.WEST, lblRecommendedFood_1, 165, SpringLayout.WEST, homepage);
				homepage.add(lblRecommendedFood_1);
				
				JLabel lblRecommendedFood_2 = new JLabel("recommended food 4 with pic");
				sl_panel_1.putConstraint(SpringLayout.NORTH, lblRecommendedFood_2, 0, SpringLayout.NORTH, lblRecommendedFood_1);
				sl_panel_1.putConstraint(SpringLayout.WEST, lblRecommendedFood_2, 480, SpringLayout.WEST, homepage);
				sl_panel_1.putConstraint(SpringLayout.EAST, lblRecommendedFood_2, 0, SpringLayout.EAST, lblRecommendedFood);
				homepage.add(lblRecommendedFood_2);
				
				
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registration window = new Registration();
				window.frame.setVisible(true);				
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			
			//put login requirements here
			//if
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginpanel.setVisible(false);
				homepage.setVisible(true);
			}
		});
		
		
	}
	
	public ArrayList<String> getFood() {
		//TODO: Task 2
		ArrayList<String> foods = new ArrayList<String>();
		Statement stmt = null;
		String FoodName;
		String query = "Select * From UserwithAllergen";
		try{
			DatabaseConnectionService dbcs = new DatabaseConnectionService("golem.csse.rose-hulman.edu", "Food Selector");
			stmt = dbcs.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				FoodName = rs.getString("name");
				foods.add(FoodName);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return foods;
	}
}
