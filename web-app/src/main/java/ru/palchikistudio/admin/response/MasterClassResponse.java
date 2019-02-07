package ru.palchikistudio.admin.response;

import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 24.12.2018.
 */
public class MasterClassResponse {
    private boolean success;

    private String errorMessaage;
    private List<MasterClass> data;
    private int total;
    public MasterClassResponse(boolean success, List<MasterClass> data, int total) {
        this.success = success;
        this.data = data;
        this.total = total;
    }

    public MasterClassResponse(String errormessage) {
        this.errorMessaage = errormessage;
        this.success = false;
    }


    public MasterClassResponse(List<MasterClass> data, int total) {
        this.data = data;
        this.total = total;
        this.success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessaage() {
        return errorMessaage;
    }

    public void setErrorMessaage(String errorMessaage) {
        this.errorMessaage = errorMessaage;
    }

    public List<MasterClass> getData() {
        return data;
    }

    public void setData(List<MasterClass> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

