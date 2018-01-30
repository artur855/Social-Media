package com.arthurzera.website.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService implements INotificationService {

    public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

    @Autowired
    private HttpSession httpSession;

    @Override
    public void addInfoMessage(String msg) {
        addNotificationMessage(NotificationMessageType.INFO, msg);
    }

    @Override
    public void addDangerMessage(String msg) {
        addNotificationMessage(NotificationMessageType.DANGER, msg);
    }

    @Override
    public void addSuccessMessage(String msg) {
        addNotificationMessage(NotificationMessageType.SUCCESS, msg);
    }

    @Override
    public void addWarningMessage(String msg) {
        addNotificationMessage(NotificationMessageType.WARNING, msg);
    }

    private void addNotificationMessage(NotificationMessageType type, String text) {
        List<NotificationMessage> notifyMessages = (List<NotificationMessage>) httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
        if (notifyMessages == null) {
            notifyMessages = new ArrayList<>();
        }
        notifyMessages.add(new NotificationMessage(type, text));
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notifyMessages);
    }

    public enum NotificationMessageType {
        INFO, DANGER, WARNING, SUCCESS;
    }

    public class NotificationMessage {

        NotificationMessageType type;
        String text;

        public NotificationMessage(NotificationMessageType type, String text) {
            this.type = type;
            this.text = text;
        }

        public NotificationMessageType getType() {
            return this.type;
        }

        public String getText() {
            return this.text;
        }
    }

}
