package com.whoami.attributebaseaccesscontrol.controllers;

import com.whoami.attributebaseaccesscontrol.dao.PolicyDAO;
import com.whoami.attributebaseaccesscontrol.dao.PolicySetDAO;
import com.whoami.attributebaseaccesscontrol.dao.RequestDAO;
import com.whoami.attributebaseaccesscontrol.objects.PolicyObject;
import com.whoami.attributebaseaccesscontrol.objects.PolicySetObject;
import com.whoami.attributebaseaccesscontrol.objects.RequestObject;
import com.whoami.attributebaseaccesscontrol.objects.ResponseObject;
import org.springframework.web.bind.annotation.*;
import org.wso2.balana.utils.exception.PolicyBuilderException;

import java.io.IOException;

/**
 * Rest Controller that serves the incoming requests
 */
@RestController
public class RestControl {

    //DAO class creates and returns its single instance
    //This instance is then used by all APIs below to call the appropriate DAO methods
    private RequestDAO dao = RequestDAO.getInstance();
    private PolicyDAO dao1 = PolicyDAO.getInstance();
    private PolicySetDAO dao2 = PolicySetDAO.getInstance();

    /**
     * Creates and Evaluates a request
     *
     * @param request
     * @return
     * @throws PolicyBuilderException
     */
    @PostMapping("/evaluate")
    public ResponseObject evaluate(@RequestBody RequestObject request) throws PolicyBuilderException {

        String request1 = dao.createRequest(request);

        String responseMessage = dao.initAndEval(request1);

        return new ResponseObject(responseMessage);
    }


    /**
     * Creates a Policy and saves it to the disk
     *
     * @param policy
     * @return throws IOException
     */
    @PostMapping("/createPolicy")
    public ResponseObject createPolicy(@RequestBody PolicyObject policy) {

        String responseMessage = dao1.policyCreation(policy);

        return new ResponseObject(responseMessage);
    }

    /**
     * Creates a Policy-Set and saves it to the disk
     *
     * @param policyset
     * @return
     * @throws IOException
     */
    @PostMapping("/PolicySetCreation")
    public ResponseObject createPolicySet(@RequestBody PolicySetObject policyset) {

        String responseMessage = dao2.policySet(policyset);

        return new ResponseObject(responseMessage);
    }

}


