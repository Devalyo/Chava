package Pieces;

import Game.Board;

public class Queen extends Piece {

    public Queen(char color)
    {
        super(color);
    }    

    @Override
    public boolean isValidMove(int x, int y, int x2, int y2, Board board) {
        if (x == x2 || y == y2) {
            return new Rook(this.getColor()).isValidMove(x, y, x2, y2, board);
        } else if (Math.abs(x2 - x) == Math.abs(y2 - y)) {
            return new Bishop(this.getColor()).isValidMove(x, y, x2, y2, board);
        }

        return false; 
    }

}