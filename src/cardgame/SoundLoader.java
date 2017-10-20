package cardgame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

class SoundLoader extends Thread {
    private SoundList soundList;
    private String    filename;


    public SoundLoader(SoundList currentList, String newFilename) {
        soundList = currentList;
        filename  = newFilename;
        setPriority(MAX_PRIORITY);
        start();
    }

    public void run() { 
      try {
          String tempKey = filename;
        AudioClip audioClip = Applet.newAudioClip(new File(filename).toURI().toURL());
        soundList.putClip(tempKey, audioClip);
      } catch (Exception e) {
        System.err.println(e.toString());
      }
    }
    
}