package com.example.demo.domains;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Leitor {

	public static String imageToText(MultipartFile arquivo) {
		List<String> pathsArquivos = Conversor.pdfToImage(arquivo);
		
		String texto = "";
		for (String pathArquivo : pathsArquivos) {
			File imageFile = new File(pathArquivo);
			ITesseract instance = new Tesseract();
			instance.setDatapath("tessdata");
			instance.setLanguage("por");
			try {
				String result = instance.doOCR(imageFile);
				texto += result;
			} catch (TesseractException e) {
				throw new Error(e);
			}			
		}
		
		return texto;
	}
	
}