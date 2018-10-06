package com.ifpb.projeto.view;

import com.ifpb.projeto.model.Gerencia;
import com.ifpb.projeto.model.GerenciarMesa;
import com.ifpb.projeto.model.Setor;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

                VerPedidos verpedidos = new VerPedidos();
                verpedidos.pack();
                verpedidos.setVisible(true);
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
    }

    public static void main(String[] args) {
        GerenciarMesas dialog = new GerenciarMesas();
        dialog.pack();
        dialog.setVisible(true);
    }


    private void createUIComponents() {
        spinnerMesa = new JSpinner();
        spinnerMesa.setModel(new SpinnerNumberModel(1, 1, null, 1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)spinnerMesa.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
    }
}
