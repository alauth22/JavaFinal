package Model.Music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public int playSoundDuration = 0;


    public void setSoundTime(int seconds)
    {
        if(seconds < 0)
        {
            throw new IllegalArgumentException("You must use a positive integer value for the duration " +
                    "that the music plays!");
        }
        else
        {
            this.playSoundDuration = seconds;
        }

    }

   //constructor play sound
   public void start(String filePath) {
       try {
           File filePathname = new File(filePath);
           AudioInputStream audiostream = AudioSystem.getAudioInputStream(filePathname);
           Clip clip = AudioSystem.getClip();
           clip.open(audiostream);

           clip.start();

           if (playSoundDuration > 0) {
               Thread.sleep(playSoundDuration * 1000L); // Sleep for the specified duration
               clip.stop(); // Stop playback after the duration
           } else {
               while (clip.isRunning()) {
                   Thread.sleep(100); // Check if clip is still playing
               }
           }


       } catch (UnsupportedAudioFileException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (LineUnavailableException e) {
           throw new RuntimeException(e);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }

   }


}
