package cn.jasgroup.jasframework.acquisitiondata.utils;

import java.io.File;
import java.nio.file.Path;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/***
 * 二维码生成类
  *<p>类描述：。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年11月22日 下午5:55:24。</p>
 */
public class ScannerUtils {

	public static String createScanner(String folderPath,String scannerContext){
		String fileName = System.currentTimeMillis() + ".png";
		try {
			File file = new File(folderPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			Path path = new File(folderPath + File.separator + fileName).toPath();
			int width = 300, height = 300;
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, new Integer(0));
			BitMatrix bitMatrix = qrCodeWriter.encode(scannerContext, BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
			return path.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
}
