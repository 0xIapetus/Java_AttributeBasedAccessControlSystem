package com.whoami.attributebaseaccesscontrol.dao;

import com.whoami.attributebaseaccesscontrol.objects.PolicySetObject;
import com.whoami.attributebaseaccesscontrol.Utils.Utilities;
import org.wso2.balana.utils.exception.PolicyBuilderException;
import org.wso2.balana.utils.policy.PolicyBuilder;
import org.wso2.balana.utils.policy.dto.PolicySetElementDTO;

import java.io.IOException;


public class PolicySetDAO {

    private static PolicySetDAO instance;

    private PolicySetDAO (){}

    //Creates and returns single class instance
    //The if statement ensures that, no matter how many times getInstance() is called (from external objects_, it will only create
    //a single instance of the class

    /**
     * Creates and returns single class instance
     * The if statement ensures that, no matter how many times getInstance() is called (from external objects_, it will only create
     * a single instance of the class
     * @return
     */
    public static PolicySetDAO getInstance() {
        if (instance == null) {
            instance = new PolicySetDAO(); //call private constructor
        }
        return instance;
    }

    /**
     * Creates a Policy-Set and returns a message to the user
     * @param policyset
     * @return
     */
    public String policySet(PolicySetObject policyset){
        System.out.println(policyset);

        String responseMessage ="";

        PolicySetElementDTO policySetElementDTO1 = Utilities.PolicySetBuilding(policyset.getPolicySetName().trim(),
                policyset.getPolicySetCombAlg().trim(), policyset.getPolicySetDesc().trim(), policyset.getPolicyTargetResource().trim(), policyset.getPolicyTargetAction().trim(), policyset.getPolicyTargetSubject().trim(),
                policyset.ConvertMapsToArray(policyset.getPolicyIdList()));

        try {
            String policySetString = PolicyBuilder.getInstance().build(policySetElementDTO1);
            Utilities.stringToDom1(policySetString, policyset.getPolicySetName());
            responseMessage = "PolicySet Created";
        } catch (PolicyBuilderException | IOException ex) {
            responseMessage = String.valueOf(ex);
        }
        return responseMessage;
    }
}
