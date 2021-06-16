package com.example.OCR;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;
@Controller
public class CommonController {
	
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public String home() {
		ITesseract tr= new Tesseract();
		tr.setDatapath("C:/Users/Furious/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/OCR/src/main/java/com/example/OCR/tessdata");
		tr.setLanguage("kor");
		List<BufferedImage> imgs= new ArrayList<>();
		
		InputStream pdf = CommonController.class.getResourceAsStream("/01-20080000081136-01_1.0.pdf");
		
		
		try {
//			PDDocument pdfDoc = PDDocument.load(pdf);
//			PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);
//			
//			for (int i=0; i<pdfDoc.getPages().getCount(); i++) {
//				imgs.add(pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB));
//			}
//			pdfDoc.close();
			imgs.add(ImageIO.read(CommonController.class.getResourceAsStream("/KakaoTalk_20210228_040853868.jpg")));
			
			int page=1;
			for (BufferedImage img : imgs) {
				Graphics2D g2d = img.createGraphics();
				for ( Word word : tr.getWords(img, ITessAPI.TessPageIteratorLevel.RIL_TEXTLINE)) {
					System.out.println(word);
					Rectangle boundingBox=word.getBoundingBox();
					//g2d.setColor(Color.GREEN);
					//g2d.setStroke(new BasicStroke(10));
					//g2d.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
//					if(word.getText().contains("제목")) {
//						Rectangle boundingBox=word.getBoundingBox();
//						g2d.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
//						System.out.println(boundingBox);
//						break;
//					}
				
			}
				//ImageIOUtil.writeImage(img, Integer.toString(page)+".png", 300);
				//g2d.dispose();
				++page;
			}
			imgs.clear();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "index";
	}
}
