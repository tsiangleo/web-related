package com.chance.swift;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;

public class SwiftImpl {
	
    private FilesClient    client         = null;
    private boolean        isLoginSuc     = false;
    //private final String mimeType         = FilesConstants.getMimetype("jpg");
    private static SwiftImpl swiftImpl = null;
    
    public static SwiftImpl getSwiftImpl()
    {
    	if(swiftImpl == null)
    	{
    		swiftImpl = new SwiftImpl();
    	}
    	return swiftImpl;
    }
    
    private SwiftImpl()
    {
    	client = new FilesClient();
    }
    
    public boolean swiftLogin()
    {
        try
        {
            isLoginSuc = client.login();
        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }

        if (isLoginSuc)
        {
            System.out.println("username: " + client.getUserName() + " url: " + client.getStorageURL()
                           + " token: " + client.getAuthToken());
        }
        else
        {
            System.out.println("login failed..");
        }

        return isLoginSuc;
    }

	public String swiftDownloadImgFile(String key, String downloadDir,
			String newName) {
		return swiftDownloadFile(SwiftConstants.img_bucket, key, downloadDir,
				newName);
	}
	
	/**
	 * 获取日志图片
	 */
	public String swiftDownloadImgFile(String key,URI dirUri,String newName){
		String dirString = dirUri.getPath();
		boolean isExist = new File(dirString+newName).exists();
		if (isExist) {
			return dirString+newName;
		}else {
			return swiftDownloadFile(SwiftConstants.img_bucket, key, dirString,
					newName);
		}
	}
	
	/**
	 * 获取头像图片
	 */
	public String swiftDownloadAvatarImgFile(String key,URI dirUri,String newName){
		String dirString = dirUri.getPath();
		boolean isExist = new File(dirString+newName).exists();
		if (isExist) {
			return dirString+newName;
		}else {
			return swiftDownloadFile(SwiftConstants.avatar_bucket, key, dirString,
					newName);
		}
	}
	
	/**
	 * 商业版logo与证件图片
	 */
	public String swiftDownloadTradeLCFile(String key,URI dirUri,String newName){
		String dirString = dirUri.getPath();
		boolean isExist = new File(dirString+newName).exists();
		if (isExist) {
			return dirString+newName;
		}else {
			return swiftDownloadFile(SwiftConstants.trade_lc_bucket, key, dirString,
					newName);
		}
	}
	
	/**
	 * 商业版图片
	 */
	public String swiftDownloadTradeImgFile(String key,URI dirUri,String newName){
		String dirString = dirUri.getPath();
		boolean isExist = new File(dirString+newName).exists();
		if (isExist) {
			return dirString+newName;
		}else {
			return swiftDownloadFile(SwiftConstants.trade_img_bucket, key, dirString,
					newName);
		}
	}
	
	/**
	 * 获取聊天图片
	 */
	public String swiftDownloadChatImgFile(String key,URI dirUri,String newName){
		String dirString = dirUri.getPath();
		boolean isExist = new File(dirString+newName).exists();
		if (isExist) {
			return dirString+newName;
		}else {
			return swiftDownloadFile(SwiftConstants.chat_img_bucket, key, dirString,
					newName);
		}
	}

	//获取音频图片
	public String swiftDownloadAVFile(String key, String downloadDir,
			String newName) {
		return swiftDownloadFile(SwiftConstants.av_bucket, key, downloadDir,
				newName);
	}
	
	/**
	 *  下载文件到downloadDir+newName，下载成功返回文件的完整路径名，失败返回null
	 * @param bucketName
	 * @param key
	 * @param downloadDir
	 * @param newName
	 * @return
	 */
	private String swiftDownloadFile(String bucketName, String key,
			String downloadDir, String newName) {
		if (!isLoginSuc)
			swiftLogin();

		String destPath = null;
		try {
			InputStream is = client.getObjectAsStream(bucketName, key);
			int buffSize = 1024 * 4;
			
			if (!downloadDir.endsWith(File.separator)) {
				downloadDir += File.separator;
			}
			File dir = new File(downloadDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			destPath = downloadDir + newName;
			FileOutputStream fos = new FileOutputStream(destPath);
			byte buffer[] = new byte[buffSize];
			int read = -1;
			while ((read = is.read(buffer)) > 0) {
				fos.write(buffer, 0, read);

			}

			fos.close();
			is.close();

			System.out.println(key + " downlaoded to " + destPath);

		} catch (Exception ex) {
			System.out.println("Problem getting " + key);
			if (destPath != null) {
				File file = new File(destPath);
				if (file.exists()) {
					file.delete();
				}
			}
			destPath = null;
			//ex.printStackTrace();
		}
		return destPath;
	}
}
