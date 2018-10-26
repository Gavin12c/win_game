package com.hy.win;
import com.hy.game.MouseInfo3_1;
import com.hy.game.Zero;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class KeyboardHook implements Runnable{
    private WinUser.HHOOK hhk;
    boolean Tabflag = true;
    boolean againFlag = true;
    public boolean isAgainFlag() {
		return againFlag;
	}
	public void setAgainFlag(boolean againFlag) {
		this.againFlag = againFlag;
	}
	public boolean isIfYesFlag() {
		return ifYesFlag;
	}
	public void setIfYesFlag(boolean ifYesFlag) {
		this.ifYesFlag = ifYesFlag;
	}
	boolean ifYesFlag = true;
    public boolean isTabflag() {
		return Tabflag;
	}
	public void setTabflag(boolean Tabflag) {
		this.Tabflag = Tabflag;
	}
	//钩子回调函数
    private WinUser.LowLevelKeyboardProc keyboardProc = new WinUser.LowLevelKeyboardProc() {
        public LRESULT callback(int nCode, WPARAM wParam, WinUser.KBDLLHOOKSTRUCT event) {
            // 输出按键值和按键时间
            if (nCode >= 0) {
//                if(event.vkCode==27) KeyboardHook.this.setHookOff();
                switch (event.vkCode) {
				case 27:  //ESC键退出
					System.exit(0);
					break;
				case 112: //F1开启脚本
					WinTab.go();
					setAgainFlag(true);
					setIfYesFlag(true);
					Zero.go(isAgainFlag(),isIfYesFlag());
					break;
				case 113: //F2关闭启脚本
					setAgainFlag(false);
					setIfYesFlag(false);
					break;
				case 114: //F3窗口切换
					setTabflag(true);
					while(isTabflag()){
						WinTab.go();
					}
					break;
				case 115: //F4切换关闭
					setTabflag(false);
					break;
				case 117: //F6坐标工具
					MouseInfo3_1.coordinate();
					break;

				default:
					break;
				}
            }
            return User32.INSTANCE.CallNextHookEx(hhk, nCode, wParam, null);
        }
    };//Blog @See http://blog.csdn.net/shenpibaipao

    public void run() {
        setHookOn();
        System.out.println("nihao ");
    }
    // 安装钩子
    public void setHookOn(){
        System.out.println("Hook On!");

        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        hhk = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardProc, hMod, 0);

        int result;
        WinUser.MSG msg = new WinUser.MSG();
        while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {
            if (result == -1) {
                setHookOff();
                break;
            } else {
                User32.INSTANCE.TranslateMessage(msg);
                User32.INSTANCE.DispatchMessage(msg);
            }
        }
    }
    // 移除钩子并退出
    public void setHookOff(){
        System.out.println("Hook Off!");
        User32.INSTANCE.UnhookWindowsHookEx(hhk);
        System.exit(0);
    }
}