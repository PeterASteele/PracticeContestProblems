public class StaticsWrench {
/*
Program to solve a statics problem (solving for the magnitude and location for a wrench). 
*/
	public static void main(String[] args) {
		double[] forces = new double[3];
		forces[0] = -18;
		forces[1] = -45 * 4.3 / hypot(1.7, 4.3);
		forces[2] = 45 * 1.7 / hypot(1.7, 4.3);
		double[] targetMoment = new double[3];
		targetMoment[0] = 0;
		targetMoment[1] = 18 * 1.7;
		targetMoment[2] = -37;
		double error = 9999;
		double minX = 0;
		double minY = 0;
		double minM = 0;
		double tempMomentX = 0;
		double tempMomentY = 0;
		double tempMomentZ = 0;
		double tempError = 0;
		for (double x = 0.188; x < 0.189; x = x + .00001) {
			for (double y = -.88; y < -.87; y = y + .00001) {
				for (double m = -39.1; m < -39; m = m + .00001) {
					tempMomentX = forces[2] * y + m * forces[0]
							/ hypot(forces[0], forces[1], forces[2]);
					tempMomentY = -1 * forces[2] * x + m * forces[1]
							/ hypot(forces[0], forces[1], forces[2]);
					tempMomentZ = -1 * forces[0] * y + forces[1] * x + m
							* forces[2]
							/ hypot(forces[0], forces[1], forces[2]);
					tempError = Math.abs(tempMomentX - targetMoment[0])
							+ Math.abs(tempMomentY - targetMoment[1])
							+ Math.abs(tempMomentZ - targetMoment[2]);
					if (tempError < error) {
						error = tempError;
						minX = x;
						minY = y;
						minM = m;
						 //System.out.println("Current record is off by " +
						 //error + " at (" + x + ", " + y + ") with moment " +
						//m);
					}
				}
			}
		}
		System.out.println("Current record is off by " + error + " at (" + minX
				+ ", " + minY + ") with moment " + minM);

	}

	public static double hypot(double x, double y, double z) {
		return Math.sqrt(x * x + y * y + z * z);
	}
	public static double hypot(double x, double y) {
		double z = 0;
		return Math.sqrt(x * x + y * y + z * z);
	}
}