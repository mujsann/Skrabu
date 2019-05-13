/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Conrad Joye Thomas
 */
public class Rack extends javax.swing.JPanel{
private Image image = new javax.swing.ImageIcon(getClass().getResource("/icons/rack.png")).getImage();
public static int TILE_COUNT = 103;
private ArrayList<Tile> tilesVector = new ArrayList<>();
private Box boxes[] = new Box[7];
    /**
     * Creates new form Rack
     */
    public Rack() {
        initComponents();
        initTiles();
    }
 @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0,  null);
        
    }
    
    private void addTilesToVector(Tile tile, int count){
        for(int i = 0; i < count; i++){
            tilesVector.add(tile);
        }
    }
    private void initTiles(){
        addTilesToVector(new Tile(ScrabbleValues.A), 9);
        addTilesToVector(new Tile(ScrabbleValues.B), 2);
        addTilesToVector(new Tile(ScrabbleValues.D), 4);
        addTilesToVector(new Tile(ScrabbleValues.E), 12);
        addTilesToVector(new Tile(ScrabbleValues.Ẹ), 4);
        addTilesToVector(new Tile(ScrabbleValues.F), 2);
        addTilesToVector(new Tile(ScrabbleValues.G), 3);
        addTilesToVector(new Tile(ScrabbleValues.GB), 2);
        addTilesToVector(new Tile(ScrabbleValues.H), 2);
        addTilesToVector(new Tile(ScrabbleValues.I), 9);
        addTilesToVector(new Tile(ScrabbleValues.J), 1);
        addTilesToVector(new Tile(ScrabbleValues.K), 1);
        addTilesToVector(new Tile(ScrabbleValues.L), 4);
        addTilesToVector(new Tile(ScrabbleValues.M), 2);
        addTilesToVector(new Tile(ScrabbleValues.N), 6);
        addTilesToVector(new Tile(ScrabbleValues.O), 8);
        addTilesToVector(new Tile(ScrabbleValues.Ọ), 2);
        addTilesToVector(new Tile(ScrabbleValues.P), 2);
        addTilesToVector(new Tile(ScrabbleValues.R), 6);
        addTilesToVector(new Tile(ScrabbleValues.S), 4);
        addTilesToVector(new Tile(ScrabbleValues.Ṣ), 4);
        addTilesToVector(new Tile(ScrabbleValues.T), 6);
        addTilesToVector(new Tile(ScrabbleValues.U), 4);
        addTilesToVector(new Tile(ScrabbleValues.W), 2);
        addTilesToVector(new Tile(ScrabbleValues.Y), 2);
        initRack(); 
    }
    private void initRack(){
     Tile tile;
        for(int i = 0; i < boxes.length; i++){
            Collections.shuffle(tilesVector);
            tile = tilesVector.get(0);
            boxes[i] = new Box(ScrabbleValues.EMPTY);
            tile.setSourceListener(boxes[i].getDragSourceListener());
            boxes[i].setRackBox(true);
            boxes[i].setBox(tile);
            tilesVector.remove(tile);
            --TILE_COUNT;
            add(boxes[i]);
        }
    }
    public void replenishRack(){
        Tile tile;
        if(boxes[0].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) && TILE_COUNT > 0){
            Collections.shuffle(tilesVector);
            tile = tilesVector.get(0);
            tile.setSourceListener(boxes[0].getDragSourceListener());
            boxes[0].setBox(tile);
            boxes[0].revalidate();
            tilesVector.remove(tile);
            --TILE_COUNT;
        }
        if(boxes[1].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) && TILE_COUNT > 0){
            Collections.shuffle(tilesVector);
            tile = tilesVector.get(0);
            tile.setSourceListener(boxes[1].getDragSourceListener());
            boxes[1].setBox(tile);
            boxes[1].revalidate();
            tilesVector.remove(tile);
            --TILE_COUNT;
        }
        if(boxes[2].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) && TILE_COUNT > 0){
            Collections.shuffle(tilesVector);
            tile = tilesVector.get(0);
            tile.setSourceListener(boxes[2].getDragSourceListener());
            boxes[2].setBox(tile);
            boxes[2].revalidate();
            tilesVector.remove(tile);
            --TILE_COUNT;
        }
        if(boxes[3].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) && TILE_COUNT > 0){
            Collections.shuffle(tilesVector);
            tile = tilesVector.get(0);
            tile.setSourceListener(boxes[3].getDragSourceListener());
            boxes[3].setBox(tile);
            boxes[3].revalidate();
            tilesVector.remove(tile);
            --TILE_COUNT;
        }
        if(boxes[4].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) && TILE_COUNT > 0){
            Collections.shuffle(tilesVector);
            tile = tilesVector.get(0);
            tile.setSourceListener(boxes[4].getDragSourceListener());
            boxes[4].setBox(tile);
            boxes[4].revalidate();
            tilesVector.remove(tile);
            --TILE_COUNT;
        }
        if(boxes[5].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) && TILE_COUNT > 0){
            Collections.shuffle(tilesVector);
            tile = tilesVector.get(0);
            tile.setSourceListener(boxes[5].getDragSourceListener());
            boxes[5].setBox(tile);
            boxes[5].revalidate();
            tilesVector.remove(tile);
            --TILE_COUNT;
        }
        if(boxes[6].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) && TILE_COUNT > 0){
            Collections.shuffle(tilesVector);
            tile = tilesVector.get(0);
            tile.setSourceListener(boxes[6].getDragSourceListener());
            boxes[6].setBox(tile);
            boxes[6].revalidate();
            tilesVector.remove(tile);
            --TILE_COUNT;
        }
    }
    public int getSackCount(){
        return tilesVector.size();
    }

    public ArrayList<Tile> getRack(){
        ArrayList<Tile> t = new ArrayList<>();
        for (Box box : boxes) {
            t.add(box.getBoxTile());
        }
        return t;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    
}
