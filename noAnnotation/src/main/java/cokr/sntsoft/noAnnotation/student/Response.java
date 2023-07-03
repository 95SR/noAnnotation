package cokr.sntsoft.noAnnotation.student;

import java.util.ArrayList;

public class Response {
    private boolean success;
    private int status;

     public Response(){
        success = true;
        status = 200;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

   
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    



    
    
}