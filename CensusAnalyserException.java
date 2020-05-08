package stateProblem;

class CensusAnalyserException extends Exception {
    private final String message;
    ExceptionType type;
    enum ExceptionType{
        CENSUS_FILE_PROBLEM,DELIMITER_PROBLEM,NO_CENSUS_DATA;
    }
    public CensusAnalyserException(String message, ExceptionType type)
    {
        super(message);
        this.type=type;
        this.message=message;
    }

}


//