package org.ibcn.gso.labo4.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ibcn.gso.labo4.FileImageProvider;
import org.ibcn.gso.labo4.ImageProviderDecorator;
import org.ibcn.gso.labo4.api.EffectType;
import org.ibcn.gso.labo4.api.EngineFacade;
import org.ibcn.gso.labo4.api.ImageObserver;
import org.ibcn.gso.labo3.util.Toolbox;
import org.ibcn.gso.labo4.ImageProvider;
import org.ibcn.gso.labo4.api.EffectFactory;

public class EngineImpl implements EngineFacade {
    private ImageProvider providerRoot;        
    private final List<ImageObserver> imageObserverLijst = new ArrayList<>();
    
    @Override
    public void load(File sourceFile) {  
        providerRoot = new FileImageProvider(sourceFile);       
        notifyObservers();
    }

    @Override
    public void saveAs(File targetFile) {
        try {         
            Toolbox.writeToDisk(providerRoot.getImage(), targetFile.getPath());
        } catch (IOException ex) {
            Logger.getLogger(EngineImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void toggle(EffectType effectType) {
        ImageProvider removed = null;
        ImageProvider current = providerRoot;
        ImageProvider previous = null;
        
        while(current instanceof ImageProviderDecorator && removed == null ){
            if (((ImageProviderDecorator)current).getEffectType() == effectType){
                removed = current;
                if (previous != null){
                    ((ImageProviderDecorator)previous).setProvider(((ImageProviderDecorator)current).getProvider());
                }
                else{
                    providerRoot = ((ImageProviderDecorator)current).getProvider();
                }
            }
            previous = current;
            current = ((ImageProviderDecorator)current).getProvider();
        }
        if (removed == null){
            providerRoot = new EffectFactory().Create(providerRoot, effectType);
        }
        notifyObservers();
    }
    
    @Override
    public void register(ImageObserver observer) {
        imageObserverLijst.add(observer);
    }
    
    @Override
    public void unregister(ImageObserver observer) {
        int index = imageObserverLijst.indexOf(observer);
        imageObserverLijst.remove(index);
    }
    
    protected void notifyObservers(){
        for(int i =0;i<imageObserverLijst.size();i++){
            imageObserverLijst.get(i).updated(providerRoot.getImage());
        }
    }
}