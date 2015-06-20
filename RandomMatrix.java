import java.util.*;
public class RandomMatrix {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Rows: ");
		int N = input.nextInt();
		System.out.print("Columns: ");
		int M = input.nextInt();
		int [][] matrix = createRandomMatrix(N, M);
		printArray(matrix);
	}
	public static int[][] createRandomMatrix(int N, int M){
		int[][] a = new int[N][M];
		for(int b = 0; b < a.length; b++){
			for(int c = 0; c < a[0].length; c++){
				int temp = (int) (Math.random() * 2);
				//temp is a random number, either 0 and 1
				temp = temp * 2;
				//temp is a random number, either 0 and 1
				temp = temp - 1;
				//temp is now a random number, either -1 and 1
				a[b][c] = temp;
			}
		}
		return a;
	}
	public static void printArray(int [][] a){
		for(int b = 0; b < a.length; b++){
			for(int c = 0; c < a[0].length; c++){
				System.out.print(a[b][c] + "\t");
			}
			System.out.println("");
		}
		
		
	}
}
