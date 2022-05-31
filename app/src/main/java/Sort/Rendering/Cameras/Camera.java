package Sort.Rendering.Cameras;

import processing.core.PApplet;

/**
 * Represent a camera object in the visualization.
 */
public abstract class Camera implements IColorScheme, IRenderCamera{
    protected PApplet mainProgram;

    public Camera(PApplet mainProgram) {
        this.mainProgram = mainProgram;
    }
}
