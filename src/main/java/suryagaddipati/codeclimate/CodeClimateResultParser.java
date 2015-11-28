package suryagaddipati.codeclimate;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import hudson.plugins.analysis.core.*;
import hudson.plugins.analysis.util.model.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class CodeClimateResultParser extends AbstractAnnotationParser {
    protected CodeClimateResultParser(String defaultEncoding) {
        super(defaultEncoding);
    }

    @Override
    public Collection<FileAnnotation> parse(InputStream file, String moduleName) throws InvocationTargetException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<FileAnnotation> annotations = new ArrayList<FileAnnotation>();
        try {
            mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                    .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                    .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                    .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                    .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

            List<CodeClimateWarning> warnings=  mapper.readValue(file, new TypeReference<List<CodeClimateWarning>>() { });
            for(CodeClimateWarning warning: warnings){
               annotations.add( new CodeClimateAnnotation(warning));
            }

        } catch (IOException e) {
           throw new RuntimeException(e) ;
        }
        return annotations;
    }
}
