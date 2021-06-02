import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/** Child of MAIN
 *
 */

// Category Frame calls QuestionFrame
public class CategoryFrame extends MainFrame{
    //<editor-fold desc="Swing Components">
    private JPanel mainPanel;
    public JButton CategA;
    public JButton CategB;
    public JButton CategD;
    public JButton CategC;
    private JButton returnToMainMenuButton;
    private JButton exitGameButton;
    //</editor-fold>

    public int q_cnt = 1; // Counter for number of questions printed
    ArrayList<QuestionObject> questions; // Array for all QuestionObjects A,B,C and D, to change points efficiently
    QuestionObject forA, forB, forC, forD; // Paired with JButtons CategA, CategB, CategC, CategD respectively.
    JButton selectedButton; // Placeholder to know what button the player has selected for disabling
    DataReader DR;
    public CategoryFrame(ArrayList<QuestionObject> questions){
        Utility.__initialization__(this,mainPanel);
        Utility.styleComponents(mainPanel.getComponents());
        this.questions = questions;
        DR = new DataReader();

        repickCategories();
        Categorize(); // Todo: Not halving or doubling properly
        JButton[] categs = new JButton[]{CategA, CategB, CategC, CategD};
        for(JButton categ: categs) {
            categ.setEnabled(true);
            categ.addActionListener(e -> {
                Sounds.click_();
                QuestionObject Q = null;
                selectedButton = categ;
                if(categ == CategA){
                    Q = forA;
                }
                else if(categ == CategB){
                    Q = forB;
                }
                else if(categ == CategC){
                    Q = forC;
                }
                else if(categ == CategD){
                    Q = forD;
                }
                else{
                    System.out.println("ERROR on Category Frame");
                }
                assert  Q != null;
                QuestionFrame UIQ = null;
//                doublePoints();
                UIQ = new QuestionFrame(questions, Q);
                UIQ.setVisible(true);
                dispose();
            });
        }
        ArrayList<Character> disable = DR.getDisable_category();
        if(disable == null) {
            System.out.println("No disable categories, hence previous question is correct");
        }else{
            halfPoints();
            Categorize();
            if(disable.contains('A')) {
                CategA.setEnabled(false);
            }
            else if(disable.contains('B')) {
                CategB.setEnabled(false);
            }
            else if(disable.contains('C')) {
                CategC.setEnabled(false);
            }
            else if(disable.contains('D')) {
                CategD.setEnabled(false);
            }
            else{
                System.out.println("ERROR ON DISABLING CATEGORY BUTTONS");
            }
        }
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
    } // Constructor that includes ActionListeners for Buttons
    public boolean has(char[] arr, char a){
        if(arr == null) return false;
        for(char b: arr){
            if( a == b) return true;
        }
        return false;
    }
    public void repickCategories(){
        forA = questions.get(0);
        forB = questions.get(1);
        forC = questions.get(2);
        forD = questions.get(3);
        questions = new ArrayList<>();
        questions.add(forA);
        questions.add(forB);
        questions.add(forC);
        questions.add(forD);
    } // Calls QR again to pick new questions
    public void Categorize(){
        CategA.setText(forA.category + " - " + forA.points + " points");
        CategB.setText(forB.category + " - " + forB.points + " points");
        CategC.setText(forC.category + " - " + forC.points + " points");
        CategD.setText(forD.category + " - " + forD.points + " points");
        q_cnt++;
    } // Swinger editor method to apply QuestionObject to Swing Components
    public void halfPoints(){
        for(QuestionObject thisQO: questions){
            thisQO.points /= 2;
        }
    } // Half method for points
    public void doublePoints(){
        for(QuestionObject thisQO: questions) {
            thisQO.points *= 2;
        }
    } // Double method for points
}
