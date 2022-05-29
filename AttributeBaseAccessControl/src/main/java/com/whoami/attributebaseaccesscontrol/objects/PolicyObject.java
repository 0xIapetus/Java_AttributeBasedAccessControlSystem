package com.whoami.attributebaseaccesscontrol.objects;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * This is policy object class that will later represent a XACML Policy
 */
public class PolicyObject {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("policyName")
    public String policyName;
    @JsonProperty("ruleCombAl")
    public String ruleCombAl;
    @JsonProperty("policyDesc")
    public String policyDesc;
    @JsonProperty("policyTargetResource")
    public String policyTargetResource;
    @JsonProperty("policyTargetAction")
    public String policyTargetAction;
    @JsonProperty("policyTargetSubject")
    public String policyTargetSubject;
    @JsonProperty("RuleObject")
    public List<RuleObject> rules = new ArrayList<>();


    public PolicyObject(String policyName, String ruleCombAl, String policyDesc, String policyTargetResource, String policyTargetAction, String policyTargetSubject, List<RuleObject> rules) {
        super();
        this.policyName = policyName;
        this.ruleCombAl = ruleCombAl;
        this.policyDesc = policyDesc;
        this.policyTargetResource = policyTargetResource;
        this.policyTargetAction = policyTargetAction;
        this.policyTargetSubject = policyTargetSubject;
        this.rules = rules;
    }

    public PolicyObject() {
    }


    public String getPolicyName() {
        return policyName;
    }


    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }


    public String getRuleCombAl() {
        return ruleCombAl;
    }


    public void setRuleCombAl(String ruleCombAl) {
        this.ruleCombAl = ruleCombAl;
    }


    public String getPolicyDesc() {
        return policyDesc;
    }


    public void setPolicyDesc(String policyDesc) {
        this.policyDesc = policyDesc;
    }


    public String getPolicyTargetResource() {
        return policyTargetResource;
    }


    public void setPolicyTargetResource(String policyTargetResource) {
        this.policyTargetResource = policyTargetResource;
    }

    public String getPolicyTargetAction() {
        return policyTargetAction;
    }


    public void setPolicyTargetAction(String policyTargetAction) {
        this.policyTargetAction = policyTargetAction;
    }


    public String getPolicyTargetSubject() {
        return policyTargetSubject;
    }


    public void setPolicyTargetSubject(String policyTargetSubject) {
        this.policyTargetSubject = policyTargetSubject;
    }

    public List<RuleObject> getRules() {
        return rules;
    }

    public void setRules(List<RuleObject> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "PolicyObject{" +
                "policyName='" + policyName + '\'' +
                ", ruleCombAl='" + ruleCombAl + '\'' +
                ", policyDesc='" + policyDesc + '\'' +
                ", policyTargetResource='" + policyTargetResource + '\'' +
                ", policyTargetAction='" + policyTargetAction + '\'' +
                ", policyTargetSubject='" + policyTargetSubject + '\'' +
                ", rules=" + rules +
                '}';
    }

}
