package view;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class LoginInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField gmailField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LoginInterface frame = new LoginInterface();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public LoginInterface() {
		setTitle("SpyAgency - Login/Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        URL urlIconKeylock = getClass().getResource("/icon/setting.png");
        if (urlIconKeylock != null) {
            Image img = Toolkit.getDefaultToolkit().createImage(urlIconKeylock);
            this.setIconImage(img);
        }

		JLabel titleLabel = new JLabel("SpyAgency - Login / Register");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		titleLabel.setBounds(50, 10, 400, 40);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(titleLabel);

		Font labelFont = new Font("Tahoma", Font.PLAIN, 16);

		JLabel userLabel = new JLabel("Username:");
		userLabel.setFont(labelFont);
		userLabel.setBounds(30, 70, 140, 25);
		contentPane.add(userLabel);

		JLabel gmailLabel = new JLabel("Gmail:");
		gmailLabel.setFont(labelFont);
		gmailLabel.setBounds(30, 110, 140, 25);
		contentPane.add(gmailLabel);

		JLabel passLabel = new JLabel("Password:");
		passLabel.setFont(labelFont);
		passLabel.setBounds(30, 150, 140, 25);
		contentPane.add(passLabel);

		JLabel confirmLabel = new JLabel("Confirm Password:");
		confirmLabel.setFont(labelFont);
		confirmLabel.setBounds(30, 190, 160, 25);
		contentPane.add(confirmLabel);

		usernameField = new JTextField();
		usernameField.setFont(labelFont);
		usernameField.setBounds(200, 70, 250, 28);
		contentPane.add(usernameField);

		gmailField = new JTextField();
		gmailField.setFont(labelFont);
		gmailField.setBounds(200, 110, 250, 28);
		contentPane.add(gmailField);

		passwordField = new JPasswordField();
		passwordField.setFont(labelFont);
		passwordField.setBounds(200, 150, 250, 28);
		contentPane.add(passwordField);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setFont(labelFont);
		confirmPasswordField.setBounds(200, 190, 250, 28);
		contentPane.add(confirmPasswordField);

		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		loginButton.setBackground(new Color(0, 153, 76));
		loginButton.setForeground(Color.WHITE);
		loginButton.setBounds(80, 260, 130, 35);
		contentPane.add(loginButton);

		JButton registerButton = new JButton("Register");
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		registerButton.setBackground(new Color(0, 153, 76));
		registerButton.setForeground(Color.WHITE);
		registerButton.setBounds(270, 260, 130, 35);
		contentPane.add(registerButton);
		
		ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/icon/hinhdangky.jpg"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setBounds(0, 0, 500, 400);
        contentPane.add(backgroundLabel);
        
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(50, 205, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(34, 139, 34));
            }
        });
        
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(50, 205, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(34, 139, 34));
            }
        });

		loginButton.addActionListener(e -> {
			LoginInterface2 l = new LoginInterface2();
			l.setVisible(true);
			LoginInterface.this.dispose();
		});

		registerButton.addActionListener(e -> {
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			String confirm = new String(confirmPasswordField.getPassword());
			String gmail = gmailField.getText();

			if (username.isEmpty() || password.isEmpty() || gmail.isEmpty() || confirm.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Fields cannot be empty.");
				return;
			}

			if (!password.equals(confirm)) {
				JOptionPane.showMessageDialog(null, "Password confirmation does not match.");
				return;
			}

			int result = JOptionPane.showConfirmDialog(
				null,
				"Are you sure you want to register this account?",
				"Confirm Registration",
				JOptionPane.OK_CANCEL_OPTION
			);
			if (result != JOptionPane.OK_OPTION) {
				return;
			}

			if (DatabaseUtils.registerUser(username, password, gmail, confirm)) {
				JOptionPane.showMessageDialog(null, "Registration successful!");
			} else {
				JOptionPane.showMessageDialog(null, "Username already exists or another error occurred.");
			}
		});
	}
}
