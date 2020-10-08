package com.example.demo.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by 24345 on 2020/8/6.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private final ByteBuf message;

    public ClientHandler() {
        message = Unpooled.buffer(256);
        message.writeBytes("hello netty".getBytes(CharsetUtil.UTF_8));
    }

    /**
     * 这个函数是通道连接成功触发的
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //ctx.writeAndFlush(message);
    }

    /**
     * 这个函数是接收到消息触发的
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {


        System.out.println((String)msg);
        ctx.close();
        /*ctx.write(msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
