package camppackage;
import java.util.Calendar;

public class CampInfo {
    private String campName;
    private Calendar startDate;
    private Calendar endDate;
    private Calendar regEndDate;
    private userGroup userGroup;
    private String location;
    private int totalSlots;
    private int campCommiteeSlots;
    private String description;
    private String staffInCharge;

    public CampInfo(String campName, Calendar startDate, Calendar endDate, Calendar regEndDate, userGroup userGroup, String location, int totalSlots, int campCommiteeSlots, String description, String staffInCharge) {
        this.campName = campName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regEndDate = regEndDate;
        this.userGroup = userGroup;
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

    public void setUserGroup(userGroup userGroup) {
        this.userGroup = userGroup;
    }

    public userGroup getUserGroup() {
        return userGroup;
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
}