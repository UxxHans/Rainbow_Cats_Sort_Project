package Sort.Rendering.Cameras;

import Sort.GeneralClasses.Color;

/**
 * The interface for art approach.
 */
public interface IColorScheme {
    /**
     * Set the rule of how color reacts to the value within the maximum.
     * @param value The value between 0-1.
     * @return Return the color correspond to the value.
     */
    public Color colorRule(float value);
}
