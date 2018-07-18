package zhh.game.tetris;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

import zhh.game.tetris.controller.GameController;
import zhh.game.tetris.controller.WinkController;
import zhh.game.tetris.dialog.AboutAuthorDialog;
import zhh.game.tetris.dialog.AboutGameDialog;
import zhh.game.tetris.dialog.HotkeySetDialog;
import zhh.game.tetris.dialog.LevelSetChooseDialog;
import zhh.game.tetris.dialog.ViewSetDialog;
import zhh.game.tetris.entity.Level;
import zhh.game.tetris.entity.LevelSetFactory;
import zhh.game.tetris.global.Config;
import zhh.game.tetris.listener.GameListener;
import zhh.game.tetris.listener.ScoringListener;
import zhh.game.tetris.view.GamePanel;
import zhh.game.tetris.view.PreviewPanel;
import zhh.game.tetris.view.ThickBevelBorder;

/**
 * 俄罗斯方块游戏<br>
 * <br>
 * 游戏简介:<br>
 * 俄罗斯方块是一款益智方块游戏<br>
 * 这款游戏最初是由苏联的电脑科学家帕吉特诺夫(Alex Pajitnov)于1985年制作的<br>
 * 作者给了他一个源自希腊字4(tetra)的名字Tetris<br>
 * 1989年由任天堂于发行GameBoy版, 推出后风靡全球, 成为益智方块类型游戏中知名度最高的一款<br>
 * 它看似简单但却变化无穷, 上手极其容易, 但是要熟练地掌握其中的操作与摆放技巧, 难度却不低<br>
 * <br>
 * 玩法简介:<br>
 * 游戏具有一个用于摆放小方块的平面虚拟场地<br>
 * 一组由几个小方块组成的规则形状(Tetromino)<br>
 * 游戏每次随机输出一种形状到场地顶部, 自动以一定的速度下落<br>
 * 用户在形状的过程中可以控制形状的左右移动及旋转以将形状填充到场地中<br>
 * 直至形状下落至场地底部或被场地中已有的方块阻挡而不能再下落<br>
 * 游戏再次输出一个形状, 周而复始<br>
 * 如果这次填充将场地的一行或多行完全填满, 则组成这些行的所有方块将被消除<br>
 * 并且以此来换取一定的积分奖励<br>
 * 而未被消除的方块会一直累积, 并对后来的形状摆放造成各种影响<br>
 * 如果下一个形状的输出位置已经被未消除的方块所占据，则游戏结束<br>
 * @author zhaohuihua
 */
public class Tetris extends JFrame implements GameListener, ScoringListener {
	
	/**
	 * 串行化版本统一标识符
	 */
	private static final long serialVersionUID = -8223961532412482771L;
	
	/**
	 * 游戏控制器
	 */
	private final GameController controller;
	
	/**
	 * 游戏显示区
	 */
	private final GamePanel gamePanel;
	
	/**
	 * 预览显示区
	 */
	private final PreviewPanel previewPanel;
	
	/**
	 * 闪烁控制器
	 */
	private WinkController winkController;

	private final JButton btnStart;
	
	private final JButton btnPause;

	private final JLabel lblScoring;
	
	private final JLabel lblSpeed;
	
	private final JLabel lblLevel;
	
	private final JLabel lblLevelSet;
	
	public Tetris() {
		super("俄罗斯方块");
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		try {
			URL url = this.getClass().getResource("/zhh/game/tetris/resource/images/tetris.png");
			if(url != null) {
				ImageIcon icon = new ImageIcon(url);
				if(icon != null) {
					this.setIconImage(icon.getImage());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		this.setSize(356, 463);
		this.setLocationRelativeTo(null);
		
		controller = new GameController();

		final JPanel infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoPanel.setBounds(0, 0, 100, 410);
		getContentPane().add(infoPanel);
		
		final CompoundBorder compoundBorder = new CompoundBorder(
				new LineBorder(Config.COLOR_BORDER, 1, false),
				new ThickBevelBorder(1, 4, 
						new Color(0xF1EFE2), new Color(0xECE9D8), 
						new Color(0x716F64), new Color(0xACA899)));
		final LineBorder lineBorder = new LineBorder(Config.COLOR_BORDER, 1, false);
		
		previewPanel = new PreviewPanel();
		previewPanel.setBounds(10, 10, 80, 80);
		previewPanel.setBorder(compoundBorder);
		infoPanel.add(previewPanel);
		
		gamePanel = new GamePanel();
		gamePanel.setBounds(100, 0, 250, 410);
		gamePanel.setBorder(compoundBorder);
		this.getContentPane().add(gamePanel);

		controller.addScoringListener(this, true);
		controller.addGameListener(gamePanel);
		controller.addGameListener(this);
		controller.addGameViewListener(gamePanel);
		controller.addPreviewListener(previewPanel);
		gamePanel.addKeyListener(controller);
		gamePanel.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e){
				controller.gamePause();
			}
		});
		
		// 游戏信息
		final JLabel lblLevelSetLabel = new JLabel("关卡");
		lblLevelSetLabel.setBorder(lineBorder);
		lblLevelSetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevelSetLabel.setBounds(10, 100, 80, 19);
		infoPanel.add(lblLevelSetLabel);

		lblLevelSet = new JLabel();
		lblLevelSet.setBorder(lineBorder);
		lblLevelSet.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevelSet.setOpaque(true);
		lblLevelSet.setBackground(Config.COLOR_BACKGROUND);
		lblLevelSet.setBounds(10, 125, 80, 19);
		infoPanel.add(lblLevelSet);

		final JLabel lblLevelLabel = new JLabel("级别");
		lblLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevelLabel.setBorder(lineBorder);
		lblLevelLabel.setBounds(10, 154, 80, 19);
		infoPanel.add(lblLevelLabel);

		lblLevel = new JLabel();
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setBorder(lineBorder);
		lblLevel.setOpaque(true);
		lblLevel.setBackground(Config.COLOR_BACKGROUND);
		lblLevel.setBounds(10, 175, 80, 19);
		infoPanel.add(lblLevel);

		final JLabel lblScoringLabel = new JLabel("得分");
		lblScoringLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoringLabel.setBorder(lineBorder);
		lblScoringLabel.setBounds(10, 204, 80, 19);
		infoPanel.add(lblScoringLabel);

		lblScoring = new JLabel();
		lblScoring.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoring.setBorder(lineBorder);
		lblScoring.setOpaque(true);
		lblScoring.setBackground(Config.COLOR_BACKGROUND);
		lblScoring.setBounds(10, 225, 80, 19);
		infoPanel.add(lblScoring);

		final JLabel lblSpeedLabel = new JLabel("速度");
		lblSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedLabel.setBorder(lineBorder);
		lblSpeedLabel.setBounds(10, 254, 80, 19);
		infoPanel.add(lblSpeedLabel);
		
		lblSpeed = new JLabel();
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeed.setBorder(lineBorder);
		lblSpeed.setOpaque(true);
		lblSpeed.setBackground(Config.COLOR_BACKGROUND);
		lblSpeed.setBounds(10, 275, 80, 19);
		infoPanel.add(lblSpeed);

		// 菜单
		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		final JMenu mnuSet = new JMenu();
		mnuSet.setText("设置(S)");
		mnuSet.setMnemonic('S');
		menuBar.add(mnuSet);

		final JMenu mnuHelp = new JMenu();
		mnuHelp.setText("帮助(H)");
		mnuHelp.setMnemonic('H');
		menuBar.add(mnuHelp);

		final JCheckBoxMenuItem mnuSound = new JCheckBoxMenuItem();
		mnuSound.setText("游戏声音(A)");
		mnuSound.setState(Config.SUPPORT_SOUND);
		mnuSound.setMnemonic('A');
		mnuSound.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 
				InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnuSet.add(mnuSound);
		mnuSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Config.SUPPORT_SOUND = mnuSound.getState();
			}
		});
		
		mnuSet.addSeparator();

		final JMenuItem mnuHotkeySet = new JMenuItem();
		mnuHotkeySet.setText("控制键设置(C)...");
		mnuHotkeySet.setMnemonic('C');
		mnuHotkeySet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, 
				InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnuSet.add(mnuHotkeySet);
		mnuHotkeySet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final HotkeySetDialog dialog = new HotkeySetDialog(Tetris.this, true);
				dialog.setVisible(true);
			}
		});

		final JMenuItem mnuViewSet = new JMenuItem();
		mnuViewSet.setText("显示设置(V)...");
		mnuViewSet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, 
				InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnuViewSet.setMnemonic('V');
		mnuSet.add(mnuViewSet);
		mnuViewSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final ViewSetDialog dialog = new ViewSetDialog(Tetris.this, true);
				dialog.addConfigListener(gamePanel);
				dialog.addConfigListener(previewPanel);
				dialog.setVisible(true);
			}
		});

		final JMenuItem mnuLevelSetChoose = new JMenuItem();
		mnuLevelSetChoose.setText("关卡选择(L)...");
		mnuLevelSetChoose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, 
				InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnuLevelSetChoose.setMnemonic('L');
		mnuSet.add(mnuLevelSetChoose);
		mnuLevelSetChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final LevelSetChooseDialog dialog = new LevelSetChooseDialog(Tetris.this, true,
						controller.isPlaying());
				dialog.addConfigListener(controller);
				dialog.setVisible(true);
			}
		});

		final JMenuItem mnuAboutGame = new JMenuItem();
		mnuHelp.add(mnuAboutGame);
		mnuAboutGame.setText("关于游戏(G)...");
		mnuAboutGame.setMnemonic('G');
		mnuAboutGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final AboutGameDialog dialog = new AboutGameDialog(Tetris.this, true);
				dialog.setVisible(true);
			}
		});

		final JMenuItem mnuAboutAuthor = new JMenuItem();
		mnuHelp.add(mnuAboutAuthor);
		mnuAboutAuthor.setText("关于作者(A)...");
		mnuAboutAuthor.setMnemonic('A');
		mnuAboutAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final AboutAuthorDialog dialog = new AboutAuthorDialog(Tetris.this, true);
				dialog.setVisible(true);
			}
		});
		
		// 开始 暂停
		btnStart = new JButton();
		btnStart.setText("开始(B)");
		btnStart.setMnemonic('B');
		btnStart.setBounds(10, 348, 80, 23);
		infoPanel.add(btnStart);
		btnStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				if (controller.isPlaying())
					controller.gameStop();
				else {
					// 耗时操作, 另启线程执行
					new Thread() {
						public void run() {
							controller.gameCreate();
						}
					}.start();
				}
			}
		});

		btnPause = new JButton();
		btnPause.setText("暂停(P)");
		btnPause.setMnemonic('P');
		btnPause.setEnabled(false);
		btnPause.setBounds(10, 377, 80, 23);
		infoPanel.add(btnPause);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(controller.isPause()) 
					controller.gameContinue();
				else
					controller.gamePause();
			}
		});
		
		btnStart.setFocusable(false);
		btnPause.setFocusable(false);
		menuBar.setFocusable(false);
		gamePanel.setFocusable(true);
	}
	
	public void levelChanged(Level level) {
		lblLevel.setText("" + (level.getId() + 1));
		winkController.wink(lblLevel);
	}


	public void speedChanged(int speed) {
		lblSpeed.setText("" + speed + "毫秒/格");
		winkController.wink(lblSpeed);
	}
	public void scoringChanged(int scoring) {
		lblScoring.setText("" + scoring);
		winkController.wink(lblScoring);
	}

	public void winning(int scoring, int speed, Level level) {
		lblLevel.setText("通关");
		winkController.wink(lblLevel);
	}

	public void scoringInit(int scoring, int speed, Level level) {
		lblLevelSet.setText(LevelSetFactory.getLevelSet(Config.CURRENT_LEVELSET).getName());
		lblLevel.setText("" + (level.getId() + 1));
		lblScoring.setText("" + scoring);
		lblSpeed.setText("" + speed + "毫秒/格");
		winkController.wink(new JLabel[] {lblLevelSet, lblLevel, lblScoring, lblSpeed});
	}

	public void gameContinue() {
		btnPause.setText("暂停(P)");
	}

	public void gameOver() {
		btnPause.setEnabled(false);
		btnStart.setText("开始(B)");
		btnStart.setMnemonic('B');
		winkController.dispose();
	}

	public void gamePause() {
		btnPause.setText("继续(P)");
	}

	public void gameStart() {
		winkController = new WinkController();
		btnPause.setEnabled(true);
		btnStart.setText("结束(E)");
		btnStart.setMnemonic('E');
		btnPause.setText("暂停(P)");
	}

	public boolean gameWillStop() {
		int response = JOptionPane.showConfirmDialog(
				this, "确定要停止当前游戏吗?", "确认", JOptionPane.YES_NO_OPTION);
		return response == 0;
	}
	
	public static void main(String args[]) {
		try {
			// 更改应用程序的默认外观
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			
			// 更改应用程序的默认字体
			Enumeration keys = UIManager.getDefaults().keys();
			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				Object value = UIManager.get(key);
				if (value instanceof FontUIResource)
					UIManager.put(key, new FontUIResource(Config.FONT_DEFAULT));
			}

			Tetris tetris = new Tetris();
			tetris.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
