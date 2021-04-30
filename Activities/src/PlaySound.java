import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlaySound {
    static String click1 = "src/Sounds/click.wav";
    static String click2 = "src/Sounds/click1.mp3";
    static String click3 = "src/Sounds/click2.mp3";
    public static void click_(String path) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                int counter = 0;
                try {

                    System.out.println("started");

                    Clip clip = AudioSystem.getClip();

                    File file = new File(path);

                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);

                    clip.open(inputStream);

                    clip.start();

                    while(clip.isOpen()) {
                        try { Thread.sleep(2000); } catch(InterruptedException ie) {}
                        if(!clip.isActive()) break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    public void clickMedia(){
    }
    public static void main(String[] args) {
        PlaySound.click_(click2);
    }
}