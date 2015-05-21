package com.linda.fileupdown;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileUploadManager extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File file;
	private String fileContentType;
	private String fileFileName;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	@Override
	public String toString() {
		return "FileUploadManager [file=" + file + ", fileContentType="
				+ fileContentType + ", fileFileName=" + fileFileName + "]";
	}

	@Override
	public String execute() throws Exception {
		System.out.println(file);
		System.out.println(fileContentType);
		System.out.println(fileFileName);

		ServletContext servletContext = ServletActionContext
				.getServletContext();
		String dir = servletContext.getRealPath("/files/" + fileFileName);

		System.out.println("path is: " + dir);

		FileOutputStream out = new FileOutputStream(dir);
		FileInputStream in = new FileInputStream(file);

		byte[] buffer = new byte[1024];
		int len = 0;

		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}

		out.close();
		in.close();

		return super.execute();
	}

}
