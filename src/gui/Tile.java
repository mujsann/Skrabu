/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Conrad Joye Thomas
 */
public class Tile extends JLabel {
    private static final long serialVersionUID = -8896445938230019796L;
    private ImageIcon imageIcon = null;
    private ImageIcon emptyIcon = (new javax.swing.ImageIcon(getClass().getResource("/icons/boxTile.png")));
    private DragSource source = DragSource.getDefaultDragSource();
    protected Tile draggedComponent;
    transient protected DragSourceListener sourceListener = null;
    private boolean TileLocked = false;
    private ScrabbleValues letter = ScrabbleValues.DEFAULT_EMPTY;
    private int value;
    
        public Tile() {
            super("");
            initSource();
            setImageIcon(emptyIcon);
        }
        
        public Tile(Tile letter) {
            super("");
            initSource();
            setLetter(letter.getLetter());
            setSourceListener(letter.getSourceListener());
        }
        public Tile(ScrabbleValues letter) {
            super("");
            initSource();
            setLetter(letter);
        }
        
        public Tile(String letter) {
            super("");
            initSource();
            setLetter(letter);
        }
        
        public Tile(DragSourceListener sourceListener) {
            super("");
            initSource();
            setImageIcon(emptyIcon);
            this.sourceListener = sourceListener;
        }
        
        public Tile(ScrabbleValues letter, DragSourceListener sourceListener) {
            super("");
            initSource();
            setImageIcon(emptyIcon);
            this.sourceListener = sourceListener;
            setLetter(letter);
        }

        public ImageIcon getImageIcon() {
            return imageIcon;
        }

        private void setImageIcon(ImageIcon imageIcon) {
            this.imageIcon = imageIcon;
            setIcon(imageIcon);
        }

        public ScrabbleValues getLetter() {
            return letter;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public final void setLetter(ScrabbleValues letter) {
            this.letter = letter;
            switch(letter){
                case DEFAULT_EMPTY:
                    setImageIcon(emptyIcon);
                    break;
                case A:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/A.png")));
                    setValue(1);
                    break;
                case B:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/B.png")));
                    setValue(3);
                    break;
                case C:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/C.png")));
                    setValue(3);
                    break;
                case D:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/D.png")));
                    setValue(2);
                    break;
                case E:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/E.png")));
                    setValue(1);
                    break;
                case Ẹ:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/EE.png")));
                    setValue(1);
                    break;
                case F:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/F.png")));
                    setValue(4);
                    break;
                case G:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/G.png")));
                    setValue(2);
                    break;
                case GB:
                    setValue(2);
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/GB.png")));
                    break;
                case H:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/H.png")));
                    setValue(4);
                    break;
                case I:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/I.png")));
                    setValue(1);
                    break;
                case J:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/J.png")));
                    setValue(8);
                    break;
                case K:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/K.png")));
                    setValue(5);
                    break;
                case L:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/L.png")));
                    setValue(1);
                    break;
                case M:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/M.png")));
                    setValue(3);
                    break;
                case N:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/N.png")));
                    setValue(1);
                    break;
                case O:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/O.png")));
                    setValue(1);
                    break;
                case Ọ:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/OO.png")));
                    setValue(1);
                    break;
                case P:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/P.png")));
                    setValue(3);
                    break;
                case Q:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/P.png")));
                    setValue(10);
                    break;
                case R:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/R.png")));
                    setValue(1);
                    break;
                case S:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/S.png")));
                    setValue(1);
                    break;
                case Ṣ:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/SS.png")));
                    setValue(1);
                    break;
                case T:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/T.png")));
                    setValue(1);
                    break;
                case U:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/U.png")));
                    setValue(1);
                    break;
                case V:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/V.png")));
                    setValue(4);
                    break;
                case W:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/W.png")));
                    setValue(4);
                    break;
                case X:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/X.png")));
                    setValue(8);
                    break;
                case Y:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/Y.png")));
                    setValue(4);
                    break;
                case Z:
                    setImageIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letters/Z.png")));
                    setValue(10);
                    break;
            }
        }
        
        public final void setLetter(String value) {
            this.letter = convertScrabbleValues(value);
            setLetter(letter);
        }
        
        private ScrabbleValues convertScrabbleValues(String value){
            switch(value){
                case "A":
                    this.letter = ScrabbleValues.A;
                    break;
                case "B":
                    this.letter = ScrabbleValues.B;
                    break;
                case "C":
                    this.letter = ScrabbleValues.C;
                    break;
                case "D":
                    this.letter = ScrabbleValues.D;
                    break;
                case "E":
                    this.letter = ScrabbleValues.E;
                    break;
                case "Ẹ":
                    this.letter = ScrabbleValues.Ẹ;
                    break;
                case "F":
                    this.letter = ScrabbleValues.F;
                    break;
                case "G":
                    this.letter = ScrabbleValues.G;
                    break;
                case "GB":
                    this.letter = ScrabbleValues.GB;
                    break;
                case "H":
                    this.letter = ScrabbleValues.H;
                    break;
                case "I":
                    this.letter = ScrabbleValues.I;
                    break;
                case "J":
                    this.letter = ScrabbleValues.J;
                    break;
                case "K":
                    this.letter = ScrabbleValues.K;
                    break;
                case "L":
                    this.letter = ScrabbleValues.L;
                    break;
                case "M":
                    this.letter = ScrabbleValues.M;
                    break;
                case "N":
                    this.letter = ScrabbleValues.N;
                    break;
                case "O":
                    this.letter = ScrabbleValues.O;
                    break;
                case "Ọ":
                    this.letter = ScrabbleValues.Ọ;
                    break;
                case "P":
                    this.letter = ScrabbleValues.P;
                    break;
                case "Q":
                    this.letter = ScrabbleValues.Q;
                    break;
                case "R":
                    this.letter = ScrabbleValues.R;
                    break;
                case "S":
                    this.letter = ScrabbleValues.S;
                    break;
                case "Ṣ":
                    this.letter = ScrabbleValues.Ṣ;
                    break;
                case "T":
                    this.letter = ScrabbleValues.T;
                    break;
                case "U":
                    this.letter = ScrabbleValues.U;
                    break;
                case "V":
                    this.letter = ScrabbleValues.V;
                    break;
                case "W":
                    this.letter = ScrabbleValues.W;
                    break;
                case "X":
                    this.letter = ScrabbleValues.X;
                    break;
                case "Y":
                    this.letter = ScrabbleValues.Y;
                    break;
                case "Z":
                    this.letter = ScrabbleValues.Z;
                    break;
            }
            return letter;
        }
        
        public static final int getLetterValue(String letter) {
            int returnValue = 0;
            switch(letter.toUpperCase()){
                case "A":
                    returnValue = 1;
                    break;
                case "B":
                    returnValue = 3;
                    break;
                case "C":
                    returnValue = 3;
                    break;
                case "D":
                    returnValue = 2;
                    break;
                case "E":
                    returnValue = 1;
                    break;
                case "Ẹ":
                    returnValue = 1;
                    break;
                case "F":
                    returnValue = 4;
                    break;
                case "G":
                    returnValue = 2;
                    break;
                case "GB":
                    returnValue = 2;
                    break;
                case "H":
                    returnValue = 4;
                    break;
                case "I":
                    returnValue = 1;
                    break;
                case "J":
                    returnValue = 8;
                    break;
                case "K":
                    returnValue = 5;
                    break;
                case "L":
                    returnValue = 1;
                    break;
                case "M":
                    returnValue = 3;
                    break;
                case "N":
                    returnValue = 1;
                    break;
                case "O":
                    returnValue = 1;
                    break;
                case "Ọ":
                    returnValue = 1;
                    break;
                case "P":
                    returnValue = 3;
                    break;
                case "Q":
                    returnValue = 10;
                    break;
                case "R":
                    returnValue = 1;
                    break;
                case "S":
                    returnValue = 1;
                    break;
                case "Ṣ":
                    returnValue = 1;
                    break;
                case "T":
                    returnValue = 1;
                    break;
                case "U":
                    returnValue = 1;
                    break;
                case "V":
                    returnValue = 4;
                    break;
                case "W":
                    returnValue = 4;
                    break;
                case "X":
                    returnValue = 8;
                    break;
                case "Y":
                    returnValue = 4;
                    break;
                case "Z":
                    returnValue = 10;
                    break;
            }
            return returnValue;
        }
        
        public final void setSourceListener(DragSourceListener sourceListener) {
            this.sourceListener = sourceListener;
        }

        public DragSourceListener getSourceListener() {
            return sourceListener;
        } 

        public final void setEmpty() {
            this.letter = ScrabbleValues.DEFAULT_EMPTY;
            this.imageIcon = emptyIcon;
            setIcon(emptyIcon);
        }

        public boolean isEmpty() {
            return letter.equals(ScrabbleValues.DEFAULT_EMPTY);
        }

        public final void initSource() {
            source.createDefaultDragGestureRecognizer(this,DnDConstants.ACTION_MOVE, new MyGestureListener());
        }

        public boolean isTileLocked() {
            return TileLocked;
        }

        public void setTileLocked(boolean TileLocked) {
            this.TileLocked = TileLocked;
        }
        
        
   class MyGestureListener implements DragGestureListener {
       @Override
        public void dragGestureRecognized(DragGestureEvent event) {
            Cursor cursor = null;
            draggedComponent = (Tile)(event.getComponent());
            switch (event.getDragAction()) {
                case DnDConstants.ACTION_MOVE:
                    if(isEmpty() || isTileLocked()){
                        cursor = DragSource.DefaultMoveNoDrop;
                    }else{
                        //Image cursorImage = new javax.swing.ImageIcon(getClass().getResource("/icons/cursor.png")).getImage(); //toolkit.getImage("/icons/cursor.png");
                        Toolkit toolkit = Toolkit.getDefaultToolkit();
                        Image currentTile = createImage(32, 32);
                        //Image currentTile = imageIcon.getImage();
                        
                        currentTile.getGraphics().drawImage(imageIcon.getImage(), 0, 0, 32, 32, null);
                        //currentTile.getGraphics().drawImage(cursorImage,event.getDragOrigin().x, event.getDragOrigin().y, null);
                        cursor = toolkit.createCustomCursor(currentTile , new Point(0,0), "img");
                        //cursor = DragSource.DefaultMoveDrop;  
                    }
                    break;
                case DnDConstants.ACTION_COPY:
                    cursor = DragSource.DefaultCopyNoDrop;
                    break;
                case DnDConstants.ACTION_LINK:
                    cursor = DragSource.DefaultLinkNoDrop;
                    break;
            }
            if(sourceListener == null){
                event.startDrag(cursor, new TileSelection(draggedComponent));
            }else{
                event.startDrag(cursor, new TileSelection(draggedComponent), sourceListener);
            } 
        }
    }
   
}
