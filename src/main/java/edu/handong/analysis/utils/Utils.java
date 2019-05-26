package edu.handong.analysis.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
	public static ArrayList<String> getLines(String file, boolean removeHeader) {
		ArrayList<String> lines = new ArrayList();

		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(file));
			if (removeHeader) {
				bf.readLine();
				while (true) {
					String a = bf.readLine();
					if(a!=null) lines.add(a);
					else break;
					
				}
			} else {
				while (true) {
					String a = bf.readLine();
					if(a!=null) lines.add(a);
					else break;
					
				}
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
