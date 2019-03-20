/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ibcn.gso.labo4;

import org.ibcn.gso.labo3.util.Pixel;
import org.ibcn.gso.labo4.api.EffectType;

/**
 *
 * @author Benjamin
 */
public class SepiaEffect extends ImageProviderDecorator{
    public SepiaEffect(ImageProvider provider) {
        super(provider);
        System.out.println("adding sepia effect");
    }

    @Override
    public EffectType getEffectType() {
      return EffectType.SEPIA;
    }

    @Override
    public Pixel[][] getImage() {
        Pixel[][] image = provider.getImage();
        for (Pixel[] u: image) {
            for (Pixel pixel: u) {
                
                int r = pixel.r;
                int g = pixel.g;
                int b = pixel.b;
                pixel.r = (int)Math.min((r * 0.393 + g * 0.769 + b * 0.189), 255);
                pixel.g = (int)Math.min((r * 0.349 + g * 0.686 + b * 0.168), 255);
                pixel.b = (int)Math.min((r * 0.272 + g * 0.534 + b * 0.131), 255);
            }
        }
        return image;
    }
    
}
