import java.util.Scanner;
public class DigitSumSmall {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String test = input.nextLine();
		int sum = 0;
		for(int a = 0; a <= Integer.parseInt(test); a++){
		sum += getDigitSum("" + a);
		}
		System.out.println(sum);
	}
	public static int getDigitSum(String a){
		String b = a;
		int sum = 0;
		for(int c = 0; c < b.length(); c++){
			sum += Integer.parseInt("" + b.charAt(c));
		}
		//System.out.println(sum);
		return sum;
	}
}
