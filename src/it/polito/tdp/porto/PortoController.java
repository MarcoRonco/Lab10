package it.polito.tdp.porto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Edge;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {

	Model m;
	
	public void setModel(Model m){
		this.m = m;
		boxPrimo.getItems().addAll(m.createGraph());
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox <Author> boxPrimo;

    @FXML
    private ComboBox <Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void doPopola(ActionEvent event) {
    	List<Author> l = m.createGraph();
    	l.removeAll(m.getCoautori(boxPrimo.getValue()));
    	l.remove(boxPrimo.getValue());
    	boxSecondo.getItems().addAll(l);
    }

    @FXML
    void handleCoautori(ActionEvent event) {
    	txtResult.clear();
    	if(boxPrimo.getValue()!=null){
    		for(Author a : m.getCoautori(boxPrimo.getValue())){
    			txtResult.appendText("-"+a.toString()+"\n");
    		}
    	}
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	txtResult.clear();
    	if(boxPrimo.getValue()!=null && boxSecondo.getValue()!=null){
    		for(Edge a : m.articoliTraAutori(boxPrimo.getValue(), boxSecondo.getValue())){
    			txtResult.appendText("-"+a.toString()+"\n");
    		}
    	}
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";
    }
}
