package ui.managerui.organizationui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JSeparator;

import businessLogic.businessLogicController.managerController.OrganizationManagementController;
import businessLogicService.managerBLService.OrganizationManagementBLService;
import ui.baseui.DetailPanel;
import ui.baseui.LimpidButton;
import ui.managerui.SearchPanel;
import vo.managerVO.OrganizationVO;
import vo.managerVO.StaffVO;

@SuppressWarnings("serial")
public class ModifyOrganization extends DetailPanel{
	
	private OrganizationManagementBLService organization = new OrganizationManagementController();

	private SearchPanel orgId = new SearchPanel("机构编号", WORD_FONT, 0, 0, DETAIL_PANEL_W, DETAIL_PANEL_H / 6);
	
	private JSeparator separator = new JSeparator();
	
	private OrganizationInfoPanel orgPanel = null;
	
	private JLabel tip = new JLabel();
	
	private LimpidButton modify = new LimpidButton("","picture/修改.png");
	
	private LimpidButton cancel = new LimpidButton("","picture/取消.png");
	
	private static Font WORD_FONT = new Font("宋体", Font.PLAIN, 12);
	
	private static final int BUTTON_W = 80;
	
	private static final int BUTTON_H = 30;
	
	public ModifyOrganization() {
		//机构信息查询面板
		this.add(this.orgId);
		//查询面板与信息面板的分割线
		this.separator.setBounds(0, this.orgId.getHeight(), DETAIL_PANEL_W, 10);
		this.add(this.separator);

		//修改按钮
		this.modify.setBounds(orgId.getWidth() >> 1, (int)(orgId.getHeight()* 0.1), BUTTON_W, BUTTON_H);
		this.modify.setFont(WORD_FONT);
		this.modify.setVisible(false);
		this.add(this.modify);
		//取消按钮
		this.cancel.setBounds(this.modify.getX() + (BUTTON_W << 1), this.modify.getY(), BUTTON_W, BUTTON_H);
		this.cancel.setFont(WORD_FONT);
		this.cancel.setVisible(false);
		this.add(this.cancel);
		//提示标签
		this.tip.setBounds(BUTTON_W, this.modify.getY(), BUTTON_W << 1, BUTTON_H);
		this.tip.setFont(WORD_FONT);
		tip.setForeground(Color.RED);
		this.add(this.tip);
		//增加事件监听
		this.addListener();
	}
	
	private void addListener() {
		//确定查询按钮
		this.orgId.getOk().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tip.setText("");
				//获得用户输入的机构编号
				String id = orgId.getIdText();
				//查询机构信息
				OrganizationVO vo = organization.findOrganization(id);
				//如果没有找到机构信息
				if(vo == null) {
					//提示用户没有该机构信息
					orgId.setText("没有该机构信息", WORD_FONT, Color.RED);
					//刷新面板
					repaint();
					return ;
				}
				else {
					orgId.removeText();
				}
				//查询人员信息
				List<StaffVO> list = organization.getStaffInfos(id);
				//显示机构信息和人员信息
				if(orgPanel != null) orgPanel.setVisible(false);
				orgPanel = new OrganizationInfoPanel(orgId.getX(), orgId.getHeight(),
						DETAIL_PANEL_W, DETAIL_PANEL_H * 5 / 6, list);
				orgPanel.setOrganizationInfo(vo);
				orgPanel.disableStaffInfo();
				add(orgPanel);
				//询问是否确认修改
				tip.setText("确定要修改吗");
				//隐藏查询按钮
				orgId.setVisible(false);
				//显示修改按钮
				modify.setVisible(true);
				cancel.setVisible(true);
				//刷新面板
				repaint();
			}
		});
		//取消查询按钮
		this.orgId.getCancel().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//隐藏机构和人员信息面板
				orgPanel.setVisible(false);
				//消除提示信息
				orgId.removeText();
				tip.setText("");
				
				repaint();
			}
		});
		//确定修改按钮
		this.modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//验证修改之后的信息是否合法
				AddOrganization add = new AddOrganization();
				if(!add.verifyInput(orgPanel.getIdText(), orgPanel.getNameText(), tip, true)) return ;
				//保存对机构信息的修改
				OrganizationVO vo = orgPanel.createOrganizationVO();
				organization.modifyOrganization(vo);
				//返回查询界面
				backToInquire(true);
			}
		});
		//取消修改按钮
		this.cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//返回查询界面
				backToInquire(false);
			}
		});
	}
	
	private void backToInquire(boolean isModify) {
		//隐藏查询按钮
		modify.setVisible(false);
		cancel.setVisible(false);
		//隐藏机构信息面板
		orgPanel.setVisible(false);
		//显示查询面板
		orgId.setVisible(true);
		//消除错误提示
		if(isModify) {
			tip.setBounds(10, this.orgId.getIDLabel().getY() + 30, 240, 30);
			this.tip.setForeground(Color.BLUE);
			this.tip.setText("修改成功");
		}else {
			tip.setBounds(80, this.modify.getY(), 240, 30);
			this.tip.setText("");
		}
		//消除用户输入的人员编号
		this.orgId.clearIdText();
		
		repaint();
	}
}
