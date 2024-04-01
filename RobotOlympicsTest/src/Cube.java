import java.util.concurrent.ThreadLocalRandom;

import enigma.console.TextWindow;
public class Cube {
	public int horizontal;
	public int vertical;
	public char[][]cubeArray=new char[5][5];
	private int min;
	private int max;
	public boolean flag;
	public int pieceId;
	   
	
	
	//constructorunda cube'nin alacağı aralık
	Cube(int min,int max,int pieceId) {
		this.min = min;
		this.max=max;
		this.pieceId=pieceId;
		
	}
	Cube(int min, int max){
		this.min = min;
		this.max=max;
		pieceId=-1;
	}
	
	
	
	//random sayı
	public void forceGenerator() {
		horizontal=ThreadLocalRandom.current().nextInt(min,max+1);
		vertical=ThreadLocalRandom.current().nextInt(min,max+1);
		 
	
	}
	//küp içi ayarlama
	public void arrayArranger() {
		forceGenerator();
		for(int i =0; i<cubeArray.length;i++) {
			for(int j =0; j<cubeArray[0].length;j++) {
				cubeArray[0][j]='+';
				cubeArray[4][j]='+';
			
				
			}
		}
		cubeArray[1][0]='+';cubeArray[2][0]='+';
		cubeArray[3][0]='+';cubeArray[1][4]='+';
		cubeArray[2][4]='+';cubeArray[3][4]='+';
		cubeArray[1][1]=' ';cubeArray[1][3]=' ';
		cubeArray[3][3]=' ';cubeArray[3][1]=' ';cubeArray[2][2]=' ';
		
		
		//1 uzunluğundaki sayılar için
		if(Integer.toString(horizontal).length()==1) {
			cubeArray[2][1]='0';
			cubeArray[2][3]=(char)(horizontal+'0');
			
		}
		if(Integer.toString(vertical).length()==1) {
			cubeArray[1][2]='0';
			cubeArray[3][2]=(char)(vertical+'0');
			
		}
		//2 uzunluğundaki sayılar için
		if(Integer.toString(horizontal).length()>1) {
			String number = Integer.toString(horizontal);
			char left = number.charAt(0);
			char right = number.charAt(1);
			cubeArray[2][1]=left;
			cubeArray[2][3]=right;
			
		}
		if(Integer.toString(vertical).length()>1) {
			String number = Integer.toString(vertical);
			char left = number.charAt(0);
			char right = number.charAt(1);
			cubeArray[1][2]=left;
			cubeArray[3][2]=right;
			
		}
		
	}
	//kübü yazdırma
	public int cubeWriter(enigma.console.Console textWindow,int x, int y) {
	
		textWindow.getTextWindow().setCursorPosition(x,y);
		for (int i = 0; i < cubeArray.length; i++) {
			for (int j = 0; j < cubeArray[i].length; j++) {
				textWindow.getTextWindow().output(cubeArray[i][j]);
			}
			y++;
			textWindow.getTextWindow().setCursorPosition(x,y);
		}
		return y;
	}
	//döndürdükten sonra sayıların yer değiştirmesi
	public void forceAfterRotate() {
		//horizontal -- vertical çapraz yer değiştiriyor
		//---------------------------------------------
		char test1=cubeArray[1][2];
		cubeArray[1][2]=cubeArray[2][1];
		cubeArray[2][1]=test1;
		
		char test2=cubeArray[3][2];
		cubeArray[3][2]=cubeArray[2][3];
		cubeArray[2][3]=test2;
		
		int test = horizontal;
		horizontal=vertical;
		vertical=test;
		
	
		
	}
	
	
	
	
	

}
