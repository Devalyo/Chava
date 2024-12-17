package GUI;
import javax.swing.*;
import Game.*;
import Pieces.Piece;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessGUI extends JFrame {
    private JButton[][] squares = new JButton[8][8];
    private Game game;
    private Board board;
    private Piece selectedPiece = null; 
    private int selectedX = -1, selectedY = -1;
    
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
                squares[row][col].setBackground((row + col) % 2 == 0 ? new Color(112, 102, 119) : new Color(204, 183, 174));
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
                System.out.println("selecinando" + piece.getName() + " em (" + row + ", " + col + ")");
            }

        } else {

            Piece piece = board.getSquares()[selectedX][selectedY].getPiece();
            
            if(piece.isValidMove(selectedX, selectedY, row, col, board))
            {
                board.movePiece(selectedX, selectedY, row, col, board);
                if(game.isKingInCheck(color == 'W'? 'B': 'W'))
                {
                    System.out.println("Check");

                }
                game.nextTurn();
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
                
                if (piece != null) {

                    // System.out.println(piece.getSprite());
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
}