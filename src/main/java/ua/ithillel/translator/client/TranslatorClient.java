package ua.ithillel.translator.client;


import ua.ithillel.translator.Message;
import ua.ithillel.translator.model.TranslationResult;

public interface TranslatorClient {
    TranslationResult translateIntoPirate(Message message);
}
