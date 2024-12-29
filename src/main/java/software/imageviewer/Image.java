package software.imageviewer;

public interface Image {
    String name();
    Image nextImage();
    Image previousImage();
}
