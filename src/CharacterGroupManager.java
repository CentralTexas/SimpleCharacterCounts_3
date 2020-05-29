import java.io.*;

class CharacterGroupBase {
// This is the base class for other derived processing classes.
// Default character group here are numbers 0 -> 9.
	
	protected static FileWriter outputFile = null;
	protected static FileReader inputFile = null;
	
	protected String title = "";
	protected String characterSet = "";
	protected int charSetLen = 0;
	protected int[] characterCount;
	
// Set static file references once at beginning.	
	protected static void setFiles(  FileReader inFile, FileWriter outFile) {
		
		inputFile = inFile;
		
		outputFile = outFile;
		
	}
	
// Perform a character read from the input file.	
	protected void ReadInText() {
		
		int chRead;
		
		try {
			
			SetCharacterString();
			
			InitializeHistogram();
		
			while ( (chRead = inputFile.read()) != -1) {
				
				CreateHistogram( chRead);
				
			}
			
			GenerateReport();
		
		} catch( IOException ex) {
			
			System.out.println("Error reading input stream " + ex.getMessage());
		}
		
	}
	
// Set the characters for the histogram.
// Set the title for the report.
	protected void SetCharacterString() {
		
		title = "Numerical Histogram";
		characterSet = "0123456789";
		charSetLen = characterSet.length();
		characterCount = new int[charSetLen];		
		
	}
// Zero out histogram counts.	
	protected void InitializeHistogram() {
		
		int i;
		
		for ( i = 0; i < charSetLen; i++) { characterCount[i] = 0;}
		
	}
// Compare the char from the file to each char in the set.
// Histogram counts are done here.
	protected void CreateHistogram( int ch) {
		
		int j;
		
		j = characterSet.indexOf((char)ch);
		
		if ( j != -1) { characterCount[j]++;}
		
	}
// The report is generic and can be overwritten in derived classes.
// Results are written to the output file.
	protected void GenerateReport() {
		
		int i;
		String strLine;
		
		try {
			
			strLine = String.format("%s%s%n", "Begin ", title);
			
			outputFile.append(strLine);
		
			for ( i = 0; i < charSetLen; i++) {;
				
				strLine = String.format("%c: %d%n", characterSet.charAt(i),  characterCount[i]);
				
				outputFile.append(strLine);
				
			}
			
			strLine = String.format("%s%s%n", "End ", title);
			
			outputFile.append(strLine);
		
		} catch( IOException ex) {
			
			System.out.println("Error generating report: " + ex.getMessage());			
		}
		
	}
	
	
} // end CharacterGroupBase

// Derived processing class, just need to overwrite 2 base functions for another character set.
class VowelGroup extends CharacterGroupBase {
	
	
	protected void SetCharacterString() {
		
		title = "Vowel Histogram";
		characterSet = "aeiouy";
		charSetLen = characterSet.length();
		characterCount = new int[charSetLen];		
		
	}
	
	protected void CreateHistogram( int ch) {
		
		int j;
		
		char charLow;
		
		charLow = Character.toLowerCase((char)ch);
		
		j = characterSet.indexOf(charLow);
		
		if ( j != -1) { characterCount[j]++;}
		
	}
	
	
} // end class VowelGroup




public class CharacterGroupManager {
// This class instantiates and holds the individual processing classes.
	
	public CharacterGroupBase numGroup = null;
	public VowelGroup vowelGroup = null;
	
// Constructor.	
	CharacterGroupManager(FileReader inFile, FileWriter outFile) {
		
// Set static file references.		
		CharacterGroupBase.setFiles(inFile, outFile);
		
// Processing classes.		
		numGroup = new CharacterGroupBase();
		
		vowelGroup = new VowelGroup();
		
	}

}
