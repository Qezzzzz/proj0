public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public double dx;
	public double dy;
	public double r;
	public double dt;

	public double Force;
	public static double G=6.67*Math.pow(10,-11);

	public double ForceX;
	public double ForceY;

	public Planet[] allPlanets;
	public int size;

	public double netForceX;
	public double netForceY;

	public Planet(double xP,double yP,double xV,double yV,
				  double m,String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}
	public double calcDistance(Planet p){
		dx=p.xxPos-this.xxPos;
		dy=p.yyPos-this.yyPos;
		r=Math.sqrt(dx*dx+dy*dy);
		return r;
	}
	public double calcForceExertedBy(Planet p){
		r=this.calcDistance(p);
		Force=G*this.mass*p.mass/Math.pow(r,2);
		return Force;
	}
	public double calcForceExertedByX(Planet p){
		Force=this.calcForceExertedBy(p);
		dx=p.xxPos-this.xxPos;
		r=this.calcDistance(p);
		ForceX=Force*dx/r;
		return ForceX;

	}
	public double calcForceExertedByY(Planet p){
		Force=this.calcForceExertedBy(p);
		dy=p.yyPos-this.yyPos;
		r=this.calcDistance(p);
		ForceY=Force*dy/r;
		return ForceY;

	}
	public double calcNetForceExertedByX(Planet[] allPlanets){
		size=allPlanets.length;
		netForceX=0;
		for(int i=0;i<size;i++){
			if(this.equals(allPlanets[i])) continue;			
			netForceX=this.calcForceExertedByX(allPlanets[i])+netForceX;
		}
		return netForceX;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		size=allPlanets.length;
		netForceY=0;
		for(int i=0;i<size;i++){
			if(this.equals(allPlanets[i])) continue;			
			netForceY=this.calcForceExertedByY(allPlanets[i])+netForceY;
		}
		return netForceY;
	}
	public void update(double dt,double Fx,double Fy){
		double ax=Fx/this.mass;
		double ay=Fy/this.mass;
		 this.xxVel=this.xxVel+ax*dt;
		 this.yyVel=this.yyVel+ay*dt;
		 this.xxPos=this.xxPos+this.xxVel*dt;
		 this.yyPos=this.yyPos+this.yyVel*dt;


	}
	public  void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/earth.gif");
		//StdDraw.point(this.xxPos,this.yyPos);
	}
}