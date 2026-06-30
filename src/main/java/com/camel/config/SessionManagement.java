package com.camel.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.camel.common.CamelConstants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionManagement {

    /**
     * Initializes the session and session cache if they do not already exist.
     */
    public void handleSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        if (session.getAttribute(CamelConstants.SESSION_CACHE) == null) {
            session.setAttribute(CamelConstants.SESSION_CACHE, session.getId());
        }

        if (session.getAttribute(CamelConstants.SESSION_CACHE_MAP) == null) {
            session.setAttribute(
                    CamelConstants.SESSION_CACHE_MAP,
                    new HashMap<String, Object>());
        }
    }

    /**
     * Returns the session cache map, creating it if necessary.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getSessionCache(HttpServletRequest request) {
        handleSession(request);

        HttpSession session = request.getSession(false);

        return (Map<String, Object>) session.getAttribute(
                CamelConstants.SESSION_CACHE_MAP);
    }

    /**
     * Store a value in the session cache.
     */
    public void put(HttpServletRequest request, String key, Object value) {
        getSessionCache(request).put(key, value);
    }

    /**
     * Retrieve a value from the session cache.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(HttpServletRequest request, String key) {
        return (T) getSessionCache(request).get(key);
    }

    /**
     * Remove a single entry from the session cache.
     */
    public void remove(HttpServletRequest request, String key) {
        getSessionCache(request).remove(key);
    }

    /**
     * Clear all entries from the session cache.
     */
    public void clear(HttpServletRequest request) {
        getSessionCache(request).clear();
    }

    /**
     * Destroy the user's session.
     */
    public void invalidate(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * Check whether the session cache contains a key.
     */
    public boolean contains(HttpServletRequest request, String key) {
        return getSessionCache(request).containsKey(key);
    }

    /**
     * Get the current session ID.
     */
    public String getSessionId(HttpServletRequest request) {
        handleSession(request);

        HttpSession session = request.getSession(false);
        return session.getId();
    }
}