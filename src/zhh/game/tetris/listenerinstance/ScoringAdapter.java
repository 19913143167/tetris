package zhh.game.tetris.listenerinstance;

import zhh.game.tetris.entity.Level;
import zhh.game.tetris.listener.ScoringListener;

/**
 * 计分监听器适配器
 * @author zhaohuihua
 */
public class ScoringAdapter implements ScoringListener {

	/**
	 * 级别变化了
	 * @param level Level 当前的级别
	 */
	public void levelChanged(Level level) {}

	/**
	 * 得分变化了
	 * @param scoring int 当前的得分
	 */
	public void scoringChanged(int scoring) {}

	/**
	 * 初始化
	 * @param scoring int 当前的得分
	 * @param speed int 当前的速度
	 * @param level Level 当前的级别
	 */
	public void scoringInit(int scoring, int speed, Level level) {}

	/**
	 * 速度变化了
	 * @param speed int 当前速度
	 */
	public void speedChanged(int speed) {}

	/**
	 * 胜利了, 过了最后一关
	 * @param scoring int 最后的得分
	 * @param speed int 最后的速度
	 * @param level Level 最后的级别
	 */
	public void winning(int scoring, int speed, Level level) {}

}
