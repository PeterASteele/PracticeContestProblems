import java.util.*;
public class EncodedMessage {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int numberOfCases = input.nextInt();
		input.nextLine();
		String[] tests = new String[numberOfCases];
		for(int a = 0; a < numberOfCases; a++){
			tests[a] = input.nextLine();
		}
		char[][] testCase;
		int length;
		String decodedMessage;
		for(int a = 0; a < numberOfCases; a++){
			decodedMessage = "";
			length = (int) Math.sqrt(tests[a].length());
			testCase = new char[length][length];
			for(int b = 0; b < tests[a].length(); b++){
				testCase[b/length][b%length] = tests[a].charAt(b);
			}
			for(int b = 1; b <= length; b++){
				for(int c = 0; c < length; c++){
					decodedMessage += testCase[c][length-b];
				}
			}
			System.out.println(decodedMessage);
		}
	}
}
