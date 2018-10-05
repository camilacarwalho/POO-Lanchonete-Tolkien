package com.ifpb.projeto.view.auxilio;

import javax.swing.JTextField;

import javax.swing.text.AttributeSet;

import javax.swing.text.BadLocationException;

import javax.swing.text.Document;

import javax.swing.text.PlainDocument;



/**

 * Esta classe permite fazer entrada de valores tipo double

 * apenas aceitará valores double dentro de uma faixa designada ou qualquer valor double

 * Também permite o uso de limite de decimais para informar o número máximo de casas de decimais desejado

 * a formatação utilizada foi vírgula ao invés de ponto para facilitar formato brasileiro ao digitar

 * ponto ou vírgula visualmente aparece como vírgula

 *

 * @author junior

 * @version 1.0

 */

public class FloatField extends JTextField

{

    private double maiorLimit;

    private int casasDecimais;

    private static final double MAX_DEFAULT = 999999;



    // construtor completo todas as propriedades

    public FloatField(int cols, double amaiorLimit, int acasasDecimais)

    {

        super(cols);

        this.maiorLimit = amaiorLimit;

        this.casasDecimais = acasasDecimais;

        setHorizontalAlignment(JTextField.LEFT); // alinhamento à direita

    }



    // construtor com cols e numero de casas decimais

    public FloatField(int cols, int acasasDecimais) {

        this(cols, MAX_DEFAULT, acasasDecimais);

    }



    // construtor padrão tem que informar pelo menos o cols para definir o tamanho do field

    public FloatField(int cols)

    {

        this(cols, MAX_DEFAULT, 2);

    }



    // seta limites ao valor máximo e mínino permitidos

    public void setLimites(double amenorLimit, double amaiorLimit)

    {

        this.maiorLimit = amaiorLimit;

    }



    // define a quantidade de casas decimais

    public void setCasasDecimais(int acasasDecimais) {

        this.casasDecimais = acasasDecimais;

    }



    // método para obter o valor em formato double

    public double getFloat()

    {

        try

        {

            if (getText().trim().equals("")) return 0;

            return Double.parseDouble(getText().replace(',', '.'));

        }

        catch(NumberFormatException ex)

        {

            return 0;

        }

    }



    // método para "setar" o valor da JTextField

    public void setFloat(Double valor) {

        setText(String.valueOf(valor));

    }



    // método interno que define qual Document o JTextField usará

    protected Document createDefaultModel()

    {

        return new FloatDocument();

    }



    /**

     * classe interna LimitDocument

     * objetivo: formatar entrada e limitar valores da FloatField

     */

    class FloatDocument extends PlainDocument

    {

        // método herdado

        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException

        {

            // tem algum valor "entrando"?

            if(str != null)

            {

                // se usuário pressionou PONTO, convertemos para vírgura - visual brasileiro

                str = str.replace('.', ',');

                try

                {

                    // acrescenta ao texto o digitado numa var local (newStr)

                    String newStr = getText(0, getLength()) + str;



                    // só foi digitado a vírgula? substitui por "0,"

                    if (newStr.equals(",")) {

                        newStr = "0,";

                        str = "0,";

                    }



                    // converte newStr para float, tomando o cuidado de converter vírgula para ponto - formato java

                    double f = Double.parseDouble(newStr.replace(',', '.'));



                    // conta quantas casas decimais nós temos

                    int numeroDecimais = (newStr.indexOf(',') > -1 ? newStr.substring(newStr.indexOf(',')).length()-1 : 0);



                    // está dentro dos limites?

                    if (f <= maiorLimit && numeroDecimais <= casasDecimais)

                    {

                        super.insertString(offs, str, a);  // permite ir para tela

                    }

                }

                catch(NumberFormatException ex)

                {

                    // se falhar não faz nada

                }

            }

        }

    }

}