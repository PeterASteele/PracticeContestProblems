import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
//https://www.facebook.com/hackercup/problem/1424196571244550/
public class FacebookRound1BoomerangTournament {
	// Special Note: make sure to use -Xmx1024m!!!! If you don't do this we run
	// out of memory.
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfTestCases = input.nextInt();
		//Precompute trees of every possible 1st round bracket, without ordering with respect to the next round.
		BitSet availableNumbers16 = new BitSet(16);
		BitSet availableNumbers8 =  new BitSet(8);
		BitSet availableNumbers4 =  new BitSet(4);
		for (int a = 0; a < 16; a++) {
			availableNumbers16.set(a);
		}
		for(int  a  = 0; a < 8; a++){
			availableNumbers8.set(a);
		}
		for(int  a  = 0; a < 4; a++){
			availableNumbers4.set(a);
		}
		GameTree game16 = getAllGames(availableNumbers16);
		GameTree game8 = getAllGames(availableNumbers8);
		GameTree game4 = getAllGames(availableNumbers4);
		for (int testcase = 0; testcase < numberOfTestCases; testcase++) {
			int n = input.nextInt();
			boolean[][] win = new boolean[n][n];
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					win[a][b] = (input.nextInt() == 1);
				}
			}
			BitSet availableNumbers = new BitSet(n);
			for (int a = n - 1; a >= 0; a--) {
				availableNumbers.set(a);
			}
			// step 1, generate all possible 1st rounds with simplifications.
			// Sweet, we managed to store all possible games! (barely...)
			// System.out.println(games); (perfect! Also, I get the
			// binaryIndexTree joke, but I do it with pointers instead.)
			HashSet<BitSet> afterRound = new HashSet<BitSet>();
			GameTree games = null;
			if(n == 16){
				games = game16;
			}
			if(n == 8){
				games = game8;
			}
			if(n == 4){
				games = game4;
			}
			if(games == null){
				games = getAllGames(availableNumbers);
			}
			evaluate(games, afterRound, win);
			int[] bestEach = new int[n];
			int[] worstEach = new int[n];
			for (int a = 0; a < n; a++) {
				worstEach[a] = 1;
			}
			for (int a = 0; a < n; a++) {
				bestEach[a] = n / 2 + 1;
			}
			for (int a = 2; a < n; a = a * 2) {
				HashSet<BitSet> nextRound = new HashSet<BitSet>();
				for (BitSet storage : afterRound) {
					ArrayList<Integer> map = new ArrayList<Integer>();
					for (int bitSetIterate = 0; bitSetIterate < n; bitSetIterate++) {
						if (storage.get(bitSetIterate)) {
							map.add(bitSetIterate);
							if (n / a / 2 + 1 < bestEach[bitSetIterate]) {
								bestEach[bitSetIterate] = n / a / 2 + 1;
							}
						} else {
							if (n / (a-1) / 2 + 1 > worstEach[bitSetIterate]) {
								worstEach[bitSetIterate] = n / (a-1) / 2 + 1;
							}
						}
					}
					GameTree tempGame = null;
					if(map.size() == 8){
						tempGame = game8;
						evaluate(tempGame, nextRound, win, map);
					}
					else{
						tempGame = getAllGames(storage);
						evaluate(tempGame, nextRound, win);
					}
				}
				afterRound = nextRound;
			}
			for (BitSet storage : afterRound) {
				for (int bitSetIterate = 0; bitSetIterate < n; bitSetIterate++) {
					if (storage.get(bitSetIterate)) {
						if (1 < bestEach[bitSetIterate]) {
							bestEach[bitSetIterate] = 1;
						}
					} else {
						if (2 > worstEach[bitSetIterate]) {
							worstEach[bitSetIterate] = 2;
						}
					}
				}
			}
			System.out.println("Case #" + (testcase+1) + ": ");
			for (int a = 0; a < n; a++) {
				System.out.println(bestEach[a] + " " + worstEach[a]);
			}
		}
	}

	private static void evaluate(GameTree games,
			HashSet<BitSet> nextRound, boolean[][] win, ArrayList<Integer> map) {
		BitSet temp = new BitSet(win.length);
		for (int b = 0; b < games.games.size(); b ++) {
			GameTree a = games.games.get(b);
			evaluateHelper(a, temp, nextRound, win, map);
		}
	}

	private static void evaluateHelper(GameTree a, BitSet temp,
			HashSet<BitSet> nextRound, boolean[][] win, ArrayList<Integer> map) {
		int tempWin = getWin(a.current, win, map);
		temp.set(tempWin);
		for (int c = 0; c < a.games.size(); c++) {
			GameTree b = a.games.get(c);
			evaluateHelper(b, temp, nextRound, win, map);
		}
		if (a.games.size() == 0) {
			BitSet temp2 = new BitSet(win.length);
			for(int d = 0; d < win.length; d++){
				if(temp.get(d)){
					temp2.set(map.get(d));
				}
			}
			nextRound.add(temp2);
		}
		temp.set(tempWin,  false);		
	}

	private static int getWin(short current, boolean[][] win,
			ArrayList<Integer> map) {
		int a = current / 16;
		int b = current % 16;
		return win[map.get(a)][map.get(b)] ? a : b;
	}

	private static void evaluate(GameTree games,
			HashSet<BitSet> nextRound, boolean[][] win) {
		BitSet temp = new BitSet(win.length);
		for (int b = 0; b < games.games.size(); b ++) {
			GameTree a = games.games.get(b);
			evaluateHelper(a, temp, nextRound, win);
		}
	}

	private static void evaluateHelper(GameTree a, BitSet temp,
			HashSet<BitSet> nextRound, boolean[][] win) {
		int tempWin = getWin(a.current, win);
		temp.set(tempWin);
		for (int c = 0; c < a.games.size(); c++) {
			GameTree b = a.games.get(c);
			evaluateHelper(b, temp, nextRound, win);
		}
		if (a.games.size() == 0) {
			BitSet temp2 = (BitSet) temp.clone();
			nextRound.add(temp2);
		}
		temp.set(tempWin,  false);
	}

	private static GameTree getAllGames(BitSet availableNumbers) {
		GameTree output = new GameTree();
		int n = getFirstTrue(availableNumbers);
		if (n == -1) {
			return output;
		}
		availableNumbers.set(n, false);
		for (int a = n + 1; a < availableNumbers.size(); a++) {
			if (availableNumbers.get(a)) {
				BitSet tempBoolArray = (BitSet) availableNumbers.clone();
				tempBoolArray.set(a, false);
				GameTree temp = getAllGames(tempBoolArray);
				temp.addCurrent(compress(n, a));
				output.addSubGame(temp);
			}
		}
		return output;
	}

	private static int getFirstTrue(BitSet availableNumbers) {
		for (int a = 0; a < availableNumbers.size(); a++) {
			if (availableNumbers.get(a)) {
				return a;
			}
		}
		return -1;
	}

	public static String getString(short data) {
		return "(" + data / 16 + " " + data % 16 + ")";
	}

	public static short compress(int one, int two) {
		short data = (short) (16 * Math.min(one, two) + Math.max(one, two));
		return data;
	}

	public static int getWin(short input, boolean[][] win) {
		int a = input / 16;
		int b = input % 16;
		return win[a][b] ? a : b;
	}

	public static class GameTree {
		short current;
		ArrayList<GameTree> games;

		public GameTree() {
			current = -1;
			games = new ArrayList<GameTree>(16);
		}

		public void addCurrent(short a) {
			current = a;
		}

		public void addSubGame(GameTree a) {
			games.add(a);
		}

		public String toString() {
			StringBuilder a = new StringBuilder();
			if (current != -1) {
				a.append(getString(current) + "\n");
			}
			for (GameTree b : games) {
				a.append(b.toStringHelper(""));
			}
			return a.toString();
		}

		public String toStringHelper(String tabs) {
			StringBuilder a = new StringBuilder();
			if (current != -1) {
				a.append(tabs + getString(current) + "\n");
			} else {
				a.append(tabs + "DNE \n");
			}
			for (GameTree b : games) {
				a.append(b.toStringHelper(tabs + "\t"));
			}
			return a.toString();
		}
	}
}
