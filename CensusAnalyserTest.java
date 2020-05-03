package stateProblem;

import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CensusAnalyserTest {
    private static  final String INDIA_CENSUS_CSV_FILE_PATH ="C:\\Users\\Kis\\Desktop\\StateCensusData.csv";
    private static final String INDIA_CENSUS_CSV_WRONG_FILE_PATH="C:\\Users\\Kis\\StateCensusData.csv";

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
    public void givenStateCensusCsvFile_whenwrongFileName_shouldReturnFileErrorCustomException(){
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        try{
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_FILE_PATH);
        }
        catch(CensusAnalyserException exception){
            assertEquals("Census File Problem", exception.getMessage());
        }
    }
}