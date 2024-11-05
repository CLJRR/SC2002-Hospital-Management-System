package FIleManager;

import java.io.IOException;
import java.util.List;

public interface Load {

    List<?> load(String fileName) throws IOException;
}
