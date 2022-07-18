package me.coley.recaf.ui.media;

import java.io.IOException;

/**
 * A common base for audio players sourced from different back-ends.
 *
 * @author Matt Coley
 * @see FxPlayer
 * @see CombinedPlayer
 */
public abstract class AudioPlayer {
	private SpectrumListener listener;

	/**
	 * Play the track.
	 */
	public abstract void play();

	/**
	 * Pause the track.
	 */
	public abstract void pause();

	/**
	 * Stop the track.
	 */
	public abstract void stop();

	/**
	 * Stop the track and clear references.
	 */
	public void reset() {
		stop();
	}

	/**
	 * @return Current spectrum listener.
	 */
	public SpectrumListener getSpectrumListener() {
		return listener;
	}

	/**
	 * @param listener
	 * 		New spectrum listener.
	 */
	public void setSpectrumListener(SpectrumListener listener) {
		this.listener = listener;
	}

	/**
	 * Initialize a new track.
	 *
	 * @param path
	 * 		File path name in the current {@link me.coley.recaf.workspace.Workspace}.
	 *
	 * @throws IOException
	 * 		When the file cannot be loaded for playback.
	 */
	public abstract void load(String path) throws IOException;
}