package nameSurferPackage;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class NameSurfer {

	// Main reads in data from the file and calls other driver methods
	public static void main(String[] args) throws IOException {
		int option;
		int i = 0;
		NameRecord NRObj[] = new NameRecord[4430];
		FileReader fr = null;
		BufferedReader br = null;
		Scanner scnr = new Scanner(System.in);
		
		try {
			fr = new FileReader("C:\\Users\\Bret Elphick\\Downloads\\name_data.txt");
			br = new BufferedReader(fr);
			String line;
			
			while ((line = br.readLine()) != null) {
				NRObj[i] = new NameRecord(line);
				i++;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				br.close();
			}
		}
		
		option = choice(scnr);
		optionHandling(option, NRObj, scnr);

		scnr.close();
				
		
	}
		
	// Prints the menu and returns the users choice
	public static int choice(Scanner scnr) {
		int choice;
		System.out.print("1 - Find best year for a name");
		System.out.print("\n2 - Find best rank for a name");
		System.out.print("\n3 - Plot popularity of a name");
		System.out.print("\n4 - Clear plot");
		System.out.print("\n5 - Quit");
		System.out.print("\nEnter your selection: ");
		choice = scnr.nextInt();
		while (choice > 5 || choice < 1) {
			System.out.print("Enter a choice between 1 and 5: ");
			choice = scnr.nextInt();
		}
		return choice;
	}
	
	// Finds the index of the user entered name (binary search)
	public static int findIndex(NameRecord[] NRObj, String userName, int startIndex, int lastIndex) {
		int index = 0;
		int midpoint;
		int range; 
		
		range = (lastIndex - startIndex) + 1;
		midpoint = (lastIndex + startIndex) / 2;
		
		
		if (userName.equals(NRObj[midpoint].getName())) {
			index = midpoint;
		}
		else if (range == 1) {
			index = -1;
		}
		else {
			if (userName.compareTo(NRObj[midpoint].getName()) < 0) {
				index = findIndex(NRObj, userName, startIndex, midpoint);
			}
			else {
				index = findIndex(NRObj, userName, midpoint + 1, lastIndex);
			}
		}
		
		return index;
	}
	
	// Prompts user to eneter a name and returns it
	public static String getName(Scanner scnr) {
		String userName;
		
		System.out.print("Please enter a name (capitalize the first letter): ");
		userName = scnr.next();
		
		return userName;
	}
	
	// Handles the choice entered by the user by calling the corresponding methods in NameRecord class
	public static void optionHandling(int option, NameRecord[] NRObj, Scanner scnr) {
		int index;
		String userName;
		int decade;
		while (option != 5) {
			 if (option == 1) {
				 userName = getName(scnr);
				 index = findIndex(NRObj, userName, 0, NRObj.length-1);
				 System.out.print("The best year for the name " + NRObj[index].getName() + " was " + NRObj[index].bestYear() + ".");

			 }
			 else if (option == 2) {
				 userName = getName(scnr);
				 index = findIndex(NRObj, userName, 0, NRObj.length-1);
				 // Converts the returned year to a decade
				 decade = (NRObj[index].bestYear() - 1900) / 10;
				 System.out.print("The best rank for the name " + NRObj[index].getName() + " is " + NRObj[index].getRank(decade) + ".");
			 }
			 else if (option == 3) {
				 userName = getName(scnr);
				 index = findIndex(NRObj, userName, 0, NRObj.length-1);
				 NRObj[index].plot();
				 System.out.print("The name " + NRObj[index].getName() + " has been plotted.");
			 }
			 else { 
				 StdDraw.clear();
				 System.out.print("The plot has been cleared.");
			 }
			 System.out.print("\n\n");
			 option = choice(scnr);
		}
		System.out.print("Thank you for using the Name Surfer program!");
	}
}
