import java.util.ArrayList;
import java.util.Scanner;

public class GoogleCodeJamInfiniteHouseOfPancakesEfficient {
	/*
	 * Problem
	 * 
	 * At the Infinite House of Pancakes, there are only finitely many pancakes,
	 * but there are infinitely many diners who would be willing to eat them!
	 * When the restaurant opens for breakfast, among the infinitely many
	 * diners, exactly D have non-empty plates; the ith of these has Pi pancakes
	 * on his or her plate. Everyone else has an empty plate.
	 * 
	 * Normally, every minute, every diner with a non-empty plate will eat one
	 * pancake from his or her plate. However, some minutes may be special. In a
	 * special minute, the head server asks for the diners' attention, chooses a
	 * diner with a non-empty plate, and carefully lifts some number of pancakes
	 * off of that diner's plate and moves those pancakes onto one other diner's
	 * (empty or non-empty) plate. No diners eat during a special minute,
	 * because it would be rude.
	 * 
	 * You are the head server on duty this morning, and it is your job to
	 * decide which minutes, if any, will be special, and which pancakes will
	 * move where. That is, every minute, you can decide to either do nothing
	 * and let the diners eat, or declare a special minute and interrupt the
	 * diners to make a single movement of one or more pancakes, as described
	 * above.
	 * 
	 * Breakfast ends when there are no more pancakes left to eat. How quickly
	 * can you make that happen?
	 * 
	 * Input
	 * 
	 * The first line of the input gives the number of test cases, T. T test
	 * cases follow. Each consists of one line with D, the number of diners with
	 * non-empty plates, followed by another line with D space-separated
	 * integers representing the numbers of pancakes on those diners' plates.
	 * 
	 * Output
	 * 
	 * For each test case, output one line containing "Case #x: y", where x is
	 * the test case number (starting from 1) and y is the smallest number of
	 * minutes needed to finish the breakfast.
	 * 
	 * Limits
	 * 
	 * 1 ≤ T ≤ 100. Small dataset
	 * 
	 * 1 ≤ D ≤ 6. 1 ≤ Pi ≤ 9. Large dataset
	 * 
	 * 1 ≤ D ≤ 1000. 1 ≤ Pi ≤ 1000.
	 */
	public static void main(String[] args) {
		ArrayList<Integer> pancakes = new ArrayList<Integer>();
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.nextLine();
		for (int a = 0; a < n; a++) {
			int m = input.nextInt();
			pancakes = new ArrayList<Integer>();
			for (int b = 0; b < m; b++) {
				pancakes.add(input.nextInt());
			}
			int m2 = solve(pancakes);
			System.out.println("Case #" + (a+1) + ": " + m2);
		}
	}
	
	public static int solveViaEatPancakes(ArrayList<Integer> pancakes) {
		int max = 0;
		for (int a = 0; a < pancakes.size(); a++) {
			if (max < pancakes.get(a)) {
				max = pancakes.get(a);
			}
		}
		return max;
	}
	
	public static int split(ArrayList<Integer> pancakes) {
		ArrayList<Integer> pancakesCopy = new ArrayList<Integer>();
		int max = 0;
		int maxIndex = -1;
		int secondMax = -1;
		for (int a = 0; a < pancakes.size(); a++) {
			pancakesCopy.add(pancakes.get(a));
			if (secondMax < pancakes.get(a)){
				if(max < pancakes.get(a)){
					secondMax = max;
					max = pancakes.get(a);
					maxIndex = a;
				}
				else{
					secondMax = pancakes.get(a);
				}
			}
			
			if (max < pancakes.get(a)) {
				max = pancakes.get(a);
				maxIndex = a;
			}
		}
		if (max < 1){
			return 0;
		}
		if (max == 1) {
			return 1;
		}
		int[] temp = new int[((max - secondMax > 0) ? (max - secondMax): 1)];
		
		for (int d = 0; d < ((max - secondMax > 0) ? (max - secondMax): 1); d++){
			pancakesCopy.add(d+1);
			pancakesCopy.set(maxIndex, max - (d+1));
			temp[d] = solve(pancakesCopy);
		}
		
		int min = 99999;
		for(int e = 0; e < ((max - secondMax > 0) ? (max - secondMax): 1); e++){
			if (min > temp[e]){
				min = temp[e];
			}
		}
		
		//System.out.println(pancakes);
		
		
		return min;
	}

	public static int solve(ArrayList<Integer> pancakes) {
		if (pancakes.size() == 0) {
			return 9999999;
		}
		return Math.min(split(pancakes) + 1, solveViaEatPancakes(pancakes));
	}
}
