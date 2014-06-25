package ui

import javax.swing.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * Controls for adding filters and logs
 *
 * @author Leni Kirilov
 * @date 6/25/2014
 */
class ButtonBar extends JToolBar {

    private LogParserWindow mainWindow;

    ButtonBar(LogParserWindow parent) {
        this.mainWindow = parent

        def newLogBtn = new JButton("Add local log file")
        newLogBtn.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                def fileChooser = new JFileChooser()
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY)

                def userInput = fileChooser.showOpenDialog(parent)
                if (userInput == JFileChooser.APPROVE_OPTION) {
                    parent.addLog(fileChooser.getSelectedFile())
                }
            }
        })

        add newLogBtn

        def remoteLogBtn = new JButton("Add remote log file ")
        add remoteLogBtn

        def filterBtn = new JButton("Add filter")
        add filterBtn
    }

}
