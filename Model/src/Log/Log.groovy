package Log

import java.time.format.DateTimeFormatter

/**
 * Created by Jedy on 6/25/2014.
 */
class Log {

    private ArrayList<Entity> _entities
    private String _dateFormat

    private DateTimeFormatter _dateFormatter

    public Log() {
        _entities = new ArrayList<>()
        _dateFormatter = new DateTimeFormatter(_dateFormat)
    }

    public void AddEntity(String stringEntity) {

        def date = _dateFormatter.parse(stringEntity)
        //setup actual parsing
        //remove date from content
        def entity = new Entity(date, stringEntity)
        AddEntitiy(entity)
    }

    public void AddEntitiy(Entity entity) {

    }

    public ArrayList<Entity> GetEntities() {
        return _entities
    }

    public Date GetDateWithOffset(Entity entity) {
        //add the date offset to the entities date
        return null
    }

}
