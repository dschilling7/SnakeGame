package com.dani.simplesnake;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class SimpleSnake extends Application implements SpielAblaufInterface {
// Spielfeld Groesse

    public static final int BLOCK_GROESSE = 30;
    public static final int SPIELFELD_BREITE = 30 * BLOCK_GROESSE;
    public static final int SPIELFELD_HOEHE = 20 * BLOCK_GROESSE;

    // Variable in Sekunden, in der das Spielfeld neu gezeichnet wird. / Spielgeschwindigkeit
    public static double spielfeldNeuZeichnenInSekunden = 0.1;

    // Bewegungsrichtungen
    public enum Direction {
        OBEN,
        UNTEN,
        LINKS,
        RECHTS
    }

    private Direction richtung = Direction.RECHTS;

    /**
     * Hauptfenster
     */
    private Stage primaryStage;

    private boolean programmLaeuft;

    private final Timeline animation = new Timeline();

    private ObservableList<Node> schlangenKoerperTeile;

    /**
     * Spielfeld zeichnen
     */
    private Parent drawMethod() {
        Pane root = new Pane();
        root.setPrefSize(SPIELFELD_BREITE, SPIELFELD_HOEHE);

        Group schlangenKoerper = new Group();
        schlangenKoerperTeile = schlangenKoerper.getChildren();

        Frucht frucht = new Frucht(Color.BLUE);
        frucht.platzieren();

        Stein stein = new Stein(Color.GRAY);
        stein.platzieren();

        KeyFrame frame = new KeyFrame(Duration.seconds(spielfeldNeuZeichnenInSekunden), event -> {
            if (!programmLaeuft) {
                return;
            }

            Node schlangenKopf = zeichneSchlangenbewegung();

            ermittleKollissionUndStarteAllenfallsNeu(schlangenKopf);

            if (wurdeSpielFeldVerlassen(schlangenKopf)) {
                spielNeuStarten();
            }

            if (frucht.kollisionVerursacht(schlangenKopf)) {
                // Frucht umplatzieren
                frucht.platzieren();

                // Schlange um einen Block vergrössern, da Frucht gefressen
                Rectangle neuerSchlangenTeil = new Rectangle(BLOCK_GROESSE, BLOCK_GROESSE);
                neuerSchlangenTeil.setTranslateX(schlangenKopf.getTranslateX());
                neuerSchlangenTeil.setTranslateY(schlangenKopf.getTranslateY());
                schlangenKoerperTeile.add(neuerSchlangenTeil);
            }

            if (stein.kollisionVerursacht(schlangenKopf)) {
                spielNeuStarten();
            }
        });

        animation.getKeyFrames().add(frame);
        animation.setCycleCount(Timeline.INDEFINITE);

        root.getChildren().addAll(frucht.element, stein.element, schlangenKoerper);

        return root;
    }

    // aktualisiert die Position der Schlange entsprechend der aktuellen Richtung
    private Node zeichneSchlangenbewegung() {
        boolean mindestensEineFruchtGefressen = schlangenKoerperTeile.size() > 1;

        Node neuerSchlangenkopf = mindestensEineFruchtGefressen ? schlangenKoerperTeile.remove(schlangenKoerperTeile.size() - 1) : schlangenKoerperTeile.get(0);

        switch (richtung) {
            case OBEN:
                neuerSchlangenkopf.setTranslateX(schlangenKoerperTeile.get(0).getTranslateX());
                neuerSchlangenkopf.setTranslateY(schlangenKoerperTeile.get(0).getTranslateY() - BLOCK_GROESSE);
                break;
            case UNTEN:
                neuerSchlangenkopf.setTranslateX(schlangenKoerperTeile.get(0).getTranslateX());
                neuerSchlangenkopf.setTranslateY(schlangenKoerperTeile.get(0).getTranslateY() + BLOCK_GROESSE);
                break;
            case LINKS:
                neuerSchlangenkopf.setTranslateX(schlangenKoerperTeile.get(0).getTranslateX() - BLOCK_GROESSE);
                neuerSchlangenkopf.setTranslateY(schlangenKoerperTeile.get(0).getTranslateY());
                break;
            case RECHTS:
                neuerSchlangenkopf.setTranslateX(schlangenKoerperTeile.get(0).getTranslateX() + BLOCK_GROESSE);
                neuerSchlangenkopf.setTranslateY(schlangenKoerperTeile.get(0).getTranslateY());
                break;
        }

        if (mindestensEineFruchtGefressen) {
            schlangenKoerperTeile.add(0, neuerSchlangenkopf);
        }

        return neuerSchlangenkopf;
    }

    // Bedingung Spielfeldwand
    public boolean wurdeSpielFeldVerlassen(Node schlangenKopf) {
        return schlangenKopf.getTranslateX() < 0
                || schlangenKopf.getTranslateX() >= SPIELFELD_BREITE
                || schlangenKopf.getTranslateY() < 0
                || schlangenKopf.getTranslateY() >= SPIELFELD_HOEHE;
    }

    // Bedingung eigene Körperkollision
    private void ermittleKollissionUndStarteAllenfallsNeu(Node schlangenKopf) {
        for (Node node : schlangenKoerperTeile) {
            if (node != schlangenKopf && schlangenKopf.getTranslateX() == node.getTranslateX() && schlangenKopf.getTranslateY() == node.getTranslateY()) {
                spielNeuStarten();
                break;
            }
        }
    }

    // Methode für Spielneustart
    @Override
    public void spielNeuStarten() {
        spielBeenden();
        spielStarten();
    }

    // Methode für Spielstart
    @Override
    public void spielStarten() {
        richtung = Direction.RECHTS;
        Rectangle schlangenKopf = new Rectangle(BLOCK_GROESSE, BLOCK_GROESSE);
        schlangenKoerperTeile.add(schlangenKopf);
        animation.play();
        programmLaeuft = true;
    }

    @Override
    public void spielBeenden() {
        programmLaeuft = false;
        animation.stop();
        schlangenKoerperTeile.clear();
    }

    // Einstiegspunkt. Initialisiert das Hauptfenster, reagiert auf Tastatureingaben und startet die Anwendung
    @Override
    public void start(Stage primaryStage) {
        try {
            Scene scene = new Scene(drawMethod());

            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case W:
                    case UP:
                        if (richtung != Direction.UNTEN) {
                            richtung = Direction.OBEN;
                        }
                        break;
                    case S:
                    case DOWN:
                        if (richtung != Direction.OBEN) {
                            richtung = Direction.UNTEN;
                        }
                        break;
                    case A:
                    case LEFT:
                        if (richtung != Direction.RECHTS) {
                            richtung = Direction.LINKS;
                        }
                        break;
                    case D:
                    case RIGHT:
                        if (richtung != Direction.LINKS) {
                            richtung = Direction.RECHTS;
                        }
                        break;

                    case ESCAPE:
                        spielBeenden();
                }
            });

            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Danis Snakegame");
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

            spielStarten();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main startet das Spiel
    public static void main(String[] args) {
        launch(args);
    }
}
