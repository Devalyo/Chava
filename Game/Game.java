package Game;

import GUI.ChessGUI;
import Pieces.*;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    int turn;

    public Game()
    {
        this.board = new Board();
        this.turn = 0;
        this.player1 = new Player('W');
        this.player2 = new Player('B');
        new ChessGUI(this);

    }
    public Game(Board board)
    {
        this.board = board;
        this.player1 = new Player('W');
        this.player2 = new Player('B');
        this.turn = 0;
    }

   public boolean isKingInCheck(char kingColor) {
        Square[][] squares = board.getSquares();

        int kingX = -1, kingY = -1;
        boolean kingFound = false;
        for (int i = 0; i < 8 && !kingFound; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = squares[i][j].getPiece();
                if (piece != null && piece instanceof King && piece.getColor() == kingColor) {
                    kingX = i;
                    kingY = j;
                    kingFound = true;
                    System.out.println("King in" + kingX + "and" + kingY);
                    break;
                }
            }
        }

        char opponentColor = (kingColor == 'W') ? 'B' : 'W';
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                Piece piece = squares[i][j].getPiece();
                if (piece != null && piece.getColor() == opponentColor) {
                    if (piece.isValidMove(i, j, kingX, kingY, board)) {
                        System.out.println(piece.getName() + " em " + i + " " + j + " atacando o rei");
                        return true; 
                    }
                }
            }

        }

        return false; 
    }

    public boolean isCheckmate(char kingColor) {

        Square[][] squares = board.getSquares();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = squares[i][j].getPiece();
                if (piece != null && piece.getColor() != kingColor) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            if (piece.isValidMove(i, j, x, y, board)) {
                                Piece capturedPiece = board.getSquares()[x][y].getPiece();
                                board.movePiece(i, j, x, y, board);

                                boolean stillInCheck = isKingInCheck(kingColor);

                                board.getSquares()[x][y].setPiece(capturedPiece);
                                board.getSquares()[i][j].setPiece(piece);

                                if (!stillInCheck) {
                                    return false; 
                                }
                            }
                        }
                    }
                }
            }
        }

    return true;
}
    public void reset()
    {
        this.board = new Board();
        this.turn = 0;
    }

    public Board getBoard() {
        return board;
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public int getTurn() {
        return turn;
    }
    public void nextTurn() {
        this.turn++;
    }
    
}
