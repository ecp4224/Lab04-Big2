public class Result {
    private boolean result;
    private String reason;

    public static Result success() {
        return new Result(true);
    }

    public static Result fail() {
        return new Result(false);
    }

    public static Result fail(String reason) {
        return new Result(false, reason);
    }

    private Result(boolean result) {
        this.result = result;
        this.reason = "N/A";
    }

    private Result(boolean result, String reason) {
        this.reason = reason;
        this.result = result;
    }

    public boolean didPass() {
        return result;
    }

    public String getReason() {
        return reason;
    }
}
