package Sort.Sound;

/**
 * The interface to determine the pitch of a sound with given value.
 * Could create precise music note according to given value.
 */
public interface ISoundScheme{
    /**
     * It takes in a value between 0-1 and output a pitch value for play(pitch) function.
     * @param value The value between 0-1.
     * @return Return a pitch value.
     */
   public float soundRule(float value);
}