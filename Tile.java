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

    @Override
    public String toString() {
        return "" + value;
    }

    public void setTileColor(){
        if(this.value == 2){
            this.tileColor = new Color(125, 225, 225);
        }else if (this.value == 4){
            this.tileColor = new Color(125, 125, 225);
        }else if (this.value == 8){
            this.tileColor = new Color(125,125,125);
        }else if (this.value == 16){
            this.tileColor = new Color(225, 150, 225);
        }else if (this.value == 32){
            this.tileColor = new Color(75, 150, 150);
        }else if (this.value == 64){
            this.tileColor = new Color(255, 75, 75);
        }else if (this.value == 128){
            this.tileColor = new Color(255, 255, 75);
        }else if (this.value == 256){
            this.tileColor = new Color(0, 255, 0);
        }else if (this.value == 512){
            this.tileColor = new Color(162, 81, 111);
        }else if (this.value == 1024){
            this.tileColor = new Color(255, 102, 0);
        }else if (this.value == 2048){
            this.tileColor = new Color(0,175,255);
        }else if (this.value == 4096){
            this.tileColor = new Color(255, 0, 255);
        }
    }

    public Color getTileColor() {
        return tileColor;
    }
}
