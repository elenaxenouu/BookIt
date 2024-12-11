import java.sql.*;
public class CustomersDAO {
        public void insertCustomer(User user) throws DuplicateFieldException, Exception {
            DB db = new DB();
            Connection con = null;
            String sql = "INSERT INTO customers (fullname,service_number,Availability) VALUES (?,?,?);";
            try {
                
                con = db.getConnection();
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, user.getName());
                smt.setString(2, user.getAvailableHours());
                smt.setService(3, user.getSelectedService());
                smt.executeUpdate();
                smt.close();
                db.close();
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            } finally {
                try {
                db.close();   
                } catch (Exception e) {
                    
                }           
                
            }
        }
}