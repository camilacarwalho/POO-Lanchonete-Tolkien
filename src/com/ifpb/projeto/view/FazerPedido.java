package com.ifpb.projeto.view;

import com.ifpb.projeto.Exceptions.QuantidadePorPedidoPositivaException;
import com.ifpb.projeto.control.CadastroProduto;
import com.ifpb.projeto.model.Comanda;
import com.ifpb.projeto.model.GerenciarMesa;
import com.ifpb.projeto.model.Pedido;
import com.ifpb.projeto.model.Produto;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class FazerPedido extends JDialog{
    private JPanel panel1;
    private JButton adicionarButton;
    private JComboBox comboBoxProduto;
    private JSpinner spinnerQuantidade;
    private JButton voltarButton;
    private JList listProdutos;
    private static int numMesa = 0;

    public FazerPedido(){
        setContentPane(panel1);
        setTitle("Fazer Pedido");
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pedido = (String) listProdutos.getSelectedValue();
                try {
                    Pedido p = new Pedido(CadastroProduto.buscarPorCodigo(Integer.parseInt(pedido.split("-")[0])),
                            (int) spinnerQuantidade.getValue());
                    if(GerenciarMesa.fazerPedido(numMesa,p)){
                        JOptionPane.showMessageDialog(null, "Pedido feito com sucesso!", "Mensagem de Erro",
                                JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possivel fazer o pedido!", "Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (QuantidadePorPedidoPositivaException e1) {
                    JOptionPane.showMessageDialog(null, "A quantidade de produtos por pedido deve ser um valo positivo",
                            "Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Problema com a classe Produto", "Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex){
                    JOptionPane.showMessageDialog(null, "Falha na conexão com o arquivo", "Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Problema com a conversão do código do produto", "Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void setNumMesa(int mesa){
        numMesa = mesa;
    }

    private void createUIComponents() {
        spinnerQuantidade = new JSpinner();
        spinnerQuantidade.setModel(new SpinnerNumberModel(1, 1, null, 1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)spinnerQuantidade.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        try {
            for(Produto p:CadastroProduto.getProdutos()) {
                listModel.addElement(p.getCodigo()+"-"+p.getNome());
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Problema com a classe Produto", "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Falha na conexão com o arquivo", "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        listProdutos = new JList();
        listProdutos.setModel(listModel);
        listProdutos.setSelectedIndex(0);
        listProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
