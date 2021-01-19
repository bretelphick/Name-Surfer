package nameSurferPackage;
import java.util.Scanner;


public class NameRecord {
	private int[] ranks;  // Holds the ranks for the name over the decades
	private String name;  // Holds the name
	private final int START = 1900; // Constant variable for the start year in file
	private final int DECADES = 11;  // Constant variable for the number of decades in file
	
	// Constructor sets the values of the instance variables
	public NameRecord(String line){
		Scanner scnr = new Scanner(line);
		name = scnr.next();
		ranks = new int[DECADES];
		for (int i = 0; i < DECADES; i++) {
			ranks[i] = scnr.nextInt();
		}
		scnr.close();
	}
	
	// Returns the name instance variable
	public String getName() {
		return name;
	}
	
	// Returns the rank of name given a decade
	public int getRank(int decade) {
		int rank;
		rank = ranks[decade];
		return rank;
		}
	
	// Methods returns the bestYear for name
	public int bestYear() {
		int bestRank = ranks[0];
		int bestYear = 0;
		
		// Finds the first non-zero rank
		for (int j = 0; j < DECADES; j++) {
			if (ranks[j] != 0) {
				bestYear = j;
				bestRank = ranks[bestYear];
				break;
			}	
		}
	
		// Finds the best rank using first non zero rank
		for (int i = bestYear + 1; i < DECADES; i++) {
			if ((ranks[i] < bestRank) && (ranks[i] != 0)) {
				bestYear = i;
				bestRank = ranks[bestYear];
			}
		}
		bestYear = (bestYear*10) + START;
		return bestYear;
	}
	
	// Plots the rank of name over the decades as a bar graph
	public void plot(){
		double incrementX = -0.1;
		double barHeight;
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.text(0.5, 1, "X-Axis: Decade, Y-Axis: Rank");
		StdDraw.setPenColor(StdDraw.GREEN);
		
		for (int i = 0; i < DECADES; i++) {
			incrementX = incrementX + 0.1;
			
			if (ranks[i] == 0) {
				barHeight = 0;
			}
			else {
				barHeight = 1 - (ranks[i]*0.001);
			}
			StdDraw.filledRectangle(incrementX, 0, 0.02, barHeight);
		}
	}
}
