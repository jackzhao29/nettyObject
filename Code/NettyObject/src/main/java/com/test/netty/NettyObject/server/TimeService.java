package com.test.netty.NettyObject.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeService {

	public static void main(String[] arg) throws Exception {
		int port = 8080;
		if (arg != null && arg.length > 0) {
			port = Integer.parseInt(arg[0]);
		}
		System.out.println(port);
		new TimeService().bing(port);
	}

	public void bing(int port) throws Exception {
		// 配置服务端的NIO线程组
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boosGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChildchannelHandler());
			// 绑定端口，同步等待成功
			ChannelFuture future = bootstrap.bind(port).sync();
		} catch (Exception ex) {

		} finally {
			// 优雅退出，释放线程池资源
			boosGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
