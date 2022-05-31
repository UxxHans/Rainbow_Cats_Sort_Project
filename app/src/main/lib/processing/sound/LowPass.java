package processing.sound;

import com.jsyn.unitgen.FilterLowPass;

import processing.core.PApplet;

/**
 * This is a low pass filter.
 * @webref effects
 * @param parent PApplet: typically use "this"
 **/
public class LowPass extends Effect<FilterLowPass> {

	public LowPass(PApplet parent) {
		super(parent);
	}

	@Override
	protected FilterLowPass newInstance() {
		return new FilterLowPass();
	}

	/**
	 * Set the cut off frequency for the filter.
	 * @webref lowpass
	 * @webBrief Set the cut off frequency for the filter.
	 * @param freq the cutoff frequency in Hertz
	 */
	public void freq(float freq) {
		this.left.frequency.set(freq);
		this.right.frequency.set(freq);
	}

	public void process(SoundObject input, float freq) {
		this.freq(freq);
		this.process(input);
	}
}
