package swingLayout;

import gamePackage.Game;

import java.util.ArrayList;

/**
 * Created by kurt.Schoenhoff on 26/10/2016.
 */
public class GuiView {

    public static AddPlayerFrame addPlayerFrame;

    public static FrameGridBagMainGame gameFrame;

    public static Game game;

    public GuiView(Game game){

        this.game = game;

        startAddPlayerFrame();
    }

    public void startAddPlayerFrame(){

        addPlayerFrame = new AddPlayerFrame();
    }

    public static void startGameFrame(){
        gameFrame = new FrameGridBagMainGame();
        gameFrame.createAndShowGUI(game);
    }



    public static void main(String[] args) {
        GuiView guiView = new GuiView(new Game());
        ArrayList<String> names = new ArrayList<>();
        guiView.startAddPlayerFrame();
    }
}
