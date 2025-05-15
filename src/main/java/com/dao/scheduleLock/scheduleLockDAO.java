package com.dao.scheduleLock;

public interface scheduleLockDAO  {
    boolean acquireLock(String lockName);
    void releaseLock(String lockName);
    void resetLock();
    String getServerIpAddress();
}