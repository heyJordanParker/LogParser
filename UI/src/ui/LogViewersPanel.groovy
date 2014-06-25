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

    public addLogView(Reader reader) {
        def logPanel = new LogViewerScrollPane(reader)
        _logViewers.add logPanel

        add logPanel
        repaint()
    }

    public removeLogView(int index) {
        def removedLog = _logViewers.remove(index)
        remove(removedLog)
    }
}
