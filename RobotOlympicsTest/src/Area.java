
public class Area {
	//başlangıç ve bitiş koordinatları
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	boolean flag;
	int px;
	int py;
	int pieceNumber;
	
	//başlangıç koordinatları x ve y, cube array atıyoruz ki alanı düzgün hesaplayalım
	public Area(int x , int y, Cube[][]array){
		startX=x;
		startY=y;
		endX=startX+array.length*4;
		endY=startY+array.length*4;
		
	}
	//px ve py piece alanın içinde mi değil mi kontrol ediyoruz,
	//piece'nin kendine ait AREA'sını PieceWriter içinde alıyoruz
	public boolean isInArea(int px, int py) {
		if(startX<=px && px<=endX && startY<=py && py<=endY) {
			return true;
		}
		else
			return false;
	}
	
}
