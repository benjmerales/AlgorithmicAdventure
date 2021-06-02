import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public interface Sounds {
        String click = "Sounds/click1.wav";
        String bgm_splash = "Sounds/splash_bgm.wav";
        String bgm_main = "Sounds/main_bgm.wav";

        String win_sfc = "Sounds/correct.wav";
        String wro_sfc = "Sounds/wrong.wav";

        public static void play(String path, boolean loop) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    int counter = 0;
                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sounds.class.getResourceAsStream(path));
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
    public static void click_() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                int counter = 0;
                try {
                    Clip clip = AudioSystem.getClip();

                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sounds.class.getResourceAsStream(click));

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
    }
