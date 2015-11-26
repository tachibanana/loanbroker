package com.loanbroker.gui

import groovy.swing.SwingBuilder

import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.event.ActionListener

import javax.swing.*
class GUI extends SwingBuilder{

	JFrame mainFrame
	JPanel contentPane , firstLayer ,secondLayer
	JTextField txtCustomerName , txtSSN , txtLoanAmount , txtYear , txtPercent
	JTextArea txtAreaShowResult
	JButton btnReview
	
	String commissionValue , interestValue , statusValue , bankNameValue , rateValue
	
	def edt(){
		contentPane = new JPanel()
		contentPane.setLayout(new BorderLayout())
		
		firstLayer = new JPanel()
		secondLayer = new JPanel()
		
		BoxLayout layout1 = new BoxLayout(firstLayer , BoxLayout.Y_AXIS)
		BoxLayout layout2 = new BoxLayout(secondLayer , BoxLayout.Y_AXIS)

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
		txtPercent = new JTextField(15)
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
		firstLayer.add(new JLabel('Percent : '))
		firstLayer.add(txtPercent)
		firstLayer.add(new JLabel('Month : '))
		firstLayer.add(txtYear)
		firstLayer.add(new JPanel(new BorderLayout()).add(btnReview))
		firstLayer.add(Box.createRigidArea(new Dimension(0 , 400)))
		firstLayer.setLayout(layout1)
		
		//secondeLayer component(s)		
		txtAreaShowResult = new JTextArea(13 ,20)
		secondLayer.add(Box.createRigidArea(new Dimension(0 , 40)))
		secondLayer.add(new JScrollPane(txtAreaShowResult))
		secondLayer.setLayout(layout2)
		secondLayer.setAlignmentX(Component.LEFT_ALIGNMENT)
		secondLayer.add(Box.createRigidArea(new Dimension(0 , 10)))
		
		contentPane.add(firstLayer , BorderLayout.WEST)
		contentPane.add(secondLayer , BorderLayout.CENTER)
		
		//show frame
		mainFrame.show()
	}
	
	def result = {
		StringBuilder str = new StringBuilder()
		txtAreaShowResult.append(
		"Result :\n\n" +
		"Lender Name : $txtCustomerName.text\n" +
		"Loan Amount : $txtLoanAmount.text\n" +
		"Commissions : $commissionValue\n" +
		"Interest : $interestValue\n" +
		"Status : $statusValue\n\n" +
		"Bank Quote :\n\n" +
		"Bank Name :\n" +
		"Rate :"
		)
		
		
		
	}
	
}
