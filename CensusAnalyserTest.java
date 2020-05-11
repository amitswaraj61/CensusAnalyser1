package stateProblem;

import com.google.gson.Gson;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\Kis\\Desktop\\StateCensusData.csv";
    private static final String INDIA_CENSUS_CSV_WRONG_FILE_PATH = "C:\\Users\\Kis\\StateCensusData.csv";
    private static final String INDIA_CENSUS_CSV_WRONG_TYPE = "C:\\Users\\Kis\\Desktop\\StateCensusData.cs";
    private static final String INDIA_CENSUS_CSV_DELIMITER_HEADER_WRONG = "E:\\StateCensusData.csv";
    private static final String STATE_CODE_CSV_FILE_PATH = "C:\\Users\\Kis\\Desktop\\StateCode.csv";
    private static final String INDIA_CODE_CSV_WRONG_TYPE = "C:\\Users\\Kis\\Desktop\\StateCode.cs";
    private static final String INDIA_STATE_CODE_DELIMITER_HEADER_WRONG = "E:\\StateCode.csv";

    @Test
    public void givenStateCensusCSVFile_countTotalRecord_shouldReturnTotalRecord() {
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
    public void givenStateCensusCsvFile_whenWrongFileName_shouldReturnFileNotFoundCustomException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        try {
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_FILE_PATH);
        } catch (CensusAnalyserException exception) {
            assertEquals("File Not Found", exception.getMessage());
        }
    }

    @Test
    public void givenStateCensusCsvFile_whenWrongFileType_shouldReturnFileNotFoundCustomException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        try {
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_TYPE);
        } catch (CensusAnalyserException exception) {
            assertEquals("File Not Found", exception.getMessage());
        }
    }

    @Test
    public void givenStateCensusCsvFile_whenDelimiterIncorrect_shouldReturnDelimiterIncorrectCustomException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        try {
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_DELIMITER_HEADER_WRONG);
        } catch (CensusAnalyserException exception) {
            assertEquals("File Delimiter Incorrect Or Header Incorrect", exception.getMessage());
        }
    }

    @Test
    public void givenStateCensusCsvFile_whenHeaderIncorrect_shouldReturnHeaderIncorrectCustomException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        try {
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_DELIMITER_HEADER_WRONG);
        } catch (CensusAnalyserException exception) {
            assertEquals("File Delimiter Incorrect Or Header Incorrect", exception.getMessage());
        }
    }

    @Test
    public void givenStateCodeCsvFile__countTotalRecord_shouldReturnTotalRecord() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int numOfRecords;
        try {
            numOfRecords = censusAnalyser.loadStateCodeData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
            assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCsvFile_whenWrongFileName_shouldReturnFileNotFoundCustomException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        try {
            censusAnalyser.loadStateCodeData(INDIA_CENSUS_CSV_FILE_PATH,INDIA_CENSUS_CSV_WRONG_FILE_PATH);
        } catch (CensusAnalyserException exception) {
            assertEquals("File Not Found", exception.getMessage());
        }
    }

    @Test
    public void givenStateCodeCsvFile_whenWrongFileType_shouldReturnFileNotFoundCustomException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        try {
            censusAnalyser.loadStateCodeData(INDIA_CENSUS_CSV_FILE_PATH,INDIA_CODE_CSV_WRONG_TYPE);
        } catch (CensusAnalyserException exception) {
            assertEquals("File Not Found", exception.getMessage());
        }
    }

    @Test
    public void givenStateCodeCsvFile_whenDelimiterIncorrect_shouldReturnDelimiterIncorrectCustomException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        try {
            censusAnalyser.loadStateCodeData(INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATE_CODE_DELIMITER_HEADER_WRONG);
        } catch (CensusAnalyserException exception) {
            assertEquals("File Delimiter Incorrect Or Header Incorrect", exception.getMessage());
        }
    }

    @Test
    public void givenStateCodeCsvFile_whenHeaderIncorrect_shouldReturnHeaderIncorrectCustomException() {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        try {
            censusAnalyser.loadStateCodeData(INDIA_CENSUS_CSV_FILE_PATH,INDIA_CENSUS_CSV_DELIMITER_HEADER_WRONG);
        } catch (CensusAnalyserException exception) {
            assertEquals("File Delimiter Incorrect Or Header Incorrect", exception.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadStateCodeData(INDIA_CENSUS_CSV_FILE_PATH,STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedData();
            CSVStateCensusDao[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensusDao[].class);
            assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WhenSortedOnPopulation_shouldReturnMostPopulousState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            assertEquals(199812341, censusCSV[censusCSV.length - 1].population);
        } catch (CensusAnalyserException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WhenSortedOnDensity_shouldReturnMostPopulationDensity() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getDensityWiseSortedCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            assertEquals(1102, censusCSV[censusCSV.length - 1].densityPerSqKm);
        } catch (CensusAnalyserException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WhenSortedOnLargestStateArea_shouldReturnLargestStateArea() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateAreaWiseSortedCensusData();
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            assertEquals(342239, censusCSV[censusCSV.length - 1].areaInSqKm);
        } catch (CensusAnalyserException exception) {
            exception.printStackTrace();
        }
    }
}