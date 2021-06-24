package com.az.gitember.controller.lang;

import com.az.gitember.controller.lang.json.JSONLexer;
import org.antlr.v4.runtime.Lexer;

public class JsonTokenTypeAdapter extends BaseTokenTypeAdapter {

    private JSONLexer jsonLexer;

    public JsonTokenTypeAdapter(Lexer lexer) {

        super(lexer);

        this.jsonLexer = (JSONLexer) lexer;

    }

    @Override
    public String adaptToStyleClass(int tokenType) {
        if (tokenType == jsonLexer.STRING) {
            return STRING;
        } else if (tokenType == jsonLexer.NUMBER) {
            return KEYWORD;
        }
        return super.adaptToStyleClass(tokenType);
    }
}
