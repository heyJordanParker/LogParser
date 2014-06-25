package Log

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Formatter

/**
 * Created by Jedy on 6/25/2014.
 */
public class Log {

    private ArrayList<Entity> _entities
    private String _dateFormat
    private String _fileName

    private DateTimeFormatter _dateFormatter

    public Log() {
        _entities = new ArrayList<>()
        _dateFormat = "yyyy-MM-dd"
        _dateFormatter = DateTimeFormatter.ofPattern(_dateFormat)
    }

    public void AddEntity(String stringEntity) {
        //setup actual parsing
        //remove date from content
//        Parser parser = new Parser();
//        List groups = parser.parse("the day before next thursday");
//        for(DateGroup group:groups) {
//            List dates = group.getDates();
//            int line = group.getLine();
//            int column = group.getPosition();
//            String matchingValue = group.getText();
//            String syntaxTree = group.getSyntaxTree().toStringTree();
//            Map> parseMap = group.getParseLocations();
//            boolean isRecurreing = group.isRecurring();
//            Date recursUntil = group.getRecursUntil();
//        }
        def entity = new Entity(null, stringEntity)
        AddEntity(entity)
    }

    public void AddEntity(Entity entity) {
        _entities.add(entity)
        println "Added"
    }

    public ArrayList<Entity> GetEntities() {
        return _entities
    }

    public Date GetDateWithOffset(Entity entity) {
        //add the date offset to the entities date
        return null
    }

    public String GetFileName() {
        return _fileName
    }

    public void SetFileName(String name) {
        _fileName = name
    }

}
