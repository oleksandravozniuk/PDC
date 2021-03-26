package com.company;
import com.company.TextAnalysis.Folder;
import com.company.TextAnalysis.ITWords;
import com.company.TextAnalysis.WordCounter;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServerSide {

    public static void main(String[] args) throws IOException {
        boolean clientSide = true;
        WordCounter wordCounter = new WordCounter();
        Folder folder;
        try
        {
            ServerSocket myServerSocket = new ServerSocket(9999);
            System.out.println("Waiting for connections.....");
            Socket skt = myServerSocket.accept();
            System.out.println("Client is connected");

            //get request
            // get the input stream from the connected socket
            InputStream inputStream = skt.getInputStream();
            // create a DataInputStream so we can read data from it.
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            // read data from client
            if(clientSide){
                folder = (Folder)objectInputStream.readObject();
            } else{
                String message = (String)objectInputStream.readObject();
                System.out.println(message);
                folder = Folder.fromDirectory(new File("C:\\Users\\Oleksandra\\Desktop\\PDC\\PDC\\pdc8\\Server\\books"));
            }
            System.out.println("Data from client was received");

            List<Double> statistics =  wordCounter.countStatInParallel(folder);

            try
            {
                ObjectOutputStream objectOutput = new ObjectOutputStream(skt.getOutputStream());
                objectOutput.writeObject(statistics);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}