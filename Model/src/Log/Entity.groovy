package Log
/**
 * Created by Jedy on 6/25/2014.
 */
public class Entity {

    Date _date
    String _content

    public Entity(Date date, String content) {
        _date = date
        _content = content
    }

    public Date GetDate() {
        return _date
    }

    public String GetContent() {
        return _content
    }

}
