/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

/**
 *
 * @author Conrad Joye Thomas
 */

public class Box extends javax.swing.JPanel {
    private static final long serialVersionUID = -1483465271986543254L;
    private ScrabbleValues boxType;
    private Tile tile;
    private boolean rackBox = false;
    private Image image = (new javax.swing.ImageIcon(getClass().getResource("/icons/blank.png"))).getImage();
    public final static DataFlavor TILE_FLAVOR = new DataFlavor(Tile.class, "Tile Instances");
    
    
    /**
     * Creates new form Box
     * @param boxType
     */
    public Box(ScrabbleValues boxType) {
        initComponents();
        DropTarget dt = new DropTarget(this,DnDConstants.ACTION_MOVE, new MyDropListener());
        this.boxType = boxType;
        switch(boxType){
            case BLANK:
                image = (new javax.swing.ImageIcon(getClass().getResource("/icons/blank.png"))).getImage();
                break;
            case EMPTY:
                image = (new javax.swing.ImageIcon(getClass().getResource("/icons/boxTile.png"))).getImage();
                break;
            case DOULBE_LETTER_SCORE:
                image = (new javax.swing.ImageIcon(getClass().getResource("/icons/dls.png"))).getImage();
                break;
            case DOULBE_WORD_SCORE:
                image = (new javax.swing.ImageIcon(getClass().getResource("/icons/dws.png"))).getImage();
                break;
            case STAR:
                image = (new javax.swing.ImageIcon(getClass().getResource("/icons/star.png"))).getImage();
                break;
            case TRIPLE_WORD_SCORE:
                image = (new javax.swing.ImageIcon(getClass().getResource("/icons/tws.png"))).getImage();
                break;
            case TRIPLE_LETTER_SCORE:
                image = (new javax.swing.ImageIcon(getClass().getResource("/icons/tls.png"))).getImage();
                break;
        }
        repaint();
    }

    
    public void setBox(Tile box) {
        this.box.setIcon(box.getIcon());
        this.box.setSourceListener(new MySourceListener());
        this.box.setLetter(box.getLetter());
        this.box.setValue(box.getValue());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  //To change body of generated methods, choose Tools | Templates.
        g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
        
    }

    public Tile getBoxTile() {
        return box;
    }

    public boolean isRackBox() {
        return rackBox;
    }

    public void setRackBox(boolean rackBox) {
        this.rackBox = rackBox;
        setOpaque(false);
    }

    public int getBoxTileValue(){
        return getBoxTile().getValue();
    }
    class MyDropListener implements DropTargetListener {
        @Override
        public void dragEnter(DropTargetDragEvent event) 
        {
            if (event.isDataFlavorSupported(TILE_FLAVOR) && box.isEmpty()) {
                    return;
            }
            event.rejectDrag();
        }
        @Override
        public void dragExit(DropTargetEvent event) {
        }
        @Override
        public void dragOver(DropTargetDragEvent event) {
        }
        @Override
        public void dropActionChanged(DropTargetDragEvent event) {
        }
        @Override
        public void drop(DropTargetDropEvent event) {
            if (event.isDataFlavorSupported(TILE_FLAVOR)){
                try {
                        event.acceptDrop(DnDConstants.ACTION_MOVE);
                        Transferable t = event.getTransferable();
                        tile = (Tile)(t.getTransferData(TILE_FLAVOR));
                        if(tile.isTileLocked()){
                            event.dropComplete(false);
                        }else{
                            setBox(tile);
                            event.dropComplete(true);
                        }
                        
                    }catch (UnsupportedFlavorException | IOException  e) {
                        event.dropComplete(false);
                        e.printStackTrace();
                    }
            
            }
        }
    }
    
    public DragSourceListener getDragSourceListener(){
        return new MySourceListener();
    }
    
     class MySourceListener implements DragSourceListener {
    
    @Override
        public void dragEnter(DragSourceDragEvent event) 
        {
            
        }
        @Override
        public void dragExit(DragSourceEvent event)
        {
            
        }
        @Override
        public void dragOver(DragSourceDragEvent event)
        {
            
        }
        @Override
        public void dropActionChanged(DragSourceDragEvent event)
        {
            
        }
        @Override
        public void dragDropEnd(DragSourceDropEvent event)
        {
            if ((event.getDropSuccess()) && (event.getDropAction() == DnDConstants.ACTION_MOVE)) {
                if(!box.isEmpty()){
                        box.setEmpty();
                }
                      
                
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        box = new gui.Tile(new MySourceListener());

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.Tile box;
    // End of variables declaration//GEN-END:variables
}
