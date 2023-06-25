/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dani.simplesnake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
public class SteinTest {

    public SteinTest() {
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
    public void schlangenKopfNichtAufSteinFuehrtNichtZuEinerKollision() {
        Stein stein = new Stein(Color.AQUA);
        stein.platzieren();

        Rectangle schlangenKopf = new Rectangle(1, 1);
        schlangenKopf.setTranslateX(stein.element.getTranslateX() + 1);
        schlangenKopf.setTranslateY(stein.element.getTranslateY() + 1);
        assertFalse(stein.kollisionVerursacht(schlangenKopf));
    }

    @Test
    public void schlangenKopfAufSteinFuehrtZuKollision() {
        Stein stein = new Stein(Color.AQUA);
        stein.platzieren();

        Rectangle schlangenKopf = new Rectangle(1, 1);
        schlangenKopf.setTranslateX(stein.element.getTranslateX());
        schlangenKopf.setTranslateY(stein.element.getTranslateY());

        assertTrue(stein.kollisionVerursacht(schlangenKopf));
    }

}
