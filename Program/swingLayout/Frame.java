package swingLayout;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
/*
 * BorderLayoutDemo.java
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Frame {

    public static void main(String[] args) {
        new Frame();
    }

    public Frame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.setSize(1024,768);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {

            setLayout(new GridLayout(2, 0));
            add(createTopPane());
            add(createBottomPane());

        }

        protected JPanel createTopPane() {

            JPanel panel = new JPanel(new BorderLayout());
            panel.setSize(1024, 768/3);
            //panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            //panel.setBackground(Color.RED);

            JPanel content = new JPanel(new GridBagLayout());
            content.setOpaque(false);

            JPanel view3D = new JPanel();
            //view3D.setBackground(Color.DARK_GRAY);
            JPanel view2D = new JPanel();
            //view2D.setBackground(Color.PINK);
            JPanel viewIsometric = new JPanel();
            //viewIsometric.setBackground(Color.YELLOW);
            JPanel viewData = new JPanel();
            //viewData.setBackground(Color.MAGENTA);

            JPanel top = new JPanel(new GridLayout(0, 2));
            top.setBorder(new LineBorder(Color.GREEN, 2));
            top.add(view3D);
            top.add(view2D);

            JPanel bottom = new JPanel(new GridLayout(2, 0));
            bottom.add(viewIsometric);
            bottom.add(new JScrollPane(viewData));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weighty = 0.5f;
            gbc.weightx = 1f;
            gbc.fill = GridBagConstraints.BOTH;

            content.add(top, gbc);
            content.add(bottom, gbc);

            panel.add(content);
            panel.add(new JLabel("Character name"), BorderLayout.NORTH);

            return  panel;

        }

        protected JPanel createBottomPane() {

            JPanel panel = new JPanel();
            panel.setBackground(Color.BLUE);

            return panel;

        }

    }

}