import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Pattern;

public class PasswordStrengthIndicator extends JFrame {
    private JTextField passwordField;
    private JLabel strengthLabel;

    public PasswordStrengthIndicator() {
        setTitle("Password Strength Indicator");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        strengthLabel = new JLabel("Password Strength: ");
        passwordField = new JTextField(20);

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateStrength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateStrength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateStrength();
            }
        });

        panel.add(new JLabel("Enter Password: "));
        panel.add(passwordField);
        panel.add(strengthLabel);

        add(panel, BorderLayout.CENTER);
    }

    private void updateStrength() {
        String password = passwordField.getText();
        int strength = calculatePasswordStrength(password);

        if (strength < 3) {
            strengthLabel.setText("Password Strength: Weak");
        } else if (strength < 6) {
            strengthLabel.setText("Password Strength: Medium");
        } else {
            strengthLabel.setText("Password Strength: Strong");
        }
    }

    private int calculatePasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasLowercase = Pattern.compile("[a-z]").matcher(password).find();
        boolean hasDigit = Pattern.compile("\\d").matcher(password).find();
        boolean hasSpecialChar = Pattern.compile("[!@#$%^&*]").matcher(password).find();

        int strength = 0;

        if (length >= 8) {
            strength++;
        }
        if (hasUppercase) {
            strength++;
        }
        if (hasLowercase) {
            strength++;
        }
        if (hasDigit) {
            strength++;
        }
        if (hasSpecialChar) {
            strength++;
        }

        return strength;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordStrengthIndicator indicator = new PasswordStrengthIndicator();
            indicator.setVisible(true);
        });
    }
}

