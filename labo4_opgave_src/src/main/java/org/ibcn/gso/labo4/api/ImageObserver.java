package org.ibcn.gso.labo4.api;

import org.ibcn.gso.labo3.util.Pixel;

public interface ImageObserver {

    void updated(Pixel[][] image);

}
