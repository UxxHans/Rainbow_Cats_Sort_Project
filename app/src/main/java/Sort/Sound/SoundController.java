package Sort.Sound;

import processing.core.PApplet;
import processing.sound.*;

/**
 * This is a class representing a sound controller
 */
public class SoundController{
    private int soundSchemeIndex;
    private AudioSample sample;
    private Reverb reverb;
    
    /**
     * Some options of how the sound is pitched according to the value.
     */
    private final static ISoundScheme[] SOUND_SCHEMES = {
        (a) -> 1 + a * 10,  //High tone.
        (a) -> 1 + a * 5,   //Mid tone.
        (a) -> 1 + a * 1,   //Low tone.
    };

    /**
     * Create a sound controller that can play sound.
     * @param soundSchemeIndex Set the tone.
     * @param mainProgram The main program that makes sound.
     */
    public SoundController(int soundSchemeIndex, PApplet mainProgram) {
        this.soundSchemeIndex = soundSchemeIndex > SOUND_SCHEMES.length ? 0 : soundSchemeIndex;
        createSample(mainProgram);
    }

    /**
     * Create an array and manually write a single sine wave oscillation into it.
     */
    public void createSample(PApplet mainProgram){
        final int RESOLUTION = 1000;
        final int LENGTH = 200;
        final int FREQ = 2;

        float[] sinewave = new float[RESOLUTION];
        for (int i = 0; i < RESOLUTION; i++) {
            sinewave[i] = (float) Math.sin(FREQ * Math.PI * i / RESOLUTION);
        }
        sample = new AudioSample(mainProgram, sinewave, LENGTH * RESOLUTION);

        //Play the sample (but don't make it too loud).
        sample.amp(0.8f);

        //Create a reverb effect.
        reverb = new Reverb(mainProgram);
        reverb.process(sample);
    }

    /**
     * Called to make a sound.
     * @param value The 0 - 1 value for the pitch.
     */
    public void tick(float value){
        //Stop the sample to avoid overlap.
        sample.stop();

        //Play the sample.
        sample.play(SOUND_SCHEMES[soundSchemeIndex].soundRule(value));
    }
}
