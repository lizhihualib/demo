package com.example.demo.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by 24345 on 2020/8/6.
 */
public class MyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //ctx.writeAndFlush(message);
        System.out.println(11);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("客户端发来的消息：" + (String)msg);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String resp = "我收到你的消息了！11223344";
            ByteBuf message = Unpooled.buffer(256);
            message.writeBytes(resp.getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(resp);
            System.out.println("send end!");
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
