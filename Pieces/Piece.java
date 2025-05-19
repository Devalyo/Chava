package Pieces;
import Game.Board;

public abstract class Piece {
    
    protected char color;
    private String sprite;
    private String name;

    public Piece(char color)
    {
        this.color = color;
        this.name = this.getClass().getSimpleName();
        this.sprite = "/img/" + this.name.toLowerCase() + this.color + ".png";
    }

    public boolean isValidMove(int x, int y, int x2, int y2, Board board)
    {
        return true;
    }

    public char getColor() {
        return color;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public abstract Piece clone();

}
