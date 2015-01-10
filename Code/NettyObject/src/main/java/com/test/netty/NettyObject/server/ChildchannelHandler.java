package com.test.netty.NettyObject.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;



public class ChildchannelHandler extends ChannelInitializer<SocketChannel> {
	@Override
	public void initChannel(SocketChannel arg0) throws Exception{
		arg0.pipeline().addLast(new TimeServiceHandler());
	}

}
