package com.ifpb.projeto.view;

import com.ifpb.projeto.model.Gerencia;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gerência extends JFrame {
    private JPanel panel1;
    private JButton okButton;
    private JTable tableComandas;
    private JFormattedTextField fim;
    private JFormattedTextField inicio;
    private JButton pesquisarButton;
    private JLabel calendar;
    private JLabel calendar2;

    public Gerência(){
        setContentPane(panel1);
        setTitle("Gerência");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDate dataI = null;
                LocalDate dataF = null;
                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("dd/MM/yyyy");

                String datainicio = inicio.getText();
                String datafinal = fim.getText();


                if (inicio.getText().length()<10 || fim.getText().length()<10) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, preencha todos os campos corretamente!", "Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    try{
                        dataI = LocalDate.parse(datainicio,formatter);
                        dataF = LocalDate.parse(datafinal,formatter);
                        Gerencia.between(dataI, dataF);
                    }catch(NullPointerException ex){
                        JOptionPane.showMessageDialog(null,
                                "Erro na conversão da data", "Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal menu = new MenuPrincipal();
                menu.pack();
                menu.setVisible(true);
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

        inicio = new JFormattedTextField();
        fim = new JFormattedTextField();
        if(formatter1!=null){
            formatter1.install(inicio);
            formatter1.install(fim);
        }

        ImageIcon icone = new ImageIcon("img/calendar.png");
        calendar = new JLabel(icone);
        calendar2 = new JLabel(icone);
    }
}