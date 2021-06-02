import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DATA FILE FORMAT
 *      0 - Data Game File - DO NOT EDIT*
 *      1 - Total Questions
 *      2 - Current Points of the game
 *      3 - Fullscreen settings
 *      4 - Already Asked Questions
 *      5 - Disable Category
 *      6 -
 */
public interface DataEdit {
    String QUESTION_PATH = "src/resources/questions.txt"; // TODO: Paths are incorrect
    String DATA_PATH = "src/resources/data.txt";
    int DESCRIPTION_dl = 0;
    int TOTAL_QUESTIONS_dl = 1;
    int CURRENT_PTS_dl = 2;
    int FULLSCREEN_dl = 3;
    int ALREADY_ASKED_Qs_dl = 4;
    int DISABLE_CATEGORY_dl = 5;
    int LEVEL_dl = 6;
    int PROFESSORS_dl = 7;
//    String QUESTION_PATH = DataEdit.class.getResource("questions.txt").getFile();
//    String DATA_PATH = DataEdit.class.getResource("data.txt").getFile();

    /**
     * gets question from question text file
     * @param index
     * @return String from that line
     */
    static String getQuestionLineAt(int index){
        try{
            File dataFile = new File(QUESTION_PATH);
            Scanner S = new Scanner(dataFile);
            int counter = 0;
            //TODO: There are times when they pick already used questions
            while(S.hasNextLine()){
                String data = S.nextLine();
                if(counter == index){
                    return data;
                }
                counter++;
            }
            S.close();
        }catch (FileNotFoundException e){
            System.out.println("An Error Occured.");
            e.printStackTrace();
        }
        return null;
    }
    static String getDataLineAt(int index){
        try{
            File dataFile = new File(DATA_PATH);
            Scanner S = new Scanner(dataFile);
            int counter = 0;
            while(S.hasNextLine()){
                String data = S.nextLine();
                if(counter == index){
                    return data;
                }
                counter++;
            }
            S.close();
        }catch (FileNotFoundException e){
            System.out.println("An Error Occured.");
            e.printStackTrace();
        }
        return null;
    }

    static int stripInt(String str){
        return Integer.parseInt(str.replaceAll("[^0-9]", ""));
    }

    static void replace(int index, String str) {
//        Path path = Paths.get("data.txt");
        try{
            File dataFile = new File(DATA_PATH);
            Scanner S = new Scanner(dataFile);
            ArrayList<String> allLines = new ArrayList<>();
            int counter = 0;
            while(S.hasNextLine()){
                if(counter == index){
                    allLines.add(str);
                    S.nextLine();
                }else{
                    allLines.add(S.nextLine());
                }
                counter++;
            }
            S.close();
            FileWriter dataFileNew = new FileWriter(DATA_PATH);
            for(String i: allLines){
                dataFileNew.write(i + "\n");
            }
            dataFileNew.close();
        }catch (FileNotFoundException e){
            System.out.println("We can't find the file.");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("There was an error");
            e.printStackTrace();
        }
    }
    static void resetData(){
        int length = 0;
        try {
            File file = new File(QUESTION_PATH);
            Scanner S = new Scanner(file);
            int counter = 0;
            while(S.hasNextLine()){
                counter++;
                S.nextLine();
            }
            length = counter;
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }
        try {
            FileWriter dataFileNew = new FileWriter(DATA_PATH);
            dataFileNew.write("Data Game File - DO NOT EDIT*\n");
//            dataFileNew.write("Total Questions: " + length + "\n");
            dataFileNew.write("Total Questions: 15\n");
            dataFileNew.write("Current Points: 0\n");
            dataFileNew.write("Fullscreen: OFF\n");
            dataFileNew.write("Exempted Questions: \n");
            dataFileNew.write("Disabled Categories: \n");
            dataFileNew.write("Level: 0\n");
            dataFileNew.write("Available Professors: 1111111\n");
            dataFileNew.close();
        }catch (IOException e){
            System.out.println("ERROR in WRITING FILE.");
            e.printStackTrace();
        }
    }

}
