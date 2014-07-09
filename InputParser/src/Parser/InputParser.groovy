package Parser

import Log.Log

import javax.swing.text.html.parser.Entity

/**
 * Created by Jedy on 6/25/2014.
 */
public class InputParser extends Thread {

    private Queue<String> _input
    private boolean _running
    private Log _log

    public InputParser(Queue<String> input, Log log) {
        _input = input
        _log = log
    }

    @Override
    void run() throws Exception {
        if(_log == null) {
            throw Exception("Log is not defined. LogParser is exiting")
            return
        }
        _running = true
        while (_running) {
            if(_input.peek() != null)
                _log.AddEntity(_input.take())
            Thread.sleep(20)
        }
    }

    public void Stop() {
        _running = false
    }

}
