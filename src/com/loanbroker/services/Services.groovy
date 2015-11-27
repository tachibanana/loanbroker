package com.loanbroker.services

import wslite.soap.SOAPClient

class Services {
	
	def getCreditScoring(customerNameValue , ssnValue , loanAmountValue){
		def client = new SOAPClient('https://dev-esb.toroserver.com/services/CreditAgencyServiceImpl.CreditAgencyServiceImplHttpsSoap11Endpoint/')
		def response = client.send(SOAPAction:'urn:getCreditScoring'){
			body{
				getCreditScoring(xmlns:'http://impl.service.creditagency.toro.com'){
					customerName(customerNameValue)
					ssn(ssnValue)
					loan_amount(loanAmountValue)
				}
			}
		}
		
		response.getCreditScoringResponse.return
	}
	
	def getBankQuote(creditScoringValue , loanAmountValue , termValue){
		def client = new SOAPClient('https://dev-esb.toroserver.com/services/LenderAgencyServiceImpl.LenderAgencyServiceImplHttpsSoap11Endpoint/')
		def response = client.send(SOAPAction:'urn:getBankQuote'){
			body{
				getBankQoute(xmlns:'http://impl.service.lenderagency.toro.com'){
					creditScoring(creditScoringValue)
					loanAmount(loanAmountValue)
					term(termValue)
				}
			}
		}
		
		response.getBankQuoteResponse.return
	}

}
