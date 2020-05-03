package stateProblem;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser {
    private int count;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException{
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBean<CSVStateCensus> csvToBean=new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStateCensus> csvUseraIterator=csvToBean.iterator();
            while(csvUseraIterator.hasNext()){
                count++;
                CSVStateCensus csvUser = csvUseraIterator.next();
            }
        } catch (IOException exception ) {
                throw new CensusAnalyserException("Census File Problem", CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return count;
    }
}


