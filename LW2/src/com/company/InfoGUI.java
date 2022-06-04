package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class InfoGUI {

    /* GUI
     *
     *  This method for the 1st Task.
     * It creates a GUI for the 1st Task
     *
     * To see how it works just run the main()
     *
     * Hotkeys:
     * When the input field is active you can press 'Enter' instead of "Аналізувати"-button.
     * Also you can press 'Esc' to exit instead of "Вихід"-button
     * or clicking on the cross in the corner of the frame.
     * */

    public static void main(String[] args) {
        var ref = new Object() {
            String cur_content = "";
        };

        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Аналізатор класів");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            JPanel buttons = new JPanel();
            JPanel insert_panel = new JPanel();
            JPanel main_panel=new JPanel();
            JTextPane txt_area=new JTextPane();
            JScrollPane scroll=new JScrollPane(txt_area, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setWheelScrollingEnabled(true);
            txt_area.setEnabled(false);
            txt_area.setDisabledTextColor(Color.black);
            txt_area.setPreferredSize(new Dimension(600,340));
            JTextField insert = new JTextField(30);
            JLabel msg=new JLabel("Введіть повне ім'я типу: ");
            msg.setFont(new Font("Times New Roman", Font.BOLD, 14));
            JButton clear_btn=new JButton("Очистити");
            JButton analyze_btn=new JButton("Аналізувати");
            JButton exit_btn=new JButton("Вихід");
            clear_btn.setBackground(new Color(240, 230, 140));
            analyze_btn.setBackground(new Color(144, 238, 144));
            exit_btn.setBackground(new Color(255, 160, 122));

            insert.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);
                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        ref.cur_content +=Main.getInfo(insert.getText())+"\n\n";
                        txt_area.setText(ref.cur_content);
                    }
                    if(e.getKeyCode()==KeyEvent.VK_ESCAPE)System.exit(0);
                }
            });
            analyze_btn.addActionListener(event->{
                ref.cur_content +=Main.getInfo(insert.getText())+"\n\n";
                txt_area.setText(ref.cur_content);
            });
            clear_btn.addActionListener(event->{
                txt_area.setText("");
                ref.cur_content="";
            });
            exit_btn.addActionListener(event->System.exit(0));

            insert_panel.add(msg);
            insert_panel.add(insert);
            buttons.add(clear_btn);
            buttons.add(analyze_btn);
            buttons.add(exit_btn);
            main_panel.add(scroll);
            frame.add(main_panel, BorderLayout.CENTER);
            frame.add(insert_panel, BorderLayout.NORTH);
            frame.add(buttons, BorderLayout.SOUTH);
            frame.setSize(720, 480);
            String plaf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
            try {
                UIManager.setLookAndFeel(plaf);
                SwingUtilities.updateComponentTreeUI(frame);
            } catch (Exception e) { e.printStackTrace (); }
            frame.setVisible(true);
        });
    }
}
