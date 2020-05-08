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
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    private int count;

    Map<String, CSVStateCensus> csvStateCensusMap=null;
    Map<String, CSVStateCode> csvStateCodeMap=null;

    public CensusAnalyser(){
        csvStateCensusMap=new HashMap<String, CSVStateCensus>();
        csvStateCodeMap=new HashMap<String, CSVStateCode>();
    }
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensus> csvStateCensusIterator = csvBuilder.getCSVFileIterator(reader,CSVStateCensus.class);
            Iterable<CSVStateCensus> csvStateCensusIterable = () -> csvStateCensusIterator;
            StreamSupport.stream(csvStateCensusIterable.spliterator(),false)
                    .forEach(censusCSV -> csvStateCensusMap.put(censusCSV.state,censusCSV));
            return csvStateCensusMap.size();
        } catch (NoSuchFileException exception){
            throw new CensusAnalyserException("File Not Found", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (RuntimeException exception) {
            throw new CensusAnalyserException("File Delimiter Incorrect Or Header Incorrect", CensusAnalyserException.ExceptionType.DELIMITER_PROBLEM);
        } catch (IOException exception){
            exception.printStackTrace();
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
        }
        return 0;
    }

    public int loadStateCodeData(String csvFilePath1) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath1));
            ICSVBuilder csvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCode> csvStateCodeIterator = csvBuilder.getCSVFileIterator(reader,CSVStateCode.class);
            Iterable<CSVStateCode> csvStateCodeIterable = () -> csvStateCodeIterator;
            StreamSupport.stream(csvStateCodeIterable.spliterator(),false)
                    .forEach(censusCSV -> csvStateCodeMap.put(censusCSV.state,censusCSV));
            return csvStateCodeMap.size();
        }catch (NoSuchFileException exception){
            throw new CensusAnalyserException("File Not Found", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (RuntimeException exception){
            throw new CensusAnalyserException("File Delimiter Incorrect Or Header Incorrect", CensusAnalyserException.ExceptionType.DELIMITER_PROBLEM);
        } catch (IOException exception){
            exception.printStackTrace();
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
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
        if(csvStateCensusMap== null || csvStateCensusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensus> censusComparator=Comparator.comparing(census -> census.state);
        List<CSVStateCensus> sortedStateCode=this.sort(censusComparator,new ArrayList<>(csvStateCensusMap.values()));
        String sortedStateCodeJson=new Gson().toJson(sortedStateCode);
        return sortedStateCodeJson;
    }

    public String getStateWiseSortedData() throws CensusAnalyserException {
        if(csvStateCodeMap== null || csvStateCodeMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCode> censusComparator=Comparator.comparing(census -> census.state);
        List<CSVStateCode> sortedStateCode=this.sort(censusComparator,new ArrayList<>(csvStateCodeMap.values()));
        String sortedStateCodeJson=new Gson().toJson(sortedStateCode);
        return sortedStateCodeJson;
    }
    private <E> List<E> sort(Comparator<E> comparator, List<E> censusComparator) {
        for(int i=0;i<censusComparator.size()-1;i++){
            for(int j=0;j<censusComparator.size()-i-1;j++){
                E census1=censusComparator.get(i);
                E census2=censusComparator.get(j+1);
                if(comparator.compare(census1,census2) > 0){
                    censusComparator.set(i,census2);
                    censusComparator.set(j+1,census1);
                }
            }
        }
        return censusComparator;
    }
}


