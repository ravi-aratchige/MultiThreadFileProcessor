import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileProcessorGui {

    // class variables (GUI components)
    private JFrame frame;
    private JLabel label, footer;

    // constructor for this class (to set up the GUI)
    public FileProcessorGui() {

        // configure empty panel to act as padding between GUI components on the frame
        JPanel paddingPanel = new JPanel();
        paddingPanel.setPreferredSize(new Dimension(400, 10));

        // set window title and other configurations
        frame = new JFrame("Multi-Thread File Processor");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // center the GUI window on the screen
        frame.setLocationRelativeTo(null);

        // add description to window
        label = new JLabel("This program can process text files concurrently.");
        frame.add(label);

        // instantiate "Select Folder" button
        JButton selectFolderButton = new JButton("Select Folder");
        selectFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFolder();
            }
        });
        // add button to frame
        frame.add(selectFolderButton);

        // add padding between "Select Folder" button and footer
        frame.add(paddingPanel);

        // instantiate footer message
        footer = new JLabel("Developed by Ravindu Aratchige");

        // add footer to frame
        frame.add(footer);

        // display GUI window
        frame.setVisible(true);
    }

    // handle directory selection
    private void selectFolder() {
        // instantiate JFileChooser object
        JFileChooser fileChooser = new JFileChooser();

        // configure fileChooser object to select folders only, not files
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // instantiate java.io.File object with selected folder
            File selectedFolder = fileChooser.getSelectedFile();

            // get absolute path of selected folder
            String directoryPath = selectedFolder.getAbsolutePath();

            // start concurrently processing files in the selected folder
            ConcurrentFileProcessor processor = new ConcurrentFileProcessor(directoryPath);
            processor.processFiles();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // launch the GUI
                new FileProcessorGui();
            }
        });
    }
}

