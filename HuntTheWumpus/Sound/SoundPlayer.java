import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class SoundPlayer {
    public static void SoundPlayer(String[] args) {

    }

    public void background(){
        try {
            // Provide the path to your .wav file
            String filePath = "HuntTheWumpus\Sound\SoundEffects\background.wav";

            // Create an AudioInputStream from the file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get a Clip object to play the audio
            Clip clip = AudioSystem.getClip();

            // Open the AudioInputStream with the Clip
            clip.open(audioInputStream);

            // Start playing the audio
            clip.start();

            // Add a delay to let the sound play
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Close the resources
            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void correct(){
        try {
            // Provide the path to your .wav file
            String filePath = "HuntTheWumpus\Sound\SoundEffects\correct.wav";

            // Create an AudioInputStream from the file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get a Clip object to play the audio
            Clip clip = AudioSystem.getClip();

            // Open the AudioInputStream with the Clip
            clip.open(audioInputStream);

            // Start playing the audio
            clip.start();

            // Add a delay to let the sound play
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Close the resources
            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lose(){
        try {
            // Provide the path to your MP3 file
            String filePath = "HuntTheWumpus\Sound\SoundEffects\lose.mp3";

            // Create a FileInputStream for the MP3 file
            FileInputStream fileInputStream = new FileInputStream(filePath);

            // Create a new Player instance
            Player player = new Player(fileInputStream);

            // Start playing the MP3
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void win(){
        try {
            // Provide the path to your .wav file
            String filePath = "HuntTheWumpus\Sound\SoundEffects\win.wav";

            // Create an AudioInputStream from the file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get a Clip object to play the audio
            Clip clip = AudioSystem.getClip();

            // Open the AudioInputStream with the Clip
            clip.open(audioInputStream);

            // Start playing the audio
            clip.start();

            // Add a delay to let the sound play
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Close the resources
            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void wrong(){
        try {
            // Provide the path to your MP3 file
            String filePath = "HuntTheWumpus\Sound\SoundEffects\wrong.mp3";

            // Create a FileInputStream for the MP3 file
            FileInputStream fileInputStream = new FileInputStream(filePath);

            // Create a new Player instance
            Player player = new Player(fileInputStream);

            // Start playing the MP3
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}