package ru.astondev.context.interfaces;

import java.util.ArrayList;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Predicate;

final class ProfilesParser {

    private ProfilesParser() {
    }

    static Profiles parse(String... expressions) {
        Profiles[] parsed = new Profiles[expressions.length];

        for (int i = 0; i < expressions.length; ++i) {
            parsed[i] = parseExpression(expressions[i]);
        }

        return new ProfilesParser.ParsedProfiles(expressions, parsed);
    }

    private static Profiles parseExpression(String expression) {
        StringTokenizer tokens = new StringTokenizer(expression, "()&|!", true);
        return parseTokens(expression, tokens);
    }

    private static Profiles parseTokens(String expression, StringTokenizer tokens) {
        return parseTokens(expression, tokens, ProfilesParser.Context.NONE);
    }

    private static Profiles parseTokens(String expression, StringTokenizer tokens, ProfilesParser.Context context) {
        List<Profiles> elements = new ArrayList();
        ProfilesParser.Operator operator = null;
        return null;

    }
    private static class ParsedProfiles implements Profiles {
        private final Set<String> expressions = new LinkedHashSet();
        private final Profiles[] parsed;

        ParsedProfiles(String[] expressions, Profiles[] parsed) {
            Collections.addAll(this.expressions, expressions);
            this.parsed = parsed;
        }

        @Override
        public boolean matches(Predicate<String> activeProfiles) {
            return true;
        }
    }
    private static enum Context {
        NONE,
        NEGATE,
        PARENTHESIS;

        private Context() {
        }
    }

    private static enum Operator {
        AND,
        OR;

        private Operator() {
        }
    }
}
