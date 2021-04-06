package utils.singlenetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

	private final int port;
	
	public NettyServer(int port) {
		this.port = port;
	}
	
	public void run() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap()
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ServerInitializer());
			
			bootstrap.bind(port).sync().channel().closeFuture().sync();
			
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new NettyServer(6000).run();
	}

}
