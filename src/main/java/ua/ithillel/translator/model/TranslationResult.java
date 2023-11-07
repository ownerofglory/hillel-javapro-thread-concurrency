package ua.ithillel.translator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationResult {
    private TranslationContent contents;

    public TranslationContent getContents() {
        return contents;
    }

    public void setContents(TranslationContent contents) {
        this.contents = contents;
    }
}
