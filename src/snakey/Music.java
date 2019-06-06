package snakey;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Music {


    private static Clip clip;
    private static Clip clipGameOver;
    private boolean musicState;
    public boolean gameOverState;

    Music() {
        this.musicState = true;
        Mixer mixer = null;
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();


        for (Mixer.Info mixInfo : mixInfos) {
            try {
                mixer = AudioSystem.getMixer(mixInfo);
                DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
                clip = (Clip) mixer.getLine(dataInfo);
                clipGameOver = (Clip) mixer.getLine(dataInfo);
                break;
            } catch (IllegalArgumentException e) {

            } catch (LineUnavailableException e) {

            }
        }

        try {

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("sounds/normal.wav"));
            clip.open(audioStream);

            AudioInputStream audioStreamGameOver = AudioSystem.getAudioInputStream(new File("sounds/normal.wav"));
            clipGameOver.open(audioStream);


        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    void playMusic() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        this.musicState = true;
    }

    void playMusic(int number) {
        if (number != 7698) {
            clipGameOver.start();
            gameOverState = true;
        }
    }


    void stopMusic() {
        this.musicState = false;
        clip.stop();
    }


    boolean getMusicState() {
        return this.musicState;
    }
}
