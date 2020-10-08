package com.example.demo.netty;

import com.example.demo.netty.handler.MyHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @program: qingcheng
 * @author: XIONG CHUAN
 * @create: 2019-04-28 15:10
 * @description: 服务端初始化，客户端与服务器端连接一旦创建，这个类中方法就会被回调，设置出站编码器和入站解码器
 **/

public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                0, 4,0,4));
        channel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
        channel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));



        //channel.pipeline().addLast(new NettyServerHandler());
        channel.pipeline().addLast("handler",new MyHandler());
        System.out.println("字处理器");
    }
}
