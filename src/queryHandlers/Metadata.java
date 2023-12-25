package queryHandlers;

import util.AppConstants;

import java.util.HashMap;
import java.util.Map;

class Metadata {
    private Map<String, Object> currencyValues = new HashMap<>();
    private String query;

    public boolean isRomanAlias(String key) {
        return currencyValues.get(key.toUpperCase()) != null && currencyValues.get(key.toUpperCase()) instanceof String;
    }

    public boolean isTradeCommodity(String key) {
        return !isRomanAlias(key);
    }

    public void update(String key, Object value) {
        currencyValues.put(key.toUpperCase(), value);
    }

    public Object getValue(String key) throws Exception {
        if (!currencyValues.containsKey(key.toUpperCase())) throw new RuntimeException(AppConstants.FAILED_QUERY);
        return currencyValues.get(key.toUpperCase());
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}