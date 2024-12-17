package Pieces;

import Game.Board;
import Game.Square;

public class Bishop extends Piece {

    public Bishop(char color)
    {
        super(color);
    }   


    @Override
    public boolean isValidMove(int x, int y, int x2, int y2, Board board) {
        Square[][] squares = board.getSquares();

          if (x == x2 && y == y2) {
            return false; 
        }
        if (Math.abs(x2 - x) != Math.abs(y2 - y)) {
            return false;
        }

        int xStep = (x2 > x) ? 1 : -1;
        int yStep = (y2 > y) ? 1 : -1;

        int i = x + xStep;
        int j = y + yStep;
        while (i != x2 && j != y2) {
            if (squares[i][j].getPiece() != null) {
                return false; 
            }
            i += xStep;
            j += yStep;
        }

        Piece destinationPiece = squares[x2][y2].getPiece();
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

}