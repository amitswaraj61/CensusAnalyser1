package csvBuilderLibrary;


public class CSVBuilderException extends Exception {
    public  String message;
    public ExceptionType type;
   public enum ExceptionType{
        CENSUS_FILE_PROBLEM,DELIMITER_PROBLEM
    }
    public CSVBuilderException(String message,CSVBuilderException.ExceptionType type)
    {
        super(message);
        this.type=type;
        this.message=message;
    }

}
//