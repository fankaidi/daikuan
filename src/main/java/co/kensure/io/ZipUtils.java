/*
 * 文件名称: MapUtils.java
 * 版权信息: Copyright 2015-2017 jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-5-8
 * 修改内容: 
 */
package co.kensure.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;

import co.kensure.exception.BusinessExceptionUtil;

/**
 * zip压缩类
 * 
 * @author fankd
 * @since SHK FileUtils 1.0
 */
public final class ZipUtils {

	/**
	 * 将存放在sourceDirPath目录下的源文件，打包成fileName名称的zip文件，并存放到zipDirPath路径下
	 * 
	 * @param sourceDirPath
	 *            :待压缩的文件路径
	 * @param zipDirPath
	 *            :压缩后存放路径
	 * @param fileName
	 *            :压缩后文件的名称，不要带.zip
	 * @return
	 */
	public static void fileToZip(String sourceDirPath, String zipDirPath, String fileName) {
		File sourceDir = new File(sourceDirPath);
		if (sourceDir.exists() == false) {
			throw new RuntimeException("待压缩的文件目录：" + sourceDirPath + "不存在.");
		}

		File[] sourceFiles = sourceDir.listFiles();
		if (null == sourceFiles || sourceFiles.length < 1) {
			throw new RuntimeException("待压缩的文件目录：" + sourceDirPath + "里面不存在文件，无需压缩.");
		}

		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			File zipFile = new File(zipDirPath + "/" + fileName + ".zip");

			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(new BufferedOutputStream(fos));
			for (int i = 0; i < sourceFiles.length; i++) {
				// 创建ZIP实体，并添加进压缩包
				filesToZip(sourceFiles[i], zos);
			}

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭流
			FileUtils.close(zos);
			FileUtils.close(fos);
		}
	}

	/**
	 * 读文件，然后加入zip
	 * 
	 * @param file
	 * @param zos
	 */
	private static void filesToZip(File file, ZipOutputStream zos) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;

		try {
			byte[] bufs = new byte[1024 * 10];
			// 创建ZIP实体，并添加进压缩包
			ZipEntry zipEntry = new ZipEntry(file.getName());
			zos.putNextEntry(zipEntry);
			// 读取待压缩的文件并写进压缩包里
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis, 1024 * 10);
			int read = 0;
			while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
				zos.write(bufs, 0, read);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭流
			FileUtils.close(bis);
			FileUtils.close(fis);
		}
	}

	/**
	 * 解压zip
	 * 
	 * @param filePath
	 *            文件的路径，包含文件名称
	 * @param zipDirPath
	 *            解压到哪里，如果为空，就是加压到当前路径
	 */
	public static void unZip(String filePath, String zipDirPath) {
		FileInputStream fis = null;
		ZipInputStream zin = null;
		BufferedInputStream bin = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				throw new RuntimeException("解压文件：" + filePath + "不存在!");
			}

			// 输入源zip路径
			fis = new FileInputStream(file);

			zin = new ZipInputStream(fis,Charset.forName("GBK"));
			bin = new BufferedInputStream(zin);

			if (StringUtils.isBlank(zipDirPath)) {
				zipDirPath = file.getParent();
			}

			// 创建目录
			FileUtils.createDir(zipDirPath);

			ZipEntry entry;
			boolean hasFile = false;
			while ((entry = zin.getNextEntry()) != null && !entry.isDirectory()) {
				hasFile = true;
				FileOutputStream fos = null;
				BufferedOutputStream bos = null;
				try {
					File zipFile = new File(zipDirPath, entry.getName());
					fos = new FileOutputStream(zipFile);
					bos = new BufferedOutputStream(fos);
					int b;
					while ((b = bin.read()) != -1) {
						bos.write(b);
					}
				} finally {
					FileUtils.close(bos);
					FileUtils.close(fos);
				}
			}
			//如果包下面没有文件，就抛异常
			if(!hasFile){
				BusinessExceptionUtil.threwException("zip包下没有文件");
			}
		} catch (Exception e) {
			BusinessExceptionUtil.threwException(e);
		} finally {
			FileUtils.close(bin);
			FileUtils.close(zin);
			FileUtils.close(fis);
		}

	}

}
