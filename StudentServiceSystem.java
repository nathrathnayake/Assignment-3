import java.util.ArrayList;

public class StudentServiceSystem {

    private ArrayList<ServiceRequest> requests;
    private int nextRequestNumber;

    public StudentServiceSystem() {
        requests = new ArrayList<>();
        nextRequestNumber = 1;
    }

    public String generateRequestId() {
        String requestId = String.format("REQ%03d", nextRequestNumber);
        nextRequestNumber++;
        return requestId;
    }

    public void addRequest(ServiceRequest request) {
        requests.add(request);
    }

    public ServiceRequest searchByRequestId(String requestId) {

        for (ServiceRequest request : requests) {

            if (request.getRequestId().equalsIgnoreCase(requestId)) {
                return request;
            }
        }

        return null;
    }

    public ServiceRequest searchByStudentId(String studentId) {

        for (ServiceRequest request : requests) {

            if (request.getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                return request;
            }
        }

        return null;
    }

    public boolean updateRequestStatus(String requestId, String newStatus) {

        ServiceRequest request = searchByRequestId(requestId);

        if (request != null) {
            request.setStatus(newStatus);
            return true;
        }

        return false;
    }
}