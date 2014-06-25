package ui

import javax.swing.*
import java.awt.*
import java.util.List

/**
 * Contains a dynamic number of LogViewerPanels. Should be able to add/remove them at runtime
 *
 * @author Leni Kirilov
 * @date 6/25/2014
 */
class LogViewersPanel extends JPanel {

    private List<LogViewerScrollPane> _logViewers = [];

    LogViewersPanel() {
        setLayout(new GridLayout(1, 2))
        visible = true
    }

    public addLogView(File file) {
        def logPanel = new LogViewerScrollPane(this, file)
        _logViewers.add logPanel

        add logPanel
        repaint()
    }

    public removeLogView(LogViewerScrollPane paneForRemoval) {
        (0.._logViewers.size()).each {
            if (_logViewers[it] == paneForRemoval) {
                _logViewers.remove(it)
            }
        }

        remove(paneForRemoval)
        repaint()
    }

    public static void main(String[] args) {
        def list = ["a", "b", "c", "d", "e", "f"]
        def paneForRemoval = "b"

        ((0..list.size())).each {
            if (list[it] == paneForRemoval) {
                list.remove(it)
                println "removed $it"
            }
        }

        println list
    }
}
