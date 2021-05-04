package it.polito.tdp.extflightdelays.db;

import it.polito.tdp.extflightdelays.model.Distance;

public class TestDAO {

	public static void main(String[] args) {

		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();

		//System.out.println(dao.loadAllAirlines());
		//System.out.println(dao.loadAllAirports());
		//System.out.println(dao.loadAllFlights().size());
		
		for(Distance d: dao.getAvgDistances(4000))
			System.out.println(d);
		
	}

}
