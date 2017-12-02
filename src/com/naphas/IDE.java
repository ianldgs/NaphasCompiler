package com.naphas; /**
 * FACULDADE COTEMIG
 * TRABALHO PR�TICO - COMPILADORES
 * com.naphas.IDE PARA COMPILADOR
 * REVIS�O: 2017.2.1
 * AUTOR: prof. VIRGILIO BORGES DE OLIVEIRA.
 * DATA DA �LTIMA ALTERA��O: 09/10/2017
 **/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

public class IDE extends JFrame implements ActionListener {
    JTextPane editor = new JTextPane();
    JScrollPane p1 = new JScrollPane(editor);

    JTextPane msg = new JTextPane();
    JScrollPane p2 = new JScrollPane(msg);

    JMenuBar mnBar = new JMenuBar();

    JMenu mnArquivo = new JMenu("Arquivo");
    JMenuItem mnNovo = new JMenuItem("Novo", KeyEvent.VK_N);
    JMenuItem mnAbrir = new JMenuItem("Abrir", KeyEvent.VK_A);
    JMenuItem mnSair = new JMenuItem("Sair", KeyEvent.VK_R);

    JMenu mnProjeto = new JMenu("Projeto");
    JMenuItem mnCompilar = new JMenuItem("Compilar", KeyEvent.VK_C);

    JMenu mnAjuda = new JMenu("Ajuda");
    JMenuItem mnSobre = new JMenuItem("Sobre...", KeyEvent.VK_S);

    public IDE() {
        super("Compiladores - com.naphas.IDE vers�o 2017.2.1");
        setLayout(null);

        mnBar.add(mnArquivo);

        mnArquivo.add(mnNovo);
        mnNovo.addActionListener(this);

        mnArquivo.add(mnAbrir);
        mnAbrir.addActionListener(this);

        mnArquivo.addSeparator();

        mnArquivo.add(mnSair);
        mnSair.addActionListener(this);

        mnBar.add(mnProjeto);

        mnProjeto.add(mnCompilar);
        mnCompilar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        mnCompilar.addActionListener(this);

        mnBar.add(mnAjuda);

        mnAjuda.add(mnSobre);

        mnSobre.addActionListener(this);

        setJMenuBar(mnBar);

        setSize(700, 550);
        msg.setEditable(false);
        getContentPane().add(p1);
        getContentPane().add(p2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        int larg = this.getContentPane().getWidth();
        int alt = this.getContentPane().getHeight();
        editor.setFont(new Font("Monospaced", Font.PLAIN, 13));
        msg.setFont(new Font("Monospaced", Font.PLAIN, 13));
        msg.setBackground(Color.LIGHT_GRAY);
//        msg.setText("Erros:");
        p1.setBounds(1, 0, larg - 1, alt - 100);
        p2.setBounds(1, alt - 100, larg - 1, 100);
    }

    public static void main(String[] args) {
        new IDE();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mnNovo) {
            editor.setText("");
        } else if (e.getSource() == mnAbrir) {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    editor.setContentType("text/plain");
                    editor.read(new BufferedReader(new FileReader(fc.getSelectedFile().getAbsoluteFile())), "");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao abrir arquivo.\n" + ex);
                }
            }
        } else if (e.getSource() == mnCompilar) {
            msg.setText("");

    		Tokenizer tokenizer = new Tokenizer(editor.getText());

    		while (true) {
    		    try {
                    Token token = tokenizer.getNextToken();

                    if (token == null) {
                        break;
                    }

                    msg.setText(msg.getText() + "\n" + token.toString());
                } catch (LexicalException ex) {
    		        msg.setText(msg.getText() + "\n" + ex.getMessage() + "\n");
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == mnSair) {
            System.exit(0);
        } else if (e.getSource() == mnSobre) {
            JOptionPane.showMessageDialog(this, "Trabalho de Compiladores\ncom.naphas.IDE vers�o 2017.2.1\n\nDesenvolvido por: prof. Virgilio Borges de Oliveira\nSomente para fins did�ticos.");
        }

    }
}
