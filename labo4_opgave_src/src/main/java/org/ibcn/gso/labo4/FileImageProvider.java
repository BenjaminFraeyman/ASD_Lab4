/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ibcn.gso.labo4;

import java.io.File;
import java.io.IOException;
import org.ibcn.gso.labo3.util.Pixel;
import org.ibcn.gso.labo3.util.Toolbox;

/**
 *
 * @author Benjamin
 */
public class FileImageProvider implements ImageProvider{
    private final File file;
    
    public FileImageProvider(File file){
        this.file = file;
    }
    
    @Override
    public Pixel[][] getImage() {
        try {
            Pixel[][] pixels = Toolbox.getPixelRaster(file.getAbsolutePath());
            return pixels;
        }
        catch(IOException exception){
            System.out.println("error in getimage");
            return null;
        }
    }  
}