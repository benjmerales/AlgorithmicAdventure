import javax.swing.*;

/** Child of MAIN
 *
 */

// Category Frame calls QuestionFrame
public class CategoryFrame extends MainFrame implements ConstantFrames{
    private JPanel mainPanel;
    public JButton CategA;
    public JButton CategB;
    public JButton CategD;
    public JButton CategC;

    public int q_cnt = 1; // Counter for number of questions printed
    QuestionReader QR = new QuestionReader(); // Calls QuestionReader Object to get inputted texts
    QuestionObject forA, forB, forC, forD; // Paired with JButtons CategA, CategB, CategC, CategD respectively.
    JButton selectedButton; // Placeholder to know what button the player has selected for disabling
    QuestionObject[] Qs; // Array for all QuestionObjects A,B,C and D, to change points efficiently
    public CategoryFrame(){
        Utility.__init__(this,mainPanel);
        reset();
        repickCategories();
        Categorize();
        JButton[] categs = new JButton[]{CategA, CategB, CategC, CategD};
        for(JButton categ: categs) {
            categ.addActionListener(e -> {
                QuestionObject Q = null;
                selectedButton = categ;
                if(categ == CategA){
                    Q = forA;
                    QR.addBackQuestion(forB); QR.addBackQuestion(forC); QR.addBackQuestion(forD);

                }
                else if(categ == CategB){
                    Q = forB;
                    QR.addBackQuestion(forA); QR.addBackQuestion(forC); QR.addBackQuestion(forD);
                }
                else if(categ == CategC){
                    Q = forC;
                    QR.addBackQuestion(forB); QR.addBackQuestion(forA); QR.addBackQuestion(forD);
                }
                else if(categ == CategD){
                    Q = forD;
                    QR.addBackQuestion(forB); QR.addBackQuestion(forC); QR.addBackQuestion(forA);
                }
                else{
                    System.out.println("ERROR on Category Frame");
                }
                assert Q != null;
                S_inter.currentPoints = Q.points; // Todo: Change to non-static
                QuestionFrame UIQ = new QuestionFrame(Q);
                setVisible(false);
                UIQ.setVisible(true);
            });
        }

    } // Constructor that includes ActionListeners for Buttons
    public void repickCategories(){
        forA = QR.pickQuestion();
        forB = QR.pickQuestion();
        forC = QR.pickQuestion();
        forD = QR.pickQuestion();
        Qs = new QuestionObject[]{forA, forB, forC, forD};
    } // Calls QR again to pick new questions
    public void Categorize(){
        CategA.setText(forA.category + " - " + forA.points + " points");
        CategB.setText(forB.category + " - " + forB.points + " points");
        CategC.setText(forC.category + " - " + forC.points + " points");
        CategD.setText(forD.category + " - " + forD.points + " points");
        q_cnt++;
    } // Swinger editor method to apply QuestionObject to Swing Components
    public void halfPoints(){
        for(QuestionObject thisQO: Qs){
            thisQO.points /= 2;
        }
    } // Half method for points
    public void doublePoints(){
        for(QuestionObject thisQO: Qs) {
            thisQO.points *= 2;
        }
    } // Double method for points
    public void reset(){
        QR = new QuestionReader();
        q_cnt = 1;
        repickCategories();
        Categorize();
    }
}
