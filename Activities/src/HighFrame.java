import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HighFrame extends JFrame{
    private JPanel mainPanel;
    private JButton returnButton;
    private JTextArea scoresArea;

    public HighFrame(){
        URL filename = HighFrame.class.getResource("scores.txt");
        Utility.__initialization__(this, mainPanel);
        scoresArea.setBounds(527, 125, 800, 550);
        scoresArea.setPreferredSize(new Dimension(200,50));
        PriorityQueue<PointsObject> PQ = new PriorityQueue<>();
        String file = "C:\\Users\\Lenovo.DESKTOP-EAORNA8\\Documents\\00 Current 2nd Year - Second Semester (2021)\\CMSC 123\\Activities\\src\\resources\\scores.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] temp = line.split(" - ");
                PQ.add(new PointsObject(temp[0], Integer.parseInt(temp[1])));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HighFrame.class.getName()).log(Level.SEVERE, null, ex);
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
                MainFrame MF = new MainFrame();
                MF.setVisible(true);
                dispose();
            }
        });
        this.setVisible(true);
    }
}
