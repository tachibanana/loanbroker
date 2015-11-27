package com.loanbroker.services

class Util {
	
	int getInterest(double rate , double amount, double time){
				
		return (((rate/100) * amount) * time)
	}
	
	double getCommission(double amount, String bankName , double time){
		double commissions = amount * time
		
		switch(bankName){
			case 'Bronze Bank 1':
				commissions *= 0.45/100
				break;
			case 'Bronze Bank 2':
				commissions *= 0.50/100
				break;
			case 'Bronze Bank 3':
				commissions *= 0.75/100
				break;
			case 'Bronze Bank 4':
				commissions *= 1/100
				break;
			case 'Bronze Bank 5':
				commissions *= 1.25/100
				break;
			case 'Bronze Bank 6':
				commissions *= 1.5/100
				break;
			case 'Bronze Bank 7':
				commissions *= 1.75/100
				break;
			case 'Silver Bank 1':
				commissions *= 2.0/100
				break;
			case 'Silver Bank 2':
				commissions *= 2.25/100
				break;
			case 'Silver Bank 3':
				commissions *= 2.52/100
				break;
			case 'Silver Bank 4':
				commissions *= 2.55/100
				break;
			case 'Silver Bank 5':
				commissions *= 2.57/100
				break;
			case 'Silver Bank 6':
				commissions *= 2.75/100
				break;
			case 'Gold Bank 1':
				commissions *= 3.0/100
				break;
			case 'Gold Bank 2':
				commissions *= 3.25/100
				break;
			case 'Gold Bank 3':
				commissions *= 3.5/100
				break;
			case 'Gold Bank 4':
				commissions *= 3.75/100
				break;
			case 'Gold Bank 5':
				commissions *= 4.0/100
				break;
			case 'Gold Bank 6':
				commissions *= 4.25/100
				break;
			case 'Gold Bank 7':
				commissions *= 4.5/100
				break;
		}
		
		commissions
	}

}
