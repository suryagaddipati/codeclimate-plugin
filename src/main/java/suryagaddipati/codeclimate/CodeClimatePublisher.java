package suryagaddipati.codeclimate;

import hudson.*;
import hudson.matrix.*;
import hudson.model.*;
import hudson.plugins.analysis.core.*;
import hudson.plugins.analysis.util.*;
import hudson.tasks.*;
import org.apache.commons.lang.*;
import org.kohsuke.stapler.*;

import java.io.*;

public class CodeClimatePublisher extends HealthAwarePublisher{
    private static final String DEFAULT_FILE_NAME = "code-climate-result.json";
    private String resultFile;
    @DataBoundConstructor
    public CodeClimatePublisher(){
        super("CODECLIMATE");
        setUsePreviousBuildAsReference(true);
        setUseStableBuildAsReference(true);
        setCanRunOnFailed(false);
    }

    @Override
    protected boolean perform(final Run<?, ?> build, final FilePath workspace, final Launcher launcher, final PluginLogger logger)
            throws IOException, InterruptedException {
        try {
            String codeClimateCommand = "docker run " +
                    "  --interactive  --rm  " +
                    "  --env CODE_PATH=\"$PWD\" " +
                    "  --volume \"$PWD\":/code " +
                    "  --volume /var/run/docker.sock:/var/run/docker.sock " +
                    "  --volume /tmp/cc:/tmp/cc " +
                    "  codeclimate/codeclimate analyze -f json> code-climate-result.json";
            Shell execution = new Shell("#!/bin/bash  -ex \n" + codeClimateCommand);
            execution.perform(((AbstractBuild) build), launcher, launcher.getListener());
            FilesParser parser = new FilesParser("CODE_CLIMATE", StringUtils.defaultIfEmpty(getResultFile(), DEFAULT_FILE_NAME),
                    new CodeClimateResultParser(getDefaultEncoding()),
                    false, false);
            ParserResult project = ((AbstractBuild) build).getWorkspace().act(parser);
            CodeClimateResult result = new CodeClimateResult(build, getDefaultEncoding(), project,
                    usePreviousBuildAsReference(), useOnlyStableBuildsAsReference());
            build.addAction(new CodeClimateResultAction(build, this, result));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public MatrixAggregator createAggregator(MatrixBuild matrixBuild, Launcher launcher, BuildListener buildListener) {
        return null;
    }

    public String getResultFile() {
        return resultFile;
    }
    @DataBoundSetter
    public void setResultFile(String resultFile) {
        this.resultFile = resultFile;
    }
}

