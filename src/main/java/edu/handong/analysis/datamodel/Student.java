package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken = new ArrayList<Course>();
	private HashMap<String,Integer> semestersByYearAndSemester = new HashMap<String,Integer>();
		
	
	public Student(String studentId){
		this.studentId = studentId;
	}
	
	public void addCourse(Course newRecord) {
		coursesTaken.add(newRecord);
	}
	/*2018-1, 2 이런식으로 학생별로 쭉 넣어서 만듬*/
	public HashMap<String, Integer> getSemesterByYearAndSemester(){
		int numOfSemester = 1;
		/*String year = Integer.toString(coursesTaken.get(0).getYearTaken());
		String seme = Integer.toString(coursesTaken.get(0).getSemesterCourseTaken());
		semestersByYearAndSemester.put(year+"-"+seme, numOfSemester);
		numOfSemester++;*/
		
		for(int i=0; i< coursesTaken.size();i++) {
			String year = Integer.toString(coursesTaken.get(i).getYearTaken());
			String seme = Integer.toString(coursesTaken.get(i).getSemesterCourseTaken());
			if(!semestersByYearAndSemester.containsKey(year+"-"+seme)) {
				semestersByYearAndSemester.put(year+"-"+seme, numOfSemester);
				numOfSemester++;
			}
		}
		return semestersByYearAndSemester;
	}
	
	public int getNumCourseInNthSemester(int semester) {
		
		int numCourse = 0;
		for(String a : semestersByYearAndSemester.keySet()) {
			if(semestersByYearAndSemester.get(a).equals(semester)) {
				String yearseme=a;
				int year = Integer.parseInt(yearseme.split("-")[0]);
				int seme = Integer.parseInt(yearseme.split("-")[1]);
				
				for(int i=0; i< coursesTaken.size();i++) {
					if(coursesTaken.get(i).getYearTaken()==year && coursesTaken.get(i).getSemesterCourseTaken()==seme)
						numCourse++;
			}
		}
		
		
		}
		return numCourse;
	}
}
