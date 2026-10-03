public class ServiceRequest {

    private String requestId;
    private Student student;
    private String serviceType;
    private String description;
    private String status;

    public ServiceRequest(String requestId, Student student, String serviceType, String description) {
        this.requestId = requestId;
        this.student = student;
        this.serviceType = serviceType;
        this.description = description;
        this.status = "Pending";
    }

    public String getRequestId() {
        return requestId;
    }

    public Student getStudent() {
        return student;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}