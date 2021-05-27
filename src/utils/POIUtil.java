package utils;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;


public class POIUtil {
	
	
	
	//時間序號
    private static String getTimeNumber() {
        String pattern = "yyyyMMddHHmmssSSS";
        SimpleDateFormat d = new SimpleDateFormat(pattern);
        return d.format(new Date());
    }

	public void createExcel(JSONObject jsonObj) {
		
		System.out.println("createExcel!!!");
		
//		System.out.println("jsonObj : " + jsonObj);
//		System.out.println("obj.name : " + jsonObj.get("filename"));
//		System.out.println("obj.titleObj : " + jsonObj.getJSONObject("title"));
//		System.out.println("obj.titleObj : " + jsonObj.getJSONObject("title").get("ttime"));
//		System.out.println("obj.titleObj : " + jsonObj.getJSONObject("title").get("event"));
//		System.out.println("obj.dataObj : " + jsonObj.getJSONArray("data"));
//		System.out.println("data length : " + jsonObj.getJSONArray("data").length());
//		for(int i = 0; i < jsonObj.getJSONArray("data").length(); i++) {
//			System.out.println("obj.dataObj  : " + i + " : " + jsonObj.getJSONArray("data").getJSONObject(i).get("訊息"));
//			System.out.println("obj.dataObj  : " + i + " : " + jsonObj.getJSONArray("data").getJSONObject(i).get("時間"));
//
//		}
		
//		String filename = (String)jsonObj.get("filename");
		//Object titleObj = jsonObj.getJSONObject("title");
		JSONObject titleJsonObj = jsonObj.getJSONObject("title");
		
		@SuppressWarnings("resource")
        // 新建工作簿
        XSSFWorkbook book = new XSSFWorkbook();
        // 建立工作表
        XSSFSheet sheet = book.createSheet("records");
        
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue( (String)titleJsonObj.get("ttime"));
        cell = row.createCell(1);
        cell.setCellValue( (String)titleJsonObj.get("event"));
        
//        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//       	Date date = new Date();

        int length =  jsonObj.getJSONArray("data").length();
        for(int i = 1; i < length; i++) {
        	JSONObject dataJsonObj = jsonObj.getJSONArray("data").getJSONObject(i);
        	
        	row = sheet.createRow(i);
        	cell = row.createCell(0);
        	cell.setCellValue((String)dataJsonObj.get("時間"));
        	
        	System.out.println("時間: " + (String)dataJsonObj.get("時間"));
        	cell = row.createCell(1);
        	cell.setCellValue((String) dataJsonObj.get("訊息"));
        	System.out.println("訊息 : " + (String) dataJsonObj.get("訊息"));
		}
        
        // 指定檔案名稱
        String fileName = "D:\\Temp\\%1$s.xlsx";
        //String fileName = "JavaBooks_%1$s.xlsx";
        fileName = String.format(fileName, getTimeNumber());
        System.out.println("fileName : " + fileName);
        /*
         * 尚未指定檔案路徑，檔案建立在本執行專案內
         * 儲存工作簿
         * */
        try (FileOutputStream os = new FileOutputStream(fileName)) {
            book.write(os);
            System.out.println(fileName + " excel export finish.");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
