/**
 *  You have a Jackpot machine. There are four "wheels" showing
 *  numbers. Prompt the user for the number of sides of each wheel.
 *  The machine generates four numbers until all four are the same.
 */

public class LittleJackpot
{
	private Dice die1, die2, die3, die4;
	private int numberofsides;
	
	// Constructor
	public LittleJackpot ( )
	{
		die1 = die2 = die3 = die4 = new Dice(6);
		numberofsides = 6;
	}
	
	public static void main (String [] args)
	{
		LittleJackpot run = new LittleJackpot();
		run.getSidesTrials();
		run.runTrials();
	}
	
	/**
	 *  Prompt user for number of sides on each wheel.
	 */
	public void getSidesTrials ( )
	{
		System.out.println("\n\n");
		numberofsides = Prompt
					.getInt("Please enter the number of sides for each wheel",4,100);
		System.out.println("\n");
	}
	
	/**
	 *  Run the number of trials collecting the number of spins.
	 */
	public void runTrials() {
		die1 = new Dice(numberofsides);
		die2 = new Dice(numberofsides);
		die3 = new Dice(numberofsides);
		die4 = new Dice(numberofsides);
		System.out.println("\n\n");
		spinWheel();
		System.out.println("It took " + die1.getRollCount() + " spins to win "
					+ "the jackpot.\n\n");
	}
	
	/**
	 *  Spin each wheel until all the numbers are equal.
	 */
	public void spinWheel ( )
	{
		boolean done = false;
		do
		{
			die1.roll();
			die2.roll();
			die3.roll();
			die4.roll();
			System.out.printf("%4d %4d %4d %4d%n", 
				die1.getValue(), die2.getValue(), die3.getValue(),
				die4.getValue());
			if (die1.getValue() == die2.getValue())
			{
				if (die2.getValue() == die3.getValue())
				{
					if (die3.getValue() == die4.getValue())
					{
						done = true;
					}
				}
			}
		}
		while(!done);
	}
}
