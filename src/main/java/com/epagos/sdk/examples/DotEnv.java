package com.epagos.sdk.examples;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class DotEnv {
    private static final Map<String, String> VALUES = load();

    private DotEnv() {
    }

    static String get(String name) {
        String processValue = System.getenv(name);
        if (processValue != null && !processValue.isBlank()) {
            return processValue;
        }
        return VALUES.get(name);
    }

    private static Map<String, String> load() {
        Path path = resolvePath();
        if (path == null || !Files.isRegularFile(path)) {
            return Collections.emptyMap();
        }

        try {
            return parse(Files.readAllLines(path, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer el archivo .env: " + path, e);
        }
    }

    private static Path resolvePath() {
        String explicitPath = System.getenv("EPAGOS_ENV_FILE");
        if (explicitPath != null && !explicitPath.isBlank()) {
            return Path.of(explicitPath).toAbsolutePath().normalize();
        }

        Path current = Path.of("").toAbsolutePath().normalize();
        while (current != null) {
            Path candidate = current.resolve(".env");
            if (Files.isRegularFile(candidate)) {
                return candidate;
            }
            current = current.getParent();
        }

        return null;
    }

    private static Map<String, String> parse(List<String> lines) {
        Map<String, String> values = new LinkedHashMap<>();
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            if (trimmed.startsWith("export ")) {
                trimmed = trimmed.substring("export ".length()).trim();
            }

            int separator = trimmed.indexOf('=');
            if (separator <= 0) {
                continue;
            }

            String key = trimmed.substring(0, separator).trim();
            String value = trimmed.substring(separator + 1).trim();
            values.put(key, stripQuotes(value));
        }
        return values;
    }

    private static String stripQuotes(String value) {
        if (value.length() < 2) {
            return value;
        }

        char first = value.charAt(0);
        char last = value.charAt(value.length() - 1);
        if ((first == '"' && last == '"') || (first == '\'' && last == '\'')) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }
}
