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
//
//public class HealthFile {
//	private File myFile;
//	private static HealthFile singletonHealthFile;
//	private HealthFile(File myFile) {
//		this.myFile = myFile;
//	}
//	
//	public static HealthFile getHealthFile(File singletonFile) {
//		if(singletonHealthFile == null) {
//			singletonHealthFile = new HealthFile(singletonFile);
//		}
//		return singletonHealthFile;
//	}
//
//	public void saveHealthRecord(HealthRecord hr) {
//		boolean flag = myFile.exists();
//		try {
//			FileWriter recordWriter = new FileWriter(myFile, true);
//			if(!flag) {
//				myFile.createNewFile();
//				String header = "USER-ID, Name, Age, Gender, BpSys, BpDias, Weight, PulseRate, Date \n";
//				recordWriter.write(header);
//			}
//
//			String record = String.join(", ", hr.getUserID(), hr.getName(), String.valueOf(hr.getAge()), hr.getGender(), 
//					String.valueOf(hr.getBpSys()), String.valueOf(hr.getBpDias()), 
//					String.valueOf(hr.getWeight()), String.valueOf(hr.getPulseR()), hr.getDate().toString());
//			recordWriter.write(record + "\n");
//			recordWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public List<HealthRecord> getAllRecords(String userID){
//		if(myFile.exists()) {
//			List<HealthRecord> hRecords = new ArrayList<>();
//
//			try {
//				Scanner scan = new Scanner(myFile);
//				scan.nextLine();
//				while (scan.hasNextLine()) {
//					String data = scan.nextLine();
//					String[] record = data.split(", ");
//					String recordUserID = record[0];
//
//					if(userID.equals(recordUserID)) {
//
//						HealthRecord hr = new HealthRecord();
//
//						hr.setUserID(recordUserID);
//						hr.setName(record[1]);
//						int age = Integer.parseInt(record[2]);
//						hr.setAge(age);
//						hr.setGender(record[3]);
//						int bpSys = Integer.parseInt(record[4]);
//						hr.setBpSys(bpSys);
//						int bpDias = Integer.parseInt(record[5]);
//						hr.setBpDias(bpDias);
//						float weight = Float.parseFloat(record[6]);
//						hr.setWeight(weight);
//						int pRate = Integer.parseInt(record[7]);
//						hr.setPulseR(pRate);
//						hr.setDate(record[8]);
//
//						hRecords.add(hr);
//					}
//				}
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			return hRecords;
//		} else {
//			return null;
//		}
//	}
//}
