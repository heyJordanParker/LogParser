import javafx.scene.layout.Region
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView

/**
 * Created by jgeorgiev on 7/2/2014.
 */
class Browser extends Region {

    WebView _webView;
    WebEngine _webEngine;

    public Browser() {
        _webView = new WebView()
        _webEngine = _webView.getEngine()

        URL html = getClass().getResource("index.html")
        _webEngine.load(html.toExternalForm())

        getChildren().add(_webView)
    }

}
