package com.example.demo.netty;

import com.example.demo.netty.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created by 24345 on 2020/8/6.
 */
public class NettyClient {
    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    //设置连接超时，就是客户端去连接服务端，这个时间内没有连接上，就会放弃连接，报超时错误
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                                    0, 4,0,4));
                            //p.addLast("frameEncoder", new LengthFieldPrepender(4));
                            p.addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
                            p.addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));

                            //读取超时 在设置时间内没有读取操作
                            //p.addLast(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS));
                            //写入超时 在设置时间内没有写入操作
                           // p.addLast(new WriteTimeoutHandler(3000,TimeUnit.MILLISECONDS));
                            p.addLast(new ClientHandler());
                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect("localhost", 8088).sync();
            String reqStr = "lizhihua李志华第三方士大夫士大夫 水电费水电费胜多负少水电费水电费是发送的f" +
                    "水电费水电费水电费第三方士大夫士大夫第三方士大夫士大夫" +
                    "sdfsdsdfsfsdfdsfsfsf";
            ByteBuf buf = Unpooled.buffer(reqStr.getBytes().length + 4);
            //手动拼接请求报文头，先写入一个
            buf.writeInt(reqStr.getBytes().length);
            buf.writeBytes(reqStr.getBytes("UTF-8"));
            //f.channel().writeAndFlush(Unpooled.copiedBuffer(reqStr.getBytes("UTF-8")));
            f.channel().writeAndFlush(buf);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
