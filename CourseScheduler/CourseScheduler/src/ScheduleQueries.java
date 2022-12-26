import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dohyoung
 */
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement scheduleSeats;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry entry) {
        connection = DBConnection.getConnection();
        try
        {
            connection.setAutoCommit(false); // manually control commit for transactions
            // Insert ScheduleEntry into Schedule table
            addScheduleEntry = connection.prepareStatement("INSERT INTO app.schedule (Semester, CourseCode, StudentID, Status, Timestamp) VALUES (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getCourseCode());
            addScheduleEntry.setString(3, entry.getStudentID());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
            // Update number of seats in Course table
            scheduleSeats = connection.prepareStatement("UPDATE app.course SET seats = seats - 1 WHERE semester LIKE (?) AND CourseCode LIKE (?)");
            scheduleSeats.setString(1, entry.getSemester());
            scheduleSeats.setString(2, entry.getCourseCode());
            scheduleSeats.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true); // return to default commit settings
        }
        catch (SQLException sqlException)
        {
            if (connection != null) {
                try {
                    connection.rollback(); // abort transaction
                }
                catch (SQLException e) {
                    sqlException.printStackTrace();
            }
            }
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID) {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules = new ArrayList<>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("SELECT CourseCode, Status, Timestamp FROM app.schedule WHERE Semester LIKE (?) AND StudentID LIKE (?)");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while (resultSet.next()) {
                schedules.add(new ScheduleEntry(
                        semester,
                        resultSet.getString("CourseCode"),
                        studentID,
                        resultSet.getString("Status"),
                        resultSet.getTimestamp("Timestamp")
                ));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedules;
    }
    
    public static int getScheduledStudentCount(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        int count = 0; // initialize
        try
        {
            getScheduleByStudent = connection.prepareStatement("SELECT count(StudentID) FROM app.schedule WHERE Semester LIKE (?) AND CourseCode LIKE (?)");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, courseCode);
            resultSet = getScheduleByStudent.executeQuery();
            // Store output
            count = resultSet.getInt(1);
            
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules = new ArrayList<>();
        // Leave it blank for part 1 submission
        return schedules;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules = new ArrayList<>();
        // Leave it blank for part 1 submission
        return schedules;
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode) {
        // Leave it blank for part 1 submission
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode) {
        // Leave it blank for part 1 submission
    }
    
    public static void updateScheduleEntry(String semester, ScheduleEntry entry) {
        // Leave it blank for part 1 submission
    }
    
}
