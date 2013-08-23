package bookmarktable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BookmarkTable extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("BookmarkView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.setTitle("Bookmark Table");
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }    
}
