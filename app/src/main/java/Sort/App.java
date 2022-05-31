package Sort;

import Sort.Algorithms.ArrayData;
import Sort.Algorithms.BubbleSort;
import Sort.Algorithms.DoubleSelectionSort;
import Sort.Algorithms.InsertionSort;
import Sort.Algorithms.SelectionSort;
import Sort.Algorithms.SortAlgorithm;
import Sort.GeneralClasses.Color;
import Sort.GeneralClasses.TextObject;
import Sort.Rendering.Cameras.BarChartRenderCamera;
import Sort.Rendering.Cameras.Camera;
import Sort.Sound.SoundController;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * The main program of the project.
 */
public class App extends PApplet {

    //Main camera and sound controller.
    public Camera camera;
    public SoundController soundEffect;

    //Text object.
    public PFont font;
    public TextObject titleText;
    public TextObject sortAlgorithmText;
    public TextObject iterationsText;
    public TextObject instructionText;

    //Algorithms and array data.
    public ArrayData arrayData;
    public SortAlgorithm[] algorithms;
    public SortAlgorithm currentAlgorithm;
    public int currentAlgorithmIndex;

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(GlobalSettings.CANVAS_WIDTH, GlobalSettings.CANVAS_HEIGHT);
    }

    /**
     * Load all resources. Initialise the elements.
    */
    public void setup() {
        //Set frame rate of the game.
        frameRate(GlobalSettings.FRAME_RATE);

        //No border on the geometry.
        noStroke();

        //Set renderer and sound.
        this.camera = new BarChartRenderCamera(this);
        this.soundEffect = new SoundController(1, this);

        //Set algorithms.
        this.arrayData = new ArrayData(GlobalSettings.ARRAY_DATA_SIZE);
        this.algorithms = new SortAlgorithm[] {
            new BubbleSort(arrayData),
            new InsertionSort(arrayData),
            new SelectionSort(arrayData),
            new DoubleSelectionSort(arrayData)
        };
        this.currentAlgorithmIndex = 0;
        this.currentAlgorithm = algorithms[currentAlgorithmIndex];

        //Load font.
        final int paddings = 30;
        this.font = createFont(this.getClass().getResource("Quicksand-Light.ttf").getPath(), 128, true);
        this.titleText = new TextObject(paddings, paddings, 15, "PROJECT SORT", Color.WHITE, font);
        this.sortAlgorithmText = new TextObject(paddings, paddings * 2, 15, "Current Sort Algorithm: " + currentAlgorithm.getName(), Color.WHITE, font);
        this.iterationsText = new TextObject(paddings, paddings * 3, 15, "Total Iterations: NaN ", Color.WHITE, font);
        this.instructionText = new TextObject(paddings, paddings * 4, 15, "Space - Shuffle | Enter - Start Over | Left & Right - Switch Algorithm", Color.WHITE, font);
    }

    public void nextAlgorithm(){
        currentAlgorithmIndex = currentAlgorithmIndex + 1 >= algorithms.length ? 0 : currentAlgorithmIndex + 1;
        setAlgorithm();
    }

    public void previousAlgorithm(){
        currentAlgorithmIndex = currentAlgorithmIndex - 1 < 0 ? algorithms.length - 1 : currentAlgorithmIndex - 1;
        setAlgorithm();
    }

    public void setAlgorithm(){
        this.currentAlgorithm.stop();
        this.currentAlgorithm = algorithms[currentAlgorithmIndex];
        this.sortAlgorithmText.setText("Current Sort Algorithm: " + currentAlgorithm.getName());
    }

    public void startAlgorithm(){
        this.currentAlgorithm.startOver();
    }

    public void stopAlgorithm(){
        this.currentAlgorithm.stop();
    }

    public void startShuffle(){
        stopAlgorithm();
        this.arrayData.startShuffle();
    }

    /**
     * Change iterations max using keyboard.
     */
    public void keyPressed(){
        final int LEFT = 37;
        final int RIGHT = 39;
        final int SPACE = 32;
        final int ENTER = 10;
        switch(this.keyCode){
            case LEFT:
                previousAlgorithm();
                break;
            case RIGHT:
                nextAlgorithm();
                break;
            case SPACE:
                startShuffle();
                break;
            case ENTER:
                startAlgorithm();
                break;
        }
    }

    /**
     * Draw all elements by current frame.
    */
    public void draw() {
        background(20);

        if(!arrayData.isShuffling()){
            this.currentAlgorithm.tick();
            if(currentAlgorithm.getProcessingIndexes().size() > 0){
                int index = currentAlgorithm.getProcessingIndexes().get(0);
                float pitchValue = (float)arrayData.array[index]/(float)arrayData.array.length;
                soundEffect.tick(pitchValue);
            }
            iterationsText.setText("Total Iterations: " + currentAlgorithm.getIterations() + (currentAlgorithm.isPlaying() ? " - Processing" : " - Stopped"));
        }else{
            arrayData.tick();
            float pitchValue = (float)arrayData.getCurrentShuffleNumber()/(float)arrayData.array.length;
            soundEffect.tick(pitchValue);
        }
    
        //Draw the graph.
        camera.render(arrayData.array, currentAlgorithm.getProcessingIndexes());
        this.titleText.draw(this);
        this.sortAlgorithmText.draw(this);
        this.iterationsText.draw(this);
        this.instructionText.draw(this);
    }

    public static void main(String[] args) {
        PApplet.main("Sort.App");
    }

    /**
     * Print the array.
     * @param array The int array.
     */
    public static void printArray(int[] array){
        System.out.printf("{");
        for(int i = 0; i < array.length; i++){
            System.out.printf("%d", array[i]);
            if (i != array.length - 1)  System.out.printf(",");
        }
        System.out.printf("}%n");
    }
}
