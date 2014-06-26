package ui

import javax.swing.*
import java.awt.*

/**
 * Contains a dynamic number of LogViewerPanels. Should be able to add/remove them at runtime
 *
 * @author Leni Kirilov
 * @date 6/25/2014
 */
class LogViewersPanel extends JPanel {

    LogViewersPanel() {
        setLayout(new GridLayout(1, 2))
        visible = true
    }

    public addLogView(File file) {
        def logPanel = new LogViewerScrollPane(this, file)

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Here I perform adding new tab
                add logPanel
                invalidate();
                repaint();
            }
        });
    }

    public removeLogView(LogViewerScrollPane paneForRemoval) {
        remove paneForRemoval
        repaint()
    }
}
