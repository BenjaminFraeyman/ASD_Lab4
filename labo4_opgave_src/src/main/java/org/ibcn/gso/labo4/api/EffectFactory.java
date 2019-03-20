/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ibcn.gso.labo4.api;

import org.ibcn.gso.labo3.filters.*;
import org.ibcn.gso.labo3.util.Pixel;
import org.ibcn.gso.labo4.FilterAdapter;
import org.ibcn.gso.labo4.ImageProvider;
import org.ibcn.gso.labo4.ImageProviderDecorator;
import org.ibcn.gso.labo4.ImageProviderDecoratorFactory;
import org.ibcn.gso.labo4.SepiaEffect;
import org.ibcn.gso.labo4.impl.EffectsUtil;

/**
 *
 * @author Benjamin
 */
public class EffectFactory implements ImageProviderDecoratorFactory{
    @Override
    public ImageProviderDecorator Create(ImageProvider source, EffectType effectType) {
        switch(effectType) {
            case SEPIA:
                return new SepiaEffect(source);
            case INVERT:
                return new FilterAdapter(source, new InvertFilter()); 
            case HORIZONTAL_MIRROR:
                return new FilterAdapter(source, new HMirrorFilter());
            case VERTICAL_MIRROR:
                return new FilterAdapter(source, new VMirrorFilter());
            case RGB_SHIFT:
                return new FilterAdapter(source, new ShiftRgbFilter());
            case MOTION_BLUR:
                return new ImageProviderDecorator(source){
                    @Override
                    public EffectType getEffectType() {
                        return EffectType.MOTION_BLUR;
                    }
                    
                    @Override
                    public Pixel[][] getImage() {
                        double[][] array = {{1, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 1, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 1, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 1, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 1, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 1, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 1, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 1, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 1}};
                       return EffectsUtil.applyConvolution(source.getImage(), array, 1.0/9.0);
                    }
                };
            case SHARPEN:
                return new ImageProviderDecorator(source) {
                        @Override
                        public EffectType getEffectType() {
                            return EffectType.SHARPEN; 
                        }

                        @Override
                        public Pixel[][] getImage() {
                            double[][] array = {{-1, -1, -1},
                                                {-1, 9, -1},
                                                {-1, -1, -1}};
                            return EffectsUtil.applyConvolution(source.getImage(), array, 1.0);
                        }
                    };
            case DETECT_EDGES:
                return new ImageProviderDecorator(source) {
                        @Override
                        public EffectType getEffectType() {
                            return EffectType.DETECT_EDGES; 
                        }

                        @Override
                        public Pixel[][] getImage() {
                            double[][] array = {{-1, -1, -1},
                                                {-1, 8, -1},
                                                {-1, -1, -1}};
                            return EffectsUtil.applyConvolution(source.getImage(), array, 1.0);
                        }
                    };
            default:
                throw new UnsupportedOperationException();
           }
    }
}