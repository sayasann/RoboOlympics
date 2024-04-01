import java.util.concurrent.ThreadLocalRandom;

public class Piece {
	private static int prevId=0;
	public int id=++prevId;
	
	
	public Cube[][] fourPiece;
	public Cube[][] threePiece;
	public Cube[][] twoPiece;
	public Cube[][] onePiece;
	boolean flag=false;
	public int isUsed=0;
	public int isUsedNew=0;
	public int startX;
	public int startY;
	
	
	
	
	//her Piece'nin alanını tutan bir nesnesi var
	public Area area;
	
	
	
	
	
	//bana null olmayanı yani içi dolu olanı döndür
	public Cube[][] getPiece() {
        if (fourPiece != null) return fourPiece;
        else if (threePiece != null) return threePiece;
        else if (twoPiece != null) return twoPiece;
        else if (onePiece != null) return onePiece;
        else return null; // Hiçbir şey bulunamazsa null döndürün
    }
	public String getPieceName() {
		 if (fourPiece != null) return "four";
	        else if (threePiece != null) return "three";
	        else if (twoPiece != null) return "two";
	        else if (onePiece != null) return "one";
	        else return null; // Hiçbir şey bulunamazsa null
	}

	
	
	
	public void generateFour() {
		
		int randomNumber=ThreadLocalRandom.current().nextInt(1,5);
		
		if(randomNumber==1) {
			fourPiece= new Cube[3][3];
			fourPiece[0][0]=new Cube(24,99,id);
			fourPiece[1][0]=new Cube(24,99,id);
			fourPiece[1][1]=new Cube(24,99,id);
			fourPiece[1][2]=new Cube(24,99,id);
			for (int i = 0; i < fourPiece.length; i++) {
				for (int j = 0; j < fourPiece[i].length; j++) {
					if(fourPiece[i][j]!=null) {
						fourPiece[i][j].arrayArranger();
						
						
				
						
					}
				}
			}
			
		}
		
		
		
		else if (randomNumber==2) {
			fourPiece = new Cube[3][3];
			fourPiece[0][1]= new Cube(24,99,id);
			fourPiece[1][0]= new Cube(24,99,id);
			fourPiece[1][1]= new Cube(24,99,id);
			fourPiece[1][2]= new Cube(24,99,id);
			for (int i = 0; i < fourPiece.length; i++) {
				for (int j = 0; j < fourPiece[i].length; j++) {
					if(fourPiece[i][j]!=null) {
						fourPiece[i][j].arrayArranger();
					}
				}
			}
			
		}
		else if (randomNumber==3) {
			fourPiece = new Cube[3][3];
			fourPiece[1][0]= new Cube(24,99,id);
			fourPiece[1][1]= new Cube(24,99,id);
			fourPiece[0][1]= new Cube(24,99,id);
			fourPiece[0][2]= new Cube(24,99,id);
			for (int i = 0; i < fourPiece.length; i++) {
				for (int j = 0; j < fourPiece[i].length; j++) {
					if(fourPiece[i][j]!=null) {
						fourPiece[i][j].arrayArranger();
					}
				}
			}
		}
		else if(randomNumber==4) {
			fourPiece = new Cube[3][3];
			fourPiece[0][0]= new Cube(24,99,id);
			fourPiece[0][1]= new Cube(24,99,id);
			fourPiece[1][0]= new Cube(24,99,id);
			fourPiece[1][1]= new Cube(24,99,id);
			for (int i = 0; i < fourPiece.length; i++) {
				for (int j = 0; j < fourPiece[i].length; j++) {
					if(fourPiece[i][j]!=null) {
						fourPiece[i][j].arrayArranger();
					}
				}
			}
		}
		
		
		
	}
	public void generateThree() {
		int randomNumber=ThreadLocalRandom.current().nextInt(1,3);
		if(randomNumber==1) {
			threePiece = new Cube[3][3];
			threePiece[0][0]= new Cube(12,87,id);
			threePiece[1][0]= new Cube(12,87,id);
			threePiece[1][1]= new Cube(12,87,id);
			for (int i = 0; i < threePiece.length; i++) {
				for (int j = 0; j < threePiece[i].length; j++) {
					if(threePiece[i][j]!=null) {
						threePiece[i][j].arrayArranger();
					}
				}
			}
		}
		else if (randomNumber==2) {
			threePiece = new Cube[3][3];
			threePiece[0][0]= new Cube(12,87,id);
			threePiece[0][1]= new Cube(12,87,id);
			threePiece[0][2]= new Cube(12,87,id);
			for (int i = 0; i < threePiece.length; i++) {
				for (int j = 0; j < threePiece[i].length; j++) {
					if(threePiece[i][j]!=null) {
						threePiece[i][j].arrayArranger();
					}
				}
			}
		}
		
	}
	public void generateTwo() {
		twoPiece = new Cube[2][2];
		twoPiece[0][0]= new Cube(6,81,id);
		twoPiece[0][1]=new Cube(6,81,id);
		for (int i = 0; i < twoPiece.length; i++) {
			for (int j = 0; j < twoPiece[i].length; j++) {
				if(twoPiece[i][j]!=null) {
					twoPiece[i][j].arrayArranger();
				}
			}
		}
		
	}
	public void generateOne() {
		onePiece = new Cube[1][1];
		onePiece[0][0]= new Cube(0,75,id);
		onePiece[0][0].arrayArranger();
		
	}
	
	public void pieceWriter(enigma.console.Console textWindow,Cube[][]array,char[][]emptyCube,int xF, int yF) {
		//yeni y'yi her cube yazdırmasından sonra return etmesi lazım ki burda da o yeni y koordinatı gözüksün, y-5 çünkü en sonda gereksiz bir kere
		//daha satır atlıyor
		int counter=0;
		//burda başlangıç noktalarını tutuyorum ortlamalar için, hem de başa dönmek için
		int xTest=xF;
		int yTest=yF;
		int xTest1=xF;
		int yTest1=yF;
		//Piecenin areasını yani başlangıç koordinatlarını kaydediyoruz
		area=new Area(xF,yF,array);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				
				int yNew=oneSkeletoneGenerate(textWindow,xTest,yTest,emptyCube);
				xTest+=4;
				yTest=yNew;
				yTest-=5;
				counter++;
				if(counter>=array[i].length) {
					xTest=xTest-4*array[i].length;
					yTest+=4;
					textWindow.getTextWindow().setCursorPosition(xTest,yTest);
					counter=0;
				}
			
			}
		}
		counter=0;
		textWindow.getTextWindow().setCursorPosition(xF,yF);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(array[i][j]==null) {
					xF+=4;
					counter++;
				}
				else if(array[i][j]!=null) {
					int yNew=array[i][j].cubeWriter(textWindow,xF,yF);
					xF+=4;
					yF=yNew;
					yF-=5;
					counter++;
				}
				if(counter>=array[i].length) {
					xF=xF-4*array[i].length;
					yF+=4;
					textWindow.getTextWindow().setCursorPosition(xF,yF);
					counter=0;
				}
				
			
			}
		}
		if(array.length!=1)//birlik array için ortalamalara gerek yok
			meanOfForces(textWindow,array, xTest1, yTest1);
		
		
	}
	//null gördüğünde bu iskelet oluşuyor
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
	//90 derece sağ döndürme
	public void shiftToRight(Cube[][]array,String name) {
		
		Cube[][] testPiece=new Cube[array.length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				//formül 90 derece sağa döndürme için
				if(array[i][j]!=null) {
					array[i][j].forceAfterRotate();
				}
				testPiece[j][array.length-1-i]=array[i][j];
			}
		}
		//piece'miz kaçlık ise ona atamamız gerekiyor
		if(name=="four") {
			fourPiece=testPiece;
			moveFormToTopLeft(fourPiece);
		}
		else if (name=="three") {
			threePiece=testPiece;
			moveFormToTopLeft(threePiece);
		}
		else if (name=="two") {
			twoPiece=testPiece;
			moveFormToTopLeft(twoPiece);
		}
		
		
		
		
	
	}
	//90 derece sola döndürme
	public void shiftToLeft(Cube[][]array,String name) {
		
		Cube[][] testPiece=new Cube[array.length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				//formül 90 derece sola döndürme için
				if(array[i][j]!=null) {
					array[i][j].forceAfterRotate();
				}
				
				testPiece[array.length-1-i][j]=array[j][i];
			}
		}
		
		if(name=="four") {
			fourPiece=testPiece;
			moveFormToTopLeft(fourPiece);
		}
		else if (name=="three") {
			threePiece=testPiece;
			moveFormToTopLeft(threePiece);
		}
		else if (name=="two") {
			twoPiece=testPiece;
			moveFormToTopLeft(twoPiece);
		}
		
		
		
		
	
	}
	//reverse
	public void reversePiece(Cube[][]array,String name) {
		Cube[][] testPiece=new Cube[array.length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				
				testPiece[i][array[i].length-1-j]=array[i][j];
			}
		}
		
		if(name=="four") {
			fourPiece=testPiece;
			moveFormToTopLeft(fourPiece);
		}
		else if (name=="three") {
			threePiece=testPiece;
			moveFormToTopLeft(threePiece);
		}
		else if (name=="two") {
			twoPiece=testPiece;
			moveFormToTopLeft(twoPiece);
		}
		
		
	}
	
	//sol üst tarafa taşıma
	public  void moveFormToTopLeft(Cube[][] array) {
		int minX=9999;
		int minY=9999;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(array[i][j]!=null) {
					if(i<minX)
						minX=i;
					if(j<minY)
						minY=j;
				}
			}
		}
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(array[i][j]!=null) {
					array[i-minX][j-minY]=array[i][j];
					if(minX!=0 || minY!=0)
						array[i][j]=null;
				}
			}
		}
		
        
    }
	//ortalama hesaplanması satır sütün piece içinde
	public void meanOfForces(enigma.console.Console textWindow,Cube[][] array,int x,int y) {
		int sumX=0;
		int sumY=0;
		int count=0;
		x+=4*array.length+1;
		y+=2;
		//en sağdaki ortalamaları yazdırmak için
		for (int i = 0; i < array.length; i++) {
			sumX=0;
			count=0;
			for (int j = 0; j < array[i].length; j++) {
				if(array[i][j]!=null) {
					sumX+=(array[i][j].horizontal);
					count++;
				
				}
				
			}
			//o satırda küp var mı yok mu onu kontrol ediyoruz ki sayı mı 00 mı yazalım
			if(count!=0) {
				int mean= sumX/count;
				textWindow.getTextWindow().setCursorPosition(x, y);
				//sayı tek basamaklı ise sadece örneğin 6 değil de 06 yazsın.
				if(Integer.toString(mean).length()==1) {
					textWindow.getTextWindow().output("0"+mean);
				}
				else
					textWindow.getTextWindow().output(Integer.toString(mean));
					
				y+=4;
			}
			else {
				textWindow.getTextWindow().setCursorPosition(x, y);
				textWindow.getTextWindow().output("00");
				y+=4;
			}
			
		}
		//alttaki ortalamaları yazdırmak için
		x-=4*array.length-1;
		y-=1;
		for (int i = 0; i < array.length; i++) {
			sumY=0;
			count=0;
			for (int j = 0; j < array[i].length; j++) {
				if(array[j][i]!=null) {
					sumY+=(array[j][i].vertical);
					count++;
				}
			}
			//o satırda küp var mı yok mu onu kontrol ediyoruz ki sayı mı 00 mı yazalım
			if(count!=0) {
				int mean = sumY/count;
				textWindow.getTextWindow().setCursorPosition(x, y);
				////sayı tek basamaklı ise sadece örneğin 6 değil de 06 yazsın.
				if(Integer.toString(mean).length()==1) {
					textWindow.getTextWindow().output("0"+mean);
				}
				else
					textWindow.getTextWindow().output(Integer.toString(mean));
				x+=4;
			}
			else {
				textWindow.getTextWindow().setCursorPosition(x, y);
				textWindow.getTextWindow().output("00");
				x+=4;
			}
			
			
		}
		
		
	}
}
	
	
	
	
	
	


