package stateProblem;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm")
    public int areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public int densityPerSqKm;

    public CSVStateCensus(){

    }
    @Override
    public String toString(){
        return "IndiaCensusCSV{" +
                "State='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + areaInSqKm + '\'' +
                ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}
//
