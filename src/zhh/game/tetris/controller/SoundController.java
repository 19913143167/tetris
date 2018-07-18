package zhh.game.tetris.controller;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import zhh.game.tetris.global.Config;

/**
 * 声音控制器
 * @author zhaohuihua
 */
public class SoundController {

	/**
	 * 声音类型(开始)
	 */
	public static final int START = 0;
	/**
	 * 声音类型(结束)
	 */
	public static final int OVER = 1;
	/**
	 * 声音类型(暂停)
	 */
	public static final int PAUSE = 2;
	/**
	 * 声音类型(继续)
	 */
	public static final int CONTINUE = 3;
	/**
	 * 声音类型(下落到位)
	 */
	public static final int DOWN = 4;
	/**
	 * 声音类型(直落到底)
	 */
	public static final int SWIFT = 5;
	/**
	 * 声音类型(得分)
	 */
	public static final int SCORING = 6;
	/**
	 * 声音类型(级别变化)
	 */
	public static final int LEVEL = 7;
	/**
	 * 声音类型(赢啦)
	 */
	public static final int WINNING = 8;
	
	/**
	 * 声音文件的位置
	 */
	private static final String[] sounds = {
		"start.wav",
		"over.wav",
		"pause.wav",
		"continue.wav",
		"down.wav",
		"swift.wav",
		"scoring.wav",
		"level.wav",
		"winning.wav"
	};
	
	/**
	 * 声音剪辑池
	 */
	private static AudioClip[] pool = new AudioClip[sounds.length];
	
	private SoundController() {
	}
	
	/**
	 * 按指定类型播放声音
	 * @param type
	 */
	public static void play(int type) {
		if(!Config.SUPPORT_SOUND) return;
		
		if(pool[type] == null) {
			try {
				URL url = SoundController.class.getResource(
						"/zhh/game/tetris/resource/sounds/" + sounds[type]);
				if(url != null) {
					pool[type] = Applet.newAudioClip(url);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(pool[type] != null) {
			pool[type].play();
		}
	}
}
