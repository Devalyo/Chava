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

        Square squares[][] = board.getSquares();
        int invertIfBlack = this.getColor() == 'B'? -1 : 1;

        if(Math.abs(x2 - x) > 2)
        {
            return false;
        }

        if(firstMove)
        {
            if((y2 == y && (x2 == x - (1 * invertIfBlack) || x2 == x - (2 * invertIfBlack)) && squares[x2][y2].getPiece() == null)) 
            {
                firstMove = false;
                return true;
            }

            if(squares[x2][y2].getPiece() != null && squares[x2][y2].getPiece().getColor() != this.getColor())
            {
                return true;
            }

        }
        else
        {
            if(y2 == y && (x2 == x - (1 * invertIfBlack) && squares[x2][y2].getPiece() == null)) 
            {
                return true;
            }

            if(squares[x2][y2].getPiece() != null && (y2 == (y -1) || y2 == y + 1) && x2 == x - (1 * invertIfBlack) && squares[x2][y2].getPiece().getColor() != this.getColor())
            {
                return true;
            }

        }

        return false;
        }
        
    }
        
    

