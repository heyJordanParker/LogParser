package SSHReader

import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session

import java.nio.channels.Channel

/**
 * Created by Jedy on 6/25/2014.
 */
class SSHTest {

    public static void main(String[] args) {
        String sshHost = 'vcac152-011-136.eng.vmware.com'
        String sshUser = 'root'
        String sshPassword = 'VMware1!'

        def connection = new SSHConnection(sshHost, sshUser, sshPassword)

        try {
            connection.Connect()
            println "Connected"
        } catch (Exception e) {
            println e
            return
        }



    }

}
