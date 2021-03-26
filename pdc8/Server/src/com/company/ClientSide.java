package com.company;

import com.company.TextAnalysis.Folder;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientSide {

    public static void main(String[] args)
    {
        boolean clientSide = true;
        try {
            Socket socket = new Socket("127.0.0.1",9999);


            ArrayList<Double> response;
            try {
                //send request

                // get the output stream from the socket.
                OutputStream outputStream = socket.getOutputStream();
                // create an object output stream from the output stream so we can send an object through it
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                if(clientSide){
                    Folder request = Folder.fromDirectory(new File("C:\\Users\\Oleksandra\\Desktop\\PDC\\PDC\\pdc8\\Server\\books"));
                    objectOutputStream.writeObject(request);
                } else{
                    String request2 = "get books";
                    objectOutputStream.writeObject(request2);
                }

                long m = System.currentTimeMillis();

                //get answer
                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream()); //Error Line!
                try {
                    Object object = objectInput.readObject();
                    response =  (ArrayList<Double>) object;
                    System.out.println("time: " + (double) (System.currentTimeMillis() - m));
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