import javax.swing.*;

public class SplashFrame extends JFrame{
    private JPanel mainPanel;
    private JButton title;

    public SplashFrame(){
        Utility.__init__(this, mainPanel);
        PlaySound.play(PlaySound.bgm_main, true);
        title.setFocusable(false); title.setBorderPainted(false); title.setForeground(ThemeValues.FG2);
        title.addActionListener(e -> {
            Utility.button_actionLThread();
            mainPanel.setVisible(false);
            MainFrame MM = new MainFrame();
           MM.setVisible(true);
           this.dispose();
        });
    }

    public static void main(String[] args) {
        SplashFrame I = new SplashFrame();
        I.setVisible(true);
    }
}
