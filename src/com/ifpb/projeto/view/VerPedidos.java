package com.ifpb.projeto.view;

import com.ifpb.projeto.model.GerenciarMesa;
import com.ifpb.projeto.model.Pedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GerenciarMesa.verTodosOsPedidos(numMesa)==null){
                    JOptionPane.showMessageDialog(null,
                            "Não Existe pedidos para editar!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    try{
                        if(GerenciarMesa.getComanda(numMesa).getPedido(Integer.parseInt(
                                listPedidos.getSelectedValue().toString().split(" ")[0])).isAtendido()){
                            JOptionPane.showMessageDialog(null,
                                    "Este pedido já foi atendido. Não possivel editá-lo","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }else{
                            EditarPedido.setIdPedido(Integer.parseInt(listPedidos.getSelectedValue().toString().split(" ")[0]));
                            EditarPedido editarpedio = new EditarPedido();
                            editarpedio.pack();
                            editarpedio.setVisible(true);
                            atualizarLista();
                        }
                    }catch(NullPointerException ex ){
                        JOptionPane.showMessageDialog(null,
                                "Não Foi Selecionado nenhum pedido!","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    public static void setNumMesa(int mesa){
        numMesa = mesa;
    }

    public static int getNumMesa(){
        return numMesa;
    }

    private void createUIComponents() {
        listPedidos = new JList();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<Pedido> pedidos = GerenciarMesa.verTodosOsPedidos(numMesa);
        if(pedidos!=null){
            DecimalFormat fm = new DecimalFormat("0.00");
            for(Pedido p:pedidos) {
                listModel.addElement(p.getNumeroPedido()+" - "+p.getProduto().getNome()+"|Quant: "+p.getQuantidade()+
                        " |Subtotal:"+ fm.format(p.getValorTotal()));
            }
            listPedidos.setModel(listModel);
            listPedidos.setSelectedIndex(0);
            listPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
    }

    private void atualizarLista(){
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<Pedido> pedidos = GerenciarMesa.verTodosOsPedidos(numMesa);
        if(pedidos!=null){
            DecimalFormat fm = new DecimalFormat("0.00");
            for(Pedido p:pedidos) {
                listModel.addElement(p.getNumeroPedido()+" - "+p.getProduto().getNome()+"|Quant: "+p.getQuantidade()+
                        " |Subtotal:"+fm.format(p.getValorTotal()));
            }
            listPedidos.setModel(listModel);
        }else listPedidos.setModel(new DefaultListModel<String>());
    }

}
