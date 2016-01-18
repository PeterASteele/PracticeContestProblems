import java.util.Scanner;
//https://www.facebook.com/hackercup/problem/910374079035613/
public class FacebookQualifierBoomerangConstellations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int cases = input.nextInt();
		for (int a = 0; a < cases; a++) {
			int numStarts = input.nextInt();
			Point[] stars = new Point[numStarts];
			for (int b = 0; b < numStarts; b++) {
				stars[b] = new Point(input.nextInt(), input.nextInt());
			}
			long count = 0;
			for (int b = 0; b < numStarts; b++) {
				for (int c = 0; c < numStarts; c++) {
					for (int d = c + 1; d < numStarts; d++) {
						if (b != c && b != d && c != d) {
							if (distanceSquared(stars[b], stars[c]) == distanceSquared(
									stars[b], stars[d])) {
								count++;
							}
						}
					}
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
