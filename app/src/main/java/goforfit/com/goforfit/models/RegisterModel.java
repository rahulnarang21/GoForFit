package goforfit.com.goforfit.models;

public class RegisterModel {
    /**
     * IsSuccess : true
     * Message : User Registered Successfully
     */

    private boolean IsSuccess;
    private String Message;

    public boolean isIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
