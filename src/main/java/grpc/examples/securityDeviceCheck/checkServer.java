package grpc.examples.securityDeviceCheck;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

//import the grpc package for the server
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import grpc.examples.securityDeviceCheck.userGrpc.userImplBase;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class checkServer extends userImplBase {

    private static final Logger logger = Logger.getLogger(checkServer.class.getName());

    public static void main(String[] args) {
        int port = 50056;

        try {
            // Create a JmDNS instance firstly.
            JmDNS jmdns = JmDNS.create();
            // Declare serviceInfo with information.
            ServiceInfo serviceInfo = ServiceInfo.create("_check._tcp.local.", "checkDevice", port,
                    "checkserver");
            jmdns.registerService(serviceInfo);
            
            System.out.println("Security devices checking Server is being loaded!");
        } catch (IOException e) {
            System.err.println("Error detected while registering service: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error detected: " + e.getMessage());
            System.exit(1);
        }
        


        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(new checkServer())
                    .build()
                    .start();
            System.out.println("Security devices checking Server is being started!");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Quitting the server...");
                server.shutdown();
            }));

            server.awaitTermination();
        } catch (IOException e) {
            System.err.println("Error occurred while starting server: " + e.getMessage());
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println("Server interrupted: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error detected: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void deviceCheck(deviceRequest request, StreamObserver<deviceList> responseObserver) {
        System.out.println("Device check request is received!");

        try {
            String[] deviceArray = request.getMassData().split(",");

            for (int i = 0; i < deviceArray.length; i++) {
                responseObserver.onNext(deviceList.newBuilder().setVal(deviceArray[i]).build());
            }

            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void printOut(printRequest request, StreamObserver<printResponse> responseObserver) {
        System.out.println("Print request is received..");

        try {
            String confirm = "The process is successfully completed!";

            printResponse reply = printResponse.newBuilder().setComfirm(confirm).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

}
