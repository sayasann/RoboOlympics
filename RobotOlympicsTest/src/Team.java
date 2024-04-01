
public class Team {
	Robot[]humanRobots;
	Robot[]computerRobots;
	int c=1;
	int h=1;
	
	public Team(boolean isComputer,int number)
	{  if(isComputer) {
			computerRobots=new Robot[number];
			for (int i = 0; i < computerRobots.length; i++) {
				computerRobots[i]=new Robot(true,c);
				c++;
			}
			
		}
		else {
			humanRobots=new Robot[number];
			for (int i = 0; i < humanRobots.length; i++) {
				humanRobots[i]=new Robot(false,h);
				h++;
			}
			
		}
	}
	
	
	

}
