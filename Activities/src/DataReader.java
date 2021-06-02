import javax.xml.crypto.Data;
import java.util.ArrayList;

// TODO: Make this class to make it easier to read data
public class DataReader {

    String description;
    int sizeQuestions;
    int points;
    boolean fullscreen;
    ArrayList<Integer> already_asked_questions;
    ArrayList<Character> disable_category;
    int level;
    public DataReader(){
        already_asked_questions = new ArrayList<>();
        // DESCRIPTION
        this.description = DataEdit.getDataLineAt(0);
        // SIZE
        this.sizeQuestions = DataEdit.stripInt(DataEdit.getDataLineAt(1));
        // POINTS
        this.points = DataEdit.stripInt(DataEdit.getDataLineAt(2));
        // FULLSCREEN
        if(DataEdit.getDataLineAt(3).contains("ON")) {
            fullscreen = true;
        }else{ fullscreen = false;}

        String asked_raw = DataEdit.getDataLineAt(4);
        String asked = asked_raw.substring(20);

        if(Utility.isNull(asked)){
            System.out.println("Asked is Empty");
        }
        else {
            String[] split_asked = asked.split(" ");
            for (String i : split_asked) {
                already_asked_questions.add(Integer.parseInt(i));
            }
        }
        String disable_raw = DataEdit.getDataLineAt(5);
        String disable = disable_raw.substring(21);
        if(Utility.isNull(disable)){
            System.out.println("Disable is Empty.");
        }else{
            String[] split_disable = disable.split(" ");
            this.disable_category = new ArrayList<>();
            for (int i = 0; i < split_disable.length; i++) {
                disable_category.add(split_disable[i].charAt(0));
            }
        }
        this.level = DataEdit.stripInt(DataEdit.getDataLineAt(6));
    }

    public void setDescription(String description) {
        this.description = description;
        DataEdit.replace(DataEdit.DESCRIPTION_dl, description);
    }
    public void setSizeQuestions(int sizeQuestions) {
        this.sizeQuestions = sizeQuestions;
        DataEdit.replace(DataEdit.TOTAL_QUESTIONS_dl, "Total Questions: " + sizeQuestions);
    }
    public void setPoints(int points) {
        this.points = points;
        DataEdit.replace(DataEdit.CURRENT_PTS_dl, "Current Points: " + points);
    }
    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
        DataEdit.replace(DataEdit.FULLSCREEN_dl, fullscreen ? "Fullscreen: ON" : "Fullscreen: OFF");
    }
    public void setAlready_asked_questions(ArrayList<Integer> already_asked_questions) {
        this.already_asked_questions = already_asked_questions;
        String temp = "Exempted Questions: ";
        for(int i: already_asked_questions){
            temp+= i + " ";
        }
        DataEdit.replace(DataEdit.ALREADY_ASKED_Qs_dl, temp);
    }
    public void setDisable_category(ArrayList<Character> disable_category) {
        this.disable_category = disable_category;
        String temp = "Disabled Categories: ";
        for(char i: disable_category){
            temp+= i + " ";
        }
        DataEdit.replace(DataEdit.DISABLE_CATEGORY_dl, temp);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }
    public int getSizeQuestions() {
        return sizeQuestions;
    }
    public int getPoints() {
        return points;
    }
    public boolean isFullscreen() {
        return fullscreen;
    }
    public ArrayList<Integer> getAlready_asked_questions() {
        return already_asked_questions;
    }
    public ArrayList<Character> getDisable_category() {
        return disable_category;
    }
    public int getLevel() {
        return level;
    }

    public void addPoints(int points) {
        int current_points = DataEdit.stripInt(DataEdit.getDataLineAt(DataEdit.CURRENT_PTS_dl));
        int total = points + current_points;
        setPoints(total);
        DataEdit.replace(DataEdit.CURRENT_PTS_dl, "Current Points: " + getPoints());
    }
    public void nextLevel(){
        int current_level = getLevel();
        setLevel(++current_level);
        DataEdit.replace(DataEdit.LEVEL_dl, "Level: " + getLevel());
    }

    public void addDisabled(char letter){
        String line_raw = DataEdit.getDataLineAt(DataEdit.DISABLE_CATEGORY_dl);
        System.out.println("Raw: " + line_raw);
        String line = line_raw.substring(21);

        ArrayList<Character> current;
        if(getDisable_category() != null){
            current = getDisable_category();
        }else{
            current = new ArrayList<>();
        }
        current.add(letter);
        String temp = "Disabled Categories: ";
        for (char c : current){
            temp += c + " ";
        }
        System.out.println("Line: " + temp);
        DataEdit.replace(DataEdit.DISABLE_CATEGORY_dl, temp);
    }
    public void clearDisabled(){
        DataEdit.replace(DataEdit.DISABLE_CATEGORY_dl, "Disabled Categories: ");
        setDisable_category(new ArrayList<>());
    }
    public void addQuestion(int id){
        String line_raw = DataEdit.getDataLineAt(DataEdit.ALREADY_ASKED_Qs_dl);
        String line = line_raw.substring(20);

        ArrayList<Integer> questions = getAlready_asked_questions();
        questions.add(id+1);
        String temp = "Exempted Questions: ";
        for(int i: questions){
            temp += i + " ";
        }
        DataEdit.replace(DataEdit.ALREADY_ASKED_Qs_dl, temp);
    }
    public void clearQuestions(){
        DataEdit.replace(DataEdit.ALREADY_ASKED_Qs_dl, "Exempted Questions: ");
        setAlready_asked_questions(new ArrayList<>());
    }
    public void askProfessor(int index){
        String raw_line = DataEdit.getDataLineAt(7).substring(22);
        String new_line = "";
        char[] array = raw_line.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if(index == i){
                array[i] = '0';
            }else{

            }
            new_line += array[i];
        }
        DataEdit.replace(DataEdit.PROFESSORS_dl, "Available Professors: " + new_line);
    }
    public boolean isProfAvailable(int index){
        String raw_line = DataEdit.getDataLineAt(7).substring(22);
        if(raw_line.charAt(index) == '1'){
            return true;
        }else if(raw_line.charAt(index) == '0'){
            return  false;
        }
        else{
            System.out.println("ERROR ON IS PROF METHOD. RETURNING FALSE ON DEFAULT");
            return false;
        }
    }
    public static void main(String[] args) {
        DataEdit.resetData();

        LevelFrame LF = new LevelFrame();
        LF.setVisible(true);
    }
}
