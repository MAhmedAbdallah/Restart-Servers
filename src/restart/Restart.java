/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 3amerjr
 */
public class Restart {

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int TCP_SERVER_PORT = 8091;
    private static boolean connected = false;
    static Socket s;

    public static void main(String[] args) {

        try {
            if (Restart.sendPingRequest(SERVER_ADDRESS,TCP_SERVER_PORT , 200)) {
                System.out.println("Server Is Running going to shut it down");
                manageServer("shutdown");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Restart.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Server has been shutdown");
                System.out.println("Server Is Down going to start it up");

                manageServer("startup");

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Restart.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Server has been started up");
                //Restart.task.cancel();
            } else {
                System.out.println("Server is down");
            }

            System.exit(0);
        }
        catch(Exception e){
            e.printStackTrace();
//        Timer timer = new Timer();
//        timer.schedule(task, 01, 5001);
        } 
    }

    /*   static TimerTask task = new TimerTask() {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            if (!hostAvailabilityCheck()) {

                try {
                    System.out.println("down");
                    manageServer("startup");

                    Thread.sleep(10000);
                    System.out.println("Server has been started up");

                    Restart.task.cancel();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Restart.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);

            }
            if (hostAvailabilityCheck()) {

                try {
                    System.out.println("Running");
                    manageServer("shutdown");
                    Thread.sleep(10000);
                    System.out.println("Server has been shutdown");
                    Restart.task.cancel();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Restart.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);

            }
        }
    };

    public static boolean hostAvailabilityCheck() {

        boolean available = true;
        try {
            if (connected == false) {

                s = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT);
                available = s.isConnected();
            }
        } catch (UnknownHostException e) { // unknown host 
            available = false;
            s = null;
        } catch (IOException e) { // io exception, service probably not running 
            available = false;
            s = null;
        } catch (NullPointerException e) {
            available = false;
            s = null;
        }

        return available;
    }
     */
    public static void manageServer(String command) {
        try {
            ProcessBuilder pb = new ProcessBuilder("/Volumes/Data/Programs/apache-tomcat-9.0.20/bin/" + command + ".sh");
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }
        } catch (IOException ex) {
            Logger.getLogger(Restart.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean sendPingRequest(String ipAddress, int port, int timeOut) {
        // boolean flag = true;
//        InetAddress geek = InetAddress.getByName(ipAddress);
//        InetSocketAddress s = new InetSocketAddress(geek, 8091);
//        //  boolean x = s.isUnresolved();
//        System.out.println("Sending Ping Request to " + ipAddress);
//        if (geek.isReachable(5000)) {
//            System.out.println("Host is reachable");
//
//        } else {
//            System.out.println("Sorry ! We can't reach to this host");
//            flag = false;
//        }
//        return flag;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ipAddress, port), 200);
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
