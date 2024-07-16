package Game_Crossing_Road.Misc;

import javax.sound.sampled.*;
import java.io.File;

public abstract class Sounds {
    private static Clip clip;
    private static boolean stopLoop = false;

    public static void gotLost() {
        loadSound("res/Sound/gotLost.wav", 10);
    }

    public static void buttonPressed(){
        loadSound("res/Sound/buttonPressed.wav");
    }

    public static void intro() {
        stopLoop();
        clip = loopSound("res/Sound/Intro.wav", -7.0f);
    }

    public static void inGameSound() {
        stopLoop();
        clip = loopSound("res/Sound/inGameMusic.wav", -15.0f);
    }

    public static void countDown(){
        loadSound("res/Sound/countDown.wav" , 19);
    }

    public static void gotHit(){
        loadSound("res/Sound/gotHit.wav");
    }

    public static void jumping(){
        loadSound("res/Sound/jumping.wav");
    }

    public static void loadSound(String path, int start) {
        try {
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip singleClip = AudioSystem.getClip();
            singleClip.open(audioStream);
            singleClip.setMicrosecondPosition(start);
            singleClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Clip loopSound(String path){
        return loopSound(path,0);
    }
    private static void loadSound(String path){
        loadSound(path , 0);
    }
    private static Clip loopSound(String path, float volume) {
        try {
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip loopClip = AudioSystem.getClip();
            loopClip.open(audioStream);
            setVolume(loopClip, volume);
            loopClip.loop(Clip.LOOP_CONTINUOUSLY);
            loopClip.start();

            new Thread(() -> {
                while (!stopLoop) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                loopClip.stop();
                loopClip.close();
            }).start();

            return loopClip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void stopLoop() {
        stopLoop = true;
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            clip = null;
        }
        stopLoop = false;
    }

    private static void setVolume(Clip clip, float volume) {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);
        }
    }
}
