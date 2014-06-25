package ui

import javax.swing.*
import java.awt.*

/**
 *
 * This is the GUI window of the application
 * It is a container of other UI elements
 *
 * @author Leni Kirilov
 * @date 6/25/2014
 */
class LogParserWindow extends JFrame {
    //contains the following UI items

    //LogChooser - opens a dialog for configuring a connection towards a local/remote Log file

    //FiltersBar - the filters On/Off; (+) button for dialog to add a regex
    //LogBar - the names of the opened logs is held there

    //LogViewer - contains the log itself; should be able to display different rows
    // indepedently; also marks different places of the text with different colour depending on the filters

    /**
     * 3 rows with 1 element each
     *  Filter bar
     *  Logs Bar
     *  LogViewer
     */

    private LogViewersPanel _logViewersPanel;

    public LogParserWindow() {
        setLayout(new BorderLayout())

        //TODO to extract in separate class together with Logs Bar (names of the log files)
        def filterBar = new JButton("Filter + Log bar")
//        filterBar.setMaximumSize(new Dimension(100,30))
        add filterBar, BorderLayout.PAGE_START

//        def logsBar = new JButton("Logs bar")
//        logsBar.setMaximumSize(new Dimension(100,30))
//        add logsBar

        _logViewersPanel = new LogViewersPanel()
        add _logViewersPanel, BorderLayout.CENTER
    }

    public void addLog(Reader r) {
        _logViewersPanel.addLogView(r)
        repaint()
    }
}
