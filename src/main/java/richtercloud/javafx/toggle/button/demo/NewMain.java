/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package richtercloud.javafx.toggle.button.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author richter
 */
public class NewMain extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NewMain().setVisible(true);
        });
    }
    
    public NewMain() throws HeadlessException {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //working0();
        //working1();
        //working2();
        working3();
        this.setPreferredSize(new Dimension(800, 600));
        pack();
    }

    private void working3() {
        JButton dialogButton = new JButton("show dialog");
        add(dialogButton,
                BorderLayout.CENTER);
        Runnable runnable = () -> {
            System.out.println("Do something");
        };
        dialogButton.addActionListener(event -> {
            Runnable swingDialogRunnable = () -> {
                try {
                    Thread.sleep(2000);
                    SwingUtilities.invokeLater(() -> {
                        MyDialog2 dialog = new MyDialog2(NewMain.this,
                                new LinkedList<>(Arrays.asList(runnable)));
                        dialog.setVisible(true);
                    });
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            };
            Thread swingDialogThread = new Thread(swingDialogRunnable);
            swingDialogThread.start();
        });
    }

    private void working2() {
        JFXPanel jFXPanel = new JFXPanel();
        Platform.runLater(() -> {
            Pane toggleButtonPane = new FlowPane();
            Button backgroundTaskButton = new Button("Start background task");
            backgroundTaskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
                ToggleButton toggleButton = new ToggleButton("some text");
                toggleButton.setDisable(true);
                toggleButtonPane.getChildren().add(toggleButton);
                Runnable runnable = () -> {
                    try {
                        System.out.println("sleep started");
                        Thread.sleep(2000);
                        System.out.println("sleep finished");
                        Platform.runLater(() -> {
                            System.out.println("re-enabling toggle button on Java FX thread");
                            toggleButton.setDisable(false);
                        });
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            });
            
            BorderPane mainPane = new BorderPane();
            mainPane.setTop(backgroundTaskButton);
            mainPane.setCenter(toggleButtonPane);
            Scene  scene  =  new  Scene(mainPane, Color.ALICEBLUE);
            //putting splitPane inside a Group causes JFXPanel to be not resized
            //<ref>http://stackoverflow.com/questions/41034366/how-to-provide-javafx-components-in-a-dialog-in-a-swing-application/41047426#41047426</ref>
            jFXPanel.setScene(scene);
        });
        this.add(jFXPanel,
                BorderLayout.CENTER);
    }

    private void working0() {
        JFXPanel jFXPanel = new JFXPanel();
        Platform.runLater(() -> {
            Button backgroundTaskButton = new Button("Start background task");
            ToggleButton toggleButton = new ToggleButton("some text");
            toggleButton.setDisable(true);
            backgroundTaskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
                Runnable runnable = () -> {
                    try {
                        System.out.println("sleep started");
                        Thread.sleep(2000);
                        System.out.println("sleep finished");
                        Platform.runLater(() -> {
                            System.out.println("re-enabling toggle button on Java FX thread");
                            toggleButton.setDisable(false);
                        });
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            });
            
            BorderPane mainPane = new BorderPane();
            mainPane.setTop(backgroundTaskButton);
            mainPane.setCenter(toggleButton);
            Scene  scene  =  new  Scene(mainPane, Color.ALICEBLUE);
            //putting splitPane inside a Group causes JFXPanel to be not resized
            //<ref>http://stackoverflow.com/questions/41034366/how-to-provide-javafx-components-in-a-dialog-in-a-swing-application/41047426#41047426</ref>
            jFXPanel.setScene(scene);
        });
        this.add(jFXPanel,
                BorderLayout.CENTER);
    }

    private void working1() {
        JButton dialogButton = new JButton("show dialog");
        add(dialogButton,
                BorderLayout.CENTER);
        dialogButton.addActionListener(event -> {
            MyDialog dialog = new MyDialog(NewMain.this);
            dialog.setVisible(true);
        });
    }

}
