package com.whoami.attributebaseaccesscontrol.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    /**
     * Maps the index page
     *
     * @return
     */
    @GetMapping("/")
    public String index() {

        return "index";
    }

    /**
     * Maps the page that creates and evaluates the request
     *
     * @return
     */
    @GetMapping("/RequestCreateEvalConstruction.html")
    public String request() {

        return "RequestCreateEvalConstruction";
    }

    /**
     * Maps the page that creates the Policy
     *
     * @return
     */
    @GetMapping("/PolicyCreation.html")
    public String policy() {

        return "PolicyCreation";
    }

    /**
     * Maps the page that creates the Policy-Sets
     * @return
     */
    @GetMapping("/PolicySetCreation.html")
    public String policySet() {

        return "PolicySetCreation";
    }
}
