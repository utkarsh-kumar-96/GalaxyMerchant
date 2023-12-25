package queryHandlers;

import util.AppConstants;

import java.util.HashMap;
import java.util.Map;

class Metadata {
    private Map<String, Object> currencyValues = new HashMap<>();
    private String query;

    public boolean isRomanAlias(String key) {
        if (currencyValues.get(key) != null && currencyValues.get(key) instanceof String) return true;
        return false;
    }

    public boolean isTradeCommodity(String key) {
        return !isRomanAlias(key);
    }

    public void update(String key, Object value) {
        currencyValues.put(key, value);
    }

    public Object getValue(String key) throws Exception {
        if (!currencyValues.containsKey(key)) throw new RuntimeException(AppConstants.FAILED_QUERY);
        return currencyValues.get(key);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}