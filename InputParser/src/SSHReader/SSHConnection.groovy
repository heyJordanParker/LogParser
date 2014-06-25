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

    public SSHConnection(String host, String user, String password, Integer port = 22) {
        _host = host
        _user = user
        _password = password
        _port = port
    }

    public SSHConnection Connect() throws Exception {
        _jSch = new JSch()

        Properties config = new Properties()
        config.put("StrictHostKeyChecking", "no")

        Session sshSession = _jSch.getSession(GetUser(), GetHost(), GetPort())
        sshSession.setPassword(GetPassword())
        sshSession.setConfig(config)

        sshSession.connect()

        _commandChannel = (ChannelExec) sshSession.openChannel("exec")

        return this
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
}
