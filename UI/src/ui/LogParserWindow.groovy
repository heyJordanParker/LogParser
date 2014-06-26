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
    //ButtonBar - the names of the opened logs is held there

    //LogViewer - contains the log itself; should be able to display different rows
    // indepedently; also marks different places of the text with different colour depending on the filters

    /**
     * 3 rows with 1 element each
     *  Filter bar
     *  Log filenames bar
     *  LogViewer
     */

    private LogViewersPanel _logViewersPanel;

    public LogParserWindow() {
        setLayout(new BorderLayout())

        def toolsBar = new ToolsBar()
        add toolsBar, BorderLayout.PAGE_START

        _logViewersPanel = new LogViewersPanel()
        add _logViewersPanel, BorderLayout.CENTER
    }

    private class ToolsBar extends JPanel {
        ToolsBar() {
            setLayout(new GridLayout(1, 1))
//            add (new JMenuBar())
            add(new ButtonBar(LogParserWindow.this))
        }
    }

    public void addLog(File filePath) {
        _logViewersPanel.addLogView(filePath)
        repaint(500)
        repaint()
    }
}
