package org.example.Proyecto_FFE;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelos.Controlador;
import modelos.Fichaje;
import modelos.Usuario;
import utils.Utils;

import java.io.IOException;
import java.time.LocalDateTime;

public class HelloApplication extends Application {

    private StringBuilder pinIntup = new StringBuilder();
    public Controlador c = new Controlador();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("I.E.S. Fernando III - Entrada Principal");


        // Cargamos la imagen desde resources.images
        Image logo = new Image(getClass().getResourceAsStream("/images.png"));
        ImageView imageView = new ImageView(logo);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        Label label = new Label("I.E.S. Fernando III  - Entrada Principal");
        Label label1 = new Label("Introduzca su pin: ");


        // Campo de entrada de PIN
        TextField pinField = new TextField();
        pinField.setPromptText("Insertar PIN");
        pinField.setEditable(false);
        pinField.setMaxWidth(300);
        pinField.setAlignment(Pos.TOP_CENTER);


        // Reloj digital
        Label clockLabel = new Label();
        clockLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #333");
        updateClock(clockLabel);

        Timeline clock = new Timeline(
                new KeyFrame(Duration.ZERO, e -> updateClock(clockLabel)),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();


        // Botón enviar
        Button sendButton = new Button("Enviar");
        sendButton.setFont(Font.font(16));
        sendButton.setOnAction(e -> {
            System.out.println("PIN enviado: " + pinIntup.toString());
            pinIntup.setLength(0);
            pinField.setText("");
        });


        GridPane keypad = new GridPane();
        keypad.setHgap(10);
        keypad.setVgap(10);
        keypad.setPadding(new Insets(20));
        keypad.setAlignment(Pos.CENTER);

        Font buttonFont = Font.font(18);

        int num = 1;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int numActual = num;
                Button btn = new Button(String.valueOf(numActual));
                btn.setFont(buttonFont);
                btn.setPrefSize(60, 60);
                btn.setOnAction(e -> {
                    pinIntup.append(numActual);
                    pinField.setText(pinIntup.toString());
                });
                keypad.add(btn, col, row);
                num++;
            }
        }


        // Botón de borrar
        Button borrarBtn = new Button("⬅");
        borrarBtn.setFont(buttonFont);
        borrarBtn.setPrefSize(60, 60);
        borrarBtn.setOnAction(e -> {
            if (pinIntup.length() > 0) {
                pinIntup.deleteCharAt(pinIntup.length() - 1);
                pinField.setText(pinIntup.toString());
            }
        });


        // Botón de reset
        Button resetBtn = new Button("\uD83D\uDD04");
        resetBtn.setFont(buttonFont);
        resetBtn.setPrefSize(60, 60);
        resetBtn.setOnAction(e -> {
            pinIntup.setLength(0);
            pinField.setText("");
        });


        // Botón 0
        Button ceroBtn = new Button("0");
        ceroBtn.setFont(buttonFont);
        ceroBtn.setPrefSize(60, 60);
        ceroBtn.setOnAction(e -> {
            pinIntup.append("0");
            pinField.setText(pinIntup.toString());
        });

        // Incluimos los tres botones independientes a la última fila del pad
        keypad.add(resetBtn, 0, 3);
        keypad.add(ceroBtn, 1, 3);
        keypad.add(borrarBtn, 2, 3);


        // Creamos una etiqueta que de un feedback
        Label feedBackLabel = new Label();
//        feedBackLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-text-fill: red;");
        feedBackLabel.setVisible(false);


        TextArea areaRegistros = new TextArea();
        areaRegistros.setEditable(false);
        areaRegistros.setVisible(false);
        areaRegistros.setPrefHeight(200);
        areaRegistros.setStyle("-fx-font-size: 14px;");

        // Conectamos el boton de "Enviar" con el backEnd
        sendButton.setOnAction(e -> {
            try {
                int pin = Integer.parseInt(pinIntup.toString());
                Usuario uTemp = c.login(pin);
                int mensaje = -1;

                if (!c.compruebaAdmin(pin)) {

                    areaRegistros.setVisible(false);
                    if (uTemp == null) {
                        feedBackLabel.setText("Código incorrecto.");
                        feedBackLabel.setVisible(true);
                        feedBackLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: red;");
                    } else mensaje = c.registraActividad(uTemp);


                    switch (mensaje) {
                        case -1:
                            feedBackLabel.setText("Código incorrecto.");
                            feedBackLabel.setVisible(true);
                            feedBackLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: red;");
                            break;
                        case 0:
                            feedBackLabel.setText("Fichaje de Entrada.");
                            feedBackLabel.setVisible(true);
                            feedBackLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: green;");
                            break;
                        case 1:
                            feedBackLabel.setText("Fichaje de Salida.");
                            feedBackLabel.setVisible(true);
                            feedBackLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: green;");
                            break;
                        default:
                            feedBackLabel.setText("Código incorrecto.");
                            feedBackLabel.setVisible(true);
                            feedBackLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: red;");
                            break;
                    }
                } else {
                    if (c.compruebaAdmin(pin) && c.getFichajes() != null) {
                        StringBuilder pintaRegistros = new StringBuilder("Registros:\n");
                        for (Fichaje f : c.getFichajes()) {
                            pintaRegistros.append(f.toString()).append("\n");
                        }
                        areaRegistros.setText(pintaRegistros.toString());
                        areaRegistros.setVisible(true);
                    } else {
                        areaRegistros.setVisible(false);
                    }
                }


            } catch (NumberFormatException exception) {
                feedBackLabel.setText("Código incorrecto.");
                feedBackLabel.setVisible(true);
                feedBackLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: red;");
            }

            // Reset
            pinIntup.setLength(0);
            pinField.setText("");
        });

        VBox root = new VBox();
        root.setSpacing(5);
        root.getChildren().addAll(imageView, label, label1, pinField, clockLabel, sendButton,
                feedBackLabel, keypad, areaRegistros);
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 350, 150);
        stage.setScene(scene);
        stage.show();
    }

    private void updateClock(Label clockLabel) {
        clockLabel.setText(LocalDateTime.now().format(Utils.TIME_FORMATTER));
    }

    @Override
    public void init() throws Exception {
        System.out.println("init() method: " + Thread.currentThread().getName());
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop() method: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        launch();
    }
}