/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dohyoung
 */
public class CourseEntry {
    private String Semester;
    private String CourseCode;
    private String courseDescription;
    private int Seats;

    public CourseEntry(String Semester, String CourseCode, String Description, int Seats) {
        this.Semester = Semester;
        this.CourseCode = CourseCode;
        this.courseDescription = Description;
        this.Seats = Seats;
    }

    public String getSemester() {
        return Semester;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public int getSeats() {
        return Seats;
    }
}
