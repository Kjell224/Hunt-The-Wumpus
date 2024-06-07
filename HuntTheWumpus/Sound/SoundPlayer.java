import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
    public static void main(String[] args) {
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
}