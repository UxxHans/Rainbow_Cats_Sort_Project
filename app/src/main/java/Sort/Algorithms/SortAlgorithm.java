package Sort.Algorithms;

import java.util.ArrayList;

import Sort.GlobalSettings;

/**
 * An abstract class for sort algorithm.
 */
public abstract class SortAlgorithm {
    protected String name;
    protected ArrayData arrayData;                      //The array of the current data.
    protected ArrayList<Integer> processingIndexes;     //The list of all the indexes that is processing.
    protected boolean isFinished;                       //The boolean to indicate the progress.

    private int totalIterations = 0;                    //Total iterations since sort started.
    private int currentFrame = 0;                       //Current frame counter.

    public SortAlgorithm(String name, ArrayData arrayData) {
        this.name = name;
        this.arrayData = arrayData;
        this.isFinished = true;
        this.processingIndexes = new ArrayList<Integer>();
    }

    /**
     * Get total frames since sort started.
     * @return Total frames since sort started.
     */
    public int getIterations() {
        return totalIterations;
    }

    /**
     * Get processing indexes in the array.
     * @return Current processing index.
     */
    public ArrayList<Integer> getProcessingIndexes() {
        return processingIndexes;
    }

    /**
     * Abstract method that represent procedure to next step in the algorithm.
     */
    protected abstract void step();

    /**
     * Abstract method that represent resetting the algorithm.
     */
    protected abstract void resetAlgorithm();

    /**
     * The sort logic per frame.
     */
    public void tick(){
        if(isFinished) return;
        if(currentFrame >= GlobalSettings.FRAMES_PER_TICK){
            for(int i = 0; i <  GlobalSettings.STEPS_PER_TICK; i++){
                step();
                totalIterations++;
            }
            currentFrame = 0;
        }
        currentFrame++;
    }

    /**
     * Get the name of the algorithm.
     */
    public String getName() {
        return name;
    }

    /**
     * Start over the algorithm.
     */
    public void startOver(){
        resetAlgorithm();
        resetCounters();
        isFinished = false;
    }

    /**
     * Stop the algorithm.
     */
    public void stop(){
        resetAlgorithm();
        resetCounters();
        isFinished = true;
    }

    /**
     * Is the algorithm playing?
     * @return Return true if the algorithm is playing.
     */
    public boolean isPlaying(){
        return !isFinished;
    }

    /**
     * Reset all the counter variables.
     */
    private void resetCounters(){
        processingIndexes.clear();
        totalIterations = 0;
        currentFrame = 0;
    }
}
