package com.ifpb.projeto.view.auxilio;

import javax.swing.UIManager;

import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
        /**
         * Classe de Aplicação do Look and Feel GUI
         * Nimbus
         * @author Gabriel
         *
         */
public class AplicaNimbusLookAndFeel {

            private AplicaNimbusLookAndFeel() {

            }

            public static void pegaNimbus() {
                try {
                    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (UnsupportedLookAndFeelException e) {

                    System.out.println("Erro: " + e.getMessage());
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {

                    System.out.println("Erro: " + e.getMessage());
                    e.printStackTrace();

                } catch (InstantiationException e) {

                    System.out.println("Erro: " + e.getMessage());
                    e.printStackTrace();

                } catch (IllegalAccessException e) {

                    System.out.println("Erro: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
