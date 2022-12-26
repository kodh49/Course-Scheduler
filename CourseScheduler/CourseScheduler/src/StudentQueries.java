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
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student) {
        connection = DBConnection.getConnection();
        // SQL Access
        try
        {
            addStudent = connection.prepareStatement("INSERT INTO app.student (StudentID, FirstName, LastName) VALUES (?, ?, ?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents() {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> studentsList = new ArrayList<>();
        // SQL Access
        try
        {
            getAllStudents = connection.prepareStatement("SELECT StudentID, FirstName, LastName FROM app.student ORDER BY StudentID");
            resultSet = getAllStudents.executeQuery();
            
            while (resultSet.next()) {
                studentsList.add(new StudentEntry(
                resultSet.getString("StudentID"),
                resultSet.getString("FirstName"),
                resultSet.getString("LastName"))
                );
            }
            
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentsList;
    }
    
    public static StudentEntry getStudent(String studentID) {
        connection = DBConnection.getConnection();
        String[] studentData = new String[3];
        // SQL Access
        try
        {
            getStudent = connection.prepareStatement("SELECT StudentID, FirstName, LastName FROM app.student WHERE StudentID LIKE (?)");
            getStudent.setString(1, studentID);
            resultSet = getStudent.executeQuery();
            studentData[0] = resultSet.getString("StudentID");
            studentData[1] = resultSet.getString("FirstName");
            studentData[2] = resultSet.getString("LastName");
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        StudentEntry studentInfo = new StudentEntry(studentData[0], studentData[1], studentData[2]);
        return studentInfo;
    }
    
    public static void dropStudent(String studentID) {
        connection = DBConnection.getConnection();
        // Leave it blank for part 1 submission.
    }
    
    
}
