package bookmarktable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookmarkViewController implements Initializable {
    
    @FXML
    private TableView<Bookmark> table;
    @FXML
    private TableColumn<Bookmark, String> siteColumn;
    @FXML
    private TableColumn<Bookmark, String> urlColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
