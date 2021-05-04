import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HighFrame extends JFrame{
    private JPanel mainPanel;
    private JButton returnButton;
    private JTextArea scoresArea;

    public HighFrame(){
        String filename = "src/scores.txt";
        Utility.__init__(this, mainPanel);

        scoresArea.setBounds(527, 125, 300, 550);
        scoresArea.setPreferredSize(new Dimension(200,50));
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
            scoresArea.append(holder.getName() + " - " + holder.getPoints() + "\n");
        }

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Utility.button_actionLThread();
                MainFrame MF = new MainFrame();
                MF.setVisible(true);
            }
        });
        this.setVisible(true);
    }
}
