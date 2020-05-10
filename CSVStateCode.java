package stateProblem;

import com.opencsv.bean.CsvBindByName;
public class CSVStateCode{
    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;

    @CsvBindByName(column = "StateName", required = true)
    public String stateName;

    @CsvBindByName(column = "Tin",required = true)
    public int tin;

   @CsvBindByName(column = "StateCode",required = true)
   public String stateCode;

   public CSVStateCode(){

   }
    public  int getSrNo(){
       return srNo;
    }

    public void setSrNo(int srNo){
       this.srNo=srNo;
    }

    public String getStateName()
    {
       return stateName;
    }

    public void setStateName(String stateName){
        this.stateName=stateName;
    }

    public int getTin(){
       return tin;
    }

    public void setTin(int tin){
       this.tin=tin;
    }

    public String getStateCode(){
        return stateCode;
    }

    public void setStateCode(String stateCode){
       this.stateCode=stateCode;
    }
    @Override
    public String toString(){
        return "IndiaStateCSV{" +
                "SrNo='" + srNo + '\'' +
                ", StateName='" + stateName + '\'' +
                ", Tin='" + tin + '\'' +
                ", StateCode='" + stateCode + '\'' +
                '}' ;
   }
}
//