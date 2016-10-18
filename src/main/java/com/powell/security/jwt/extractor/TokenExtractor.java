package com.powell.security.jwt.extractor;

public interface TokenExtractor {
    public String extract(String payload);
}
