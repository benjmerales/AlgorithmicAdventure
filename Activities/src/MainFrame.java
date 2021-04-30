import javax.swing.*;

public class MainFrame extends JFrame implements ConstantFrames{
    private JPanel mainPanel;
    private JButton startButton;
    private JButton highscoreButton;
    private JButton exitButton;
    private JButton howToPlayButton;
    public JButton continueButton;

    public MainFrame(){
        Utility.__init__(this, mainPanel);

        startButton.addActionListener(e -> {
            M_inter.runNewGame();
            this.dispose();
        });
        highscoreButton.addActionListener(e -> {
            HighFrame HF = new HighFrame();
            HF.setVisible(true);
            this.dispose();
        });
        exitButton.addActionListener(e -> {
            int process = JOptionPane.showConfirmDialog(null, "Are you sure want to quit?");
            if(process == JOptionPane.YES_OPTION) System.exit(0);
        });
        continueButton.addActionListener(e ->{
            LF_inter.setVisible(true);
            this.dispose();
        });
        howToPlayButton.addActionListener(e -> {
            HowFrame HF = new HowFrame();
            HF.setVisible(true);
            this.dispose();
        });
    }

}
