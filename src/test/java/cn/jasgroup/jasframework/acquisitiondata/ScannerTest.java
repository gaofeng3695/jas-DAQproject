package cn.jasgroup.jasframework.acquisitiondata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.w3c.dom.Node;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.PictureRenderData;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.microsoft.schemas.vml.CTGroup;
import com.microsoft.schemas.vml.CTShape;

import cn.jasgroup.jasframework.utils.DateTimeUtil;


public class ScannerTest {

	// @Test
	public void scannerTest() {
		String filePath = "D:\\二维码";
		String fileName = System.currentTimeMillis() + ".png";
		String contents = "RW;ZEGD_PROJECT;ZEHOTBENDS08;17209621;106.000*21.000;X42M;14.120;2.000;SAWH;30.000;沙市钢管分公司;3LPE+;2018-11-06";
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			Path path = new File(filePath + File.separator + fileName).toPath();
			int width = 300, height = 300;
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, new Integer(0));
			BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
			MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void wordTest() {
		try {
			XWPFDocument doc = new XWPFDocument(); // 创建word文件
			// XWPFParagraph p1 = doc.createParagraph(); //创建段落
			// XWPFRun r1 = p1.createRun(); //创建段落文本
			// r1.setText("hello world"); //设置文本
			XWPFParagraph paragraph = doc.createParagraph();
			XWPFRun run = paragraph.createRun();
			 /*CTGroup ctGroup = CTGroup.Factory.newInstance();
			
			 CTShape ctShape = ctGroup.addNewShape();
			 ctShape.setStyle("width:100pt;height:24pt");
			 CTTxbxContent ctTxbxContent =
			 ctShape.addNewTextbox().addNewTxbxContent();
			 ctTxbxContent.addNewP().addNewR().addNewT().setStringValue("TheTextBox text...");
			 Node ctGroupNode = ctGroup.getDomNode();
			 CTPicture ctPicture = CTPicture.Factory.parse(ctGroupNode);
			 run = paragraph.createRun();
			 CTR cTR = run.getCTR();
			 cTR.addNewPict();
			 cTR.setPictArray(0, ctPicture);*/

			for (int i = 0; i < 5; i++) {
				CTGroup ctGroup = CTGroup.Factory.newInstance();

				CTShape ctShape = ctGroup.addNewShape();
				ctShape.setStyle("width:100pt;height:120pt;font-size:9pt");

				CTTxbxContent ctTxbxContent = ctShape.addNewTextbox().addNewTxbxContent();
				ctTxbxContent.addNewP().addNewR().addNewT().setStringValue("The TextBox text1...");
				
				
				CTPPr ppr = ctTxbxContent.addNewP().addNewPPr();
				CTSpacing ctSpacing = ppr.getSpacing() != null ? ppr.getSpacing() : ppr.addNewSpacing();
				ctSpacing.setLine(BigInteger.valueOf(12*20));
				ctSpacing.setLineRule(STLineSpacingRule.EXACT);

				Node ctGroupNode = ctGroup.getDomNode();
				CTPicture ctPicture = CTPicture.Factory.parse(ctGroupNode);
				run = paragraph.createRun();
				CTR cTR = run.getCTR();
				cTR.addNewPict();
				cTR.setPictArray(0, ctPicture);
			}
			/*
			 * CTPPr ctpPr; if (paragraph.getCTP() != null) { if
			 * (paragraph.getCTP().getPPr() != null) { ctpPr =
			 * paragraph.getCTP().getPPr(); } else { ctpPr =
			 * paragraph.getCTP().addNewPPr(); } CTSpacing
			 * ctSpacing=ctpPr.getSpacing()!=null?
			 * ctpPr.getSpacing():ctpPr.addNewSpacing(); //
			 * ctSpacing.setBefore(BigInteger.valueOf((long) (12))); //
			 * ctSpacing.setAfter(BigInteger.valueOf((long) (12)));
			 * ctSpacing.setLine(BigInteger.valueOf(12*20));
			 * ctSpacing.setLineRule(STLineSpacingRule.EXACT); }
			 */
			// run.setFontSize(9);
			// run.setText("RW;ZEGD_PROJECT;ZEHOTBENDS08;17209621;106.000*21.000;X42M;14.120;2.000;SAWH;30.000;沙市钢管分公司;3LPE+;2018-11-06");

//			CTSdtContentBlock ctSdtContentBlock = doc.getDocument().getBody().addNewSdt().addNewSdtContent();
//
//			for(int i=0;i<3;i++){
//				CTP ctP = ctSdtContentBlock.addNewP();
//				ctP.addNewR().addNewT().setStringValue("The TextFrame text...");
//
//				CTPPr ctPPr = ctP.addNewPPr();
//				CTSpacing ctSpacing = ctPPr.getSpacing()!=null?ctPPr.getSpacing():ctPPr.addNewSpacing();
//				ctSpacing.setLine(BigInteger.valueOf(12*20));
//				ctSpacing.setLineRule(STLineSpacingRule.EXACT);
//			}



			FileOutputStream out = new FileOutputStream("D:\\二维码\\test.docx"); // 创建输出流
			doc.write(out); // 输出
			out.close(); // 关闭输出流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void dda(){
		
		try {
			StoryData data = new StoryData();
			data.setTitleName("直管");
			
			List<SegmentData> segments = new ArrayList<SegmentData>();
			List<SegmentData> segments2 = new ArrayList<SegmentData>();
			SegmentData s1 = new SegmentData();
			s1.setProjectName("ZEGD_PROJECT");
			s1.setPipeCode("ZEHOTBENDS01");
			s1.setDiameter("106.000*21.000,Gr.A,SAWL");
			s1.setPipeLength("3.000");
			s1.setPipeWeight("3.000");
			s1.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments.add(s1);
			
			SegmentData s2 = new SegmentData();
			s2.setProjectName("ZEGD_PROJECT");
			s2.setPipeCode("ZEHOTBENDS02");
			s2.setDiameter("106.000*21.000,Gr.BM,HFW");
			s2.setPipeLength("4.000");
			s2.setPipeWeight("4.000");
			s2.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments2.add(s2);
			
			SegmentData s3 = new SegmentData();
			s3.setProjectName("ZEGD_PROJECT");
			s3.setPipeCode("ZEHOTBENDS03");
			s3.setDiameter("106.000*21.000,Gr.A,SMLS");
			s3.setPipeLength("5.000");
			s3.setPipeWeight("5.000");
			s3.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments.add(s3);
			
			SegmentData s4 = new SegmentData();
			s4.setProjectName("ZEGD_PROJECT");
			s4.setPipeCode("ZEHOTBENDS04");
			s4.setDiameter("106.000*21.000,Gr.BM,SAWH");
			s4.setPipeLength("6.000");
			s4.setPipeWeight("6.000");
			s4.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments2.add(s4);
			
			SegmentData s5 = new SegmentData();
			s5.setProjectName("ZEGD_PROJECT");
			s5.setPipeCode("ZEHOTBENDS05");
			s5.setDiameter("106.000*21.000,Gr.A,SMLS");
			s5.setPipeLength("5.000");
			s5.setPipeWeight("5.000");
			s5.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments.add(s5);
			
			SegmentData s6 = new SegmentData();
			s6.setProjectName("ZEGD_PROJECT");
			s6.setPipeCode("ZEHOTBENDS04");
			s6.setDiameter("106.000*21.000,Gr.BM,SAWH");
			s6.setPipeLength("6.000");
			s6.setPipeWeight("6.000");
			s6.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments2.add(s6);
			
			SegmentData s7 = new SegmentData();
			s7.setProjectName("ZEGD_PROJECT");
			s7.setPipeCode("ZEHOTBENDS05");
			s7.setDiameter("106.000*21.000,Gr.A,SMLS");
			s7.setPipeLength("5.000");
			s7.setPipeWeight("5.000");
			s7.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments.add(s7);
			
			SegmentData s8 = new SegmentData();
			s8.setProjectName("ZEGD_PROJECT");
			s8.setPipeCode("ZEHOTBENDS04");
			s8.setDiameter("106.000*21.000,Gr.BM,SAWH");
			s8.setPipeLength("6.000");
			s8.setPipeWeight("6.000");
			s8.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments2.add(s8);
			
			SegmentData s9 = new SegmentData();
			s9.setProjectName("ZEGD_PROJECT");
			s9.setPipeCode("ZEHOTBENDS05");
			s9.setDiameter("106.000*21.000,Gr.A,SMLS");
			s9.setPipeLength("5.000");
			s9.setPipeWeight("5.000");
			s9.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments.add(s9);
			
			SegmentData s10 = new SegmentData();
			s10.setProjectName("ZEGD_PROJECT");
			s10.setPipeCode("ZEHOTBENDS04");
			s10.setDiameter("106.000*21.000,Gr.BM,SAWH");
			s10.setPipeLength("6.000");
			s10.setPipeWeight("6.000");
			s10.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments2.add(s10);
			
			SegmentData s11 = new SegmentData();
			s11.setProjectName("ZEGD_PROJECT");
			s11.setPipeCode("ZEHOTBENDS05");
			s11.setDiameter("106.000*21.000,Gr.A,SMLS");
			s11.setPipeLength("5.000");
			s11.setPipeWeight("5.000");
			s11.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments.add(s11);
			
			SegmentData s12 = new SegmentData();
			s12.setProjectName("ZEGD_PROJECT");
			s12.setPipeCode("ZEHOTBENDS04");
			s12.setDiameter("106.000*21.000,Gr.BM,SAWH");
			s12.setPipeLength("6.000");
			s12.setPipeWeight("6.000");
			s12.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments2.add(s12);
			
			SegmentData s13 = new SegmentData();
			s13.setProjectName("ZEGD_PROJECT");
			s13.setPipeCode("ZEHOTBENDS05");
			s13.setDiameter("106.000*21.000,Gr.A,SMLS");
			s13.setPipeLength("5.000");
			s13.setPipeWeight("5.000");
			s13.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
			segments.add(s13);
			
//			SegmentData s12 = new SegmentData();
//			s12.setProjectName("ZEGD_PROJECT");
//			s12.setPipeCode("ZEHOTBENDS04");
//			s12.setDiameter("106.000*21.000,Gr.BM,SAWH");
//			s12.setPipeLength("6.000");
//			s12.setPipeWeight("6.000");
//			s12.setPicture(new PictureRenderData(130, 130, "D:/二维码/1542008414769.png"));
//			segments2.add(s12);
			
			
			DocxRenderData segment = new DocxRenderData(new File("D:/二维码/segment.docx"), segments );
			DocxRenderData segment2 = new DocxRenderData(new File("D:/二维码/segment.docx"), segments2 );
			data.setSegment(segment);
			data.setSegment2(segment2);

			XWPFTemplate template = XWPFTemplate.compile("D:/二维码/story.docx").render(data);

			FileOutputStream out = new FileOutputStream("D:/二维码/out_story.docx");
			template.write(out);
			out.flush();
			out.close();
			template.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
//	@Test
	public void sqlParser(){
		DecimalFormat df = new DecimalFormat("#.000");
		NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(3);
        System.out.println(nf.format(0.001001));
		System.err.println(df.format(0.001001));
	}
	@Test
	public void testShareFile(){
//		try{
//			String sFilePath = "D:/test.png";
//			String folderPath = "//192.168.40.113/123123/1";
//			if(!(new File(folderPath)).exists()){
//				new File(folderPath).mkdirs();
//			}
//			String filePath = folderPath+"/test.png";
//			
//			InputStream is = new FileInputStream(new File(sFilePath));
//			OutputStream os = new FileOutputStream(new File(filePath));
//			int i = 0;
//			byte[] buffer = new byte[1024];
//			i = is.read(buffer);
//			while (i != -1) {
//				os.write(buffer, 0, i);
//				i = is.read(buffer);
//			}
//			is.close();
//			os.flush();
//			os.close();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		boolean flag = false;
		String folderPath = "E:/test1234";
		if(!new File(folderPath).exists()){
			flag = new File(folderPath).mkdirs();
		}
		System.err.println(flag);
	}
}
