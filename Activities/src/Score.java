public class Score {
    public int right = 0; // Tallies the number of questions player was correct
    public int wrong = 0; // Tallies the number of questions player was wrong
    public int totalPoints = 0; // Tallies the number of questions player was correct
    public boolean wrongOnce = false; // Determines if the player was incorrect on the last question
    public int currentPoints =0; // Changes on CategoryFrame by getting points of selected question object
    public double multiplier = 1;

    public Score(){} // Init constructor

    public void reset(){
        right = 0; // Tallies the number of questions player was correct
        wrong = 0; // Tallies the number of questions player was wrong
        totalPoints = 0; // Tallies the number of questions player was correct
        wrongOnce = false; // Determines if the player was incorrect on the last question
        currentPoints =0; // Changes on CategoryFrame by getting points of selected question object
        multiplier = 1;
    }
}
