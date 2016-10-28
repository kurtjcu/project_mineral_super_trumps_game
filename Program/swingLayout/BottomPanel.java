package swingLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Frame;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class BottomPanel extends JPanel {
    public BottomPanel(){
        super(new BorderLayout());
        int gameHeight = (int) (Math.round(Frame.ySize * .666));
        int gameWidth = (int) (Math.round(Frame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));

        this.setVisible(true);
    }
}
