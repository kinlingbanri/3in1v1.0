package utils.netty;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Pipeline;

import com.mem.model.MemService;
import com.mem.model.MemVO;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
	
	private MemService memService = new MemService();

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
		
		char[] chars = msg.toCharArray();
	    StringBuffer hex = new StringBuffer();
	    for (int i = 0; i < chars.length; i++)
	    {
	        hex.append(Integer.toHexString((int) chars[i]));
	    }
	    System.out.println("ascii to Hex : " + hex.toString());
		
//		MemVO memVO = memService.getOneMem("Van007");
//		memVO.setPoint(1333);
//		memService.updateMem(memVO.getUsername(), memVO.getEmail(), memVO.getPassword(), memVO.getPoint());
//		System.out.println("Update DB Success!!");
		
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
