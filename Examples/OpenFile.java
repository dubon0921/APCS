import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Utilities for reading and writing files.
 */
 
 public class OpenFile {
	 public static Scanner openToRead(String fileName) {
		 Scanner input = null; // can't put scanner inside try block
		 try {
			 input = new Scanner(new File(fileName));
		 } catch (FileNotFoundException e) {
			 System.err.println("ERROR: Cannot open " + fileName + " for reading.");
			 System.exit(1);
		 } // catch
		 return input;
	 } // openToRead
	 
	 public static PrintWriter openToWrite(String fileName) {
		 PrintWriter output = null;
		 try {
			 output = new PrintWriter(new File(fileName));
		 } catch (IOException e) {
			 System.err.println("ERROR: Cannot open " + fileName + " for writing.");
			 System.exit(1);
		 } // catch
		 return output;
	 } // PrintWriter	 
} // class OpenFile
