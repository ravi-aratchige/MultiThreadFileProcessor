import java.io.File;
import java.util.Map;

public class ConcurrentFileProcessor {
    // class variables
    private final String directoryPath;

    // constructor for this class
    public ConcurrentFileProcessor(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void processFiles() {
        // instantiate a directory object
        File directory = new File(directoryPath);

        // check if the directory is empty
        if (directory.listFiles() == null || directory.listFiles().length == 0) {
            System.out.println("ERROR: The selected directory is empty!");
            return;
        }

        // traverse the directory object and detect all text files (ending in .txt or .TXT)
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        // check if no text files are found
        if (files.length == 0) {
            System.out.println("ERROR: No text files found in the selected directory!");
            return;
        }

        // create a thread for each text file found
        Thread[] threads = new Thread[files.length];
        FileProcessor[] processors = new FileProcessor[files.length];
        for (int i = 0; i < files.length; i++) {
            processors[i] = new FileProcessor(files[i]);
            threads[i] = new Thread(processors[i]);
            threads[i].start();
        }

        // wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // display summary of the text file processing
        for (FileProcessor processor : processors) {
            System.out.println("\nFile Name: " + processor.getFile().getName());
            System.out.println("Word Count: " + processor.getWordCount());
            System.out.println("Word Frequency: " + processor.getWordFrequency());
        }
    }
}
