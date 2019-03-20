/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ibcn.gso.labo4;

import org.ibcn.gso.labo4.api.EffectType;

/**
 *
 * @author Benjamin
 */
public abstract class ImageProviderDecorator implements ImageProvider{
    protected ImageProvider provider;
    
    public ImageProviderDecorator(ImageProvider provider){
        this.provider = provider;
    }
    
   public  ImageProvider getProvider(){
        return provider;
    }
    
    public void setProvider(ImageProvider provider){
        this.provider = provider;
    }
    
    public abstract EffectType getEffectType();
    
}
