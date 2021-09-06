package com.example.demo.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domains.Leitor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LeitorController {

	@RequestMapping(value="/lerImagem", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> read(@RequestParam("arquivo") MultipartFile arquivo) {
		String texto = Leitor.imageToText(arquivo);		
		return ResponseEntity.ok(texto);
	}
}
