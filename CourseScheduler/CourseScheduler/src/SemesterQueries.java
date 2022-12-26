
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
public class SemesterQueries {
    
    private static Connection connection;
    private static PreparedStatement addSemester;
    private static PreparedStatement getSemesterList;
    private static ResultSet resultSet;
    
    
    public static void addSemester(String semester) {
        connection = DBConnection.getConnection();
        try {
            addSemester = connection.prepareStatement("insert into app.semester (semester) values (?)");
            addSemester.setString(1, semester);
            addSemester.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<String> getSemesterList() {
        connection = DBConnection.getConnection();
        ArrayList<String> semester = new ArrayList<String>();
        try {
            getSemesterList = connection.prepareStatement("select semester from app.semester order by semester");
            resultSet = getSemesterList.executeQuery();
            
            while (resultSet.next()) {
                semester.add(resultSet.getString(1));
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return semester;
    }
}
