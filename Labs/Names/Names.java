/**     Based off of a list of the most popular baby names from 1900-2000,
 * 	the user can enter a name in order to find its popularity within
 * 	the 100 year span. A popularity index for the 100 years will be shown
 * 	with 10 year increments. Also, a chart of popularity is printed.
 	On the y-axis of the chart, there are numbers denoting the popularity
 * 	in increments of 20 (a lower number in this case denotes a high 
 * 	popularity). On the x-axis is the years from 1900-2000 in ten year 
 * 	increments. 
 */

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class Names {
	File textFile; // text file containing the list of all names with their popularity
	Scanner scanner, userInput; // scanners used to read names.txt and receive user input
	int[] popularityArray = new int[12]; // array storing the 11 popularity values for a given name
	int[][] results = new int[50][11]; 
	
	boolean nameFound; // boolean denoting whether the name the user typed was found
			   // in the text file
	String name, temp;
	
	public Names( ) { // initialize fields
		textFile = new File("names.txt"); // the text file being read from with the list
						  // of the most popular names and their rank
		name = "name";
	} // class Names constructor
	
	public static void main (String[] args) {
		Names n = new Names();
		n.ReadAndPrint();
	} // main
	
	/**
	 *  This method reads the text file and searches it for the name inputted
	 * 	by the user. Also, it inputs the popularity for the name in the 
	 * 	100 year period into an array and prints the results in a popularity
	 * 	chart.
	 */
	public void ReadAndPrint() {
		try {	
			scanner = new Scanner(textFile); // associate scanner with names.txt
			
			userInput = new Scanner(System.in); // associate userInput scanner with
							    // what the user types
			System.out.print("Please enter the name you're looking for -> ");
			
			// assign variable typedName to what the user types
			String typedName = userInput.next(); 
			
			scanner.useDelimiter(" "); // seperate different elements in a given line		
						   // using a space
			
			while(scanner.hasNextLine()) { // while there's another line in the text file...
						       // if there is more in a given line, assign the next element to the 
						       // variable name
				if (scanner.hasNext()) name = scanner.next(); 
				
				// if the element found in the array matches the typed name...				
				if (name.toLowerCase().equals(typedName.toLowerCase())) {
					nameFound = true; // assign the nameFound boolean to true
					System.out.print(name);
				
					// for loop assigns popularity values for a given
					// name into the popularityArray array
					for (int c =0; c < 11; c++) {
						if (scanner.hasNextInt() && c < 10) {
							popularityArray[c] = scanner.nextInt();
							System.out.print("   " + popularityArray[c]);
						} // if statement
						
						else {
							temp = scanner.nextLine();
							temp = temp.trim();
							popularityArray[c] = Integer.parseInt(temp);
							System.out.print("   " + popularityArray[c]);
						} // else statement	
					} // for loop
				} // if statement
				else {
					if (scanner.hasNextLine()) scanner.nextLine();
				} // else statement	
			} // while
				
			// if the name inputted by the user is unable to be found in the
			// text file, print an error statement and exit the program
			if (!nameFound) {
				System.out.println(typedName + " not found in database.");
				System.exit(1);
			} // if statement
			
			System.out.println("\n ");
			
			for (int e = 0; e < 11; e++) {
				// index determining which row the value will be printed in
				// by dividing the popularity value by 20 (because the axis
				// increases with increments of 20)
				int index = popularityArray[e]/20; 
				
				// remainder determined by dividing the popularity value by 20
				int remainder = popularityArray[e] % 20;
				
				if (remainder == 0)results[index+1][e] = popularityArray[e];
				else results[index][e] = popularityArray[e];	
			} // for loop
			System.out.println(" ");
			
			// print the x-axis of the popularity chart showing the years from 1900-2000
			// in 10 year increments
			System.out.print("        1900    1910    1920    1930    1940    1950"); 
			System.out.println("    1960    1970    1980    1990    2000 \n");
			
			// for loop that prints the axis on the left showing popularity
			// in increments of 20 & prints the popularity index (or a space) in the
			// appropriate position according to year and popularity 
			for (int x = 0; x < 50; x++) {
				System.out.printf(" %4d  ", (x + 1) * 20);
				for (int y = 0; y < 11; y++) {	
					if (results[x][y] != 0) System.out.printf("%5d   ", results[x][y]); 
					else System.out.printf("%5s", "  ---   ");
				} // for loop
				System.out.println("");	
			} // for loop
		
		// if the text file can't be found, print an error message and exit
		// the program	
		} catch (FileNotFoundException e) { 
			System.err.println("ERROR: Cannot open file names.txt");
			System.exit(1);
		} // catch
		scanner.close(); // close the scanner once the text file has been read from
	} // ReadAndPrint
} // class Names
