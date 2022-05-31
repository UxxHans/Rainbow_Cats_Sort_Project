package processing.sound;

import processing.core.PApplet;

/**
 * This is a White Noise Generator. White Noise has a flat spectrum. 
 * @webref noise
 * @webBrief This is a White Noise Generator.
 **/
public class WhiteNoise extends Noise<com.jsyn.unitgen.WhiteNoise> {

	/**
	 * @param parent typically use "this"	
	 */
	public WhiteNoise(PApplet parent) {
		super(parent, new com.jsyn.unitgen.WhiteNoise());
		this.amplitude = this.noise.amplitude;
	}

	// Below are just duplicated methods from the Noise and SoundObject superclass which
	// are required for the reference to build the corresponding pages.

	public void play() {
		super.play();
	}

	public void play(float amp) {
		super.play(amp);
	}
	
	public void play(float amp, float pos) {
		super.play(amp, pos);
	}

	/**
	 * Start the generator.
	 * @param amp the amplitude of the noise as a value from 0.0 (complete silence) to 1.0 (full volume)
	 * @param add offset the output of the noise by given value
	 * @param pos pan the generator in stereo panorama. Allowed values are between -1.0 and 1.0.
	 * @webref whitenoise
	 * @webBrief Start the generator.
	 **/
	public void play(float amp, float add, float pos) {
		super.play(amp, add, pos);
	}

	/**
	 * Set multiple parameters at once.
	 * @param amp the amplitude of the noise as a value from 0.0 (complete silence) to 1.0 (full volume)
	 * @param add offset the output of the noise by given value
	 * @param pos pan the generator in stereo panorama. Allowed values are between -1.0 and 1.0.
	 * @webref whitenoise
	 * @webBrief Set multiple parameters at once.
	 **/
	public void set(float amp, float add, float pos) {
		super.set(amp, add, pos);
	}

	/**
	 * Change the amplitude/volume of this sound. Allowed values are between 0.0 and 1.0.
	 * @param amp the amplitude of the noise as a value from 0.0 (complete silence) to 1.0 (full volume)
	 * @webref whitenoise
	 * @webBrief Change the amplitude/volume of this sound.
	 **/
	public void amp(float amp) {
		// the JSyn Brownian noise generator can drift to exceed one, so tone down the volume a bit
		super.amp(amp / 4);
	}

	/**
	 * Offset the output of this generator by a fixed value.
	 * @param add offset the output of the generator by the given value
	 * @webref whitenoise
	 * @webBrief Offset the output of this generator by a fixed value.
	 **/
	public void add(float add) {
		super.add(add);
	}

	/**
	 * Move the sound in a stereo panorama. -1.0 pans to the left channel and 1.0 to the right channel.
	 * @param pos
	 *            the panoramic position of this sound unit from -1.0 (left) to 1.0
	 *            (right).
	 * @webref whitenoise
	 * @webBrief Move the sound in a stereo panorama.
	 **/
	public void pan(float pos) {
		super.pan(pos);
	}

	/**
	 * Stop the generator.
	 * @webref whitenoise
	 * @webBrief Stop the generator.
	 **/
	public void stop() {
		super.stop();
	}
}
