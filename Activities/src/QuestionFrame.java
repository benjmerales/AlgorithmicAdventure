import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class QuestionFrame extends JFrame implements ConstantFrames{
    private JPanel mainPanel;
    private JButton choiceA;
    private JButton choiceB;
    private JButton choiceC;
    private JButton choiceD;
    private JPanel choicePanels;
    private JLabel questionField;
    private JLabel topicText;
    private JLabel scoreText;
    private JLabel accPtsText;
    private JButton skipButton;
    private JButton askButton;
    private JButton a5050Button;
    private JComboBox profsBox;
    private JPanel sidePanel;
    private JPanel questionPanel;
    private JButton returnToMainMenuButton;
    private JButton fullscreenOnButton;
    private JButton exitGameButton;
    private JButton fullscreenONButton;

    private char correct = '~'; // Placeholder for correct answer a b c or d
    public QuestionFrame(QuestionObject Question){
        Utility.__init__(this, mainPanel);
        Utility.specialChangeButton(choicePanels);
        this.setTitle("Algorithmic Adventure");
        this.updateScore();
        constructQuestion(Question);
        setProfsBox();
        returnToMainMenuButton.setBorder(null);
        returnToMainMenuButton.setFocusPainted(false);
        exitGameButton.setBorder(null);
        exitGameButton.setFocusPainted(false);
        JButton[] buttons = {choiceA, choiceB, choiceC, choiceD};
        for(JButton button: buttons) {
            button.addActionListener(e -> {
                Utility.button_actionLThread();
                if(     (correct == 'A' && button == choiceA) || (correct == 'B' && button == choiceB) ||
                        (correct == 'C' && button == choiceC) || (correct == 'D' && button == choiceD) ) {
                    PlaySound.play(PlaySound.win_sfc, false);
                    JOptionPane.showMessageDialog(null, "Correct!");
                    updateScore(true);
                    this.setVisible(false);
                    M_inter.categoryChange(true);
                    M_inter.resetButtons();
                    if(!M_inter.won)  LF_inter.setVisible(true);
                }
                else{
                    PlaySound.play(PlaySound.wro_sfc, false);
                    JOptionPane.showMessageDialog(null, "Wrong");
                    updateScore(false);
                    this.setVisible(false);
                    M_inter.categoryChange(false);
                    CF_inter.setVisible(true);
                }
            });
        }

        // Powerups
        a5050Button.addActionListener(e -> {
            Utility.button_actionLThread();
            JButton[] choices = {choiceA, choiceB, choiceC, choiceD};
            for(JButton b: choices){
                b.setEnabled(false);
            }
            int r;
            if(correct == 'A'){
                choiceA.setEnabled(true);
                r = getRandomExcept(0);
                choices[r].setEnabled(true);
            }else if(correct == 'B'){
                r = getRandomExcept(1);
                choiceB.setEnabled(true);
                choices[r].setEnabled(true);
            }
            else if(correct == 'C'){
                r = getRandomExcept(2);
                choiceB.setEnabled(true);
                choices[r].setEnabled(true);
            }else if(correct == 'D'){
                r = getRandomExcept(3);
                choiceD.setEnabled(true);
                choices[r].setEnabled(true);
            }
            S_inter.multiplier = .5;
        });
        skipButton.addActionListener(e -> {
            Utility.button_actionLThread();
            JOptionPane.showMessageDialog(null, "Skipped! Points deducted greatly");
            updateScore(true);
            this.setVisible(false);
            M_inter.categoryChange(true);
            M_inter.resetButtons();
            LF_inter.setVisible(true); // Changed
            this.dispose();
            S_inter.multiplier = 0.25; // Changed
        });
        askButton.addActionListener(e -> {
            Utility.button_actionLThread();
            int x = profsBox.getSelectedIndex();
            if((x == 0 && topicText.getText().contains("Graph")) || (x==1 && topicText.getText().contains("Design")) ||
               (x == 2 && topicText.getText().contains("Meta")) || (x==3 && topicText.getText().contains("Intract")) ||
               (x == 4 && topicText.getText().contains("Swarm")) || (x==5 && topicText.getText().contains("Sorting")) ||
               (x == 6 && topicText.getText().contains("Search")) ){
                    if(!Utility.didWeAskAlready[x]) {
                        JOptionPane.showMessageDialog(null, "This question is easy! It is obviously " + correct + "");
                        Utility.didWeAskAlready[x] = true;
                    }else{
                        JOptionPane.showMessageDialog(null, "You have already asked me! Go bother someone else");
                    }
                }
            else{
                int r = new Random().nextInt(4);
                char rc = '-';
                if(r == 0) rc = 'A'; else if(r == 1) rc = 'B'; else if(r == 2) rc = 'C'; else if(r == 3) rc = 'D'; else
                    System.out.println("ERROR on ASK POWER UP");
                if(!Utility.didWeAskAlready[x]) {
                    JOptionPane.showMessageDialog(null, "This isn't my expertise so uhh.... " + rc + "?");
                    Utility.didWeAskAlready[x] = true;
                }else{
                    JOptionPane.showMessageDialog(null, "You have already asked me! Go bother someone else");
                }
            }
        });

        // Hot bar
        exitGameButton.addActionListener(e -> {
            Utility.button_actionLThread();
            int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if(result == JOptionPane.YES_OPTION) System.exit(0);
        });
        returnToMainMenuButton.addActionListener(e -> {
            Utility.button_actionLThread();
            new MainFrame().setVisible(true);
            this.dispose();
        });
        fullscreenONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utility.button_actionLThread();
                if(fullscreenONButton.getText().contains("ON")){
                    fullscreenONButton.setText("Fullscreen OFF");
                    dispose();
                    setUndecorated(false);
                    setVisible(true);
                }
               else{
                    fullscreenONButton.setText("Fullscreen ON");
                    dispose();
                    setUndecorated(true);
                    setVisible(true);
                }
            }
        });
    }

    public void setProfsBox(){
        profsBox.addItem("Guichard"); // Graphs
        profsBox.addItem("Bentley"); // ADP
        profsBox.addItem("Gandomi"); // MA
        profsBox.addItem("Budd"); // I
        profsBox.addItem("Yang"); // SI
        profsBox.addItem("Morin"); // Sorting
        profsBox.addItem("Vidya"); // Searching
    }
    public int getRandomExcept(int except){
        Random R = new Random();
        int r;
        do{
            r = R.nextInt(4);
        }while(r == except);
        return r;
    }
    public void constructQuestion(QuestionObject Q){
        topicText.setText(Q.category);
        questionField.setText(Q.question);
        choiceA.setText(Q.choices[0]);
        choiceB.setText(Q.choices[1]);
        choiceC.setText(Q.choices[2]);
        choiceD.setText(Q.choices[3]);
        correct = Q.correct;
        accPtsText.setText(S_inter.totalPoints + "");

        if(Q.q_img){
            String sub = "src/Images/Questions/q_" + 1 + ".png";
            questionField.setIcon(new ImageIcon(sub));
        }
        if(Q.c_img){
            String sub = "src/Images/Choices/c_" + 1;
            choiceA.setIcon(new ImageIcon(sub + "_a.png"));
            choiceB.setIcon(new ImageIcon(sub + "_b.png"));
            choiceC.setIcon(new ImageIcon(sub + "_c.png"));
            choiceD.setIcon(new ImageIcon(sub + "_d.png"));
        }
    } // Swing editor method to change components by Q data
    public void updateScore(boolean correct){
        M_inter.tally(correct);
        updateScore();
    } // sets number of rights and wrong
    public void updateScore(){
        scoreText.setText(S_inter.right + " | " + S_inter.wrong);
    } // utility function for constructor to print current tally on window

}
