package bookmarktable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BookmarkViewController implements Initializable {
    ObservableList<Bookmark> bookmarks = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Bookmark> table;
    @FXML
    private TableColumn<Bookmark, String> siteColumn;
    @FXML
    private TableColumn<Bookmark, String> urlColumn;
    @FXML
    private WebView webView;
    
    private WebEngine engine;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // テーブルに表示するブックマーク
        bookmarks.addAll(
            new Bookmark("Google", "https://www.google.co.jp/"),
            new Bookmark("Yahoo!", "http://www.yahoo.co.jp/"),
            new Bookmark("Facebook", "https://www.facebook.com/"),
            new Bookmark("Twitter", "https://twitter.com/")
        );
        
        // カラムとBookmarkクラスのプロパティの対応付け
        siteColumn.setCellValueFactory(new PropertyValueFactory("site"));
        urlColumn.setCellValueFactory(new PropertyValueFactory("url"));
        
        // テーブルにブックマークをセット
        table.setItems(bookmarks);
        
        // WebEngineをWebViewから取得
        engine = webView.getEngine();
        
        TableView.TableViewSelectionModel<Bookmark> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Bookmark>() {
            @Override
            public void changed(ObservableValue<? extends Bookmark> value, Bookmark old, Bookmark next) {
                String url = next.getUrl();
                engine.load(url);
            }
        });
    }    
}
