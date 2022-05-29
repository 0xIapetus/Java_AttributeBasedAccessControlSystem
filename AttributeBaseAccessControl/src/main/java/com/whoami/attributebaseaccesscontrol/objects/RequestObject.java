package com.whoami.attributebaseaccesscontrol.objects;

/**
 *
 * This is Request object class that will later represent a XACML Request
 */
public class RequestObject {

    String Resource;
    String Subject;
    String Action;
    String Location;
    String Time;
    String IpAddress;
    String IpAddress1;
    String Device;

    @Override
    public String toString() {
        return "RequestObject{" +
                "Resource='" + Resource + '\'' +
                ", Subject='" + Subject + '\'' +
                ", Action='" + Action + '\'' +
                ", Location='" + Location + '\'' +
                ", Time='" + Time + '\'' +
                ", IpAddress='" + IpAddress + '\'' +
                ", IpAddress1='" + IpAddress1 + '\'' +
                ", Device='" + Device + '\'' +
                '}';
    }

    public RequestObject() {
    }

    public RequestObject(String resource, String subject, String action, String location, String time, String ipAddress, String ipAddress1, String device) {
        Resource = resource;
        Subject = subject;
        Action = action;
        Location = location;
        Time = time;
        IpAddress = ipAddress;
        IpAddress1 = ipAddress1;
        Device = device;
    }

    public String getIpAddress1() {
        return IpAddress1;
    }

    public void setIpAddress1(String ipAddress1) {
        IpAddress1 = ipAddress1;
    }

    public String getResource() {
        return Resource;
    }

    public void setResource(String resource) {
        Resource = resource;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

}