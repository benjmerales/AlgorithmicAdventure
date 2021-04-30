import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

// Mechanism level selection
public class Mechanism implements ConstantFrames{

    public boolean won = false;

    public void categoryChange(boolean new_level){
        if(new_level) {                         // If the player chooses right, gets a new set of questions
            CF_inter.repickCategories();
            CF_inter.Categorize();
            S_inter.wrongOnce = false;
        }
        else{                           // if the player chooses wrong, same set of question but wrong question is disable
            if(S_inter.wrongOnce){
                JOptionPane.showMessageDialog(null, "GAME OVER"); System.exit(0);}          // If he chooses wrong twice, then game over.
            S_inter.wrongOnce = true;
            CF_inter.selectedButton.setEnabled(false);
            CF_inter.halfPoints();
            CF_inter.Categorize();
            CF_inter.doublePoints();
        }
    } // Changes the CategoryFrame swing based on whether we generate new_level/not

    public void tally(boolean correct){
        if(correct){
            S_inter.right++;
            S_inter.totalPoints += (int) (S_inter.currentPoints * S_inter.multiplier);
            S_inter.multiplier = 1;
            if(S_inter.right >= 3){
                JOptionPane.showMessageDialog(null, "You Win! Your Total Score is: " + S_inter.totalPoints);
                won = true;
                String name = JOptionPane.showInputDialog(null, "Please enter your name, adventurer!\n My name is... ", "", JOptionPane.QUESTION_MESSAGE);
                try {
                    FileWriter FW = new FileWriter("src/scores.txt", true);
                    FW.write("\n" + name + " - " + S_inter.totalPoints);
                    FW.close();
                }catch(IOException e){
                    System.out.println("ERROR on reading File Mechanism");
                    e.printStackTrace();
                }
                LF_inter.setVisible(false);
                new score_draft_frame();
            }
            if(S_inter.wrongOnce) S_inter.multiplier = .5;
        }
        else S_inter.wrong++;
    } // Tallies the number of rights and wrongs on the QuestionFrame along with adding pts.
    public void resetButtons(){
        CF_inter.CategA.setEnabled(true);
        CF_inter.CategB.setEnabled(true);
        CF_inter.CategC.setEnabled(true);
        CF_inter.CategD.setEnabled(true);
    } // Reset all buttons to enable from categoryChange disable method
    public void runNewGame(){
        CF_inter.reset(); // Reset Category Frame
        S_inter.reset(); // Reset Scores
        resetButtons(); // Reset Button enables
        Utility.resetDWAA(); // Reset srAsk Power-Up data
        LF_inter.setImg_progress(1);
        LF_inter.setVisible(true);
    }
}
