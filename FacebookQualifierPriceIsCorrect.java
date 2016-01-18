import java.util.Scanner;
//https://www.facebook.com/hackercup/problem/881509321917182/
public class FacebookQualifierPriceIsCorrect {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int testCases = input.nextInt();
		for(int a = 0; a < testCases; a++){
			int numberOfIntegers = input.nextInt();//(int)(Math.random()*200000);//
			long threshhold = input.nextLong(); //(int)(Math.random() * 1000000000);//need sequences less than or equal to this.
			long[] listOfIntegers = new long[numberOfIntegers];
			for(int b = 0; b < numberOfIntegers; b++){
				listOfIntegers[b] = input.nextLong();//(int)(Math.random() * 1000000000);//
			}
			long answerGreedy = greedy(listOfIntegers, threshhold);
			//long answerDP = dp(listOfIntegers, threshhold);
			//if(answerGreedy != answerDP){
			//	System.out.println("HUGE PROBLEM!!!!!!!! " + answerGreedy + " " + answerDP);
			//}
			System.out.println("Case #"+(a+1)+": " + answerGreedy);
		}
	}

	private static long dp(long[] listOfIntegers, long threshhold) {
		long total = 0;
		for(int a = 0; a < listOfIntegers.length; a++){
			long tempSum = 0;
			for(int b = a; b < listOfIntegers.length; b++){
				tempSum += listOfIntegers[b];
				if(tempSum <= threshhold){
					total++;
				}
			}
		}
		return total;
	}

	private static long greedy(long[] listOfIntegers, long threshhold) {
		long prevPossibilities = 0;
		long totalPossibilities = 0;
		int length = 0;
		long currentSum = 0;
		for(int b = 0; b < listOfIntegers.length; b++){
			if(currentSum + listOfIntegers[b] <= threshhold){
				prevPossibilities = totalPossibilities;
				length++;
				currentSum += listOfIntegers[b];
				totalPossibilities += length;
				if(totalPossibilities < prevPossibilities){
					System.out.println("OVERFLOW WARNING");
				}
			}
			else{
				currentSum -= listOfIntegers[b-length];
				length--;
				b--;
			}
		}
		return totalPossibilities;
	}
}
