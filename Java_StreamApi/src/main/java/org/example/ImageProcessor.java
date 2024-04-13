package org.example;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageProcessor {
    private Path source;
    private Path destination;
    private List<Path> paths;


    public ImageProcessor(String source, String destination) {
        this.source = Path.of(source);
        this.destination = Path.of(destination);

        try (Stream<Path> stream = Files.list(this.source)) {
            this.paths = stream.collect(Collectors.toList());
        } catch (IOException ignored) {
        }
    }

    //custom thread pool
    //https://www.baeldung.com/java-8-parallel-streams-custom-threadpool
    public void process(int threadsNumber) {
        ExecutorService pool = Executors.newFixedThreadPool(threadsNumber);

        pool.submit( () ->{
                paths.parallelStream()
                        .map( this::getImage )
                        .map( this::transform )
                        .forEach( this::saveImage );
            });

        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Pair<Path, BufferedImage> getImage(Path path) {
        try {
            BufferedImage image = ImageIO.read(path.toFile());
            return Pair.of(path, image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Pair<Path, BufferedImage> transform (Pair<Path, BufferedImage> pair) {
        BufferedImage original = pair.getRight();
        BufferedImage newImage = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {
                int rgb = original.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();
                Color outColor = new Color(red, blue, green);
                int outRgb = outColor.getRGB();
                newImage.setRGB(i, j, outRgb);
            }
        }

        return Pair.of(pair.getLeft(), newImage);
    }

    public void saveImage(Pair<Path, BufferedImage> pair) {
        Path destinationPath = destination.resolve(pair.getLeft());
        try {
            ImageIO.write(pair.getRight(), "png", destinationPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}