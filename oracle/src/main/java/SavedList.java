import java.sql.SQLException;
import java.util.ArrayList;

public interface SavedList {
    int size() throws SQLException;
    ArrayList<Data> getData() throws SQLException;
}
