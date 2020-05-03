package stateProblem;

import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;

import static org.junit.Assert.*;
public class CensusAnalyserTest {
    private static  final String INDIA_CENSUS_CSV_FILE_PATH ="C:\\Users\\Kis\\Desktop\\StateCensusData.csv";
    private static final String INDIA_CENSUS_CSV_WRONG_FILE_PATH="C:\\Users\\Kis\\StateCensusData.csv";
    private static final String INDIA_CENSUS_CSV_WRONG_TYPE="C:\\Users\\Kis\\Desktop\\StateCensusData.cs";
    private static final String INDIA_CENSUS_CSV_DELIMETER_WRONG="E:\\StateCensusData.csv";
    @Test
    public void givenStateCensusCSVFile_countTotalRecord_shouldReturnTotalRecord()  {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int numOfRecords = 0;
        try {
            numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException exception) {
            exception.printStackTrace();
        }
    }
    @Test
    public void givenStateCensusCsvFile_whenWrongFileName_shouldReturnFileNotFoundCustomException(){
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        try{
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_FILE_PATH);
        }
        catch(CensusAnalyserException exception){
            assertEquals("File Not Found", exception.getMessage());
        }
    }
    @Test
    public void givenStateCensusCsvFile_whenWrongFileType_shouldReturnFileNotFoundCustomException(){
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        try{
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_TYPE);
        }
        catch(CensusAnalyserException exception){
            assertEquals("File Not Found", exception.getMessage());
        }
    }
    @Test
    public void givenStateCensusCsvFile_whenDelimiterIncorrect_shouldReturnDelimiterIncorrectCustomException(){
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        try{
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_DELIMETER_WRONG);
        }
        catch(CensusAnalyserException exception){
            assertEquals("File Delimiter Incorrect Or Header Incorrect", exception.getMessage());
        }
    }
    @Test
    public void givenStateCensusCsvFile_whenHeaderIncorrect_shouldReturnHeaderIncorrectCustomException(){
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        try{
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_DELIMETER_WRONG);
        }
        catch(CensusAnalyserException exception){
            assertEquals("File Delimiter Incorrect Or Header Incorrect", exception.getMessage());
        }
    }
}