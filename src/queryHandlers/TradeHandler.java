package queryHandlers;

import util.AppConstants;
import util.ConvertRomanToDecimal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TradeHandler extends Statement {
    public TradeHandler(Statement queryProcessor) {
        super(queryProcessor);
    }

    @Override
    public String execute(Metadata metadata) {
        Pattern pattern = Pattern.compile(AppConstants.TRADE_COMMODITY_REGEX);
        Matcher matcher = pattern.matcher(metadata.getQuery());
        if (matcher.matches()) {
            StringBuilder valueInRomanNumeral = new StringBuilder();
            String commodity = null;
            String[] currency = matcher.group(1).split(" ");
            for (String s : currency) {
                if (metadata.isRomanAlias(s)) {
                    try {
                        valueInRomanNumeral.append(metadata.getValue(s));
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                } else {
                    commodity = s;
                }
            }
            double value = Integer.parseInt(matcher.group(2));
            double romanNumberToNumber = ConvertRomanToDecimal.convert(valueInRomanNumeral.toString());
            if (romanNumberToNumber == -1)
                return AppConstants.INVALID_ROMAN_NUMERAL_FORMAT;
            metadata.update(commodity, Double.valueOf(value / romanNumberToNumber));
            return null;
        }
        return super.execute(metadata);
    }
}
