package shiftmanager;

public class Shift {
    private String employee;
    private String date;
    private String startTime;
    private String endTime;

    public Shift(String employee, String date, String startTime, String endTime) {
        this.employee = employee;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEmployee() { return employee; }
    public String getDate() { return date; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
}
