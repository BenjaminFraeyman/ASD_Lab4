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
public interface ImageProviderDecoratorFactory {
    public ImageProviderDecorator Create(ImageProvider source, EffectType effectType);
}
