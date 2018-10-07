package com.ifpb.projeto.view;

import com.ifpb.projeto.control.GerenciaComandasFechadas;
import com.ifpb.projeto.model.Comanda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Gerência extends JFrame {

    private JPanel panel1;
    private JButton okButton;
    private JTable tableComandas;
    private JFormattedTextField fim;
    private JFormattedTextField inicio;
    private JButton pesquisarButton;
    private JLabel calendar;
    private JLabel calendar2;
    private DefaultTableModel model;

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
                        if(dataI.isAfter(dataF)){
                            JOptionPane.showMessageDialog(null,
                                    "Por favor insira um intervalo de datas válido!", "Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                            limparTabela();
                        }else{
                            try {
                                ArrayList<Comanda> fechadas = GerenciaComandasFechadas.between(dataI,dataF);
                                if(fechadas.isEmpty()){
                                    JOptionPane.showMessageDialog(null,
                                            "Não foi encontrada nenhuma comanda nesse intervalo de mtepo!","Mensagem de Erro",
                                            JOptionPane.ERROR_MESSAGE);
                                    limparTabela();
                                }else{
                                    limparTabela();
                                    for(Comanda comanda:fechadas){
                                        String[] dataQuebrada = comanda.getData().toString().split("-");
                                        String data = dataQuebrada[2]+"/"+dataQuebrada[1]+"/"+dataQuebrada[0];
                                        model.addRow(new String[]{data,"Comanda "+comanda.getNumComanda(),"R$ "+
                                                new DecimalFormat("0.00").format(comanda.getValorFinal())});
                                    }
                                }
                            }catch (IOException ex) {
                                JOptionPane.showMessageDialog(null,
                                        "Falha ao se conectar com o arquivo!","Mensagem de Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            }catch(ClassNotFoundException ex){
                                JOptionPane.showMessageDialog(null,
                                        "Problema com a classe Comanda","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }catch(NullPointerException ex){
                        JOptionPane.showMessageDialog(null,
                                "Problema com valor nulo", "Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }catch(DateTimeParseException ex ){
                        JOptionPane.showMessageDialog(null,
                                "Foi informada uma data inválida!", "Mensagem de Erro",
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

    private void limparTabela(){
        int quantRows = model.getRowCount();
        //Caso já exista alguma linha na tabela, a tabela será limpa antes de se fazer a pesquisa!
        if(quantRows>0){
            for(int i=0;i<quantRows;i++){
                model.removeRow(0);
            }
        }
    }

    private void createUIComponents() {
        MaskFormatter formatter1 = null;
        try {
            formatter1 = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        inicio = new JFormattedTextField(formatter1);
        fim = new JFormattedTextField(formatter1);

        ImageIcon icone = new ImageIcon("img/calendar.png");
        calendar = new JLabel(icone);
        calendar2 = new JLabel(icone);

        tableComandas = new JTable() {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };

        tableComandas.removeEditor();
        model = new DefaultTableModel();
        model.addColumn("Data");
        model.addColumn("Comanda");
        model.addColumn("Valor");
        tableComandas.setModel(model);
    }
}