package com.hy.game;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author John
 */
public class Zero {

	// 装配文件路径
	final static String path = System.getProperty("user.dir");
	static Random random;
	static {
		try {
			random = new Random();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		go(true, true);


	}




	/**
	 * 参数 ： 需要的开关传true
	 * @param againFlag
	 * @param ifYesFlag
	 */
	public static List<Timer> go(boolean againFlag,boolean ifYesFlag) {

			// 再次挑战坐标
			String[] split = io("/againXY.txt");
			List<Integer[]> list = new CopyOnWriteArrayList<>();
			for (int i = 1; i < split.length; i++) {
				String[] smap = split[i].split(",");
				Integer[] point = { Integer.parseInt(smap[0]), Integer.parseInt(smap[1]) };
				list.add(point);
			}
			Timer againTimer = new Timer();//Integer.parseInt(split[0])
			TimerTask againTask = againXY(list);
			if(null != againTask){
				if(againFlag == true){
					int delay;
					if(split[0] == "" || split[0].isEmpty()){
						delay = 3000;
					}else{
						delay = Integer.parseInt(split[0]);
					}
					againTimer.schedule(againTask, 1000, delay);
				}
			}
			
			// 判断是rbg颜色，就点
			String[] split3 = io("/ifYesXY.txt");
			// LinkedList add,del 有优势,arraylist 适用查找
			List<Integer[]> list3 = new CopyOnWriteArrayList<>();
			for (int i = 1; i < split3.length; i++) {
				String[] smap3 = split3[i].split(",");
				Integer[] point = { Integer.parseInt(smap3[0]), Integer.parseInt(smap3[1]), Integer.parseInt(smap3[2]),
						Integer.parseInt(smap3[3]), Integer.parseInt(smap3[4]) };
				list3.add(point);
			}
			Timer ifYesTimer = new Timer();
			TimerTask ifYesTask = ifYesXY(list3, 1000);
			if(null != ifYesTask){
				if(ifYesFlag == true){
					int delay;
					System.out.println(split3[0]);
					if(split3[0].isEmpty()||split3[0] == "" || split3[0] == null){
						delay = 3000;
					}else{
						delay = Integer.parseInt(split3[0]);
					}
					ifYesTimer.schedule(ifYesTask, 1000, delay);
				}
			}
			
			List<Timer> timer = new ArrayList<Timer>();
			timer.add(ifYesTimer);
			timer.add(againTimer);
			return timer;
	}




	/**过滤汉字*/
	public static String[] io(String filePath) {
		String t = FileIO.readTxtFile(path+filePath);
		// 过滤汉字
		Pattern pattern = Pattern.compile("[^0-9,;;]");
		Matcher matcher = pattern.matcher(t);
		String all = matcher.replaceAll("");
		return all.split(";");
	}

	public synchronized static TimerTask againXY(final List<Integer[]> list) {
		
		if (list != null && list.size() > 0) {
		TimerTask task = new TimerTask() {
			@Override
			public synchronized void run() {
				double v = random.nextDouble();
				v = 200 + v * (2 * 200 - 200);
				Robot r;
				try {
					r = new Robot();
					for (Integer[] integers : list) {
						Common.clickLMouse(r,integers[0], integers[1], (int) v);
					}
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		};

		return task;
		}
		return null;

	}

	public synchronized static void ifNoXY(final List<Integer[]> list, int delay) {

		if (list != null && list.size() > 0) {

			TimerTask task = new TimerTask() {
				@Override
				public synchronized void run() {
					try {
						Robot r = new Robot();
						for (Integer[] integers : list) {
							Common.clickLMouseIfNo(r, integers[0], integers[1], integers[2], integers[3], integers[4],
									1, 0);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			new Timer().schedule(task, 1500, delay);

		}
	}

	/**
	 * @param list  坐标集合
	 * @param delay 每次遍历休息时间
	 */
	public synchronized static TimerTask ifYesXY(final List<Integer[]> list, int delay) {

		if (list != null && list.size() > 0) {

			TimerTask task = new TimerTask() {
				@Override
				public synchronized void run() {
					try {
						Robot r1 = new Robot();
						for (Integer[] integers : list) {
							Common.clickLMouseIfYes(r1, integers[0], integers[1], integers[2], integers[3], integers[4],
									1, 0);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			return task;
		}
		return null;
	}

	public synchronized static void ifChange(final List<Integer[]> list, int delay,final boolean flag) {

		if (list != null && list.size() > 0) {

			TimerTask task = new TimerTask() {
				@Override
				public synchronized void run() {
					try {
						Robot r = new Robot();
						// 判断颜色 改变了 就 点击
						for (Integer[] integers : list) {
							Common.ifChange(r, integers[0], integers[1]);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			new Timer().schedule(task, 1000, 1000);

		}

	}

}
