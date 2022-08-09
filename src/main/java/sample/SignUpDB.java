package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SignUpDB {
	String url, userName, password;
	SignUpDB(String url, String userName, String password){
		this.url = url;
		this.userName = userName;
		this.password = password;
	}
	
	public void saveSignUpRecord(SignUpRecord sr) throws ClassNotFoundException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(this.url, this.userName, this.password);
			
			PreparedStatement pstmt=con.prepareStatement("insert into SignUP(user_id, name, email_id, password) values(?,?,?,?)");
			pstmt.setString(1, sr.getUName());
			pstmt.setString(2, sr.getName());
			pstmt.setString(3, sr.getEmail());
			pstmt.setString(4, sr.getPass());
			
			int row = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(con != null) {
				con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public String getPasswordByUserID(String userID) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection(this.url, this.userName, this.password);
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select password from SignUP where USER_ID = " + "'" + userID + "'");

			if(rs.next()) {  
				return rs.getString(1);
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(con != null) {
				con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		}
}	
