import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowFrame extends JFrame{
    private JPanel mainPanel;
    private JTextArea howArea;
    private JButton returnButton;
    private JTextPane howPane;
    private JPanel outSideHow;
    private JPanel inSideHow;

    public HowFrame(){
        Utility.__initialization__(this, mainPanel);
        howPane.setBackground(ThemeValues.FG2);
        outSideHow.setBackground(ThemeValues.FG2);
        inSideHow.setBackground(ThemeValues.BG1);
        howPane.setText(
                "Greetings Adventurer! I see you wanted to explore the vast mysteries of Algorithms and Data Structures. " +
                        "Well you’re in luck! Welcome to the Island of Algos! Explore the different areas from Aliquam Lacinia Purus, to insulam! " +
                        "\n How To Play  \n\t\tEach level the player would have the option to choose a category from 4 selections out of 7 total categories. " +
                        "The categories would indicate a certain subtopic, namely. Graph Theory, Algorithmic Design Paradigms. Metaheuristic Algorithm, " +
                        "Intractability, Metaheuristic Algorithms: Swarm Intelligence, Sorting Algorithms, and Searching Algorithms. Instead of an" +
                        " incrementing cash, we replaced it to have a checkpoint system. There would also be a point system where you answer the" +
                        " question in the least tries, would benefit to a greater point reward. ");
        setVisible(true);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame MF = new MainFrame();
                MF.setVisible(true);
                dispose();
            }
        });
    }
}
