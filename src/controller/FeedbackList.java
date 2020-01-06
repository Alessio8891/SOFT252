package controller;

import data.Data;
import data.Serialise;
import data.User;
import system.Feedback;
import user.AbstractUser;
import user.Doctor;
import user.Patient;
import view.ComboItem;
import view.GUI;
import view.ListItem;

import javax.swing.*;

public class FeedbackList {
    public static void main(GUI GUI) {
        char authority = User.getUser().getAuthority();

        switch (authority) {
            case 'S':
                GUI.getNewFeedbackButton().setVisible(false);
                GUI.getEditFeedbackButton().setVisible(true);
                GUI.getSubmitFeedbackButton().setVisible(true);
                GUI.getDeleteSelectedFeedbackButton().setVisible(true);

                GUI.getFeedbackTextArea().setEnabled(true);
                GUI.getFeedbackRatingSlider().setEnabled(true);
                GUI.getFeedbackDoctorSelectCombo().setEnabled(false);
                GUI.getFeedbackApprovedCheckBox().setEnabled(true);
                break;
            case 'P':
                GUI.getNewFeedbackButton().setVisible(true);
                GUI.getEditFeedbackButton().setVisible(true);
                GUI.getSubmitFeedbackButton().setVisible(true);
                GUI.getDeleteSelectedFeedbackButton().setVisible(true);

                GUI.getFeedbackTextArea().setEnabled(true);
                GUI.getFeedbackRatingSlider().setEnabled(true);
                GUI.getFeedbackDoctorSelectCombo().setEnabled(true);
                GUI.getFeedbackApprovedCheckBox().setEnabled(false);
                break;
            case 'D':
                GUI.getNewFeedbackButton().setVisible(false);
                GUI.getEditFeedbackButton().setVisible(false);
                GUI.getSubmitFeedbackButton().setVisible(false);
                GUI.getDeleteSelectedFeedbackButton().setVisible(false);

                GUI.getFeedbackTextArea().setEnabled(false);
                GUI.getFeedbackRatingSlider().setEnabled(false);
                GUI.getFeedbackDoctorSelectCombo().setEnabled(false);
                GUI.getFeedbackApprovedCheckBox().setEnabled(false);
                break;
        }

        populateDoctorSelectCombo(GUI);
        populate(GUI.getFeedbackList());
        clearFeedback(GUI);
    }

    public static void populate(JList feedbackList) {
        char authority = User.getUser().getAuthority();
        int id = User.getUser().getId();

        DefaultListModel feedbackListModel = new DefaultListModel();
        feedbackList.setModel(feedbackListModel);

        for (int i = 0; i < Data.getData().getFeedback().size(); i++) {
            Feedback feedback = Data.getData().getFeedback().get(i);

            Doctor doctor = feedback.getDoctor();
            Patient patient = feedback.getPatient();
            int rating = feedback.getRating();
            String text = feedback.getFeedback();

            // only display max first 20 characters of feedback
            String shortText;
            if (text.length() > 20) {
                shortText = text.substring(0, 20);
            } else {
                shortText = text;
            }

            String element;
            // only display patients personal feedback //
            if (authority == 'P' && patient.getId() == id) {
                element = String.format("Doctor: %s, Rating: %s, Feedback: %s", doctor.getName(), rating, shortText);
                feedbackListModel.addElement(new ListItem(element, feedback));
            }
            // only display doctors own feedback //
            else if (authority == 'D' && doctor.getId() == id && feedback.getApproved() == true) {
                element = String.format("Patient: %s, Rating: %s, Feedback: %s", patient.getName(), rating, shortText);
                feedbackListModel.addElement(new ListItem(element, feedback));
            }
            // display all feedback to secretaries //
            else if (authority == 'S') {
                element = String.format("Doctor: %s, Patient: %s, Rating: %s, Feedback: %s", doctor.getName(), patient.getName(), rating, shortText);
                feedbackListModel.addElement(new ListItem(element, feedback));
            }
        }
    }

    public static void clearFeedback(GUI GUI) {
        GUI.getFeedbackApprovedCheckBox().setSelected(false);
        GUI.getFeedbackRatingSlider().setValue(0);
        GUI.getFeedbackTextArea().setText("");
    }

    public static void populateDoctorSelectCombo(GUI GUI) {
        GUI.getFeedbackDoctorSelectCombo().removeAllItems();
        for (int i = 0; i < Data.getData().getUser().size(); i++) {
            AbstractUser user = Data.getData().getUser().get(i);

            if (user.getAuthority() == 'D') {
                GUI.getFeedbackDoctorSelectCombo().addItem(new ComboItem(user.getName(), user));
            }
        }
    }

    public static void loadFeedback(GUI GUI) {
        ListItem selectedFeedback = (ListItem) GUI.getFeedbackList().getSelectedValue();

        if (selectedFeedback == null) {
            return;
        }

        Feedback feedback = (Feedback) selectedFeedback.getValue();

        GUI.getFeedbackTextArea().setText(feedback.getFeedback());
        GUI.getFeedbackDoctorSelectCombo().setSelectedItem(feedback.getDoctor());
        GUI.getFeedbackRatingSlider().setValue(feedback.getRating());
        GUI.getFeedbackApprovedCheckBox().setSelected(feedback.getApproved());
    }

    public static void newFeedback(GUI GUI) {
        String feedbackText = GUI.getFeedbackTextArea().getText();
        int feedbackRating = GUI.getFeedbackRatingSlider().getValue();

        // if user is doctor, only item in list will be their object
        ListItem selectedDoctor = (ListItem) GUI.getFeedbackDoctorSelectCombo().getSelectedItem();
        Doctor doctor = (Doctor) selectedDoctor.getValue();

        // only patients can create new feedback //
        Patient patient = Data.getData().findPatient(User.getUser().getId());

        Feedback feedback = new Feedback(doctor, patient, feedbackRating, feedbackText);

        Data.getData().getFeedback().add(feedback);

        clearFeedback(GUI);
        populate(GUI.getFeedbackList());
    }

    public static void editFeedback(GUI GUI) {
        // get selected feedback //
        ListItem selectedFeedback = (ListItem) GUI.getFeedbackList().getSelectedValue();
        Feedback feedback = (Feedback) selectedFeedback.getValue();

        int feedbackIndex = Data.getData().getFeedback().indexOf(feedback);

        Data.getData().getFeedback().get(feedbackIndex).setFeedback(GUI.getFeedbackTextArea().getText());
        Data.getData().getFeedback().get(feedbackIndex).setApproved(GUI.getFeedbackApprovedCheckBox().isSelected());
        Data.getData().getFeedback().get(feedbackIndex).setRating(GUI.getFeedbackRatingSlider().getValue());
        Serialise.serialise();

        clearFeedback(GUI);
        populate(GUI.getFeedbackList());
    }

    public static void deleteFeedback(GUI GUI) {
        ListItem selectedFeedback = (ListItem) GUI.getFeedbackList().getSelectedValue();
        Feedback feedback = (Feedback) selectedFeedback.getValue();

        int feedbackIndex = Data.getData().getFeedback().indexOf(feedback);

        Data.getData().getFeedback().remove(feedbackIndex);
        Serialise.serialise();

        clearFeedback(GUI);
        populate(GUI.getFeedbackList());
    }
}
