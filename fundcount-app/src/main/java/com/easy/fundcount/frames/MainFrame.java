package com.easy.fundcount.frames;

import com.easy.fundcount.exceptions.CalculateProfitException;
import com.easy.fundcount.exceptions.GetFixerException;
import com.easy.fundcount.interfaces.FixerFacade;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

/**
 * Main frame
 */
public class MainFrame extends JFrame {

  private JLabel labelResult;
  private JDatePickerImpl datePicker;
  private JTextField input;
  private FixerFacade facade;

  @Autowired
  public MainFrame(String title, FixerFacade facade) {
    super();
    this.facade = facade;
    setTitle(title);
    setSize(640, 480);
  }

  public void init() {

    setBounds(150, 150, 400, 150);

    setResizable(false);

    Container container = this.getContentPane();
    container.setLayout(new GridLayout(4, 2, 0, 0));

    JLabel label = new JLabel("Amount EUR:");
    container.add(label);

    input = new JTextField("", 5);
    input.setText("100");
    container.add(input);

    JLabel labelDate = new JLabel("Date:");
    container.add(labelDate);

    UtilDateModel model = new UtilDateModel();
    model.setDate(2017, 4, 2);
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");
    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    container.add(datePicker);

    JButton button = new JButton("Calculate");
    button.addActionListener(new ButtonEventListener());
    container.add(button);

    labelResult = new JLabel("Profit is ...");
    container.add(labelResult);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }

  class ButtonEventListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Date date;
      double amount;
      try {
        date = (Date) datePicker.getModel().getValue();
        amount = Double.parseDouble(input.getText());
        if (date == null)
          throw new Exception();
      } catch (Exception ex) {
        labelResult.setText("Error input data");
        return;
      }

      try {
        double profit = facade.getProfit(date, amount);
        labelResult.setText(String.format("Profit is %1$,.2f RUB", profit));

      } catch (GetFixerException e1) {
        labelResult.setText("Error of get data from fixer.io");
      } catch (CalculateProfitException e1) {
        labelResult.setText("Error of during calculate");
      }


    }
  }

}
