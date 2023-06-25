/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.simplesnake;

import static com.dani.simplesnake.SimpleSnake.BLOCK_GROESSE;
import static com.dani.simplesnake.SimpleSnake.SPIELFELD_BREITE;
import static com.dani.simplesnake.SimpleSnake.SPIELFELD_HOEHE;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Daniel Schilling
 */
public abstract class SpielfeldElement {
    public Rectangle element;
    
    public SpielfeldElement(Color farbe)
    {
        element = new Rectangle(BLOCK_GROESSE, BLOCK_GROESSE);    
        element.setFill(farbe);
    }
    
    public void platzieren(){
        element.setTranslateX(generiereZufallszahl(BLOCK_GROESSE, SPIELFELD_BREITE - BLOCK_GROESSE));
        element.setTranslateY(generiereZufallszahl(BLOCK_GROESSE, SPIELFELD_HOEHE - BLOCK_GROESSE));
    }
    
    public boolean kollisionVerursacht(Node schlangenKopf){
        return schlangenKopf.getTranslateX() == element.getTranslateX() && schlangenKopf.getTranslateY() == element.getTranslateY();
    }
    
    abstract public boolean spielBeendenBeiKollision();   
    
    // Methode für Zufallszahlgenerierung
    private int generiereZufallszahl(int min, int max) {
        int range = (max - min) + 1;
        return (int) ((Math.random() * range) + min) / BLOCK_GROESSE * BLOCK_GROESSE;
    }
}
