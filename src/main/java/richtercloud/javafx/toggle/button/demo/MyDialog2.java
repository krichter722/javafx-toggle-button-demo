/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package richtercloud.javafx.toggle.button.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.util.List;
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
import javax.swing.JDialog;

/**
 *
 * @author richter
 */
public class MyDialog2 extends JDialog {
    
    private static final long serialVersionUID = 1L;

    public MyDialog2(Window owner,
            List<Runnable> initialRunnables) {
        super(owner, ModalityType.APPLICATION_MODAL);
        JFXPanel jFXPanel = new JFXPanel();
        Platform.runLater(() -> {
            Pane toggleButtonPane = new FlowPane();
            for(Runnable initialRunnable : initialRunnables) {
                toggleButtonPane.getChildren().add(new ToggleButton());
            }
            
            Button backgroundTaskButton = new Button("Start background task");
            backgroundTaskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (javafx.scene.input.MouseEvent mouseEvent) -> {
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
            Scene scene = new Scene(mainPane, Color.ALICEBLUE);
            //putting splitPane inside a Group causes JFXPanel to be not resized
            //<ref>http://stackoverflow.com/questions/41034366/how-to-provide-javafx-components-in-a-dialog-in-a-swing-application/41047426#41047426</ref>
            jFXPanel.setScene(scene);
        });
        this.add(jFXPanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(800, 600));
        pack();
    } //putting splitPane inside a Group causes JFXPanel to be not resized
    //<ref>http://stackoverflow.com/questions/41034366/how-to-provide-javafx-components-in-a-dialog-in-a-swing-application/41047426#41047426</ref>
    
}
