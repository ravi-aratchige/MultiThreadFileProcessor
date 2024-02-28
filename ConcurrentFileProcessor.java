import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConcurrentFileProcessor {
    private final String directoryPath;

    public ConcurrentFileProcessor(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void processFiles() {
        // Traverse the directory and identify all text files
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        // Create a thread for each text file found
        Thread[] threads = new Thread[files.length];
        FileProcessor[] processors = new FileProcessor[files.length];
        for (int i = 0; i < files.length; i++) {
            processors[i] = new FileProcessor(files[i]);
            threads[i] = new Thread(processors[i]);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Display a summary of the processing results
        Map<String, Integer> totalWordCount = new HashMap<>();
        for (FileProcessor processor : processors) {
            Map<String, Integer> wordCount = processor.getWordCount();
            System.out.println("File Name: " + processor.getFile().getName());
            System.out.println("Word Count: " + wordCount);
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                totalWordCount.put(entry.getKey(),
                        totalWordCount.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }

        System.out.println("Total Word Count: " + totalWordCount);
    }
}
