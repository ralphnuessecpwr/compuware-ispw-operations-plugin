package com.compuware.ispw.restapi;

import java.io.Serializable;

import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

public class WaitForWebhookStep extends Step implements Serializable {

    private static final long serialVersionUID = -667001655472658819L;

    private final String token;

    @DataBoundConstructor
    public WaitForWebhookStep(WebhookToken webhookToken) {
        this.token = webhookToken.getToken();
    }

    public String getToken() {
        return token;
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new WaitForWebhookExecution(context, this);
    }

    @Extension
    public static class DescriptorImpl extends AbstractStepDescriptorImpl {

        public DescriptorImpl() {
            super(WaitForWebhookExecution.class);
        }

        @Override
        public String getFunctionName() {
            return "ispwWaitForWebhook";
        }

        @Override
        public String getDisplayName() {
            return "Wait for ISPW webhook to be posted to by external system";
        }
    }
}
