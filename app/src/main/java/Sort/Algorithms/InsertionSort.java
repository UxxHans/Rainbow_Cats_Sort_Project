package Sort.Algorithms;

/**
 * Insertion sort algorithm.
 */
public class InsertionSort extends SortAlgorithm {

    private int indexA = 1;
    private int indexB = 1;

    public InsertionSort(ArrayData arrayData) {
        super("Insertion Sort", arrayData);
    }

    IComparer<Integer> comparer = (a, b) -> {
        if(a > b) return 1;
        else return -1;
    };

    @Override
    protected void step() {
        processingIndexes.clear();
        if(indexA < arrayData.array.length){

            if(indexB > 0){
                //Add the comparing indexes to list.
                processingIndexes.add(indexB);
                processingIndexes.add(indexB-1);
            }

            if(indexB > 0 && comparer.compare(arrayData.array[indexB], arrayData.array[indexB-1]) < 0){

                //If a is smaller than b, exchange a & b.
                int temp = arrayData.array[indexB];
                arrayData.array[indexB] = arrayData.array[indexB-1];
                arrayData.array[indexB-1] = temp;

                //Reduce B index.
                indexB--;

            }else{
                indexA++;
                indexB=indexA;
            }
            
        }else{
            isFinished = true;
        }
    }

    @Override
    public void resetAlgorithm() {
        indexA = 1;
        indexB = 1;
    }
}
