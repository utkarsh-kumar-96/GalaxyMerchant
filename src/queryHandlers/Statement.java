package queryHandlers;

import util.AppConstants;

public abstract class Statement {
    private final Statement queryProcessor;

    Statement(Statement queryProcessor) {
        this.queryProcessor = queryProcessor;
    }

    public String execute(Metadata metadata) {
        if (queryProcessor == null) return AppConstants.FAILED_QUERY;
        return queryProcessor.execute(metadata);
    }
}