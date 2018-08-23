package com.hy.win;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hy.game.FileIO;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.WinDef;

public class WinTab{
	// 装配文件路径
	final static String path = System.getProperty("user.dir");
	private final static int delay = 2000;

	static String[] io = io("/winTab.txt");
	static String[] split = io[1].split(",");
	public static void go() {
		try {
			for (int i = 0; i < split.length; i++) {
				tab(split[i],delay);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String[] io(String filePath) {
		String t = FileIO.readTxtFile(path+filePath);
		return t.split(";");
	}
	

	
	public static synchronized void tab(String name,int delay) throws InterruptedException {
		        HWND hwnd=User32.INSTANCE.FindWindow(null, name);
		        if (hwnd == null) {
		            System.out.println("Window is not running");
		            Thread.sleep(delay);
		        } else {
		            User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE); // SW_RESTORE 9 窗口恢复
		            User32.INSTANCE.SetForegroundWindow(hwnd); // bring to front
//		            WinDef.RECT qqwin_rect = new WinDef.RECT();
//		            User32.INSTANCE.GetWindowRect(hwnd, qqwin_rect);
//		            int qqwin_width = qqwin_rect.right - qqwin_rect.left;
//		            int qqwin_height = qqwin_rect.bottom - qqwin_rect.top;
		            User32.INSTANCE.MoveWindow(hwnd, 230,50, 1063, 608, true);
		            Thread.sleep(delay);
		            User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_MINIMIZE);
		            System.out.println(name+" 窗口");
		//            User32.INSTANCE.PostMessage(hwnd, User32.WM_CLOSE, null, null);
		//            String username = "11111111111";
		//            for (Character c : username.toCharArray())
		//                sendChar(c);
		////            @formatter:off
		//            User32.INSTANCE.GetForegroundWindow(); // 获取现在前台窗口
		//            User32.INSTANCE.GetWindowRect(hwnd, qqwin_rect);
		//            User32.INSTANCE.MoveWindow(hwnd, 700, 100, qqwin_width, qqwin_height, true);
		//            for (int i = 700; i > 100; i -= 10) {
		//                User32.INSTANCE.MoveWindow(hwnd, i, 100, qqwin_width, qqwin_height, true);
		//                try {
		//                    Thread.sleep(10);
		//                } catch (Exception e) {
		//                }
		//            }
		//            User32.INSTANCE.PostMessage(hwnd, WinUser.WM_CLOSE, null, null);
		//            @formatter:on;
		        }
	}

    private static WinUser.INPUT input = new WinUser.INPUT();
    
    private static void sendChar(char ch) {
        input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        input.input.setType("ki");
        input.input.ki.wScan = new WinDef.WORD(0);
        input.input.ki.time = new WinDef.DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        // Press
        input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(0); // keydown
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
        // Release
        input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(2); // keyup
        User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
    }
    
}