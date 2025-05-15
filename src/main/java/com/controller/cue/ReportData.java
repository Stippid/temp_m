package com.controller.cue;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportData {
   private String unitType;
   private Map<String, List<String>> armData;

   public ReportData(String unitType) {
       this.unitType = unitType;
       this.armData = new LinkedHashMap<>(); // Use LinkedHashMap to maintain order
   }

   public String getUnitType() {
       return unitType;
   }
   public void addArmData(String armName, List<String> personnelCounts) {
   	this.armData.put(armName, personnelCounts);
   }
    
   public Map<String, List<String>> getArmData() {
   	return armData;
   }

}