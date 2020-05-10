package stateProblem;

public class CSVStateCensusDao {

    public  int population;
    public  int densityPerSqKm;
    public  int areaInSqKm;
    public String state;
    public String stateCode;

    public CSVStateCensusDao(CSVStateCensus censusCSV) {
        state = censusCSV.state;
        areaInSqKm=censusCSV.areaInSqkm;
        densityPerSqKm=censusCSV.densityPerSqKm;
        population=censusCSV.population;
    }
}
//