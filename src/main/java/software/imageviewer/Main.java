package software.imageviewer;

import software.imageviewer.swing.SwingMainFrame;

import java.io.File;

public class Main {
    public static String rootImages = "C:/Users/silvi/Desktop/UNIVERSIDAD/4º año/primer semestre/IS2/ImagenesImageViewer";
    public static void main(String[] args) {
        SwingMainFrame frame = new SwingMainFrame();
        Image image = new FileImageLoader(new File(rootImages)).load();
        frame.imageDisplay().show(image);
        frame.add("<", new PreviusImageCommand(frame.imageDisplay()));
        frame.add(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);
    }
}
