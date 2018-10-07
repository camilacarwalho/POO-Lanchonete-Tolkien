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
    }

    private void createUIComponents() {
        String[] pedidos = new String[GerenciarMesa.quantPedidosNaoAtendidos()];
        if(GerenciarMesa.quantPedidosNaoAtendidos()>0){
            int quant=0;
            for(Comanda comanda:GerenciarMesa.getMesas()){
                for(Pedido pedido:comanda.getComanda()){
                    if(!pedido.isAtendido()){
                        pedidos[quant++]="Pedido "+pedido.getNumeroPedido()+"| Mesa "+comanda.getNumMesa();
                    }
                }
            }
            comboBox1 = new JComboBox(pedidos);
        }else{
            comboBox1 = new JComboBox();
        }
    }
}
