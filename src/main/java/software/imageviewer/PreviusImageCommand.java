package software.imageviewer;

public class PreviusImageCommand implements Command {
    private final ImageDisplay imageDisplay;

    public PreviusImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.show(imageDisplay.image().previousImage());
    }
}
