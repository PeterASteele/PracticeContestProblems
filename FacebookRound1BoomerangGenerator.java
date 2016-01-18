import java.util.Scanner;


public class FacebookRound1BoomerangGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner  input = new Scanner(System.in);
		int n = input.nextInt();
		int m = input.nextInt();
		System.out.println(n);
		for(int a = 0; a < n;  a++){
			int[][] output = new int[m][m];
			for(int b = 0; b < m; b++){
				for(int c = b+1; c < m; c++){
					output[b][c] = (int) (Math.random()*2);
					output[c][b] = 1-output[b][c];
				}
			}
			System.out.println(m);
			for(int b = 0; b < m; b++){
				for(int c = 0; c < m; c++){
					System.out.print(output[b][c] + " ");
				}
				System.out.println();
			}
		}
		
	}

}
