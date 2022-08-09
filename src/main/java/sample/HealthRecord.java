package sample;

public class HealthRecord{
	 
	String name, gender, userID;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	int age, bpSys, bpDias, pulseR;
	float weight;
	String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getBpSys() {
		return bpSys;
	}
	public void setBpSys(int bpSys) {
		this.bpSys = bpSys;
	}
	public int getBpDias() {
		return bpDias;
	}
	public void setBpDias(int bpDias) {
		this.bpDias = bpDias;
	}
	public int getPulseR() {
		return pulseR;
	}
	public void setPulseR(int pulseR) {
		this.pulseR = pulseR;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		
		return "User-ID = "+userID +", Name = " + name +", Age = " + age +", Gender = " + gender+", Blood Pressure = "+bpSys +"/"+ bpDias +", Weight = " + weight +", Pulse Rate = " + pulseR;
	}
	
	
}
