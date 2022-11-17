package com.unip.util;

import java.awt.image.BufferedImage;

public class CompareImages {
    /**
     * Realiza a comparação entre 2 imagens quaisquer e
     * imprime a porcentagem de diferença no terminal de comandos (console).
     * @param image1 Imagem número 1.
     * @param image2 Imagem número 2.
     * @return Porcentagem de diferença entre as duas imagens.
     * @throws RuntimeException Ocorre apenas se a dimensão das imagens não forem a mesma.
     */
    public static double Compare(BufferedImage image1, BufferedImage image2) throws RuntimeException {
        int width1, width2, height1, height2;
        
        width1 = image1.getWidth();
        width2 = image2.getWidth();
        height1 = image1.getHeight();
        height2 = image2.getHeight();
        
        if ((width1 != width2) || (height1 != height2)) {
            throw new RuntimeException("Erro: As imagens possuem dimensões diferentes.");
        } else {
            long difference = 0;
            
            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {
                    int rgb1 = image1.getRGB(x, y);
                    int rgb2 = image2.getRGB(x, y);
                    int red1 = (rgb1 >> 16) & 0xFF;
                    int green1 = (rgb1 >> 16) & 0xFF;
                    int blue1 = (rgb1 >> 16) & 0xFF;
                    int red2 = (rgb2 >> 16) & 0xFF;
                    int green2 = (rgb2 >> 16) & 0xFF;
                    int blue2 = (rgb2 >> 16) & 0xFF;
                    
                    difference += Math.abs(red1 - red2);
                    difference += Math.abs(green1 - green2);
                    difference += Math.abs(blue1 - blue2);
                }
            }

            double total_pixels = width1 * height1 * 3;
            double avg_different_pixels = difference / total_pixels;
            double percentage = (avg_different_pixels / 255) * 100;
            
            System.out.println("Porcentagem de diferença: " + percentage);
            
            return percentage;
        }
    }
}
