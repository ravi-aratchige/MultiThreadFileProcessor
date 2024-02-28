public class Main {
    public static void main(String[] args) {
        // Set the directory path to the "demo" folder
        String directoryPath = "demo";

        // Create an instance of ConcurrentFileProcessor and start processing
        ConcurrentFileProcessor processor = new ConcurrentFileProcessor(directoryPath);
        System.out.println("----- MULTI-THREAD FILE PROCESSOR -----");
        processor.processFiles();
    }
}
