package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.Options;

import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {
	//run
	public void run(String[] args) {
		AnalysisOption analysisOpt = new AnalysisOption();
		Options options = analysisOpt.createOptions();

		
		if(analysisOpt.parseOptions(options, args)){
			String start = analysisOpt.getStartYear();
			String end = analysisOpt.getEndYear();
			
			if (analysisOpt.isHelp()){
				analysisOpt.printHelp(options);
				return;
			}
			//read
			Map<String, Student> sortedStudents = Utils.getStudents(analysisOpt.getInputPath(), true);
			
			if(analysisOpt.getAnalysistype().contentEquals("1")) {
				//count
				ArrayList<String> linesToBeSaved1 = countNumberOfCoursesTakenInEachSemester(sortedStudents, start, end);
				//write
				Utils.writeAFile1(linesToBeSaved1, analysisOpt.getOutputPath());
			}
			if(analysisOpt.getAnalysistype().contentEquals("2")) {
				String CourseCode = analysisOpt.getCourseCode();
				//count
				ArrayList<String> linesToBeSaved2 = countNumberOfStudentsTakeSuchCourse(sortedStudents, CourseCode, start, end);
				//write
				Utils.writeAFile2(linesToBeSaved2, analysisOpt.getOutputPath());
			}
		}
	}

	//-a 1 count
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents, String startYear, String endYear) {
		ArrayList<String> numOfCourses = new ArrayList<String>();
		for(String stukey : sortedStudents.keySet()) {
			Student stu = sortedStudents.get(stukey);
			Map<String, Integer> numOfSemesterForYear_Seme = stu.getSemesterByYearAndSemester();
			int totalSemester = numOfSemesterForYear_Seme.size();
			
			Map<String, Integer> revisedMapByStartEndYear = getRevisedMapByStartEndYear(numOfSemesterForYear_Seme,startYear,endYear);
		
			for(String key : revisedMapByStartEndYear.keySet()) {
				int i = revisedMapByStartEndYear.get(key);
				numOfCourses.add(stukey+","
								+totalSemester+","
								+Integer.toString(i)+","
								+Integer.toString(stu.getNumCourseInNthSemester(i)));
			}
		}
		return numOfCourses;
	}
	
	//-a 2 count
	private ArrayList<String> countNumberOfStudentsTakeSuchCourse(Map<String, Student> sortedStudents,String CourseCode,String startYear,String endYear){
		Map<String, Integer> NumOfStuTake= new TreeMap<String, Integer>();
		Map<String, Integer> NumOfStuEnrolled = new TreeMap<String, Integer>();
		ArrayList<String> linesToBeSaved = new ArrayList<String>();
		
		//take courseName 	
		String courseName = null;
		for(Student stu : sortedStudents.values()) {
			if(!stu.courseName(CourseCode).contentEquals("NotFound")) {
				courseName = stu.courseName(CourseCode);
				break;
			}
		}
		
		//from students, make Map<year_seme, number of students take the course>
		for(Student stu : sortedStudents.values()) {
			ArrayList<String> year_seme = stu.whenCourseTaken(CourseCode);
			if(!year_seme.isEmpty()) {
				for(int i=0; i<year_seme.size();i++) {
					if(!NumOfStuTake.containsKey(year_seme.get(i)))
						NumOfStuTake.put(year_seme.get(i),1);
					else
						NumOfStuTake.replace(year_seme.get(i), NumOfStuTake.get(year_seme.get(i))+1);
				}
			}
		}
		
		//from students, make Map<year_seme, number of students enrolled>
		for(Student stu : sortedStudents.values()) {
			for(String year_seme : NumOfStuTake.keySet()) {
				if(stu.isEnrolled(year_seme)) {
					if(!NumOfStuEnrolled.containsKey(year_seme))
						NumOfStuEnrolled.put(year_seme, 1);
					else NumOfStuEnrolled.replace(year_seme, NumOfStuEnrolled.get(year_seme)+1);
				}
			}
		}
		//get revised Map 
		NumOfStuTake = getRevisedMapByStartEndYear(NumOfStuTake, startYear, endYear);
		NumOfStuEnrolled = getRevisedMapByStartEndYear(NumOfStuEnrolled, startYear, endYear);
		
		//make writing material
		for(String year_seme : NumOfStuTake.keySet()) {
			String year = year_seme.split("_")[0];
			String seme = year_seme.split("_")[1];
			String total = Integer.toString(NumOfStuEnrolled.get(year_seme));
			String taken = Integer.toString(NumOfStuTake.get(year_seme));
			String rate = String.format("%.1f",(NumOfStuTake.get(year_seme)*100 /(float)NumOfStuEnrolled.get(year_seme)));
		linesToBeSaved.add(year+","+seme+","+CourseCode+","+courseName+","+total+","+taken+","+rate+"%");
		}
	
		return linesToBeSaved;
	}
	
	//revise Map by start-end years
	private Map<String, Integer> getRevisedMapByStartEndYear(Map<String, Integer> ObjectiveMap, String startYear, String endYear) {
		int start = Integer.parseInt(startYear+"1");
		int end = Integer.parseInt(endYear+"4");
		Iterator<String> iter = ObjectiveMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			int intKey = Integer.parseInt(key.split("_")[0]+key.split("_")[1]);
			if(!(start <= intKey && intKey <= end))
				iter.remove();
			}
		
		return ObjectiveMap;
	}
}
