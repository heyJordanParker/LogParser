package ui

import javax.swing.*

/**
 * The log parser app starts here
 *
 * @author Leni Kirilov
 * @date 6/25/2014
 */
class Main {
    public static void main(String[] args) {
        LogParserWindow window = new LogParserWindow()
        window.setSize(400, 400)
        window.visible = true
        window.locationRelativeTo = null
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }
}