import java.io.File;
import java.io.IOException;

/**
 * The {@code DeleteClassFiles} class provides functionality to recursively delete
 * all `.class` files from a specified directory and its subdirectories.
 */
public class DeleteClassFiles {

    /**
     * The main method serves as the entry point for the application.
     * It deletes all `.class` files in the specified directory and its subdirectories.
     *
     * @param args command-line arguments (not used in this application).
     * @throws IOException if an I/O error occurs during file deletion.
     */
    public static void main(String[] args) throws IOException {
        // Set the directory path
        String directoryPath = "./";

        File directory = new File(directoryPath);
        deleteClassFiles(directory);
        System.out.println(".class files deleted successfully.");
    }

    /**
     * Recursively deletes all `.class` files in the given directory and its subdirectories.
     *
     * @param dir the directory to search for `.class` files.
     */
    public static void deleteClassFiles(File dir) {
        // List all files and directories in the current directory
        File[] files = dir.listFiles();

        if (files != null) { // Check if directory is not empty
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteClassFiles(file); // Recursive call for subdirectory
                } else if (file.getName().endsWith(".class")) {
                    file.delete(); // Delete the .class file
                }
            }
        }
    }
}
