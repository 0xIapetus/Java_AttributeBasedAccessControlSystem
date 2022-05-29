package com.whoami.attributebaseaccesscontrol.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
/**
 * This is rule object which is part of XACML Policy generation.
 */
public class RuleObject {
    @JsonIgnoreProperties(ignoreUnknown =true)
    @JsonProperty("rule1Effect")
   public String rule1Effect;

    @JsonProperty("rule1Name")
   public String rule1Name;

    @JsonProperty("rule1TargetResource")
   public String rule1TargetResource;

    @JsonProperty("rule1TargetAction")
   public String rule1TargetAction;

    @JsonProperty("rule1TargetSubject")
   public String rule1TargetSubject;

    @JsonProperty("rule1location1")
   public String rule1location1;

    @JsonProperty("rule1location2")
   public String rule1location2;

    @JsonProperty("rule1timeFrom")
   public String rule1timeFrom;

    @JsonProperty("rule1timeUntil")
   public String rule1timeUntil;

    @JsonProperty("rule1ipRequest")
   public String rule1ipRequest;

    @JsonProperty("rule1ipRecipient")
   public String rule1ipRecipient;

    @JsonProperty("rule1typeofdev1")
   public String rule1typeofdev1;

    @JsonProperty("rule1typeofdev2")
   public String rule1typeofdev2;

    public RuleObject(String rule1Effect, String rule1Name, String rule1TargetResource, String rule1TargetAction, String rule1TargetSubject, String rule1location1, String rule1location2, String rule1timeFrom, String rule1timeUntil, String rule1ipRequest, String rule1ipRecipient, String rule1typeofdev1, String rule1typeofdev2) {
        super();
        this.rule1Effect = rule1Effect;
        this.rule1Name = rule1Name;
        this.rule1TargetResource = rule1TargetResource;
        this.rule1TargetAction = rule1TargetAction;
        this.rule1TargetSubject = rule1TargetSubject;
        this.rule1location1 = rule1location1;
        this.rule1location2 = rule1location2;
        this.rule1timeFrom = rule1timeFrom;
        this.rule1timeUntil = rule1timeUntil;
        this.rule1ipRequest = rule1ipRequest;
        this.rule1ipRecipient = rule1ipRecipient;
        this.rule1typeofdev1 = rule1typeofdev1;
        this.rule1typeofdev2 = rule1typeofdev2;
    }

    public RuleObject() {
    }

    public String getRule1Effect() {
        return rule1Effect;
    }

    public void setRule1Effect(String rule1Effect) {
        this.rule1Effect = rule1Effect;
    }

    public String getRule1Name() {
        return rule1Name;
    }

    public void setRule1Name(String rule1Name) {
        this.rule1Name = rule1Name;
    }

    public String getRule1TargetResource() {
        return rule1TargetResource;
    }

    public void setRule1TargetResource(String rule1TargetResource) {
        this.rule1TargetResource = rule1TargetResource;
    }

    public String getRule1TargetAction() {
        return rule1TargetAction;
    }

    public void setRule1TargetAction(String rule1TargetAction) {
        this.rule1TargetAction = rule1TargetAction;
    }

    public String getRule1TargetSubject() {
        return rule1TargetSubject;
    }

    public void setRule1TargetSubject(String rule1TargetSubject) {
        this.rule1TargetSubject = rule1TargetSubject;
    }

    public String getRule1location1() {
        return rule1location1;
    }

    public void setRule1location1(String rule1location1) {
        this.rule1location1 = rule1location1;
    }

    public String getRule1location2() {
        return rule1location2;
    }

    public void setRule1location2(String rule1location2) {
        this.rule1location2 = rule1location2;
    }

    public String getRule1timeFrom() {
        return rule1timeFrom;
    }

    public void setRule1timeFrom(String rule1timeFrom) {
        this.rule1timeFrom = rule1timeFrom;
    }

    public String getRule1timeUntil() {
        return rule1timeUntil;
    }

    public void setRule1timeUntil(String rule1timeUntil) {
        this.rule1timeUntil = rule1timeUntil;
    }

    public String getRule1ipRequest() {
        return rule1ipRequest;
    }

    public void setRule1ipRequest(String rule1ipRequest) {
        this.rule1ipRequest = rule1ipRequest;
    }

    public String getRule1ipRecipient() {
        return rule1ipRecipient;
    }

    public void setRule1ipRecipient(String rule1ipRecipient) {
        this.rule1ipRecipient = rule1ipRecipient;
    }

    public String getRule1typeofdev1() {
        return rule1typeofdev1;
    }

    public void setRule1typeofdev1(String rule1typeofdev1) {
        this.rule1typeofdev1 = rule1typeofdev1;
    }

    public String getRule1typeofdev2() {
        return rule1typeofdev2;
    }

    public void setRule1typeofdev2(String rule1typeofdev2) {
        this.rule1typeofdev2 = rule1typeofdev2;
    }

    @Override
    public String toString() {
        return "RuleObject{" +
                "rule1Effect='" + rule1Effect + '\'' +
                ", rule1Name='" + rule1Name + '\'' +
                ", rule1TargetResource='" + rule1TargetResource + '\'' +
                ", rule1TargetAction='" + rule1TargetAction + '\'' +
                ", rule1TargetSubject='" + rule1TargetSubject + '\'' +
                ", rule1location1='" + rule1location1 + '\'' +
                ", rule1location2='" + rule1location2 + '\'' +
                ", rule1timeFrom='" + rule1timeFrom + '\'' +
                ", rule1timeUntil='" + rule1timeUntil + '\'' +
                ", rule1ipRequest='" + rule1ipRequest + '\'' +
                ", rule1ipRecipient='" + rule1ipRecipient + '\'' +
                ", rule1typeofdev1='" + rule1typeofdev1 + '\'' +
                ", rule1typeofdev2='" + rule1typeofdev2 + '\'' +
                '}';
    }
}
