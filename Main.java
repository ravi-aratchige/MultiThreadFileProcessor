public class Main {
    public static void main(String[] args) {
        // set the directory path to the "demo" folder
        String directoryPath = "demo";

        // instantiate the ConcurrentFileProcessor
        ConcurrentFileProcessor processor = new ConcurrentFileProcessor(directoryPath);

        System.out.println("\n----- MULTI-THREAD FILE PROCESSOR -----");
        // begin multithreaded file processor
        processor.processFiles();
        System.out.println("\n---------------------------------------");
    }
}
