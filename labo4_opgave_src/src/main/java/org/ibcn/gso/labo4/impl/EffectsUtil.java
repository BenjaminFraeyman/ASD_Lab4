package org.ibcn.gso.labo4.impl;

import org.ibcn.gso.labo3.util.Pixel;

public class EffectsUtil {

    public static Pixel[][] applyConvolution(Pixel[][] source, double[][] filterMatrix, double factor) {
        int height = source.length;
        int width = source[0].length;
        Pixel[][] resultImage = new Pixel[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double red = 0.0;
                double green = 0.0;
                double blue = 0.0;

                for (int filterRow = 0; filterRow < filterMatrix.length; filterRow++) {
                    for (int filterCol = 0; filterCol < filterMatrix[filterRow].length; filterCol++) {
                        int imageX = (col - filterMatrix[filterRow].length / 2 + filterCol + width) % width;
                        int imageY = (row - filterMatrix.length / 2 + filterRow + height) % height;
                        red += source[imageY][imageX].r * filterMatrix[filterRow][filterCol];
                        green += source[imageY][imageX].g * filterMatrix[filterRow][filterCol];
                        blue += source[imageY][imageX].b * filterMatrix[filterRow][filterCol];
                    }
                }

                resultImage[row][col] = new Pixel((int) Math.min(Math.max(factor * red, 0.0), 255.0), (int) Math.min(Math.max(factor * green, 0.0), 255.0), (int) Math.min(Math.max(factor * blue, 0.0), 255.0));
            }
        }
        return resultImage;
    }

}
