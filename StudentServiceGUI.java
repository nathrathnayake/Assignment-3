import java.awt.*;
import javax.swing.*;

public class StudentServiceGUI extends JFrame {

    private StudentServiceSystem system;

    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField contactField;

    private JComboBox<String> serviceTypeBox;
    private JTextArea descriptionArea;

    private JButton submitButton;

    private JTextField searchField;
    private JButton searchButton;

    private JTextArea resultArea;

    private JComboBox<String> statusBox;
    private JButton updateStatusButton;

    public StudentServiceGUI() {

        system = new StudentServiceSystem();

        setTitle("Student Service Management System");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        // =========================
        // STUDENT DETAILS PANEL
        // =========================

        JPanel studentPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        studentPanel.setBorder(
            BorderFactory.createTitledBorder("Student Details")
        );

        studentIdField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        contactField = new JTextField();

        studentPanel.add(new JLabel("Student ID:"));
        studentPanel.add(studentIdField);

        studentPanel.add(new JLabel("Name:"));
        studentPanel.add(nameField);

        studentPanel.add(new JLabel("Email:"));
        studentPanel.add(emailField);

        studentPanel.add(new JLabel("Contact Number:"));
        studentPanel.add(contactField);

        add(studentPanel, BorderLayout.NORTH);


        // =========================
        // REQUEST PANEL
        // =========================

        JPanel requestPanel = new JPanel();
        requestPanel.setLayout(
            new BoxLayout(requestPanel, BoxLayout.Y_AXIS)
        );

        requestPanel.setBorder(
            BorderFactory.createTitledBorder("Service Request")
        );

        String[] serviceTypes = {
            "Academic Enquiry",
            "IT Support",
            "Assessment Support",
            "General Enquiry"
        };

        serviceTypeBox = new JComboBox<>(serviceTypes);

        JPanel typePanel = new JPanel(new FlowLayout());

        typePanel.add(new JLabel("Service Type:"));
        typePanel.add(serviceTypeBox);

        descriptionArea = new JTextArea(6, 40);

        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JScrollPane descriptionScroll =
            new JScrollPane(descriptionArea);

        submitButton = new JButton("Submit Request");

        requestPanel.add(typePanel);
        requestPanel.add(new JLabel("Request Description:"));
        requestPanel.add(descriptionScroll);
        requestPanel.add(submitButton);

        add(requestPanel, BorderLayout.CENTER);


        // =========================
        // SEARCH / UPDATE PANEL
        // =========================

        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(
            new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)
        );

        bottomPanel.setBorder(
            BorderFactory.createTitledBorder("Search and Update")
        );

        JPanel searchPanel = new JPanel(new FlowLayout());

        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        searchPanel.add(
            new JLabel("Student ID or Request ID:")
        );

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        resultArea = new JTextArea(8, 40);

        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane resultScroll =
            new JScrollPane(resultArea);

        String[] statuses = {
            "Pending",
            "In Progress",
            "Resolved"
        };

        statusBox = new JComboBox<>(statuses);

        updateStatusButton =
            new JButton("Update Status");

        JPanel statusPanel = new JPanel(new FlowLayout());

        statusPanel.add(new JLabel("Status:"));
        statusPanel.add(statusBox);
        statusPanel.add(updateStatusButton);

        bottomPanel.add(searchPanel);
        bottomPanel.add(resultScroll);
        bottomPanel.add(statusPanel);

        add(bottomPanel, BorderLayout.SOUTH);


        // =========================
        // BUTTON ACTIONS
        // =========================

        submitButton.addActionListener(e -> {

            // TODO:
            // 1. Read student information
            // 2. Validate the fields
            // 3. Create Student object
            // 4. Generate Request ID
            // 5. Create ServiceRequest object
            // 6. Add request to system
            // 7. Show success message

        });

        searchButton.addActionListener(e -> {

            // TODO:
            // Search using Student ID or Request ID
            // Display result in resultArea

        });

        updateStatusButton.addActionListener(e -> {

            // TODO:
            // Update selected request status

        });


        setVisible(true);
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new StudentServiceGUI();

        });
    }
}