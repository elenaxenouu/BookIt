
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * UserDAO provides all the necessary methods related to users.
 * 
 * @author 
 *
 */
public class UserDAO {
		
	/**
	 * This method returns a List with all Users
	 * 
	 * @return List<User>
	 */
	public List<User> getUsers() throws Exception {
				
		List<User> users = new ArrayList<User>();

		DB db = new DB();

		Connection con = null;

		String query = "SELECT * FROM users;";

		try {
			con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				users.add (new User(rs.getString("name"),
				rs.getInt("availableHours"),
				rs.getService("selectedService")
				));
			}
			rs.close();
			stmt.close();
			db.close();
			return users;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
			
		} finally {
			try {
				db.close();
			} catch (Exception e) {

			}
		}
		
		
	} //End of getUsers

	/**
	 * Search user by username
	 * 
	 * @param username, String
	 * @return User, the User object or null
	 * @throws Exception
	 */
	public User findUser(String username) throws Exception {
		DB db = new DB();
		Connection con = null;
		String query = "SELECT * FROM users_ex3_8220148_2024_2025 WHERE username=?;";
		try {
			con = db.getConnection();  
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				rs.close();
				stmt.close();
				db.close();
				return null;
			}
			User user = new User(rs.getString("firstname"),
			rs.getString("lastname"),
			rs.getString("email"),
			rs.getString("username"),
			rs.getString("password"));
			rs.close();
			stmt.close();
			db.close();
			return user;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally {
			try {
				db.close();
			}catch (Exception e) {
				
			}
		}
		
	} //End of findUser

	/**
	 * This method is used to authenticate a user.
	 * 
	 * @param username, String
	 * @param password, String
	 * @return User, the User object
	 * @throws Exception, if the credentials are not valid
	 */
	public User authenticate(String username, String password) throws Exception {
		DB db = new DB();
		Connection con = null;
		String query = "SELECT * FROM users WHERE username=? AND password=?;";
		try {
			con = db.getConnection();  
			PreparedStatement stmt =con.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				rs.close();
				stmt.close();
				db.close();
				throw new Exception("Wrong username or password");
			}
			User user = new User(rs.getString("name"),
			rs.getString("password"));

			rs.close();
			stmt.close();
			db.close();
			return user;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally {
			try {
				db.close();
			}catch (Exception e) {
				
			}
		}	
		
			
		
	} //End of authenticate
	
	

} //End of class
