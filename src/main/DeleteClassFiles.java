
import java.io.File;

public class DeleteClassFiles {

    public static void main(String[] args) {
        // Set the directory path
        String directoryPath = "./";

        File directory = new File(directoryPath);
        deleteClassFiles(directory);
        System.out.println(".class files deleted successfully.");
    }

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
