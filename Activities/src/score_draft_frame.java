import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class score_draft_frame extends JFrame{
    JTextArea textArea;
    
    score_draft_frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("HighScore");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(ThemeValues.BG1);
        this.setLayout(null);

        
        textArea = new JTextArea();
        textArea.setBounds(527, 125, 300, 550);
        textArea.setPreferredSize(new Dimension(200,50));
        textArea.setFont(new Font("Consolas", Font.BOLD, 30));
        textArea.setForeground(Color.BLUE);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEtchedBorder());

        this.add(textArea);

        String filename = "src/scores.txt";
        PriorityQueue<PointsObject> PQ = new PriorityQueue<>();
        try (BufferedReader reader = new BufferedReader(new FileReader( filename))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] temp = line.split(" - ");
                PQ.add(new PointsObject(temp[0], Integer.parseInt(temp[1])));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(score_draft_frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(score_draft_frame.class.getName()).log(Level.SEVERE, null, ex);
        }

        while(!PQ.isEmpty()){
            PointsObject holder = PQ.remove();
            System.out.println(holder);
            textArea.append(holder.getName() + " - " + holder.getPoints() + "\n");
        }
        this.setVisible(true);
    }

}
