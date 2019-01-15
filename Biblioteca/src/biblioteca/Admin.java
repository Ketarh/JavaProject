/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;


import static biblioteca.Cauta.cautat;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
    
/**
 *
 * @author avent
 */
public class Admin extends javax.swing.JFrame {

    public static int count = 0;
    public static int countRow;
    public static int zile = 0;
    public static char cr;
    public static Object[] row = new Object [7];
    public static ArrayList<Carte> carti = new ArrayList<>();
    public static ArrayList<Time> dates = new ArrayList<>();
    public static ArrayList<Carte> imprumutat = new ArrayList<>();
    public static ArrayList<Object[]> rows = new ArrayList<>();
    public static File file = new File("BOOKS.txt");
    public static File fileTable = new File("Table.txt");
    public static File fileTime = new File("Dates.txt");
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static LocalDateTime now = LocalDateTime.now();   
          
    /**
     * Creates new form Admin
     */
    
    public static DefaultListModel dm = new DefaultListModel();
    public static DefaultTableModel table = new DefaultTableModel(){
        
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
        
        
    };
      
    
    public Admin() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
                
        countRow=0;
        
        if(dm.isEmpty() == true){
                   updateBT.setEnabled(false);
                   delBT.setEnabled(false);
               }
        
        cartiJLIST.setModel(dm);
        table.addColumn("Titlu");
        table.addColumn("Autor");
        table.addColumn("Editura");
        table.addColumn("An Publicatie");
        table.addColumn("Data Imprumutului");
        table.addColumn("Data Returnarii");
        table.addColumn("Pret Total");
        cartiTB.setModel(table);
        
        try {
            LoadFile();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            LoadTable();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       
    void numberOnly(KeyEvent evt){
        
        cr = evt.getKeyChar();
        if(Character.isDigit(cr) != true){
            evt.consume();
        }
        
    }
    
    void deselect(){
        cartiJLIST.clearSelection();
        addBT.setEnabled(true);
        updateBT.setEnabled(false);
        delBT.setEnabled(false);
    }
    
    void clear(){
        titluTF.setText("");
        autorTF.setText("");
        edituraTF.setText("");
        rezumatTF.setText("");
        pretTF.setText("");
        cantTF.setText("");
        anTF.setText("");
    }
    
    void clear2(){
        titluLB.setText("");
        autorLB.setText("");
        edituraLB.setText("");
        rezumatLB.setText("");
        pretLB.setText("");
        cantLB.setText("");
        anLB.setText("");
    }
       
    void selectup2(int i){
        titluTF.setText(carti.get(i).getTitlu());
        autorTF.setText(carti.get(i).getAutor());
        edituraTF.setText(carti.get(i).getEditura());
        rezumatTF.setText(carti.get(i).getRezumat());
        anTF.setText(Integer.toString(carti.get(i).getAn()));
        pretTF.setText(Integer.toString(carti.get(i).getPret()));
        cantTF.setText(Integer.toString(carti.get(i).getCantitate()));
        
        addBT.setEnabled(false);
        updateBT.setEnabled(true);
        delBT.setEnabled(true);
        
        titluLB.setText(carti.get(i).getTitlu());
        autorLB.setText(carti.get(i).getAutor());
        edituraLB.setText(carti.get(i).getEditura());
        rezumatLB.setText(carti.get(i).getRezumat());
        anLB.setText(Integer.toString(carti.get(i).getAn()));
        pretLB.setText(Integer.toString(carti.get(i).getPret()));
        cantLB.setText(Integer.toString(carti.get(i).getCantitate()));
        
        if(carti.get(i).getCantitate() == 0){
            imprumutBT.setEnabled(false);
        }else imprumutBT.setEnabled(true);
        
    }    
    
    void update2(int i){
        carti.get(i).setTitlu(titluTF.getText());
        carti.get(i).setAutor(autorTF.getText());
        carti.get(i).setEditura(edituraTF.getText());
        carti.get(i).setRezumat(rezumatTF.getText());
        carti.get(i).setAn(Integer.parseInt(anTF.getText()));
        carti.get(i).setCant(Integer.parseInt(cantTF.getText()));
        carti.get(i).setPret(Integer.parseInt(pretTF.getText()));
    }      
                   
    static void SaveFile() throws FileNotFoundException, IOException{
        
        FileOutputStream FO = new FileOutputStream(file);
        ObjectOutputStream OUTPUT = new ObjectOutputStream(FO);
        
        OUTPUT.reset();
        for(Carte ccc: carti){
            OUTPUT.writeObject(ccc);
        }
        
        FO.close();
        OUTPUT.close();
    }
    
    static void SaveTable() throws FileNotFoundException, IOException{
        
        FileOutputStream FOT = new FileOutputStream(fileTable);
        ObjectOutputStream outTable = new ObjectOutputStream(FOT);
        FileOutputStream FOSTime = new FileOutputStream(fileTime);
        ObjectOutputStream outTime = new ObjectOutputStream(FOSTime);
                
        outTable.reset();
        outTime.reset();
        for(Carte r: imprumutat){
            outTable.writeObject(r);
        }
        for(Time t: dates){
            outTime.writeObject(t);
        }
        
        FOT.close();
        outTable.close();
        FOSTime.close();
        outTime.close();
    }   
    
    static void LoadFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        FileInputStream fileInput = new FileInputStream(file);
        ObjectInputStream INPUT = new ObjectInputStream(fileInput);
        
        try {
            while(true){
                Carte c = (Carte)INPUT.readObject();
                carti.add(c);
                
                dm.addElement(carti.get(count).getTitlu());
                count++;
            }
        }catch(EOFException ex){
            
        }
    }
    
    static void LoadTable() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        FileInputStream fileInputTable = new FileInputStream(fileTable);
        ObjectInputStream inputTable = new ObjectInputStream(fileInputTable);
        FileInputStream fileInputTime = new FileInputStream(fileTime);
        ObjectInputStream inputTime = new ObjectInputStream(fileInputTime);
        
        try {
            while(true){
                Carte r = (Carte)inputTable.readObject();
                Time t = (Time)inputTime.readObject();
                
                imprumutat.add(r);
                dates.add(t);
                
                row[0]=imprumutat.get(countRow).getTitlu();
                row[1]=imprumutat.get(countRow).getAutor();
                row[2]=imprumutat.get(countRow).getEditura();
                row[3]=imprumutat.get(countRow).getAn();
                row[4]=dtf.format(dates.get(countRow).getFirstDay());
                row[5]=dtf.format(dates.get(countRow).getReturnDay());
                row[6]=dates.get(countRow).getTotalPrice();
                rows.add(row);
                table.addRow((Object[])rows.get(countRow));
                countRow++;
            }
        }catch(EOFException ex){
            
        }
    }
      
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Panou = new javax.swing.JTabbedPane();
        CartiDisponibile = new javax.swing.JSplitPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        cartiJLIST = new javax.swing.JList<>();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        AdaugaCarti = new javax.swing.JPanel();
        addBT = new javax.swing.JButton();
        updateBT = new javax.swing.JButton();
        delBT = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        titluTF = new javax.swing.JTextField();
        autorTF = new javax.swing.JTextField();
        edituraTF = new javax.swing.JTextField();
        anTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pretTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cantTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        rezumatTF = new javax.swing.JTextArea();
        InformatiiCarti = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        autorLB = new javax.swing.JLabel();
        titluLB = new javax.swing.JLabel();
        edituraLB = new javax.swing.JLabel();
        anLB = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        rezumatLB = new javax.swing.JTextPane();
        jLabel21 = new javax.swing.JLabel();
        cantLB = new javax.swing.JLabel();
        pretLB = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        imprumutBT = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        zileTF = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        returnBT = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        cautaTF = new javax.swing.JTextField();
        cautaBT = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartiTB = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalii Carte"));

        jLabel2.setText("Titlu:");

        jLabel3.setText("Rezumat:");

        jLabel4.setText("Anul Publicatiei:");

        jLabel5.setText("Autor:");

        jLabel6.setText("Editura:");

        jLabel7.setText("Disponibilitate:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Adiministrator Biblioteca");

        jLabel1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Admin Biblioteca");

        Panou.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CartiDisponibile.setDividerLocation(200);
        CartiDisponibile.setDividerSize(20);

        cartiJLIST.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartiJLISTMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(cartiJLIST);

        CartiDisponibile.setLeftComponent(jScrollPane6);

        AdaugaCarti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdaugaCartiMouseClicked(evt);
            }
        });

        addBT.setText("Add");
        addBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBTActionPerformed(evt);
            }
        });

        updateBT.setText("Update");
        updateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBTActionPerformed(evt);
            }
        });

        delBT.setText("Delete");
        delBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBTActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Titlu:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Autor:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Editura:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("An Publicatie:");

        anTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anTFActionPerformed(evt);
            }
        });
        anTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                anTFKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Scurt Rezumat");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Pret");

        pretTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pretTFKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Cantitate");

        cantTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantTFKeyTyped(evt);
            }
        });

        rezumatTF.setColumns(20);
        rezumatTF.setRows(5);
        jScrollPane1.setViewportView(rezumatTF);

        javax.swing.GroupLayout AdaugaCartiLayout = new javax.swing.GroupLayout(AdaugaCarti);
        AdaugaCarti.setLayout(AdaugaCartiLayout);
        AdaugaCartiLayout.setHorizontalGroup(
            AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdaugaCartiLayout.createSequentialGroup()
                .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdaugaCartiLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(addBT, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateBT, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170)
                        .addComponent(delBT, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AdaugaCartiLayout.createSequentialGroup()
                        .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AdaugaCartiLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdaugaCartiLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(70, 70, 70)
                        .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(titluTF)
                            .addComponent(autorTF)
                            .addComponent(anTF, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(edituraTF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                        .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdaugaCartiLayout.createSequentialGroup()
                                .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pretTF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AdaugaCartiLayout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(cantTF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdaugaCartiLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdaugaCartiLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(80, 80, 80))
        );
        AdaugaCartiLayout.setVerticalGroup(
            AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdaugaCartiLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(titluTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AdaugaCartiLayout.createSequentialGroup()
                        .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(autorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)))
                .addGap(18, 18, 18)
                .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edituraTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdaugaCartiLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pretTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AdaugaCartiLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(anTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AdaugaCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delBT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jTabbedPane2.addTab("Adauga Carti", AdaugaCarti);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("Titlu:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("Autor:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("Editura:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("An Publicatie:");

        autorLB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        autorLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        titluLB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        titluLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        edituraLB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edituraLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        anLB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        anLB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Pret:");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Scurt Rezumat");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        rezumatLB.setEditable(false);
        rezumatLB.setPreferredSize(new java.awt.Dimension(164, 94));
        jScrollPane3.setViewportView(rezumatLB);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Cantitate:");
        jLabel21.setFocusable(false);
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cantLB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        pretLB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Lei pe zi");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("buc.");

        imprumutBT.setText("Imprumuta");
        imprumutBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imprumutBTMouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("pentru");

        zileTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        zileTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        zileTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zileTFKeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Zile");

        returnBT.setText("Returneaza");
        returnBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnBTMouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Cauta Carte:");

        cautaBT.setText("Cauta");
        cautaBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cautaBTMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout InformatiiCartiLayout = new javax.swing.GroupLayout(InformatiiCarti);
        InformatiiCarti.setLayout(InformatiiCartiLayout);
        InformatiiCartiLayout.setHorizontalGroup(
            InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformatiiCartiLayout.createSequentialGroup()
                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InformatiiCartiLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17))))
                    .addGroup(InformatiiCartiLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel18))
                    .addGroup(InformatiiCartiLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(returnBT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imprumutBT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InformatiiCartiLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(titluLB, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146))
                    .addGroup(InformatiiCartiLayout.createSequentialGroup()
                        .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(InformatiiCartiLayout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(zileTF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InformatiiCartiLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(autorLB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(anLB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edituraLB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(InformatiiCartiLayout.createSequentialGroup()
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(pretLB, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel24))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InformatiiCartiLayout.createSequentialGroup()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(cantLB, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(InformatiiCartiLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cautaBT, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                    .addComponent(cautaTF))))
                        .addGap(80, 80, 80))))
        );
        InformatiiCartiLayout.setVerticalGroup(
            InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformatiiCartiLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titluLB, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InformatiiCartiLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(autorLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(InformatiiCartiLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edituraLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pretLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cantLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anLB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imprumutBT)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zileTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cautaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(InformatiiCartiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnBT)
                    .addComponent(cautaBT))
                .addGap(67, 67, 67))
        );

        jTabbedPane2.addTab("Informatii Carti", InformatiiCarti);

        CartiDisponibile.setRightComponent(jTabbedPane2);

        Panou.addTab("Carti Disponibile", CartiDisponibile);

        cartiTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        cartiTB.setColumnSelectionAllowed(true);
        cartiTB.getTableHeader().setReorderingAllowed(false);
        cartiTB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartiTBMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(cartiTB);
        cartiTB.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        Panou.addTab("Carti Imprumutate", jScrollPane2);

        jLabel26.setIcon(new javax.swing.ImageIcon("C:\\Users\\avent\\OneDrive\\Documents\\NetBeansProjects\\Biblioteca\\library.jpg")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Panou, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(409, 409, 409))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Panou, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 761, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

         
    
    private void addBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBTActionPerformed
        // TODO add your handling code here:  
        cartiJLIST.setModel(dm);                    
        dm.addElement(titluTF.getText());
        
        JOptionPane.showMessageDialog(rootPane, "A fost adaugata cartea: " +titluTF.getText());
        
        carti.add(new Carte(titluTF.getText(), autorTF.getText(), edituraTF.getText(), rezumatTF.getText(),
                Integer.parseInt(pretTF.getText()), Integer.parseInt(cantTF.getText()), Integer.parseInt(anTF.getText())));
        
        try {
            SaveFile();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        carti.get(count).Afisare();
        count++;
        clear();
    }//GEN-LAST:event_addBTActionPerformed

    private void cartiJLISTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartiJLISTMouseClicked
        // TODO add your handling code here:
        int index = cartiJLIST.getSelectedIndex(); 
        
        selectup2(index);

        System.out.println("Index: " + index);
        carti.get(index).Afisare();
    }//GEN-LAST:event_cartiJLISTMouseClicked

    private void updateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBTActionPerformed
        // TODO add your handling code here:
        int index = cartiJLIST.getSelectedIndex();
        System.out.println(index);               
        
        dm.set(index, titluTF.getText());  
        JOptionPane.showMessageDialog(rootPane, "Cartea " +carti.get(index).getTitlu() +" a fost modificata!");
        update2(index);
                        
        try {
            SaveFile();            
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        clear();
        deselect();
        carti.get(index).Afisare();      
    }//GEN-LAST:event_updateBTActionPerformed

    private void delBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBTActionPerformed
        // TODO add your handling code here:
        int i, index = cartiJLIST.getSelectedIndex();
        
        JOptionPane.showMessageDialog(rootPane, "Cartea " +carti.get(index).getTitlu() +" a fost stearsa!");
        
        for(i=0;i<imprumutat.size();i++){
            
            System.out.println("i: " +i);
            if(imprumutat.get(i).getTitlu().equals(carti.get(index).getTitlu())){                
                table.removeRow(i);
                rows.remove(i);
                imprumutat.remove(i);
                dates.remove(i);
                countRow--;                                                                                               
            }
        }
        
        carti.remove(index);
        dm.remove(index);
        
        
        count--;
        
        
        
        try {
            SaveFile();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        clear();
        clear2();
        deselect();             
    }//GEN-LAST:event_delBTActionPerformed

    private void AdaugaCartiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdaugaCartiMouseClicked
        // TODO add your handling code here:
        deselect();
        clear();
    }//GEN-LAST:event_AdaugaCartiMouseClicked

    private void imprumutBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imprumutBTMouseClicked
        // TODO add your handling code here:        
        zile = Integer.parseInt(zileTF.getText());
        int index = cartiJLIST.getSelectedIndex();
                
        if(carti.get(index).getCantitate() > 0){
            row[0]=carti.get(index).getTitlu();
            row[1]=carti.get(index).getAutor();
            row[2]=carti.get(index).getEditura();
            row[3]=carti.get(index).getAn();
        
            dates.add(new Time(now, zile, carti.get(index).getPret()));

            row[4]=dtf.format(dates.get(countRow).getFirstDay());
            row[5]=dtf.format(dates.get(countRow).getReturnDay());
            row[6]=dates.get(countRow).getTotalPrice();        
        
            rows.add(row);

            System.out.println(Arrays.toString(rows.get(countRow)));
            table.addRow((Object[])rows.get(countRow));
            countRow++;

            carti.get(index).setCant(carti.get(index).getCantitate()-1);
            cantLB.setText(Integer.toString(carti.get(index).getCantitate()));
            imprumutat.add(carti.get(index));
            zileTF.setText("");

            JOptionPane.showMessageDialog(rootPane, "Cartea " +carti.get(index).getTitlu() +" a fost imprumutata pentru " +Integer.toString(zile) +" zile!");
        }else {
            JOptionPane.showMessageDialog(rootPane, "Stoc epuizat!");
            zileTF.setText("");
        }
            
            
            
        try {
            SaveFile();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            SaveTable();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_imprumutBTMouseClicked

    private void returnBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnBTMouseClicked
        // TODO add your handling code here:        
        int index = cartiJLIST.getSelectedIndex();
        int i, ok=0;
        
        System.out.println("countRow" +countRow);
        for(i=0;i<imprumutat.size();i++){
            
            System.out.println("i: " +i);
            if(imprumutat.get(i).getTitlu().equals(carti.get(index).getTitlu())){                
                table.removeRow(i);
                rows.remove(i);
                imprumutat.remove(i);
                dates.remove(i);
                countRow--;
                
                carti.get(index).setCant(carti.get(index).getCantitate()+1);
                cantLB.setText(Integer.toString(carti.get(index).getCantitate()));
                System.out.println("i: " +i);
                i=imprumutat.size();
                ok=1;
                JOptionPane.showMessageDialog(rootPane, "Cartea " +carti.get(index).getTitlu() +" a fost returnata!");
            }
            
            
        }
        if(ok == 0){
            JOptionPane.showMessageDialog(rootPane, "Nu este nicio carte '" +carti.get(index).getTitlu() +"' imprumutata!");
        }
        
        System.out.println("i: " +i);
        System.out.println("countRow: "+countRow);
        
        try {
            SaveFile();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            SaveTable();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_returnBTMouseClicked

    private void anTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anTFActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_anTFActionPerformed

    private void anTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anTFKeyTyped
        // TODO add your handling code here:        
        numberOnly(evt);
    }//GEN-LAST:event_anTFKeyTyped

    private void pretTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pretTFKeyTyped
        // TODO add your handling code here: 
        numberOnly(evt);
    }//GEN-LAST:event_pretTFKeyTyped

    private void cantTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantTFKeyTyped
        // TODO add your handling code here:
        numberOnly(evt);
    }//GEN-LAST:event_cantTFKeyTyped

    private void cartiTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartiTBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cartiTBMouseClicked

    private void cautaBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cautaBTMouseClicked
        // TODO add your handling code here:
        int elem=0, i=0, one=0;
        deselect();
        cautat.removeAll(carti);
               
        for(Carte c: carti){
            
            if(cautaTF.getText().equals(c.getTitlu())){
                cartiJLIST.setSelectedIndex(i);
                JOptionPane.showMessageDialog(rootPane, "Cartea " +c.getTitlu() +" a fost gasita!");
                selectup2(i);
                elem = -1;
            }
            
            if(cautaTF.getText().equals(c.getAutor()) || cautaTF.getText().equals(c.getEditura()) || cautaTF.getText().equals(Integer.toString(c.getAn()))){
                cautat.add(c);
                elem++; 
                one=i;
            } 
            i++;
        }
        
        if(elem > 0){
            if(elem == 1){
                JOptionPane.showMessageDialog(rootPane, "A fost gasita o carte!");
                cartiJLIST.setSelectedIndex(one);
                selectup2(one);
                
            }else{
                JOptionPane.showMessageDialog(rootPane, "Au fost gasite " +Integer.toString(elem) +" carti!");
                new Cauta().setVisible(true);
                clear();
                clear2();
            }
        }else if(elem == 0){
            JOptionPane.showMessageDialog(rootPane, "Nu a fost gasita nicio carte!");
            clear();
            clear2();
        }
        
        cautaTF.setText("");       
    }//GEN-LAST:event_cautaBTMouseClicked

    private void zileTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zileTFKeyTyped
        // TODO add your handling code here:
        numberOnly(evt);
    }//GEN-LAST:event_zileTFKeyTyped

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
         
//         LoadFile();
         
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                    new Admin().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdaugaCarti;
    private javax.swing.JSplitPane CartiDisponibile;
    private javax.swing.JPanel InformatiiCarti;
    private javax.swing.JTabbedPane Panou;
    private javax.swing.JButton addBT;
    private javax.swing.JLabel anLB;
    private javax.swing.JTextField anTF;
    private javax.swing.JLabel autorLB;
    private javax.swing.JTextField autorTF;
    private javax.swing.JLabel cantLB;
    private javax.swing.JTextField cantTF;
    private javax.swing.JList<String> cartiJLIST;
    private javax.swing.JTable cartiTB;
    private javax.swing.JButton cautaBT;
    private javax.swing.JTextField cautaTF;
    private javax.swing.JButton delBT;
    private javax.swing.JLabel edituraLB;
    private javax.swing.JTextField edituraTF;
    private javax.swing.JButton imprumutBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel pretLB;
    private javax.swing.JTextField pretTF;
    private javax.swing.JButton returnBT;
    private javax.swing.JTextPane rezumatLB;
    private javax.swing.JTextArea rezumatTF;
    private javax.swing.JLabel titluLB;
    private javax.swing.JTextField titluTF;
    private javax.swing.JButton updateBT;
    private javax.swing.JTextField zileTF;
    // End of variables declaration//GEN-END:variables
}
