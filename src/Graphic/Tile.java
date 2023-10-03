package Graphic;

import Pieces.Piece;
import javax.swing.JPanel;
import java.awt.Color;

public class Tile extends JPanel {
    private static final Color LIGHT = Color.decode("#f0f0f0");
    private static final Color DARK = Color.decode("#5b80ba");
    private final Integer locationX;
    private final Integer locationY;
    private final Color color;
    private Piece piece;
    private Boolean isOccupied;

    public Tile(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.piece = null;
        this.isOccupied = false;
        setBackground(color = locationX % 2 == locationY % 2 ? DARK : LIGHT);
    }

    public void removePiece() {
        if(this.piece == null) return;
        isOccupied = false;
        remove(piece);
        piece = null;
        repaint();
    }

    public void addPiece(Piece piece) {
        removePiece();
        this.piece = piece;
        this.piece.setLocation(locationX, locationY);
        this.isOccupied = true;
        this.add(piece);
        this.validate();
        this.repaint();
    }

    public Piece getPiece() {
        return piece;
    }

    public Integer getLocationX() {
        return locationX;
    }

    public Integer getLocationY() {
        return locationY;
    }
}
