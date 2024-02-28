import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class FileProcessor implements Runnable {
    private final File file;
    private Map<String, Integer> wordCount;

    public FileProcessor(File file) {
        this.file = file;
        this.wordCount = new HashMap<>();
    }

    public File getFile() {
        return file;
    }

    public Map<String, Integer> getWordCount() {
        return wordCount;
    }

    @Override
    public void run() {
        try {
            processFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processFile() throws IOException {
        // Read the content of the file
        String content = new String(Files.readAllBytes(file.toPath()));

        // Perform some processing (e.g., word count)
        String[] words = content.split("\\s+");
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
    }
}
