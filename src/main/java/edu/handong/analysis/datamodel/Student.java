package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class Student {
	private String studentId;
	
	private ArrayList<Course> coursesTaken = new ArrayList<Course>();
	private TreeMap<String,Integer> semestersByYearAndSemester = new TreeMap<String,Integer>();
		
	public Student(String studentId){
		this.studentId = studentId;
	}
	
	public void addCourse(Course newRecord) {
		coursesTaken.add(newRecord);
	}
	
	// return HashMap <year-semester(key), number of semester(value)
	public TreeMap<String, Integer> getSemesterByYearAndSemester(){
		int numOfSemester = 1;
		for(Course course : coursesTaken) {
			String year = course.getYearTaken();
			String seme = course.getSemesterCourseTaken();
			if(!semestersByYearAndSemester.containsKey(year+"_"+seme)) {
				semestersByYearAndSemester.put(year+"_"+seme, numOfSemester);
				numOfSemester++;
			}
		}
		return semestersByYearAndSemester;
	}
	

	
	// return number of courses in Nth Semester.
	public int getNumCourseInNthSemester(int semester) {
		int numOfCourse = 0;
		for(String yearseme : semestersByYearAndSemester.keySet()) {
			if(semestersByYearAndSemester.get(yearseme).equals(semester)) {
				String year = yearseme.split("_")[0];
				String seme = yearseme.split("_")[1];
				for(Course course : coursesTaken) {
					if(course.getYearTaken().contentEquals(year) && 
						course.getSemesterCourseTaken().contentEquals(seme))
						numOfCourse++;
				}
			}
		}
		return numOfCourse;
	}
	
	public ArrayList<String> whenCourseTaken(String CourseCode) {
		ArrayList<String> when = new ArrayList<String>();
		for(Course course : coursesTaken) {
			if(course.getCourseCode().contentEquals(CourseCode)) {
				when.add(course.getYearTaken()+ "_" + course.getSemesterCourseTaken());
			}
		}
		return when;
	}
	
	public String courseName(String CourseCode) {
		for(Course course : coursesTaken) {
			if(course.getCourseCode().contentEquals(CourseCode)) {
				return course.getCourseName();
			}
		}
		return "NotFound";
	}
	
	public boolean isEnrolled(String year_seme) {
		for(Course course : coursesTaken) {
			if(course.getYearTaken().contentEquals(year_seme.split("_")[0]) &&
					course.getSemesterCourseTaken().contentEquals(year_seme.split("_")[1]))
				return true;
		}
		return false;
	}
	

	
}
