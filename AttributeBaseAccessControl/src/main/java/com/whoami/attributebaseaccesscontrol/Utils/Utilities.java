package com.whoami.attributebaseaccesscontrol.Utils;
import com.whoami.attributebaseaccesscontrol.exceptions.XACMLNotFoundException;
import com.whoami.attributebaseaccesscontrol.objects.RuleObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wso2.balana.Balana;
import org.wso2.balana.PDP;
import org.wso2.balana.PDPConfig;
import org.wso2.balana.finder.AttributeFinder;
import org.wso2.balana.finder.AttributeFinderModule;
import org.wso2.balana.finder.impl.FileBasedPolicyFinderModule;
import org.wso2.balana.utils.Constants.PolicyConstants;
import org.wso2.balana.utils.policy.dto.*;
import org.wso2.carbon.identity.entitlement.common.dto.RequestDTO;
import org.wso2.carbon.identity.entitlement.common.dto.RowDTO;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
    private static Balana balana;

    /**
     * This function creates a Resource Target
     * @param policyTargetResource
     * @return
     */
    protected static MatchElementDTO TargetMatchResourceBuilding(String policyTargetResource) {

        if (policyTargetResource != null) {

            MatchElementDTO targetMatchResource = new MatchElementDTO();
//            "urn:oasis:names:tc:xacml:1.0:function:anyURI-equal"
            targetMatchResource.setMatchId("urn:oasis:names:tc:xacml:1.0:function:string-equal");

            AttributeValueElementDTO attributeValueElementDTOPolicy = new AttributeValueElementDTO();
                                                    //            http://www.w3.org/2001/XMLSchema#anyURI
            attributeValueElementDTOPolicy.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeValueElementDTOPolicy.setAttributeValue(policyTargetResource);

            AttributeDesignatorDTO attributeDesignatorDTOPolicy = new AttributeDesignatorDTO();
            attributeDesignatorDTOPolicy.setAttributeId("urn:oasis:names:tc:xacml:2.0:resource:target-namespace");
            attributeDesignatorDTOPolicy.setCategory("urn:oasis:names:tc:xacml:3.0:attribute-category:resource");
                                        //            http://www.w3.org/2001/XMLSchema#anyURI
            attributeDesignatorDTOPolicy.setDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeDesignatorDTOPolicy.setMustBePresent("true");

            targetMatchResource.setAttributeValueElementDTO(attributeValueElementDTOPolicy);
            targetMatchResource.setAttributeDesignatorDTO(attributeDesignatorDTOPolicy);

            return targetMatchResource;
        }
        return null;
    }

    /**
     * This function creates an Action Target
     * @param targetAction
     * @return
     */
    protected static MatchElementDTO TargetMatchActionBuilding(String targetAction) {

        if (targetAction != null) {

            MatchElementDTO targetMatchAction = new MatchElementDTO();
            targetMatchAction.setMatchId("urn:oasis:names:tc:xacml:1.0:function:string-equal");

            AttributeValueElementDTO attributeValueElementDTO = new AttributeValueElementDTO();
            attributeValueElementDTO.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeValueElementDTO.setAttributeValue(targetAction);

            AttributeDesignatorDTO attributeDesignatorDTO = new AttributeDesignatorDTO();
            attributeDesignatorDTO.setAttributeId("urn:oasis:names:tc:xacml:1.0:action:action-id");
            attributeDesignatorDTO.setCategory("urn:oasis:names:tc:xacml:3.0:attribute-category:action");
            attributeDesignatorDTO.setDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeDesignatorDTO.setMustBePresent("true");

            targetMatchAction.setAttributeValueElementDTO(attributeValueElementDTO);
            targetMatchAction.setAttributeDesignatorDTO(attributeDesignatorDTO);

            return targetMatchAction;
        }
        return null;
    }

    /**
     * This function creates a Subject Target
     * @param targetSubject
     * @return
     */
    protected static MatchElementDTO TargetMatchSubjectBuilding(String targetSubject) {

        if (targetSubject != null) {

            MatchElementDTO targetMatchSubject = new MatchElementDTO();
            //Uri not working nice framework balana !!!
            targetMatchSubject.setMatchId("urn:oasis:names:tc:xacml:1.0:function:string-equal");

            AttributeValueElementDTO attributeValueElementDTO1 = new AttributeValueElementDTO();
            attributeValueElementDTO1.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeValueElementDTO1.setAttributeValue(targetSubject);

            AttributeDesignatorDTO attributeDesignatorDTO1 = new AttributeDesignatorDTO();
            attributeDesignatorDTO1.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
            attributeDesignatorDTO1.setCategory("urn:oasis:names:tc:xacml:1.0:subject-category:access-subject");
            attributeDesignatorDTO1.setDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeDesignatorDTO1.setMustBePresent("true");

            targetMatchSubject.setAttributeValueElementDTO(attributeValueElementDTO1);
            targetMatchSubject.setAttributeDesignatorDTO(attributeDesignatorDTO1);

            return targetMatchSubject;
        }
        return null;
    }

    /**
     * This function creates a Time condition
     * @param timeFrom
     * @param timeUntil
     * @return
     */
    protected static ApplyElementDTO TimeConditionBuilding(String timeFrom, String timeUntil) {

        if ( (timeFrom != null && !(timeFrom.length()==0) )  && (timeUntil != null && !(timeUntil.length()==0 )) ) {

            ApplyElementDTO applyElementDTOC3 = new ApplyElementDTO();
            applyElementDTOC3.setFunctionId("urn:oasis:names:tc:xacml:1.0:function:time-one-and-only");

            AttributeDesignatorDTO attributeDesignatorDTOC3 = new AttributeDesignatorDTO();
            attributeDesignatorDTOC3.setAttributeId("urn:oasis:names:tc:xacml:1.0:environment:current-time");
            attributeDesignatorDTOC3.setCategory("urn:oasis:names:tc:xacml:3.0:attribute-category:environment");
            attributeDesignatorDTOC3.setDataType("http://www.w3.org/2001/XMLSchema#time");
            attributeDesignatorDTOC3.setMustBePresent("true");
            applyElementDTOC3.setAttributeDesignators(attributeDesignatorDTOC3);

            ApplyElementDTO applyElementDTOT = new ApplyElementDTO();
            applyElementDTOT.setFunctionId("urn:oasis:names:tc:xacml:2.0:function:time-in-range");
            applyElementDTOT.setApplyElement(applyElementDTOC3);


            AttributeValueElementDTO attributeValueElementDTOC_3 = new AttributeValueElementDTO();
            attributeValueElementDTOC_3.setAttributeDataType("http://www.w3.org/2001/XMLSchema#time");
            attributeValueElementDTOC_3.setAttributeValue(timeFrom);

            AttributeValueElementDTO attributeValueElementDTOC__3 = new AttributeValueElementDTO();
            attributeValueElementDTOC__3.setAttributeDataType("http://www.w3.org/2001/XMLSchema#time");
            attributeValueElementDTOC__3.setAttributeValue(timeUntil);
            applyElementDTOT.setAttributeValueElementDTO(attributeValueElementDTOC_3);
            applyElementDTOT.setAttributeValueElementDTO(attributeValueElementDTOC__3);

            return applyElementDTOT;
        }
        return null;
    }

    /**
     * This function creates an Ip Address condition
     * @param ipRequestM
     * @param ipRecipM
     * @return
     */
    protected static ApplyElementDTO IpConditionBuilding(String ipRequestM, String ipRecipM) {

        if ((ipRequestM != null && !(ipRequestM.length() == 0)) && (ipRecipM != null && !(ipRecipM.length() == 0))) {

            ApplyElementDTO applyElementDTOC4 = new ApplyElementDTO();
                                     // urn:oasis:names:tc:xacml:2.0:function:ipAddress-one-and-only
            applyElementDTOC4.setFunctionId("urn:oasis:names:tc:xacml:1.0:function:string-is-in");

            AttributeDesignatorDTO attributeDesignatorDTOC4 = new AttributeDesignatorDTO();
            attributeDesignatorDTOC4.setAttributeId("urn:oasis:names:tc:xacml:1.0:environment:environment-id");
            attributeDesignatorDTOC4.setCategory("urn:oasis:names:tc:xacml:3.0:attribute-category:environment");
            attributeDesignatorDTOC4.setDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeDesignatorDTOC4.setMustBePresent("true"); // or false ;;
            applyElementDTOC4.setAttributeDesignators(attributeDesignatorDTOC4);


            ApplyElementDTO applyElementDTOT = new ApplyElementDTO();
                                            // urn:oasis:names:tc:xacml:3.0:function:ipAddress-value-equal
            applyElementDTOT.setFunctionId("urn:oasis:names:tc:xacml:1.0:function:and");
            applyElementDTOT.setApplyElement(applyElementDTOC4);


            AttributeValueElementDTO attributeValueElementDTOC4 = new AttributeValueElementDTO();
            attributeValueElementDTOC4.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeValueElementDTOC4.setAttributeValue(ipRequestM);


            applyElementDTOC4.setAttributeValueElementDTO(attributeValueElementDTOC4);


            ApplyElementDTO applyElementDTOC5 = new ApplyElementDTO();
                                // urn:oasis:names:tc:xacml:2.0:function:ipAddress-one-and-only
            applyElementDTOC5.setFunctionId("urn:oasis:names:tc:xacml:1.0:function:string-is-in");

            AttributeValueElementDTO attributeValueElementDTOC5 = new AttributeValueElementDTO();
            attributeValueElementDTOC5.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeValueElementDTOC5.setAttributeValue(ipRecipM);

            AttributeDesignatorDTO attributeDesignatorDTOC5 = new AttributeDesignatorDTO();
            attributeDesignatorDTOC5.setAttributeId("urn:oasis:names:tc:xacml:1.0:environment:environment-id");
            attributeDesignatorDTOC5.setCategory("urn:oasis:names:tc:xacml:3.0:attribute-category:environment");
            attributeDesignatorDTOC5.setDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeDesignatorDTOC5.setMustBePresent("true"); // or false ;;


            applyElementDTOC5.setAttributeDesignators(attributeDesignatorDTOC5);
            applyElementDTOC5.setAttributeValueElementDTO(attributeValueElementDTOC5);
            applyElementDTOT.setApplyElement(applyElementDTOC5);

            return applyElementDTOT;
        }
        return null;
    }

    /**
     * This function creates a Location condition
     * @param location1
     * @param location2
     * @return
     */
    protected static ApplyElementDTO LocationConditionBuilding(String location1, String location2) {


        if ( location1 != null  || location2 != null ) {

            ApplyElementDTO applyElementDTOC2 = new ApplyElementDTO();
            applyElementDTOC2.setFunctionId("urn:oasis:names:tc:xacml:1.0:function:string-bag");
            AttributeDesignatorDTO attributeDesignatorDTOC_5 = new AttributeDesignatorDTO();
            attributeDesignatorDTOC_5.setAttributeId("urn:oasis:names:tc:xacml:1.0:environment:environment-id");
            attributeDesignatorDTOC_5.setCategory("urn:oasis:names:tc:xacml:3.0:attribute-category:environment");
            attributeDesignatorDTOC_5.setDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeDesignatorDTOC_5.setMustBePresent("true");
            ApplyElementDTO applyElementDTOC7 = new ApplyElementDTO();
            applyElementDTOC7.setFunctionId("urn:oasis:names:tc:xacml:1.0:function:string-at-least-one-member-of");


            if (location1 != null && !(location1.length()==0) && (location2 == null || location2.length()==0)) {

                AttributeValueElementDTO attributeValueElementDTOC2 = new AttributeValueElementDTO();
                attributeValueElementDTOC2.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
                attributeValueElementDTOC2.setAttributeValue(location1);

                applyElementDTOC2.setAttributeValueElementDTO(attributeValueElementDTOC2);
                applyElementDTOC7.setApplyElement(applyElementDTOC2);
                applyElementDTOC7.setAttributeDesignators(attributeDesignatorDTOC_5);

                return applyElementDTOC7;
            }

            if (location2 != null && !(location2.length()==0) && (location1 == null || location1.length()==0))  {

                AttributeValueElementDTO attributeValueElementDTOC_2 = new AttributeValueElementDTO();
                attributeValueElementDTOC_2.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
                attributeValueElementDTOC_2.setAttributeValue(location2);

                applyElementDTOC2.setAttributeValueElementDTO(attributeValueElementDTOC_2);
                applyElementDTOC7.setApplyElement(applyElementDTOC2);
                applyElementDTOC7.setAttributeDesignators(attributeDesignatorDTOC_5);

                return applyElementDTOC7;

            } else {
                AttributeValueElementDTO attributeValueElementDTOC2_ = new AttributeValueElementDTO();
                attributeValueElementDTOC2_.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
                attributeValueElementDTOC2_.setAttributeValue(location1);

                AttributeValueElementDTO attributeValueElementDTOC_2_ = new AttributeValueElementDTO();
                attributeValueElementDTOC_2_.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
                attributeValueElementDTOC_2_.setAttributeValue(location2);

                applyElementDTOC2.setAttributeValueElementDTO(attributeValueElementDTOC2_);
                applyElementDTOC2.setAttributeValueElementDTO(attributeValueElementDTOC_2_);
                applyElementDTOC7.setApplyElement(applyElementDTOC2);
                applyElementDTOC7.setAttributeDesignators(attributeDesignatorDTOC_5);

                return applyElementDTOC7;
            }
        }
        return null;
    }

    /**
     * This function creates a Type of Device condition
     * @param typeofDev1
     * @param typeofDev2
     * @return
     */
    protected static ApplyElementDTO TypeOfDeviceConditionBuilding(String typeofDev1, String typeofDev2) {

        if (typeofDev1 != null || typeofDev2 != null) {

            ApplyElementDTO applyElementDTOC5 = new ApplyElementDTO();
            applyElementDTOC5.setFunctionId("urn:oasis:names:tc:xacml:1.0:function:string-bag");

            AttributeDesignatorDTO attributeDesignatorDTOC5 = new AttributeDesignatorDTO();
            attributeDesignatorDTOC5.setAttributeId("urn:oasis:names:tc:xacml:1.0:environment:environment-id");
            attributeDesignatorDTOC5.setCategory("urn:oasis:names:tc:xacml:3.0:attribute-category:environment");
            attributeDesignatorDTOC5.setDataType("http://www.w3.org/2001/XMLSchema#string");
            attributeDesignatorDTOC5.setMustBePresent("true");

            ApplyElementDTO applyElementDTOC6 = new ApplyElementDTO();
            applyElementDTOC6.setFunctionId("urn:oasis:names:tc:xacml:1.0:function:string-at-least-one-member-of");

            if ((typeofDev1 != null && !(typeofDev1.length()==0)) && (typeofDev2 == null || typeofDev2.length()==0)){
//                if (typeofDev1 != null && !(typeofDev1.length()==0) && typeofDev2 == null) {

                AttributeValueElementDTO attributeValueElementDTOC5 = new AttributeValueElementDTO();
                attributeValueElementDTOC5.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
                attributeValueElementDTOC5.setAttributeValue(typeofDev1);

                applyElementDTOC5.setAttributeValueElementDTO(attributeValueElementDTOC5);
                applyElementDTOC6.setApplyElement(applyElementDTOC5);
                applyElementDTOC6.setAttributeDesignators(attributeDesignatorDTOC5);

                return applyElementDTOC6;
            }
            if (typeofDev2 != null && !(typeofDev2.length()==0) && (typeofDev1 == null || typeofDev1.length()==0)) {


                AttributeValueElementDTO attributeValueElementDTOC5r2 = new AttributeValueElementDTO();
                attributeValueElementDTOC5r2.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
                attributeValueElementDTOC5r2.setAttributeValue(typeofDev2);

                applyElementDTOC5.setAttributeValueElementDTO(attributeValueElementDTOC5r2);
                applyElementDTOC6.setApplyElement(applyElementDTOC5);
                applyElementDTOC6.setAttributeDesignators(attributeDesignatorDTOC5);

                return applyElementDTOC6;
            } else {

                AttributeValueElementDTO attributeValueElementDTOC5 = new AttributeValueElementDTO();
                attributeValueElementDTOC5.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
                attributeValueElementDTOC5.setAttributeValue(typeofDev1);

                AttributeValueElementDTO attributeValueElementDTOC5r2 = new AttributeValueElementDTO();
                attributeValueElementDTOC5r2.setAttributeDataType("http://www.w3.org/2001/XMLSchema#string");
                attributeValueElementDTOC5r2.setAttributeValue(typeofDev2);

                applyElementDTOC5.setAttributeValueElementDTO(attributeValueElementDTOC5);
                applyElementDTOC5.setAttributeValueElementDTO(attributeValueElementDTOC5r2);
                applyElementDTOC6.setApplyElement(applyElementDTOC5);
                applyElementDTOC6.setAttributeDesignators(attributeDesignatorDTOC5);

                return applyElementDTOC6;
            }

        }
        return null;
    }

    /**
     *  Policy,Policy-Set Target Building
     * @param policyTargetResource
     * @param policyTargetAction
     * @param policyTargetSubject
     * @return
     */
    protected static TargetElementDTO TargetBuilding (String policyTargetResource, String policyTargetAction, String policyTargetSubject) {

        AllOfElementDTO allOfElementDTOPolicy = new AllOfElementDTO();

        // Building Policy  Target
        if (!(policyTargetResource.length() == 0) && policyTargetResource!=null) {
            MatchElementDTO targetMatchResource = TargetMatchResourceBuilding(policyTargetResource);
            if (targetMatchResource != null) {
                allOfElementDTOPolicy.addMatchElementDTO(targetMatchResource);
            }
        }
        if (!(policyTargetAction.length() == 0) && policyTargetAction!=null) {
            MatchElementDTO targetMatchAction = TargetMatchActionBuilding(policyTargetAction);
            if (targetMatchAction != null) {
                allOfElementDTOPolicy.addMatchElementDTO(targetMatchAction);
            }
        }
        if (!(policyTargetSubject.length() == 0) && policyTargetSubject!=null) {
            MatchElementDTO targetMatchSubject = TargetMatchSubjectBuilding(policyTargetSubject);
            if (targetMatchSubject != null) {
                allOfElementDTOPolicy.addMatchElementDTO(targetMatchSubject);
            }
        }
        if (!(policyTargetResource.length() == 0) || !(policyTargetAction.length() == 0) || !(policyTargetSubject.length() == 0)) {

            List<AllOfElementDTO> allOfElementDTOsPolicy = new ArrayList<>();
            allOfElementDTOsPolicy.add(allOfElementDTOPolicy);

            AnyOfElementDTO anyOfElementDTOPolicy = new AnyOfElementDTO();
            anyOfElementDTOPolicy.setAllOfElementDTOs(allOfElementDTOsPolicy);

            List<AnyOfElementDTO> anyOfElementDTOsPolicy = new ArrayList<>();
            anyOfElementDTOsPolicy.add(anyOfElementDTOPolicy);

            TargetElementDTO targetElementDTOPolicy = new TargetElementDTO();
            targetElementDTOPolicy.setAnyOfElementDTOs(anyOfElementDTOsPolicy);
            // Set Policy Target if any

            // End of building the policy target

            return targetElementDTOPolicy;
        }
        return null;
    }

    /**
     * Creates and sets up a Policy
     * @param policyName
     * @param ruleCombAl
     * @param policyDesc
     * @param policyTargetResource
     * @param policyTargetAction
     * @param policyTargetSubject
     * @param ruleObjectList
     * @return
     */
    public static PolicyElementDTO PolicyBuilding(String policyName, String ruleCombAl, String policyDesc, String policyTargetResource, String policyTargetAction, String policyTargetSubject,
                                                  List<RuleObject> ruleObjectList) {

        try {
            // Creating Policy Object
            PolicyElementDTO policyElementDTO = new PolicyElementDTO();

            // Set Policy Target if any
            TargetElementDTO targetElementDTOPolicy = TargetBuilding(policyTargetResource,policyTargetAction,policyTargetSubject);

            policyElementDTO.setTargetElementDTO(targetElementDTOPolicy);
             // End of building the policy target

            if (ruleObjectList != null && !(ruleObjectList.isEmpty()) ) {
                for (RuleObject ruleObject : ruleObjectList) {
                    // Rule

                    RuleElementDTO ruleElementDTO = new RuleElementDTO();
                    AllOfElementDTO allOfElementDTO = new AllOfElementDTO();

                    // Building Target
                    if (!(ruleObject.getRule1TargetResource().length() ==0)) {
                        MatchElementDTO targetMatchResourceRule1 = TargetMatchResourceBuilding(ruleObject.getRule1TargetResource());
                        if (targetMatchResourceRule1 != null) {
                            allOfElementDTO.addMatchElementDTO(targetMatchResourceRule1);
                        }
                    }
                    if (!(ruleObject.getRule1TargetAction().length() ==0)) {
                        MatchElementDTO targetMatchActionRule1 = TargetMatchActionBuilding(ruleObject.getRule1TargetAction());
                        if (targetMatchActionRule1 != null){
                            allOfElementDTO.addMatchElementDTO(targetMatchActionRule1);
                        }
                    }
                    if (!(ruleObject.getRule1TargetSubject().length() ==0)) {
                        MatchElementDTO targetMatchSubjectRule1 = TargetMatchSubjectBuilding(ruleObject.getRule1TargetSubject());
                        if (targetMatchSubjectRule1 != null) {
                            allOfElementDTO.addMatchElementDTO(targetMatchSubjectRule1);
                        }
                    }

                    if (!(ruleObject.getRule1TargetResource().length() ==0) || !(ruleObject.getRule1TargetAction().length() ==0) || !(ruleObject.getRule1TargetSubject().length() ==0)) {
                        List<AllOfElementDTO> allOfElementDTOs = new ArrayList<>();
                        allOfElementDTOs.add(allOfElementDTO);

                        AnyOfElementDTO anyOfElementDTO = new AnyOfElementDTO();
                        anyOfElementDTO.setAllOfElementDTOs(allOfElementDTOs);
                        List<AnyOfElementDTO> anyOfElementDTOs = new ArrayList<>();
                        anyOfElementDTOs.add(anyOfElementDTO);

                        TargetElementDTO targetElementDTO = new TargetElementDTO();
                        targetElementDTO.setAnyOfElementDTOs(anyOfElementDTOs);

                        ruleElementDTO.setTargetElementDTO(targetElementDTO);
                        // Set Rule Target if any
                    }

                    ApplyElementDTO applyElementDTO = new ApplyElementDTO();

                    // Second Nested apply of First Condition of Rule
                    if ( !(ruleObject.getRule1location1().length() == 0) || !(ruleObject.getRule1location2().length() == 0) )  {
                        ApplyElementDTO applyElementDTOC7 = LocationConditionBuilding(ruleObject.getRule1location1(), ruleObject.getRule1location2());
                        if (applyElementDTOC7 != null) {
                            applyElementDTO.setApplyElement(applyElementDTOC7);
                        }
                    }

                    // Third Nested apply
                    if ( !(ruleObject.getRule1timeFrom().length() == 0) && !(ruleObject.getRule1timeUntil().length() == 0) ) {
                        ApplyElementDTO applyElementDTOT = TimeConditionBuilding(ruleObject.getRule1timeFrom(), ruleObject.getRule1timeUntil());
                        if (applyElementDTOT != null) {
                            applyElementDTO.setApplyElement(applyElementDTOT);
                        }

                    }
                    // Fourth Nested apply
                    if( !(ruleObject.getRule1ipRequest().length() == 0) || !(ruleObject.getRule1ipRecipient().length() == 0) ) {
                        ApplyElementDTO applyElementDTOC4 = IpConditionBuilding(ruleObject.getRule1ipRequest(), ruleObject.getRule1ipRecipient());
                        if (applyElementDTOC4 != null) {
                            applyElementDTO.setApplyElement(applyElementDTOC4);
                        }
                    }

                    // Fifth Nested apply
                    if( !(ruleObject.getRule1typeofdev1().length() == 0) || !(ruleObject.getRule1typeofdev2().length() == 0) ) {
                        ApplyElementDTO applyElementDTOC6 = TypeOfDeviceConditionBuilding(ruleObject.getRule1typeofdev1(), ruleObject.getRule1typeofdev2());
                        if (applyElementDTOC6 != null) {
                            applyElementDTO.setApplyElement(applyElementDTOC6);
                        }
                    }


                    if(!(ruleObject.getRule1location1().length() == 0) || !(ruleObject.getRule1location2().length() == 0) || !(ruleObject.getRule1timeFrom().length() == 0) || !(ruleObject.getRule1timeUntil().length() == 0) ||
                    !(ruleObject.getRule1ipRequest().length() == 0) || !(ruleObject.getRule1ipRecipient().length() == 0) || !(ruleObject.getRule1typeofdev1().length() == 0) || !(ruleObject.getRule1typeofdev2().length() == 0)) {

                        applyElementDTO.setFunctionId("urn:oasis:names:tc:xacml:1.0:function:and");

                        ConditionElementDT0 conditionElementDT0 = new ConditionElementDT0();
                        conditionElementDT0.setApplyElement(applyElementDTO);

                        ruleElementDTO.setConditionElementDT0(conditionElementDT0);
                    }
                        // Setting the Rules
                        ruleElementDTO.setRuleId(ruleObject.getRule1Name());
                        ruleElementDTO.setRuleEffect(ruleObject.getRule1Effect());

                    policyElementDTO.addRuleElementDTO(ruleElementDTO);

                }
            }

            // Creating Policy
            policyElementDTO.setPolicyName(policyName);
            policyElementDTO.setPolicyDescription(policyDesc);
            policyElementDTO.setRuleCombiningAlgorithms(ruleCombAl);
            policyElementDTO.setVersion("1.0");


            return policyElementDTO;

        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Method for creating Policy-Set
     * @param policySetName
     * @param policyCombAlg
     * @param policySetDesc
     * @param policySetTargetResource
     * @param policySetTargetAction
     * @param policySetTargetSubject

     * @return
     */
    public static PolicySetElementDTO PolicySetBuilding (String policySetName, String policyCombAlg, String policySetDesc, String policySetTargetResource, String policySetTargetAction, String policySetTargetSubject, List<String> policyIdList) throws XACMLNotFoundException {

        TargetElementDTO targetElementDTPolicySet = TargetBuilding(policySetTargetResource,policySetTargetAction,policySetTargetSubject);

        // setting policy set
        PolicySetElementDTO policySetElementDTO = new PolicySetElementDTO();
        policySetElementDTO.setPolicySetId(policySetName);
        policySetElementDTO.setPolicyCombiningAlgId(policyCombAlg);
        policySetElementDTO.setDescription(policySetDesc);
        policySetElementDTO.setVersion("1.0");
        policySetElementDTO.setTargetElementDTO(targetElementDTPolicySet);
        policySetElementDTO.setPolicyIdReferences(policyIdList);

        return policySetElementDTO;
    }

    /**
     * Sets and Creates the values for the xacml Request
     *
     * @param resource Data, service or system component
     * @param action   An operation on a resource
     * @param subject  An actor whose attributes may be referenced by a predicate
     * @param Ipadress The geographical location of a request
     * @param Time     The time that the request is made
     * @param Location The temperature of the place when the request is made
     * @param Device   The type of device when a request is made (Computer,Phone,Tablet,etc)
     */
    public static RequestDTO createRequestDTO(String resource, String action, String subject, String Location, String Time, String Ipadress,String Ipadress1, String Device) {

        List<RowDTO> rowDTOs = new ArrayList<>();

        if(!(resource.length()== 0) && resource != null) {
            RowDTO resourceDTO =
                    createRowDTO(resource,
                            "urn:oasis:names:tc:xacml:2.0:resource:target-namespace", PolicyConstants.RESOURCE_CATEGORY_URI);
            rowDTOs.add(resourceDTO);
        }

        if(!(action.length()==0) && resource!=null) {
            RowDTO actionDTO =
                    createRowDTO(action,
                            PolicyConstants.ACTION_ID, PolicyConstants.ACTION_CATEGORY_URI);
            rowDTOs.add(actionDTO);
        }
        if(!(subject.length()==0) && subject!=null){
            RowDTO subjectDTO = createRowDTO(subject,
                    PolicyConstants.SUBJECT_ID_DEFAULT, PolicyConstants.SUBJECT_CATEGORY_URI);
            rowDTOs.add(subjectDTO);
        }

        RowDTO environmentDTO = createRowDTO(Ipadress,
                "urn:oasis:names:tc:xacml:1.0:environment:environment-id", "urn:oasis:names:tc:xacml:3.0:attribute-category:environment");
        rowDTOs.add(environmentDTO);

        RowDTO environmentDTO4 = createRowDTO(Ipadress1,
                "urn:oasis:names:tc:xacml:1.0:environment:environment-id", "urn:oasis:names:tc:xacml:3.0:attribute-category:environment");
        rowDTOs.add(environmentDTO4);

        if(!(Time.length()==0) && Time!=null) {
            RowDTO environmentDTO1 = createRowDTO1(Time,
                    "urn:oasis:names:tc:xacml:1.0:environment:current-time", PolicyConstants.ENVIRONMENT_CATEGORY_URI);
            rowDTOs.add(environmentDTO1);
        }
        if(!(Location.length()==0) && Location!=null) {
            RowDTO environmentDTO2 = createRowDTO(Location,
                    PolicyConstants.ENVIRONMENT_ID, PolicyConstants.ENVIRONMENT_CATEGORY_URI);
            rowDTOs.add(environmentDTO2);
        }
        if(!(Device.length()==0) && Device!=null) {
            RowDTO environmentDTO3 = createRowDTO(Device,
                    PolicyConstants.ENVIRONMENT_ID, PolicyConstants.ENVIRONMENT_CATEGORY_URI);
            rowDTOs.add(environmentDTO3);
        }

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRowDTOs(rowDTOs);

        return requestDTO;
    }

    /**
     * Creates the backbone for setting up Requests
     *
     * @param attributeValue The actual attribute value
     * @param attributeId    The id of the attribute Category
     * @param categoryValue  The Category of the type of our value
     */
    public static RowDTO createRowDTO(String attributeValue, String attributeId, String categoryValue) {

        RowDTO rowDTO = new RowDTO();
        rowDTO.setAttributeValue(attributeValue);
        rowDTO.setAttributeDataType(PolicyConstants.STRING_DATA_TYPE);
        rowDTO.setAttributeId(attributeId);
        rowDTO.setCategory(categoryValue);
        return rowDTO;
    }

    /**
     * Creates the backbone for setting up Requests
     *
     * @param attributeValue The actual attribute value
     * @param attributeId    The id of the attribute Category
     * @param categoryValue  The Category of the type of our value
     */
    public static RowDTO createRowDTO1(String attributeValue, String attributeId, String categoryValue) {

        RowDTO rowDTO = new RowDTO();
        rowDTO.setAttributeValue(attributeValue);
        rowDTO.setAttributeDataType("http://www.w3.org/2001/XMLSchema#time");
        rowDTO.setAttributeId(attributeId);
        rowDTO.setCategory(categoryValue);
        return rowDTO;
    }



    /**
     * Writes a xacml policy to a .xml file in the specified path
     *
     * @param xmlSource  Policy in a string representation
     * @param PolicyName The name of the file in which policy will be saved
     * @throws IOException
     */

    public static void stringToDom1(String xmlSource, String PolicyName) throws IOException {
        Path path = Paths.get("C:\\Users\\Admin\\IdeaProjects\\AttributeBaseAccessControl\\resources\\"+PolicyName+".xml");
        String Path = "C:\\Users\\Admin\\IdeaProjects\\AttributeBaseAccessControl\\resources\\";
        if(Files.exists(path)) {
            String Policyname =  "Ex"+ PolicyName + ".xml";
            File file = new File(Path, Policyname);
            if (file.getCanonicalPath().startsWith(Path)) {
                java.io.FileWriter fw = new java.io.FileWriter(file);
                fw.write(xmlSource);
                fw.flush();
                fw.close();
            }
        }
        else {
            String Policyname = PolicyName + ".xml";
            File file = new File(Path, Policyname);
            if (file.getCanonicalPath().startsWith(Path)) {
                java.io.FileWriter fw = new java.io.FileWriter(file);
                fw.write(xmlSource);
                fw.flush();
                fw.close();
            }
        }
    }

    /**
     * Initialize the Balana instance and locates where the policies are placed
     *
     * @throws IOException Error in locating Policy
     */
    public static void initBalana() {

        try {
            // using file based policy repository. so set the policy location as system property
            String policyLocation = (new File(".")).getCanonicalPath() + File.separator + "resources";
            System.setProperty(FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY, policyLocation);
        } catch (IOException e) {
            System.err.println("Can not locate policy repository");
        }
        // create default instance of Balana
        balana = Balana.getInstance();
    }

    /**
     * Generates new Policy Decision Point instance.
     * Returns a new PDP instance with new XACML policies
     *
     * @return a  PDP instance
     */
    public static PDP getPDPNewInstance() {

        PDPConfig pdpConfig = balana.getPdpConfig();

        // registering new attribute finder. so default PDPConfig is needed to change
        AttributeFinder attributeFinder = pdpConfig.getAttributeFinder();
        List<AttributeFinderModule> finderModules = attributeFinder.getModules();
        //   finderModules.add(new SampleAttributeFinderModule());
        attributeFinder.setModules(finderModules);

        return new PDP(new PDPConfig(attributeFinder, pdpConfig.getPolicyFinder(), null, true));
    }


    /**
     * Parser for the XACML response.
     *
     * @param response XACML response in String.
     * @throws IOException Error in closing input stream of XACML response
     * @throws Exception   DOM of request element can not be created from String
     */
    public static Element getXacmlResponse(String response) {

        ByteArrayInputStream inputStream;
        DocumentBuilderFactory dbf;
        Document doc;

        inputStream = new ByteArrayInputStream(response.getBytes());
        dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        try {
            doc = dbf.newDocumentBuilder().parse(inputStream);
        } catch (Exception e) {
            System.err.println("DOM of request element can not be created from String");
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                System.err.println("Error in closing input stream of XACML response");
            }
        }
        return doc.getDocumentElement();
    }
}
