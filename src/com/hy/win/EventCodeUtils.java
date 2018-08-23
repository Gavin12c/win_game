package com.hy.win;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EventCodeUtils {
    public static Map<String, Object> getVkCode(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("8","Backspace");
        map.put("9","Tab");
        map.put("13","Enter");
        map.put("20","CapsLock");
        map.put("27","Esc");
        map.put("32","空格键");
        map.put("33","PageUp");
        map.put("34","PageDown");
        map.put("35","End");
        map.put("36","Home");
        map.put("37","向左箭头");
        map.put("38","向上箭头");
        map.put("39","向右箭头");
        map.put("40","向下箭头");
        map.put("44","printScreenSysRq");
        map.put("45","Insert");
        map.put("46","Delete");
        map.put("48","0");
        map.put("49","1");
        map.put("50","2");
        map.put("51","3");
        map.put("52","4");
        map.put("53","5");
        map.put("54","6");
        map.put("55","7");
        map.put("56","8");
        map.put("57","9");
        map.put("65","A");
        map.put("66","B");
        map.put("67","C");
        map.put("68","D");
        map.put("69","E");
        map.put("70","F");
        map.put("71","G");
        map.put("72","H");
        map.put("73","I");
        map.put("74","J");
        map.put("75","K");
        map.put("76","L");
        map.put("77","M");
        map.put("78","N");
        map.put("79","O");
        map.put("80","P");
        map.put("81","Q");
        map.put("82","R");
        map.put("83","S");
        map.put("84","T");
        map.put("85","U");
        map.put("86","V");
        map.put("87","W");
        map.put("88","X");
        map.put("89","Y");
        map.put("90","Z");
        map.put("91","Win(Left)");
        map.put("92","Win(Right)");
        map.put("93","键盘右键功能");
        map.put("96","NumPad-0");
        map.put("97","NumPad-1");
        map.put("98","NumPad-2");
        map.put("99","NumPad-3");
        map.put("100","NumPad-4");
        map.put("101","NumPad-5");
        map.put("102","NumPad-6");
        map.put("103","NumPad-7");
        map.put("104","NumPad-8");
        map.put("105","NumPad-9");
        map.put("106","NumPad*");
        map.put("107","NumPad+");
        map.put("109","NumPad-");
        map.put("110","NumPad.");
        map.put("111","NumPad/");
        map.put("112","F1");
        map.put("113","F2");
        map.put("114","F3");
        map.put("115","F4");
        map.put("116","F5");
        map.put("117","F6");
        map.put("118","F7");
        map.put("119","F8");
        map.put("120","F9");
        map.put("121","F10");
        map.put("122","F11");
        map.put("123","F12");
        map.put("127","Delete");
        map.put("144","NumLock");
        map.put("145","ScrollLock");
        map.put("155","Insert");
        map.put("160","左Shift");
        map.put("161","右Shift");
        map.put("162","左Ctrl");
        map.put("163","右Ctrl");
        map.put("164","左Alt");
        map.put("165","右Alt");
        map.put("186","分号;");
        map.put("187","等号=");
        map.put("188",",");
        map.put("189","减号-");
        map.put("190",".");
        map.put("191","/");
        map.put("192","后引号");
        map.put("219","[");
        map.put("220","反斜杠\"");
        map.put("221","]");
        map.put("222","引号");
        map.put("524","Windows");
        return map;
    }

    public static void main(String[] args) {
        Map<String, Object> map = getVkCode();
        List<Integer> l = new ArrayList<Integer>();
        for(String s : map.keySet()){
            l.add(Integer.parseInt(s));
        }
        for (int i = 0 ; i < l.size() -1 ;i++){
            for (int j = 0;j<l.size()-1-i;j++){
                if(l.get(j) > l.get(j+1)){
                    int t = l.get(j);
                    l.set(j,l.get(j+1));
                    l.set(j+1,t);
                }
            }
        }

        for (int i = 0 ; i < l.size() ; i++){
            System.out.println("map.put(\""+l.get(i)+"\",\""+map.get(l.get(i)+"")+"\");");
        }

    }

}
