import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Um programa para ajudar a perceber as Threads e a JavaFX Thread
 * @author João Paulo BArros
 * @version 2020/04/13
 */

public class GuiStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println("begin of start: this is thread " + Thread.currentThread().getName());

        Button oneButton = new Button("Click me!");
        oneButton.setPrefWidth(300);

        oneButton.setOnMouseClicked((e) -> {
            System.out.println("Started handling clicked mouse in " + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            new Thread( () -> {
                System.out.println("this new thread waits 6 seconds: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Platform.runLater( () -> {
                    System.out.println("New thread send something to be executed in  " + Thread.currentThread().getName());
                });

                System.out.println("this new thread has ended: " + Thread.currentThread().getName());
            }).start();;

            System.out.println("Finished handling clicked mouse in " + Thread.currentThread().getName());

        });

        Scene scene = new Scene(oneButton);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest((e) -> {
            System.exit(0);
        });
        primaryStage.show();
        System.out.println("end of start: this is thread " + Thread.currentThread().getName());

    }

}
