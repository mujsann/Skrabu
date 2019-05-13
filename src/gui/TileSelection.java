/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author Conrad Joye Thomas
 */
public class TileSelection implements Transferable{
    final static DataFlavor TILE_FLAVOR = new DataFlavor(Tile.class, "Tile Instances");
    private DataFlavor[] flavors = {TILE_FLAVOR};
    protected Tile label;
        public TileSelection(Tile lbl) {
            label = lbl;
        }
        
    @Override
        public DataFlavor[] getTransferDataFlavors() {
            return flavors;
        }
    
    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        for (int i = 0; i < flavors.length; i++) {
            if (flavors[i].equals(flavor)) {
                    return true;
            }
        }
        return false;
    }
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(TILE_FLAVOR)) {
                return label;
        }
        throw new UnsupportedFlavorException(flavor);
    }
}
