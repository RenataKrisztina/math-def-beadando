import mathDefinitions.model.Def;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DefinitionAddTest {
    @Test
    public void testAdd() {
        int size = Def.definitions.size();
        Def.add("kör", "Azon pontok halmaza a síkban, melyek a sík egy adott pontjától egyenlő távolságra vannak.", "GEOMETRIA", "ALTALANOS_ISKOLA");

        Assertions.assertEquals(size+1, Def.definitions.size());
        Def.definitions.clear();
    }

}
