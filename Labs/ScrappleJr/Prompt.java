import java.util.Scanner;

/**
 *  Provide some utilities for user input.  We
 *  want to enhance the Scanner class, so that
 *  our programs can recover from "bad" input,
 *  and also provide a way to limit numerical
 *  input to a range of values.
 *
 *  @author  Daniel Wang
 *  @version  August 21, 2014
 */

public class Prompt
{
	/**
	 *  Prompts for a string from the keyboard.
	 *
	 *  @param ask  string prompt
	 *  @return    the string gotten from the keyboard
	 */
	public static String getString (String ask)
	{
		Scanner keyboard = new Scanner (System.in);
		System.out.print(ask);
		String input = keyboard.nextLine();
		return input;
	}
	
	/**
	 *  Prompts for an integer from the keyboard
	 *
	 *  @param ask  string prompt
	 *  @return    the integer gotten from the keyboard
	 */
	public static int getInt (String ask)
	{
		boolean badInput = false;
		String input = new String("");
		int value = 0;
		
		do {
			badInput = false;
			input = getString(ask);
			try {
				value = Integer.parseInt(input); // parseInt = static method inside Integer
			}// try
			catch(NumberFormatException e)
			{
				badInput = true;
			} // catch
		} // do
		while (badInput);
		
		return value;
		
	} 
	
	/**
	 *  Prompts for an integer from the keyboard within a range of values.
	 *
	 *  @param ask  string prompt
	 *  @param min  the minimum acceptable value
	 *  @param max  the maximum acceptable value
	 *  @return    the integer gotten from the keyboard
	 */
	public static int getInt (String ask, int min, int max)
	{
		int value = 0;
		do 
		{
			value = getInt(ask + "(" + min + " - " + max
						+ ") -> ");
		} // do
		while (value < min || value > max);
		return value;
	}
			
	/**
	 *  Prompts for an double from the keyboard
	 *
	 *  @param ask  string prompt
	 *  @return    the double gotten from the keyboard
	 */
	public static double getDouble (String ask)
	{
		boolean badInput = false;
		String input = "";
		double value = 0;
		do {
			badInput = false;
			input = getString(ask);
			try {
				value = Double.parseDouble(input);
			} // try
			catch (NumberFormatException e) 
			{
				badInput = true;
			} // catch
		} // do
		while (badInput);
		return value;
	}

}
// get stats: avg # of spins it takes on a 20 wheel, do 100 trials one after the other