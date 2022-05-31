package Sort.Algorithms;

import java.util.ArrayList;

import Sort.GlobalSettings;

/**
 * This is a class that stores the value sorting. It can also shuffle the values.
 */
public class ArrayData {
    public int[] array;                         //The primary array data.
    
    private ArrayList<Integer> allData;         //The list of all data sorted.
    private int currentShuffleIndex = 0;        //Index of current shuffling array index.
    private boolean isShuffleStarted = false;   //If the shuffling is started.

    public ArrayData(int length) {
        this.array = new int[length];
        this.allData = new ArrayList<Integer>();
        startShuffle();
    }

    /**
     * Start the shuffle.
     */
    public void startShuffle(){
        createAllData();
        currentShuffleIndex = 0;
        isShuffleStarted=true; 
    }

    /**
     * Stop the array from shuffling and reset all for next shuffle.
     */
    public void stopShuffle(){
        currentShuffleIndex = 0;
        isShuffleStarted=false; 
    }

    /**
     * Is the array data shuffling?
     * @return Return true is the array is shuffling.
     */
    public boolean isShuffling(){
        return isShuffleStarted; 
    }

    /**
     * Return the current shuffling number.
     * @return Current shuffling number.
     */
    public int getCurrentShuffleNumber(){
        return currentShuffleIndex < array.length ? array[currentShuffleIndex] : 0;
    }

    /**
     * Pick a random number from the all data list and put it in the array.
     */
    private void shuffle(){
        array[currentShuffleIndex] = pickRandomNumber();
        currentShuffleIndex++; 
    }

    /**
     * Create a random array.
     */
    private void createAllData(){
        allData.clear();
        for (int i = 0; i<array.length; i++) {
            allData.add(i);
        }
    }

    /**
     * Shuffle if started.
     */
    public void tick(){
        final int SHUFFLE_DELAY = 50;
        for(int i = 0; i<GlobalSettings.ARRAY_DATA_SIZE / SHUFFLE_DELAY; i++){
            if(isShuffleStarted){
                if(currentShuffleIndex < array.length){
                    shuffle();
                }else{
                    stopShuffle();
                }
            }
        }
    }

    /**
     * Pick a random number from allData list and remove it.
     * @return A random number from the list.
     */
    private int pickRandomNumber(){
        int randomIndex = (int)(Math.random() * allData.size());
        int randomNumber = allData.get(randomIndex);
        allData.remove(randomIndex);
        return randomNumber;
    }
}
