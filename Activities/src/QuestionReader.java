import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuestionReader {
    List<QuestionObject> QO_Lists = new ArrayList<>();      // ALL Questions
    List<QuestionObject> Q_Avail;                           // Questions still available for picking
    List<QuestionObject> Q_Bin = new ArrayList<>();         // Questions that have been already used and answered
    String PATHNAME = "src/questions.txt";
    QuestionObject currentQ = null;                         // Placeholder for QuestionObject
    Random R = new Random();
    int line_counter = 1;
    public QuestionReader(){
        try {
            File questionFile = new File(PATHNAME);
            Scanner S = new Scanner(questionFile);
            while(S.hasNextLine()){
                //System.out.println("Parsing Line " + line_counter++);
                String data = S.nextLine();
                String[] tempData = data.split(" / ");
                QO_Lists.add(new QuestionObject(tempData));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("questions in format");
        Q_Avail = QO_Lists;
    }

    public QuestionObject pickQuestion(){
        if(Q_Avail.size() <= 0){
            System.out.println("ERROR on Question Reader: Ran out of questions!");
            String[] err = {"00", "NULL_CAT", "NULL_Q", "NULL_A", "NULL_B", "NULL_C", "NULL_D", "X", "999"};
            return new QuestionObject(err);
        }
        int x = R.nextInt(Q_Avail.size());
        QuestionObject question = Q_Avail.remove(x);
        Q_Bin.add(question);
        currentQ = question;
        System.out.println("Picked Question ID0" + currentQ.id);
        return question;
    }

    public void addBackQuestion(QuestionObject Q){
        Q_Bin.remove(Q);
        Q_Avail.add(Q);
    }

}
