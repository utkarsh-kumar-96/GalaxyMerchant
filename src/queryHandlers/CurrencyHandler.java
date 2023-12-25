package queryHandlers;

import util.AppConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyHandler extends Statement {
    public CurrencyHandler(Statement queryProcessor) {
        super(queryProcessor);
    }

    @Override
    public String execute(Metadata metadata) {
        Pattern pattern = Pattern.compile(AppConstants.ASSIGN_CURRENCY_REGEX);
        Matcher matcher = pattern.matcher(metadata.getQuery());
        if (matcher.matches()) {
            String romanNumeralAlias = matcher.group(1);
            String value = matcher.group(2);
            metadata.update(romanNumeralAlias, value);
            return null;
        }
        return super.execute(metadata);
    }
}
