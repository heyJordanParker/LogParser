package SSHReader

import com.jcraft.jsch.JSch

/**
 * Created by Jedy on 6/25/2014.
 */
class SSHTest {

    public static void main(String[] args) {
        String sshHost = 'vcac148-084-036.eng.vmware.com'
        String sshUser = 'root'
        String sshPassword = 'VMware1!'
        Integer sshPort = 22;

        JSch jSch = new JSch()

        Properties config = new Properties()
        config.put("StrictHostKeyChecking", "no")

        def sshSession = jSch.getSession(sshUser, sshHost, sshPort)
        sshSession.setPassword(sshPassword)
        sshSession.setConfig(config)
        println "Connected"
    }

}
