package Pieces;

import Game.Board;
import Game.Square;

public class King extends Piece {

    public King(char color)
    {
        super(color);
    }

    @Override
    public boolean isValidMove(int x, int y, int x2, int y2, Board board) {
        Square[][] squares = board.getSquares();

        int offsetX = Math.abs(x2 - x);
        int offsetY = Math.abs(y2 - y);

        if (offsetX > 1 || offsetY > 1) {
            return false;
        }

        Piece destinationPiece = squares[x2][y2].getPiece();
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }

    @Override
    public Piece clone() {
        King copy = new King(this.color);
        return copy;
    }



    
}
