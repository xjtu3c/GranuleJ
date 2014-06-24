package granulej.util;

import granulej.bean.JClosableTabbedPane;

import java.io.File;

import javax.swing.JOptionPane;

import config.GUIConfig;

public class FileUtil {
	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static JClosableTabbedPane jtp;
	public static GUIConfig config;

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
        if (!file.exists()) {
			JOptionPane.showMessageDialog(null, "Failed to delete the file '" + fileName + "', it does not exist!","Warning",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				for (int i = 0; i < jtp.getTabCount(); i++) {
					String temp = jtp.getTitleAt(i);
					if (fileName.equals(temp)
							|| fileName
									.substring(
											fileName.lastIndexOf(File.separatorChar) + 1)
									.equals(temp)) {
						jtp.removeTabAt(i);
						config.fileAndPath.remove(temp);
					}
				}
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Failed to delete the file '" + fileName + "'!", "Error",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Failed to delete the file '" + fileName + "', it does not exist !","Warning",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 * 
	 * @param dir
	 *            要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir, boolean recurFlag) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符	
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			JOptionPane.showMessageDialog(null, "Failed to delete directory : the directory '" + dir + "' does not exist!","Warning",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory() && recurFlag) {
				flag = deleteDirectory(files[i].getAbsolutePath(), true);
				if (!flag)
					break;
			}
		}
		if (!flag) {
			JOptionPane.showMessageDialog(null, "Failed to delete directory!","Warning",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		// 删除当前目录
		if (recurFlag && dirFile.delete()) {
			return true;
		} else if (!recurFlag && flag) {			
		    return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件，可以是文件或文件夹
	 * 
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName, boolean recurFlag) {
		File file = new File(fileName);
		if (!file.exists()) {
			JOptionPane.showMessageDialog(null, "Failed to delete file : the file '" + fileName + "' does not exist!");
			return false;
		} 
	      if (file.isFile())
				return deleteFile(fileName);
		  else
				return deleteDirectory(fileName,recurFlag);
		}
	}
