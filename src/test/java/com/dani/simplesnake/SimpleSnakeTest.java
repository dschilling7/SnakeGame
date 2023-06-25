/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dani.simplesnake;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Schilling
 */
public class SimpleSnakeTest {
    
    public SimpleSnakeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ermittlungObDasSpielfeldVerlassenWurdeFunktioniertWieErwartet() {        
        SimpleSnake simpleSnake = new SimpleSnake();
        Rectangle schlangenKopf = new Rectangle(1, 1);
        
        schlangenKopf.setTranslateX(0);
        schlangenKopf.setTranslateY(0);        
        assertFalse(simpleSnake.wurdeSpielFeldVerlassen(schlangenKopf));
        
        schlangenKopf.setTranslateX(SimpleSnake.SPIELFELD_BREITE - 1);
        schlangenKopf.setTranslateY(SimpleSnake.SPIELFELD_HOEHE - 1);        
        assertFalse(simpleSnake.wurdeSpielFeldVerlassen(schlangenKopf));
        
        schlangenKopf.setTranslateX(SimpleSnake.SPIELFELD_BREITE);
        schlangenKopf.setTranslateY(SimpleSnake.SPIELFELD_HOEHE);        
        assertTrue(simpleSnake.wurdeSpielFeldVerlassen(schlangenKopf));
        
        schlangenKopf.setTranslateX(-1);
        schlangenKopf.setTranslateY(-1);        
        assertTrue(simpleSnake.wurdeSpielFeldVerlassen(schlangenKopf));
    }    
}
