package SSHReader

import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session

/**
 * Created by Jedy on 6/25/2014.
 */
class SSHConnection {

    private String _host
    private String _user
    private String _password
    private Integer _port = 22

    private JSch _jSch
    private ChannelExec _commandChannel
    private Session _session
    private Boolean _executing

    private int _exitCode = 0;

    public SSHConnection(String host, String user, String password, Integer port = 22) {
        _host = host
        _user = user
        _password = password
        _port = port
        _executing = false
    }

    public SSHConnection Connect() throws Exception {
        _jSch = new JSch()

        Properties config = new Properties()
        config.put("StrictHostKeyChecking", "no")

        _session = _jSch.getSession(GetUser(), GetHost(), GetPort())
        _session.setPassword(GetPassword())
        _session.setConfig(config)

        _session.connect()

        _commandChannel = (ChannelExec) _session.openChannel("exec")

        return this
    }

    public SSHConnection Disconnect() throws Exception {
        _exitCode = _commandChannel.getExitStatus()
        if (_commandChannel != null) _commandChannel.disconnect()
        if (_session != null) _session.disconnect()
        return this
    }

    public void Execute(String command) throws Exception {
        if(_executing) return
        _executing = true

        InputStream stream = _commandChannel.getInputStream()
        _commandChannel.setCommand(command)
        _commandChannel.connect()

        Thread.start {
            def reader = new BufferedReader(new InputStreamReader(stream))
            String line
            while ((line = reader.readLine()) != null && _executing) {
                println line
            }
        }
    }

    public void Stop(){
        _executing = false
    }

    String GetHost() {
        return _host
    }

    String GetUser() {
        return _user
    }

    String GetPassword() {
        return _password
    }

    Integer GetPort() {
        return _port
    }

    Integer GetExitCode() {
        return _exitCode
    }

}
