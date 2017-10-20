package cardgame;

import java.applet.AudioClip;
import java.util.Hashtable;

class SoundList extends Hashtable {

    private boolean isPlaying;
    private AudioClip tempClip;
    public String currentlyPlaying = new String();

    public SoundList( int soundlist_len, String []sound_list) {
        super(soundlist_len); //Initialize Hashtable with capacity of 5 entries.
        startLoading(sound_list);
        isPlaying = false;
    }

    public void startLoading(String filename[]) {
        for (int i = 0; i < filename.length; i++) {
            new SoundLoader(this, filename[i]);
        }
    }

    public AudioClip getClip(String name) {
        return (AudioClip) get(name);
    }

    public void putClip(String name, AudioClip clip) {
        put(name, clip);
    }

    public void playSound(String key) {
        tempClip = getClip(key);
        if ( tempClip != null && this.isPlaying ) { /* something currently playing, stop it first */
            stopSound(this.currentlyPlaying);
            playSound(key); /* RECURSIOMUNDO */
        } else {    
            tempClip.play();
            this.currentlyPlaying = key;
            this.isPlaying = true;
        }
    }
    
    public void miscSound(String key){
        AudioClip t = getClip(key);
        t.play();
    }
    
    public void stopSound(String key){
        tempClip = getClip(key);
        if (this.isPlaying ) {
            tempClip.stop();
            this.currentlyPlaying = new String();
            this.isPlaying = false;
        }
    }
}
