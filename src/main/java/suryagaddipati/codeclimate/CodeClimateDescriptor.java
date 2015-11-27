package suryagaddipati.codeclimate;

import hudson.*;
import hudson.plugins.analysis.core.*;

@Extension
public class CodeClimateDescriptor extends PluginDescriptor {
    static final String ICON_URL_PREFIX = "/plugin/codeclimate/icons/";
    public CodeClimateDescriptor() {
        super(CodeClimatePublisher.class);
    }

    @Override
    public String getDisplayName() {
        return "Code Climate Publisher";
    }

    @Override
    public String getPluginName() {
        return "codeclimate";
    }

    @Override
    public String getIconUrl() {
        return ICON_URL_PREFIX + "codeclimate-48x48.png";
    }
}
