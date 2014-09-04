/**
 *  This program shows the user the number of times it takes to win a 
 * 	4-wheel jackpot based on the number of sides of the wheel. Numerous
 * 	trials (a number given by the user) will be run in order to get an
 * 	average number of times it takes to win given wheels of 4 to 20 sides. n
 * 	In order to present the data in a more graphical way, a histogram will
 * 	be shown. The greater the number of asteriks, the greater average number 
 * 	of spins needed to hit the jackpot. Hypothetically, should the program be
 * 	run an infinite number of times, the histogram should be shown as a smooth
 * 	curve.
 */

public class Casino {
	Dice[] myDice = new Dice[4]; // the four wheels (die) spun placed into an array
	
	static int numberofsides, numberoftrials, average, numberofspins, sum, 
										 greatestaverage, spinsperasterik;
							// numberofsides = number of sides the wheel has
							// numberoftrials = number of trials run to be run (given by user)
						    // average = average number of spins for each number of sides 
							// numberofspins = the number of spins needed to reach a jackpot for a particular trial
						    // sum = total number of spins added from each trial for a given number of sides
							// greatestaverage = the greatest average number of spins (to be used for histogram)
							// spinsperasterik = value determined by dividing the greatest average by 60
						    // in order to find out the number of spins needed to print 1 asterik for the histogram
					
					
	// stores the number of spins for each trial for a given # of 
	// sides -> row of array = # of sides (index 0 = 4 sides, etc.)									
	static int [][] spinsarray = new int[17][100000]; 
									
	// stores average number of spins for each number of sides										
	static int [] averagearray = new int [17]; 
	
	public Casino ( ) { // initialize fields
		//die1 = die2 = die3 = die4 = new Dice(6);
		
		for (int b = 0; b < 4; b++) myDice[b] = new Dice();
		numberofsides = 4; 
		greatestaverage = 0; 
	} // class Casino constructor
	
	public static void main (String [] args) {
		Casino run = new Casino();
		run.getSidesTrials(); // run getSideTrials method 
		
		// for loop will find the average number of spins needed
		// for each number of sides (4 - 20)
		for (int y = 0; y < 17; y++) {
			sum = 0; // ensure sum of averages for each number of sides starts at 0
			numberofspins = 0; // ensure number of spins for each number of sides starts at 0
			
			// will a certain number of trials based off of the user's input
			for (int x = 0; x < numberoftrials; x++) { 
				run.runTrials(); 
				
				// enter the average number of spins for each trial into the array
				spinsarray[numberofsides - 4][x] = numberofspins; 
				
				// as each trials progress, ensure the number of spins for
				// the particular trial is added to the existing value of total spins
				sum = sum + numberofspins;
			} // for loop
			
			// determine average number of spins needed for a given number 
			// of sides for the wheel
			average = sum/numberoftrials; 
			
			// enter the average number of spins for a given number of sides
			// into the array
			averagearray [y] = average; 
			
			// if any given value in the array is greater than the current
			// value for the greatest average, make the value currently
			// in the array to be the greatest average
			if (averagearray[y] > greatestaverage) greatestaverage = averagearray[y]; 
			
			// increase the number of sides of the wheel for which the trials are done 
			numberofsides++; 
		} // outer for loop
		
		// determine the number of spins needed to display 1 asterik by dividing
		// the greatest average by 60 (max. # of asteriks possible)
		spinsperasterik = greatestaverage/60; 
		
		// run the output method
		run.output(); 
	} // main
	
	/**
	 *  This method prompts the user for the number of trials to be run.
	 */
	public void getSidesTrials ( ) {
		System.out.println("\n\n");
		
		// have number of trials be determined by the getInt method
		numberoftrials = Prompt.getInt("The number of trials", 10, 100000); 
		System.out.println("\n");
	} //getSidesTrials
	
	/**
	 *  Run the number of trials given by the user and collect
	 *  the number of spins.
	 */
	public void runTrials( ) {
		// create 4 new die based off of the number of sides
		for (int g = 0; g < 4; g++) myDice[g] = new Dice(numberofsides); 
		
		spinWheel(); // run the spinWheel method
		
		// assign the number of rolls it takes to hit a jackpot
		// to the numberofspins field
		numberofspins = myDice[0].getRollCount(); 
	} // runTrials
	
	
	/**
	 * This method generates a random value for each of the 4 die and
	 * will continue doing so until all the values for the die are equal. 
	 */
	public void spinWheel ( ) { 
		boolean done = false; // indicates whether a jackpot has been reached
		do {
			for (int d = 0; d < 4; d++) myDice[d].roll();
			
			// continue to generate random values for the die
			// until they are all equal and a jackpot has been reached
			if (myDice[0].getValue() == myDice[1].getValue( )) { 
				if (myDice[1].getValue() == myDice[2].getValue( )) { 
					if (myDice[2].getValue() == myDice[3].getValue( )) { 
						done = true; // jackpot has been reached
					} // inner if statement
				} // if statement
			} // outer if statement
		} // do statement
		while(!done); 
	} // spinWheel
	
	
	/**
	 * This method is responsible for printing the results of the trials,
	 * which includes a chart listing the average number of spins needed
	 * for each number of sides for the wheel (4-20). Alongside this chart
	 * is a histogram.
	 */
	public void output() { 
		String asterik = "*"; // asterik string used in histogram
		String repeat = ""; // string printed on individual lines of histogram
		int numasteriks = 0; // number of asteriks needed for the histogram 
							 // on a particular line
		
		System.out.println("Number"); 						
		System.out.println(" of          Ave. number");     
		System.out.print("sides	     of spins");		
		
		// print the average number of spins needed to reach a jackpot
		// for each number of sides 
		for (int k = 4; k < 21; k++) {
			// reset the individual lines of the histogram for each 
			// particular number of sides
			repeat = ""; 
			System.out.println("");
			
			// right align the column displaying the number of sides for the wheel
			System.out.printf("%4s:", k); 
			
			// right align the column displaying the averages for each number of sides
			System.out.printf("%14s", averagearray[k-4]);  
		
			// have the number of asteriks needed be determined
			// by dividing the number of spins for a particular
			// number of sides by the number of spins needed for 1 asterik
			numasteriks = averagearray[k-4]/spinsperasterik; 
			
			// concatenate asteriks into the string for each line of histogram
			for (int h = 0; h < numasteriks; h++) repeat+= asterik;
			
			// left align the asteriks printed for the histogram
			System.out.printf("  %-30s", repeat); 
		} // for loop
	} // output
} // Casino
