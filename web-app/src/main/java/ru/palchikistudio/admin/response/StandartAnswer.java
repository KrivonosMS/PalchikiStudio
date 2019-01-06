package ru.palchikistudio.admin.response;

/**
 * Created by Admin on 31.12.2018.
 */
public class StandartAnswer {
    private boolean success;
    private String msg;

    public StandartAnswer(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }
}
