import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
//https://www.facebook.com/hackercup/problem/512731402225321/
public class FacebookRound1Yachtzee {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfTestCases = input.nextInt();
		for (int testcase = 0; testcase < numberOfTestCases; testcase++) {
			int n = input.nextInt();
			long start = input.nextInt();
			long end = input.nextInt();
			long[] yachtParts = new long[n];
			long totalCost = 0;
			for (int a = 0; a < n; a++) {
				yachtParts[a] = input.nextInt();
				totalCost += yachtParts[a];
			}
			long startYachtFinished = start / totalCost;
			long finishYacht = end / totalCost;
			long startYachtmod = start % totalCost;
			long finishYachtmod = end % totalCost;
			double EV1Yacht = getEV1Yacht(yachtParts, totalCost);
			long yachtNumbers = finishYacht - startYachtFinished - 1;
			// edge 1:
			long sum = 0;
			long sumAlongRelevantInterval = 0;
			double EVCalcWeightSum = 0;
			boolean beforeStart = true;
			for (int a = 0; a < yachtParts.length; a++) {
				if (beforeStart) {
					if (sum < startYachtmod
							&& sum + yachtParts[a] > startYachtmod) {
						beforeStart = false;
						// unfortunately, we have to do the sum of the series
						// that starts at:
						long startRange = startYachtmod - sum;
						long endRange = yachtParts[a];
						// sum up, not including endRange.
						double sumAlongInterval = (startRange + endRange)
								* (endRange - startRange) / 2.0;
						EVCalcWeightSum += sumAlongInterval;
						sumAlongRelevantInterval += endRange - startRange;
					}
				} else {
					EVCalcWeightSum += yachtParts[a] * yachtParts[a]
							/ 2.0;
					sumAlongRelevantInterval += yachtParts[a];
				}
				sum += yachtParts[a];
			}
			// starting interval done
			// compute middle chunks, 1 yacht at a time
			EVCalcWeightSum += EV1Yacht * totalCost * yachtNumbers;
			sumAlongRelevantInterval += totalCost * yachtNumbers;
			// yachtNumbers can be -1! this is fine.
			// endingInterval
			boolean beforeEnd = true;
			double cumulativeSum = 0;
			for(int a = 0; a < yachtParts.length; a++){
				if(cumulativeSum <= finishYachtmod && cumulativeSum + yachtParts[a] > finishYachtmod){
					double seriesSum = finishYachtmod-cumulativeSum;
					EVCalcWeightSum += seriesSum * seriesSum/ 2.0;
					sumAlongRelevantInterval += seriesSum;
					break;
				}
				EVCalcWeightSum += yachtParts[a] * yachtParts[a]
						/ 2.0;
				sumAlongRelevantInterval += yachtParts[a];
				cumulativeSum += yachtParts[a];
			}
			System.out.print("Case #"+(testcase+1)+": ");
			System.out.printf("%.9f\n", EVCalcWeightSum/sumAlongRelevantInterval);
		}

	}

	private static double getEV1Yacht(long[] yachtParts, long totalCost) {
		double total = 0.0;
		for (int a = 0; a < yachtParts.length; a++) {
			total += yachtParts[a] * yachtParts[a] / 2.0;
		}
		//.out.println(total  + " " + totalCost);
		return total / totalCost;
	}
}
