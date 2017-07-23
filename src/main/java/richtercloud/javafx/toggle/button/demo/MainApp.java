package richtercloud.javafx.toggle.button.demo;

import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainApp extends Application {
    private final static Random RANDOM = new Random();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        ToggleButton bottomButton = new ToggleButton("Some button");
        borderPane.setBottom(bottomButton);

        Button button = new Button("Disable");
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
            bottomButton.setDisable(RANDOM.nextBoolean());
        });
        borderPane.setRight(button);

        StackPane  root  =  new  StackPane();
        root.getChildren().add(borderPane);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        launch(args);
//    }
}
