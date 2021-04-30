import javax.swing.*;
import java.awt.*;

public class Utility implements ConstantFrames{
    public static boolean[] didWeAskAlready = new boolean[7];
    public static boolean fullscreenEnable = true;
    public static void __init__(JFrame mainF, JPanel mainP){
        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainF.setContentPane(mainP);
        mainF.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainF.setUndecorated(fullscreenEnable);
        mainF.setTitle("Algorithmic Adventure!");

        mainF.setBackground(ThemeValues.BG1);
        mainP.setBackground(ThemeValues.BG1);

        styleComponents(mainP.getComponents());
    }
    private static void styleComponents(Component[] C){
        for(Component c: C){
            String name = c.getClass().getName();
            c.setFont(ThemeValues.F);
            if(name.contains("JButton")){
                JButton b = (JButton) c;
                b.setFocusable(false);
                b.setFocusPainted(false);
                b.setBorderPainted(false);
                if(b.getIcon() == null){
                    b.setBackground(ThemeValues.BG2);
                    b.setForeground(ThemeValues.FG2);
                }
                else{
                    b.setBackground(ThemeValues.BG1);
                    b.setForeground(ThemeValues.BG3);
                }

            }
            else if(name.contains("JComboBox")){
                JComboBox b = (JComboBox) c;
                b.setForeground(ThemeValues.FG2);
                b.setBackground(ThemeValues.BG2);
                b.setBorder(null);
                b.setFocusable(false);
            }
            else if(name.contains("JLabel")){
                c.setForeground(ThemeValues.FG1);
            }
            else if(name.contains("JPanel")){
                JPanel p= (JPanel) c;
                p.setBackground(ThemeValues.BG3);
                styleComponents(p.getComponents());
            }

        }
    }
    public static void specialChangeButton(JPanel J){
        for(Component C: J.getComponents()){
            if(C.getClass().getName().contains("Button")){
                JButton b = (JButton) C;
                b.setFocusable(false);
                b.setFocusPainted(false);
            }
        }
    }
    public static void resetDWAA(){
        didWeAskAlready = new boolean[7];
    }

}
