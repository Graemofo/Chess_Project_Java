import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/
 
public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
	
 
    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);
 
        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
            else
                square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){			
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);		
    }



	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}
	
	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false; 
		}		
		return oponent;
	}
	/*
    This is a method to check if a piece is a White piece.
    */
	private Boolean checkBlackOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}
	/*
		This method is called when we press the Mouse. So we need to find out what piece we have 
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) 
			return;
 
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }
   
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }
     
 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before 
		putting the piece back on the board.
	*/
	boolean whiteMove = true;
    public void mouseReleased(MouseEvent e) {
      if (chessPiece == null) return;

        chessPiece.setVisible(false);
        boolean success = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = tmp.substring(0, (tmp.length() - 4));
        boolean validMove = false;


		/* Moves */
        int landingX = (e.getX() / 75);
        int landingY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -");
        System.out.println("Piece Being Moved = " + pieceName);
        System.out.println("Starting Coordinantes = " + startX + ", " + startY);
        System.out.println("X Movement is = " + xMovement);
        System.out.println("Y Movement is = " + yMovement);
        System.out.println("Landing Coordinantes = " + landingX + ", " + landingY);
        //System.out.println("White Move =" + whiteMove);
        System.out.println("--  --  --  --  --  --  --  --  --  --  --  --  --  --  --");
        if (whiteMove) {
            System.out.println("+++++  Blacks Move Next  +++++");
        } else {
            System.out.print("+++++  White's Move Next  +++++");
        }






// Pawn Logic Start

        if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
			validMove = false;
		} else {
			if (pieceName.equals("WhitePawn") && (whiteMove)) {    //If the piece is white
				if (startY == 1) {                  //If the piece is at the starting position
					if (((yMovement == 1) || (yMovement == 2)) && (startY < landingY) && (xMovement == 0)) {  //If the piece is moving 1 or 2 moves forward and not left or right
						if (yMovement == 2) {
							if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), e.getY() - 75))) {
								whiteMove = false;
								validMove = true;
							}
						} else {
							if (!piecePresent(e.getX(), e.getY())) {
								whiteMove = false;
								validMove = true;
							}
						}
					} else if ((yMovement == 1) && (startY < landingY) && (xMovement == 1)) { //if moving one space forward && one space to the left or right
						if (piecePresent(e.getX(), e.getY())) {                       //if there is a piece present on the spot
							if (checkWhiteOponent(e.getX(), e.getY())) {              //if the piece is an oponents piece
								validMove = true;
								whiteMove = false;
							}
						}
					}
				} else {   //Everything after first move
					if (((yMovement == 1)) && (startY < landingY) && (xMovement == 0)) {
						if (!piecePresent(e.getX(), e.getY())) {
							validMove = true;
							whiteMove = false;
							if (landingY == 0) {
								//progression = true;
							}
						}
					} else if ((yMovement == 1) && (startY < landingY) && (xMovement == 1)) {   // if pawn moves 1 space forward and one to either side
						if (piecePresent(e.getX(), e.getY())) {                        // if piece present in diagonal square
							if (checkWhiteOponent(e.getX(), e.getY())) {              // check if black opponent is in square
								validMove = true;
								whiteMove = false;
							}
						}
					}
				}
			}  //End of White Pawn method

			//Start of Black Pawn
			if (pieceName.equals("BlackPawn") && (!whiteMove)) {    //If the piece is black
				if (startY == 6) {                  //If the piece is at the starting position
					if (((yMovement == 1) || (yMovement == 2)) && (startY > landingY) && (xMovement == 0)) {  //If the piece is moving 1 or 2 moves forward and not left or right
						if (yMovement == 2) {
							if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), e.getY() + 75))) {
								validMove = true;
								whiteMove = true;
							}
						} else {
							if (!piecePresent(e.getX(), e.getY())) {
								validMove = true;
								whiteMove = true;
							}
						}
					} else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
								whiteMove = true;
							}
						}
					}
				} else {   //Everything after first move
					if (((yMovement == 1)) && (startY > landingY) && (xMovement == 0)) {
						if (!piecePresent(e.getX(), e.getY())) {
							validMove = true;
							whiteMove = true;
						}
					} else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
								whiteMove = true;
							}
						}
					}
				}
			}  //End of black pawn
//End of Pawn Logic
//Knight Logic
			//Start of Black Knight
			if (pieceName.equals("BlackKnight") && (!whiteMove)) {  //Start of Knight
					if ((yMovement == 2) && (xMovement == 1) || (yMovement == 1) && (xMovement == 2)) { //
						if (!piecePresent(e.getX(), e.getY())) {
							whiteMove = true;
							validMove = true;
						} else if (piecePresent(e.getX(), e.getY())) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								whiteMove = true;
								validMove = true;
							}
						}
					} else {
						validMove = false;
					}
				}

			//End of Black Knight
			//Start of White Knight
			if (pieceName.equals("WhiteKnight") && (whiteMove)) {  //Start of Knight
					if ((yMovement == 2) && (xMovement == 1) || (yMovement == 1) && (xMovement == 2)) { //
						if (!piecePresent(e.getX(), e.getY())) {
							whiteMove = false;
							validMove = true;
						} else if (piecePresent(e.getX(), e.getY())) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								whiteMove = false;
								validMove = true;
							}
						}
					} else {
						validMove = false;
					}
				}

//End of Knight Logic
//Start of Queen Logic
			//Start of Black Queen Logic
			if (pieceName.equals("BlackQueen")) {
				validMove = true;
			}
//End of Queen Logic
//Start of King Logic
			//Start of White King Logic
			//Black King Logic
			if (pieceName.equals("BlackKing") && (!whiteMove)) {
					if (yMovement == 1 || xMovement == 1) {
						if (!piecePresent(e.getX(), e.getY())) {
							whiteMove = true;
							validMove = true;
						} else if (piecePresent(e.getX(), e.getY())) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								whiteMove = true;
								validMove = true;
							} else {
								validMove = false;
							}
						}
					}
				}


			if (pieceName.equals("WhiteKing") && (whiteMove)) {
					if (yMovement == 1 || xMovement == 1) {
						if (!piecePresent(e.getX(), e.getY())) {
							whiteMove = false;
							validMove = true;
						} else if (piecePresent(e.getX(), e.getY())) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								whiteMove = false;
								validMove = true;
							} else {
								validMove = false;
							}
						}
					}
				}

//End of King Logic

//Start of Rook Logic
			//Start of White Rook
			if (pieceName.equals("WhiteRook") && (whiteMove)) {
				boolean inTheWay = false;
				int distance = Math.abs(startX - landingX);
					if (startX == landingX || startY == landingY) {
						whiteMove = false;
						validMove = true;
					} else {
						validMove = false;
					}
				}


			if (pieceName.equals("BlackRook") && (!whiteMove)) {
				boolean inTheWay = false;
				int distance = Math.abs(startX - landingX);
					if (startX == landingX || startY == landingY) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							} else {

							}
						}
					}
				}

//End of Rook Logic
//Start of Bishop Logic

			if (pieceName.equals("BlackBishup") && (!whiteMove)) {
				boolean inTheWay = false;
				int distance = Math.abs(startX - landingX);
					if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
						if ((startX - landingX < 0) && (startY - landingY < 0)) {
							for (int i = 0; i < distance; i++) {
								if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {  // Down Right
									inTheWay = true;
								}
							}
						} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
							for (int i = 0; i < distance; i++) {
								if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {    // Up Right
									inTheWay = true;
								}
							}
						} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
							for (int i = 0; i < distance; i++) {
								if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
							for (int i = 0; i < distance; i++) {
								if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
									inTheWay = true;
								}
							}
						}

						if (inTheWay) {
							validMove = false;
						} else {
							if (piecePresent(e.getX(), e.getY())) {
								if (pieceName.contains("White")) {
									if (checkWhiteOponent(e.getX(), e.getY())) {
										whiteMove = true;
										validMove = true;
									} else {
										validMove = false;
									}
								} else {
									if (checkBlackOponent(e.getX(), e.getY())) {
										whiteMove = true;
										validMove = true;
									} else {
										validMove = false;
									}
								}
							} else {
								whiteMove = true;
								validMove = true;
							}
						}
					} else {
						validMove = false;
					}
				}

			//Start of White Bishop
			if (pieceName.equals("WhiteBishup") && (whiteMove)) {
				boolean inTheWay = false;
				int distance = Math.abs(startX - landingX);
					if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
						if ((startX - landingX < 0) && (startY - landingY < 0)) {
							for (int i = 0; i < distance; i++) {
								if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
							for (int i = 0; i < distance; i++) {
								if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
							for (int i = 0; i < distance; i++) {
								if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
							for (int i = 0; i < distance; i++) {
								if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
									inTheWay = true;
								} 
							}
						}

						if (inTheWay) {
							validMove = false;
						} else {
							if (piecePresent(e.getX(), e.getY())) {
								if (pieceName.contains("Black")) {
									if (checkBlackOponent(e.getX(), e.getY())) {
										validMove = true;
										whiteMove = false;
									} else {
										validMove = false;
									}
								} else {
									if (checkWhiteOponent(e.getX(), e.getY())) {
										validMove = true;
										whiteMove = false;
									} else {
										validMove = false;
									}
								}
							} else {
								validMove = true;
								whiteMove = false;
							}
						}
					} else {
						validMove = false;
					}
				}



//End of Bishop Logic
		}


        if (!validMove) {
            int location = 0;
            if (startY == 0) {
                location = startX;
            } else {
                location = (startY * 8) + startX;
            }
            String pieceLocation = pieceName + ".png";
            pieces = new JLabel(new ImageIcon(pieceLocation));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        } else {
            if (success) {
                int location = 56 + (e.getX() / 75);
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                } else {
                    Container parent = (Container) c;
                    pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                }
            } else {
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    parent.add(chessPiece);
                } else {
                    Container parent = (Container) c;
                    parent.add(chessPiece);
                }
                chessPiece.setVisible(true);
            }
        }
	}

 
    public void mouseClicked(MouseEvent e) {
	
    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){
	
    }
    public void mouseExited(MouseEvent e) {
	
    }
 	
	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}


