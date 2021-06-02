public class QuestionObject {
    public int id;
    public String category;
    public String question;
    public String[] choices;
    public char correct;
    public int points;
    public boolean q_img;
    public boolean c_img;
    // ID   |   Cat     |   Q   |   A   |   B   |   C   |   D   |   A   |   q_0   | c_0_a | c_0_b | c_0_c | c_0_d
    public QuestionObject(String[] compiled){
        int size = compiled.length;
        this.id = Integer.parseInt(compiled[0]);
        this.category = compiled[1];
        this.question = compiled[2];
        this.choices = new String[] {compiled[3], compiled[4], compiled[5], compiled[6]};
        this.correct = compiled[7].charAt(0);
        this.points = Integer.parseInt(compiled[8]);

        boolean B;
        B = compiled[9].equals("T");
        if(B) q_img = true; else q_img = false;
        B = compiled[10].equals("T");
        if(B) c_img = true; else c_img = false;

    }
    public QuestionObject(int i, String cat, String q, String[] choices, char correct, int pts){
        this.id = i;
        this.category = cat;
        this.question = q;
        this.choices = choices;
        this.correct = correct;
        this.points = pts;
    }
    public String toString(){
        String temp = "";
        temp += "ID: " + id + "\n";
        temp += "Category: " + category + "\n";
        temp += "\tQuestion: " + question + "\n";
        temp += "\t\tA.) : " + choices[0] + "\n";
        temp += "\t\tB.) : " + choices[1] + "\n";
        temp += "\t\tC.) : " + choices[2] + "\n";
        temp += "\t\tD.) : " + choices[3] + "\n";
        temp += "\tCorrect Answer: " + correct + "\n";
        temp += "\tScore: " + points + "pts \n";

        return temp;
    }
}
