
import java.math.BigInteger;
import java.util.*;
public class DigitSum{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfCases = input.nextInt();
		for(int a = 0; a < numberOfCases; a++){
		String test = input.next();
		if (test.equals("0")){
			test = "1";
		}
		String test2 = input.next();
		BigInteger sum = new BigInteger("0");
		sum = sum.add(new BigInteger("" + getDigitSumZeroToA("" + new BigInteger(test).subtract(BigInteger.ONE))));
		BigInteger sum2 = new BigInteger("" + getDigitSumZeroToA("" + test2));
		
		System.out.println(sum2.subtract(sum));
		}
	}
	public static BigInteger getDigitSumZeroToA(String a){
		String b = a;
		BigInteger sum = new BigInteger("0");
		String temp = "";
		BigInteger chunk1 = new BigInteger("0");
		BigInteger chunk2 = new BigInteger("0");
		BigInteger chunk3 = new BigInteger("0");
		
		for(int c = 0; c < b.length(); c++){
			temp = a.substring(0, a.length()-1-c);
			chunk1 = BigInteger.ZERO;
			if(!temp.equals("")){
				chunk1 = new BigInteger(temp).multiply(new BigInteger("45").multiply((new BigInteger("10").pow(c))));
			}
			int digit2 = Integer.parseInt("" + a.charAt(a.length()-1-c));
			if (digit2 != 0) digit2--;
			chunk2 = new BigInteger("" + sum0_9(digit2)).multiply((new BigInteger("10").pow(c)));
			
			int digit = Integer.parseInt("" + (a.charAt(a.length()-1-c)));
						
			chunk3 = new BigInteger("" + digit).multiply((new BigInteger((a.substring(a.length()-c, a.length()).equals(""))?"0":a.substring(a.length()-c, a.length())).add(new BigInteger("1"))));
		
			
			sum = sum.add(chunk1.add(chunk2.add(chunk3)));
		
		}
		
	
		
		return sum;
	}
	public static int sum0_9(int a){
		return a * (a+1) / 2; 
	}
}

