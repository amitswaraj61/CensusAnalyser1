package stateProblem;

public class CSVBuilderException extends Exception {
    private final String message;
    ExceptionType type;
    enum ExceptionType{
        CENSUS_FILE_PROBLEM,DELIMITER_PROBLEM;
    }
    public CSVBuilderException(String message,ExceptionType type)
    {
        super(message);
        this.type=type;
        this.message=message;
    }

}
