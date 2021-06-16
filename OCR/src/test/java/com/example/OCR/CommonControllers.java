package com.example.OCR;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;
@Controller
public class CommonControllers {
	
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public String home() {
		System.out.println("ocr");
		ITesseract tr= new Tesseract();
		tr.setLanguage("kr");
		BufferedImage img;
		
		try {
			img = ImageIO.read(CommonController.class.getResource("C:\\Users\\Furious\\Desktop\\tess\\01-20080000081139-01_1.0.pdf"));
			for ( Word word : tr.getWords(img, ITessAPI.TessPageIteratorLevel.RIL_TEXTLINE)) {
				System.out.println("ocr 시작");
				if(word.getText().contains("제목")) {
					Rectangle boundingBox=word.getBoundingBox();
					System.out.println(boundingBox);
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "index";
	}
}
