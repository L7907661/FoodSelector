package sodabase.services;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

public class Registration {

	public JFrame frame;
	private JTextField textField1111;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField2222;
	private JTextField textField3333;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 699, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel TitleLabel = new JLabel("Food Selector");
		TitleLabel.setBounds(181, 63, 325, 56);
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setFont(new Font("Lucida Handwriting", Font.BOLD, 40));

		JLabel emailLabel = new JLabel("E-mail *");

		JLabel nameLabel = new JLabel("Name");

		JLabel lblNewLabel_2 = new JLabel("Date of Birth");

		JLabel usernameLabel = new JLabel("Username*");

		JLabel lblNewLabel_4 = new JLabel("Nationality");

		JLabel lblNewLabel_5 = new JLabel("Prefered Taste");

		JComboBox comboBox = new JComboBox();

		JComboBox comboBox_1 = new JComboBox();

		textField1111 = new JTextField();
		textField1111.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		textField2222 = new JTextField();
		textField2222.setColumns(10);

		JLabel pwLabel = new JLabel("Password*");

		JButton ButtonR = new JButton("Register!");
		ButtonR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseConnectionService dbcs = new DatabaseConnectionService("golem.csse.rose-hulman.edu", "Food Selector");
				boolean b = dbcs.connect();
				if(b) System.out.println("yes");
				else	System.out.println("no");
				UserService us = new UserService(dbcs);
				us.addUser(textField1111.getText(),Integer.parseInt(textField2222.getText()), textField3333.getText());
			}
		});
		
		textField3333 = new JTextField();
		textField3333.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(173).addComponent(TitleLabel).addContainerGap(114,
						Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(117, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addGap(36)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(nameLabel).addComponent(emailLabel))
										.addGap(18))
								.addGroup(groupLayout
										.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_5).addComponent(lblNewLabel_4))
										.addGap(5)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup().addGap(72)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(139).addComponent(
												textField3333, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup().addGap(95)
												.addComponent(ButtonR))))
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textField1111, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(41)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel_2)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(pwLabel).addComponent(usernameLabel)))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_2, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField2222, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGap(116)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(23).addComponent(TitleLabel).addGap(49)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2)
								.addComponent(textField1111, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(emailLabel))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(usernameLabel)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField2222, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(nameLabel))
						.addGap(41)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(pwLabel)
								.addComponent(textField3333, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4))
						.addGap(51)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(ButtonR)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5))
						.addContainerGap(126, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
