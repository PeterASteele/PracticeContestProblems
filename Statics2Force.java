
public class Statics2Force {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double error = 0;
		double errorRec = 9999;
		double x = 0;
		double y = 0;
		double recordx = 0;
		double recordy = 0;
		for(double m1 = 4; m1 < 5; m1 += .0001){
			for(double m2 = -2; m2 < 0; m2 += .0001){
				y = m1 * Math.sin(Math.toRadians(33.37159)) + m2 * Math.sin(Math.toRadians(80.731644));
				x = m1 * Math.cos(Math.toRadians(33.37159)) + m2 * Math.cos(Math.toRadians(80.731644));
				error = (.630724 - y) * (.630724 - y) + (3.538645 - x)*(3.538645 - x);
				if(error < errorRec){
					recordx = m1;
					recordy = m2;
					errorRec = error;
				}
			}	
		}
		System.out.println("BC is " + recordx  + " and BE is " + recordy + " error is " + errorRec );

	}

}
