package com.arthurzera.website.services;

public interface INotificationService {

    void addInfoMessage(String msg);

    void addDangerMessage(String msg);

    void addSuccessMessage(String msg);

    void addWarningMessage(String msg);
}
