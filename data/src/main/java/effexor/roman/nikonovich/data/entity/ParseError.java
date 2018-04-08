package effexor.roman.nikonovich.data.entity;

public class ParseError extends Exception {

    private ErrorType clearError;

    public ParseError(ErrorType clearError) {
        this.clearError = clearError;
    }

    public ErrorType getClearError() {
        return clearError;
    }
}
