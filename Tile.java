import java.awt.*;

public class Tile {

    int value;
    Color tileColor;

    public Tile(){
        value = 0;
    }

    public Tile(int number){
        value = number;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
