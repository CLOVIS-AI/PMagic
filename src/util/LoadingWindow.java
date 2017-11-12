/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author CLOVIS
 */
public class LoadingWindow extends JFrame {
boolean isMouseOver = false;
int count = 0;
JProgressBar progressBar;

    public LoadingWindow(String name)
    {
        this.setTitle(name);
        this.setBounds(100, 100, 407, 119);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        progressBar = new JProgressBar();
        progressBar.setBounds(10, 45, 371, 22);
        this.getContentPane().add(progressBar);

//        JButton btnMousOver = new JButton("Mouse Over");
//        btnMousOver.addMouseListener(new MouseAdapter()
//        {
//            public void mouseEntered(MouseEvent evt)
//            {
//                isMouseOver = true;
//                btnMousOver.setEnabled(false);
//                Thread go = new Thread()
//                {
//                    public void run()
//                    {
//                        while (isMouseOver && count < 101)
//                        {
//                            count = count + 2;
//                            progressBar.setValue(count);
//                            // do some stuffs here
//                            try
//                            {
//                                Thread.sleep(100);
//                            }
//                            catch (InterruptedException e)
//                            {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                };
//                go.start();
//
//            }
//
//            public void mouseExited(MouseEvent evt)
//            {
//                isMouseOver = false;
//                btnMousOver.setEnabled(true);
//            }
//
//            public void mousePressed(MouseEvent evt)
//            {
//                JOptionPane.showMessageDialog(null, "Mouse CLicked");
//            }
//
//            public void mouseReleased(MouseEvent evt)
//            {
//                JOptionPane.showMessageDialog(null, "Mouse released");
//            }
//        });
//        btnMousOver.setBounds(142, 11, 108, 23);
//
//        this.getContentPane().add(btnMousOver);
        this.getContentPane().add(progressBar);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    
    public void progress(int count){
        progressBar.setValue(count);
    }
}