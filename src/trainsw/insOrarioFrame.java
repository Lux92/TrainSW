/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainsw;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luciano Limina
 */
public class insOrarioFrame extends javax.swing.JFrame {

    private TrainSW trainSW = TrainSW.getIstanza();
    List<Treno> listTr = new ArrayList<Treno>();
    List<FermataOrario> listFerm = new ArrayList<FermataOrario>();
    /**
     * Creates new form insOrarioFrame
     */
    public insOrarioFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_Data = new javax.swing.JTextField();
        next_orarioTrButton = new javax.swing.JButton();
        annulla_orarioButton = new javax.swing.JButton();
        txt_Orario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Inserisci Data e Orario");

        next_orarioTrButton.setText("Avanti");
        next_orarioTrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_orarioTrButtonActionPerformed(evt);
            }
        });

        annulla_orarioButton.setText("Annulla");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Orario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Data, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(next_orarioTrButton)
                        .addGap(29, 29, 29)
                        .addComponent(annulla_orarioButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_Orario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next_orarioTrButton)
                    .addComponent(annulla_orarioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void next_orarioTrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_orarioTrButtonActionPerformed
     
        
String str = txt_Orario.getText();
 DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
        try {
            Date ora = formatter.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(insOrarioFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
Date anno = null;
        try {
            anno = format.parse(txt_Data.getText());
        } catch (ParseException ex) {
            Logger.getLogger(insOrarioFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        

         this.dispose();
                
                //Caricare prima questa lista, importante : listSt e JTable devono essere sincronizzati
                // stessi indici-->  -riga = index list 
                
                   final List<Treno> listTr = trainSW.InserisciOrario(anno);
                
                  // listTr.add();
               
                
                String[] columnNames = {"ID TRENO"
                       };
                
                final JTable stTab = new JTable();
                final DefaultTableModel dtm = new DefaultTableModel(0,0);
                
                dtm.setColumnIdentifiers(columnNames);
                stTab.setModel(dtm);
                
               int index =0 , i =0;
               index = listTr.size();
                
                while(i<=index){
                    Treno tr = listTr.get(i);
                dtm.addRow(new Object[] {
                 //Aggiungi treni qui 
                    tr.getId_tr()
                });
                i++;
                
                }
           final JFrame tabfram = new JFrame("Scegli un treno");
           tabfram.setSize(200, 400);
      
                                      
          stTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          stTab.setSize(200, 500);

JButton nextButton = new JButton("Avanti");
JButton endButton = new JButton("Fine");
JPanel panst = new JPanel(new FlowLayout());
panst.add(stTab);
panst.add(nextButton);
panst.add(endButton);
tabfram.add(panst);
tabfram.setVisible(true);

    
nextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                     
                       tabfram.dispose();
                           
                       // Inserire treno nella tratta
                       Treno tr;
                           tr = listTr.get((int) stTab.getSelectedRow());
                           
                       
                        
                           
                           
                           
                           /* VIsualizzare Fermate */
                           
                            
                
                //Caricare prima questa lista, importante : listSt e JTable devono essere sincronizzati
                // stessi indici-->  -riga = index list 
                
                  listFerm = trainSW.InserisciTreno(tr);
                   
               
                   
                
                String[] columnNames = {"ID FERMATA",
                        "Orario",
                        "DISTANZA"
                       
                       };
                
                final JTable stTab1 = new JTable();
                final DefaultTableModel dtm1 = new DefaultTableModel(0,0);
                
                dtm1.setColumnIdentifiers(columnNames);
                stTab1.setModel(dtm);
                
                int index,i=0;
                index = listFerm.size();
                while(i<=index){
               FermataOrario fermOr = listFerm.get(i);
                dtm1.addRow(new Object[] {
                  //Aggiungi fermate alla lista
                    fermOr.getId(),fermOr.getOrario().toString(),fermOr.getDistanzaParziale()
                });
                i++;
                }
                
           final JFrame tabfram1 = new JFrame("Visualizza Fermate della Tratta");
           tabfram1.setSize(500, 400);
      
                                      
      
          stTab1.setSize(200, 500);


JButton endButton1 = new JButton("Fine");
JPanel panst1 = new JPanel(new FlowLayout());
panst1.add(stTab);

panst1.add(endButton1);
tabfram1.add(panst1);
tabfram1.setVisible(true);


             endButton1.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           tabfram1.dispose();
                           trainSW.ConfermaTratta();
                           throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                       }
                   });
           
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                           
                           
                           
                       
                           
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 


            
           
            
   
        
        
    }//GEN-LAST:event_next_orarioTrButtonActionPerformed


/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(insOrarioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(insOrarioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(insOrarioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(insOrarioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new insOrarioFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annulla_orarioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton next_orarioTrButton;
    private javax.swing.JTextField txt_Data;
    private javax.swing.JTextField txt_Orario;
    // End of variables declaration//GEN-END:variables
}
