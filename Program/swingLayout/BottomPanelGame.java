package swingLayout;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class BottomPanelGame extends JPanel {


    public Font largeFont = new Font("SansSerif", Font.BOLD, 20);
    public Font extraLargeFont = new Font("SansSerif", Font.BOLD, 40);
    JPanel text = new JPanel(new BorderLayout());
    JPanel buttons = new JPanel(new BorderLayout());

    public BottomPanelGame() {
        super(new BorderLayout());
        int gameHeight = (int) (Math.round(FrameGridBagMainGame.ySize * .666));
        int gameWidth = (int) (Math.round(FrameGridBagMainGame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.add(text, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.PAGE_END);
        this.setVisible(true);
    }

    public BottomPanelGame(ArrayList<String> players) {
        this();
        JList finishedPlayerList;
        DefaultListModel listModel = new DefaultListModel();
        finishedPlayerList = new JList(listModel);
        finishedPlayerList.setLayoutOrientation(JList.VERTICAL);
        JLabel winner = new JLabel("Winner: " + players.get(0), SwingConstants.CENTER);
        int i ;
        for (i = 1; i < players.size() - 1; i++){
            listModel.addElement(players.get(i));
        }
        //TODO: refactor into "addText"
        winner.setBorder(new EmptyBorder(30,30,30,30));
        winner.setFont(extraLargeFont);
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

        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            names.add("Player " + (1+i));
        }
        BottomPanelGame bottomPanel = new BottomPanelGame(names);
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
