package stateProblem;

class CensusAnalyserException extends Exception {
    public  String message;
   ExceptionType type;
   public enum ExceptionType{
        CENSUS_FILE_PROBLEM,DELIMITER_PROBLEM,NO_CENSUS_DATA;
    }

    public CensusAnalyserException(String message,String name){
        super(message);
        this.type=ExceptionType.valueOf(name);
    }
    public CensusAnalyserException(String message, ExceptionType type)
    {
        super(message);
        this.type=type;
        this.message=message;
    }

}

//
