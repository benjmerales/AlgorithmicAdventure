import javax.swing.*;

public class SplashFrame extends JFrame implements ThemeValues, Utility{
    private JPanel mainPanel;
    private JButton title;

    public SplashFrame(){
        Utility.__initialization__(this, mainPanel);
        Utility.styleComponents(mainPanel.getComponents());
        Sounds.play(Sounds.bgm_splash, true);
        title.addActionListener(e -> {
            Sounds.click_();
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
