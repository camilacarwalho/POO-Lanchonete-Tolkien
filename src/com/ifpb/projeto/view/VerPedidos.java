package com.ifpb.projeto.view;

import com.ifpb.projeto.control.CadastroProduto;
import com.ifpb.projeto.model.GerenciarMesa;
import com.ifpb.projeto.model.Pedido;
import com.ifpb.projeto.model.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class VerPedidos extends JDialog {
    private JPanel panel1;
    private JList listPedidos;
    private JButton editarButton;
    private JButton voltarButton;
    private static int numMesa = 0;

    public VerPedidos(){
        setContentPane(panel1);
        setTitle("Ver Pedidos");
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void setNumMesa(int mesa){
        numMesa = mesa;
    }

    private void createUIComponents() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<Pedido> pedidos = GerenciarMesa.verPedidos(numMesa);
        listPedidos = new JList();
        for(Pedido p:pedidos) {
            listModel.addElement(p.getNumeroPedido()+"-"+p.getProduto().getNome()+" Subtotal:"+p.getValorTotal());
            }
        listPedidos.setModel(listModel);
        listPedidos.setSelectedIndex(0);
        listPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
