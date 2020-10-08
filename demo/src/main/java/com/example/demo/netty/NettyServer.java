package com.example.demo.netty;

import com.example.demo.netty.handler.MyHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.util.ClassUtils;

import java.net.InetSocketAddress;

/**
 * Created by 24345 on 2020/7/21.
 */
public class NettyServer {

    public static void main(String[] args) {
        /*NettyServer bb = new NettyServer();
        //ClassLoader classLoader = getClassLoader();
        System.out.println(ClassUtils.getDefaultClassLoader());*/
        int a = 12;
        ByteBuf buf = Unpooled.buffer();
        String resp = "14lizhhua";
        buf.writeBytes(resp.getBytes(CharsetUtil.UTF_8));

        //buf.writeInt(15);
    }
    public static void start (){
        /*
            1.创建两个线程组BossGroup 和 WorkerGroup
            2.BossGroup只处理连接请求
            3.WorkerGroup处理读写逻辑
            4.两个都是无限循环
         */
        System.out.println("Netty Server start!");
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务端的启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //设置参数:两个线程组
            serverBootstrap.group(bossGroup,workerGroup);
            //设置参数:使用NioSocketChannel作为服务器的通道实现
            serverBootstrap.channel(NioServerSocketChannel.class);
            //设置参数:设置线程队列等待连接的个数,如果队列已满，连接将被拒绝
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            //设置参数:设置保持活动连接状态，2小时无数据交互激活心跳机制
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            //设置参数：给WorkerGroup的EventLoop的管道设置设置处理器
            serverBootstrap.childHandler(new NettyServerChannelInitializer());
            // 启动server，并且设置8088为启动的端口号，同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            System.out.println("Netty Server end1!");
            // 监听关闭的channel，设置位同步方式
            channelFuture.channel().closeFuture().sync();
            System.out.println("Netty Server end2!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //退出线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
