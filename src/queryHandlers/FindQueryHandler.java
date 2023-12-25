package queryHandlers;

import util.AppConstants;
import util.ConvertRomanToDecimal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindQueryHandler extends Statement {
    public FindQueryHandler(Statement queryProcessor) {
        super(queryProcessor);
    }

    @Override
    public String execute(Metadata metadata) {
        Pattern pattern = Pattern.compile(AppConstants.FIND_QUERY_REGEX);
        Matcher matcher = pattern.matcher(metadata.getQuery());
        if (matcher.matches()) {
            String[] find = matcher.group(1).split(" ");
            StringBuilder roman = new StringBuilder();
            for (int i = 0; i < find.length - 1; i++) {
                if (!metadata.isRomanAlias(find[i])) return AppConstants.FAILED_QUERY;
                try {
                    roman.append(metadata.getValue(find[i]));
                } catch (Exception e) {
                    return e.getMessage();
                }
            }

            String currency = find[find.length - 1];
            if (metadata.isTradeCommodity(currency)) {
                int dec = ConvertRomanToDecimal.convert(roman.toString());
                if (dec == -1) return AppConstants.INVALID_ROMAN_NUMERAL_FORMAT;
                if (!metadata.isTradeCommodity(currency)) return AppConstants.FAILED_QUERY;
                Double value = null;
                try {
                    value = (Double) metadata.getValue(currency);
                } catch (Exception e) {
                    return e.getMessage();
                }
                return buildResultMessage(find, value * dec, false);
            } else {
                if (!metadata.isRomanAlias(currency)) return AppConstants.FAILED_QUERY;
                try {
                    roman.append(metadata.getValue(currency));
                } catch (Exception e) {
                    return e.getMessage();
                }
                double convert = ConvertRomanToDecimal.convert(roman.toString());
                if (convert == -1) return AppConstants.INVALID_ROMAN_NUMERAL_FORMAT;
                return buildResultMessage(find, convert, true);
            }
        }
        return super.execute(metadata);
    }

    private String buildResultMessage(String[] find, Double value, boolean findDecimal) {
        StringBuilder msg = new StringBuilder(String.join(" ", find));
        String output = value.toString();
        if (Math.ceil(value) == value.intValue()) output = String.valueOf(value.intValue());
        if (!findDecimal) {
            msg.append(" is ").append(output).append(" Credits");
        } else {
            msg.append(" is ").append(output);
        }
        return msg.toString();
    }

}