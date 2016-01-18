import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
//Faster implementation of boomerang constelations.
//https://www.facebook.com/hackercup/problem/910374079035613/
public class FacebookQualifierBoomerangConstellationsFast.java {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int cases = input.nextInt();
		if(cases > 75){
			System.exit(0);
		}
		for (int a = 0; a < cases; a++) {
			int numStarts = input.nextInt();
			if(numStarts > 2000){
			//	System.exit(0);
			}
			Point[] stars = new Point[numStarts];
			for (int b = 0; b < numStarts; b++) {
				stars[b] = new Point(input.nextInt(), input.nextInt());
			}
			long count = 0;
			for (int b = 0; b < numStarts; b++) {
				HashMap<Long, Integer> tempMap = new HashMap<Long, Integer>();
				for (int c = 0; c < numStarts; c++) {
					if(b != c){
						long distance = distanceSquared(stars[b], stars[c]);
						if(tempMap.containsKey(distance)){
							int tempValue = tempMap.get(distance);
							tempMap.remove(distance);
							tempMap.put(distance, tempValue+1);
						}
						else{
							tempMap.put(distance, 1);
						}
					}
				}
				for(Entry<Long, Integer> temp: tempMap.entrySet()){
					count = count +  (temp.getValue() * (temp.getValue()-1) / 2);
				}
			}
			System.out.println("Case #" + (a+1) + ": " + count);
		}
	}

	public static long distanceSquared(Point a, Point b) {
		long xDiff = (b.x - a.x);
		long yDiff = (b.y - a.y);
		return xDiff * xDiff + yDiff * yDiff;
	}

	public static class Point {
		int x;
		int y;

		public Point(int x2, int y2) {
			x = x2;
			y = y2;
		}
	}

}
