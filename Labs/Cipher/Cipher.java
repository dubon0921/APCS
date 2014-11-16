/** This program allows the user to either encrypt/decrypt a text file
 *  using a very simple cipher. In the key file, each letter in the alphabet
 *  has a corresponding letter that will replace it if a text file is 
 * 	encrypted and vice versa if a text file is decrypted.
 */

import java.util.Scanner;
import java.io.PrintWriter;

public class Cipher {
	String keytextfile, textfileread, numbertyped, createdtextfile, line, line2, line3, oneline;
				// keytextfile, textfileread = name of the text files typed
				// by the user containing the key and the text file to be read,
				// respectively
				
				// numbertyped = number typed (1 or 2) by the user to determine 
				// if the text file is to be encrypted or decrypted
				
				// createdtextfile = name of the new text file that will contain
				// the encrypted (or decrypted) text
	char[][]encryptarray; // the char array containing the letters a-z in the first column
			      // and their corresponding letters in the second column if the 
			      // text file is to be encrypted
	char[][]decryptarray; // the char array containing the letters a-z in the second column and
			      // their corresponding letters in the first colum nif the text file is 
			      // to be decrypted
	boolean encrypt; // boolean determining if a text file is being encrypted or decrypted
	Scanner keyscanner, textscanner, printscanner; 
			// the scanners that read the key file and the text file containing the 
			// text to be encrypted (or decrypted), respectively
									 
	public Cipher() { // initialize the values of all fields 
		encrypt = true;
		encryptarray = new char[26][2]; // initialize encryptarray with 2 columns and 26 rows
		decryptarray = new char[26][2]; // intialize decryptarray with 2 columns and 26 rows
	} // class Cipher constructor
	
	public static void main (String[] args) {
		Cipher c = new Cipher();
		c.GetInput();
		c.ReadAndWrite();
		c.Print();
	} // main
	
	/** This method obtains the name of the key file, text file to be encrypted, and
	 * 	the new text file which will contain the encrypted (or decrypted) text from
	 *  the user by using a scanner. Also, a number (1 or 2) is obtained by the user
	 * 	which determines if the text file is to be encrypted or decrypted.
	 */
	public void GetInput() {
		Scanner keyboard = new Scanner (System.in); // the scanner which the user will be using to typed the names
		System.out.print("Please enter the name of the text file to be used for the encryption key: ");
		keytextfile = keyboard.nextLine();
		
		System.out.print("Please enter the name of the text file to be read from                  : ");
		textfileread = keyboard.nextLine();
		
		System.out.print("Please enter 1 to encrypt the text file, or 2 to decrypt the text file  : ");
		numbertyped = keyboard.nextLine();
		
		// while statement ensures that the user presses either 1 or 2 when asked to encrypt
		// or decrypt; otherwise, the user will be prompted again with the same question
		while (!numbertyped.equals("1") && !numbertyped.equals("2")) { 
			System.out.print("Please enter 1 to encrypt the text file, or 2 to decrypt the text file  : ");
			numbertyped = keyboard.nextLine();
		} // while statement
		
		if (numbertyped.equals("1")) encrypt = true; // if the user types "1", boolean encrypt is true
		else encrypt = false;
		
		System.out.print("Please enter the name of the text file to be created                    : ");
		createdtextfile  = keyboard.nextLine();
	} // GetInput
	
	/** This method will read the text file containing the text to be encrypted (or decrypted).
	 * 	For each line, the characters of each will be stored into a char array and the new output
	 * 	will be produced. After this occurs, the new text file will be created with the new encrypted
	 * 	(or decrypted) text.
	 */
	public void ReadAndWrite() {
		char[]chararray;     // char array storing all the characters only in particular line
		char origchar = 'x'; // the original character in the text to be read
		char newchar = 'x';  // the new converted character in the new text file to be created
		int linecount = 0;   // integer representing the current line of the text file
						     // that is being read
		
		keyscanner = OpenFile.openToRead(keytextfile); // scanner that reads the key file
		textscanner = OpenFile.openToRead(textfileread); // scanner that reads the text file that is to be
								 // encrypted (or decrypted)
			
		PrintWriter pw = OpenFile.openToWrite(createdtextfile); // print writer that will write the new encrypted
																// (or decrypted) text file
		
		while (keyscanner.hasNextLine()) { // while the key file has another line...
			if (keyscanner.hasNext()) { // if there is another element in a given line of the key file...
				line = keyscanner.nextLine();
				encryptarray[linecount][0] = line.charAt(0);  // assign the 1st character of each line in the key
									      // file to the first column of the encrypt array
				encryptarray [linecount][1] = line.charAt(3); // assign the 4th character of each line in the key
									      // file to the second column of the encrypt array
				
				decryptarray[linecount][0] = line.charAt(3); // assign the 4th character of each line in the key
								  	     // file to the second column of the decrypt array
				decryptarray[linecount][1] = line.charAt(0); // assign the 1st character of each line in the key
									     // file to the first column of the decrypt array
			} // if statement
			
			else if (keyscanner.hasNextLine())keyscanner.nextLine();
			linecount++; // increase linecount by 1
		} // while
		
		while (textscanner.hasNextLine()) { // while there is another line in the text file that
						   // is to be encrypted (or decrypted)...
			if (textscanner.hasNext()) {     // if there is another element in the text file in a given line
						         // of the text file that is to be encrypted (or decrypted)...
				oneline = textscanner.nextLine();
				chararray = oneline.toCharArray();
				
				// NOTE: upper case A-Z are ASCII numbers 65-90
				// NOTE: lower case a-z are ASCII numbers 97-122
				
				// for loop will replace individual characters in a particular line with their
				// corresponding characters by searching either the encrypt or decrypt array
				// and printing the character in the second column of the array 
				for (int q = 0; q < chararray.length; q++) { 
					if (encrypt) { // if the user chooses to encrypt rather than decrypt...
						if((int)chararray[q] > 64 && (int)chararray[q] < 91) { // if the letter is uppercase...
							origchar = (char)((int)(chararray[q] + 32)); // convert the character into lower case temporarily
																		 // because all the characters in the key file are lower case
								for (int h = 0; h < 26; h++) {
									if(origchar == encryptarray[h][0]) { // if a particular character in a line matches one in the
																		 // encrypt array... (it should)
										newchar = (char)((int)(encryptarray[h][1] - 32)); // replace the character with its corresponding
																						  // character
										pw.print(newchar); // print the new character into the text file
									} // if statement
								} // for loop
						} // if statement
						
						else if ((int)chararray[q] > 96 && (int)chararray[q] < 123){ // if the character is lowercase...
							for (int t = 0; t < 26; t++) {
								if (chararray[q] == encryptarray[t][0]) { // if a particular character in a line matches
																		  // one in the encrypt array... (it should)
									newchar = encryptarray[t][1]; // replace the character with its corresponding character in the
																		  // second column of the array
									pw.print(newchar); // print the new character into the text file
								} // if statement
							} // for loop
						} // else if statement
						else if (oneline.isEmpty())pw.println();
						
						else pw.print(chararray[q]); // if the character in the text file is neither an
									     // upper/lower case letter, simply print that character
									     // into the new text file
							
					} // if statement
					else { // if the user wants to decrypt rather than encrypt...
						if ((int)chararray[q] > 64 && (int)chararray[q] < 91) { // if the letter is uppercase...
							origchar = (char)((int)(chararray[q] + 32)); // convert the character into lower case temporarily
																		 // because all the characters in the key file are lower case
							for (int f = 0; f < 26; f++) {
								if (origchar == decryptarray[f][0]) { // if a particular character in a line matches one in the first
																	  // column of the decrypt array... (it should)
									newchar = (char)((int)(decryptarray[f][1] - 32)); // replace the character with its corresponding character
																					  // in the second column of the array
									pw.print(newchar); // print the new character into the new text file
								} // if statement
							} // for loop
						} // if statement
						
						else if ((int)chararray[q] > 96 && (int)chararray[q] < 123) { // if the letter is lowercase...
							for (int v = 0; v < 26; v++) { 
								if (chararray[q] == decryptarray[v][0]) { // if a particular character in a line matches one in the first
																		  // column of the decrypt array... (it should)
									newchar = decryptarray[v][1]; // replace the character with its corresponding character in the second column
																  // of the array
									pw.print(newchar); // print the new character into the new text file
								} // if statement
							} // for loop
						} // else if
						
						else if (oneline.isEmpty())pw.println();
						
						else pw.print(chararray[q]); // if the character in the text file is neither an
									     // upper/lower case letter, simply print that character
									     // into the new text file
					} // else statement
				} // for loop
				pw.println(); // return to the next line when each line in the original text file has been red
			} // if statement
			else {
				if (textscanner.hasNextLine()) textscanner.nextLine();
			} // else statement
		} // while statement
		
		keyscanner.close(); // close the scanners and the printwriter
		textscanner.close();
		pw.close();
	} // ReadAndWrite
	
	/** This method prints the contents of the output text file into the terminal.
	 * 	terminal.
	 */
	public void Print() { 
		printscanner = OpenFile.openToRead(createdtextfile);
		System.out.println();
		while (printscanner.hasNextLine()) { // if there is another line in the text file...
			if (printscanner.hasNext()) { // if there is another element in a given line...
				line3 = printscanner.nextLine(); 
				System.out.print(line3); // print each line in the text file
			} // if statement
			else if (printscanner.hasNextLine())printscanner.nextLine();
			System.out.println();
		} // while statement
		printscanner.close(); // close the print scanner
	} // Print
} // class Cipher
