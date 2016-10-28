package swingLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kurt.Schoenhoff on 26/10/2016.
 */
public class AddPlayerFrame extends JFrame {

    JLabel title;
    AddPlayerAddPlayers playerEntry;
    AddPlayerSuperTrumpImage superTumpsImage;
    JButton showRules;

    String fileName = "Slide65.jpg";


    public AddPlayerFrame(){
        super("Mineral Supertrumps - Add players");
        this.setLayout(new BorderLayout());
        setSize(Frame.xSize, Frame.ySize);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        title = new JLabel("Welcome to Mineral Supertrumps");
        playerEntry = new AddPlayerAddPlayers();
        superTumpsImage = new AddPlayerSuperTrumpImage(fileName, Frame.filePrefix);
        showRules = new JButton("Show Rules");

        this.add(title, BorderLayout.PAGE_START);
        this.add(playerEntry, BorderLayout.LINE_START);
        this.add(superTumpsImage, BorderLayout.CENTER);
        this.add(showRules, BorderLayout.LINE_END);
        this.setVisible(true);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            AddPlayerFrame frame = new AddPlayerFrame();

        });
    }
}
