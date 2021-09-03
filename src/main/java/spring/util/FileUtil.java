package spring.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import spring.constant.GlobalConstant;
import spring.model.ImageProduct;

public class FileUtil {

	public static String uploadFile(MultipartFile multipartFile, HttpServletRequest request, String folder) {
		String fileName = renameFile(multipartFile.getOriginalFilename());
		if (!fileName.equals(GlobalConstant.EMPTY)) {
			try {
				String contextRoot = request.getServletContext().getRealPath(GlobalConstant.EMPTY);
				String dirUpload = contextRoot + GlobalConstant.DIR_UPLOAD + File.separator + folder;
				File file = new File(dirUpload);
				if (!file.exists()) {
					file.mkdirs();
				}
				String filePath = dirUpload + File.separator + fileName;
				multipartFile.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}

	// upload multiple file
	public static List<String> uploadMultipleFile(List<MultipartFile> multipartFileList, HttpServletRequest request,
			String folder) {
		List<String> fileNameList = new ArrayList<String>();
		if (multipartFileList.size() > 0) {
			for (MultipartFile multipartFile : multipartFileList) {
				String fileName = renameFile(multipartFile.getOriginalFilename());
				try {
					String contextRoot = request.getServletContext().getRealPath(GlobalConstant.EMPTY);
					String dirUpload = contextRoot + GlobalConstant.DIR_UPLOAD + File.separator + folder;
					File file = new File(dirUpload);
					if (!file.exists()) {
						file.mkdirs();
					}
					String filePath = dirUpload + File.separator + fileName;
					multipartFile.transferTo(new File(filePath));
					fileNameList.add(fileName);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileNameList;
	}

	public static String renameFile(String fileName) {
		if (!fileName.equals(GlobalConstant.EMPTY)) {
			StringBuilder bd = new StringBuilder();
			bd.append(FilenameUtils.getBaseName(fileName)).append("-").append(System.nanoTime()).append(".")
					.append(FilenameUtils.getExtension(fileName));
			return bd.toString();
		}
		return GlobalConstant.EMPTY;
	}

	public static void delFile(String fileName, HttpServletRequest request, String folder) {
		if (!fileName.equals(GlobalConstant.EMPTY)) {
			StringBuilder bd = new StringBuilder();
			bd.append(request.getServletContext().getRealPath(GlobalConstant.EMPTY)).append(GlobalConstant.DIR_UPLOAD)
					.append(File.separator).append(folder).append(File.separator).append(fileName);
			File file = new File(bd.toString());
			file.delete();
		}
	}

	public static void delMultipleFile(List<ImageProduct> pictureList, HttpServletRequest request, String folder) {
		if (pictureList.size() > 0) {
			for (ImageProduct picture : pictureList) {
				delFile(picture.getPicture(), request, folder);
			}
		}
	}

	// check đuôi file
	public static boolean checkFileExtension(String fileName) {
		String fileNameExtension = FilenameUtils.getExtension(fileName);
		for (String fileExtension : GlobalConstant.FILE_EXTENSION) {
			if (fileExtension.equals(fileNameExtension)) {
				return true;
			}
		}
		return false;
	}

}
