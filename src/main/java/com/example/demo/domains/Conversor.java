package com.example.demo.domains;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.web.multipart.MultipartFile;

public class Conversor {
	
	public static List<String> pdfToImage(MultipartFile arquivo) {
		List<String> arquivos = new ArrayList<>();
		
		try {			
			String extension = FilenameUtils.getExtension(arquivo.getOriginalFilename());			
			
			if (extension.equals("pdf")) {
				BufferedInputStream bufferedInput = new BufferedInputStream(arquivo.getInputStream());             
				PDDocument documento = PDDocument.load(bufferedInput);
				
				@SuppressWarnings("unchecked")
				List<PDPage> paginas = documento.getDocumentCatalog().getAllPages();
				
				int pageNumber = 1;				
				for (PDPage page : paginas) {
					BufferedImage image = page.convertToImage();
					
					String destinationDir = "arquivos/";
					
					String fileName = arquivo.getOriginalFilename().replace(".pdf", "");
					File outputfile = new File(destinationDir + fileName +"("+ pageNumber + ")"+".png");					
					ImageIO.write(image, "png", outputfile);
					
					arquivos.add(outputfile.getAbsolutePath());
					
					pageNumber++;
				}
				
				documento.close();
			} else {
				throw new FileNotFoundException("Wrong format!");
			}
			
		} catch (IOException e) {
			throw new Error(e);
		}
		
		return arquivos;
	}
	
}
