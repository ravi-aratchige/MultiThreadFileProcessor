import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class FileProcessor implements Runnable {
    // class variables
    private final File file;
    private final Map<String, Integer> wordFrequency;
    private int wordCount;

    // constructor for this class
    public FileProcessor(File file) {
        this.file = file;
        this.wordFrequency = new HashMap<>();
        this.wordCount = 0;
    }

    // getter for file object
    public File getFile() {
        return file;
    }

    // getter for the word frequency of a text file
    public Map<String, Integer> getWordFrequency() {
        return wordFrequency;
    }

    // getter for the word count of a text file
    public int getWordCount() {
        return wordCount;
    }

    // overriden run() method for this class
    @Override
    public void run() {
        try {
            processFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processFile() throws IOException {
        // read the content of the file
        String content = new String(Files.readAllBytes(file.toPath()));

        // split contents of text file into words
        String[] words = content.split("\\s+");
        // traverse through the words in the file
        for (String word : words) {
            // update frequency value of a word in wordFrequency
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            // increment the wordCount by 1
            wordCount++;
        }
    }
}
