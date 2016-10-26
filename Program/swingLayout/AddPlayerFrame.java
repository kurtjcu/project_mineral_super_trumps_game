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
    String filePrefix = "Program/swingLayout/images/";

    public static int xSize = 1024;
    public static int ySize = 768;

    public AddPlayerFrame(){
        super("Mineral Supertrumps - Add players");
        this.setLayout(new BorderLayout());
        setSize(xSize, ySize);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        title = new JLabel("Welcome to Mineral Supertrumps");
        playerEntry = new AddPlayerAddPlayers();
        superTumpsImage = new AddPlayerSuperTrumpImage(fileName, filePrefix);
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
