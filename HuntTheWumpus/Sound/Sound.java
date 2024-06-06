// Amrit Gill
// February 16, 2024
// Period 5
// Hunt the Wumpus - Sound Class

package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sound {
    ///////////////////////
    // Properties & Fields
    //////////////////////

    ///////////////////////
    // Constructor(s)
    //////////////////////
    public Sound(){
        BackgroundSound();
    }
    ///////////////////////
    // Methods
    //////////////////////

    // This method plays the background sound of the entire game
    public static void BackgroundSound() {
        try {
            File backgroundFile = new File("HuntTheWumpus/Sound/SoundEffects/background.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(backgroundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
 
    // This method is the sound for when the player dies
    public void DeathSound(){
        File death = new File("HuntTheWumpus/Sound/SoundEffects/lose.mp3");
    

        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(death));
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // This method is the sound for when the player beats the Wumpus
    public void WinSound(){
        File win = new File("HuntTheWumpus/Sound/SoundEffects/win.wav");
    

        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(win));
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // This method is the sound for when the player gets a trvia question wrong.
    public void WrongSound(){
        File wrong = new File("HuntTheWumpus/Sound/SoundEffects/wrong.mp3");

        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(wrong));
            clip.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // This method is the sound for when the player gets a trivia question right.
    public void CorrectSound(){
        File correct = new File("HuntTheWumpus/Sound/SoundEffects/correct.wav");

        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(correct));
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}