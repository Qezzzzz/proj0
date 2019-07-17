public class NBody{
	public static double readRadius(String s){
		
		In in=new In(s);
		
			int num=in.readInt();
			double radius=in.readDouble();
			return radius;
		}
	public static Planet[] readPlanets(String s){
		In in=new In(s);
		int num=in.readInt();
		double radius=in.readDouble();
		Planet[] allPlanets=new Planet[num];
		for(int i=0;i<num;i++){
			allPlanets[i]=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),
									 in.readDouble(),in.readDouble(),in.readString());
		}
		return allPlanets;
	}
	public static void main(String[] args) {
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);		
		String filename=args[2];
		double radius=readRadius(filename);
		Planet[] allPlanets=readPlanets(filename);
		/* Drawing Background*/
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(0-radius,radius);
		StdDraw.clear();
		
		In in=new In(filename);
		int num=in.readInt();
		
		for(double time=0;time<T;time=time+dt){
			double[] xForces=new double[num];
			double[] yForces=new double[num];
			for(int i=0;i<num;i++){
				xForces[i]=allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i]=allPlanets[i].calcNetForceExertedByY(allPlanets);
				allPlanets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0,0,"./images/starfield.jpg",2*radius,2*radius);
			for(int j=0;j<num;j++){
				allPlanets[j].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

		}

		StdOut.printf("%d\n", allPlanets.length);
			StdOut.printf("%.2e\n", radius);
			for (int i = 0; i < allPlanets.length; i++) {
   					 StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                 	 allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                 	 allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
}
	}
         
         
        
	}
