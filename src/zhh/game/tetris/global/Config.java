package zhh.game.tetris.global;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 * 配置类
 * @author zhaohuihua
 */
public class Config {
	
	/**
	 * 默认字体
	 */
	public static final Font FONT_DEFAULT = new Font("宋体", Font.PLAIN, 12);
	
	/**
	 * 默认颜色
	 */
	public static final Color COLOR_DEFAULT = Color.GRAY; //	灰色		gray		0x808080
	
	/**
	 * 边框颜色
	 */
	public static final Color COLOR_BORDER = new Color(0x000088);
	
	/**
	 * 背景颜色
	 */
	public static final Color COLOR_BACKGROUND = Color.WHITE;
	
	/**
	 * 网格颜色
	 */
	public static final Color COLOR_GRID_LINE = new Color(0xCCCCCC);
	
	/**
	 * 游戏结束的蒙色
	 */
	public static final Color COLOR_COVERING_OVER = new Color(255, 153, 153, 128);
	
	/**
	 * 游戏暂停的蒙色
	 */
	public static final Color COLOR_COVERING_PAUSEA = new Color(153, 153, 255, 128);
	
	/**
	 * 闪烁颜色
	 */
	public static final Color COLOR_WINK = Color.WHITE;
	
	/**
	 * 闪烁的暂停时间
	 */
	public static final int WINK_PAUSE_TIME = 100;
	
	/**
	 * 地形的宽度(单位:格)
	 */
	public static final int GROUND_WIDTH = 12;
	
	/**
	 * 地形的高度(单位:格)
	 */
	public static final int GROUND_HEIGHT = 20;

	/**
	 * 单元格的宽度
	 */
	public static final int CELL_WIDTH = 20;
	
	/**
	 * 单元格的高度
	 */
	public static final int CELL_HEIGHT = 20;

	/**
	 * 预览的宽度(单位:格)
	 */
	public static final int PREVIEW_WIDTH = 5;
	
	/**
	 * 预览的高度(单位:格)
	 */
	public static final int PREVIEW_HEIGHT = 5;
	
	/**
	 * 预览的单元格的宽度
	 */
	public static final int PREVIEW_CELL_WIDTH = 14;
	
	/**
	 * 预览的单元格的高度
	 */
	public static final int PREVIEW_CELL_HEIGHT = 14;
	
	/**
	 * 是否显示游戏区的网格
	 */
	public static boolean SHOW_GRID_LINE = true;
	
	/**
	 * 是否显示预览区的网格
	 */
	public static boolean SHOW_GRID_LINE_PREVIEW = true;
	
	/**
	 * 形状是否支持彩色
	 */
	public static boolean SUPPORT_COLORFUL_SHAPE = true;
	
	/**
	 * 障碍物是否支持彩色
	 */
	public static boolean SUPPORT_COLORFUL_GROUND = true;
	
	/**
	 * 预览是否支持彩色
	 */
	public static boolean SUPPORT_COLORFUL_PREVIEW = true;
	
	/**
	 * 是否支持声音
	 */
	public static boolean SUPPORT_SOUND = false;
	
	/**
	 * 当前的级别集
	 */
	public static int CURRENT_LEVELSET = 2;
	
	/**
	 * 初始级别
	 */
	public static int INIT_LEVEL = 0;

	/**
	 * 游戏开始/结束的键码
	 */
	public static int KEY_START = KeyEvent.VK_ENTER;
	
	/**
	 * 游戏暂停/继续的键码
	 */
	public static int KEY_PAUSE = KeyEvent.VK_P;
	
	/**
	 * 形状左移的键码
	 */
	public static int KEY_LEFT = KeyEvent.VK_LEFT;
	
	/**
	 * 形状右移的键码
	 */
	public static int KEY_RIGHT = KeyEvent.VK_RIGHT;
	
	/**
	 * 形状变形的键码
	 */
	public static int KEY_ROTATE = KeyEvent.VK_UP;
	
	/**
	 * 形状加速的键码
	 */
	public static int KEY_DOWN = KeyEvent.VK_DOWN;
	
	/**
	 * 形状直落到底的键码
	 */
	public static int KEY_SWIFT = KeyEvent.VK_SPACE;
	
	/**
	 * 游戏开始/结束的默认键码
	 */
	public static final int KEY_START_DEFAULT = KeyEvent.VK_ENTER;

	/**
	 * 游戏暂停/继续的默认键码
	 */
	public static final int KEY_PAUSE_DEFAULT = KeyEvent.VK_P;
	
	/**
	 * 形状左移的默认键码
	 */
	public static final int KEY_LEFT_DEFAULT = KeyEvent.VK_LEFT;
	
	/**
	 * 形状右移的默认键码
	 */
	public static final int KEY_RIGHT_DEFAULT = KeyEvent.VK_RIGHT;
	
	/**
	 * 形状变形的默认键码
	 */
	public static final int KEY_ROTATE_DEFAULT = KeyEvent.VK_UP;
	
	/**
	 * 形状加速的默认键码
	 */
	public static final int KEY_DOWN_DEFAULT = KeyEvent.VK_DOWN;
	
	/**
	 * 形状直落到底的默认键码
	 */
	public static final int KEY_SWIFT_DEFAULT = KeyEvent.VK_SPACE;
	
	
	/**
	 * 形状的所有颜色
	 */
	public static final Color[] COLORS = new Color[] {
		new Color(0xD2691E)	,	//	巧克力 	chocolate	0xD2691E
		new Color(0x800000)	,	//	栗色 	maroon		0x800000
		new Color(0x808000)	,	//	橄榄绿	olive		0x808000
		new Color(0x008000)	,	//	深绿 	darkgreen	0x008000
		new Color(0x008080)	,	//	蓝绿 	teal		0x008080
		new Color(0x000080)	,	//	海蓝 	navy		0x000080
		new Color(0x800080)	,	//	紫色 	purple		0x800080
		new Color(0x4682B4)	,	//	钢青 	steelblue	0x4682B4
		new Color(0x9ACD32)	,	//	黄绿 	yellowgreen	0x9ACD32
		new Color(0x8FBC8B)	,	//	深海绿 	darkseagreen0x8FBC8B
		new Color(0xDC143C)	,	//	深红 	crimson		0xDC143C
		new Color(0xBA55D3)	,	//	中紫 	mediumorchid0xBA55D3
		new Color(0x6A5ACD)	,	//	暗灰蓝	slateblue	0x6A5ACD
		new Color(0xDAA520)	,	//	金麒麟色	goldenrod	0xDAA520
		new Color(0xFF4500)	,	//	桔红 	orangered	0xFF4500
		new Color(0xFA8072)		//	肉色 	salmon		0xFA8072
//		new Color(0x0000FF)		//	蓝色 	blue		0x0000FF	无立体感
//		new Color(0xFF00FF)	,	//	紫红 	fuchsia		0xFF00FF	无立体感
//		new Color(0xFFA500)	,	//	橙色 	orange		0xFFA500	与金麒麟色太接近
//		new Color(0xFFD700)	,	//	金色 	gold		0xFFD700	太浅
//		new Color(0x00BFFF)	,	//	深天蓝	deepskyblue	0x00BFFF	太浅
	};
	
	/**
	 * 根据类型获取形状的颜色
	 * @param type int 类型
	 * @return Color
	 */
	public static Color getShapeColor(int type) {
		if(SUPPORT_COLORFUL_SHAPE && type >= 0 && type < COLORS.length)
			return COLORS[type];
		else
			return COLOR_DEFAULT;
	}
	
	/**
	 * 根据类型获取障碍物的颜色
	 * @param type int 类型
	 * @return Color
	 */
	public static Color getGroundColor(int type) {
		if(SUPPORT_COLORFUL_GROUND && type >= 0 && type < COLORS.length)
			return COLORS[type];
		else
			return COLOR_DEFAULT;
	}
	
	/**
	 * 根据类型获取预览的颜色
	 * @param type int 类型
	 * @return Color
	 */
	public static Color getPreviewColor(int type) {
		if(SUPPORT_COLORFUL_PREVIEW && type >= 0 && type < COLORS.length)
			return COLORS[type];
		else
			return COLOR_DEFAULT;
	}
}
