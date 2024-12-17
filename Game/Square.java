package Game;
import Pieces.Piece;

public class Square {

    Piece piece;
    int x;
    int y;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Square setX(int x) {
        this.x = x;

        return this;
    }
    public Square setY(int y) {
        this.y = y;

        return this;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}