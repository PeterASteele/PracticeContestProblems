import java.util.Scanner;

public class contest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();
		int length = (int) Math.ceil(a/25.0) + 1;
		StringBuilder output = new StringBuilder();
		char last;
		char secondLast;
		
		if (length > 2){
		if (length %2 == 0){
			int max = (length - 1) * 25;
			int diff = max - a;
			if (diff%2 == 0){
				last = 'z';
			}
			else{
				last = 'y';
			}
			secondLast = (char)( 'z' - (char) (diff/2)); //little note
			
			output.append('a');
			output.append(secondLast);
			
		}
		else{
			int max = (length - 1) * 25;
			int diff = max - a;
			if (diff%2 == 0){
				last = 'a';
			}
			else{
				last = 'b';
			}
			secondLast = (char) ('z' - (char) (diff/2)); 
			output = new StringBuilder();
			output.append('a');
			output.append(secondLast);
		}
		for(int b = 0; b < Math.min(20000, length - 3); b++){
			if (b%2 == 0){
				output.append('a');
			}
			else{
				output.append('z');
			}
		}
		System.out.print(output.toString());
		StringBuilder end = new StringBuilder();
		if(length - 3 > 20000){
			for(int b = 20000; b < length-3; b++){
				if (b%2 == 0){
					end.append('a');
				}
				else{
					end.append('z');
				}
			}
		}
		end.append(last);
		
		System.out.println(end.toString());
	}
		else{
			System.out.println("a" + (char) (a+'a'));
		}
	}
}
