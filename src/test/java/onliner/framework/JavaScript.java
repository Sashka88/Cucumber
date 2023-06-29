package onliner.framework;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public enum JavaScript {
    GET_ELEMENT_XPATH("getElementXPath.js"),
    CLICK_ON_ELEMENT("clickOnElement.js"),
    ;

    private final String filename;

    JavaScript(String filename) {
        this.filename = filename;
    }

    public String getScript() {
        URL scriptFile = getClass().getResource("/js/" + filename);
        try {
            assert scriptFile != null;
            InputStream stream = scriptFile.openStream();
            return IOUtils.toString(stream, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException("Can't find file with JS script", e);
        }
    }
}