package com.whoami.attributebaseaccesscontrol.objects;

/**
 * This is response object to map XACML Response for client users.
 */
public class ResponseObject {

    /**
     * Generic message for the client.
     */
    private String message;

    public ResponseObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "message='" + message + '\'' +
                '}';
    }
}
