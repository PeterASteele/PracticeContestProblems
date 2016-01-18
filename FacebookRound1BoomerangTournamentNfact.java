import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
//FacebookRound1BoomerangTournamentNfact
public class FacebookRound1BoomerangTournamentNfact {
	//This code exists to do local testing against my real code (see FacebookRound1BoomerangTournament.java), to confirm it is correct before I start the 6 minutes.
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfTestCases = input.nextInt();
		// Precompute trees of every possible 1st round bracket, without
		// ordering with respect to the next round.
		for (int testcase = 0; testcase < numberOfTestCases; testcase++) {
			int n = input.nextInt();
			boolean[][] win = new boolean[n][n];
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					win[a][b] = (input.nextInt() == 1);
				}
			}
			int[] bracket = new int[n];
			int[] bestCase = new int[n];
			int[] worstCase = new int[n];
			for(int a  = 0; a < n; a++){
				bestCase[a] = n/2+1;
				worstCase[a] = 1;
			}
			
			for (int a = 0; a < 1000000; a++) {
				bracket = new int[n];
				for (int b = 0; b < n; b++) {
					bracket[b] = b;
				}
				badShuffle(bracket);
				for (int b = n; b > 1; b = b / 2) {
					bracket = evaluate(bracket, win);
					boolean[] hold = new boolean[n];
					for (int c = 0; c < bracket.length; c++) {
						hold[bracket[c]] = true;
						if (bestCase[bracket[c]] > b / 4 + 1) {
							bestCase[bracket[c]] = b / 4 + 1;
						}
					}
					for (int c = 0; c < n; c++) {
						if (hold[c] == false) {
							//System.out.println("ttest");
							if (worstCase[c] < b / 2 + 1) {
								worstCase[c] = b / 2 + 1;
							}
						}
					}					
				}
			}
			System.out.println("Case #" + (testcase+1) + ": ");
			for (int a = 0; a < n; a++) {
				System.out.println(bestCase[a] + " " + worstCase[a]);
			}
		}
	}

	private static void badShuffle(int[] bracket) {
		for (int a = 0; a < bracket.length; a++) {
			int temp  = (int) (Math.random() * (bracket.length - a) + a);
			int hold = bracket[a];
			bracket[a] = bracket[temp];
			bracket[temp] = hold;
		}
	}

	private static int[] evaluate(int[] bracket, boolean[][] win) {
		int[] newBracket = new int[bracket.length / 2];
		for (int a = 0; a < bracket.length / 2; a++) {
			newBracket[a] = win[bracket[2 * a]][bracket[2 * a + 1]] ? bracket[2 * a]
					: bracket[2 * a + 1];
		}
		return newBracket;
	}

	private static int factorial(int n) {
		if (n == 1) {
			return 1;
		}
		return n * factorial(n - 1);
	}
}
