package mathDefinitions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mathDefinitions.model.Def;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DefinitionsController {

    @FXML
    public TextField keresesMezo;
    @FXML
    public CheckBox algebra;
    @FXML
    public CheckBox geometria;
    @FXML
    public CheckBox szamelmelet;
    @FXML
    public CheckBox analizis;
    @FXML
    public CheckBox alt_isk;
    @FXML
    public CheckBox egyetem;
    @FXML
    public CheckBox kozepisk;
    @FXML
    public CheckBox algebra1;
    @FXML
    public CheckBox geometria1;
    @FXML
    public CheckBox szamelmelet1;
    @FXML
    public CheckBox analizis1;
    @FXML
    public CheckBox alt_isk1;
    @FXML
    public CheckBox egyetem1;
    @FXML
    public CheckBox kozepisk1;
    @FXML
    public TableView<Def> tabla;
    @FXML
    public Button kereses;
    @FXML
    public Button letrehozas;
    @FXML
    public TextField nevMezo;
    public TextField defMezo;
    public TableColumn<Def, String> name;
    public TableColumn<Def, String> level;
    public TableColumn<Def, String> topic;
    public TableColumn<Def, String> definition;


    @FXML
    public void initialize(){
        tabla.setItems(Def.definitions);    //feltoltom a tablazatot

        name.setCellValueFactory(new PropertyValueFactory<Def, String>("name"));
        level.setCellValueFactory(new PropertyValueFactory<Def, String>("level"));
        topic.setCellValueFactory(new PropertyValueFactory<Def, String>("topic"));
        definition.setCellValueFactory(new PropertyValueFactory<Def, String>("definition"));

    }

    public void hozzaadogomb(ActionEvent actionEvent) throws IOException {


        String level = null;
        int l1 = 0, l2 = 0, l3 = 0, t1 = 0, t2 = 0, t3 = 0, t4 = 0;
        if( alt_isk1.isSelected()){
            level = "ALTALANOS_ISKOLA";
            l1 = 1;
        }
        if( kozepisk1.isSelected()){
            level = "KOZEP_ISKOLA";
            l2 = 1;
        }
        if( egyetem1.isSelected()){
            level = "EGYETEM";
            l3 = 1;
        }

        String topic = null;
        if( algebra1.isSelected()){
            topic = "ALGEBRA";
            t1 = 1;
        }
        if( szamelmelet1.isSelected()){
            topic = "SZAMELMELET";
            t2 = 1;
        }
        if( analizis1.isSelected()){
            topic = "ANALIZIS";
            t3 = 1;
        }
        if( geometria1.isSelected()){
            topic = "GEOMETRIA";
            t4 = 1;
        }

        if( (l1+l2+l3 <=1 && l1+l2+l3 > 0) && (t1+t2+t3+t4 <= 1 && t1+t2+t3+t4 > 0) && !(nevMezo.getText().equals("")) && !(defMezo.getText().equals("")) ){
            Def.add(nevMezo.getText(), defMezo.getText(), topic, level);

            Def.hozzaadas("definiciok.json");
        }

        //lenullazom
        l1 = 0; l2 = 0; l3 = 0; t1 = 0; t2 = 0; t3 = 0; t4 = 0;
        alt_isk1.setSelected(false);
        kozepisk1.setSelected(false);
        egyetem1.setSelected(false);
        algebra1.setSelected(false);
        szamelmelet1.setSelected(false);
        analizis1.setSelected(false);
        geometria1.setSelected(false);
        defMezo.clear();
        nevMezo.clear();

    }

    public void keresogomb(ActionEvent actionEvent) {


        ObservableList<Def> filteredDef = FXCollections.observableArrayList(new ArrayList<>());


        List<String> levels = new ArrayList();
        if( alt_isk.isSelected()){
            levels.add("ALTALANOS_ISKOLA");
        }
        if( kozepisk.isSelected()){
            levels.add("KOZEP_ISKOLA");
        }
        if( egyetem.isSelected()){
            levels.add("EGYETEM");
        }

        List<String> topics = new ArrayList<>();
        if( algebra.isSelected()){
            topics.add("ALGEBRA");
        }
        if( szamelmelet.isSelected()){
            topics.add("SZAMELMELET");
        }
        if( analizis.isSelected()){
            topics.add("ANALIZIS");
        }
        if( geometria.isSelected()){
            topics.add("GEOMETRIA");
        }


        for( Def d: Def.definitions){
            if( levels.contains(d.getLevel()) && topics.contains(d.getTopic())){
                filteredDef.add(d);
            }
            if( levels.contains(d.getLevel()) && topics.isEmpty()){
                filteredDef.add(d);
            }
            if( topics.contains(d.getTopic()) && levels.isEmpty()){
                filteredDef.add(d);
            }

        }

        for(Def dd: filteredDef){
            System.out.println(dd);
        }

        tabla.setItems(filteredDef);

        //lenullazom
        alt_isk.setSelected(false);
        kozepisk.setSelected(false);
        egyetem.setSelected(false);
        algebra.setSelected(false);
        szamelmelet.setSelected(false);
        analizis.setSelected(false);
        geometria.setSelected(false);

    }
}
