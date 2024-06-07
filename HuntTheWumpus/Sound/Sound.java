// Amrit Gill
// February 16, 2024
// Period 5
// Hunt the Wumpus - Sound Class

package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Sound {
    ///////////////////////
    // Properties & Fields
    //////////////////////

    ///////////////////////
    // Constructor(s)
    //////////////////////
    public Sound(){
        
    }
    ///////////////////////
    // Methods
    //////////////////////

    // This method plays the background sound of the entire game
    public static void BackgroundSound() {
        try {
            // Provide the path to your .wav file
            String filePath = "HuntTheWumpus\\Sound\\SoundEffects\\background.wav";

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
 
    // This method is the sound for when the player dies
    public void deathSound() {
        try {

            String mp3File = "HuntTheWumpus\\Sound\\SoundEffects\\lose.mp3";

            // Create an AudioInputStream from the mp3 file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(mp3File));

            // Get a Clip object to play the audio
            Clip clip = AudioSystem.getClip();

            // Open the AudioInputStream with the Clip
            clip.open(audioInputStream);

            // Start playing the audio
            clip.start();

            // Wait for the audio to finish playing
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Close the clip and release resources
            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    // This method is the sound for when the player beats the Wumpus
    public void WinSound(){
     try {
            // Provide the path to your .wav file
            String filePath = "HuntTheWumpus\\Sound\\SoundEffects\\win.wav";

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

    // This method is the sound for when the player gets a trvia question wrong.
    public void wrongSound() {
        try {

            String mp3File = "HuntTheWumpus\\Sound\\SoundEffects\\wrong.mp3";

            // Create an AudioInputStream from the mp3 file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(mp3File));

            // Get a Clip object to play the audio
            Clip clip = AudioSystem.getClip();

            // Open the AudioInputStream with the Clip
            clip.open(audioInputStream);

            // Start playing the audio
            clip.start();

            // Wait for the audio to finish playing
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Close the clip and release resources
            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    // This method is the sound for when the player gets a trivia question right.
    public void CorrectSound(){
        try {
            // Provide the path to your .wav file
            String filePath = "HuntTheWumpus\\Sound\\SoundEffects\\correct.wav";

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