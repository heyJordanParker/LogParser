package ui

import javax.swing.*

/**
 * Displays log file with scroll pane
 *
 * @author Leni Kirilov
 * @date 6/25/2014
 */
class LogViewerScrollPane extends JScrollPane {

    private JTextArea _textArea;
    private Reader reader;

    LogViewerScrollPane(Reader log) {
        this.reader = log;
        this._textArea = new JTextArea();
        _textArea.read(reader, "")
        _textArea.setEditable false

        setViewportView(_textArea)
        setHorizontalScrollBarPolicy HORIZONTAL_SCROLLBAR_ALWAYS
        setVerticalScrollBarPolicy VERTICAL_SCROLLBAR_ALWAYS
        repaint()
    }
}