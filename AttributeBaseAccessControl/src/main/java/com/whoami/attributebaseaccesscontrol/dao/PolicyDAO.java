package com.whoami.attributebaseaccesscontrol.dao;

import com.whoami.attributebaseaccesscontrol.objects.PolicyObject;
import com.whoami.attributebaseaccesscontrol.Utils.Utilities;
import org.wso2.balana.utils.exception.PolicyBuilderException;
import org.wso2.balana.utils.policy.PolicyBuilder;
import org.wso2.balana.utils.policy.dto.PolicyElementDTO;

import java.io.IOException;

public class PolicyDAO {

    private static PolicyDAO instance;

    private PolicyDAO() {
    }

    /**
     * Creates and returns single class instance
     *The if statement ensures that, no matter how many times getInstance() is called (from external objects_, it will only create
     * a single instance of the class
     * @return
     */
    public static PolicyDAO getInstance() {
        if (instance == null) {
            instance = new PolicyDAO(); //call private constructor
        }
        return instance;
    }

    /**
     *  Creates a Policy and returns a message to the user
     * @param policy
     * @return
     */
    public String policyCreation(PolicyObject policy){

        String responseMessage;

        PolicyElementDTO policyElementDTO = Utilities.PolicyBuilding(policy.getPolicyName().trim(), policy.getRuleCombAl().trim(), policy.getPolicyDesc(), policy.getPolicyTargetResource().trim(), policy.getPolicyTargetAction().trim(), policy.getPolicyTargetSubject().trim(), policy.getRules());

        try {
            String policyString = PolicyBuilder.getInstance().build(policyElementDTO);
            Utilities.stringToDom1(policyString, policy.getPolicyName().trim());
            responseMessage = "Policy Created";
        } catch (PolicyBuilderException | IOException ex) {
            responseMessage = String.valueOf(ex);
        }

        return responseMessage;
    }
}
