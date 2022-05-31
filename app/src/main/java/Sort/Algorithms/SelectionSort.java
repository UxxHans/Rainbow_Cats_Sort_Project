package Sort.Algorithms;

/**
 * Selection sort algorithm.
 */
public class SelectionSort extends SortAlgorithm {

    private int indexA = 0;
    private int indexB = 0;
    private int minIndex = 0;

    public SelectionSort(ArrayData arrayData) {
        super("Selection Sort", arrayData);
    }

    IComparer<Integer> comparer = (a, b) -> {
        if(a > b) return 1;
        else return -1;
    };

    @Override
    protected void step() {
        processingIndexes.clear();
        if(indexA < arrayData.array.length){
            if(indexB < arrayData.array.length){

                //If min is greater than current, set min current.
                if(comparer.compare(arrayData.array[minIndex], arrayData.array[indexB]) > 0){
                    minIndex = indexB;
                }

                //Add the comparing indexes to list.
                processingIndexes.add(indexB);
                processingIndexes.add(minIndex);

                //Add B index.
                indexB++;

            }else{
                //Swap the value of the last with the min.
                int temp = arrayData.array[minIndex];
                arrayData.array[minIndex] = arrayData.array[indexA];
                arrayData.array[indexA] = temp;

                //Add the comparing indexes to list.
                processingIndexes.add(minIndex);
                processingIndexes.add(indexA);

                //Increment A index and reset variables.
                indexA++;
                indexB=indexA;
                minIndex=indexA;
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
    }
}
