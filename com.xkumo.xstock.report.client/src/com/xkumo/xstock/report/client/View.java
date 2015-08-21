/*
 * View.java
 */

package com.xkumo.xstock.report.client;

import com.xkumo.xstock.core.data.StockInfo;
import com.xkumo.xstock.report.client.data.DBFactory;
import com.xkumo.xstock.report.client.service.BonusStockService;
import com.xkumo.xstock.report.client.service.ResumeStockService;
import com.xkumo.xstock.report.client.service.StockService;
import com.xkumo.xstock.report.client.service.StockSinaService;
import com.xkumo.xstock.report.client.service.StockSuspensionService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The application's main frame.
 */
public class View extends FrameView {

    /**
     * 复牌报表服务
     */
    private ResumeStockService m_ResumeStockService;

    /**
     * 除权送股报表服务
     */
    private BonusStockService m_BonusStockService;

    public View(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

        m_ResumeStockService = new ResumeStockService();
        m_BonusStockService = new BonusStockService();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = App.getApplication().getMainFrame();
            aboutBox = new AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        App.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panelResumeStockReport = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtResumeStockReportDate = new javax.swing.JTextField();
        txtResumeStockReportFileFullNameAndPath = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnResumeStockReportFileSelector = new javax.swing.JButton();
        btnResumeStockReportRun = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelResumeStockReport1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBonusStockReportDate = new javax.swing.JTextField();
        txtBonusStockReportFileFullNameAndPath = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnBonusStockReportFileSelector1 = new javax.swing.JButton();
        btnBonusStockReportRun = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuResumeStockReport = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        panelResumeStockReport.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelResumeStockReport.setName("panelResumeStockReport"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.xkumo.xstock.report.client.App.class).getContext().getResourceMap(View.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtResumeStockReportDate.setText(resourceMap.getString("txtResumeStockReportDate.text")); // NOI18N
        txtResumeStockReportDate.setName("txtResumeStockReportDate"); // NOI18N

        txtResumeStockReportFileFullNameAndPath.setName("txtResumeStockReportFileFullNameAndPath"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        btnResumeStockReportFileSelector.setText(resourceMap.getString("btnResumeStockReportFileSelector.text")); // NOI18N
        btnResumeStockReportFileSelector.setName("btnResumeStockReportFileSelector"); // NOI18N
        btnResumeStockReportFileSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumeStockReportFileSelectorActionPerformed(evt);
            }
        });

        btnResumeStockReportRun.setText(resourceMap.getString("btnResumeStockReportRun.text")); // NOI18N
        btnResumeStockReportRun.setName("btnResumeStockReportRun"); // NOI18N
        btnResumeStockReportRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumeStockReportRunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelResumeStockReportLayout = new javax.swing.GroupLayout(panelResumeStockReport);
        panelResumeStockReport.setLayout(panelResumeStockReportLayout);
        panelResumeStockReportLayout.setHorizontalGroup(
            panelResumeStockReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumeStockReportLayout.createSequentialGroup()
                .addGroup(panelResumeStockReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumeStockReportLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelResumeStockReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelResumeStockReportLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtResumeStockReportDate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelResumeStockReportLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtResumeStockReportFileFullNameAndPath, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnResumeStockReportFileSelector))))
                    .addGroup(panelResumeStockReportLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelResumeStockReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResumeStockReportRun)
                            .addComponent(jLabel1))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelResumeStockReportLayout.setVerticalGroup(
            panelResumeStockReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumeStockReportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumeStockReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumeStockReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelResumeStockReportLayout.createSequentialGroup()
                            .addComponent(txtResumeStockReportDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelResumeStockReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtResumeStockReportFileFullNameAndPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(btnResumeStockReportFileSelector)))
                        .addComponent(jLabel2))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResumeStockReportRun, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelResumeStockReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelResumeStockReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        panelResumeStockReport1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelResumeStockReport1.setName("panelResumeStockReport1"); // NOI18N

        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtBonusStockReportDate.setName("txtBonusStockReportDate"); // NOI18N

        txtBonusStockReportFileFullNameAndPath.setName("txtBonusStockReportFileFullNameAndPath"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        btnBonusStockReportFileSelector1.setText(resourceMap.getString("btnBonusStockReportFileSelector1.text")); // NOI18N
        btnBonusStockReportFileSelector1.setName("btnBonusStockReportFileSelector1"); // NOI18N
        btnBonusStockReportFileSelector1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBonusStockReportFileSelector1ActionPerformed(evt);
            }
        });

        btnBonusStockReportRun.setText(resourceMap.getString("btnBonusStockReportRun.text")); // NOI18N
        btnBonusStockReportRun.setName("btnBonusStockReportRun"); // NOI18N
        btnBonusStockReportRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBonusStockReportRunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelResumeStockReport1Layout = new javax.swing.GroupLayout(panelResumeStockReport1);
        panelResumeStockReport1.setLayout(panelResumeStockReport1Layout);
        panelResumeStockReport1Layout.setHorizontalGroup(
            panelResumeStockReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumeStockReport1Layout.createSequentialGroup()
                .addGroup(panelResumeStockReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumeStockReport1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelResumeStockReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelResumeStockReport1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBonusStockReportDate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelResumeStockReport1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBonusStockReportFileFullNameAndPath, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBonusStockReportFileSelector1))))
                    .addGroup(panelResumeStockReport1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelResumeStockReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBonusStockReportRun)
                            .addComponent(jLabel4))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelResumeStockReport1Layout.setVerticalGroup(
            panelResumeStockReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumeStockReport1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumeStockReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumeStockReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelResumeStockReport1Layout.createSequentialGroup()
                            .addComponent(txtBonusStockReportDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelResumeStockReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBonusStockReportFileFullNameAndPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(btnBonusStockReportFileSelector1)))
                        .addComponent(jLabel5))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBonusStockReportRun, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelResumeStockReport1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelResumeStockReport1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(com.xkumo.xstock.report.client.App.class).getContext().getActionMap(View.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        menuResumeStockReport.setText(resourceMap.getString("menuResumeStockReport.text")); // NOI18N
        menuResumeStockReport.setName("menuResumeStockReport"); // NOI18N
        menuResumeStockReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuResumeStockReportActionPerformed(evt);
            }
        });
        jMenu1.add(menuResumeStockReport);

        menuBar.add(jMenu1);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 538, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void menuResumeStockReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuResumeStockReportActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            StockService service = new StockService();
            String dayFormat = this.txtResumeStockReportDate.getText();
            Date today = new Date();
            List<StockInfo> result = service.getAllStockInfo();
            StockSuspensionService suspensionService = new StockSuspensionService();
            List<StockInfo> resultResume = suspensionService.getStockResumeTradeDayList(dayFormat);
            System.out.println("恢复交易日:" + dayFormat);
            System.out.println("全部" + result.size() + "恢复交易" + resultResume.size());
            System.out.println(resultResume.toString());
            StockSinaService sinaService = new StockSinaService();
            Connection connection = null;
            connection = DBFactory.CreateConnection();
            try {
                for (StockInfo info : resultResume) {
                    int sdays = suspensionService.getStockSuspensionDays(info.getStockCode(), info.getStockExchangeCode(), dayFormat, connection);
                    System.out.println(info.getStockName() + "   " + info.getStockCode() + "   " + sinaService.getStockSinaURL(info.getStockCode(), info.getStockExchangeCode()) + "   " + sdays + "  " + service.getDaysOfClosedAtMatPrice(info.getStockCode(), info.getStockExchangeCode(), dayFormat) + "   " + service.getDaysOfStartAndClosedAtMatPrice(info.getStockCode(), info.getStockExchangeCode(), dayFormat));
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
            JOptionPane.showMessageDialog(null, "hello", "出错", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuResumeStockReportActionPerformed

    private void btnResumeStockReportFileSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumeStockReportFileSelectorActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser (".");

        int result = file.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION)
        {

        /*获得你选择的文件绝对路径。并输出。当然，我们获得这个路径后还可以做很多的事。*/
           String path = file.getSelectedFile().getAbsolutePath();
           txtResumeStockReportFileFullNameAndPath.setText(path);
        }
        else
        {
            //Do Nothing
        }
}//GEN-LAST:event_btnResumeStockReportFileSelectorActionPerformed

    private void btnResumeStockReportRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumeStockReportRunActionPerformed
        try
        {
            this.m_ResumeStockService.report(this.txtResumeStockReportDate.getText(), this.txtResumeStockReportFileFullNameAndPath.getText());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "出错",  JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_btnResumeStockReportRunActionPerformed

    private void btnBonusStockReportFileSelector1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBonusStockReportFileSelector1ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser (".");

        int result = file.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION)
        {

        /*获得你选择的文件绝对路径。并输出。当然，我们获得这个路径后还可以做很多的事。*/
           String path = file.getSelectedFile().getAbsolutePath();
           this.txtBonusStockReportFileFullNameAndPath.setText(path);
        }
        else
        {
            //Do Nothing
        }
}//GEN-LAST:event_btnBonusStockReportFileSelector1ActionPerformed

    private void btnBonusStockReportRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBonusStockReportRunActionPerformed
        // TODO add your handling code here:
         try
        {
            this.m_BonusStockService.report(this.txtBonusStockReportDate.getText(), this.txtBonusStockReportFileFullNameAndPath.getText());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "出错",  JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_btnBonusStockReportRunActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBonusStockReportFileSelector1;
    private javax.swing.JButton btnBonusStockReportRun;
    private javax.swing.JButton btnResumeStockReportFileSelector;
    private javax.swing.JButton btnResumeStockReportRun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuResumeStockReport;
    private javax.swing.JPanel panelResumeStockReport;
    private javax.swing.JPanel panelResumeStockReport1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextField txtBonusStockReportDate;
    private javax.swing.JTextField txtBonusStockReportFileFullNameAndPath;
    private javax.swing.JTextField txtResumeStockReportDate;
    private javax.swing.JTextField txtResumeStockReportFileFullNameAndPath;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}