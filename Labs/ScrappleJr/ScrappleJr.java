import java.util.Scanner;

/** This game is a more simplistic version of the popular game Scrabble. There is a pre-
 *  define pool of tiles that both the player and computer will be given letters from.
 *  Both the player and computer are given 8 random letters to start off the game and 
 *  the player must attempt to create a valid 4 to 8 letter word according the the
 *  provided word list. Each letter corresponds to a point value and at the end of the game,
 *  the person with the greater number of points wins the game.
 */

public class ScrappleJr {
	public static int playerScore, computerScore; // the scores of the player and computer
	public boolean endOfGame;            	      // boolean indicating whether the end of the game
						      // has been reached or not
	private String tilesRemaining = "AAAAAAAAAABBCCDDDDEEEEEEEEEEEEEFFGGGHHIIIIIIIII" +
					"JKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ";
	public String typedWord; // the word that the user types 
	public String infileName = "wordlist.txt"; // the name of the text file containing
											   // the words that can be created
	public int [] scores = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,
					 1, 1, 1, 1, 4, 4, 8, 4, 10}; // array with the point value for each letter
	public char[] tilePool, playerTiles, computerTiles;				   
						// -tilePool = 100 char array with the entire 100 character pool
						// that is defined at the beginning of the game
						// -playerTiles = 8-character array containing the 8 letters 
						// the player can create words from
						// -computerTiles = 8-character array containing the 8 letters
						// the computer can create words from 
	
	public ScrappleJr () { // initialization of all fields
		playerScore = 0;
		computerScore = 0;
		endOfGame = false;
		tilePool = new char[100];
		playerTiles = new char [8];
		computerTiles = new char[8];
		
		for (int h = 0; h < 8; h++) {
			playerTiles[h] = '9'; // set default values of the player's tiles to be 9
			computerTiles[h] = '9'; // set default values of the computer's tiles to be 9
		} // for loop	
	} // class ScrappleJr constructors
	
	public static void main(String [] args) {
		ScrappleJr sj = new ScrappleJr();
		sj.inputValues();
		sj.oneGame();
	} // main
	
	// method coverts the entire tile pool string into a 100 character array
    public void inputValues() { 
		tilePool = tilesRemaining.toCharArray();             
	} // inputValues
	
	/** This method runs a full game of Scrabble between the player
	 * 	and the computer.
	 */
	public void oneGame() {
		printIntroduction();
		while (!endOfGame) { // while the game hasn't ended yet...
			getRandomPlayer();
			getRandomComputer();
			printStats();
			getPlayerInput();
			getRandomPlayer();
			printStats();
			getComputerInput();
		} // while loop 
	} // oneGame
	
	/**
	 *  This method prints the introduction screen for Scrapple. This includes the
	 *  "Scrapple" graphic and the instructions of how to play the game.
	 */
	public void printIntroduction() {
		System.out.print(" _______     _______     ______     ______    ");
		System.out.println(" ______    ______   __          _______");
		System.out.print("/\\   ___\\   /\\  ____\\   /\\  == \\   /\\  __ \\   ");
		System.out.println("/\\  == \\  /\\  == \\ /\\ \\        /\\  ____\\");
		System.out.print("\\ \\___   \\  \\ \\ \\____   \\ \\  __<   \\ \\  __ \\  ");
		System.out.println("\\ \\  _-/  \\ \\  _-/ \\ \\ \\_____  \\ \\  __\\");
		System.out.print(" \\/\\______\\  \\ \\______\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\ ");
		System.out.println(" \\ \\_\\     \\ \\_\\    \\ \\______\\  \\ \\______\\");
		System.out.print("  \\/______/   \\/______/   \\/_/ /_/   \\/_/\\/_/ ");
		System.out.println("  \\/_/      \\/_/     \\/______/   \\/______/ TM");
		System.out.println();
		System.out.print("This game is a \"scaled down\" version of Scrabble. ");
		System.out.println("The game starts with a pool of letter tiles, with");
		System.out.println("the following group of 100 tiles:\n");
		
		// for loop prints the initial pool of 100 tiles
		for (int i = 0; i < tilesRemaining.length(); i ++) {
			System.out.printf("%c ", tilesRemaining.charAt(i));
			if ((i+1)%50 == 0) System.out.println();
		} // for loop
		
		System.out.println("\n");
		System.out.println("The game starts with 8 tiles being chosen at random to fill the player's hand. The player must");
		System.out.println("then create a valid word, with a length from 4 to 8 letters, from the tiles in his/her hand. The");
		System.out.println("\"word\" entered by the player is then checked. It is first checked for length, then checked to make");
		System.out.println("sure it is made up of letters from the letters in the current hand, and then it is checked against");
		System.out.println("the word text file. If any of these tests fail, the game terminates. If the word is valid, points");
		System.out.println("are added to the player's score according to the following table (These scores are taken from the");
		System.out.println("game of Scrabble):");
		
		// Print line of letter scores
		char c = 'A';
		for (int i = 0; i < 26; i++) {
			System.out.printf("%3c", c);
			c = (char)(c + 1);
		} // for loop
		System.out.println();
		
		for (int i = 0; i < scores.length; i++) System.out.printf("%3d", scores[i]);
		System.out.println("\n");
		
		System.out.println("Once the player's score has been updated, more tiles are chosen at random from the remaining pool");
		System.out.println("of letters, to fill the player's hand to 8 letters. The player again creates a word, and the");
		System.out.println("process continues. The game ends when the player enters an invalid word, or the letters in the");
		System.out.println("pool and player's hand run out. Ready? Let's play!");

		System.out.println();
		Prompt.getString("HIT ENTER on the keyboard to continue:");
	} // printIntroduction
	
	/**
	 * This method will print the remaining tile pool, along with the player's
	 * score, computer's score, the tiles in the player's hand, and the tiles
	 * in the computer's hand. These statistics will print after every turn
	 * made by the player and computer.
	 */
	public void printStats() {
		System.out.println("\nHere are the tiles remaining in the pool of letters:\n");	
		
        int charCount = 0; // counts the number of characters printed (once it reaches 20,
						   // a new line will be printed to make the printing look nice)
        
        // for loop goes through the master tile array and prints it
        // if the element of each index isn't '6' or '7', meaning the 
        // letter has already been used
		for (int j = 0; j < 100; j++) {
			if (tilePool[j] != '6' && tilePool[j] != '7') { // if the character hasn't already been used...
                System.out.print(tilePool[j] + " ");
                charCount++;
                if (charCount % 20 == 0) System.out.println(); // if 20 characters have been printed, print the next
															   // 20 characters on a new line
             }  // if statement
		}  // for loop
		
		System.out.println();
		System.out.println("\nPlayer Score:     " + playerScore);
		System.out.println("Computer Score:   " + computerScore);
		System.out.println();
		
		System.out.print("THE TILES IN YOUR HAND ARE:            ");
		for (int s = 0; s < 8; s++) {
			if (playerTiles[s] != '9') System.out.print(playerTiles[s] + " ");
		} // for loop
		System.out.println("\n");
		
		System.out.print("THE TILES IN THE COMPUTER'S HAND ARE:  ");
		for (int bc = 0; bc < 8; bc++) {
            if (computerTiles[bc] != '9') System.out.print(computerTiles[bc] + " ");
		} // for loop
		System.out.println();
	} // printStats
	
	/** This method produces 8 random letters for the player to start off with.
	 *  And as the game goes on, the user will be given more random letters to 
	 * 	fill the 8-letter maximum.
	 */
	public void getRandomPlayer() {
		int count = 0;
		
		for (int u = 0; u < 100; u++) {
			if (tilePool[u] == '6' || tilePool[u] == '7') count++;
		} // for loop

        if (count > 90) { // if the number of characters already used in the 
			  // master tile array is greater than 90...
			for (int v = 0; v < 8; v++) {
				if (playerTiles[v] == '9') {
					for (int z = 0; z < 100; z++) {
                        if (tilePool[z] != '6' && tilePool[z] != '7') {
							playerTiles[v] = tilePool[z];
							tilePool[z] = '6';
                            break;
                        }  // if statement
                    } // for loop
                 } // if statement
             } // for loop
             
             int numtiles = 0;
             for (int h = 0; h < 8; h++) {
				 if (playerTiles[h] != '6' && playerTiles[h] != '7') numtiles++;
			} // for loop
			
			if (numtiles < 4) {
				System.out.println("No valid word can be made from your tiles!\n");
				System.out.println("Player Score:    " + playerScore);
				System.out.println("Computer Score:  " + computerScore);
				System.out.println();
				System.out.println("Thank you for playing ScrappleJr!");
				System.exit(4);
			} // if statement
        } // if statement
        else { // if the number of characters already used in the master
			   // tile array is less than 90...
			for (int a = 0; a < 8; a++) {
				if (playerTiles[a] == '9') { // if the element of a certain index
							     // in the playerTiles array is '9', meaning that
							     // that character has been used, generate a random
							     // number that picks a random character from the 
							     // master tile array
					int pRandom = (int)(Math.random() * ((99) + 1)); // generate random number between 0 and 99
					while (tilePool[pRandom] == '6' || tilePool[pRandom] == '7'){ // if the element with
												      // the index of the random number
												      // generated is '6' or '7', meaning
												      // that the character in that spot has
												      // already been used, generate another
												      // random number
						pRandom = (int)(Math.random() * ((99) + 1));	
					} // while loop
					
					playerTiles[a] = tilePool[pRandom]; // once an appropriate character in the master tile array is chosen,
														 // assign that character to the playerTiles array and make that position 
														 // in the master tile array to be '6' to indicate that it's been used
					tilePool[pRandom] = '6';
				} // if statement	
            } // for loop
        } // else statement
		System.out.println();
	} // getRandomPlayer
	
	/** This method generates 8 random letters that the computer will start off with 
	 * 	in order to create its words.
	 */
	public void getRandomComputer() {
		int count = 0;
		for (int u = 0; u < 100; u++) {
			if (tilePool[u] == '6' || tilePool[u] == '7') count++;
		} // for loop
         if (count > 90) { // if the number of characters used in the master tile
						   // array is greater than 90...
			for (int v = 0; v < 8; v++) {
				if (computerTiles[v] == '9') {
                      for (int z=0; z < 100; z++) {
					      if (tilePool[z] != '6' && tilePool[z] != '7') {
							 computerTiles[v] = tilePool[z];
							 tilePool[z] = '7';
							 break;
						  } // if statement
                      } // for 
                    } // if 
            } // for loop
            
            int numtiles = 0;
            for (int h = 0; h < 8; h++) {
				 if (computerTiles[h] != '6' && computerTiles[h] != '7') numtiles++;
			} // for loop
			
			if (numtiles < 4) {
				System.out.println("No valid word can be made from these tiles!\n");
				System.out.println("Player Score:    " + playerScore);
				System.out.println("Computer Score:  " + computerScore);
				System.out.println();
				System.out.println("Thank you for playing ScrappleJr!");
				System.exit(4);
			} // if statement
            
         } // if statement
          else { // if the number of characters used in the master tile array is 
				 // less than 90...
		      for (int e = 0; e < 8; e++) {
			      int cRandom = (int)(Math.random() * ((99) + 1)); // find random # between 0-99
						
			   	  // will generate a new random number if the corresponding position in the 
			   	  // master character array has already been used up (represented by '6'/'7')
				  while (tilePool[cRandom] == '6' || tilePool[cRandom] == '7'){ 
				      cRandom = (int)(Math.random() * ((99) + 1));	
				  } // while loop
						
				  // if the user uses a letter in the 8 letters the user's given, generate
				  // a new letter for that position
				  if(computerTiles[e] == '9') {
				      computerTiles[e] = tilePool[cRandom];	
					  tilePool[cRandom] = '7';
				  } // if statement
              } // for loop
          } // if 
	} // getRandomComputer
	
	/** This method receieves the word that the user types when prompted
	 *  to type a word and determines if the word is valid based on a series
	 *  of tests. These tests determine if the word is 4-8 characters long,
	 *  if the word is created with letters the player given, and if the word
	 *  is actually a word according to the provided word list.  
	 */
	public void getPlayerInput() {
		boolean match = false; // determines if word inputted is valid
		
		System.out.println();
		// prompt the user for the word that the player wants to create 
		typedWord = Prompt.getString("Please enter a word from your current set of tiles -> ");
		String uppercaseWord = typedWord.toUpperCase();
		
		// print error message and exit program if word length is not between
		// 4 to 8 characters
		if (typedWord.length() < 4 || typedWord.length() > 8) {
			System.out.println();
			System.out.println("ERROR: Word typed is not 4-8 letters in length.");
			System.out.println();
			System.out.println("Player Score: " + playerScore);
			System.out.println("Computer Score: " + computerScore);
			System.out.println();
			System.out.println("Thank you for playing ScrappleJr!");
			System.exit(1);
		} // if statement
		
		// if statement ultimately determines if the word the user enters
		// is created using only from the 8 letters the user is provided 
		if (WordUtilities.findFirstWord(typedWord.toLowerCase())) {
			for (int y = 0; y < typedWord.length(); y++){
					if (uppercaseWord.charAt(y) == playerTiles[0]) {
						playerTiles[0] = '9';
						match = true;
					} // if statement
					else if (uppercaseWord.charAt(y) == playerTiles[1]) {
						playerTiles[1] = '9';
						match = true;
					} // else if statement
					else if (uppercaseWord.charAt(y) == playerTiles[2]) {
						playerTiles[2] = '9';
						match = true;
					} // else if statement
					else if (uppercaseWord.charAt(y) == playerTiles[3]) {
						playerTiles[3] = '9';
						match = true;
					} // else if statement
					else if (uppercaseWord.charAt(y) == playerTiles[4]) {
						playerTiles[4] = '9';
						match = true;
					} // else if statement
					else if (uppercaseWord.charAt(y) == playerTiles[5]) {
						playerTiles[5] = '9';
						match = true;
					} // else if statement
					else if (uppercaseWord.charAt(y) == playerTiles[6]) {
						playerTiles[6] = '9';
						match = true;
					} // else if statement
					else if (uppercaseWord.charAt(y) == playerTiles[7]) {
						playerTiles[7] = '9';
						match = true;
					} // else if statement
					else {
						System.out.println();
						System.out.println("ERROR: Invalid Word!");
						System.out.println("Player Score:     " + playerScore);
						System.out.println("Computer Score:   " + computerScore);
						System.out.println();
						System.out.println("Thank you for playing ScrappleJr!");
						System.exit(4);
					} // else statement
			} // for loop
			playerScore = playerScore + WordUtilities.getScore(typedWord.toLowerCase(), scores);
		 } // if statement
		
		else { // if the word the user entered can't be found in the word list, print an
			   // error message and exit the program
			System.out.println();
			System.out.println("ERROR: Invalid Word!");
			System.out.println("Player Score:     " + playerScore);
			System.out.println("Computer Score:   " + computerScore);
			System.out.println();
			System.out.println("Thank you for playing ScrappleJr!");
			System.exit(7);
		} // else statement
	} // getPlayerInput
	
	/** This method finds the highest scoring word based on the letters
	 * 	that it is given. This is done by calling the methods of WordUtilities.
	 */
	public void getComputerInput() {
		String bestword; // the word with the greatest point value that the computer chooses
		int compscore; // the score of the best word that the computer creates
		
		System.out.println();
		Prompt.getString("It's the computer's turn. Hit ENTER on the keyboard to continue:");	
		String compLetters = new String(computerTiles);
		
		// a String array containing all the possible words that can be created
		// with the letters that the computer is given
		String[] allwords = WordUtilities.findAllWords(compLetters.toLowerCase()); 
		
		// find the best word out of the tiles the computer is given by calling the
		// "bestWord" method in WordUtilities
		bestword = WordUtilities.bestWord(allwords, scores);
		if (bestword.equals("xyz")) { // bestWord method in WordUtilities will return "xyz" if
									  // no words can be created with the letters provided
			System.out.println("Player Score:   " + playerScore);
			System.out.println("Computer Score: " + computerScore);
			System.exit(4);
		} // if statement
		System.out.println();
		System.out.println("The computer chose: " + bestword.toUpperCase());
		compscore = WordUtilities.getScore(bestword, scores);
		
		computerScore = computerScore + compscore;
			
		// for loop will make the positions of the letters in the array that have just been
		// used into '9' to indicate that there is no character occupying that element 
		// in the computerTiles array
		for (int y = 0; y < bestword.length(); y++){
			if (bestword.toUpperCase().charAt(y) == computerTiles[0]) {
				computerTiles[0] = '9';
			} // if statement
			else if (bestword.toUpperCase().charAt(y) == computerTiles[1]) {
				computerTiles[1] = '9';
			} // else if statement
			else if (bestword.toUpperCase().charAt(y) == computerTiles[2]) {
				computerTiles[2] = '9';
			} // else if statement
			else if (bestword.toUpperCase().charAt(y) == computerTiles[3]) {
				computerTiles[3] = '9';
			} // else if statement
			else if (bestword.toUpperCase().charAt(y) == computerTiles[4]) {
				computerTiles[4] = '9';	
			} // else if statement
			else if (bestword.toUpperCase().charAt(y) == computerTiles[5]) {
				computerTiles[5] = '9';	
			} // else if statement
			else if (bestword.toUpperCase().charAt(y) == computerTiles[6]) {
				computerTiles[6] = '9';
			} // else if statement
			else if (bestword.toUpperCase().charAt(y) == computerTiles[7]) {
				computerTiles[7] = '9';	
			} // else if statement
		} // for loop
	} // getComputerInput
} // class ScrappleJr
