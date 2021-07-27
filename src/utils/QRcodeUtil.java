package utils;

import com.adminconfig.model.AdminConfigService;
import com.adminconfig.model.AdminConfigVO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class QRcodeUtil {

	
	public String UrlToBase64(String url, int size)  throws Exception  {
		 //String content = "http://211.21.93.171:8080/3in1/index.jsp?DID=TY00003&MACHID=WS00006";

	    String qrCode = crateQRCodeBase64(url, size);
	    System.out.println("qrCode : " + qrCode);
	    return qrCode;
	    
	    
	    
	    
//	  //2.通過zxing生成二維碼(儲存到本地圖片，支援以data url的形式體現)
//        QRCodeWriter writer = new QRCodeWriter();
//        
//        //引數1：二維碼資訊；引數2：圖片型別；引數3：寬度；引數4：長度
//        int qrcodeSize = 120;
//        BitMatrix bt = writer.encode(content, BarcodeFormat.QR_CODE, qrcodeSize, qrcodeSize);
//        
//        //儲存二維碼到本地
//        String contentPath = "C:\\Users\\USER\\eclipse-workspace\\3in1\\WebContent\\Admin4\\qrcode\\";
//        //String contentPath = "D:\\Temp\\";
//        String fileName = "樹林站後店加值機1號.png";
//        String fullPath = contentPath + fileName;
//        Path path = new File(fullPath).toPath();
//        MatrixToImageWriter.writeToPath(bt,"png",path);
        
        
	}
	
	/**
	 * 生成QR code base64 編碼
	 */
	public static String crateQRCodeBase64(String url, int size) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, size, size);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bufferedImage, "png", os);
            
            //新增圖片格式標識
            String base64 = new String("data:image/png;base64," + Base64.encode(os.toByteArray()));
            System.out.println("base64 : " + base64);
            return base64;
            //return new String("data:image/png;base64," + Base64.encode(os.toByteArray()));
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
        return null;
    }
}
