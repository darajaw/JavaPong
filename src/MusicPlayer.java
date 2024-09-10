/*
 * Class: MusicPlayer
 * Purpose: Plays background music for the game
 */
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

    private Clip clip;
    private boolean isPlaying;

    public MusicPlayer() {
        try {
            // Use class loader to find the resource
            InputStream music = getClass().getClassLoader().getResourceAsStream("assets/pixify.wav");

            // if music not found throw a Run time exception
            if (music == null) {
                throw new RuntimeException("Resource not found: assets/pixify.wav");
            }
            
            AudioInputStream background = AudioSystem.getAudioInputStream(music);
            clip = AudioSystem.getClip();
            clip.open(background);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    /**
     *  Turn backgorund music on and off
     */
    public void toggleBackgroundMusic() {
        
        if (!isPlaying) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else{clip.stop();}

        isPlaying = !isPlaying;
    }

    /**
     *  Triggers collision sound when called
     */
    public void triggerCollisionSound() {
        //TODO add collision sound
    }
}
