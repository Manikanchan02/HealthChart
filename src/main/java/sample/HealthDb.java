package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HealthDb {
	String url, userName, password;
	HealthDb(String url, String userName, String password){
		this.url = url;
		this.userName = userName;
		this.password = password;
	}
	public void saveHealthRecord(HealthRecord hr) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(this.url, this.userName, this.password);
			PreparedStatement pstmt=con.prepareStatement("insert into Health(user_id, name, age, gender, bp_sys, bp_dias, weight, pulse_rate) values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, hr.getUserID());
			pstmt.setString(2, hr.getName());
			pstmt.setInt(3, hr.getAge());
			pstmt.setString(4, hr.getGender());
			pstmt.setInt(5, hr.getBpSys());
			pstmt.setInt(6, hr.getBpDias());
			pstmt.setFloat(7, hr.getWeight());
			pstmt.setInt(8, hr.getPulseR());
			
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
	
	public List<HealthRecord> getAllRecords(String userID){
		List<HealthRecord> records = new ArrayList<HealthRecord>();
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection(this.url, this.userName, this.password);
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from health where USER_ID = " + "'" + userID + "' order by date desc");
			
			while(rs.next()) {  
				HealthRecord hr = new HealthRecord();
				hr.setUserID(rs.getString(2));
				hr.setName(rs.getString(3));
				hr.setAge(rs.getInt(4));
				hr.setGender(rs.getString(5));
				hr.setBpSys(rs.getInt(6));
				hr.setBpDias(rs.getInt(7));
				hr.setWeight(rs.getFloat(8));
				hr.setPulseR(rs.getInt(9));
				hr.setDate(rs.getString(10));
				
				records.add(hr);
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
		return records;
	}
}
