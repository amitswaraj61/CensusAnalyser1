package stateProblem;

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
            ICSVBuilder csvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensus> csvUserIterator =csvBuilder.getCSVFileIterator(reader,CSVStateCensus.class);
            return getCount(csvUserIterator);
        } catch (NoSuchFileException exception){
            throw new CensusAnalyserException("File Not Found", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (RuntimeException exception) {
            throw new CensusAnalyserException("File Delimiter Incorrect Or Header Incorrect", CensusAnalyserException.ExceptionType.DELIMETER_PROBLEM);
        } catch (IOException exception){
            exception.printStackTrace();
        }
        return 0;
    }

    public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCode> csvStateCodeIterator =csvBuilder.getCSVFileIterator(reader,CSVStateCode.class);
            return getCount(csvStateCodeIterator);

        }catch (NoSuchFileException exception){
            throw new CensusAnalyserException("File Not Found", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (RuntimeException exception){
            throw new CensusAnalyserException("File Delimiter Incorrect Or Header Incorrect", CensusAnalyserException.ExceptionType.DELIMETER_PROBLEM);
        } catch (IOException exception){
            exception.printStackTrace();
        }

        return 0;
    }

    private <E> int getCount(Iterator<E> iterator)
    {
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;
    }
}


