/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ibcn.gso.labo4;

import org.ibcn.gso.labo3.util.Pixel;
import org.ibcn.gso.labo3.util.Region;
import org.ibcn.gso.labo3.filters.*;
import org.ibcn.gso.labo4.api.EffectType;

/**
 *
 * @author Benjamin
 */
public class FilterAdapter extends ImageProviderDecorator{
    private final Filter filter;
    
    public FilterAdapter(ImageProvider provider, Filter filter) {
        super(provider);
        this.filter = filter;
    }

    @Override
    public EffectType getEffectType() {
        if (filter instanceof InvertFilter){
            return EffectType.INVERT;
        }else if(filter instanceof ShiftRgbFilter){
            return EffectType.RGB_SHIFT;
        }else if(filter instanceof HMirrorFilter){
            return EffectType.HORIZONTAL_MIRROR;
        }else if(filter instanceof VMirrorFilter){
            return EffectType.VERTICAL_MIRROR;
        }
        else{return null;}
    }

    @Override
    public Pixel[][] getImage() {
        Pixel[][] pixel = provider.getImage();
        filter.apply(pixel, new Region(0, 0, pixel[0].length, pixel.length));
        return pixel;
    }
    
}
