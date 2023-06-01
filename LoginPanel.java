
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JFrame {

    private JLabel lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnReset;

    // predefined users
    private String[][] users = {
            {"Preetham", "12345"},
            {"Srihas", "12345"},
            {"Sanjay", "12345"},
            {"Dayakar", "12345"}
    };

    public LoginPanel() {
        setTitle("Bus Reservation System");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // set background color
        getContentPane().setBackground(new Color(240, 248, 255));

        // create components
        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        txtUsername = new JTextField(10);
        txtPassword = new JPasswordField(10);
        btnLogin = new JButton("Login");
        btnReset = new JButton("Reset");

        // set label colors
        lblUsername.setForeground(new Color(70, 130, 180));
        lblPassword.setForeground(new Color(70, 130, 180));

        // set button color
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        btnReset.setBackground(new Color(70, 130, 180));
        btnReset.setForeground(Color.WHITE);

        // create panel to hold components
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBackground(new Color(240, 248, 255));

        // add components to panel
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnReset);

        // add panel to frame
        add(panel);

        // add action listeners to buttons
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        setVisible(true);
    }

    // check if user is valid
    private boolean isValidUser(String username, String password) {
        for (String[] user : users) {
            if (user[0].equals(username) && user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    // login method
    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (isValidUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!");
        }
    }

    // reset method
    private void reset() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public static void main(String[] args) {
        new LoginPanel();
    }
}

