import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage


/**
 * Created by jgeorgiev on 6/30/2014.
 */
public class BrowserTest extends Application {

    private Scene _scene;

    @Override
    void start(Stage stage) throws Exception {
        _scene = new Scene(new Browser(), 750, 500, Color.web("#666970"))

        stage.setScene(_scene)
        stage.show()
    }

    public static void main(String[] args) {
        launch(BrowserTest.class, args)
    }

}
