package Sort.Algorithms;

/**
 * Double selection sort algorithm.
 */
public class DoubleSelectionSort extends SortAlgorithm {

    private int indexA = 0;
    private int indexB = 0;
    private int minIndex = 0;
    private int maxIndex = 0;

    public DoubleSelectionSort(ArrayData arrayData) {
        super("Double Selection Sort", arrayData);
    }

    IComparer<Integer> comparer = (a, b) -> {
        if(a > b) return 1;
        else return -1;
    };

    @Override
    protected void step() {
        processingIndexes.clear();
        if(indexA < arrayData.array.length - indexA){
            if(indexB < arrayData.array.length - indexA){

                //If min is greater than current, set min current.
                if(comparer.compare(arrayData.array[minIndex], arrayData.array[indexB]) > 0){
                    minIndex = indexB;
                }
                //If max is smaller than current, set max current.
                if(comparer.compare(arrayData.array[maxIndex], arrayData.array[indexB]) < 0){
                    maxIndex = indexB;
                }

                //Add the comparing indexes to list.
                processingIndexes.add(indexB);
                processingIndexes.add(minIndex);
                processingIndexes.add(maxIndex);

                //Add B index.
                indexB++;

            }else{
                //Swap the value of the last with the min.
                int tempMin = arrayData.array[minIndex];
                arrayData.array[minIndex] = arrayData.array[indexA];
                arrayData.array[indexA] = tempMin;

                //Swap the value of the first with the max.
                //If max is the last, then its been swapped to the min.
                if(maxIndex == indexA) maxIndex = minIndex;
                int tempMax = arrayData.array[maxIndex];
                arrayData.array[maxIndex] = arrayData.array[arrayData.array.length - indexA - 1];
                arrayData.array[arrayData.array.length - indexA - 1] = tempMax;

                //Add the comparing indexes to list.
                processingIndexes.add(minIndex);
                processingIndexes.add(maxIndex);
                processingIndexes.add(indexA);
                processingIndexes.add(arrayData.array.length - indexA - 1);

                //Increment A index and reset variables.
                indexA++;
                indexB=indexA;
                minIndex=indexA;
                maxIndex=indexA;
            }
        }else{
            isFinished = true;
        }
    }

    @Override
    public void resetAlgorithm() {
        indexA = 0;
        indexB = 0;
        minIndex = 0;
        maxIndex = 0;
    }
}
