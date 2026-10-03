import javax.swing.*;

public class StudentServiceGUI extends JFrame {

    private StudentServiceSystem system;

    public StudentServiceGUI() {

        system = new StudentServiceSystem();

        setTitle("Student Service Management System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StudentServiceGUI();
        });
    }
}