import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import enigma.console.TextAttributes;
import java.awt.Color;

public class RobotOlympics {
	public enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard", 150, 55, 12);

	public TextMouseListener tmlis;
	public KeyListener klis;
	// ------ Standard variables for mouse and keyboard ------
	public int mousepr; // mouse pressed?
	public int mousex, mousey; // mouse text coords.
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	// ----------------------------------------------------
	public char[][] emptyCube = new char[5][5];
	public Piece[] fourMainPieces = new Piece[4];
	public Piece[] threeMainPieces = new Piece[4];
	public Piece[] twoMainPieces = new Piece[5];
	public Piece[] oneMainPieces = new Piece[7];
	public boolean flag = true;
	public Piece[][] twentyPieces = new Piece[4][7];
	private int olympicPointH=0;
	private int olympicPointC=0;
	public int gameCountH = -1;
	public int gameCountC = -1;
	Team humanTeam;
	Team computerTeam;
	private boolean flagEndEnter=true;
	private int chessPointH1 = 0;
	private int pingPointH1;
	private int runPointH1;
	private int chessPointH2 = 0;
	private int pingPointH2;
	private int runPointH2;
	private int chessPointR1 = 0;
	private int pingPointR1;
	private int runPointR1;
	private int chessPointR2 = 0;
	private int pingPointR2;
	private int runPointR2;

	// array 4 tane piece nesne--> her biri için generateFour()
	// array 4 tane piece nesne--> her biri için generateThree()
	// array 5 tane piece nesne--> her biri için generateTwo()
	// array 7 tane piece nesne--> her biri için generateOne()

	RobotOlympics() throws Exception { // --- Contructor

		humanTeam = new Team(false, 2);
		computerTeam = new Team(true, 2);
		gameScreen_1();
		mainGeneratorOfTwentyPiecesVisually();
		// humanTeam.humanRobots[0].humanRobotSkeletWriter(cn, 3, 2, emptyCube);

		// computerRobotGenerateVisually(computerTeam);

		// oyun başladığında başlangıç noktalarında bulunsunlar
		currentPiece();
		mouseClick(33, 2);

		// ------ Standard code for mouse and keyboard ------ Do not change
		tmlis = new TextMouseListener() {
			public void mouseClicked(TextMouseEvent arg0) {
			}

			public void mousePressed(TextMouseEvent arg0) {
				if (mousepr == 0) {
					mousepr = 1;
					mousex = arg0.getX();
					mousey = arg0.getY();
				}
			}

			public void mouseReleased(TextMouseEvent arg0) {
			}
		};
		cn.getTextWindow().addTextMouseListener(tmlis);

		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		// ----------------------------------------------------

		int px = 5, py = 5;
		int testI = 0;
		int testJ = 0;

		int cursorX = 3;
		int cursorY = 2;
		int whichCoordinatePieceX = 0;
		int whichCoordinatePieceY = 0;

		cn.getTextWindow().setCursorPosition(cursorX, cursorY);
		cn.getTextWindow().output("#");
		while (true) {
			if(gameCountC < 1) {
				informationUsedPieces();
				cn.getTextWindow().setCursorPosition(19, 25);
				cn.getTextWindow().output("  ");
				cn.getTextWindow().setCursorPosition(19, 25);
				cn.getTextWindow().output(Integer.toString(twentyPieces[testI][testJ].id));
			}
			if (mousepr == 1) { // if mouse button pressed
				// cn.getTextWindow().output(mousex,mousey,'#'); // write a char to x,y position
				// without changing cursor position
				px = mousex;
				py = mousey;

				mousepr = 0; // last action

				if (gameCountH > -1 && humanTeam.humanRobots[gameCountH].usedPieces[testI][testJ] != null) {
					mouseClickUsed(twentyPieces[testI][testJ].area.startX, twentyPieces[testI][testJ].area.startY);
				} else if (gameCountH == 1 && humanTeam.humanRobots[0].usedPieces[testI][testJ] != null) {
					mouseUsedBefore(twentyPieces[testI][testJ].area.startX, twentyPieces[testI][testJ].area.startY);
				} else {
					mouseReset(twentyPieces[testI][testJ].area.startX, twentyPieces[testI][testJ].area.startY);
				}
				
				if(gameCountC < 1) {
				// burda 20 tane piece üstünden geçip hepsinin areasına bakıp true olanı alıp
				// onun seçilmesi gerekiyor
				for (int i = 0; i < twentyPieces.length; i++) {
					for (int j = 0; j < twentyPieces[i].length; j++) {

						if (twentyPieces[i][j] != null) {
							if (twentyPieces[i][j].area.isInArea(px, py)) {

								mouseClick(twentyPieces[i][j].area.startX, twentyPieces[i][j].area.startY);

								testI = i;
								testJ = j;

							}
						}
					}
				}
				}

			}

			// 2 rotate to LEFT
			// 3 rotate to RİGHT
			// 4 REVERSE
			if (keypr == 1) { // if keyboard button pressed
				if (rkey == KeyEvent.VK_LEFT)
					px--;
				if (rkey == KeyEvent.VK_RIGHT)
					px++;
				if (rkey == KeyEvent.VK_UP)
					py--;
				if (rkey == KeyEvent.VK_DOWN)
					py++;
				if (rkey == KeyEvent.VK_3 && twentyPieces[testI][testJ].isUsed == 0 && flag == false
						&& gameCountC <= 1) {
					twentyPieces[testI][testJ].shiftToLeft(twentyPieces[testI][testJ].getPiece(),
							twentyPieces[testI][testJ].getPieceName());
					twentyPieces[testI][testJ].pieceWriter(cn, twentyPieces[testI][testJ].getPiece(), emptyCube,
							twentyPieces[testI][testJ].area.startX, twentyPieces[testI][testJ].area.startY);
				}
				if (rkey == KeyEvent.VK_4 && twentyPieces[testI][testJ].isUsed == 0 && flag == false
						&& gameCountC <= 1) {
					twentyPieces[testI][testJ].shiftToRight(twentyPieces[testI][testJ].getPiece(),
							twentyPieces[testI][testJ].getPieceName());
					twentyPieces[testI][testJ].pieceWriter(cn, twentyPieces[testI][testJ].getPiece(), emptyCube,
							twentyPieces[testI][testJ].area.startX, twentyPieces[testI][testJ].area.startY);
				}
				if (rkey == KeyEvent.VK_5 && twentyPieces[testI][testJ].isUsed == 0 && flag == false
						&& gameCountC <= 1) {
					twentyPieces[testI][testJ].reversePiece(twentyPieces[testI][testJ].getPiece(),
							twentyPieces[testI][testJ].getPieceName());
					twentyPieces[testI][testJ].pieceWriter(cn, twentyPieces[testI][testJ].getPiece(), emptyCube,
							twentyPieces[testI][testJ].area.startX, twentyPieces[testI][testJ].area.startY);
				}
				if (rkey == KeyEvent.VK_1 && twentyPieces[testI][testJ].isUsed != 1 && flag == false
						&& gameCountC <= 1) {
					twentyPieces[testI][testJ].isUsed = 1;
					twentyPieces[testI][testJ].startX = whichCoordinatePieceX;
					twentyPieces[testI][testJ].startY = whichCoordinatePieceY;
					humanTeam.humanRobots[gameCountH].robotPiecePlacement(whichCoordinatePieceX, whichCoordinatePieceY,
							twentyPieces[testI][testJ].getPiece(), twentyPieces, testI, testJ, cn);
					// asda

					humanTeam.humanRobots[gameCountH].robotWriter(cn, 3, 2, emptyCube);
					//mouseClickUsed(twentyPieces[testI][testJ].area.startX, twentyPieces[testI][testJ].area.startY);
					cn.getTextWindow().setCursorPosition(cursorX, cursorY);
					cn.getTextWindow().output("#");

				}
				if (rkey == KeyEvent.VK_2 && flag == false && gameCountC <= 1) {
					twentyPieces[testI][testJ].isUsed = 0;
					humanTeam.humanRobots[gameCountH].robotPieceErase(whichCoordinatePieceX, whichCoordinatePieceY,
							twentyPieces, testI, testJ, cn);

					// asda
					humanTeam.humanRobots[gameCountH].forcesWriterReset(cn);
					humanTeam.humanRobots[gameCountH].robotWriter(cn, 3, 2, emptyCube);
					cn.getTextWindow().setCursorPosition(cursorX, cursorY);
					cn.getTextWindow().output("#");

				}
				if (rkey == KeyEvent.VK_W && flag == false && gameCountC <= 1) {
					int testx = cursorX;
					int testy = cursorY;

					if (cursorY > 2) {
						whichCoordinatePieceY -= 1;
						cn.getTextWindow().setCursorPosition(testx, testy);
						cn.getTextWindow().output(" ");
						humanTeam.humanRobots[gameCountH].robotWriter(cn, 3, 2, emptyCube);
						cursorY -= 4;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						cn.getTextWindow().output("#");
					}

				}
				if (rkey == KeyEvent.VK_A && flag == false && gameCountC <= 1) {

					int testx = cursorX;
					int testy = cursorY;

					if (cursorX > 3) {
						whichCoordinatePieceX -= 1;
						cn.getTextWindow().setCursorPosition(testx, testy);
						cn.getTextWindow().output(" ");
						humanTeam.humanRobots[gameCountH].robotWriter(cn, 3, 2, emptyCube);
						cursorX -= 4;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						cn.getTextWindow().output("#");
					}
				}
				if (rkey == KeyEvent.VK_S && flag == false && gameCountC <= 1) {

					int testx = cursorX;
					int testy = cursorY;
					if (cursorY < 18) {
						whichCoordinatePieceY += 1;
						cn.getTextWindow().setCursorPosition(testx, testy);
						cn.getTextWindow().output(" ");
						humanTeam.humanRobots[gameCountH].robotWriter(cn, 3, 2, emptyCube);
						cursorY += 4;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						cn.getTextWindow().output("#");

					}

				}

				if (rkey == KeyEvent.VK_D && flag == false && gameCountC <= 1) {

					int testx = cursorX;
					int testy = cursorY;
					if (cursorX < 18) {
						whichCoordinatePieceX += 1;
						cn.getTextWindow().setCursorPosition(testx, testy);
						cn.getTextWindow().output(" ");
						humanTeam.humanRobots[gameCountH].robotWriter(cn, 3, 2, emptyCube);
						cursorX += 4;
						cn.getTextWindow().setCursorPosition(cursorX, cursorY);
						cn.getTextWindow().output("#");

					}
				}

				if (rkey == KeyEvent.VK_ENTER) {
					if (gameCountC < 1) {
						if (flag ) {
							gameCountH++;
							humanTeam.humanRobots[gameCountH].humanRobotSkeletWriter(cn, 3, 2, emptyCube);
							flag = false;
							humanTeam.humanRobots[gameCountH].forcesWriterReset(cn);
							humanTeam.humanRobots[gameCountH].forcesWriter(cn);
							for (int a = 0; a <= gameCountH; a++) {
								Piece[][] temPieces = humanTeam.humanRobots[a].usedPieces;
								for (int i = 0; i < temPieces.length; i++) {
									for (int j1 = 0; j1 < temPieces[i].length; j1++) {
										if (temPieces[i][j1] != null) {
											if (gameCountH == a) {
												mouseClickUsed(temPieces[i][j1].area.startX,
														temPieces[i][j1].area.startY);
											} else {
												mouseUsedBefore(temPieces[i][j1].area.startX,
														temPieces[i][j1].area.startY);
											}
										}
									}
								}
							}
							informationScreenHuman();

						} else if (!flag &&  humanTeam.humanRobots[gameCountH].robotFull()){
							gameCountC++;
							
							informationUsedPieces();
							informationScreenComp();
							computerTeam.computerRobots[gameCountC].forcesWriterReset(cn);
							computerTeam.computerRobots[gameCountC].robotWriter(cn, 3, 2, emptyCube);
							flag = true;
						}
					} else {
						
						if(flagEndEnter) {
						cleanScreen();
						robotGamesScreen();
						roboChess();
						roboPingPong();
						roboRun();
						flagEndEnter=false;
						}
						

					}

				}

				if (gameCountH < 1) {
					cn.getTextWindow().setCursorPosition(5, 26);
					cn.getTextWindow().output(Integer.toString(whichCoordinatePieceX));
					cn.getTextWindow().setCursorPosition(12, 26);
					cn.getTextWindow().output(Integer.toString(whichCoordinatePieceY));
					cn.getTextWindow().setCursorPosition(0, 0);
				}

				char rckey = (char) rkey;
				// left right up down
				if (rckey == '%' || rckey == '\'' || rckey == '&' || rckey == '(')
					; // VK kullanmadan test teknigi
				// else cn.getTextWindow().output(rckey);

				if (rkey == KeyEvent.VK_SPACE) {
					String str;
					str = cn.readLine(); // keyboardlistener running and readline input by using enter
					cn.getTextWindow().setCursorPosition(5, 20);
					cn.getTextWindow().output(str);
				}

				keypr = 0; // last action
			}
			Thread.sleep(20);
		}
	}

	// oyun çerçevesi
	public void gameScreen_1() {
		cn.getTextWindow().output("+-");
		for (int i = 1; i < 6; i++) {
			cn.getTextWindow().output(i + "---");
		}
		cn.getTextWindow().output(">" + "  X" + "\n");
		cn.getTextWindow().output(0, 1, '|');
		cn.getTextWindow().output("\n");
		for (int i = 1; i < 5; i++) {
			cn.getTextWindow().output(i + "\n" + "+" + "\n" + "+" + "\n" + "+" + "\n");
		}
		cn.getTextWindow().output(0, 17, '.');

		cn.getTextWindow().output("5" + "\n" + "." + "\n" + "." + "\n" + "v" + "\n" + "\n" + "Y");
		cn.getTextWindow().setCursorPosition(32, 0);
		for (int i = 0; i < 23; i++) {
			cn.getTextWindow().output("-");
		}
		cn.getTextWindow().output("   P   I   E   C   E   S   ");
		for (int i = 0; i < 23; i++) {
			cn.getTextWindow().output("-");
		}

	}

	public void generateTwentyPieces() {

		// tüm satırdaki piece arrayleri tek bir yirmilik arrayde birleştirme

		for (int j = 0; j < 4; j++) {
			twentyPieces[0][j] = fourMainPieces[j];
			twentyPieces[1][j] = threeMainPieces[j];

		}
		for (int i = 0; i < 5; i++) {
			twentyPieces[2][i] = twoMainPieces[i];

		}
		for (int i = 0; i < 7; i++) {
			twentyPieces[3][i] = oneMainPieces[i];
		}

	}

	// 4 lük pieceler oluşturma ve onları bir arrayde birleştirme
	public void generateFourPieces() {
		for (int i = 0; i < fourMainPieces.length; i++) {
			fourMainPieces[i] = new Piece();
			fourMainPieces[i].generateFour();

		}

	}

	// 3 lük pieceler oluşturma ve onları bir arrayde birleştirme
	public void generateThreePieces() {
		for (int i = 0; i < threeMainPieces.length; i++) {
			threeMainPieces[i] = new Piece();
			threeMainPieces[i].generateThree();

		}

	}

	// 2 lik pieceler oluşturma ve onları bir arrayde birleştirme
	public void generateTwoPieces() {
		for (int i = 0; i < twoMainPieces.length; i++) {
			twoMainPieces[i] = new Piece();
			twoMainPieces[i].generateTwo();

		}

	}// 1 lik pieceler oluşturma ve onları bir arrayde birleştirme

	public void generateOnePieces() {
		for (int i = 0; i < oneMainPieces.length; i++) {
			oneMainPieces[i] = new Piece();
			oneMainPieces[i].generateOne();

		}

	}

	// üstteki işlemleri tek fonksiyonda birleştirme ve en sonda onları 20 lik
	// arrayde birleştirme
	public void mainGeneratorOfTwentyPieces() {
		generateFourPieces();
		generateThreePieces();
		generateTwoPieces();
		generateOnePieces();
		generateTwentyPieces();

	}

	// piece yazdırdığımızda iskeletini oluşturan 5x5 lik bir char arrayi
	public void emptyCubeGenerate() {
		for (int i = 0; i < emptyCube.length; i++) {
			for (int j = 0; j < emptyCube[i].length; j++) {
				emptyCube[i][0] = '.';
				emptyCube[i][4] = '.';
				emptyCube[0][j] = '.';
				emptyCube[4][j] = '.';

			}
		}
		emptyCube[1][1] = ' ';
		emptyCube[2][1] = ' ';
		emptyCube[1][2] = ' ';
		emptyCube[1][3] = ' ';
		emptyCube[3][1] = ' ';
		emptyCube[2][1] = ' ';
		emptyCube[3][2] = ' ';
		emptyCube[3][3] = ' ';
		emptyCube[2][2] = ' ';
		emptyCube[2][3] = ' ';

	}

	// 20 Piecelerin ekranda oluşması
	public void mainGeneratorOfTwentyPiecesVisually() {
		// Piece arrayleri oluşması
		mainGeneratorOfTwentyPieces();
		// boş küp oluşması iskelet için
		emptyCubeGenerate();
		int xF = 33;
		int yF = 2;
		int cursorx = 32;
		int cursory = 2;
		int counter = 1;
		int j = 0;
		// 4 tane 4'lük piece yazdırılması
		for (int i = 0; i < fourMainPieces.length; i++) {

			// piece'nin yanındaki numaralar
			cn.getTextWindow().setCursorPosition(cursorx - 1, cursory);
			cn.getTextWindow().output(String.format("%s", 0));
			;
			cn.getTextWindow().setCursorPosition(cursorx, cursory);
			cn.getTextWindow().output(String.format("%s", counter));
			;
			// piece yazdırılması
			fourMainPieces[i].pieceWriter(cn, fourMainPieces[i].fourPiece, emptyCube, xF, yF);
			// piece yazdırıldıktan sonra koordinatı sağa kaydırma
			xF += 20;
			cursorx += 20;
			counter++;

		}
		int xTh = 33;
		int yTh = 17;
		cursorx = 32;
		cursory = 17;
		// 4 tane 3'lük piece yazdırılması
		for (int i = 0; i < threeMainPieces.length; i++) {
			cn.getTextWindow().setCursorPosition(cursorx - 1, cursory);
			cn.getTextWindow().output(String.format("%s", 0));
			;
			cn.getTextWindow().setCursorPosition(cursorx, cursory);
			cn.getTextWindow().output(String.format("%s", counter));
			;
			threeMainPieces[i].pieceWriter(cn, threeMainPieces[i].threePiece, emptyCube, xTh, yTh);

			xTh += 20;
			cursorx += 20;
			counter++;

		}
		int xThr = 33;
		int yThr = 32;
		cursorx = 32;
		cursory = 32;
		// 5 tane 2'lik piece yazdırılması
		for (int i = 0; i < twoMainPieces.length; i++) {
			if (i == 0) {
				cn.getTextWindow().setCursorPosition(cursorx - 1, cursory);
				cn.getTextWindow().output(String.format("%s", 0));
				;
				cn.getTextWindow().setCursorPosition(cursorx, cursory);
				cn.getTextWindow().output(String.format("%s", counter));
				;
				counter++;
			}

			////////////////
			else {

				cn.getTextWindow().setCursorPosition(cursorx - 1, cursory);
				cn.getTextWindow().output(String.format("%s", counter));
				;
				counter++;
			}

			twoMainPieces[i].pieceWriter(cn, twoMainPieces[i].twoPiece, emptyCube, xThr, yThr);

			xThr += 16;
			cursorx += 16;

		}
		int xO = 33;
		int yO = 44;
		cursorx = 32;
		cursory = 44;
		// 7 tane 1'lik piece yazdırılması
		for (int i = 0; i < oneMainPieces.length; i++) {
			cn.getTextWindow().setCursorPosition(cursorx - 1, cursory);
			cn.getTextWindow().output(String.format("%s", counter));
			;
			counter++;
			oneMainPieces[i].pieceWriter(cn, oneMainPieces[i].onePiece, emptyCube, xO, yO);

			xO += 11;
			cursorx += 11;

		}

		Piece[][] temPieces = humanTeam.humanRobots[0].usedPieces;
		for (int i = 0; i < temPieces.length; i++) {
			for (int j1 = 0; j1 < temPieces[i].length; j1++) {
				if (temPieces[i][j1] != null) {
					mouseClick(temPieces[i][j1].area.startX, temPieces[i][j1].area.startY);
				}
			}
		}

		temPieces = humanTeam.humanRobots[1].usedPieces;
		for (int i = 0; i < temPieces.length; i++) {
			for (int j1 = 0; j1 < temPieces[i].length; j1++) {
				if (temPieces[i][j1] != null) {
					mouseClickUsed(temPieces[i][j1].area.startX, temPieces[i][j1].area.startY);
				}
			}
		}

	}

	// piece seçtiğimizde piece sayının yanında # şeklinde seçilmesi
	public void mouseClick(int x, int y) {
		cn.getTextWindow().setCursorPosition(x - 3, y - 1);
		System.out.println("###");
		cn.getTextWindow().setCursorPosition(x - 3, y);
		System.out.println("#");
		cn.getTextWindow().setCursorPosition(x - 3, y + 1);
		System.out.println("###");
	}

	public void mouseClickUsed(int x, int y) {
		cn.getTextWindow().setCursorPosition(x - 3, y - 1);
		System.out.println("===");
		cn.getTextWindow().setCursorPosition(x - 3, y);
		System.out.println("=");
		cn.getTextWindow().setCursorPosition(x - 3, y + 1);
		System.out.println("===");
	}

	public void mouseUsedBefore(int x, int y) {
		cn.getTextWindow().setCursorPosition(x - 3, y - 1);
		System.out.println("---");
		cn.getTextWindow().setCursorPosition(x - 3, y);
		System.out.println("-");
		cn.getTextWindow().setCursorPosition(x - 3, y + 1);
		System.out.println("---");
	}

	// başka piece seçtiğimizde bir önceki seçtiğimiz piecenin sıfırlanması
	public void mouseReset(int x, int y) {
		cn.getTextWindow().setCursorPosition(x - 3, y - 1);
		System.out.println("   ");
		cn.getTextWindow().setCursorPosition(x - 3, y);
		System.out.println(" ");
		cn.getTextWindow().setCursorPosition(x - 3, y + 1);
		System.out.println("   ");
	}

	public void currentPiece() {

		cn.getTextWindow().setCursorPosition(0, 25);
		cn.getTextWindow().output(" Current Piece (#): " + "\n" + "   X: " + "   Y: " + "\n");
		informationUsedPieces();

		// bunu koydum ki yazdırıktan sonra son harfin yarısı gitmesin başka yere
		// atıyoruz cursor'u
		cn.getTextWindow().setCursorPosition(1, 1);

	}

	public void robotGamesScreen() {
		cn.getTextWindow().setCursorPosition(0, 0);
		cn.getTextWindow().output("=== RoboChess ==============================================");
		forcesWriter(0, 2, 35, 1);
		cn.getTextWindow().setCursorPosition(0, 13);
		cn.getTextWindow().output("=== RoboPingPong ===========================================");
		forcesWriter(0, 16, 35, 15);
		cn.getTextWindow().setCursorPosition(0, 26);
		cn.getTextWindow().output("=== RoboRun ================================================");
		forcesWriter(0, 30, 35, 29);

	}

	public void cleanScreen() {
		cn.getTextWindow().setCursorPosition(0, 0);
		for (int i = 0; i < cn.getTextWindow().getRows() - 1; i++) {
			for (int j = 0; j < cn.getTextWindow().getColumns() - 1; j++) {
				cn.getTextWindow().output(' ');
			}
		}
		cn.getTextWindow().setCursorPosition(0, 0);
	}

	public void forcesWriter(int x, int y, int x1, int x2) {
		cn.getTextWindow().setCursorPosition(x, y);
		cn.getTextWindow().output("HR1 - In:" + humanTeam.humanRobots[0].In + "   Sk:" + humanTeam.humanRobots[0].Sk
				+ "   Sp:" + humanTeam.humanRobots[0].Sp);
		y += 2;
		cn.getTextWindow().setCursorPosition(0, y);
		cn.getTextWindow().output("HR2 - In:" + humanTeam.humanRobots[1].In + "   Sk:" + humanTeam.humanRobots[1].Sk
				+ "   Sp:" + humanTeam.humanRobots[1].Sp);
		y += 2;
		cn.getTextWindow().setCursorPosition(0, y);
		cn.getTextWindow().output("CR1 - In:" + computerTeam.computerRobots[0].In + "   Sk:"
				+ computerTeam.computerRobots[0].Sk + "   Sp:" + computerTeam.computerRobots[0].Sp);
		y += 2;
		cn.getTextWindow().setCursorPosition(0, y);
		cn.getTextWindow().output("CR2 - In:" + computerTeam.computerRobots[1].In + "   Sk:"
				+ computerTeam.computerRobots[1].Sk + "   Sp:" + computerTeam.computerRobots[1].Sp);
		tablePrint(x1, x2);

	}

	public void roboChess() throws InterruptedException {
		chessPointH1 = 0;
		chessPointH2 = 0;
		chessPointR1 = 0;
		chessPointR2 = 0;

		int totalInt = humanTeam.humanRobots[0].In + humanTeam.humanRobots[1].In + computerTeam.computerRobots[0].In
				+ computerTeam.computerRobots[1].In;
		while (chessPointH1 != 20 && chessPointH2 != 20 && chessPointR1 != 20 && chessPointR2 != 20) {
			int random = (int) (Math.random() * totalInt) + 1;
			if (1 <= random && random <= humanTeam.humanRobots[0].In) {
				chessPointH1++;
			} else if (humanTeam.humanRobots[0].In < random
					&& random <= humanTeam.humanRobots[1].In + humanTeam.humanRobots[0].In) {
				chessPointH2++;
			} else if (humanTeam.humanRobots[1].In + humanTeam.humanRobots[0].In < random
					&& random <= computerTeam.computerRobots[1].In + humanTeam.humanRobots[1].In
							+ humanTeam.humanRobots[0].In) {
				chessPointR1++;
			} else {
				chessPointR2++;
			}

		}

		int x = 36;
		for (int i = 0; i < chessPointH1; i++) {
			cn.getTextWindow().setCursorPosition(x, 3);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;

		}
		x = 36;
		for (int i = 0; i < chessPointH2; i++) {
			cn.getTextWindow().setCursorPosition(x, 5);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;
		}
		x = 36;
		for (int i = 0; i < chessPointR1; i++) {
			cn.getTextWindow().setCursorPosition(x, 7);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;
		}
		x = 36;
		for (int i = 0; i < chessPointR2; i++) {
			cn.getTextWindow().setCursorPosition(x, 9);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;
		}
		
		
		whoIsWinner(chessPointH1,chessPointH2,chessPointR1,chessPointR2,2, 11);
		cn.getTextWindow().setCursorPosition(0, 0);

	}

	public void tablePrint(int x, int y) {
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("0    5    10   15   20");
		y++;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("+----+----+----+----+");
		y++;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("|");
		y++;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("+----+----+----+----+");
		y++;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("|");
		y++;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("+----+----+----+----+");
		y++;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("|");
		y++;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("+----+----+----+----+");
		y++;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("|");
		y++;
		cn.getTextWindow().setCursorPosition(x, y);
		System.out.println("+----+----+----+----+");
	}

	public void roboPingPong() throws InterruptedException {
		pingPointH1 = 0;
		pingPointH2 = 0;
		pingPointR1 = 0;
		pingPointR2 = 0;

		int totalSk = humanTeam.humanRobots[0].Sk + humanTeam.humanRobots[1].Sk + computerTeam.computerRobots[0].Sk
				+ computerTeam.computerRobots[1].Sk;
		while (pingPointH1 != 20 && pingPointH2 != 20 && pingPointR1 != 20 && pingPointR2 != 20) {
			int random = (int) (Math.random() * totalSk) + 1;
			if (1 <= random && random <= humanTeam.humanRobots[0].Sk) {
				pingPointH1++;
			} else if (humanTeam.humanRobots[0].Sk < random
					&& random <= humanTeam.humanRobots[1].Sk + humanTeam.humanRobots[0].Sk) {
				pingPointH2++;
			} else if (humanTeam.humanRobots[1].Sk + humanTeam.humanRobots[0].Sk < random
					&& random <= computerTeam.computerRobots[1].Sk + humanTeam.humanRobots[1].Sk
							+ humanTeam.humanRobots[0].Sk) {
				pingPointR1++;
			} else {
				pingPointR2++;
			}

		}

		int x = 36;
		for (int i = 0; i < pingPointH1; i++) {
			cn.getTextWindow().setCursorPosition(x, 17);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;

		}
		x = 36;
		for (int i = 0; i < pingPointH2; i++) {
			cn.getTextWindow().setCursorPosition(x, 19);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;
		}
		x = 36;
		for (int i = 0; i < pingPointR1; i++) {
			cn.getTextWindow().setCursorPosition(x, 21);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;
		}
		x = 36;
		for (int i = 0; i < pingPointR2; i++) {
			cn.getTextWindow().setCursorPosition(x, 23);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;
		}
		whoIsWinner(pingPointH1,pingPointH2,pingPointR1,pingPointR2,2, 24);
		cn.getTextWindow().setCursorPosition(0, 0);
	}

	public void roboRun() throws InterruptedException {
		runPointH1 = 0;
		runPointH2 = 0;
		runPointR1 = 0;
		runPointR2 = 0;

		int totalSp = humanTeam.humanRobots[0].Sp + humanTeam.humanRobots[1].Sp + computerTeam.computerRobots[0].Sp
				+ computerTeam.computerRobots[1].Sp;
		while (runPointH1 != 20 && runPointH2 != 20 && runPointR1 != 20 && runPointR2 != 20) {
			int random = (int) (Math.random() * totalSp) + 1;
			if (1 <= random && random <= humanTeam.humanRobots[0].Sp) {
				runPointH1++;
			} else if (humanTeam.humanRobots[0].Sp < random
					&& random <= humanTeam.humanRobots[1].Sp + humanTeam.humanRobots[0].Sp) {
				runPointH2++;
			} else if (humanTeam.humanRobots[1].Sp + humanTeam.humanRobots[0].Sp < random
					&& random <= computerTeam.computerRobots[1].Sp + humanTeam.humanRobots[1].Sp
							+ humanTeam.humanRobots[0].Sp) {
				runPointR1++;
			} else {
				runPointR2++;
			}

		}

		int x = 36;
		for (int i = 0; i < runPointH1; i++) {
			cn.getTextWindow().setCursorPosition(x, 31);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;

		}
		x = 36;
		for (int i = 0; i < runPointH2; i++) {
			cn.getTextWindow().setCursorPosition(x, 33);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;
		}
		x = 36;
		for (int i = 0; i < runPointR1; i++) {
			cn.getTextWindow().setCursorPosition(x, 35);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;
		}
		x = 36;
		for (int i = 0; i < runPointR2; i++) {
			cn.getTextWindow().setCursorPosition(x, 37);
			cn.getTextWindow().output("X");
			Thread.sleep(30);
			x++;
		}
		whoIsWinner(runPointH1,runPointH2,runPointR1,runPointR2,2, 38);
		cn.getTextWindow().setCursorPosition(2, 44);
		if(olympicPointH<olympicPointC) {
			cn.getTextWindow().output("Total Winner of the RobotOlympics: RED team");
		}
		else {
			cn.getTextWindow().output("Total Winner of the RobotOlympics: BLUE team");
		}
		cn.getTextWindow().setCursorPosition(1, 1);
	}

	public void informationScreenHuman() {
		cn.getTextWindow().setCursorPosition(0, 43);
		cn.getTextWindow().output("Human Robot 1 (HR1)" + "\n" + "In:" + humanTeam.humanRobots[0].In + "  Sk:"
				+ humanTeam.humanRobots[0].Sk + "  Sp:" + humanTeam.humanRobots[0].Sp);
	}

	public void informationUsedPieces() {
		cn.getTextWindow().setCursorPosition(0, 27);
		cn.getTextWindow().output(" Used pieces (=/-)" + "\n");
		for (int i = 0; i < twentyPieces.length; i++) {
			for (int j = 0; j < twentyPieces[i].length; j++) {
				if(twentyPieces[i][j]!=null) {
					String symbol = " ";
					if (gameCountH > -1 && humanTeam.humanRobots[gameCountH].usedPieces[i][j] != null) {
						symbol = "=";
					} else if (gameCountH == 1 && humanTeam.humanRobots[0].usedPieces[i][j] != null) {
						symbol = "-";
					}
					cn.getTextWindow().output(" "+symbol+twentyPieces[i][j].id);
				}
			}
			cn.getTextWindow().output("\n");
		}

	}

	public void informationScreenComp() {
		cn.getTextWindow().setCursorPosition(0, 45);
		cn.getTextWindow()
				.output("Computer Robot "+ (gameCountC+1) +"(CR1)" + "\n" + "In:" + computerTeam.computerRobots[gameCountC].In + "  Sk:"
						+ computerTeam.computerRobots[gameCountC].Sk + "  Sp:"
						+ computerTeam.computerRobots[gameCountC].Sp);

	}
	public void whoIsWinner(int h1, int h2, int r1, int r2, int x, int y) {
		
		int maxt1= Math.max(h1,h2);
		int maxt2=Math.max(r1, r2);
		int point=Math.max(maxt1,maxt2);
		cn.getTextWindow().setCursorPosition(x, y);
		if(point==h1) {
			cn.getTextWindow().output("Winner: Blue Team (HR1)");;
			olympicPointH++;
		}
		else if(point==h2) {
			cn.getTextWindow().output("Winner: Blue Team (HR2)");
			olympicPointH++;
		}
		else if(point==r1) {
			cn.getTextWindow().output("Winner: Red Team (CR1)");
			olympicPointC++;
		}
		else if(point==r2) {
			cn.getTextWindow().output("Winner: Red Team (CR2)");
			olympicPointC++;
		}
		
	}

	
}
