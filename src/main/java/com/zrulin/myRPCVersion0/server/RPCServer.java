package com.zrulin.myRPCVersion0.server;

import com.zrulin.myRPCVersion0.pojo.User;
import com.zrulin.myRPCVersion0.service.UserService;
import com.zrulin.myRPCVersion0.service.impl.UserServiceImpl;
import lombok.experimental.StandardException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zrulin
 * @create 2022-05-03 22:06
 */
public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务器启动了。。。。。");
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(()->{
                    ObjectOutputStream oos = null;
                    ObjectInputStream ois = null;
                    try {
                        oos = new ObjectOutputStream(socket.getOutputStream());
                        ois = new ObjectInputStream(socket.getInputStream());

                        int id = ois.readInt();
                        User userById = userService.getUserById(id);

                        oos.writeObject(userById);
                        oos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            if(oos != null) oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            if(ois != null) ois.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
