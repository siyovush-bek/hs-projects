package engine.dto;

public class QuizResponse {

    private static final String SUCCESS_MESSAGE = "Congratulations, you're right!";
    private static final String FAILURE_MESSAGE = "Wrong answer! Please, try again.";

    private boolean success;
    private String feedback;

    public QuizResponse(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public QuizResponse() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        feedback = success ? SUCCESS_MESSAGE : FAILURE_MESSAGE;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
