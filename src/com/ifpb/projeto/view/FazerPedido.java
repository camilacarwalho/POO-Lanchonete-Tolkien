package com.ifpb.projeto.view;

import com.ifpb.projeto.control.CadastroProduto;
import com.ifpb.projeto.model.Comanda;
import com.ifpb.projeto.model.GerenciarMesa;
import com.ifpb.projeto.model.Produto;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.io.IOException;
import java.util.List;

public class FazerPedido extends JDialog{
    private JPanel panel1;
    private JButton adicionarButton;
    private JComboBox comboBoxProduto;
    private JSpinner spinnerQuantidade;
    private JButton voltarButton;
    private static int numMesa = 0;

    public FazerPedido(){
        setContentPane(panel1);
        setTitle("Fazer Pedido");
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
        comboBoxProduto = new JComboBox();
        try {
            List<Produto> produtos = CadastroProduto.getProdutos();
            String[] resultado = new String[produtos.size()];
            int cont = 0;
            for(Produto p:produtos){
                resultado[cont++] = p.getNome();
            }
            comboBoxProduto = new JComboBox(resultado);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
