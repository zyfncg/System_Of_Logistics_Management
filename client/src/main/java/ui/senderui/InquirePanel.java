package ui.senderui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businessLogic.businessLogicController.deliveryController.OrderController;
import businessLogic.businessLogicController.senderController.InquireController;
import businessLogic.businessLogicModel.util.CommonLogic;
import businessLogicService.deliveryBLService.OrderBLService;
import businessLogicService.senderBLService.InquireBLService;
import constant.City;
import constant.TransitionNode;
import ui.baseui.LimpidButton;
import ui.mainui.ExpressFrame;
import ui.mainui.ExpressPanel;
import ui.viewcontroller.ViewController;
import vo.deliveryVO.OrderVO;
import vo.senderVO.LogisticsVO;

@SuppressWarnings("serial")
public class InquirePanel extends JPanel{
	
	private ViewController viewController;
	
	private InquireBLService inquireService = new InquireController();
	
	private boolean isShow = false;	//是否显示物流信息
	
	private LogisticsVO logisticsInfo;
	
	private JTextField search = new JTextField();
	
	private JLabel orderLabel = new JLabel("订单条形码号");
	
	private JLabel tip = new JLabel();
	
	private LimpidButton ok = new LimpidButton("","picture/inquire.png");
	
	private LimpidButton cancel = new LimpidButton("","picture/back.png");
	
	private static Image NAN_JING = new ImageIcon("picture/city/nan_jing.png").getImage();
	
	private static Image BEI_JING = new ImageIcon("picture/city/bei_jing.png").getImage();
	
	private static Image SHANG_HAI = new ImageIcon("picture/city/shang_hai.png").getImage();
	
	private static Image GUANG_ZHOU = new ImageIcon("picture/city/guang_zhou.png").getImage();
	
	private static Image ARRIVED = new ImageIcon("picture/city/Arrived.png").getImage();
	
	private static Image TOARRIVE = new ImageIcon("picture/city/ToArrive.png").getImage();
	
	private static Image BUSINESS_HALL = new ImageIcon("picture/city/BusinessHall.png").getImage();
	
	private static Image TRANSITION = new ImageIcon("picture/city/Transition.png").getImage();
	
	private static Image SENDER = new ImageIcon("picture/city/sender.png").getImage();
	
	private static Image RECEIVER = new ImageIcon("picture/city/receiver.png").getImage();
	
	private static Map<City, Image> CITY_IMG_MAP = new HashMap<City, Image>();
	
	static {
		CITY_IMG_MAP.put(City.NAN_JING, NAN_JING);
		CITY_IMG_MAP.put(City.BEI_JING, BEI_JING);
		CITY_IMG_MAP.put(City.SHANG_HAI, SHANG_HAI);
		CITY_IMG_MAP.put(City.GUANG_ZHOU, GUANG_ZHOU);
	}
	
	private static Map<TransitionNode, Image> NODE_IMG_MAP = new HashMap<TransitionNode, Image>();
	
	static {
		NODE_IMG_MAP.put(TransitionNode.BUSINESS_HALL, BUSINESS_HALL);
		NODE_IMG_MAP.put(TransitionNode.TRANSI_CENTER, TRANSITION);
	}
	
	private static final int CITY_W = NAN_JING.getWidth(null);
	
	private static final int CITY_H = NAN_JING.getHeight(null);
	
	private static final int BUSINESS_W = BUSINESS_HALL.getWidth(null);
	
	private static final int BUSINESS_H = BUSINESS_HALL.getHeight(null);
	
	private static final int TRANSITION_W = TRANSITION.getWidth(null);
	
	private static final int TRANSITION_H = TRANSITION.getHeight(null);
	
	private static final int ARRIVED_W = ARRIVED.getWidth(null);
	
	private static final int ARRIVED_H = ARRIVED.getHeight(null);
	
	private static final int TOARRIVE_W = TOARRIVE.getWidth(null);
	
	private static final int TOARRIVE_H = TOARRIVE.getHeight(null);
	
	private static final int SENDER_W = SENDER.getWidth(null);
	
	private static final int SENDER_H = SENDER.getHeight(null);
	
	private static final int RECEIVER_W = RECEIVER.getWidth(null);
	
	private static final int RECEIVER_H = RECEIVER.getHeight(null);
	
	private static Font WORD_FONT = new Font("宋体", Font.PLAIN, 15);
	
	private static final int LABEL_W = 150;
	
	private static final int LABEL_H = 30;
	
	private static final int TEXT_W = LABEL_W;
	
	private static final int TEXT_H = LABEL_H;
	
	private static final int BUTTON_W = 80;
	
	private static final int BUTTON_H = LABEL_H;
	
	private static final int COMPONENT_GAP = (ExpressFrame.FRAME_W - LABEL_W - TEXT_W - (BUTTON_W << 1)) / 5;
	
	private static Image BACKGROUND = new ImageIcon("picture/background.jpg").getImage();
	
	private static final int ORDER_X = COMPONENT_GAP;	//组件与组件的距离 
	
	private static final int ORDER_Y = COMPONENT_GAP; 	
	 
	public InquirePanel(ViewController viewController) {
		this.viewController = viewController;
		
		//面板
		this.setLayout(null);
		this.setBounds(0, 0, ExpressFrame.FRAME_W, ExpressFrame.FRAME_H);
		//订单条形码号标签
		this.orderLabel.setBounds(ORDER_X+60, ORDER_Y, LABEL_W, LABEL_H);
		this.orderLabel.setFont(WORD_FONT);
		//订单条形码号文本框
		this.search.setBounds(this.orderLabel.getX() + LABEL_W , this.orderLabel.getY(),
				TEXT_W + COMPONENT_GAP, TEXT_H);
		this.search.setFont(WORD_FONT);
		this.search.setOpaque(false);
		//提示信息标签
		this.tip.setBounds(this.orderLabel.getX(), this.orderLabel.getY() + (LABEL_H << 1),
				this.search.getWidth(), LABEL_H);
		this.tip.setFont(WORD_FONT);
		this.tip.setForeground(Color.RED);
		//确定按钮
		this.ok.setBounds(this.search.getX() + TEXT_W + (COMPONENT_GAP << 1),
				          this.search.getY(),
				          BUTTON_W, BUTTON_H);
		this.ok.setFont(WORD_FONT);
		//取消按钮
		this.cancel.setBounds(this.ok.getX() + BUTTON_W + COMPONENT_GAP, this.ok.getY(),
				BUTTON_W, BUTTON_H);
		this.cancel.setFont(WORD_FONT);
		//把组件添加到面板
		this.add(this.orderLabel);
		this.add(this.search);
		this.add(this.tip);
		this.add(this.ok);
		this.add(this.cancel);
		//添加事件监听
		this.addListener(this.ok, this.cancel);
		setClose();
	}
	
	private void addListener(JButton ok, JButton cancel) {
		
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//检查订单条形码号是否有效
				String id = search.getText();
				if(id.length() != 10 || !CommonLogic.isNumber(id)) {
					//提示输入不是10位数字
					tip.setText("订单条形码号应为10位数字");
					repaint();
					return ;
				}

				//检查是否存在该条形码号对应的订单
				logisticsInfo = inquireService.getLogInfoById(id);
				if(logisticsInfo == null) {
					//提示输入的订单条形码号不存在
					tip.setText("该订单不存在");
					repaint();
					return ;
				}
				//显示物流信息
				isShow = true;
				tip.setText("");
				repaint();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//设当前页面不可见
				setVisible(false);
				//回到开始页面
				viewController.switchView(ExpressPanel.class.getName());
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(!isShow) return ;
		
		LogisticsVO vo = this.inquireService.getLogInfoById(this.search.getText());
		if(vo == null) return ;
		
		//绘制城市轨迹与物流节点轨迹
		int startX = 130;	//第一张图片X坐标
		int startY = 250;	//第一张图片Y坐标
		int xGap = 210;		//图片之间在X轴上的距离
		int strH = startY + (int)(CITY_H * 1.2);  //字符串图片Y坐标
		//城市图片
		OrderBLService order = new OrderController();
		OrderVO orderVO = order.getOrderInfoById(this.search.getText());
		City source = orderVO.getSenderInfo().getCity();
		City dest = orderVO.getReceiverInfo().getCity();
		Image city = null;
		List<TransitionNode> node = vo.getState();
		if(source != dest) {//如果出发地和目的地不在同一城市
			//送件人营业厅
			city = CITY_IMG_MAP.get(source);
			g.drawImage(city, startX, startY,
					CITY_W, CITY_H, null);
			g.drawImage(BUSINESS_HALL, startX, strH,
					BUSINESS_W, BUSINESS_H, null);
			//送件人中转中心
			g.drawImage(city, startX + xGap, startY,
					CITY_W, CITY_H, null);
			g.drawImage(TRANSITION, startX + xGap, strH,
					TRANSITION_W, TRANSITION_H, null);
			//收件人中转中心
			city = CITY_IMG_MAP.get(dest);
			g.drawImage(city, startX + (xGap << 1), startY,
					CITY_W, CITY_H, null);
			g.drawImage(TRANSITION, startX + (xGap << 1), strH,
					TRANSITION_W, TRANSITION_H, null);
			//收件人营业厅
			g.drawImage(city, startX + (xGap * 3), startY,
					CITY_W, CITY_H, null);
			g.drawImage(BUSINESS_HALL, startX + (xGap * 3), strH,
					BUSINESS_W, BUSINESS_H, null);
			//箭头
			int size = node.size();
			int arrowX = startX + CITY_W;
			int arrowY = startY + (CITY_H - ARRIVED_H >> 1);
			for(int i = 0; i < size - 1; i++) {//到达箭头
				g.drawImage(ARRIVED, arrowX + xGap * i, arrowY,
						ARRIVED_W, ARRIVED_H, null);
			}
			for(int i = size - 1; i < 3; i++) {//未到达箭头
				g.drawImage(TOARRIVE, arrowX + xGap * i, arrowY,
						TOARRIVE_W, TOARRIVE_H, null);
			}
		}else {//同一城市
			//送件人营业厅
			g.drawImage(CITY_IMG_MAP.get(source), startX, startY,
					CITY_W, CITY_H, null);
			g.drawImage(SENDER, startX, strH,
					SENDER_W, SENDER_H, null);
			//收件人营业厅
			g.drawImage(CITY_IMG_MAP.get(dest), startX + xGap, startY,
					CITY_W, CITY_H, null);
			g.drawImage(RECEIVER, startX + xGap, strH,
					RECEIVER_W, RECEIVER_H, null);
			//箭头
			int arrowX = startX + CITY_W;
			int arrowY = startY + (CITY_H - ARRIVED_H >> 1);
			if(node.size() < 2) {
				g.drawImage(TOARRIVE, arrowX, arrowY,
						TOARRIVE_W, TOARRIVE_H, null);
			}else {
				g.drawImage(ARRIVED, arrowX, arrowY,
						ARRIVED_W, ARRIVED_H, null);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(BACKGROUND, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	public void setClose() {

		//最小化按钮
		JButton minButton=new JButton("");
		minButton.setBounds(ExpressFrame.FRAME_W-85, 0, 40, 30);
		minButton.setIcon(new ImageIcon("picture/FrameButton/mini.png"));
		minButton.addActionListener(new ActionListener(){
			@Override
			  public void actionPerformed(ActionEvent e) {
				ExpressFrame.getInstance().setExtendedState(JFrame.ICONIFIED);
			  }
		});
		
		
		 //关闭按钮
		 JButton closeButton = new JButton("");
		 closeButton.setIcon(new ImageIcon("picture/FrameButton/close.png"));
		 closeButton.setBounds(ExpressFrame.FRAME_W-45, 0, 40, 30);
		 closeButton.addActionListener(new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
		  System.exit(0);
		  }
		 });
		 this.add(closeButton);
		 this.add(minButton);  

		 }
}
