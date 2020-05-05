package stateProblem;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser {
    private int count;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<CSVStateCensus> csvUserIterator = this.getCSVFileIterator(reader,CSVStateCensus.class);
            while (csvUserIterator.hasNext()) {
                count++;
                CSVStateCensus csvUser = csvUserIterator.next();
            }
        } catch (NoSuchFileException exception){
            throw new CensusAnalyserException("File Not Found", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (RuntimeException exception) {
            throw new CensusAnalyserException("File Delimiter Incorrect Or Header Incorrect", CensusAnalyserException.ExceptionType.DELIMETER_PROBLEM);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return count;

    }
    public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<CSVStateCode> csvStateCodeIterator = this.getCSVFileIterator(reader,CSVStateCode.class);
            while (csvStateCodeIterator.hasNext()) {
                count++;
                CSVStateCode csvUser = csvStateCodeIterator.next();
            }
        }catch (NoSuchFileException exception){
            throw new CensusAnalyserException("File Not Found", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (RuntimeException exception){
            throw new CensusAnalyserException("File Delimiter Incorrect Or Header Incorrect", CensusAnalyserException.ExceptionType.DELIMETER_PROBLEM);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        return count;
    }
    private<E> Iterator<E>  getCSVFileIterator(Reader reader,
                                               Class csvClass) throws CensusAnalyserException{
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        }catch (IllegalStateException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }
}