package Pieces;

import Game.Board;
import Game.Square;

public class Rook extends Piece {

    public Rook(char color)
    {
        super(color);
    }   

    @Override
    public boolean isValidMove(int x, int y, int x2, int y2, Board board) {

        Square[][] squares = board.getSquares();

        if (x == x2 && y == y2) {
            return false; 
        }

        if (x != x2 && y != y2) {
            return false;
        }

        if (x == x2) {
            int direction = (y2 > y) ? 1 : -1;
            for (int i = y + direction; i != y2; i += direction) {
                if (squares[x][i].getPiece() != null) {
                    return false; 
                }
            }
        } else {
            int direction = (x2 > x) ? 1 : -1;
            for (int i = x + direction; i != x2; i += direction) {
                if (squares[i][y].getPiece() != null) {
                    return false; 
                }
            }
        }

        Piece destinationPiece = squares[x2][y2].getPiece();
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    @Override
    public Piece clone() {
        Rook copy = new Rook(this.color);
        return copy;
    }

}
