package bookmarktable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

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
        
        // テーブルの選択位置が変化したら、WebViewでそのサイトを表示
        TableView.TableViewSelectionModel<Bookmark> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Bookmark>() {
            @Override
            public void changed(ObservableValue<? extends Bookmark> value, Bookmark old, Bookmark next) {
                String url = next.getUrl();
                engine.load(url);
            }
        });

        // Webページのロードの状態に応じて、アニメーションを行う
        Worker<Void> loadWorker = engine.getLoadWorker();
        loadWorker.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State old, Worker.State next) {
                if (next == Worker.State.SCHEDULED) {
                    // 次のページのローディングが始まったら、現在表示しているページをフェードアウト
                    FadeTransition fadeOut = new FadeTransition(Duration.millis(1_000), webView);
                    fadeOut.setToValue(0.0);
                    fadeOut.play();
                } else if (next == Worker.State.SUCCEEDED) {
                    // ページのローディングが完了したら、フェードイン
                    FadeTransition fadeIn = new FadeTransition(Duration.millis(1_000), webView);
                    fadeIn.setToValue(1.0);
                    fadeIn.play();
                }
            }
        });        
    }    
}
