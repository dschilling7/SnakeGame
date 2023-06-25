/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.simplesnake;

import javafx.scene.paint.Color;

/**
 *
 * @author Daniel Schilling
 */
public class Stein extends SpielfeldElement {
    public Stein(Color farbe) {
        super(farbe);
    }
    
    @Override
    public boolean spielBeendenBeiKollision() {
        return true;
    }    
}
