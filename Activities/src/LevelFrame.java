import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Level Selection calls Category Frame from Mechanism static
public class LevelFrame extends JFrame implements  ConstantFrames{
    private JPanel mainPanel;
    private int img_progress = 1;
    public LevelFrame(){
        Utility.__init__(this, mainPanel);
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utility.button_actionLThread();
                super.mouseClicked(e);
                CF_inter.setVisible(true);
                dispose();
                img_progress++;
    }
});
    }

    public void setImg_progress(int img_progress) {
        this.img_progress = img_progress;
    }
}
