package ua.ithillel.translator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
