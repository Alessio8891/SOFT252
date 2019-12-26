package system;

import user.Doctor;

import java.io.Serializable;

/**
 * @author zacharysmith
 */
public class Feedback implements Serializable {

    private Doctor doctor;
    private int rating;
    private String feedback;
    private boolean approved;

    public Feedback(Doctor doctor, int rating, String feedback) {
        this.doctor = doctor;
        this.rating = rating;
        this.feedback = feedback;
        this.approved = false;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        // feedback may be altered if obscene or overly harsh.
        this.feedback = feedback;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        // once approved, available for doctor to view
        this.approved = approved;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public int getRating() {
        return rating;
    }


}
