class KohonenCluster {
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
	//generates random number
	public static double randomNumberGenerator(double min, double max)
	{
		double range = (max - min);     
		return (Math.random() * range) + min;
	}
	
	public static void main(String[] args) {
		
		int closestCenter;
		double closestCenterValue;
		double [][] centers = new double[4][2];
		centers[0][0]=randomNumberGenerator(-100,100); centers[0][1]=randomNumberGenerator(-100,100);
		centers[1][0]=randomNumberGenerator(-100,100); centers[1][1]=randomNumberGenerator(-100,100);
		centers[2][0]=randomNumberGenerator(-100,100); centers[2][1]=randomNumberGenerator(-100,100);
		centers[3][0]=randomNumberGenerator(-100,100); centers[3][1]=randomNumberGenerator(-100,100);

		double [][] clusterPoints = new double[1000][2];
		
		for(int x=0;x<1000;x++){
			clusterPoints[x][0]=randomNumberGenerator(-100,100);//x
			clusterPoints[x][1]=randomNumberGenerator(-100,100);//y
		}
		
		do{
			
		for(int cp = 0; cp < clusterPoints.length; cp++){//cp=clusterPoints
			closestCenter=0;
			closestCenterValue=distance(centers[0][0], centers[0][1], clusterPoints[cp][0], clusterPoints[cp][1]);
			for(int centerP=1;centerP<centers.length;centerP++){
				//which midpoint is it closer to			
				if(closestCenterValue > distance(centers[centerP][0], centers[centerP][1], clusterPoints[cp][0], clusterPoints[cp][1])){
					closestCenterValue = distance(centers[centerP][0], centers[centerP][1], clusterPoints[cp][0], clusterPoints[cp][1]);
					closestCenter = centerP;
					
				}
			}
			centers[closestCenter][0]+= modifyCoordinate(clusterPoints[cp][0],centers[closestCenter][0]); //increments midpoint
			centers[closestCenter][1]+= modifyCoordinate(clusterPoints[cp][1],centers[closestCenter][1]); //increments midpoint

		}
		//prints the midpoints
		for(int mp = 0; mp < centers.length; mp++){
			System.out.printf("Center %d: %1.2f,%1.2f \n",mp+1, centers[mp][0], centers[mp][1]);

		}
		System.out.println();
		iterations--;
		n-=0.00001;
		}
		while(n>0);//how mant times we cluster
		
	}

}