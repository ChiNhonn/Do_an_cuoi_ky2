package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.border.EmptyBorder;

public class LoginInterface2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginInterface2 frame = new LoginInterface2();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginInterface2() {
        setTitle("SpyAgency - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 350);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        URL urlIconKeylock = getClass().getResource("/icon/keylock_icon.png");
        if (urlIconKeylock != null) {
            Image img = Toolkit.getDefaultToolkit().createImage(urlIconKeylock);
            this.setIconImage(img);
        }

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/icon/hinhnen.jpg"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 720, 350);
        contentPane.add(backgroundLabel);

        JLabel titleLabel = new JLabel("SpyAgency - Login");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(250, 20, 300, 35);
        backgroundLabel.add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        userLabel.setBounds(400, 80, 100, 25);
        backgroundLabel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(520, 80, 180, 30);
        backgroundLabel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passLabel.setBounds(400, 130, 100, 25);
        backgroundLabel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(520, 130, 180, 30);
        backgroundLabel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        loginButton.setBounds(420, 200, 120, 40);
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backgroundLabel.add(loginButton);

        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(50, 205, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(34, 139, 34));
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        registerButton.setBounds(580, 200, 120, 40);
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backgroundLabel.add(registerButton);

        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(50, 205, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(34, 139, 34));
            }
        });

        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty.");
                return;
            }

            if (DatabaseUtils.checkLogin(username, password)) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                Dashboard2 dashboard = new Dashboard2();
                dashboard.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password.");
            }
        });

        registerButton.addActionListener(e -> {
            LoginInterface registerForm = new LoginInterface();
            registerForm.setVisible(true);
            dispose();
        });
    }
}
