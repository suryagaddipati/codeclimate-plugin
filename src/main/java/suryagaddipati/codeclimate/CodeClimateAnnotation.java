package suryagaddipati.codeclimate;

import hudson.plugins.analysis.util.model.*;

public class CodeClimateAnnotation extends AbstractAnnotation {
    private final String tooltip;

    public CodeClimateAnnotation(CodeClimateWarning warning) {
        super(warning.description,warning.location.getBeginLine(), warning.location.getEndLine(),warning.categories[0],warning.type);
        this.tooltip = warning.engine_name;
        setPriority(Priority.NORMAL);
        setFileName(warning.location.path);
    }

    @Override
    public String getToolTip() {
        return tooltip;
    }
}
