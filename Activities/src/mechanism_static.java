import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

// Mechanism level selection
public class mechanism_static {
    static CategoryFrame CF = new CategoryFrame();
    static LevelFrame LS = new LevelFrame();
    static Score S = new Score();
    static boolean won = false;

    public static void categoryChange(boolean new_level){
        if(new_level) {                         // If the player chooses right, gets a new set of questions
            CF.repickCategories();
            CF.Categorize();
            S.wrongOnce = false;
        }
        else{                           // if the player chooses wrong, same set of question but wrong question is disable
            if(S.wrongOnce){
                JOptionPane.showMessageDialog(null, "GAME OVER"); System.exit(0);}          // If he chooses wrong twice, then game over.
            S.wrongOnce = true;
            CF.selectedButton.setEnabled(false);
            CF.halfPoints();
            CF.Categorize();
            CF.doublePoints();
        }
    } // Changes the CategoryFrame swing based on whether we generate new_level/not

    public static void tally(boolean correct){
        if(correct){
            S.right++;
            S.totalPoints += (int) (S.currentPoints * S.multiplier);
            S.multiplier = 1;
            if(S.right >= 3){
                JOptionPane.showMessageDialog(null, "You Win! Your Total Score is: " + S.totalPoints);
                won = true;
                String name = JOptionPane.showInputDialog(null, "Please enter your name, adventurer!\n My name is... ", "", JOptionPane.QUESTION_MESSAGE);
                try {
                    FileWriter FW = new FileWriter("src/scores.txt", true);
                    FW.write("\n" + name + " - " + S.totalPoints);
                    FW.close();
                }catch(IOException e){
                    System.out.println("ERROR on reading File Mechanism");
                    e.printStackTrace();
                }
                LS.setVisible(false);
                new score_draft_frame();
            }
            if(S.wrongOnce) S.multiplier = .5;

        }
        else S.wrong++;
    } // Tallies the number of rights and wrongs on the QuestionFrame along with adding pts.
    public static void resetButtons(){
        CF.CategA.setEnabled(true);
        CF.CategB.setEnabled(true);
        CF.CategC.setEnabled(true);
        CF.CategD.setEnabled(true);
    } // Reset all buttons to enable from categoryChange disable method
    public static void runNewGame(){
        CF.reset(); // Reset Category Frame
        S.reset(); // Reset Scores
        resetButtons(); // Reset Button enables
        Utility.resetDWAA(); // Reset Ask Power-Up data
        LS = new LevelFrame(); // Creates new Level Selection
        LS.setVisible(true);
    }
}
