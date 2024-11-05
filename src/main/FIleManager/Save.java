package FIleManager;

import java.io.IOException;
import java.util.List;

public interface Save {
    public void save(String filename, List<?> al) throws IOException;

}
