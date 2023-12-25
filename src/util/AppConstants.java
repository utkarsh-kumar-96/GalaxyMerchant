package util;

public interface AppConstants {
    String VALID_ROMAN_NUMERAL_REGEX="^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    String FAILED_QUERY = "I have no idea what you are talking about";
    String ASSIGN_CURRENCY_REGEX = "^(\\w+) is (\\w+)$";
    String TRADE_COMMODITY_REGEX = "^(.*?) is (\\d+) Credits$";
    String FIND_QUERY_REGEX = ".+ is (.+) (?:\\?)$";
    String COMPARE_QUERY_REGEX = "(?:Does|Is|does|is) (.+) (has more Credits than|has less Credits than|has more credits than|has less credits than|larger than|smaller than) (.+) (?:\\?)$";
    String INVALID_ROMAN_NUMERAL_FORMAT = "Requested number is in invalid format";
}