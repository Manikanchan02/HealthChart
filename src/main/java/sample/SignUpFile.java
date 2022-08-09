//package sample;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class SignUpFile {
//	private File signUpFile;
//	
//	public SignUpFile(File signUpFile) {
//		this.signUpFile = signUpFile;
//	}
//	
//	public void saveSignUpRecord(SignUpRecord sr) {
//		boolean flag = signUpFile.exists();
//		
//		try {
//			FileWriter recordWriter = new FileWriter(signUpFile, true);
//			
//			if(!flag) {
//			signUpFile.createNewFile();
//			String header = "Name, User-Name, E-mail, Password, \n";
//			recordWriter.write(header);
//			}
//			
//			String record = String.join(", ", sr.getName(), sr.getUName(), sr.getEmail(), sr.getPass());
//			recordWriter.write(record + "\n");
//			recordWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public List<SignUpRecord> getAllRecords(){
//		if(signUpFile.exists()) {
//			List<SignUpRecord> sUpRecords = new ArrayList<>();
//			
//			try {
//				Scanner scan = new Scanner(signUpFile);
//				scan.nextLine();
//				while (scan.hasNextLine()) {
//					String data = scan.nextLine();
//					String[] sRecord = data.split(", ");
//					
//					SignUpRecord sr = new SignUpRecord();
//					sr.setName(sRecord[0]);
//					sr.setUName(sRecord[1]);
//					sr.setEmail(sRecord[2]);
//					sr.setPass(sRecord[3]);
//					
//					sUpRecords.add(sr);
//				}
//				
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return sUpRecords;
//		} else{
//			return null;
//		}
//	}
//	
//	public String getPasswordByUserID(String userID) {
//		List<SignUpRecord> sr = this.getAllRecords();
//		for (SignUpRecord signUpRecord : sr) {
//			if(signUpRecord.getUName().equals(userID)) {
//				return signUpRecord.getPass();
//			}
//		}
//		return null;
//	}
//}
