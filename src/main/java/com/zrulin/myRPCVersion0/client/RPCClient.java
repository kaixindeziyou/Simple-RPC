package com.zrulin.myRPCVersion0.client;

import com.zrulin.myRPCVersion0.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

/**
 * @author zrulin
 * @create 2022-05-03 21:56
 */
public class RPCClient {
    public static void main(String[] args) {

        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            socket = new Socket(ip,8899);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeInt(new Random().nextInt());
            objectOutputStream.flush();

            User user = (User) objectInputStream.readObject();
            System.out.println("获取到的对象为：" + user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(objectInputStream != null) objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(objectOutputStream != null) objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
