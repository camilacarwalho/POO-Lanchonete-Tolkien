package com.ifpb.projeto.view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gerência extends JDialog {
    private JPanel panel1;
    private JButton okButton;
    private JTable table1;
    private JFormattedTextField fim;
    private JFormattedTextField inicio;
    private JButton pesquisarButton;
    private JLabel calendar;
    private JLabel calendar2;

    public Gerência(){
        setContentPane(panel1);
        setTitle("Gerência");
        setModal(true);

        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDate dataI = null;
                LocalDate dataF = null;
                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("dd/MM/yyyy");

                String datainicio = inicio.getText();
                String datafinal = fim.getText();
                dataI = LocalDate.parse(datainicio,formatter);
                dataF = LocalDate.parse(datafinal,formatter);


                if (inicio.equals("") || fim.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, preencha todos os campos corretamente!", "Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }

    private void createUIComponents() {
        MaskFormatter formatter1 = null;
        try {
            formatter1 = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fim = new JFormattedTextField();

        inicio = new JFormattedTextField();



        if(formatter1!=null){
            formatter1.install(inicio);
        }
        if(formatter1!=null){
            formatter1.install(fim);
        }

        ImageIcon icone = new ImageIcon("img/calendar.png");
        calendar = new JLabel(icone);
        calendar2 = new JLabel(icone);
    }
}