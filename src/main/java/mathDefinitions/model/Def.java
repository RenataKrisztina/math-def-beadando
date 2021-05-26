package mathDefinitions.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Def {
    private String name;
    private String definition;
    private String topic;
    private String level;

    public static ObservableList<Def> definitions = FXCollections.observableArrayList(new ArrayList<>());


    public static void betoltes(String fnev) throws IOException {

        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        File f = new File(fnev);
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Def.class);
        List<Def> beolvasva = objectMapper.readValue(new FileReader(fnev), listType );
        // List<Def> beolvasva = objectMapper.readValue(new FileReader(fnev), List.class);
        //definitions = (ObservableList<Def>) objectMapper.readValue(new FileReader(fnev), List.class);

        definitions.addAll(beolvasva);
        for( int i = 0; i < beolvasva.size(); i++){
            System.out.println(beolvasva.get(i));
        }

    }


    public static void add(String nev, String def, String topic, String level){
        Def definition = new Def(nev, def, topic, level);

        Def.definitions.add(definition);
    }


    public static void hozzaadas(String fnev) throws IOException {
        var objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        //File f = new File(fnev);

        try (var writer = new FileWriter(fnev)) {
            objectMapper.writeValue(writer, Def.definitions);
        }
    }


}