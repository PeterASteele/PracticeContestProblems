import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ContestProblem1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		new ContestProblem1();
	}
	String main;
	public ContestProblem1()
	{
		Scanner s = new Scanner(System.in);
		main = s.next();
		
		if (main.length() == 1)
		{
			System.out.println(main);
			System.exit(0);
		}
		
		
		int[] charcounts = new int[26];
		int[] charcounts2 = new int[26];
		
		for (int i = 0; i < main.length(); i++)
		{
			charcounts[main.charAt(i) - 'a']++;
		}
		
		for (int length = 1; length < main.length(); length++)
		{
			ArrayList<String> strings = new ArrayList<String>();
//			if (main.length() % length != 0)
//				continue;
			
		for (int i = 0;i < main.length() - length + 1;i++)
		{

			charcounts2 = new int[26];
				String sub = main.substring(i, length + i);
				
				for (int k = 0;k < sub.length(); k++)
				{
					charcounts2[sub.charAt(k) - 'a']++;
				}
				if (this.evenMultipleOf(charcounts, charcounts2))
				{
					if (this.recurse(sub, main))
					{
						strings.add(sub);
					}
				}
			}
			if (strings.size() > 0)
			{
				Collections.sort(strings);
				System.out.println(strings.get(0));
				System.exit(0);
			}
		}
		System.out.println(main);
	}
	
	public boolean evenMultipleOf(int[] one, int[] two)
	{
		double factor = (double)two[0] / one[0];
		for (int i = 0; i < one.length; i++)
		{
			double fact = (double)two[i] / one[i];
			if (Math.abs(fact - factor) > 1E-6)
				return false;
		}
		return true;
	}
	
	public boolean recurse(String str, String large)
	{
		if (large.equals(""))
			return true;
		String next = large.replace(str, "");
		if (next.equals(large))
		{
			return false;//contains no instances of str
		}
		return recurse(str, next);
	}

}
