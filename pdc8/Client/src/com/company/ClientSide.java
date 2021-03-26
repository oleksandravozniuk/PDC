package com.company;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ClientSide {

    public static void main(String[] args)
    {
        boolean clientSide = true;
        try {
            Socket socket = new Socket("127.0.0.1",9999);
            Folder request = Folder.fromDirectory(new File("C:\\Users\\Oleksandra\\Desktop\\PDC\\PDC\\pdc4\\documents\\proga"));
            String request2 = "get books";
            ArrayList<Double> response;
            try {
                //send request

                // get the output stream from the socket.
                OutputStream outputStream = socket.getOutputStream();
                // create an object output stream from the output stream so we can send an object through it
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                if(clientSide){
                    objectOutputStream.writeObject(request);
                } else{
                    objectOutputStream.writeObject(request2);
                }

                //get answer
                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream()); //Error Line!
                try {
                    Object object = objectInput.readObject();
                    response =  (ArrayList<Double>) object;
                    for(var doc: response){
                        System.out.println(doc);
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("The title list has not come from the server");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println("The socket for reading the object has problem");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}