import java.util.ArrayList;
import java.util.Scanner;

public class GoogleCodeJamInfiniteHouseOfPancakes {
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
	 * 1 â‰¤ T â‰¤ 100. Small dataset
	 * 
	 * 1 â‰¤ D â‰¤ 6. 1 â‰¤ Pi â‰¤ 9. Large dataset
	 * 
	 * 1 â‰¤ D â‰¤ 1000. 1 â‰¤ Pi â‰¤ 1000.
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
			System.out.println("Case #" + (a + 1) + ": " + solve(pancakes));
		}
	}

	public static ArrayList<Integer> solveViaEatPancakes(
			ArrayList<Integer> pancakes) {
		int max = 0;
		ArrayList<Integer> pancakesNew = new ArrayList<Integer>();
		for (int a = 0; a < pancakes.size(); a++) {
			if (max < pancakes.get(a)) {
				max = pancakes.get(a);
			}
			if (pancakes.get(a) != 0) {
				pancakesNew.add(pancakes.get(a) - 1);
			} else {
				pancakesNew.add(pancakes.get(a));
			}
		}
		if (max == 1) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(-1);
			return temp;
		}

		return pancakesNew;
	}

	public static ArrayList<Integer> split(ArrayList<Integer> pancakes) {
		ArrayList<Integer> pancakesCopy = new ArrayList<Integer>();
		int max = 0;
		int maxIndex = -1;
		for (int a = 0; a < pancakes.size(); a++) {
			pancakesCopy.add(pancakes.get(a));
			if (max < pancakes.get(a)) {
				max = pancakes.get(a);
				maxIndex = a;
			}
		}
		pancakesCopy.add(max / 2);
		pancakesCopy.set(maxIndex, max - max / 2);
		if (max == 1) {
			return new ArrayList<Integer>();
		}
		return pancakesCopy;
	}

	public static int solve(ArrayList<Integer> pancakes) {
	//	System.out.println(pancakes);
		if (pancakes.size() == 0) {
			return 1;
		}
		if (pancakes.get(0) == -1) {
			return 0;
		}
		return Math.min(solve(split(pancakes)) + 1,
				solve(solveViaEatPancakes(pancakes)) + 1);
	}
}
