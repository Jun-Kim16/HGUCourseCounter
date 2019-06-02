package edu.handong.analysis.datamodel;

public class Course {
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private String yearTaken;
	private String semesterCourseTaken;
	
	public Course() {
		
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getYearMonthGraduated() {
		return yearMonthGraduated;
	}
	public void setYearMonthGraduated(String yearMonthGraduated) {
		this.yearMonthGraduated = yearMonthGraduated;
	}
	public String getFirstMajor() {
		return firstMajor;
	}
	public void setFirstMajor(String firstMajor) {
		this.firstMajor = firstMajor;
	}
	public String getSecondMajor() {
		return secondMajor;
	}
	public void setSecondMajor(String secondMajor) {
		this.secondMajor = secondMajor;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCredit() {
		return courseCredit;
	}
	public void setCourseCredit(String courseCredit) {
		this.courseCredit = courseCredit;
	}
	public String getYearTaken() {
		return yearTaken;
	}
	public void setYearTaken(String yearTaken) {
		this.yearTaken = yearTaken;
	}
	public String getSemesterCourseTaken() {
		return semesterCourseTaken;
	}
	public void setSemesterCourseTaken(String semesterCourseTaken) {
		this.semesterCourseTaken = semesterCourseTaken;
	}
	

	
	
	
}
