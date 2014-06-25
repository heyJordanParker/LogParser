package SSHReader

import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session

import java.nio.channels.Channel
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.LinkedBlockingQueue

/**
 * Created by Jedy on 6/25/2014.
 */
class SSHTest {

    public static void main(String[] args) {
        String sshHost = 'vcac152-011-136.eng.vmware.com'
        String sshUser = 'root'
        String sshPassword = 'VMware1!'

        String file = "/var/log/vcac/catalina.out"

        def connection = new SSHConnection(sshHost, sshUser, sshPassword)

        try {
            connection.Connect()
            println "Connected"
        } catch (Exception e) {
            println e
        }

        Queue<String> tail = new LinkedBlockingQueue<String>();

        connection.Connect().Execute("lines=`wc -l ${file} | grep -Eo '^[0-9]*\\s*'`; tail -f -n \$lines ${file}", tail);

        Thread.start {
            while (true)
                if(tail.peek() != null)
                    println tail.take()
                Thread.sleep(20)
        }


    }



}
