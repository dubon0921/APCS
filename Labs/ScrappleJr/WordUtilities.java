import java.util.Scanner;

/**
 *  Finds all the words that can be formed with a list of letters,
 *  then finds the word with the highest Scrabble score.
 *
 */
 
public class WordUtilities
{
	static String letters;
	static String infileName = "wordlist.txt";
	public static void main (String [] args)
	{
		String input = getInput();
		String [] word = findAllWords(input);
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoretable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoretable);
		System.out.println("\n\n\n" + best + "\n\n\n");
	}
	
	/**
	 *  Enter a list of 3 to 12 letters. It also checks
	 *  all letters to insure they fall between 'a' and 'z'.
	 *
	 *  @return  A string of the letters
	 */
	public static String getInput ( )
	{
		boolean done;
		do {
			done = true;
			letters = Prompt.getString("List of 3 to 12 letters for search: \t");
			if (letters.length() < 3 || letters.length() > 12) {
				done = false;
			} // if statement
			
			// check each letter
			for (int cnt = 0; cnt < letters.length(); cnt++) {
				char c = letters.charAt(cnt);
				if (c < 'a' || c > 'z') done = false;
			} // for loop
		} while (!done);
		return letters;
	}
	
	/**
	 *  Find all words that can be formed by a list of letters.
	 *
	 *  @param letters  String list of letters
	 *  @return   An array of strings with all words found.
	 */
	public static String [] findAllWords (String letters)
	{		
		String word;
		String [] words = new String[1000];
		int count = 0;
		
		Scanner infile = OpenFile.openToRead(infileName);
		
		while (infile.hasNext()) {
			word = infile.next();
			int len = word.length();
			if (matchletters(word, letters)) {
				if (word.length() > 3 && word.length() < 9) {
					words[count++] = word;
				}
			}
			//else System.out.print("NO MATCH!");
		} // while loop		
		
		String [] found = new String [count];
		for (int i = 0; i < count; i++) 
			found[i] = words[i];
		return found;
			
	}
	public static boolean findFirstWord (String myword)
	{		
		
		String word;
		String [] words = new String[1000];
		int count = 0;
		String line;
		Scanner keyscanner = OpenFile.openToRead(infileName);
		
		while (keyscanner.hasNextLine()) { // while the key file has another line...
			if (keyscanner.hasNext()) { // if there is another element in a given line of the key file...
				 line = keyscanner.nextLine();
				
				if (line.equals(myword)) {
					 return true;
					// break;
				}
			} // if statement
			
			else if (keyscanner.hasNextLine())keyscanner.nextLine();
		} // while
		return false;
			
	}
	
	
	/**
	 *  Determines if a word can be formed by a list of letters.
	 *
	 *  @param temp  The word to be tested.
	 *  @param letters  A string of the list of letter
	 *  @return   True if word can be formed, false otherwise
	 */
	public static boolean matchletters (String word, String letters)
	{
		for(int i = 0; i<word.length(); i++){
                int ind = letters.indexOf(word.charAt(i));
                if(ind == -1)
                        return false;
                letters = letters.substring(0, ind) + letters.substring(ind+1);
        }
        return true;
	}
	
	/**
	 *  Print the words found to the screen.
	 *
	 *  @param words  The string array containing the words.
	 */
	public static void printWords (String [] words)
	{
		System.out.println();
		for (int i = 0; i < words.length; i++) {
				System.out.printf("%s\t", words[i]);
				if ((i + 1)% 10 == 0) System.out.println(); // print a new line every 10 words	
		} // for loop
	}
	
	/**
	 *  Finds the highest scoring word according to Scrabble rules.
	 *
	 *  @param word  An array of words to check.
	 *  @param scoretable  An array of 26 integer scores in letter order.
	 *  @return   The word with the highest score.
	 */
	public static String bestWord (String [] word, int [] scoretable)
	{
		  String temp = "xyz";
		  int highestscore = 0, wordnumber = 0; 
			for (int i = 0; i < word.length; i++) { 
            if (getScore(word[i], scoretable) > highestscore) { 
                highestscore = getScore(word[i], scoretable); 
                wordnumber = i; 
               } 
        } 
        if (highestscore == 0) {
			System.out.println("No valid word can be made from these tiles!");
			System.out.println();
			System.out.println("Thank you for playing ScrappleJr!");
			return temp;		
		}
  
        return word[wordnumber]; 
	}
	
	/**
	 *  Calculates the score of a word according to Scrabble rules.
	 *
	 *  @param word  The word to score
	 *  @param scoretable  The array of 26 scores for alphabet.
	 *  @return   The integer score of the word.
	 */
	public static int getScore (String word, int [] scoretable)
	{
		int score = 0; 
        for (int counter = 0; counter < word.length(); counter++) { 
            char letter = word.charAt(counter); 
            int scorenum = (int)letter - 97; 
            score += scoretable[scorenum]; 
        } 
        
        return score; 
	}
}
