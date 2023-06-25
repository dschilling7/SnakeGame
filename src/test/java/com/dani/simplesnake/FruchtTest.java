/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dani.simplesnake;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javafx.scene.paint.Color;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Schilling
 */
public class FruchtTest {

    public FruchtTest() {
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
    public void beiKollisionMitDerFruchtSollSpielNichtBeendetWerden() {
        Frucht frucht = new Frucht(Color.BLUE);
        assertFalse(frucht.spielBeendenBeiKollision());
    }

}
