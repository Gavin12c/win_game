package com.hy.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	
    /**
     * 复制文件
     * @param fromFile  来源文件路径
     * @param toFile	粘贴文件路径
     * @throws IOException
     */
    public static int copyFile(String fromFile,String toFile){
    	int fale = 1;
    	try {
			File from = new File(fromFile);
			File to = new File(toFile);
			
			FileInputStream ins = new FileInputStream(from);
			FileOutputStream out = new FileOutputStream(to);
			byte[] b = new byte[1024];
			int n=0;
			while((n=ins.read(b))!=-1){
			    out.write(b, 0, n);
			}
			
			ins.close();
			out.close();
		} catch (FileNotFoundException e) {
			fale = fale + 1;
			System.out.println(fromFile + " 文件不存在！");
		} catch (IOException e) {
			fale = fale + 1;
			System.out.println("磁盘写入错误！");
			e.printStackTrace();
		}
    	
    	if(fale == 1){
    		System.out.println("成功配置 " +fromFile+ " 文件");
    	}
    	return fale;
    }
}
