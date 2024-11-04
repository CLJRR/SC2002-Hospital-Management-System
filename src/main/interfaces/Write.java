package interfaces;

import java.io.IOException;
import java.util.List;

public interface Write {

    public void write(String fileName, List<String> data) throws IOException;

}
