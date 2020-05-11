package stateProblem;

import com.google.gson.Gson;
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

    Map<String, CSVStateCensusDao> csvStateCensusMap = null;

    public CensusAnalyser() {

        csvStateCensusMap = new HashMap<String, CSVStateCensusDao>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath, CSVStateCensus.class);
    }

    public int loadStateCodeData(String csvCensusFile,String csvStateFile) throws CensusAnalyserException {
        this.loadIndiaCensusData(csvCensusFile);
        return this.loadCensusData(csvStateFile, CSVStateCode.class);
    }

    private <E> int loadCensusData(String csvFilePath, Class<E> censusCSVClass) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvStateCensusIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> csvStateCensusIterable = () -> csvStateCensusIterator;
            if (censusCSVClass.getName().equals("stateProblem.CSVStateCensus")) {
                StreamSupport.stream(csvStateCensusIterable.spliterator(), false)
                        .map(CSVStateCensus.class::cast)
                        .forEach(censusCSV -> csvStateCensusMap.put(censusCSV.state, new CSVStateCensusDao(censusCSV)));
                        return csvStateCensusMap.size();
            }
            if (censusCSVClass.getName().equals("stateProblem.CSVStateCode")) {
                StreamSupport.stream(csvStateCensusIterable.spliterator(), false)
                        .map(CSVStateCode.class::cast)
                        .filter(stateCSV -> csvStateCensusMap.get(stateCSV.stateName) != null)
                        .forEach(stateCSV -> csvStateCensusMap.get(stateCSV.stateName).stateCode = stateCSV.stateCode);
                        return csvStateCensusMap.size();
            }
        } catch (NoSuchFileException exception) {
            throw new CensusAnalyserException("File Not Found", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException exception) {
            throw new CensusAnalyserException("File Delimiter Incorrect Or Header Incorrect", CensusAnalyserException.ExceptionType.DELIMITER_PROBLEM);
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
        return 0;
    }

    private <E> int getCount(Iterator<E> iterator) {
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (csvStateCensusMap == null || csvStateCensusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.state);
        List<CSVStateCensusDao> sortedStateCode = this.sort(censusComparator, new ArrayList<>(csvStateCensusMap.values()));
        String sortedStateCodeJson = new Gson().toJson(sortedStateCode);
        return sortedStateCodeJson;
    }

    public String getStateWiseSortedData() throws CensusAnalyserException {
        if (csvStateCensusMap == null || csvStateCensusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.state);
        List<CSVStateCensusDao> sortedStateCode = this.sort(censusComparator, new ArrayList<>(csvStateCensusMap.values()));
        String sortedStateCodeJson = new Gson().toJson(sortedStateCode);
        return sortedStateCodeJson;
    }

    private <E> List<E> sort(Comparator<E> comparator, List<E> censusComparator) {
        for (int i = 0; i < censusComparator.size() - 1; i++) {
            for (int j = 0; j < censusComparator.size() - i - 1; j++) {
                E census1 = censusComparator.get(j);
                E census2 = censusComparator.get(j + 1);
                if (comparator.compare(census1, census2) > 0) {
                    censusComparator.set(j, census2);
                    censusComparator.set(j + 1, census1);
                }
            }
        }
        return censusComparator;
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (csvStateCensusMap == null || csvStateCensusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.population);
        List<CSVStateCensusDao> sortedStateCode = this.sort(censusComparator, new ArrayList<>(csvStateCensusMap.values()));
        String sortedStateCodeJson = new Gson().toJson(sortedStateCode);
        return sortedStateCodeJson;
    }

    public String getDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (csvStateCensusMap == null || csvStateCensusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        List<CSVStateCensusDao> sortedStateCode = this.sort(censusComparator, new ArrayList<>(csvStateCensusMap.values()));
        String sortedStateCodeJson = new Gson().toJson(sortedStateCode);
        return sortedStateCodeJson;
    }

    public String getStateAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (csvStateCensusMap == null || csvStateCensusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CSVStateCensusDao> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        List<CSVStateCensusDao> sortedStateCode = this.sort(censusComparator, new ArrayList<>(csvStateCensusMap.values()));
        String sortedStateCodeJson = new Gson().toJson(sortedStateCode);
        return sortedStateCodeJson;
    }
}


