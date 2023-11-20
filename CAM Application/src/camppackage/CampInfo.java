package camppackage;
import java.util.Calendar;
import camppackage.*;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

import user.Faculty;

public class CampInfo {
    private String campName;
    private Calendar startDate;
    private Calendar endDate;
    private Calendar regEndDate;
    private Faculty faculty;
    private String location;
    private int totalSlots;
    private int campCommiteeSlots;
    private String description;
    private String staffInCharge;

    public CampInfo(String campName, Calendar startDate, Calendar endDate, Calendar regEndDate, Faculty faculty, String location, int totalSlots, int campCommiteeSlots, String description, String staffInCharge) {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regEndDate = regEndDate;
        this.faculty = faculty;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommiteeSlots = campCommiteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampName() {
        return campName;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setRegEndDate(Calendar regEndDate) {
        this.regEndDate = regEndDate;
    }

    public Calendar getRegEndDate() {
        return regEndDate;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setCampCommiteeSlots(int campCommiteeSlots) {
        this.campCommiteeSlots = campCommiteeSlots;
    }

    public int getCampCommiteeSlots() {
        return campCommiteeSlots;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public String toString() {
        return  "Camp Name: " + campName +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate +
                "\nRegistration End Date: " + regEndDate +
                "\nFaculty: " + faculty +
                "\nLocation: " + location +
                "\nTotal Slots: " + totalSlots +
                "\nCamp Commitee Slots: " + campCommiteeSlots +
                "\nDescription: " + description +
                "\nStaff in charge: " + staffInCharge;
    }
}