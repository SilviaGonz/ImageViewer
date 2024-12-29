package software.imageviewer.swing;

import software.imageviewer.Image;
import software.imageviewer.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay  extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage bufferedImage;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bufferedImage = load(image.name());
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (bufferedImage != null){
            ResizerImage resizer = new ResizerImage(new Dimension(this.getWidth(), this.getHeight()));
            Dimension resized = resizer.resize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
            int x = (this.getWidth() - resized.width) / 2;
            int y = (this.getHeight() - resized.height) / 2;
            g.drawImage(bufferedImage, x, y, resized.width, resized.height, null);
        }
    }

    public static class ResizerImage {
        private final Dimension dimension;

        public ResizerImage(Dimension dimension) {
            this.dimension = dimension;
        }

        public Dimension resize(Dimension originalDimension) {
            int originalWidth = originalDimension.width;
            int originalHeight = originalDimension.height;

            int targetWidth = dimension.width;
            int targetHeight = dimension.height;

            double ratio = ratioCalculator(targetWidth, targetHeight, originalWidth, originalHeight);

            int newWidth = (int) (originalWidth * ratio);
            int newHeight = (int) (originalHeight * ratio);

            return new Dimension(newWidth, newHeight);
        }

        private double ratioCalculator(int targetWidth, int targetHeight, int originalWidth, int originalHeight) {
            double widthRatio = (double) targetWidth / originalWidth;
            double heightRatio = (double) targetHeight / originalHeight;
            return Math.min(widthRatio, heightRatio);

        }
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
