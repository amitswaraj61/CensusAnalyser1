package stateProblem;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import csvBuilderLibrary.CSVBuilderException;
import csvBuilderLibrary.CSVBuilderFactory;
import csvBuilderLibrary.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CensusAnalyser {
    private int count;

    List<CSVStateCensus> csvUserList=null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder= CSVBuilderFactory.createCSVBuilder();
            csvUserList = ((ICSVBuilder) csvBuilder).getCSVFileList(reader,CSVStateCensus.class);
            return csvUserList.size();
        } catch (NoSuchFileException exception){
            throw new CensusAnalyserException("File Not Found", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (RuntimeException exception) {
            throw new CensusAnalyserException("File Delimiter Incorrect Or Header Incorrect", CensusAnalyserException.ExceptionType.DELIMITER_PROBLEM);
        } catch (IOException exception){
            exception.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int loadStateCodeData(String csvFilePath1) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath1));
            ICSVBuilder csvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCode> csvStateCodeIterator =csvBuilder.getCSVFileIterator(reader,CSVStateCode.class);
            return getCount(csvStateCodeIterator);

        }catch (NoSuchFileException exception){
            throw new CensusAnalyserException("File Not Found", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (RuntimeException exception){
            throw new CensusAnalyserException("File Delimiter Incorrect Or Header Incorrect", CensusAnalyserException.ExceptionType.DELIMITER_PROBLEM);
        } catch (IOException exception){
            exception.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
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

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if(csvUserList == null || csvUserList.size() == 0) {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensus> censusComparator=Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortedStateCensusJson=new Gson().toJson(csvUserList);
        return sortedStateCensusJson;
    }

    private void sort(Comparator<CSVStateCensus> censusComparator) {
        for(int i=0;i<csvUserList.size()-1;i++){
            for(int j=0;j<csvUserList.size()-i-1;j++){
                CSVStateCensus census1=csvUserList.get(i);
                CSVStateCensus census2=csvUserList.get(j+1);
                if(censusComparator.compare(census1,census2) > 0){
                    csvUserList.set(i,census2);
                    csvUserList.set(j+1,census1);
                }

            }
        }
    }
}


