/**
 *  This program is an example of different parameter passing and the
 *  effects of accessing and changing data.
 *
 *  @author Mr Greenstein
 *  @version August 27, 2014
 */

public class Parameters {
	public static void main(String[] args) { 	
		
		// 1. Passing command line arguments
		System.out.println("\nNumber of arguments = " + args.length);
		for (int i = 0; i < args.length; i++)
			System.out.printf("args[%d] = %s\t", i, args[i]);
		System.out.println("\n\n");
		
		Parameters p = new Parameters();

		// 2. Simple examples
		p.runSimple();

	 	// 3. String examples
		p.runStringExamples();
		
		// 4. Arrays and objects
		p.veryInteresting();
	}
	
	public void runSimple() {
				
		int age = 47, weight = 181, graduationYear = 1984;
		System.out.printf("\nInitial print: Age = %d,\tWeight = %d,\tGraduation Year "
					+ "%d\n\n", age, weight, graduationYear);
		
		simpleOne(age, weight, graduationYear);
		System.out.printf("\nsimpleOne return: Age = %d,\tWeight = %d,\tGraduation Year "
				+ "%d\n\n", age, weight, graduationYear);
		
		age = simpleTwo(age, weight, graduationYear);
		System.out.printf("\nsimpleTwo return: Age = %d,\tWeight = %d,\tGraduation Year "
				+ "%d\n\n", age, weight, graduationYear);
		
	}
	
	/**
	 *  Does NOT change the values.
	 *
	 *  @param a  age of the person
	 *  @param w  weight of the person
	 *  @param g  graduation year
	 */
	 public void simpleOne(int a, int w, int g) {
		System.out.printf("\nsimpleOne start: Age = %d,\tWeight = %d,\tGraduation Year "
					+ "%d", a, w, g);
		a++;
		w = w + 9;
		g = 1985;
		System.out.printf("\nsimpleOne end: Age = %d,\tWeight = %d,\tGraduation Year "
					+ "%d", a, w, g);
	 }
	
	/**
	 *  Returns the new age.
	 *
	 *  @param a  age of the person
	 *  @param w  weight of the person
	 *  @param g  graduation year
	 */
	 public int simpleTwo(int a, int w, int g) {
		System.out.printf("\nsimpleTwo start: Age = %d,\tWeight = %d,\tGraduation Year "
					+ "%d", a, w, g);
		a = a + 12;
		w = w + 9;
		g = 1985;
		System.out.printf("\nsimpleTwo end: Age = %d,\tWeight = %d,\tGraduation Year "
					+ "%d", a, w, g);
		return a;
	 }
	 
	 /**
	  *  Runs String examples
	  */
	 public void runStringExamples() {
	 		 	
	 	String str = "All good things must come to an end.";
	 	stringExampleOne(str);
	 	System.out.println("stringExampleOne return: str = " + str);
	 	System.out.println("\n\n");

	 	String str2 = "And your little dog too.";
	 	str2 = stringExampleTwo(str2);
	 	System.out.println("stringExampleTwo return: str = " + str2);
	 	System.out.println("\n\n");
	 }
	 
	 /**
	  *   Does NOT change the String.
	  *
	  *  @param  s  String to manipulate.
	  */
	public void stringExampleOne(String s) {
		System.out.println("\nstringExampleOne start: s = " + s);
		s = s.substring(s.indexOf("c"));
		System.out.println("stringExampleOne end: s = " + s);
	}
	 
	 /**
	  *   Returns a different String.
	  *
	  *  @param  s  String to manipulate.
	  */
	public String stringExampleTwo(String s) {
		System.out.println("\nstringExampleTwo start: s = " + s);
		s = s.substring(s.indexOf("l"));
		System.out.println("stringExampleTwo end: s = " + s);
		return s;
	}

	/**
	 *  Examples of arrays and objects as parameters
	 */
	public void veryInteresting ( )
	{
		double [] array = {2.5, 11.54, 16.32, 45.6};
		printArray(array);
		changeArrayOne(array);
		printArray(array);
		
		changeArrayTwo(array);
		printArray(array);
		
		System.out.println("\n");
		Person me = new Person("Susan", 16, 'F');
		System.out.println("\n" + me);
		
		changePersonOne(me);
		System.out.println("\n" + me);
		
		changePersonTwo(me);
		System.out.println("\n" + me);
		
		me = changePersonThree(me);
		System.out.println("\n" + me);
		System.out.println("\n\n");
	}
	
	/*
	 *  DOES NOT change the original array.
	 */
	public void changeArrayOne (double [] arr)
	{
		arr = new double [6];
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = (double)(i * i);
		}
	}
	
	/**
	 *  Changes the original array.
	 *
	 *  @param arr  the array to work on
	 */
	public void changeArrayTwo (double [] arr)
	{
		for (int i = 1; i < arr.length; i++)
		{
			double temp = arr[i-1];
			arr[i-1] = arr[i];
			arr[i] = temp;
		}
	}
	
	/**
	 *  prints a double array.
	 *
	 *  @param arr  the array to work on
	 */
	public void printArray (double [] arr)
	{
		System.out.println();
		for(int i = 0; i < arr.length; i++)
		{
			System.out.printf("%.2f  ", arr[i]);
		}
		System.out.println();
	}
	
	/**
	 *  DOES NOT change the original Person.
	 *
	 *  @param person the Person object to work on
	 */
	public void changePersonOne (Person person)
	{
		person = new Person("Humphrey", 18, 'M');
	}
	
	/**
	 *  Changes the original Person using changePerson()
	 *
	 *  @param person the Person object to work on
	 */
	public void changePersonTwo (Person person)
	{
		person.changePerson("Tracy", 17, 'F');
	}
	
	/**
	 *  Returns a new Person
	 *
	 *  @param person the Person object to work on
	 *  @return   the new Person
	 */
	public Person changePersonThree (Person person)
	{
		person = new Person("Sally", 15, 'M');
		return person;
	}
}

/**
 *  The class describing a person.
 */
class Person
{
	private String name;
	private int age;
	private char gender;
	
	public Person ( )
	{
		name = new String("Jiminy Cricket");
		age = 12;
		gender = 'M';
	}
	
	public Person (String n, int a, char g)
	{
		name = n;
		age = a;
		gender = g;
	}
	
	public void changePerson (String n, int a, char g)
	{
		name = n;
		age = a;
		gender = g;
	}
	
	public String toString ( )
	{
		return (name + ", " + age + ", " + gender);
	}
}
