import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

// Level Selection calls Category Frame from Mechanism static
public class LevelFrame extends JFrame implements ThemeValues, Utility, DataEdit{
    private JPanel mainPanel;
    private int img_progress = 1;
    private Random R = new Random();
    public DataReader DR;
    public LevelFrame(){
        Utility.__initialization__(this, mainPanel);
        Utility.styleComponents(mainPanel.getComponents());

        DR = new DataReader();
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Sounds.click_();
                ArrayList<QuestionObject> questions = getFourQuestions();
                CategoryFrame CF = new CategoryFrame(questions);
                CF.setVisible(true);
                dispose();
                img_progress++;
            }
        });
    }

    public ArrayList<QuestionObject> getFourQuestions(){
        ArrayList<QuestionObject> temp = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            temp.add(getQuestionObjectAtRandom());
        }
        return temp;
    }
    public QuestionObject getQuestionObjectAtRandom(){
        ArrayList<Integer> already_asked = DR.getAlready_asked_questions();
        int total = DR.getSizeQuestions();
        String[] thisQSplitted;
        if(already_asked.isEmpty()){
//            System.out.println("No Questions Used Yet...");
            int r = getRandomIntIn(0, total-1);
            thisQSplitted = DataEdit.getQuestionLineAt(r).split(" / ");
            return new QuestionObject(thisQSplitted);
        }
        System.out.println("There are exempted questions...");
        // TODO: Theres something wrong here
        int r = getRandomIntIn(0, total-1, already_asked);
        thisQSplitted = DataEdit.getQuestionLineAt(r).split(" / ");
        return new QuestionObject(thisQSplitted);
    }


    public int getRandomIntIn(int low, int high){
        return low + R.nextInt(high - low + 1);
    }
    public int getRandomIntIn(int low, int high, ArrayList<Integer> except){
        int x;
        do{
            if(high - low == 0) return low;
            x = low + R.nextInt(high - low + 1);
        }while(except.contains(x));
        return x;
    }

    public static void main(String[] args) {
        LevelFrame LF = new LevelFrame();
        LF.setVisible(true);
    }
}
