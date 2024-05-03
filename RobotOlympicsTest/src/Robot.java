import java.util.Arrays;

public class Robot {
	Cube[][]robot=new Cube[5][5];
	public Piece[][] usedPieces = new Piece[4][7];
	public int num;
	boolean isComputer;
	int In;
	int LA;
	int RA;
	int Sk;
	int LL;
	int RL;
	int Sp;
	int armBalance;
	int legBalance;
	
	
	//computer robotun oluşması
	public Robot(boolean isComputer, int num) {
		if(isComputer) {
			robot[0][2]=new Cube(30,99);
			robot[1][1]=new Cube(30,99);
			robot[1][0]=new Cube(30,99);
			robot[1][1]=new Cube(30,99);
			robot[1][2]=new Cube(30,99);
			robot[1][3]=new Cube(30,99);
			robot[1][4]=new Cube(30,99);
			robot[2][1]=new Cube(30,99);
			robot[2][2]=new Cube(30,99);
			robot[2][3]=new Cube(30,99);
			robot[3][1]=new Cube(30,99);
			robot[3][2]=new Cube(30,99);
			robot[3][3]=new Cube(30,99);
			robot[2][2]=new Cube(30,99);
			robot[1][3]=new Cube(30,99);
			robot[4][1]=new Cube(30,99);
			robot[4][3]=new Cube(30,99);
			for (int i = 0; i < robot.length; i++) {
				for (int j = 0; j < robot[i].length; j++) {
					if(robot[i][j]!=null)
						robot[i][j].arrayArranger();
				}	
			}
			calculateForceOfSp();
			calculateForceOfSk();
			calculateForceOfIn();
			
			this.num=num;
			this.isComputer=isComputer;
			
			
			
		}
		else {
			In=0;
			LA=0;
			RA=0;
			Sk=0;
			LL=0;
			RL=0;
			Sp=0;
			armBalance=0;
			legBalance=0;
			this.num=num;
			this.isComputer=isComputer;
			
		}
		
		
	}
	//robot'un ekrana yazdırılması
	public void robotWriter(enigma.console.Console textWindow,int x, int y,char[][]emptyCube) {
		int counter=0;
		humanRobotSkeletWriter(textWindow, x,  y,emptyCube);
		for (int i = 0; i < robot.length; i++) {
			for (int j = 0; j < robot[i].length; j++) {
				//null ise cursoru kaydırsın 
				if(robot[i][j]==null) {
					x+=4;
					counter++;
				}
				else if(robot[i][j]!=null) {
					int yNew=robot[i][j].cubeWriter(textWindow,x,y);
					x+=4;
					y=yNew;
					y-=5;
					counter++;
				}
				//kübü yazarken yeni satıra geçmesi için
				if(counter>=robot[i].length) {
					x=x-4*robot[i].length;
					y+=4;
					textWindow.getTextWindow().setCursorPosition(x,y);
					counter=0;
				}
			}
		}
		//robot yazdırıldıktan sonra direkt güçleri güncellensin
		forcesWriter(textWindow);
		
	}
	
	public void calculateForceOfIn() {
		In=0;
		for (int i = 0; i <= 3; i++) {
		    if (robot[i][2] != null) {
		        In += robot[i][2].vertical;
		    }
		}
		
	}
	public void calculateForceOfSk() {
		LA=0;
		RA=0;
		Sk=0;
		armBalance=0;
		for (int j = 0; j <= 1; j++) {
		    if (robot[1][j] != null) {
		        LA += robot[1][j].horizontal;
		    }
		}

		for (int j = 3; j <= 4; j++) {
		    if (robot[1][j] != null) {
		        RA += robot[1][j].horizontal;
		    }
		}
		if(LA!=0 && RA!=0 ) {
		armBalance=Math.max(LA, RA)/Math.min(LA, RA);
		Sk=(LA+RA)/armBalance;
		
		}
		
	}
	public void calculateForceOfSp() {
		LL=0;
		RL=0;
		legBalance=0;
		Sp=0;
		for (int i = 2; i <= 4; i++) {
		    if (robot[i][1] != null) {
		        LL += robot[i][1].vertical;
		    }
		    if (robot[i][3] != null) {
		        RL += robot[i][3].vertical;
		    }
		}

		if(LL!=0 && RL!=0) {
			legBalance=Math.max(LL,RL)/Math.min(LL,RL);
			Sp=(LL+RL)/legBalance;
		}
		
		
	}
	
	public void forcesWriter(enigma.console.Console textWindow) {
		textWindow.getTextWindow().setCursorPosition(0,33);
		
		if(isComputer) {
			textWindow.getTextWindow().output("                         ");
			textWindow.getTextWindow().setCursorPosition(0,33);
			textWindow.getTextWindow().output(" Computer Robot "+num+"\n");
		}
		else {
			textWindow.getTextWindow().output("                         ");
			textWindow.getTextWindow().setCursorPosition(0,33);
			textWindow.getTextWindow().output(" Human Robot "+num+"\n");
		}
			
		textWindow.getTextWindow().output("   Robot Intelligence: "+In+"\n"+"   Skill: "+Sk+"\n"+"     Left Arm: "+LA+"\n"+"     Right Arm: "+RA+"\n"+"   Speed: "+Sp+"\n"+"     Left leg: "+LL+"\n"+"     Right leg: "+RL+"\n"+"\n");
		
		
		
	}
	public void humanRobotSkeletWriter(enigma.console.Console textWindow,int x, int y, char [][]emptyCube) {
		int xTest=x;
		int yTest=y;
		int counter=0;
		if(robot!=null) {
		for (int i = 0; i < robot.length; i++) {
			for (int j = 0; j < robot[i].length; j++) {
				
				if(!((i==0 && j!=2) || (i!=1 && j==0) || (i!=1 && j==4) || (i==4 && j==2))) {
					int yNew=oneSkeletoneGenerate(textWindow,xTest,yTest,emptyCube);
					xTest+=4;
					yTest=yNew;
					yTest-=5;
					counter++;
				}
				else {
					
						xTest+=4;
						counter++;
					}
				}
				if(counter>=robot[i].length) {
					xTest=xTest-4*robot[i].length;
					yTest+=4;
					textWindow.getTextWindow().setCursorPosition(xTest,yTest);
					counter=0;
				}
			}
		}
		
	}
	
	public int oneSkeletoneGenerate(enigma.console.Console textWindow,int x, int y,char[][]emptyCube) {
		textWindow.getTextWindow().setCursorPosition(x,y);
		for (int i = 0; i < emptyCube.length; i++) {
			for (int j = 0; j < emptyCube[i].length; j++) {
				textWindow.getTextWindow().output(emptyCube[i][j]);
			}
			y++;
			textWindow.getTextWindow().setCursorPosition(x,y);
		}
		return y;
	}
	public void  robotPiecePlacement(int x, int y, Cube[][]cubes,Piece[][] twentyPieces,int testx, int testy,enigma.console.Console cn) {
		
		boolean outOfBorder = false;
		boolean doesOverlap = false;
		boolean outOfMainBorder=false;
		
		
		
		
		
		
		
		
		for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes[i].length; j++) {
                if (cubes[i][j] != null ) {
                	if((i + y >= robot[0].length) || ((j + x >= robot[0].length))){
                    outOfMainBorder=true;
                    twentyPieces[testx][testy].isUsed=0;
                	}
                }
            }
        }
		
		
		for (int i = 0; i < cubes.length; i++) {
			for (int j = 0; j < cubes[i].length; j++) {
				if (cubes[i][j] != null && !outOfMainBorder)
				{
					if ((i + y == 0 && j + x != 2) || (j + x == 0 && i + y != 1) || (j + x == 4 && i + y != 1) || (i + y == 4 && j + x == 2)) {
						outOfBorder = true;
						twentyPieces[testx][testy].isUsed=0;
						
					}
					
					
				}
					
			}		
			
		}
		for (int k = 0; k < cubes.length; k++) {
			for (int j = 0; j < cubes[k].length; j++) {
				if (cubes[k][j] != null && !outOfMainBorder && !outOfBorder)
				{
					if(robot[k+y][j+x]!=null) {
						doesOverlap=true;
						twentyPieces[testx][testy].isUsed=0;
					}
					
					
				}
					
			}		
			
		}
		
		
		if(!outOfBorder && !doesOverlap && !outOfMainBorder) {
			for (int i = 0; i < cubes.length; i++) {
				for (int j = 0; j < cubes.length; j++) {
					if(cubes[i][j]!=null) {
						robot[i + y][j + x] = cubes[i][j];
						
						
					}
				}
			}

			usedPieces[testx][testy] = twentyPieces[testx][testy];
			calculateForceOfSp();
			calculateForceOfSk();
			calculateForceOfIn();
		}
	
		
	
		
		
	}
	public void robotPieceErase(int x,int y, Piece[][]pieces,int testI, int testJ,enigma.console.Console cn){
		
		for (int i = 0; i < robot.length; i++) {
			for (int j = 0; j < robot[i].length; j++) {
				
				if(robot[i][j]!=null && pieces[testI][testJ].id==robot[i][j].pieceId) {
					robot[i][j]=null;
				}
			}
		}

		usedPieces[testI][testJ] = null;
		calculateForceOfSp();
		calculateForceOfSk();
		calculateForceOfIn();
		
		
		
		
	}
	public void forcesWriterReset(enigma.console.Console textWindow) {
		
		textWindow.getTextWindow().setCursorPosition(22,34);
		textWindow.getTextWindow().output("    ");
		textWindow.getTextWindow().setCursorPosition(10,35);
		textWindow.getTextWindow().output("    ");
		textWindow.getTextWindow().setCursorPosition(15,36);
		textWindow.getTextWindow().output("    ");
		textWindow.getTextWindow().setCursorPosition(16,37);
		textWindow.getTextWindow().output("    ");
		textWindow.getTextWindow().setCursorPosition(10,38);
		textWindow.getTextWindow().output("    ");
		textWindow.getTextWindow().setCursorPosition(15,39);
		textWindow.getTextWindow().output("    ");
		textWindow.getTextWindow().setCursorPosition(16,40);
		textWindow.getTextWindow().output("    ");
		
		
		
		
	}
	public boolean robotFull() {
		boolean flag=true;
		for (int i = 0; i < robot.length; i++) {
			for (int j = 0; j < robot.length; j++) {
				if (!((i  == 0 && j != 2) || (j  == 0 && i != 1) || (j  == 4 && i != 1) || (i == 4 && j  == 2))) {
					if(robot[i][j]==null) {
						flag=false;
						break;
					}
					
				}
			}
		}
		return flag;
	}
		
		
	
	
}

	
	
	
	
	
	


