package Pieces;

import Game.Board;
import Game.Square;

public class Pawn extends Piece {

    boolean firstMove = true;

    public Pawn(char color)
    {
        super(color);
    }   

@Override
public boolean isValidMove(int x, int y, int x2, int y2, Board board) {
    Square[][] squares = board.getSquares();
    int direction = (this.getColor() == 'B') ? 1 : -1; 



    if (x2 < 0 || x2 >= 8 || y2 < 0 || y2 >= 8) {
        System.out.println("Move is out of bounds.");
        return false;
    }

    if (x2 != x + direction && !(firstMove && x2 == x + 2 * direction)) {
        return false;
    }

    if (y2 == y) {
        if (x2 == x + direction && squares[x2][y2].getPiece() == null) {
            return true;
        }
        if (firstMove && x2 == x + 2 * direction && squares[x2][y2].getPiece() == null && squares[x + direction][y].getPiece() == null) {
            return true;
        }
    }

    if (Math.abs(y2 - y) == 1 && x2 == x + direction) {
        Piece targetPiece = squares[x2][y2].getPiece();
        if (targetPiece != null && targetPiece.getColor() != this.getColor()) {
            return true;
        }
    }

    return false;
}

public void setFirstMove(boolean firstMove) {
    this.firstMove = firstMove;
}
}
