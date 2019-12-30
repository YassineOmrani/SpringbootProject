package com.yassine.app.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImagesController {
	
	@RequestMapping(value = "image/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

	    File serverFile = new File("C:/Users/DellXPS/Desktop/Projects/SpringbootProject/uploads/" + imageName );

	    return Files.readAllBytes(serverFile.toPath());
	}
}
