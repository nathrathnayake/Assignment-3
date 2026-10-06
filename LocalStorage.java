import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class LocalStorage {

    private static final String FILE_NAME = "student_requests.txt";

    public static StudentServiceSystem loadData() {

        StudentServiceSystem system = new StudentServiceSystem();
        Path file = Path.of(FILE_NAME);

        if (!Files.exists(file)) {
            return system;
        }

        int highestRequestNumber = 0;

        try {
            List<String> lines = Files.readAllLines(file);

            for (String line : lines) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\t");

                if (parts.length != 8) {
                    continue;
                }

                String requestId = decode(parts[0]);
                String studentId = decode(parts[1]);
                String name = decode(parts[2]);
                String email = decode(parts[3]);
                String contact = decode(parts[4]);
                String serviceType = decode(parts[5]);
                String description = decode(parts[6]);
                String status = decode(parts[7]);

                Student student = new Student(
                        studentId,
                        name,
                        email,
                        contact
                );

                ServiceRequest request = new ServiceRequest(
                        requestId,
                        student,
                        serviceType,
                        description
                );

                request.setStatus(status);

                system.addRequest(request);

                if (requestId.startsWith("REQ")) {

                    try {
                        int number =
                                Integer.parseInt(
                                        requestId.substring(3)
                                );

                        if (number > highestRequestNumber) {
                            highestRequestNumber = number;
                        }

                    } catch (NumberFormatException ignored) {
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Could not load saved requests: "
                            + e.getMessage()
            );
        }

        for (int i = 0; i < highestRequestNumber; i++) {
            system.generateRequestId();
        }

        return system;
    }


    public static void saveRequest(ServiceRequest request) {

        Path file = Path.of(FILE_NAME);

        try {
            Files.writeString(
                    file,
                    createLine(request) + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            System.out.println(
                    "Could not save request: "
                            + e.getMessage()
            );
        }
    }


    public static void updateRequest(ServiceRequest request) {

        Path file = Path.of(FILE_NAME);

        if (!Files.exists(file)) {
            saveRequest(request);
            return;
        }

        try {
            List<String> lines =
                    new ArrayList<>(Files.readAllLines(file));

            boolean found = false;

            for (int i = 0; i < lines.size(); i++) {

                String[] parts = lines.get(i).split("\t");

                if (parts.length != 8) {
                    continue;
                }

                String requestId = decode(parts[0]);

                if (requestId.equals(request.getRequestId())) {

                    lines.set(i, createLine(request));
                    found = true;
                    break;
                }
            }

            if (!found) {
                lines.add(createLine(request));
            }

            Files.write(file, lines);

        } catch (IOException e) {
            System.out.println(
                    "Could not update request: "
                            + e.getMessage()
            );
        }
    }


    private static String createLine(ServiceRequest request) {

        Student student = request.getStudent();

        return String.join(
                "\t",
                encode(request.getRequestId()),
                encode(student.getStudentId()),
                encode(student.getName()),
                encode(student.getEmail()),
                encode(student.getContactNumber()),
                encode(request.getServiceType()),
                encode(request.getDescription()),
                encode(request.getStatus())
        );
    }


    private static String encode(String value) {

        return Base64.getEncoder().encodeToString(
                value.getBytes(StandardCharsets.UTF_8)
        );
    }


    private static String decode(String value) {

        return new String(
                Base64.getDecoder().decode(value),
                StandardCharsets.UTF_8
        );
    }
}