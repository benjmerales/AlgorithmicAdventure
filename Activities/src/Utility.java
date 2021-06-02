import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public interface Utility {
    static void __initialization__(JFrame frame, JPanel panel){
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Algorithmic Adventure");
        panel.setBackground(ThemeValues.BG2);
        recheckScreen(frame);
        frame.setLocationRelativeTo(null);
    }
    static void recheckScreen(JFrame frame){
        boolean isFullScreen = DataEdit.getDataLineAt(DataEdit.FULLSCREEN_dl).contains("ON");
        if(!isFullScreen){
            frame.pack();
        }else if(isFullScreen){
            frame.setUndecorated(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else{
            System.out.println("ERROR CANT FIND SPECIFIED FILE");
        }
    }

    static void styleComponents(Component[] C){
        for(Component c: C){
            String name = c.getClass().getName();
            c.setFont(ThemeValues.F);
            if(name.contains("JButton")){
                JButton b = (JButton) c;
                b.setFocusPainted(false);
                b.setFocusable(false);
                b.setBorderPainted(false);
                if(b.getIcon() == null){
                    b.setBackground(ThemeValues.BG2);
                    b.setForeground(ThemeValues.FG2);
                }
                else{
                    b.setContentAreaFilled(false);
                    b.setBackground(null);
                    b.setForeground(null);
                    String path = b.getIcon().toString();
                    b.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            super.mouseEntered(e);
                            if(path.contains("start")){
                                b.setIcon(new ImageIcon(getThisResource("start_hover.png")));
                            }
                            if(path.contains("cont")){
                                b.setIcon(new ImageIcon(getThisResource("cont_hover.png")));
                            }
                            if(path.contains("category")){
                                b.setIcon(new ImageIcon(getThisResource("category_hover.png")));
                            }
                            if(path.contains("High")){
                                b.setIcon(new ImageIcon(getThisResource("High_hover.png")));
                            }
                            if(path.contains("category")){
                                b.setIcon(new ImageIcon(getThisResource("category_hover.png")));
                            }
                            if(path.contains("how")){
                                b.setIcon(new ImageIcon(getThisResource("how_hover.png")));
                            }
                            if(path.contains("exit")){
                                b.setIcon(new ImageIcon(getThisResource("exit_hover.png")));
                            }
                            if(path.contains("5050")){
                                b.setIcon(new ImageIcon(getThisResource("5050_hover.png")));
                            }
                            if(path.contains("ask")){
                                b.setIcon(new ImageIcon(getThisResource("ask_hover.png")));
                            }
                            if(path.contains("skip")){
                                b.setIcon(new ImageIcon(getThisResource("skip_hover.png")));
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            super.mouseExited(e);
                            if(path.contains("start")){
                                b.setIcon(new ImageIcon(getThisResource("start.png")));
                            }
                            if(path.contains("cont")){
                                b.setIcon(new ImageIcon(getThisResource("cont.png")));
                            }
                            if(path.contains("category")){
                                b.setIcon(new ImageIcon(getThisResource("category.png")));
                            }
                            if(path.contains("High")){
                                b.setIcon(new ImageIcon(getThisResource("High.png")));
                            }
                            if(path.contains("how")){
                                b.setIcon(new ImageIcon(getThisResource("how.png")));
                            }
                            if(path.contains("exit")){
                                b.setIcon(new ImageIcon(getThisResource("exit.png")));
                            }
                            if(path.contains("5050")){
                                b.setIcon(new ImageIcon(getThisResource("5050.png")));
                            }
                            if(path.contains("ask")){
                                b.setIcon(new ImageIcon(getThisResource("ask.png")));
                            }
                            if(path.contains("skip")){
                                b.setIcon(new ImageIcon(getThisResource("skip.png")));
                            }
                        }
                    });
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

    static URL getThisResource(String str){
        return Utility.class.getResource("Images/" + str);
    }

    static boolean isNull(String str){
        if (str.equals("") || str.equals(" ") || str == null){
            return true;
        }
        else if(!str.equals("") && !str.equals(" ") || str != null){
            return false;
        }
        else{
            System.out.println("ERROR ON ISNULL RETURNING FALSE BREAKER");
            return false;
        }
    }
}
