package edu.handong.analysis;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.handong.analysis.utils.NotEnoughArgumentException;

public class AnalysisOption {
	
	private String inputPath = null;
	private String outputPath = null;
	private String analysistype = null;
	private boolean courseCodeExistence = false;
	private String courseCode = null;
	private String startYear = null;
	private String endYear = null;
	private boolean help = false;
	

	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);
			
			inputPath = cmd.getOptionValue("i");
			if(!new File(inputPath).exists()) 
					throw new NotEnoughArgumentException("The file path does not exist. Please check your CLI argument!");
			outputPath = cmd.getOptionValue("o");
			analysistype = cmd.getOptionValue("a");
			if(analysistype.contentEquals("2")) {
				if(courseCodeExistence = cmd.hasOption("c"))
					courseCode =cmd.getOptionValue("c");
				else {
					throw new Exception("You have to write on CLI -c 'CourseCode' when you execute -a 2");
				}
			}else if(!analysistype.contentEquals("1"))
				throw new Exception();
			startYear = cmd.getOptionValue("s");
			endYear	= cmd.getOptionValue("e");
			help = cmd.hasOption("h");
			
		}catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			printHelp(options);
			return false;
		}
		return true;
	}
	
	public Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg()    
				.argName("Analysis option")
				.required() 
				.build());
		
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg()    
				.argName("course code")
				//.required() 
				.build());
		
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg()    
				.argName("Start year for analysis")
				.required() 
				.build());
		
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg()    
				.argName("End year for analysis")
				.required() 
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .argName("Help")
		        .build());

		return options;
	}
	
	public void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}
	
	public String getInputPath() {
		return inputPath;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public String getAnalysistype() {
		return analysistype;
	}

	public boolean isCourseCodeExistence() {
		return courseCodeExistence;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getStartYear() {
		return startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public boolean isHelp() {
		return help;
	}
}
