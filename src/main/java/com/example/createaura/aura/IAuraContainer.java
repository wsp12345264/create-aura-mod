package com.example.createaura.aura;

/**
 * 鐏垫皵瀹瑰櫒鎺ュ彛 - 鎵€鏈夐渶瑕佸瓨鍌?浼犺緭鐏垫皵鐨勬柟鍧楀疄浣撻兘闇€瀹炵幇姝ゆ帴鍙? */
public interface IAuraContainer {

    /**
     * 鑾峰彇褰撳墠瀛樺偍鐨勭伒姘旈噺
     */
    double getAuraStored();

    /**
     * 鑾峰彇鏈€澶х伒姘斿閲?     */
    double getMaxAura();

    /**
     * 鎺ユ敹鐏垫皵锛岃繑鍥炲疄闄呮帴鏀剁殑閲?     */
    double receiveAura(double amount, boolean simulate);

    /**
     * 鎻愬彇鐏垫皵锛岃繑鍥炲疄闄呮彁鍙栫殑閲?     */
    double extractAura(double amount, boolean simulate);

    /**
     * 妫€鏌ユ槸鍚﹀彲浠ユ帴鏀剁伒姘?     */
    boolean canReceive();

    /**
     * 妫€鏌ユ槸鍚﹀彲浠ユ彁鍙栫伒姘?     */
    boolean canExtract();
}
