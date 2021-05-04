import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowFrame extends JFrame{
    private JPanel mainPanel;
    private JTextArea howArea;
    private JButton returnButton;

    public HowFrame(){
        Utility.__init__(this, mainPanel);
        howArea.setText(
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
                Utility.button_actionLThread();
                MainFrame MF = new MainFrame();
                MF.setVisible(true);
                dispose();
            }
        });
    }
}
