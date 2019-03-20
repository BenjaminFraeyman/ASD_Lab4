package org.ibcn.gso.labo4.api;

import java.io.File;

public interface EngineFacade {

    void load(File sourceFile);

    void register(ImageObserver observer);

    void saveAs(File targetFile);

    void toggle(EffectType effectType);

    void unregister(ImageObserver observer);
    
}
