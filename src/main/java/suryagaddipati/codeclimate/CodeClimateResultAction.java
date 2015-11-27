package suryagaddipati.codeclimate;

import hudson.model.*;
import hudson.plugins.analysis.core.*;

public class CodeClimateResultAction extends AbstractResultAction<CodeClimateResult> {
    public CodeClimateResultAction(Run<?, ?> owner,final HealthDescriptor healthDescriptor, CodeClimateResult result) {
        super(owner, new CodeClimateHealthDescriptor( healthDescriptor), result);
    }

    @Override
    protected PluginDescriptor getDescriptor() {
        return new CodeClimateDescriptor();
    }

    @Override
    public String getDisplayName() {
        return "Code Climate";
    }
}
