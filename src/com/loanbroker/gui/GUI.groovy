package com.loanbroker.gui

import groovy.swing.SwingBuilder

import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.event.ActionListener

import javax.swing.*

import com.loanbroker.services.Services
import com.loanbroker.services.Util
class GUI extends SwingBuilder{

	JFrame mainFrame
	JPanel contentPane , firstLayer ,secondLayer , thirdLayer
	JTextField txtCustomerName , txtSSN , txtLoanAmount , txtYear
	JTextArea txtAreaShowResult
	JButton btnReview
	String commissionValue , interestValue , statusValue , bankNameValue , rateValue , totalAmount
	
	def edt(){
		contentPane = new JPanel()
		contentPane.setLayout(new BorderLayout())
		
		firstLayer = new JPanel()
		secondLayer = new JPanel()
		thirdLayer = new JPanel()
		
		BoxLayout layout1 = new BoxLayout(firstLayer , BoxLayout.Y_AXIS)
		BoxLayout layout2 = new BoxLayout(secondLayer , BoxLayout.Y_AXIS)
		BoxLayout layout3 = new BoxLayout(thirdLayer , BoxLayout.Y_AXIS)
		
		mainFrame = new JFrame(title:'Loan Broker',
			defaultCloseOperation:JFrame.EXIT_ON_CLOSE ,
			size:[900 , 500] ,
			resizable:true ,
			locationRelativeTo:null,
			focusable:true)
		
		mainFrame.contentPane = contentPane
		
		//firstLayer component(s)
		txtCustomerName = new JTextField(15)
		txtSSN = new JTextField(15)
		txtLoanAmount = new JTextField(15)
		txtYear = new JTextField(15)
		btnReview = new JButton(text:'Review')
		btnReview.setSize(15 , 0)
		btnReview.addActionListener(result as ActionListener)
		
		firstLayer.add(Box.createRigidArea(new Dimension(0 , 20)))
		firstLayer.add(new JLabel('Customer Name : '))
		firstLayer.add(txtCustomerName)
		firstLayer.add(new JLabel('SSN : '))
		firstLayer.add(txtSSN)
		firstLayer.add(new JLabel('Loan Amount : '))
		firstLayer.add(txtLoanAmount)
		firstLayer.add(new JLabel('Year : '))
		firstLayer.add(txtYear)
		firstLayer.add(new JPanel(new BorderLayout()).add(btnReview))
		firstLayer.add(Box.createRigidArea(new Dimension(0 , 400)))
		firstLayer.setLayout(layout1)
			
		txtAreaShowResult = new JTextArea(13 ,20)
		secondLayer.add(Box.createRigidArea(new Dimension(0 , 40)))
		secondLayer.add(new JScrollPane(txtAreaShowResult))
		secondLayer.setLayout(layout2)
		secondLayer.setAlignmentX(Component.LEFT_ALIGNMENT)
		secondLayer.add(Box.createRigidArea(new Dimension(0 , 10)))
		
		contentPane.add(firstLayer , BorderLayout.WEST)
		contentPane.add(secondLayer , BorderLayout.CENTER)
		contentPane.add(thirdLayer , BorderLayout.EAST)
		//show frame
		mainFrame.show()
	}
	
	def result = {
		Services service = new Services()
		Util util = new Util()
		
		def response1 = service.getCreditScoring(txtCustomerName.text , txtSSN.text , txtLoanAmount.text)
		def response2 = service.getBankQuote(response1.creditScoring , response1.loan_amount , txtYear.text)
	
			if((Integer.parseInt(txtYear.text) <= 20 && (Integer.parseInt(txtLoanAmount.text) >= 80000 && Integer.parseInt(txtLoanAmount.text) < 800000))){
			  
				double comm = util.getCommission(response1.loan_amount.toDouble() , response2.bankName.toString() , (txtYear.text.toInteger()))
				double intr = util.getInterest(response2.rate.toDouble() , response1.loan_amount.toDouble() , txtYear.text.toInteger())

				if(intr > comm && (intr )){
					statusValue = 'Profit'
				}else statusValue = 'Lost'
			
				txtAreaShowResult.append(
				  "Result :\n\n" +
				  "Lender Name : ${response1.lender_name}\n" +
				  "Loan Amount : ${response1.loan_amount} PHP\n" +
				  "Commissions : ${comm} PHP\n" +				 
				  "Interest : $intr PHP\n" +
				  "Status : $statusValue\n\n" +
				  "Bank Quote :\n\n" +
				  "Bank Name :  $response2.bankName\n" +
				  "Rate : $response2.rate\n" +
				  "-----------------------------------------------------\n"
				  )
				
			}else if(Integer.parseInt(txtLoanAmount.text) <= 80000){
				JOptionPane.showMessageDialog(mainFrame ,'Please enter your desired loan amount (minimum Php 100,000).')
			}else if(Integer.parseInt(txtYear.text) > 20){	
				JOptionPane.showMessageDialog(mainFrame ,'Sorry.')
			}
	}
	
}
