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

    private ServiceRequest selectedRequest;


    public StudentServiceGUI() {

        system = new StudentServiceSystem();

        setTitle("Student Service Management System");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));


        // STUDENT DETAILS

        JPanel studentPanel =
                new JPanel(new GridLayout(4, 2, 10, 10));

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


        // SERVICE REQUEST

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

        JPanel serviceTypePanel =
                new JPanel(new FlowLayout());

        serviceTypePanel.add(
                new JLabel("Service Type:")
        );

        serviceTypePanel.add(serviceTypeBox);

        descriptionArea =
                new JTextArea(6, 40);

        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JScrollPane descriptionScroll =
                new JScrollPane(descriptionArea);

        submitButton =
                new JButton("Submit Request");

        JPanel submitPanel =
                new JPanel(new FlowLayout());

        submitPanel.add(submitButton);

        requestPanel.add(serviceTypePanel);
        requestPanel.add(
                new JLabel("Request Description:")
        );
        requestPanel.add(descriptionScroll);
        requestPanel.add(submitPanel);

        add(requestPanel, BorderLayout.CENTER);


        // SEARCH AND UPDATE

        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(
                new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)
        );

        bottomPanel.setBorder(
                BorderFactory.createTitledBorder("Search and Update")
        );

        JPanel searchPanel =
                new JPanel(new FlowLayout());

        searchField =
                new JTextField(20);

        searchButton =
                new JButton("Search");

        searchPanel.add(
                new JLabel("Student ID or Request ID:")
        );

        searchPanel.add(searchField);
        searchPanel.add(searchButton);


        resultArea =
                new JTextArea(8, 40);

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

        statusBox =
                new JComboBox<>(statuses);

        updateStatusButton =
                new JButton("Update Status");

        JPanel statusPanel =
                new JPanel(new FlowLayout());

        statusPanel.add(
                new JLabel("Status:")
        );

        statusPanel.add(statusBox);
        statusPanel.add(updateStatusButton);


        bottomPanel.add(searchPanel);
        bottomPanel.add(resultScroll);
        bottomPanel.add(statusPanel);

        add(bottomPanel, BorderLayout.SOUTH);


        // SUBMIT REQUEST BUTTON

        submitButton.addActionListener(e -> {

            String studentId =
                    studentIdField.getText().trim();

            String name =
                    nameField.getText().trim();

            String email =
                    emailField.getText().trim();

            String contact =
                    contactField.getText().trim();

            String description =
                    descriptionArea.getText().trim();

            String serviceType =
                    serviceTypeBox
                            .getSelectedItem()
                            .toString();


            // Check empty fields

            if (studentId.isEmpty()
                    || name.isEmpty()
                    || email.isEmpty()
                    || contact.isEmpty()
                    || description.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please complete all fields.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // Basic email validation

            if (!email.contains("@")
                    || !email.contains(".")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid email address.",
                        "Invalid Email",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // Contact validation

            String contactNumbers =
                    contact.replaceAll("\\s", "");

            if (!contactNumbers.matches("\\d+")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Contact number must contain numbers only.",
                        "Invalid Contact Number",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // Create student

            Student student =
                    new Student(
                            studentId,
                            name,
                            email,
                            contact
                    );


            // Generate request ID

            String requestId =
                    system.generateRequestId();


            // Create service request

            ServiceRequest request =
                    new ServiceRequest(
                            requestId,
                            student,
                            serviceType,
                            description
                    );


            // Store request

            system.addRequest(request);


            // Show confirmation

            JOptionPane.showMessageDialog(
                    this,
                    "Request submitted successfully!"
                            + "\nRequest ID: "
                            + requestId
            );


            // Display submitted request

            selectedRequest = request;

            displayRequest(request);


            // Clear input fields

            studentIdField.setText("");
            nameField.setText("");
            emailField.setText("");
            contactField.setText("");
            descriptionArea.setText("");
        });


        // SEARCH BUTTON

        searchButton.addActionListener(e -> {

            String searchText =
                    searchField.getText().trim();


            if (searchText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a Student ID or Request ID.",
                        "Search Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // First search using Request ID

            selectedRequest =
                    system.searchByRequestId(searchText);


            // If not found, try Student ID

            if (selectedRequest == null) {

                selectedRequest =
                        system.searchByStudentId(searchText);
            }


            // Nothing found

            if (selectedRequest == null) {

                resultArea.setText(
                        "No matching request found."
                );

                JOptionPane.showMessageDialog(
                        this,
                        "No matching request was found.",
                        "Not Found",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }


            displayRequest(selectedRequest);
        });


        // UPDATE STATUS BUTTON

        updateStatusButton.addActionListener(e -> {

            if (selectedRequest == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please search for a request first.",
                        "No Request Selected",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            String newStatus =
                    statusBox
                            .getSelectedItem()
                            .toString();


            boolean updated =
                    system.updateRequestStatus(
                            selectedRequest.getRequestId(),
                            newStatus
                    );


            if (updated) {

                JOptionPane.showMessageDialog(
                        this,
                        "Request status updated successfully."
                );

                displayRequest(selectedRequest);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to update request.",
                        "Update Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        setVisible(true);
    }


    // DISPLAY REQUEST METHOD

    private void displayRequest(
            ServiceRequest request) {

        Student student =
                request.getStudent();

        resultArea.setText(
                "Request ID: "
                        + request.getRequestId()
                        + "\n"
                        +
                "Student ID: "
                        + student.getStudentId()
                        + "\n"
                        +
                "Name: "
                        + student.getName()
                        + "\n"
                        +
                "Email: "
                        + student.getEmail()
                        + "\n"
                        +
                "Contact Number: "
                        + student.getContactNumber()
                        + "\n"
                        +
                "Service Type: "
                        + request.getServiceType()
                        + "\n"
                        +
                "Description: "
                        + request.getDescription()
                        + "\n"
                        +
                "Status: "
                        + request.getStatus()
        );


        statusBox.setSelectedItem(
                request.getStatus()
        );
    }


    // MAIN METHOD

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new StudentServiceGUI();

        });
    }
}