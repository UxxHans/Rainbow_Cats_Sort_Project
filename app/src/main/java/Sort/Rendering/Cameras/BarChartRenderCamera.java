package Sort.Rendering.Cameras;

import Sort.GlobalSettings;
import Sort.GeneralClasses.Color;
import processing.core.PApplet;
import java.util.ArrayList;

/**
 * The camera that renders the array data as bar chart.
 */
public class BarChartRenderCamera extends Camera{

    public BarChartRenderCamera(PApplet mainProgram) {
        super(mainProgram);
    }
    
    @Override
    public Color colorRule(float value) {
        final int MAXIMUM = 255;
        return new Color((int)(MAXIMUM * value), 0, (int)(MAXIMUM * value / 2));
    }

    @Override
    public void render(int[] array, ArrayList<Integer> processingIndexes) {
        final Color PROCESSING = new Color(255, 255, 255);
        float pillarWidth = (float)GlobalSettings.CANVAS_WIDTH / (float)array.length;
        float heightUnit = (float)(GlobalSettings.CANVAS_HEIGHT - GlobalSettings.TOP_RESERVE) / (float)array.length;

        for(int i = 0; i<array.length; i++){
            Color color = colorRule((float)array[i] / (float)array.length);
            mainProgram.fill(color.getR(), color.getG(), color.getB());
            mainProgram.rect(pillarWidth * i, GlobalSettings.CANVAS_HEIGHT, pillarWidth, -heightUnit * (array[i] + 1));
        }
        
        for(int t : processingIndexes){
            mainProgram.fill(PROCESSING.getR(), PROCESSING.getG(), PROCESSING.getB());
            mainProgram.rect(pillarWidth * t, GlobalSettings.CANVAS_HEIGHT, pillarWidth, -heightUnit * (array[t] + 1));
        }
    }
}
