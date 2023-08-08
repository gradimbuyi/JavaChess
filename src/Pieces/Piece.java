package Pieces;

import javax.swing.*;
import java.awt.*;

public abstract class Piece {
    private Integer locationX;
    private Integer locationY;
    private String type;
    private Boolean color;
    private JLabel image;

    public Piece(String type, boolean color, int locationX, int locationY) {
        this.type = type;
        this.color = color;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public abstract Integer[] legalMoves();

    public void setImage(String fileName) {
        ImageIcon finalImage;
        Image temporary = new ImageIcon(fileName).getImage();
        temporary = temporary.getScaledInstance(105, 105, Image.SCALE_SMOOTH);
        finalImage = new ImageIcon(temporary);
        image = new JLabel(finalImage);
    }

    public JLabel getImage(){
        return image;
    }
}
