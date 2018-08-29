package pl.joannabrania.allegro.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField fieldUrl;

    @FXML
    Label labelPrice;

    @FXML
    Label labelInfo;

    @FXML
    Button buttonShowPrice;

    public void initialize(URL location, ResourceBundle resources){
        buttonShowPrice.setOnMouseClicked(s -> {
            try {
               labelPrice.setText(String.valueOf(getPrice(fieldUrl.getText())));
               labelInfo.setText(getInfo(fieldUrl.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private double getPrice(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element price = doc.selectFirst(".m-price");
        String text = price.text();

        return Double.valueOf(text.replaceAll("[^0-9,]","").replace(",","."));
    }

    private String getInfo(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element info = doc.selectFirst("._09810109");
        String text = info.text();

        return text;
    }
}
