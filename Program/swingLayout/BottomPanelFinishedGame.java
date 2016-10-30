package swingLayout; /**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import gamePackage.Game;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BottomPanelFinishedGame extends JPanel {



    JPanel text = new JPanel(new BorderLayout());
    JPanel buttons = new JPanel(new BorderLayout());

    Controller controller;


    public BottomPanelFinishedGame(Controller controller) {
        super(new BorderLayout());
        this.controller = controller;
        int gameHeight = (int) (Math.round(Frame.ySize * .666));
        int gameWidth = (int) (Math.round(Frame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.add(text, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.PAGE_END);
        this.setVisible(true);

        JList finishedPlayerList;
        DefaultListModel listModel = new DefaultListModel();
        finishedPlayerList = new JList(listModel);
        finishedPlayerList.setLayoutOrientation(JList.VERTICAL);
        JLabel winner = new JLabel("Winner: " + controller.game.winner.getName(), SwingConstants.CENTER);
        int i ;
        for (i = 1; i < controller.game.finishedPlayers.size(); i++) {
            listModel.addElement((i + 1) + ": " + controller.game.finishedPlayers.get(i).getName());
        }
        listModel.addElement("Loser: " + controller.game.loser.getName());
        //TODO: refactor into "addText"
        winner.setBorder(new EmptyBorder(30,30,30,30));
        winner.setFont(Frame.extraLargeFont);
        finishedPlayerList.setBorder(new EmptyBorder(100,200,100,200));
        text.add(winner, BorderLayout.PAGE_START);
        text.add(finishedPlayerList, BorderLayout.CENTER);

        addButtons();
    }

    void addButtons(){
        JButton playAgain = new JButton("Play Again");
        JButton quit = new JButton("Quit");
        buttons.add(playAgain, BorderLayout.LINE_START);
        buttons.add(quit, BorderLayout.LINE_END);
        buttons.setBorder(new EmptyBorder(20,100,20,100));
        buttons.setVisible(true);
    }

    public static void main(String[] args) {


        BottomPanelFinishedGame bottomPanel = new BottomPanelFinishedGame(new Controller(new Game()));
        bottomPanel.setBorder(new LineBorder(Color.GREEN, 2));

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(bottomPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
