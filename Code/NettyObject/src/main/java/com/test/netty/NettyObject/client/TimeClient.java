package com.test.netty.NettyObject.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
	
	public static void main(String[] arg0) throws Exception{
		int port=8080;
		if(arg0!=null && arg0.length>0){
			port=Integer.parseInt(arg0[0]);
		}
		System.out.println(port);
		new TimeClient().connect(port, "127.0.0.1");
	}
	
	public void connect(int port,String host) throws Exception{
		//配置客户端NIO线程组
		EventLoopGroup group=new NioEventLoopGroup();
		try{
			Bootstrap bootstrap=new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>(){
				@Override
				public void initChannel(SocketChannel ch) throws Exception{
					ch.pipeline().addLast(new TimeClientHandler());
				}
			});
			//发起异步链接操作
			ChannelFuture f=bootstrap.connect(host, port).sync();
			//等待客户端链路关闭
			f.channel().closeFuture().sync();
		}catch(Exception ex){
			
		}finally{
			//优雅退出，释放线程组资源
			group.shutdownGracefully();
		}
	}

}
