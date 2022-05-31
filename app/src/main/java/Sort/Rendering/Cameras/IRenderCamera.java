package Sort.Rendering.Cameras;

import java.util.ArrayList;

/**
 * The interface for a render method.
 */
public interface IRenderCamera{
    /**
     * It takes in an array and draw the array using main program.
     * @param array The current array during sorting.
     * @param processingIndexes The indexes that is processing.
     */
    public void render(int[] array, ArrayList<Integer> processingIndexes);
}