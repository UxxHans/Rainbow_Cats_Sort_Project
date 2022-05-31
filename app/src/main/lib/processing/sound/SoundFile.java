package processing.sound;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.jsyn.data.FloatSample;
import com.jsyn.util.SampleLoader;

import fr.delthas.javamp3.Sound;
import processing.core.PApplet;

// calls to amp(), pan() etc affect both the LAST initiated and still running sample, AND all subsequently started ones
/**
 * This is a Soundfile player which allows to play back and manipulate sound
 * files. Supported formats are: WAV, AIF/AIFF, and MP3.
 * 
 * MP3 decoding can be very slow on ARM processors (Android/Raspberry Pi), we generally recommend you use lossless WAV or AIF files.
 * @webref sampling
 * @webBrief This is a Soundfile Player which allows to play back and manipulate soundfiles.
 **/
public class SoundFile extends AudioSample {

	private static Map<String, FloatSample> SAMPLECACHE = new HashMap<String, FloatSample>();

	public SoundFile(PApplet parent, String path) {
		this(parent, path, true);
	}

	// the original library only printed an error if the file wasn't found,
	// but then later threw a NullPointerException when trying to play() the file.
	// it might be a better idea to throw an exception to the user in some cases,
	// e.g. when the file can't be found?
	/**
	 * @param parent
	 *            typically use "this"
	 * @param path
	 *            filename of the sound file to be loaded
	 * @param cache
	 *            keep the sound data in RAM once it has been decoded (default: true).
	 *            Note that caching essentially disables garbage collection for the
	 *            SoundFile data, so if you are planning to load a large number of audio
	 *            files, you should set this to false.
	 */
	public SoundFile(PApplet parent, String path, boolean cache) {
		super(parent);

		this.sample = SoundFile.SAMPLECACHE.get(path);

		if (this.sample == null) {
			InputStream fin = parent.createInput(path);

			// if PApplet.createInput() can't find the file or URL, it prints
			// an error message and fin returns null. In this case we can just
			// return this dysfunctional SoundFile object without initialising further
			if (fin == null) {
				Engine.printError("unable to find file " + path);
				return;
			}

			try {
				// load WAV or AIF using JSyn
				this.sample = SampleLoader.loadFloatSample(fin);

				// switching to JavaSound decoders is supposed to support 8bit
				// unsigned WAV files as well, but doesn't actually seem to be
				// the case
				//SampleLoader.setJavaSoundPreferred(true);

			} catch (IOException e) {
				// try parsing as mp3
				try {
					// stream as to be re-created, since it was modified in SampleLoader.loadFloatSample()
					fin = parent.createInput(path);
					Sound mp3 = new Sound(fin);
					try {
						ByteArrayOutputStream os = new ByteArrayOutputStream();
						// TODO make decoding asynchronous with a FutureTask<FloatSample>
						// this call is expensive
						mp3.decodeFullyInto(os);
						float data[] = new float[os.size() / 2];
						SampleLoader.decodeLittleI16ToF32(os.toByteArray(), 0, os.size(), data, 0);
						this.sample = new FloatSample(data, mp3.isStereo() ? 2 : 1);
					} catch (IOException ee) {
						throw ee;
					} catch (NullPointerException ee) {
						throw new IOException();
					} catch (ArrayIndexOutOfBoundsException ee) {
						throw new IOException();
					} finally {
						mp3.close();
					}
				} catch (IOException ee) {
					Engine.printError("unable to decode sound file " + path);
					// return dysfunctional SoundFile object
					return;
				}
			}
			if (cache) {
				SoundFile.SAMPLECACHE.put(path, this.sample);
			}
		}
		this.initiatePlayer();
	}

	/**
	 * Remove this SoundFile's decoded audio sample from the cache, allowing
	 * it to be garbage collected once there are no more references to this
	 * SoundFile.
	 * 
	 * @return true if the sample was removed from the cache, false if it wasn't
	 *.        actually cached in the first place.
	 * @webref soundfile
	 * @webBrief Remove this SoundFile's decoded audio sample from the cache, allowing
	 * it to be garbage collected once there are no more references to this
	 * SoundFile.
	 **/
	public boolean removeFromCache() {
		return SoundFile.SAMPLECACHE.values().remove(this.sample);
	}

	// Below are just duplicated methods from the AudioSample superclass which
	// are required for the reference to build the corresponding pages.

	/**
	 * Returns the number of channels of the soundfile as an int (1 for mono, 2 for stereo).
	 * 
	 * @return Returns the number of channels of the soundfile (1 for mono, 2 for
	 *         stereo)
	 * @webref soundfile
	 * @webBrief Returns the number of channels of the soundfile as an int (1 for mono, 2 for stereo).
	 **/
	public int channels() {
		return super.channels();
	}

	/**
	 * Cues the playhead to a fixed position in the soundfile. Note that <b>cue()</b> only 
	 * affects the playhead for future calls to <b>play()</b>, but not to <b>loop()</b>.
	 * 
	 * @param time
	 *            position in the soundfile that the next playback should start
	 *            from, in seconds.
	 * @webref soundfile
	 * @webBrief Cues the playhead to a fixed position in the soundfile.
	 **/
	public void cue(float time) {
		super.cue(time);
	}

	/**
	 * Returns the duration of the soundfile in seconds.
	 * 
	 * @webref soundfile
	 * @webBrief Returns the duration of the soundfile in seconds.
	 * @return The duration of the soundfile in seconds.
	 **/
	public float duration() {
		return super.duration();
	}

	/**
	 * Returns the number of frames of this soundfile.
	 * 
	 * @webref soundfile
	 * @webBrief Returns the number of frames of this soundfile.
	 * @return The number of frames of this soundfile.
	 **/
	public int frames() {
		return super.frames();
	}

	public void play() {
		super.play();
	}

	public void play(float rate) {
		super.play(rate);
	}

	public void play(float rate, float amp) {
		super.play(rate, amp);
	}

	public void play(float rate, float pos, float amp) {
		super.play(rate, pos, amp);
	}

	public void play(float rate, float pos, float amp, float add) {
		super.play(rate, pos, amp, add);
	}

	/**
	 * Starts the playback of the soundfile. Only plays to the end of the
	 * audiosample once. If <b>cue()</b> or <b>pause()</b> were called previously, playback 
	 * will resume from the cued position.
	 * 
	 * @param rate
	 *            relative playback rate to use. 1 is the original speed. 0.5 is
	 *            half speed and one octave down. 2 is double the speed and one
	 *            octave up.
	 * @param amp
	 *            the desired playback amplitude of the audiosample as a value from
	 *            0.0 (complete silence) to 1.0 (full volume)
	 * @param pos
	 *            the panoramic position of this sound unit from -1.0 (left) to 1.0
	 *            (right). Only works for mono soundfiles!
	 * @param cue
	 *            position in the audiosample that playback should start from, in
	 *            seconds.
	 * @param add
	 *            offset the output of the generator by the given value
	 * @webref soundfile
	 * @webBrief Starts the playback of the soundfile.
	 **/
	public void play(float rate, float pos, float amp, float add, float cue) {
		super.play(rate, pos, amp, add, cue);
	}


	/**
	 * Jump to a specific position in the soundfile while continuing to play 
	 * (or starting to play if it wasn't playing already).
	 * 
	 * @webref soundfile
	 * @webBrief Jump to a specific position in the soundfile while continuing to play (or starting to play if it wasn't playing already).
	 * @param time
	 *            position to jump to, in seconds.
	 **/
	public void jump(float time) {
		super.jump(time);
	}

	/**
	 * Stop the playback of the file, but cue it to the current position. The
	 * next call to <b>play()</b> will continue playing where it left off.
	 * 
	 * @see stop
	 * @webref soundfile
	 * @webBrief Stop the playback of the file, but cue it to the current position.
	 */
	public void pause() {
		super.pause();
	}

	/**
	 * Check whether this soundfile is currently playing.
	 *
	 * @return `true` if the soundfile is currently playing, `false` if it is not.
	 * @webref soundfile
	 * @webBrief Check whether this soundfile is currently playing.
	 */
	public boolean isPlaying() {
		return super.isPlaying();
	}

	public void loop() {
		super.loop();
	}

	public void loop(float rate) {
		super.loop(rate);
	}

	public void loop(float rate, float amp) {
		super.loop(rate, amp);
	}

	public void loop(float rate, float pos, float amp) {
		super.loop(rate, pos, amp);
	}

	/**
	 * Starts playback which will loop at the end of the soundfile.
	 * 
	 * @param rate
	 *            relative playback rate to use. 1 is the original speed. 0.5 is
	 *            half speed and one octave down. 2 is double the speed and one
	 *            octave up.
	 * @param pos
	 *            the panoramic position of this sound unit from -1.0 (left) to 1.0
	 *            (right). Only works for mono soundfiles!
	 * @param amp
	 *            the desired playback amplitude of the audiosample as a value from
	 *            0.0 (complete silence) to 1.0 (full volume)
	 * @param add
	 *            offset the output of the generator by the given value
	 * @webref soundfile
	 * @webBrief Starts playback which will loop at the end of the soundfile.
	 */
	public void loop(float rate, float pos, float amp, float add) {
		super.loop(rate, pos, amp, add);
	}

	/**
	 * Changes the amplitude/volume of the player. Allowed values are between 0.0 and 1.0.
	 * 
	 * @param cue
	 *            position in the audiosample that the next playback or loop should
	 *            start from, in seconds. public void loop(float rate, float pos,
	 *            float amp, float add, float cue) { super.loop(rate, pos, amp, add,
	 *            cue); }
	 */

	/**
	 * Change the amplitude/volume of this audiosample.
	 *
	 * @param amp
	 *            A float value between 0.0 (complete silence) and 1.0 (full volume)
	 *            controlling the amplitude/volume of this sound.
	 * @webref soundfile
	 * @webBrief Changes the amplitude/volume of the player.
	 **/
	public void amp(float amp) {
		super.amp(amp);
	}

	/**
	 * Move the sound in a stereo panorama.-1.0 pans to the left channel and 1.0 to the 
	 * right channel. Note that panning is only supported for mono (1 channel) soundfiles.
	 * 
	 * @param pos
	 *            the panoramic position of this sound unit from -1.0 (left) to 1.0
	 *            (right).
	 * @webref soundfile
	 * @webBrief Move the sound in a stereo panorama.
	 **/
	public void pan(float pos) {
		super.pan(pos);
	}

	/**
	 * Set the playback rate of the soundfile. 1 is the original speed. 0.5 is half speed 
	 * and one octave down. 2 is double the speed and one octave up.
	 * 
	 * @param rate
	 *            Relative playback rate to use. 1 is the original speed. 0.5 is
	 *            half speed and one octave down. 2 is double the speed and one
	 *            octave up.
	 * @webref soundfile
	 * @webBrief Set the playback rate of the soundfile.
	 **/
	public void rate(float rate) {
		super.rate(rate);
	}

	/**
	 * Stops the playback.
	 * 
	 * @see pause
	 * @webref soundfile
	 * @webBrief Stops the playback.
	 **/
	public void stop() {
		super.stop();
	}

}
