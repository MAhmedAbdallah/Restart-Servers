/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restart;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 3amerjr
 */
public class Test {

    public static void main(final String... args) {
        final ExecutorService es = Executors.newFixedThreadPool(20);
        final String ip = "127.0.0.1";
        final int timeout = 200;

        System.out.println(
                portIsOpen(es, ip, 8091, timeout));
    }

    public static Boolean portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
        //     return es.submit(new Callable<Boolean>() {

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
