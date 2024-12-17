package Pieces;

import Game.Board;
import Game.Square;

public class Knight extends Piece {


    public Knight(char color)
    {
        super(color);
    }   


    @Override
    public boolean isValidMove(int x, int y, int x2, int y2, Board board) {
        Square[][] squares = board.getSquares();

        int offsetx = Math.abs(x2 - x);
        int offsety = Math.abs(y2 - y);

        if (!((offsetx == 2 && offsety == 1) || (offsetx == 1 && offsety == 2))) {
            return false;
        }

        Piece destinationPiece = squares[x2][y2].getPiece();
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }
}
    

