import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dohyoung
 */
public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement getAllCourses;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement dropCourse;
    private static ResultSet resultSet;
    
    public static ArrayList<CourseEntry> getAllCourses(String semester) {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<>();
        // Access SQL Database
        try {
            getAllCourses = connection.prepareStatement("SELECT CourseCode, Description, Seats FROM app.course WHERE semester LIKE (?) ORDER BY CourseCode");
            getAllCourses.setString(1, semester);
            resultSet = getAllCourses.executeQuery();
            
            while (resultSet.next()) {
                CourseEntry courseData = new CourseEntry(
                    semester,
                    resultSet.getString("CourseCode"),
                    resultSet.getString("Description"),
                    resultSet.getInt("Seats")
                );
                courses.add(courseData);
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
    }
    
    public static void addCourse(CourseEntry course) {
        connection = DBConnection.getConnection();
        // Access SQL Database
        try
        {
            addCourse = connection.prepareStatement("INSERT INTO app.course (Semester, CourseCode, Description, Seats) VALUES (?, ?, ?, ?)");
            // Add value
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getCourseDescription());
            addCourse.setInt(4, course.getSeats());
            addCourse.executeUpdate(); // Execute Query
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester) {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<>();
        // Access SQL Database
        try
        {
            getAllCourseCodes = connection.prepareStatement("SELECT CourseCode FROM app.course WHERE semester LIKE (?) ORDER BY CourseCode");
            getAllCourseCodes.setString(1, semester);
            resultSet = getAllCourseCodes.executeQuery();
            
            while (resultSet.next()) {
                courseCodes.add(resultSet.getString("CourseCode"));
            }
            
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
    }
    
    public static int getCourseSeats(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        int courseSeats = 0;
        // Access SQL Database
        try
        {
            getCourseSeats = connection.prepareStatement("SELECT Seats FROM app.course WHERE Semester LIKE (?) AND CourseCode LIKE (?) ORDER BY CourseCode");
            getCourseSeats.setString(1, semester);
            getCourseSeats.setString(2, courseCode);
            resultSet = getCourseSeats.executeQuery();
            
            if (resultSet.next()) {
                courseSeats = resultSet.getInt(1);
            }
            
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseSeats;
    }
    
    public static void dropCourse(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        // Leave it blank for part 1 submission.
    }
}
