package Game;

public class Player {
    char color;
    int pontos;

    public Player(char color)
    {
        this.color = color;
        this.pontos = 0;
    }

    public char getColor() {
        return color;
    }
    public int getPontos() {
        return pontos;
    }
    public void setColor(char color) {
        this.color = color;
    }
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
}
