package com.app.ems.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.ems.entity.EmsFiles;
import com.app.ems.exceptions.MaxUploadLimitException;
import com.app.ems.repository.EmsFilesRepo;
import com.app.ems.util.FilesUtil;

@Service
public class FileUploadService {

	@Autowired
	private EmsFilesRepo emsFilesRepo;

	public void uploadImage(String path, MultipartFile[] multipartFiles) throws IOException, MaxUploadLimitException {
		List<EmsFiles> listOfImages = new ArrayList<>();
		if (multipartFiles.length > 15) {
			throw new MaxUploadLimitException("Maximum limit exceeded");
		}
		String fileName = null;
		for (MultipartFile multipartFile : multipartFiles) {
			fileName = multipartFile.getOriginalFilename();

//		String filePath = path + File.separator + fileName;
//		File file = new File(path);
//		if (!file.exists()) {
//			file.mkdir();
//		}
//
//		Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
			EmsFiles emsFiles = new EmsFiles();
			emsFiles.setFile_name(fileName);
			emsFiles.setBase64image(FilesUtil.compress(multipartFile.getBytes()));
			emsFiles.setType(multipartFile.getContentType());
			listOfImages.add(emsFiles);
		}
		emsFilesRepo.saveAll(listOfImages);
	}

	public byte[] downloadImage(String fileName) throws IOException, DataFormatException {
		Optional<EmsFiles> dbImage = emsFilesRepo.findByfileName(fileName);
		return FilesUtil.decompress(dbImage.get().getBase64image());
	}

}
