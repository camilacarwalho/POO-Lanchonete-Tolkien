package com.ifpb.projeto.view;

import com.ifpb.projeto.model.Comanda;
import com.ifpb.projeto.model.GerenciarMesa;
import com.ifpb.projeto.model.Pedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Cozinha extends JFrame {
    private JPanel panel1;
    private JButton atenderButton;
    private JComboBox comboBox1;
    private JButton voltarButton;

    public Cozinha(){
        setContentPane(panel1);
        setTitle("Cozinha");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuPrincipal menu = new MenuPrincipal();
                menu.pack();
                menu.setVisible(true);
            }
        });
        atenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String[] pedido = ((String)comboBox1.getSelectedItem()).split(" ");
                    try{
                        if(GerenciarMesa.atendePedido(Integer.parseInt(pedido[4]),Integer.parseInt(pedido[1]))){
                            JOptionPane.showMessageDialog(null,
                                    "Pedido atendido com sucesso!","Mensagem de Confirmação",
                                    JOptionPane.INFORMATION_MESSAGE);
                            atualizarComboBox();
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "Não foi possivel atender o pedido!","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(ArrayIndexOutOfBoundsException | NullPointerException ex){
                        JOptionPane.showMessageDialog(null,
                                "Ocorreu um Erro na hora de seelcionar o pedido para atender!","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(null,
                            "Não existem pedidos para se atender!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void atualizarComboBox(){
        comboBox1.removeItemAt(comboBox1.getSelectedIndex());
    }

    private void createUIComponents() {
        if(GerenciarMesa.quantPedidosNaoAtendidos()>0){
            String[] pedidos = new String[GerenciarMesa.quantPedidosNaoAtendidos()];
            int quant=0;
            for(Comanda comanda:GerenciarMesa.getMesas()){
                for(Pedido pedido:comanda.getComanda()){
                    if(!pedido.isAtendido()){
                        pedidos[quant++]="Pedido "+pedido.getNumeroPedido()+" | " + "Mesa "+comanda.getNumMesa();
                    }
                }
            }
            comboBox1 = new JComboBox(pedidos);
        }else{
            comboBox1 = new JComboBox();
        }
    }
}
