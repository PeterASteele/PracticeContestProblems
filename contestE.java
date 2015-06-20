import java.util.ArrayList;
import java.util.Scanner;


public class contestE {
	
	public static void main(String[] args)
	{
		
		
		new contestE();
		Scanner s = new Scanner(System.in);
		int col = s.nextInt();
		int rows = s.nextInt();
		
		int[][] array = new int[rows][col];
		
		for (int x =0; x < rows; x++)
		{
			for (int y =0; y < col; y++)
			{
				array[x][y] = s.nextInt();
			}
		}
		
		
		int P1 =  -1;
		outer:
		for (int x = 0; x < rows; x++)
		{
			for (int y = x%2; y < col; y+=2)
			{
				int oldP1 = P1;
				if (array[x][y] != 0 && (x%2==0))
				{
					
					P1 = array[x][y] % 2;
					
				}
				else{
					P1 = (array[x][y]+1) % 2;
				}
				if (oldP1 != -1 && P1 != oldP1)
				{
					System.out.println(-1);
					System.exit(0);
				}
			}
		}
		
		int P2 = -1;
		outer2:
			for (int x = 0; x < rows; x++)
			{
				for (int y = (x + 1)%2; y < col; y+=2)
				{
					int oldP2 = P2;
					if (array[x][y] != 0 && x % 2 == 1)
					{
						P2 = array[x][y] % 2;
					}
					else{
						P2 = (array[x][y] + 1) % 2;
					}
					if (oldP2 != -1 && P1 != oldP2)
					{
						System.out.println(-1);
						System.exit(0);
					}
				}
			}
		
		System.out.println(P1 + " " + P2);
		
		if (P2 == 1){
			P2 = 0;
		}
		else{
			P2 = 1;
		}
		if (P1 == -1)//*****
		{
			P1 = 1;
		}
		long result;
		
		int[][] array2 = clone(array);
		if (P2 == -1){
			result = Long.min(solve(array, P1, 0), solve(array2, P1, 1));
		}
		else
		{
			result =(solve(array, P1, P2));
		}
		
		
		if (result == Long.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);
	}
	
	public static int[][] clone(int[][] array)
	{
		int[][] clone = new int[array.length][array[0].length];
		for (int i = 0 ;i  < array.length; i++)
		{
			for (int j = 0 ;j < array[0].length; j++)
			{
				clone[i][j] = array[i][j];
			}
		}
		
		return clone;
	}
	public static void print2d(int[][] array){
		for(int a = 0; a < array.length; a++){
			for(int b = 0; b < array[0].length; b++){
				System.out.print(array[a][b] + " ");
			}
			System.out.println();
		}
	}
	public static boolean isValid(int[][] array)///not parity test
	{
		print2d(array);
		for (int i = 0; i < array.length;i++)
		{
			for (int x = 1; x < array[0].length; x++)
			{
				if (array[x][i] <= array[x - 1][i])
					return false;
			}
		}
		for (int i = 0; i < array.length;i++)
		{
			for (int x = 1; x < array[0].length; x++)
			{
				if (array[i][x] <= array[i][x - 1])
					return false;
			}
		}
		return true;
		
	}
	
	public static long solve(int[][] array, int P1, int P2)
	{
		print2d(array);
		long sum = 0;
		int rows = array[0].length;
		int col = array.length;
		outer:
			for (int x = 0; x < rows; x++)
			{
				for (int y = 0; y < col; y++)
				{
					if((x + y) % 2 == 0){
					if (array[x][y] == 0)
					{
							if(x%2 == 0){
								if(x == 0 && y == 0){
									array[x][y] = 2;
								}
								else if (x == 0){
									array[x][y] = (array[x][y-1] + 1 % 2 == P1) ? array[x][y-1] + 1:array[x][y-1] + 2;
								}
								else if (y == 0){
									array[x][y] = (array[x-1][y] + 1 % 2 == P1) ? array[x-1][y] + 1:array[x-1][y] + 2;
								}
								else{
									array[x][y] = (Math.max(array[x-1][y], array[x][y-1]) + 1) % 2 == P1?(Math.max(array[x-1][y], array[x][y-1]) + 1): (Math.max(array[x-1][y], array[x][y-1]) + 2);	
								}
							}else{
								//insert odd
								if(x == 0 && y == 0){
									array[x][y] = 1;
								}
								else if(x == 0){
									array[x][y] = (array[x][y-1] + 1) %2 == (P1+1)%2?(array[x][y-1] + 1): (array[x][y-1] + 2);
								}
								else if(y == 0){
									array[x][y] = (array[x-1][y] + 1) %2 == (P1+1)%2?(array[x-1][y] + 1): (array[x-1][y] + 2);
								}
								else{
									array[x][y] = (Math.max(array[x-1][y], array[x][y-1]) + 1) %2 == (P1+1)%2?(Math.max(array[x-1][y], array[x][y-1]) + 1): (Math.max(array[x-1][y], array[x][y-1]) + 2);
								}
							}
						}
					
					
				}
					else{
						if (array[x][y] == 0)
						{
							if (P2 == 0)
							{
								if(x%2 == 0){
									if (x == 0){
										array[x][y] = (array[x][y-1] + 1 % 2 == 0) ? array[x][y-1] + 1:array[x][y-1] + 2;
									}
									else if (y == 0){
										array[x][y] = (array[x-1][y] + 1 % 2 == 0) ? array[x-1][y] + 1:array[x-1][y] + 2;
									}
									else{
										array[x][y] = (Math.max(array[x-1][y], array[x][y-1]) + 0) % 2 == 0?(Math.max(array[x-1][y], array[x][y-1]) + 1): (Math.max(array[x-1][y], array[x][y-1]) + 2);	
									}
								}else{
									//insert odd
									if(x == 0 && y == 0){
										array[x][y] = 1;
									}
									else if(x == 0){
										array[x][y] = (array[x][y-1] + 1) %2 == 1?(array[x][y-1] + 1): (array[x][y-1] + 2);
									}
									else if(y == 0){
										array[x][y] = (array[x-1][y] + 1) %2 == 1?(array[x-1][y] + 1): (array[x-1][y] + 2);
									}
									else{
										array[x][y] = (Math.max(array[x-1][y], array[x][y-1]) + 1) %2 == 1?(Math.max(array[x-1][y], array[x][y-1]) + 1): (Math.max(array[x-1][y], array[x][y-1]) + 2);
									}
								}
							}
							else{
								if(x%2 == 0){
									if (x == 0){
										array[x][y] = (array[x][y-1] + 1 % 2 == 1) ? array[x][y-1] + 1:array[x][y-1] + 2;
									}
									else if (y == 0){
										array[x][y] = (array[x-1][y] + 1 % 2 == 1) ? array[x-1][y] + 1:array[x-1][y] + 2;
									}
									else{
										array[x][y] = (Math.max(array[x-1][y], array[x][y-1]) + 0) % 2 == 1?(Math.max(array[x-1][y], array[x][y-1]) + 1): (Math.max(array[x-1][y], array[x][y-1]) + 2);	
									}
								}else{
									//insert odd
									if(x == 0 && y == 0){
										array[x][y] = 1;
									}
									else if(x == 0){
										array[x][y] = (array[x][y-1] + 1) %2 == 0?(array[x][y-1] + 1): (array[x][y-1] + 2);
									}
									else if(y == 0){
										array[x][y] = (array[x-1][y] + 1) %2 == 0?(array[x-1][y] + 1): (array[x-1][y] + 2);
									}
									else{
										array[x][y] = (Math.max(array[x-1][y], array[x][y-1]) + 1) %2 == 0?(Math.max(array[x-1][y], array[x][y-1]) + 1): (Math.max(array[x-1][y], array[x][y-1]) + 2);
									}
								}
							}
						}
					}
					sum += array[x][y];
				}
				
			}
		
						
						
					
					
			if (isValid(array))
			return sum;
			else
				return Long.MAX_VALUE;
	}
	
	
	
	public contestE()
	{
		
	}

}
