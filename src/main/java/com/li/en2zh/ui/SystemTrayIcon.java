package com.li.en2zh.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;

import javax.swing.ImageIcon;

import javafx.application.Platform;

public class SystemTrayIcon {
    private TrayIcon trayIcon;

    public SystemTrayIcon(String iconPath, MainApp app) {
        try {
            URL iconURL = getClass().getResource(iconPath);
            if (iconURL == null) {
                throw new IllegalArgumentException("图标资源未找到: " + iconPath);
            }

            Image icon = new ImageIcon(iconURL).getImage();
            trayIcon = new TrayIcon(icon);
            trayIcon.setImageAutoSize(true);

            PopupMenu popup = new PopupMenu();
            MenuItem showItem = new MenuItem("show");
            showItem.addActionListener(e -> Platform.runLater(() -> app.showFloatingButton()));
            MenuItem exitItem = new MenuItem("exit");
            exitItem.addActionListener(e -> Platform.runLater(() -> System.exit(0)));

            popup.add(showItem);
            popup.addSeparator();
            popup.add(exitItem);

            trayIcon.setPopupMenu(popup);
            SystemTray.getSystemTray().add(trayIcon);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
}
