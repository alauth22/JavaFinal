package Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

   //constructor play sound
   public void playSound(String filePath) {
       try {
           File filePathname = new File(filePath);
           AudioInputStream audiostream = AudioSystem.getAudioInputStream(filePathname);
           Clip clip = AudioSystem.getClip();
           clip.open(audiostream);

           clip.start();

           while (clip.isRunning()) {
               Thread.sleep(100); // Check if clip is still playing
           }

           clip.close();


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
