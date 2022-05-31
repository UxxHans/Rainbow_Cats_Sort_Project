package Sort.Algorithms;

/**
 * Bubble sort algorithm.
 */
public class BubbleSort extends SortAlgorithm {

    private int indexA = 0;
    private int indexB = 0;

    public BubbleSort(ArrayData arrayData) {
        super("Bubble Sort", arrayData);
    }

    IComparer<Integer> comparer = (a, b) -> {
        if(a > b) return 1;
        else return -1;
    };

    @Override
    protected void step() {
        processingIndexes.clear();
        if(indexA < arrayData.array.length - 1){
            if(indexB < arrayData.array.length - 1 - indexA){

                //If a is greater than b, exchange a & b.
                if(comparer.compare(arrayData.array[indexB], arrayData.array[indexB+1]) > 0){
                    int temp = arrayData.array[indexB];
                    arrayData.array[indexB] = arrayData.array[indexB+1];
                    arrayData.array[indexB+1] = temp;
                }

                //Add the comparing indexes to list.
                processingIndexes.add(indexB);
                processingIndexes.add(indexB+1);

                //Add B index.
                indexB++;

            }else{
                indexA++;
                indexB=0;
            }
        }else{
            isFinished = true;
        }
    }

    @Override
    public void resetAlgorithm() {
        indexA = 0;
        indexB = 0;
    }
}
