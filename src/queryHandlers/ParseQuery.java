package queryHandlers;

public class ParseQuery {
    public void execute(String[] queries) {
        Statement handler = new CurrencyHandler(new TradeHandler(new FindQueryHandler(new ComparisonQueryHandler(null))));
        Metadata metadata = new Metadata();
        for (String q : queries) {
            metadata.setQuery(q);
            String execute = handler.execute(metadata);
            if (execute != null) System.out.println(execute);
        }
    }
}
