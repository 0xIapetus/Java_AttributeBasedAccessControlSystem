package com.whoami.attributebaseaccesscontrol.dao;

import com.whoami.attributebaseaccesscontrol.objects.RequestObject;
import com.whoami.attributebaseaccesscontrol.Utils.Utilities;
import org.wso2.balana.PDP;
import org.wso2.balana.ParsingException;
import org.wso2.balana.ctx.AbstractResult;
import org.wso2.balana.ctx.ResponseCtx;
import org.wso2.balana.utils.exception.PolicyBuilderException;
import org.wso2.balana.utils.policy.PolicyBuilder;
import org.wso2.balana.utils.policy.dto.RequestElementDTO;
import org.wso2.carbon.identity.entitlement.common.dto.RequestDTO;
import org.wso2.carbon.identity.entitlement.common.util.PolicyCreatorUtil;

import java.util.Objects;
import java.util.Set;

public class RequestDAO {

    private static RequestDAO instance;

    private RequestDAO() {
    }

    //Creates and returns single class instance
    //The if statement ensures that, no matter how many times getInstance() is called (from external objects_, it will only create
    //a single instance of the class

    /**
     * Creates and returns single class instance
     * The if statement ensures that, no matter how many times getInstance() is called (from external objects_, it will only create
     * a single instance of the class
     * @return
     */
    public static RequestDAO getInstance() {
        if (instance == null) {
            instance = new RequestDAO(); //call private constructor
        }
        return instance;
    }

    /**
     * This function creates a Request
     * @param request
     * @return
     * @throws PolicyBuilderException
     */
    public String createRequest(RequestObject request) throws PolicyBuilderException {

        RequestDTO requestDTO = Utilities.createRequestDTO(
                request.getResource().trim(), request.getAction().trim(), request.getSubject().trim(), request.getLocation().trim(),
                request.getTime().trim(), request.getIpAddress().trim(), request.getIpAddress1().trim(), request.getDevice().trim());

        RequestElementDTO requestElementDTO = PolicyCreatorUtil.createRequestElementDTO(requestDTO);

        String requestString = PolicyBuilder.getInstance().buildRequest(requestElementDTO);
        return requestString;
    }

    /**
     * This function takes the request as a string and evaluates it and returns the Response
     * @param requestString
     * @return
     */
    public String initAndEval(String requestString) {

        Utilities.initBalana();
        PDP pdp = Utilities.getPDPNewInstance();
        String xacmlResponse = pdp.evaluate(requestString); //evaluates XACML request here.
        String responseMessage = "";

        try {
            ResponseCtx responseCtx = ResponseCtx.getInstance(Objects.requireNonNull(Utilities.getXacmlResponse(xacmlResponse)));
            Set<AbstractResult> results = responseCtx.getResults();
            for (AbstractResult result : results) {
                if (AbstractResult.DECISION_PERMIT == result.getDecision()) {
                    responseMessage = "We have a Permit \n\n";
                } else if (AbstractResult.DECISION_DENY == result.getDecision()) {
                    responseMessage = "We have a Deny \n\n";
                } else if (AbstractResult.DECISION_INDETERMINATE == result.getDecision()) {
                    responseMessage = "We have an Itermediate \n\n";
                } else if (AbstractResult.DECISION_NOT_APPLICABLE == result.getDecision()) {
                    responseMessage = "We have an NotApplicable \n\n";
                } else {
                    responseMessage = "Unexpected Result \n\n";
                }
            }

        } catch (ParsingException e) {
            e.printStackTrace();
        }
        return responseMessage;
    }
}
