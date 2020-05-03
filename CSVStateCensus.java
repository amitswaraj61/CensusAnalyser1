package stateProblem;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm")
    public int areaInSqkm;

    @CsvBindByName(column = "DensityPerSqKm")
    public int densityPerSqKm;

    public CSVStateCensus(){

    }

    public int getAreaInSqkm()
    {
        return areaInSqkm;
    }

    public  void setAreaInSqkm(int areaInSqkm){
        this.areaInSqkm=areaInSqkm;
    }

    public int getPopulation(){
        return  population;
    }

    public  void setPopulation(int population){
        this.population=population;
    }

    public int getDensityPerSqKm(){
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(int densityPerSqKm){
        this.densityPerSqKm=densityPerSqKm;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state=state;
    }

    @Override
    public String toString(){
        return "IndiaCensusCSV{" +
                "State='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + areaInSqkm + '\'' +
                ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}
