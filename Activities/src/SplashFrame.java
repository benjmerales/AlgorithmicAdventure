import javax.swing.*;

public class SplashFrame extends JFrame{
    private JPanel mainPanel;
    private JButton title;

    public SplashFrame(){
        Utility.__init__(this, mainPanel);
        title.setFocusable(false); title.setBorderPainted(false); title.setForeground(ThemeValues.FG2);
        title.addActionListener(e -> {
            PlaySound.click_(PlaySound.click1);
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
