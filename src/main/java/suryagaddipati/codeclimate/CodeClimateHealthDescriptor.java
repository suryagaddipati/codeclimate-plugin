package suryagaddipati.codeclimate;

import hudson.*;
import hudson.plugins.analysis.core.*;
import hudson.plugins.analysis.util.model.*;
import org.jvnet.localizer.*;

public class CodeClimateHealthDescriptor extends AbstractHealthDescriptor {
    public CodeClimateHealthDescriptor(HealthDescriptor healthDescriptor) {
        super(healthDescriptor);
    }

    @Override
    protected Localizable createDescription(AnnotationProvider result) {
        return Messages._ProxyConfiguration_Success();
    }
}
