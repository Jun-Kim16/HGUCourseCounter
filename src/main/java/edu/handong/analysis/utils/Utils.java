package edu.handong.analysis.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;

public class Utils {

	public static TreeMap<String, Student> getStudents(String file, boolean removeHeader) {

		TreeMap<String, Student> students = new TreeMap<String, Student>();
		Student stu = null;
		Course course;
		
		try {
			Reader reader = new BufferedReader(new FileReader(file));
			if (removeHeader) { 
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT
							.withFirstRecordAsHeader()
							.withIgnoreHeaderCase()
							.withTrim());
				for (CSVRecord csvRecord : csvParser) {
						course = new Course();
						course.setStudentId(csvRecord.get("StudentID"));
						course.setYearMonthGraduated(csvRecord.get("YearMonthGraduated"));
						course.setFirstMajor(csvRecord.get("FistMajor"));
						course.setSecondMajor(csvRecord.get("SecondMajor"));
						course.setCourseCode(csvRecord.get("CourseCode"));
						course.setCourseName(csvRecord.get("CourseName"));
						course.setCourseCredit(csvRecord.get("CourseCredit"));
						course.setYearTaken(csvRecord.get("YearTaken"));
						course.setSemesterCourseTaken(csvRecord.get("SemesterTaken"));
						
						if(!students.containsKey(csvRecord.get("StudentID"))) {
							stu = new Student(csvRecord.get("StudentID"));
							stu.addCourse(course);
							students.put(csvRecord.get("StudentID"), stu);
						}
						else {
							stu.addCourse(course);
						}
				}	
			}
			else {
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT
							.withHeader("StudentID", "YearMonthGraduated", "FirstMajor", "SecondMajor", "CourseCode", "CourseName", "CourseCredit", "YearTaken", "SemesterTaken")
							.withIgnoreHeaderCase()
							.withTrim());
				for (CSVRecord csvRecord : csvParser) {
						course = new Course();
						course.setStudentId(csvRecord.get("StudentID"));
						course.setYearMonthGraduated(csvRecord.get("YearMonthGraduated"));
						course.setFirstMajor(csvRecord.get("FirstMajor"));
						course.setSecondMajor(csvRecord.get("SecondMajor"));
						course.setCourseCode(csvRecord.get("CourseCode"));
						course.setCourseName(csvRecord.get("CourseName"));
						course.setCourseCredit(csvRecord.get("CourseCredit"));
						course.setYearTaken(csvRecord.get("YearTaken"));
						course.setSemesterCourseTaken(csvRecord.get("SemesterTaken"));
						
						if(!students.containsKey(csvRecord.get("StudentID"))) {
							stu = new Student(csvRecord.get("StudentID"));
							stu.addCourse(course);
							students.put(csvRecord.get("StudentID"), stu);
						}
						else {
							stu.addCourse(course);
						}
							
				}			
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}

	
	public static void writeAFile1(ArrayList<String> lines, String targetFileName) {
		//	Year,Semester,CouseCode, CourseName,TotalStudents,StudentsTaken,Rate
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new FileWriter(targetFileName, false));
			bf.write("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");
			bf.newLine();
			for (String l : lines) {
				bf.write(l);
				bf.newLine();
			}
			bf.flush();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
 		}
	}
	
	public static void writeAFile2(ArrayList<String> lines, String targetFileName) {
		
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new FileWriter(targetFileName, false));
			bf.write("Year,Semester,CouseCode, CourseName,TotalStudents,StudentsTaken,Rate");
			bf.newLine();
			for (String l : lines) {
				bf.write(l);
				bf.newLine();
			}
			bf.flush();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
