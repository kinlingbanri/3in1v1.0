package utils.singlenetty;

import java.util.ArrayList;
import java.util.List;

import com.mem.model.MemJDBCClass;
import com.mem.model.MemService;
import com.mem.model.MemVO;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
	
	static final List<Channel> channels = new ArrayList<Channel>();
	private MemJDBCClass memJDBCClass;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Server received - " + msg);
		
		//char[] chars = msg.toCharArray();
		char[] chars = ((String) msg).toCharArray();
	    StringBuffer hex = new StringBuffer();
	    for (int i = 0; i < chars.length; i++)
	    {
	        hex.append(Integer.toHexString((int) chars[i]));
	    }
	    System.out.println("ascii to Hex : " + hex.toString());
	    
//		// Query All
//		List<MemVO> list = memJDBCClass.getAll();
//		for (MemVO mem : list) {
//			System.out.print(mem.getUsername() + ",");
//			System.out.print(mem.getEmail() + ",");
//			System.out.print(mem.getPassword() + ",");
//			System.out.print(mem.getPoint());
//			System.out.println();
//		}		
		
//		MemVO memVO = memService.getOneMem("Van007");
//		memVO.setPoint(1333);
//		memService.updateMem(memVO.getUsername(), memVO.getEmail(), memVO.getPassword(), memVO.getPoint());
//		System.out.println("Update DB Success!!");
		
		for (Channel c : channels) {
			c.writeAndFlush("-> " + msg + '\n');
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
        ctx.close();
	}

}
