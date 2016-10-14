/*
1000points rand(-100,100) 
center points at random too.
*/
class KMeansCluster {
	static double n=0.5; //multiplier// learning rate
	static int iterations = 10;//how mant times we cluster
	
	//distance
	public static double distance(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2), 2));
	}
	//midpoint
	public static double modifyCoordinate(double x1, double y1){ //y1 isnt alwayes more than x1
		return (x1-y1)*n;
	}
	
	public static void main(String[] args) {
		double [][] centers = new double[2][2];
		centers[0][0]=4; centers[0][1]=5;
		centers[1][0]=9; centers[1][1]=3;

		double [][] clusterPoints = new double[4][4];
		clusterPoints[0][0]=2; clusterPoints[0][1]=4;
		clusterPoints[1][0]=6; clusterPoints[1][1]=5;
		clusterPoints[2][0]=8; clusterPoints[2][1]=2;
		clusterPoints[3][0]=10; clusterPoints[3][1]=1;
		
		do{
		for(int cp = 0; cp < clusterPoints.length; cp++){//cp=clusterPoints
			if(distance(centers[0][0], centers[0][1], clusterPoints[cp][0], clusterPoints[cp][1]) <
				distance(centers[1][0], centers[1][1], clusterPoints[cp][0], clusterPoints[cp][1])){//which midpoint is it closer to
					centers[0][0]+= modifyCoordinate(clusterPoints[cp][0],centers[0][0]); //increments midpoint
					centers[0][1]+= modifyCoordinate(clusterPoints[cp][1],centers[0][1]); //increments midpoint
					//System.out.println(midpoints[0][0]+","+midpoints[0][1] +"--"+cp);

				}
				else {
					//System.out.println("---");
					centers[1][0]+= modifyCoordinate(clusterPoints[cp][0],centers[1][0]); //increments midpoint
					centers[1][1]+= modifyCoordinate(clusterPoints[cp][1],centers[1][1]); //increments midpoint
				}

		}
		//prints the midpoints
		for(int mp = 0; mp < centers.length; mp++){
			System.out.println(centers[mp][0]+","+centers[mp][1]);

		}
		System.out.println();
		iterations--;
		n-=0.00001;
		}
		while(n>0);//how mant times we cluster
		
	}
}