package model;

import java.time.LocalDateTime;

/** This is the Appointment.java class. */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;
    private int contactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId){
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId =contactId;
    }

    /**
     * @return the appointmentIdCol
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the appointmentIdCol to set
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the titleCol
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the titleCol to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the descriptionCol
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the descriptionCol to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the locationCol
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the locationCol to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the typeCol
     */
    public String getType() { return type; }

    /**
     * @param type the typeCol to set
     */
    public void setType(String type) { this.type = type; }

    /**
     * @return the startCol
     */
    public LocalDateTime getStart() { return start; }

    /**
     * @param start the startCol to set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return the endCol
     */
    public LocalDateTime getEnd() { return end; }

    /**
     * @param end the endCol to set
     */
    public void setEnd(LocalDateTime end) { this.end = end; }

    /**
     * @return the customerIdCol
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerIdCol to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the userIdCol
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userIdCol to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the contactIdCol
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactIdCol to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}

