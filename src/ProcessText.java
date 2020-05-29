import java.io.*;


class HistogramParametersAndProcess {
	
// This class has the input file reader and output file writer as member variables.
// This class also has the processing front end ProcessByType(...) function which passes on the
// input file reader and output file writer as arguments.
	
	String inFileName;
	String outFileName;
	
	FileWriter outFileWriter = null;
	FileReader inFileReader = null;
	
	public boolean ValidateInputOutput( String input, String output) {
		
		boolean boolRead = false, boolWrite = false;
		
		inFileName = input;
		outFileName = output;
		
// Check for file opening errors.		
		try {
			
			inFileReader = new FileReader( inFileName);
			
			boolRead = true;
			
		} catch( FileNotFoundException ex) {
			
			System.out.println("Error opening input file: [" + ex.getMessage() + "]");
			
		}
		
		try {
			
			outFileWriter = new FileWriter( outFileName);
			
			boolWrite = true;
			
		} catch ( IOException ex) {
			
			
			System.out.println("Error opening output file: [" + ex.getMessage() + "]");
			
		}
		
		return ( boolRead && boolWrite);
		
		
	}
	
	public void CloseInputOutput() {
		
// Check for file closing errors.		
		try {
			
			if ( inFileReader == null) { System.out.println("Input file reader is null, cannot close file.");}
			
			else { inFileReader.close();}
			
		} catch( IOException ex) {
			
			System.out.println("Error closing input file: [" + ex.getMessage() + "]");
			
		}
		
		
		try {
			
			if ( outFileWriter == null) { System.out.println("Output file writer is null, cannot close file.");}
			
			else { outFileWriter.close();}
			
		} catch( IOException ex) {
			
			System.out.println("Error closing output file: [" + ex.getMessage() + "]");
			
		}
		
	}
	
	
	public void ProcessByType( int charGrpType) {
		
		CharacterGroupManager manager = null;
		
// Redundant check before passing file references to processing classes.
	
		if ( inFileReader == null) { System.out.println("Cannot open input file.");}
		else if ( outFileWriter == null) { System.out.println("Cannot open output file.");}
		else {
			
// CharacterGroupManager has processing classes, all use the same input and output files.
			
			manager = new CharacterGroupManager( inFileReader, outFileWriter);
			
			switch (charGrpType ) {
			
			case 0:
				
				manager.numGroup.ReadInText();
				
				break;
				
			case 1:
				
				manager.vowelGroup.ReadInText();
				
				break;
				
				
			case 2:
				
				break;
				
				
			case 3:
				
				break;
				
				
			default:
				
				System.out.println("Character group type is not valid.");
				
				
				break;
			
		
			} // end switch
			
		} // end if
		
	} // end ProcessType()
		
		
} // end class HistogramParametersSetAndProcess
	
public class ProcessText {
	
// This application allows the user to create( code ) a class defining a string of characters.
// The individual characters are to be counted in the input file
// for the purpose of presenting a histogram.


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String inputFileName = "", outputFileName = "";
		int charGroupType = 0;
		boolean boolFilesOK = false;
		
		HistogramParametersAndProcess process = new HistogramParametersAndProcess();

// Check argument count.
		if ( args.length == 3) {
			
			System.out.println("ProcessText Start");
			
// Read arguments. Input file name, Output file name, Character group type as int. Example: input.txt output.txt 4			
			inputFileName = args[0];
			outputFileName = args[1];
			charGroupType = Integer.parseInt(args[2]);

// Open input and output files.
			boolFilesOK = process.ValidateInputOutput( inputFileName, outputFileName);
			
			if ( boolFilesOK) {
// Process here.			
				process.ProcessByType( charGroupType);
				
// Close input and output files.			
				process.CloseInputOutput();
			
			}
			
			System.out.println("ProcessText Finish");
			
		} else {
			
			System.out.println("Arg 0 is the input file name, the file must exist.");
			System.out.println("Arg 1 is the output file name, the output file is created if it does not exist.");
			System.out.println("Arg 2 is the character group type.");
			System.out.println("Char group types are 0: numbers, 1: vowels, 2: soft consonants, 3: hard consonants.");

		}

	}

}
