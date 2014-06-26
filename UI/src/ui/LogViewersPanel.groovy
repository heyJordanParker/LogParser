package ui

import Log.Log

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

    public addLogView(Log log) {
        def logPanel = new LogViewerScrollPane(this, log)

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
        paneForRemoval.stopPrinting()
        remove paneForRemoval
        repaint()
    }
}
