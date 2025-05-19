package Game;

import Pieces.*;

public class Board {

    private Square squares[][];

    public Board() 
    {
        squares = new Square[8][8];

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                this.squares[i][j] = new Square()
                .setX(i + 1)
                .setY(j + 1);
            }

        }
        
        for (int i = 0; i < 8; i++) {
            squares[1][i].setPiece(new Pawn('B'));
            squares[6][i].setPiece(new Pawn('W'));
        }

        squares[0][0].setPiece(new Rook('B'));
        squares[0][7].setPiece(new Rook('B'));
        squares[0][1].setPiece(new Knight('B'));
        squares[0][6].setPiece(new Knight('B'));
        squares[0][2].setPiece(new Bishop('B'));
        squares[0][5].setPiece(new Bishop('B'));
        squares[0][3].setPiece(new Queen('B'));
        squares[0][4].setPiece(new King('B'));

        squares[7][0].setPiece(new Rook('W'));
        squares[7][7].setPiece(new Rook('W'));
        squares[7][1].setPiece(new Knight('W'));
        squares[7][6].setPiece(new Knight('W'));
        squares[7][2].setPiece(new Bishop('W'));
        squares[7][5].setPiece(new Bishop('W'));
        squares[7][3].setPiece(new Queen('W'));
        squares[7][4].setPiece(new King('W'));
    }

    public Square[][] getSquares() {
        return squares;
    }

    public boolean movePiece(int x, int y, int x2, int y2, Board board)
    {
        Square square[][] = getSquares();

        Piece peca = square[x][y].getPiece();
        Square destino = square[x2][y2];
        square[x][y].setPiece(null);
        destino.setPiece(peca);

        return true;

    }

    public Board copy() {
        Board newBoard = new Board();
        newBoard.squares = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newBoard.squares[i][j] = new Square();
                Piece piece = this.squares[i][j].getPiece();
                if (piece != null) {
                    newBoard.squares[i][j].setPiece(piece.clone());
                }
            }
        }
        return newBoard;
    }
    }
        

    


