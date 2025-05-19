package GUI;
import javax.swing.*;
import Game.*;
import Pieces.Pawn;
import Pieces.Piece;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChessGUI extends JFrame {
    private JButton[][] squares = new JButton[8][8];
    private Game game;
    private Board board;
    private Piece selectedPiece = null; 
    private int selectedX = -1, selectedY = -1;
    private Color black = new Color(112, 102, 119);
    private Color white = new Color(204, 183, 174);
    private Color green = new Color(0, 125, 0);
    
    public ChessGUI(Game game) {
        this.game = game;
        this.board = game.getBoard(); 

        setTitle("Chava");
        setLayout(new GridLayout(8, 8));
        initializeBoard();
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateBoard(); // Populate the board with pieces
        setVisible(true);
    }

    private void initializeBoard() {
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JButton();
                squares[row][col].setBackground((row + col) % 2 == 0 ? black : white);
                squares[row][col].setFocusPainted(false);
                squares[row][col].setContentAreaFilled(false);
                squares[row][col].setOpaque(true);
                squares[row][col].setBorderPainted(false);

                final int r = row;
                final int c = col;
                squares[row][col].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(r, c); // Handle click on a square
                    }
                });

                add(squares[row][col]);
            }
        }
    }


      private void handleSquareClick(int row, int col) {

        char color = game.getTurn() % 2 == 0? game.getPlayer1().getColor() : game.getPlayer2().getColor();

        if (selectedPiece == null) {

            Piece piece = board.getSquares()[row][col].getPiece();
            if(piece == null || piece.getColor() != color){
                return;
            }

            if (piece != null) {
                selectedPiece = piece;
                selectedX = row;
                selectedY = col;
                System.out.println("\nselecinando" + piece.getName() + " em (" + row + ", " + col + ")\n");
                highlightValidMoves(selectedX, selectedY, selectedPiece);
            }

        } else {

            Piece piece = board.getSquares()[selectedX][selectedY].getPiece();
            
            if(squares[row][col].getBackground() == green)
            {
                Board boardClone = board.copy();

                boardClone.movePiece(selectedX, selectedY, row, col, boardClone);

                if(game.isKingInCheck(selectedPiece.getColor(), boardClone))
                {
                    System.out.println("INVALID MOVE, PUTS KING IN CHECK");
                    selectedPiece = null; 
                    selectedX = -1;
                    selectedY = -1;
                    updateBoard(); 

                }
                else
                {
                    board.movePiece(selectedX, selectedY, row, col, board);
    
                    if(piece instanceof Pawn)
                    {
                        Pawn pawn = (Pawn) piece; 
                        pawn.setFirstMove(false);
                    }
                    
                    if(game.isKingInCheck(color == 'W'? 'B': 'W'))
                    {
                        System.out.println("Check");
    
                    }
                    game.nextTurn();

                }

            }

            selectedPiece = null; 
            selectedX = -1;
            selectedY = -1;
            updateBoard(); 
        }
    }

     private void updateBoard() {
        Square[][] boardSquares = board.getSquares();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = boardSquares[row][col];
                Piece piece = square.getPiece(); 
                squares[row][col].setBackground((row + col) % 2 == 0 ?  black : white);
                
                if (piece != null) {

                    ImageIcon icon = new ImageIcon(getClass().getResource(piece.getSprite()));
                    
                    Image img = icon.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);

                    squares[row][col].setIcon(new ImageIcon(img)); 
                }
                else
                {
                    squares[row][col].setIcon(null);
                }
            }
        }
    }


        private void highlightValidMoves(int x, int y, Piece piece) {

            ArrayList<Integer> moves = new ArrayList<>();
            
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(piece.isValidMove(x, y, i, j, board))
                        {
                            moves.add(i);
                            moves.add(j);
                        }
                
                    }
                }

                for(int i = 0; i < moves.size(); i += 2)
                {
                        squares[moves.get(i)][moves.get(i + 1)].setBackground(green);

                }

        }

     }        

