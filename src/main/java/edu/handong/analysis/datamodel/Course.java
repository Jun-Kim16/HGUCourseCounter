package edu.handong.analysis.datamodel;

public class Course {
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;
	
	public Course(String line) {
		studentId=line.split(",")[0].trim().replace(",", "");
		yearMonthGraduated=line.split(",")[1].trim().replace(",", "");
		firstMajor=line.split(",")[2].trim().replace(",", "");
		secondMajor=line.split(",")[3].trim().replace(",", "");
		courseCode=line.split(",")[4].trim().replace(",", "");
		courseName=line.split(",")[5].trim().replace(",", "");
		courseCredit=line.split(",")[6].trim().replace(",", "");
		yearTaken=Integer.parseInt(line.split(",")[7].trim().replace(",", ""));
		semesterCourseTaken=Integer.parseInt(line.split(",")[8].trim().replace(",", ""));
	}
	
	
	public String getYearMonthGraduated() {
		return yearMonthGraduated;
	}
	
	public int getYearTaken() {
		return yearTaken;
	}
	
	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}
	
	
	
	
}
