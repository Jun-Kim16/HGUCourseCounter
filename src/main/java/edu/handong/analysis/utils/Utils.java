package edu.handong.analysis.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		ArrayList<String> lines = new ArrayList();
		
		
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(file));
			if (removeHeader) {
				bf.readLine();
				while (bf.readLine()!=null)
					lines.add(bf.readLine());
				return lines;
			} else {
				while (bf.readLine()!=null)
					lines.add(bf.readLine());
			}
			bf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
	
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new FileWriter(targetFileName, false));
			bf.write("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");
			bf.newLine();
			for(String l : lines) {
				 bf.write(l);
				 bf.newLine();
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
