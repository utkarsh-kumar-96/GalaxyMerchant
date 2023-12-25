package queryHandlers;

import util.AppConstants;
import util.ConvertRomanToDecimal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComparisonQueryHandler extends Statement {
    public ComparisonQueryHandler(Statement queryProcessor) {
        super(queryProcessor);
    }

    @Override
    public String execute(Metadata metadata) {
        Pattern pattern = Pattern.compile(AppConstants.COMPARE_QUERY_REGEX);
        Matcher matcher = pattern.matcher(metadata.getQuery());
        if (matcher.matches()) {
            String[] leftCurrency = matcher.group(1).split(" ");
            String[] operator = matcher.group(2).split(" ");
            String[] rightCurrency = matcher.group(3).split(" ");
            Double leftCurrencyVal;
            try {
                leftCurrencyVal = calculateValue(leftCurrency, metadata);
            } catch (Exception e) {
                return e.getMessage();
            }
            Double rightCurrencyVal;
            try {
                rightCurrencyVal = calculateValue(rightCurrency, metadata);
            } catch (Exception e) {
                return e.getMessage();
            }
            int type = -99;
            if (operator.length == 4 && operator[2].equals("Credits")) type = 1;
            else if (operator[0].equals("smaller") || operator[0].equals("larger")) type = 2;
            String resultSet;
            try {
                resultSet = buildResultSet(leftCurrency, leftCurrencyVal, rightCurrency, rightCurrencyVal, type);
            } catch (Exception e) {
                return e.getMessage();
            }
            return resultSet;
        }
        return super.execute(metadata);
    }

    public Double calculateValue(String[] currency, Metadata metadata) throws Exception {
        StringBuilder romanNumeral = new StringBuilder();
        for (int i = 0; i < currency.length - 1; i++) {
            if (!metadata.isRomanAlias(currency[i])) throw new RuntimeException(AppConstants.FAILED_QUERY);
            romanNumeral.append(metadata.getValue(currency[i]));
        }
        double val;
        String s = currency[currency.length - 1];
        if (metadata.isRomanAlias(s)) {
            romanNumeral.append(metadata.getValue(s));
            val = getCurrencyVal(romanNumeral.toString());
        } else if (metadata.isTradeCommodity(s)) {
            val = getCurrencyVal(romanNumeral.toString());
            double commodityVal = getCommodityVal(s,metadata);
            val = val * commodityVal;
        } else {
            throw new RuntimeException(AppConstants.FAILED_QUERY);
        }
        return val;
    }

    private double getCurrencyVal(String s) throws RuntimeException {
        double val;
        val = ConvertRomanToDecimal.convert(s);
        if (val == -1) throw new RuntimeException(AppConstants.INVALID_ROMAN_NUMERAL_FORMAT);
        return val;
    }

    private double getCommodityVal(String s, Metadata metadata) throws Exception {
        if (!metadata.isTradeCommodity(s)) throw new RuntimeException(AppConstants.FAILED_QUERY);
        return (double) metadata.getValue(s);
    }

    private String buildResultSet(String[] leftCurrency, Double leftCurrencyVal, String[] rightCurrency, Double rightCurrencyVal, int type) {
        StringBuilder resultSet = new StringBuilder();
        String leftStmt = String.join(" ", leftCurrency);
        String rightStmt = String.join(" ", rightCurrency);
        String interString;
        if (type == 1) interString = "has ? Credits than";
        else if (type == 2) interString = "? than";
        else throw new RuntimeException(AppConstants.FAILED_QUERY);
        if (leftCurrencyVal > rightCurrencyVal) {
            if (type == 1) interString = interString.replace("?", "more");
            else if (type == 2) interString = interString.replace("?", "larger");
            resultSet.append(leftStmt).append(" ").append(interString).append(" ").append(rightStmt);
        } else {
            if (type == 1) interString = interString.replace("?", "less");
            else if (type == 2) interString = interString.replace("?", "smaller");
            resultSet.append(leftStmt).append(" ").append(interString).append(" ").append(rightStmt);
        }
        return resultSet.toString();
    }
}