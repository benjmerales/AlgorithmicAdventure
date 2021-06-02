import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class QuestionFrame extends JFrame implements Utility, Sounds, ThemeValues{
    //<editor-fold desc="Instance Component Variables">
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
    //</editor-fold>

    private char correct = '~'; // Placeholder for correct answer a b c or d
    private double multiplier = 1;
    private boolean[] didWeAskAlready = new boolean[7];
    private QuestionObject Question;
    private DataReader DR;
    public QuestionFrame(ArrayList<QuestionObject> previous, QuestionObject Question){
        Utility.__initialization__(this, mainPanel);
        Utility.styleComponents(mainPanel.getComponents());
        DR = new DataReader();
        setSize(800,800);
        setLocationRelativeTo(null);
        //<editor-fold desc="Random Style?">
        returnToMainMenuButton.setBorder(null);
        returnToMainMenuButton.setFocusPainted(false);
        exitGameButton.setBorder(null);
        exitGameButton.setFocusPainted(false);
        //</editor-fold>
        this.Question = Question;
        this.updateScore();
        setProfsBox();
        constructQuestion(Question);
        DR.addQuestion(Question.id);

        JButton[] buttons = {choiceA, choiceB, choiceC, choiceD};
        for(JButton button: buttons) {
            button.addActionListener(e -> {
                Sounds.click_();
                if(     (correct == 'A' && button == choiceA) || (correct == 'B' && button == choiceB) ||
                        (correct == 'C' && button == choiceC) || (correct == 'D' && button == choiceD) ) {
                    Sounds.play(Sounds.win_sfc, false);
                    JOptionPane.showMessageDialog(null, "Correct!");
                    DR.setPoints(DR.getPoints() + Question.points);
                    DR.nextLevel();
                    DR.clearDisabled();
                    if(DR.getLevel() == 3){
                        System.out.println("You Win!");
                        System.out.println("Your total score is: " + DR.getPoints());
                        MainFrame MF = new MainFrame();
                        MF.setVisible(true);
                    }
                    else{
                        LevelFrame LF = new LevelFrame();
                        LF.setVisible(true);
                    }
                    updateScore(true);
                    this.setVisible(false);
                }
                else{
                    boolean alreadyLost = false;
                    Sounds.play(Sounds.wro_sfc, false);
                    JOptionPane.showMessageDialog(null, "Wrong");
                    updateScore(false);
                    ArrayList<Character> disables = DR.getDisable_category();
//                    if(!Utility.isNull(disableLine)){
                    if(disables != null){
                        JOptionPane.showMessageDialog(null, "GAME OVER");
                        DataEdit.resetData();
                        dispose();
                        alreadyLost = true;
                    }
                    this.setVisible(false);
                    String d_str = "Disabled Categories: ";
                    for (int i = 0; i < previous.size(); i++) {
                        if(previous.get(i) == Question){
                            if(i == 0) DR.addDisabled('A');
                            else if(i == 1) DR.addDisabled('B');
                            else if(i == 2) DR.addDisabled('C');
                            else if(i == 3) DR.addDisabled('D');
                            else System.out.println("ERROR ON FINDING PREVIOUS QUESTION ON PREVIOUS");
                        }
                    }
                    if(!alreadyLost) {
                        CategoryFrame CF = new CategoryFrame(previous);
                        CF.setVisible(true);
                    }
                    else {
                        MainFrame MF = new MainFrame();
                        MF.setVisible(true);
                    }
                }
            });
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    button.setBackground(ThemeValues.BG1);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    button.setBackground(ThemeValues.BG2);
                }

                @Override
                public void mousePressed(MouseEvent e){
                    super.mousePressed(e);
                    button.setForeground(Color.black);
                }

               /* @Override
                public void mouseReleased(MouseEvent e){
                    super.mouseReleased(e);
                    button.setForeground(ThemeValues.FG2);
                }
                @Override
                public void mouseDragged(MouseEvent e){
                    super.mouseDragged(e);
                    button.setForeground(ThemeValues.FG2);
                }*/
            });

        }

        //<editor-fold desc="Powerups">
        a5050Button.addActionListener(e -> {
            Sounds.click_();
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
            multiplier = .5;
            a5050Button.setEnabled(false);
        });
        skipButton.addActionListener(e -> {
            Sounds.click_();
            JOptionPane.showMessageDialog(null, "Skipped! Points deducted greatly");
            updateScore(true);
            this.setVisible(false);
            LevelFrame LF = new LevelFrame();
            LF.setVisible(true);
            multiplier = 0.25;
            int points = (int) (Question.points * multiplier);
            DR.nextLevel();
            DR.addPoints(points);
            this.dispose();
        });
        askButton.addActionListener(e -> {
            Sounds.click_();
            int x = profsBox.getSelectedIndex();
            if((x == 0 && topicText.getText().contains("Graph")) || (x==1 && topicText.getText().contains("Design")) ||
               (x == 2 && topicText.getText().contains("Meta")) || (x==3 && topicText.getText().contains("Intract")) ||
               (x == 4 && topicText.getText().contains("Swarm")) || (x==5 && topicText.getText().contains("Sorting")) ||
               (x == 6 && topicText.getText().contains("Search")) ){
                    if(DR.isProfAvailable(x)) {
                        JOptionPane.showMessageDialog(null, "This question is easy! It is obviously " + correct + "");
                        DR.askProfessor(x);
                    }else{
                        JOptionPane.showMessageDialog(null, "You have already asked me! Go bother someone else");
                    }
                }
            else{
                int r = new Random().nextInt(4);
                char rc = '-';
                if(r == 0) rc = 'A'; else if(r == 1) rc = 'B'; else if(r == 2) rc = 'C'; else if(r == 3) rc = 'D'; else
                    System.out.println("ERROR on ASK POWER UP");
                if(DR.isProfAvailable(x)) {
                    JOptionPane.showMessageDialog(null, "This isn't my expertise so uhh.... " + rc + "?");
                    DR.askProfessor(x);
                }else{
                    JOptionPane.showMessageDialog(null, "You have already asked me! Go bother someone else");
                }
            }
        });
        //</editor-fold>

        //<editor-fold desc="Hot Bar">
        exitGameButton.addActionListener(e -> {
            Sounds.click_();
            int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if(result == JOptionPane.YES_OPTION) System.exit(0);
        });
        returnToMainMenuButton.addActionListener(e -> {
            Sounds.click_();
            new MainFrame().setVisible(true);
            this.dispose();
        });
        fullscreenONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sounds.click_();
                if(fullscreenONButton.getText().contains("ON")){
                    fullscreenONButton.setText("Fullscreen OFF");
                    dispose();
                    setUndecorated(false);
                    pack();
                    setLocationRelativeTo(null);
                    DR.setFullscreen(false);
                    setVisible(true);
                }
               else{
                    fullscreenONButton.setText("Fullscreen ON");
                    dispose();
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    DR.setFullscreen(true);
                    setUndecorated(true);
                    setVisible(true);
                }
            }
        });
        //</editor-fold>

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
        accPtsText.setText(DR.getPoints() + "");
        if(Q.q_img){
            URL sub = Utility.getThisResource("resources/q_" + 1 + ".png");
//            String sub = "src/resources/Images/Questions/q_" + 1 + ".png";
            questionField.setIcon(new ImageIcon(sub));
        }
        if(Q.c_img){
            String sub = "src/resources/Images/Choices/c_" + 1;
//            String sub = "src/resources/Images/Choices/c_" + 1;
            choiceA.setIcon(new ImageIcon(sub + "_a.png"));
            choiceB.setIcon(new ImageIcon(sub + "_b.png"));
            choiceC.setIcon(new ImageIcon(sub + "_c.png"));
            choiceD.setIcon(new ImageIcon(sub + "_d.png"));
        }
    } // Swing editor method to change components by Q data
    public boolean didWeWin(){

        return true;
    }
    public void updateScore(boolean correct){
//        M_inter.tally(correct);
        updateScore();
    } // sets number of rights and wrong
    public void updateScore(){
        int right = DR.getLevel();
        ArrayList<Integer> qs = DR.getAlready_asked_questions();
        int total = qs.size();
        int wrong = total - right;
        System.out.println(total + "-" + right + "=" +wrong);
        scoreText.setText(right + " | " + wrong);
    } // utility function for constructor to print current tally on window

}
