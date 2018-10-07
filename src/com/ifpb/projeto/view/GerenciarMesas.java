package com.ifpb.projeto.view;

import com.ifpb.projeto.Exceptions.ComandaExistenteException;
import com.ifpb.projeto.Exceptions.NumeroMesaPositivoException;
import com.ifpb.projeto.model.Comanda;
import com.ifpb.projeto.model.Gerencia;
import com.ifpb.projeto.model.GerenciarMesa;
import com.ifpb.projeto.model.Setor;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

public class GerenciarMesas extends JFrame{
    private JPanel panel1;
    private JButton novaComandaButton;
    private JButton verPedidosButton;
    private JButton fazerPedidoButton;
    private JButton encerrarComandaButton;
    private JSpinner spinnerMesa;
    private JButton voltarButton;

    public GerenciarMesas(){
        setContentPane(panel1);
        setTitle("Gerenciar Mesas");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        verPedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GerenciarMesa.getComanda((int)spinnerMesa.getValue())==null){
                    JOptionPane.showMessageDialog(null,
                            "Não existe comanda criada para esta mesa!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    VerPedidos.setNumMesa((int)spinnerMesa.getValue());
                    VerPedidos verpedidos = new VerPedidos();
                    verpedidos.pack();
                    verpedidos.setVisible(true);
                }
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuPrincipal menu = new MenuPrincipal();
                menu.pack();
                menu.setVisible(true);
            }
        });
        novaComandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GerenciarMesa.gerarComanda((int)spinnerMesa.getValue());
                    JOptionPane.showMessageDialog(null,
                            "Foi criada uma comanda para a mesa "+(int)spinnerMesa.getValue(),"Mensagem de Confirmação",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (NumeroMesaPositivoException e1) {
                    JOptionPane.showMessageDialog(null,
                            "O numero informado para esta mesa é um valor inválido!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                } catch (ComandaExistenteException e1) {
                    JOptionPane.showMessageDialog(null,
                            "Já existe uma comanda criada para esta mesa!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fazerPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GerenciarMesa.getComanda((int)spinnerMesa.getValue())==null){
                    JOptionPane.showMessageDialog(null,
                            "Não existe comanda criada para esta mesa!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    FazerPedido.setNumMesa((int)spinnerMesa.getValue());
                    FazerPedido fp = new FazerPedido();
                    fp.pack();
                    fp.setVisible(true);
                }
            }
        });
        encerrarComandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comanda comanda = GerenciarMesa.getComanda((int)spinnerMesa.getValue());
                if(comanda==null){
                    JOptionPane.showMessageDialog(null,
                            "Não existe comanda criada para esta mesa!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    if(comanda.allIsAtendido()){
                        try {
                            if(GerenciarMesa.encerrarComanda((int)spinnerMesa.getValue())){
                                JOptionPane.showMessageDialog(null,
                                        "Comanda Encerrada com Sucesso!\nValor Total R$"+
                                                new DecimalFormat("0.00").format(comanda.getValorFinal()),"Mensagem de Erro",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "Falha ao se conectar com o arquivo!","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }catch(ClassNotFoundException ex){
                            JOptionPane.showMessageDialog(null,
                                    "Problema com a classe Comanda","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Esta comanda ainda possui pedidos não atendidos!","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }


    private void createUIComponents() {
        spinnerMesa = new JSpinner();
        spinnerMesa.setModel(new SpinnerNumberModel(1, 1, null, 1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)spinnerMesa.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
    }
}
