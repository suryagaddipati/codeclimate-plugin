package suryagaddipati.codeclimate;

import hudson.model.*;
import hudson.plugins.analysis.core.*;

public class CodeClimateResult  extends  BuildResult{
    public CodeClimateResult(Run<?, ?>build, String defaultEncoding, ParserResult result, boolean usePreviousBuildAsReference, boolean useStableBuildAsReference) {
        super(build, new BuildHistory(build, CodeClimateResultAction.class, usePreviousBuildAsReference, useStableBuildAsReference), result, defaultEncoding);
        serializeAnnotations(result.getAnnotations());
    }

    @Override
    protected String getSerializationFileName() {
        return "codeclimate.xml";
    }

    @Override
    protected Class<? extends ResultAction<? extends BuildResult>> getResultActionType() {
        return CodeClimateResultAction.class;
    }

    @Override
    public String getSummary() {
        return "Code Climate";
    }

    @Override
    public String getDisplayName() {
        return "Code Climate";
    }
}
