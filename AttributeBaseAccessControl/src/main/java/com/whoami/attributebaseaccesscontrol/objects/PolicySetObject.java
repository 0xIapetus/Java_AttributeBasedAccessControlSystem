package com.whoami.attributebaseaccesscontrol.objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is policy-set object class that will later represent a XACML Policy-Set
 */
public class PolicySetObject {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("policySetName")
    public String policySetName;

    @JsonProperty("policySetCombAlg")
    public String policySetCombAlg;

    @JsonProperty("policySetDesc")
    public String policySetDesc;

    @JsonProperty("policyTargetResource")
    public String policyTargetResource;

    @JsonProperty("policyTargetAction")
    public String policyTargetAction;

    @JsonProperty("policyTargetSubject")
    public String policyTargetSubject;

    @JsonProperty("policyIdList")
    private List<Map<String, String>> policyIdList = new ArrayList<>();



    public PolicySetObject(String policySetName, String policySetCombAlg, String policySetDesc, String policyTargetResource, String policyTargetAction, String policyTargetSubject, List<Map<String, String>> policyIdList) {
        this.policySetName = policySetName;
        this.policySetCombAlg = policySetCombAlg;
        this.policySetDesc = policySetDesc;
        this.policyTargetResource = policyTargetResource;
        this.policyTargetAction = policyTargetAction;
        this.policyTargetSubject = policyTargetSubject;
        this.policyIdList = policyIdList;
    }

    public PolicySetObject() {

    }

    public String getPolicySetDesc() {
        return policySetDesc;
    }

    public void setPolicySetDesc(String policySetDesc) {
        this.policySetDesc = policySetDesc;
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


    public String getPolicySetName() {
        return policySetName;
    }

    public void setPolicySetName(String policySetName) {
        this.policySetName = policySetName;
    }

    public String getPolicySetCombAlg() {
        return policySetCombAlg;
    }

    public void setPolicySetCombAlg(String policySetCombAlg) {
        this.policySetCombAlg = policySetCombAlg;
    }

    public List<Map<String, String>> getPolicyIdList() {
        return policyIdList;
    }

    public void setPolicyIdList(List<Map<String, String>> policyIdList) {

        this.policyIdList = policyIdList;
    }

    /**
     * We Convert A list of Maps to a Single Array List
     * So we map the JSON
     * @param policyIdList
     * @return
     */
    public ArrayList ConvertMapsToArray (List<Map<String, String>> policyIdList) {
        ArrayList<String> valueList = new ArrayList<>();
        for (Map policyid : policyIdList) {
            for (Object value : policyid.values() ) {
                valueList.add((String) value);
            }
        }
        return valueList;
    }

    @Override
    public String toString() {
        return "PolicySetObject{" +
                "policySetName='" + policySetName + '\'' +
                ", policySetCombAlg='" + policySetCombAlg + '\'' +
                ", policySetDesc='" + policySetDesc + '\'' +
                ", policyTargetResource='" + policyTargetResource + '\'' +
                ", policyTargetAction='" + policyTargetAction + '\'' +
                ", policyTargetSubject='" + policyTargetSubject + '\'' +
                ", policyIdList=" + policyIdList +
                '}';
    }
}
