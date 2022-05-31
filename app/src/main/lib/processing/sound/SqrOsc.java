package processing.sound;

import com.jsyn.unitgen.SquareOscillator;

import processing.core.PApplet;

/**
 * This is a simple Square Wave Oscillator.
 * @webref oscillators
 * @webBrief This is a simple Square Wave Oscillator.
 **/
public class SqrOsc extends Oscillator<SquareOscillator> {

	/**
	 * @param parent typically use "this"
	 */
	public SqrOsc(PApplet parent) {
		super(parent, new SquareOscillator());
	}

	// Below are just duplicated methods from superclasses which are required
	// for the online reference to build the corresponding pages.

	public void play() {
		super.play();
	}

	public void play(float freq, float amp) {
		super.play(freq, amp);
	}

	public void play(float freq, float amp, float add) {
		super.play(freq, amp, add);
	}

	/**
	 * Starts the oscillator.
	 * @param freq The frequency value of the oscillator in Hz.
	 * @param amp The amplitude of the oscillator as a value between 0.0 and 1.0.
	 * @param add Offset the output of the oscillator by given value
	 * @param pos The panoramic position of the oscillator as a float from -1.0 to 1.0.
	 * @webref sqrosc
	 * @webBrief Starts the oscillator.
	 **/
	public void play(float freq, float amp, float add, float pos) {
		super.play(freq, amp, add, pos);
	}

	/**
	 * Set multiple parameters at once.
	 * @webref sqrosc
	 * @webBrief Set multiple parameters at once.
	 * @param freq The frequency value of the oscillator in Hz.
	 * @param amp The amplitude of the oscillator as a value between 0.0 and 1.0.
	 * @param add Offset the output of the oscillator by given value
	 * @param pos The panoramic position of the oscillator as a float from -1.0 to 1.0.
	 **/
	public void set(float freq, float amp, float add, float pos) {
		super.set(freq, amp, add, pos);
	}
	
	/**
	 * Set the frequency of the oscillator in Hz.
	 * @webref sqrosc
	 * @webBrief Set the frequency of the oscillator in Hz.
	 * @param freq A floating point value of the oscillator in Hz.
	 **/
	public void freq(float freq) {
		super.freq(freq);
	}

	/**
	 * Change the amplitude/volume of of the square oscillator. 
	 * Allowed values are between 0.0 and 1.0.
	 *
	 * @webref sqrosc
	 * @webBrief Change the amplitude/volume of of the square oscillator. 
	 * @param amp
	 *            A float value between 0.0 (complete silence) and 1.0 (full volume)
	 *            controlling the amplitude/volume of this sound.
	 **/
	public void amp(float amp) {
		super.amp(amp);
	}

	/**
	 * Offset the output of this generator by given value.
	 *
	 * @webref sqrosc
	 * @webBrief Offset the output of this generator by given value.
	 * @param add Offset the output of the oscillator by given value
	 **/
	public void add(float add) {
		super.add(add);
	}

	/**
	 * Move the sound in a stereo panorama.
	 *
	 * @webref sqrosc
	 * @webBrief Move the sound in a stereo panorama.
	 * @param pos
	 *            The panoramic position of this sound unit as a float from -1.0
	 *            (left) to 1.0 (right).
	 **/
	public void pan(float pos) {
		super.pan(pos);
	}

	/**
	 * Stop the oscillator.
	 *
	 * @webref sqrosc
	 * @webBrief Stop the oscillator.
	 **/
	public void stop() {
		super.stop();
	}
}
