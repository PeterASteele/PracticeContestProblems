import java.util.ArrayList;
import java.util.Scanner;

public class IntegerLists {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfCases = input.nextInt();
		input.nextLine();
		String[] instructions = new String[numberOfCases];
		int[] lengthOfList = new int[numberOfCases];
		String[] lists = new String[numberOfCases];
		for (int a = 0; a < numberOfCases; a++) {
			instructions[a] = input.nextLine();
			lengthOfList[a] = input.nextInt();
			input.nextLine();
			lists[a] = input.nextLine();
		}
		for (int a = 0; a < numberOfCases; a++) {
			if (lengthOfList[a] == 0) {
				if(instructions[a].indexOf("D") != -1){
					System.out.println("error");
				}
				else{
					System.out.println("[]");
				}
			} 
			else {
				ArrayList<Integer> currentList = new ArrayList<Integer>();
				String[] temp = lists[a].substring(1, lists[a].length() - 1)
						.split(",");
				for (int b = 0; b < temp.length; b++) {
					currentList.add(Integer.parseInt(temp[b]));
				}
				
				ArrayList<Integer> list2 = executeInstructions(instructions[a], 0,
						currentList, 0, currentList.size() - 1, false);
				
				System.out.println(print(list2));
				
			}
		} // list now in earliest form
	}
	public static String print(ArrayList<Integer> list){
		if (list == null){
			return "error";
		}
		StringBuilder build2 = new StringBuilder();
				
		build2.append("[");
		if(list.get(list.size()-1) == -2){
			
			for(int b = list.size()-1; b >= 0; b--){
				if(list.get(b) > -1){
					
					build2.append("" + list.get(b));
					build2.append(",");
				}
				
			}
		}
		else{
			for(int a : list){
				if(a != -1){
					build2.append("" + a);
					build2.append(",");
				}
			}
		}	
		if(build2.length() != 1){
			build2.setCharAt(build2.length()-1, ']');
		}
		else{
			build2.append(']');
		}
		
		return build2.toString();
	}
	public static ArrayList<Integer> executeInstructions(String instructions,
			int instructionIndex, ArrayList<Integer> list, int left, int right,
			boolean reversed) {
		if (instructionIndex >= instructions.length()) {
			if (reversed) {
				list.add(-2);
			}
			return list;
		}
		if (left > right) {
			return null;
		}
		boolean rev = reversed;
		if (instructions.charAt(instructionIndex) == 'R') {
			rev = !rev;
		}
		if (instructions.charAt(instructionIndex) == 'D') {
			if (!rev) {
				list.set(left, -1);
				left++;
			} else {
				list.set(right, -1);
				right--;
			}
		}
		instructionIndex++;
		return executeInstructions(instructions, instructionIndex, list, left,
				right, rev);
	}
}
