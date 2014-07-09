package ui

import Log.Log
import InputParser
import SSHReader.SSHConnection

import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.util.concurrent.LinkedBlockingQueue

/**
 * Controls for adding filters and logs
 *
 * @author Leni Kirilov
 * @date 6/25/2014
 */
class ButtonBar extends JToolBar {

    private LogParserWindow mainWindow;
    private JFrame remoteChooserFrame;

    ButtonBar(LogParserWindow parent) {
        this.mainWindow = parent

        //TODO make it work for local files as well
//        def newLogBtn = new JButton("Add local log file")
//        newLogBtn.addActionListener(new ActionListener() {
//            @Override
//            void actionPerformed(ActionEvent e) {
//                def fileChooser = new JFileChooser()
//                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY)
//
//                def userInput = fileChooser.showOpenDialog(parent)
//                if (userInput == JFileChooser.APPROVE_OPTION) {
//                    parent.addLog(fileChooser.getSelectedFile())
//                }
//            }
//        })
//        add newLogBtn

        def remoteLogBtn = new JButton("Add remote log file ")
        remoteLogBtn.addActionListener(new ActionListener() {
            @Override
            void actionPerformed(ActionEvent e) {
                //show JPanel in new window

                remoteChooserFrame = new JFrame(remoteLogBtn.getText())
                remoteChooserFrame.add(new RemoteFileOpener());
                remoteChooserFrame.setVisible(true)
                remoteChooserFrame.setSize 300, 150
                remoteChooserFrame.setResizable(false)
                remoteChooserFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        we.getWindow().dispose();
                        mainWindow.setEnabled(true)
                        mainWindow.setVisible(true)
                    }
                });

                remoteChooserFrame.setLocationRelativeTo(mainWindow)
                mainWindow.setEnabled(false)
            }
        })
        add remoteLogBtn

//        def filterBtn = new JButton("Add filter")
//        add filterBtn
    }

    public void pullThePlug() {
        WindowEvent wev = new WindowEvent(remoteChooserFrame, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }

    private class RemoteFileOpener extends JPanel {

        private JTextField host
        private JTextField username
        private JTextField password
        private JTextField path

        RemoteFileOpener() {
            setLayout(new GridLayout(5, 2))
            JLabel hostLbl = new JLabel("Host:");
            host = new JTextField("vcac152-011-136.eng.vmware.com");
            add hostLbl
            add host

            JLabel userNameLbl = new JLabel("Username:");
            username = new JTextField("root");
            add userNameLbl
            add username

            JLabel passwordLbl = new JLabel("Password:");
            password = new JPasswordField("VMware1!");
            add passwordLbl
            add password

            JLabel pathLbl = new JLabel("Path:");
            path = new JTextField("/var/log/vcac/catalina.out");
            add pathLbl
            add path

            JButton open = new JButton("Open")
            open.addActionListener(new ActionListener() {
                @Override
                void actionPerformed(ActionEvent e) {

                    if (!checkValues()) {
                        JOptionPane.showMessageDialog(RemoteFileOpener.this, "Some of the fields were not specified", "Not enough input", JOptionPane.WARNING_MESSAGE)
                        return;
                    }

                    def connection = new SSHConnection(host.getText().trim(), username.getText().trim(), password.getText())

                    try {
                        connection.Connect()
                        println "Connected"
                    } catch (Exception ex) {
                        println "Failed to Connect"
                        println ex
                    }

                    java.util.Queue<String> inputQueue = new LinkedBlockingQueue<String>()
                    Log log = new Log()
                    InputParser parser = new InputParser(inputQueue, log)

                    String path = path.getText().trim()
                    log.SetFileName(host.getText().trim() + "->" + path)
                    connection.Connect().Execute("lines=`wc -l ${path} | grep -Eo '^[0-9]*\\s*'`; tail -f -n \$lines ${path}", inputQueue);
                    parser.start()

                    pullThePlug()
                    mainWindow.addLog(log)
                }
            })
            add open
        }

        private boolean checkValues() {
            boolean isCorrect = true
            isCorrect &= !isTextValueEmpty(host)
            isCorrect &= !isTextValueEmpty(username)
            isCorrect &= !isTextValueEmpty(password)
            isCorrect &= !isTextValueEmpty(path)

            return isCorrect
        }

        private isTextValueEmpty(JTextField field) {
            return field.getText() == null && !"".equals(field.getText())
        }
    }
}