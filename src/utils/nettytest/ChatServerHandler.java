package utils.nettytest;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

	// List of connected client channels.
	static final List<Channel> channels = new ArrayList<Channel>();
 
	/*
	 * Whenever client connects to server through channel, add his channel to the
	 * list of channels.
	 */
	@Override
	public void channelActive(final ChannelHandlerContext ctx) {
		System.out.println("Client joined - " + ctx);
		channels.add(ctx.channel());
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("Server received - " + msg);
		for (Channel c : channels) {
			c.writeAndFlush("-> " + msg + '\n');
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.out.println("Closing connection for client - " + ctx);
		ctx.close();
	}

}
