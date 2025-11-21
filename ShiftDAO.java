package shiftmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ShiftDAO {

    public boolean addShift(String emp, String date, String start, String end) {
        try {
            Connection con = DBConnect.getConnection();
            
            // ✅ Make sure your table name and columns MATCH MySQL
            String sql = "INSERT INTO shifts (employee, date, start, end) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, emp);
            pst.setString(2, date);
            pst.setString(3, start);
            pst.setString(4, end);

            int rows = pst.executeUpdate();  // ✅ insert executes here
            pst.close();
            con.close();

            return rows > 0; // ✅ return true if inserted
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
