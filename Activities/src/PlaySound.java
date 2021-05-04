import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlaySound {
    static String click2 = "Sounds/click1.wav";
    static String bgm_splash = "Sounds/splash_bgm.wav";
    static String bgm_main = "Sounds/main_bgm.wav";

    static String win_sfc = "Sounds/correct.wav";
    static String wro_sfc = "Sounds/wrong.wav";

    public static void play(String path, boolean loop) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                int counter = 0;
                try {

                    System.out.println("started");

                    Clip clip = AudioSystem.getClip();

                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(PlaySound.class.getResourceAsStream(path));

                    clip.open(inputStream);

                    if (loop) {
                        clip.loop(99);
                    } else {
                        clip.start();
                    }

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

    public static void main(String[] args) {
        play(click2, false);
    }
}