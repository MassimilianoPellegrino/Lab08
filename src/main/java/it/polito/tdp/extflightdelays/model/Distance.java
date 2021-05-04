package it.polito.tdp.extflightdelays.model;

public class Distance {

	int idA1;
	int idA2;
	double avgDistanza;
	public Distance(int idA1, int idA2, double avgDistanza) {
		super();
		this.idA1 = idA1;
		this.idA2 = idA2;
		this.avgDistanza = avgDistanza;
	}
	public int getIdA1() {
		return idA1;
	}
	public void setIdA1(int idA1) {
		this.idA1 = idA1;
	}
	public int getIdA2() {
		return idA2;
	}
	public void setIdA2(int idA2) {
		this.idA2 = idA2;
	}
	public double getAvgDistanza() {
		return avgDistanza;
	}
	public void setAvgDistanza(double avgDistanza) {
		this.avgDistanza = avgDistanza;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idA1;
		result = prime * result + idA2;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distance other = (Distance) obj;
		if (idA1 != other.idA1)
			return false;
		if (idA2 != other.idA2)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "idA1 = " + idA1 + ", idA2 = " + idA2 + ", avgDistanza = " + avgDistanza;
	}
	
	
	
	
}
