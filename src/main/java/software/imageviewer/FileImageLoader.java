package software.imageviewer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

public class FileImageLoader implements ImageLoader {
    private final File[] images;

    public FileImageLoader(File folder) {
        this.images = folder.listFiles(isImage());
    }

    private static final Set<String> imageExtensions = Set.of("jpg", "jpeg", "png", "gif");

    private static FilenameFilter isImage() {
        return (dir, name) -> imageExtensions.stream().anyMatch(name::endsWith);
    }

    @Override
    public Image load() {
        return ImageAt(0);
    }

    private Image ImageAt(int index) {
        return new Image() {
            @Override
            public String name() {
                assert images != null;
                return images[index].getAbsolutePath();
            }

            @Override
            public Image nextImage() {
                assert images != null;
                return ImageAt((index + 1)%images.length);
            }

            @Override
            public Image previousImage() {
                assert images != null;
                return ImageAt((index-1+images.length)%images.length);
            }
        };
    }
}
