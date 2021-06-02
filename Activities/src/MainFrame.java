import javax.swing.*;

public class MainFrame extends JFrame{
    private JPanel mainPanel;
    private JButton startButton;
    private JButton highscoreButton;
    private JButton exitButton;
    private JButton howToPlayButton;
    public JButton continueButton;

    public MainFrame(){
        Utility.__initialization__(this, mainPanel);
        Utility.styleComponents(mainPanel.getComponents());
        startButton.addActionListener(e -> {
            Sounds.click_();
            DataEdit.resetData();
            LevelFrame LF = new LevelFrame();
            LF.setVisible(true);
            this.dispose();
        });
        highscoreButton.addActionListener(e -> {
            Sounds.click_();
            HighFrame HF = new HighFrame();
            HF.setVisible(true);
            this.dispose();
        });
        exitButton.addActionListener(e -> {
            Sounds.click_();
            int process = JOptionPane.showConfirmDialog(null, "Are you sure want to quit?");
            if(process == JOptionPane.YES_OPTION) System.exit(0);
        });
        continueButton.addActionListener(e ->{
            Sounds.click_();
            String[] checker = DataEdit.getDataLineAt(DataEdit.DISABLE_CATEGORY_dl).split(" ");
            if(checker.length >= 2){
                JOptionPane.showMessageDialog(null, "Previous Game is over! Starting new Game");
                DataEdit.resetData();
            }
            LevelFrame LF = new LevelFrame();
            LF.setVisible(true);
            this.dispose();
        });
        howToPlayButton.addActionListener(e -> {
            Sounds.click_();
            HowFrame HF = new HowFrame();
            HF.setVisible(true);
            this.dispose();
        });
    }

}
