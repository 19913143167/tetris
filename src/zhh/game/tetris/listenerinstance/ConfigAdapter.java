package zhh.game.tetris.listenerinstance;

import zhh.game.tetris.listener.ConfigListener;

/**
 * 配置项监听器适配器
 * @author zhaohuihua
 */
public class ConfigAdapter implements ConfigListener {
	
	/**
	 * 有关控制键的配置项已经改变了
	 */
	public void hotkeyConfigChanged() {}
	
	/**
	 * 有关级别的配置项已经改变了
	 */
	public void levelConfigChanged() {}
	
	/**
	 * 有关显示的配置项已经改变了
	 */
	public void viewConfigChanged() {}
}
