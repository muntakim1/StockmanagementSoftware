/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagement;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import net.proteanit.sql.DbUtils;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author munta
 */
public final class Ui extends javax.swing.JFrame {

    /**
     * Creates new form Ui
     */
    Connection conn=null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    PreparedStatement pst4 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    ResultSet rs4 = null;    
    DefaultTableModel model = new DefaultTableModel();
    public Ui() {
        
        initComponents();
        conn=ConnectionManager.Connect();
        updateTable();
        StatusUpdater();
        StockTableUpdate();
        String sql = "SELECT Product_ID FROM ProductMaster";
        try {
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            input_product_id.addItem(rs.getString(1));
        }
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    void StatusUpdater(){
        String sql = "SELECT SUM(Weight) as total FROM Transactions WHERE Type=\"Purchase\";";
        String sql2 = "SELECT SUM(Weight) as total FROM Transactions WHERE Type=\"Sales\";";
        String sql3 = "SELECT SUM(Weight) as total FROM Transactions WHERE Type=\"Order\";";
        String sql4 = "SELECT SUM(Weight) as total FROM Transactions WHERE Type=\"Artisan\";";
        String sql5 = "SELECT SUM(Weight) as total FROM Transactions;";
        try {
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            pst1 = conn.prepareStatement(sql2);
            pst2 = conn.prepareStatement(sql3);
            pst3 = conn.prepareStatement(sql4);
            pst4 = conn.prepareStatement(sql5);
            
            rs = pst.executeQuery();
            rs1=pst1.executeQuery();
            rs2=pst2.executeQuery();
            rs3=pst3.executeQuery();
            rs4=pst4.executeQuery();
            Purchase_count.setText(rs.getString("total"));
            Sale_count.setText(rs1.getString("total"));
            Order_count.setText(rs2.getString("total"));
            Artisan_count.setText(rs3.getString("total"));
            Total_weight_count.setText(rs4.getString("total"));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void updateTable(){
        String sql = "SELECT * FROM Transactions";
        try {
            conn=ConnectionManager.Connect();

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.print(rs);
            AllTransactionsTable.setModel(DbUtils.resultSetToTableModel(rs));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    void StockTableUpdate(String Values){
        String sql ="SELECT Product_ID, \n" +
        "CASE Type \n" +
        "WHEN \"Sales\" \n" +
        "THEN Available_stock-Quantity \n" +
        "ELSE \n" +
        "Available_stock\n" +
        "END Available_stock FROM (SELECT Product_ID,Type,Quantity, sum(Quantity) as Available_stock FROM Transactions GROUP By Product_ID) GROUP By Product_ID;"; 
        try {
            conn=ConnectionManager.Connect();

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.print(rs);
            AllStockAvailableTable.setModel(DbUtils.resultSetToTableModel(rs));
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
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

        FilterButtons = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Transactions = new javax.swing.JButton();
        About = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Purchase_Weights = new javax.swing.JPanel();
        Purchase_count = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Sale_count = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Order_count = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        Artisan_count = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        GetTotalWeight = new javax.swing.JPanel();
        Total_weight_count = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AllStockAvailableTable = new javax.swing.JTable();
        SearchID = new javax.swing.JTextField();
        RefreshBtn = new javax.swing.JButton();
        ExportToExcelStock = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        input_quantity = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        Add = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        input_type = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        input_crt = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        input_weight = new javax.swing.JTextField();
        input_date = new com.toedter.calendar.JDateChooser();
        input_product_id = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        ViewAllTransactions = new javax.swing.JRadioButton();
        ViewAlllPurchases = new javax.swing.JRadioButton();
        ViewAllSales = new javax.swing.JRadioButton();
        ViewALLOrder = new javax.swing.JRadioButton();
        ViewAllArtisan = new javax.swing.JRadioButton();
        ExportToExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AllTransactionsTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        RefreshData = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        InsertStopDate = new com.toedter.calendar.JDateChooser();
        InsertStartDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(22, 36, 71));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setPreferredSize(new java.awt.Dimension(1184, 984));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(22, 36, 71));

        jPanel3.setBackground(new java.awt.Color(27, 27, 47));

        Transactions.setBackground(new java.awt.Color(31, 64, 104));
        Transactions.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Transactions.setForeground(new java.awt.Color(255, 255, 255));
        Transactions.setText("Transactions");
        Transactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransactionsActionPerformed(evt);
            }
        });

        About.setBackground(new java.awt.Color(31, 64, 104));
        About.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        About.setForeground(new java.awt.Color(255, 255, 255));
        About.setText("About");
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Weight : ");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Metrics");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 102));
        jLabel6.setText("GRAM");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 102));
        jLabel7.setText("18c, 22c, 24c");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Carat  : ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(About, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Transactions, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(Transactions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(About, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(240, 240, 240)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(440, 440, 440))
        );

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Stock Management System");

        Purchase_Weights.setBackground(new java.awt.Color(204, 204, 255));
        Purchase_Weights.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Purchase_count.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Purchase_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Purchase_count.setText("0");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Purchase");

        javax.swing.GroupLayout Purchase_WeightsLayout = new javax.swing.GroupLayout(Purchase_Weights);
        Purchase_Weights.setLayout(Purchase_WeightsLayout);
        Purchase_WeightsLayout.setHorizontalGroup(
            Purchase_WeightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Purchase_WeightsLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(Purchase_WeightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Purchase_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Purchase_WeightsLayout.setVerticalGroup(
            Purchase_WeightsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Purchase_WeightsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Purchase_count)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Sale_count.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Sale_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sale_count.setText("0");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Sale");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Sale_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sale_count)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Order_count.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Order_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Order_count.setText("0");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Order");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Order_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Order_count)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Artisan_count.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Artisan_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Artisan_count.setText("0");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Artisan");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Artisan_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Artisan_count)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GetTotalWeight.setBackground(new java.awt.Color(204, 51, 255));
        GetTotalWeight.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Total_weight_count.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Total_weight_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Total_weight_count.setText("0");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Total Weight");

        javax.swing.GroupLayout GetTotalWeightLayout = new javax.swing.GroupLayout(GetTotalWeight);
        GetTotalWeight.setLayout(GetTotalWeightLayout);
        GetTotalWeightLayout.setHorizontalGroup(
            GetTotalWeightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GetTotalWeightLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(GetTotalWeightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Total_weight_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        GetTotalWeightLayout.setVerticalGroup(
            GetTotalWeightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GetTotalWeightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Total_weight_count)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(228, 63, 90));

        AllStockAvailableTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Product Name", "Available Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(AllStockAvailableTable);

        SearchID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchIDKeyPressed(evt);
            }
        });

        RefreshBtn.setText("Refresh");
        RefreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshBtnActionPerformed(evt);
            }
        });

        ExportToExcelStock.setText("Extract");
        ExportToExcelStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportToExcelStockActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Search Prodcut");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SearchID, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RefreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(ExportToExcelStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(RefreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExportToExcelStock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(240, 190, 153));

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Product");

        jLabel17.setText("Qty");

        input_quantity.setText("0");
        input_quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                input_quantityKeyPressed(evt);
            }
        });

        jLabel19.setText("Date");

        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        jLabel18.setText("Type");

        input_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Purchase", "Sales", "Order", "Artisan" }));

        jLabel20.setText("CRT");

        input_crt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "22c", "21c", "18c" }));

        jLabel21.setText("Weight");

        input_weight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                input_weightKeyPressed(evt);
            }
        });

        input_date.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(input_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(input_product_id, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(input_quantity)
                    .addComponent(input_crt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(input_weight)
                    .addComponent(input_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Update))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17)
                                .addComponent(input_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)
                                .addComponent(input_product_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(input_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(input_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(input_crt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)
                                .addComponent(input_weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        FilterButtons.add(ViewAllTransactions);
        ViewAllTransactions.setText("Alll");
        ViewAllTransactions.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ViewAllTransactionsItemStateChanged(evt);
            }
        });

        FilterButtons.add(ViewAlllPurchases);
        ViewAlllPurchases.setText("Purchase");
        ViewAlllPurchases.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ViewAlllPurchasesItemStateChanged(evt);
            }
        });

        FilterButtons.add(ViewAllSales);
        ViewAllSales.setText("Sales");
        ViewAllSales.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ViewAllSalesItemStateChanged(evt);
            }
        });

        FilterButtons.add(ViewALLOrder);
        ViewALLOrder.setText("Order");
        ViewALLOrder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ViewALLOrderItemStateChanged(evt);
            }
        });

        FilterButtons.add(ViewAllArtisan);
        ViewAllArtisan.setText("Artisan");
        ViewAllArtisan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ViewAllArtisanItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(ViewAllTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ViewAlllPurchases)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ViewAllSales)
                .addGap(2, 2, 2)
                .addComponent(ViewALLOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
                .addComponent(ViewAllArtisan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewAllTransactions)
                    .addComponent(ViewAlllPurchases)
                    .addComponent(ViewAllSales)
                    .addComponent(ViewALLOrder)
                    .addComponent(ViewAllArtisan))
                .addContainerGap())
        );

        ExportToExcel.setText("Export To Excel");
        ExportToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportToExcelActionPerformed(evt);
            }
        });

        AllTransactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Product", "Quantitiy", "Date", "Type", "CRT", "Weight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AllTransactionsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AllTransactionsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(AllTransactionsTable);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExportToExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExportToExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(31, 64, 104));

        RefreshData.setText("Refresh");
        RefreshData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshDataActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Start Date");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("End Date");

        InsertStopDate.setDateFormatString("yyyy-MM-dd");

        InsertStartDate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(InsertStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(InsertStopDate, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(RefreshData, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(RefreshData, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(InsertStopDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InsertStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date Range");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Available Stock");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("All Transactions");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(410, 410, 410))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Purchase_Weights, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GetTotalWeight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Purchase_Weights, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GetTotalWeight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransactionsActionPerformed
       Transactions transaction = new Transactions();
        transaction.setVisible(true); 
        transaction.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        
    }//GEN-LAST:event_TransactionsActionPerformed

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        AboutPage app = new AboutPage();
        app.setVisible(true);
        app.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        // TODO add your handling code here:
    }//GEN-LAST:event_AboutActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = (Date) input_date.getDate();
            String input_date =sdf.format(date1) ;
            String sql = "INSERT INTO Transactions VALUES(?,?,?,?,?,?)";
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            pst.setString(1,input_product_id.getItemAt(input_product_id.getSelectedIndex()));
            pst.setString(2, input_type.getItemAt(input_type.getSelectedIndex()));
            pst.setDouble(3,Double.parseDouble(input_quantity.getText()) );
            pst.setDouble(4,Double.parseDouble(input_weight.getText()) );
            pst.setString(5,input_date);
            pst.setString(6, input_crt.getItemAt(input_crt.getSelectedIndex()));
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please Enter Valid Product ID");
        }
        updateTable(); 
        StatusUpdater();
        StockTableUpdate();
    }//GEN-LAST:event_AddActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = (Date) input_date.getDate();
            String input_date =sdf.format(date1) ;
            String sql = "UPDATE Transactions SET Type=?,Quantity=?,Weight=?,DATE=?,CRT=? WHERE Product_ID=?";
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            pst.setString(6, input_product_id.getItemAt(input_product_id.getSelectedIndex()));
            pst.setString(1, input_type.getItemAt(input_type.getSelectedIndex()));
            pst.setDouble(2,Double.parseDouble(input_quantity.getText()) );
            pst.setDouble(3,Double.parseDouble(input_weight.getText()) );
            pst.setString(4,input_date);
            pst.setString(5, input_crt.getItemAt(input_crt.getSelectedIndex()));
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateTable();
        StatusUpdater();
        StockTableUpdate();
// TODO add your handling code here:
    }//GEN-LAST:event_UpdateActionPerformed

    private void input_quantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_input_quantityKeyPressed
     char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') ||
         (c == KeyEvent.VK_BACK_SPACE) ||
         (c == KeyEvent.VK_DELETE)))        
      {
        JOptionPane.showMessageDialog(null, "Please Enter Valid Quatity");
        evt.consume();
      }        // TODO add your handling code here:
    }//GEN-LAST:event_input_quantityKeyPressed

    private void input_weightKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_input_weightKeyPressed
         char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') ||
         (c == KeyEvent.VK_BACK_SPACE) ||
         (c == KeyEvent.VK_DELETE)))        
      {
        JOptionPane.showMessageDialog(null, "Please Enter Valid Weight");
        evt.consume();
      }// TODO add your handling code here:
    }//GEN-LAST:event_input_weightKeyPressed

    private void RefreshDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshDataActionPerformed
       try {
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           Date date1 = (Date) InsertStopDate.getDate();
           String InsertStopDate =sdf.format(date1) ;
           Date date = (Date) InsertStartDate.getDate();
           String InsertStartDate = sdf.format(date);
           if(InsertStartDate.isEmpty() && InsertStopDate.isEmpty()){
               updateTable();
               JOptionPane.showMessageDialog(null, "Please Enter Valid Date YYYY-MM-DD");
           }
           else{
            String sql = "SELECT * FROM Transactions WHERE Date BETWEEN ? and ?";
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            pst.setString(1,InsertStartDate);
            pst.setString(2,InsertStopDate);
            System.out.println("\n"+InsertStartDate+"\n"+InsertStopDate);
            rs = pst.executeQuery();
            AllTransactionsTable.setModel(DbUtils.resultSetToTableModel(rs));
           }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_RefreshDataActionPerformed

    private void ViewAllTransactionsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ViewAllTransactionsItemStateChanged
       
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
            String sql = "SELECT * FROM Transactions";
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.print(rs);
            AllTransactionsTable.setModel(DbUtils.resultSetToTableModel(rs));
            conn.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    else if (evt.getStateChange() == ItemEvent.DESELECTED) {
        updateTable();
    }
    }//GEN-LAST:event_ViewAllTransactionsItemStateChanged

    private void ViewAlllPurchasesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ViewAlllPurchasesItemStateChanged
       if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
            String sql = "SELECT * FROM Transactions WHERE Type='Purchase'";
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.print(rs);
            AllTransactionsTable.setModel(DbUtils.resultSetToTableModel(rs));
            conn.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    else if (evt.getStateChange() == ItemEvent.DESELECTED) {
        updateTable();
    } // TODO add your handling code here:
    }//GEN-LAST:event_ViewAlllPurchasesItemStateChanged

    private void ViewAllSalesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ViewAllSalesItemStateChanged
          if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
            String sql = "SELECT * FROM Transactions WHERE Type='Sales'";
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.print(rs);
            AllTransactionsTable.setModel(DbUtils.resultSetToTableModel(rs));
            conn.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    else if (evt.getStateChange() == ItemEvent.DESELECTED) {
        updateTable();
    } // TODO add your handling code here:
    }//GEN-LAST:event_ViewAllSalesItemStateChanged

    private void ViewALLOrderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ViewALLOrderItemStateChanged
       if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
            String sql = "SELECT * FROM Transactions WHERE Type='Order'";
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.print(rs);
            AllTransactionsTable.setModel(DbUtils.resultSetToTableModel(rs));
            conn.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    else if (evt.getStateChange() == ItemEvent.DESELECTED) {
        updateTable();
    }         // TODO add your handling code here:
    }//GEN-LAST:event_ViewALLOrderItemStateChanged

    private void ViewAllArtisanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ViewAllArtisanItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
                  try {
                  String sql = "SELECT * FROM Transactions WHERE Type='Artisan'";
                  conn=ConnectionManager.Connect();
                  pst = conn.prepareStatement(sql);
                  rs = pst.executeQuery();
                  System.out.print(rs);
                  AllTransactionsTable.setModel(DbUtils.resultSetToTableModel(rs));
                  conn.close();


              } catch (SQLException ex) {
                  Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
              }

          }
          else if (evt.getStateChange() == ItemEvent.DESELECTED) {
              updateTable();
          }         // TODO add your handling code here:
    }//GEN-LAST:event_ViewAllArtisanItemStateChanged

    private void ExportToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportToExcelActionPerformed
        FileOutputStream excelFos = null;
        XSSFWorkbook excelJTableExport = null;
        BufferedOutputStream excelBos = null;
        try {
 
            //Choosing Saving Location
            //Set default location to C:\Users\Authentic\Desktop or your preferred location
            JFileChooser excelFileChooser = new JFileChooser(System.getProperty("user.home"));
            //Dialog box title
            excelFileChooser.setDialogTitle("Save As ..");
            //Filter only xls, xlsx, xlsm files
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Files", "xls", "xlsx", "xlsm");
            //Setting extension for selected file names
            excelFileChooser.setFileFilter(fnef);
            int chooser = excelFileChooser.showSaveDialog(null);
            //Check if save button has been clicked
            if (chooser == JFileChooser.APPROVE_OPTION) {
                //If button is clicked execute this code
                excelJTableExport = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExport.createSheet("Table Data Export");
                //Loop through the jtable columns and rows to get its values
                XSSFRow row = excelSheet.createRow(0);
                 for (int c = 0; c < AllTransactionsTable.getColumnCount(); c++) {
                       XSSFCell cell =  row.createCell(c);
                        cell.setCellValue(AllTransactionsTable.getColumnName(c).toString());
                       System.out.println("\n"+AllTransactionsTable.getColumnName(c).toString());
                       }
                for (int i = 0; i < AllTransactionsTable.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i+1);
                    for (int j = 0; j < AllTransactionsTable.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
 
                        excelCell.setCellValue(AllTransactionsTable.getValueAt(i, j).toString());
                      
                    }
                }
                excelFos = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBos = new BufferedOutputStream(excelFos);
                excelJTableExport.write(excelBos);
                excelBos.close();
                JOptionPane.showMessageDialog(null, "Exported Successfully");
            }
 
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                if (excelFos != null) {
                    excelFos.close();
                }
                if (excelBos != null) {
                    excelBos.close();
                }
                if (excelJTableExport != null) {
                    excelJTableExport.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_ExportToExcelActionPerformed

    private void AllTransactionsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllTransactionsTableMouseClicked
        DefaultTableModel model = (DefaultTableModel) AllTransactionsTable.getModel();
        int selectedRowIndex = AllTransactionsTable.getSelectedRow();
        input_product_id.setSelectedItem(model.getValueAt(selectedRowIndex, 0).toString());
        input_type.setSelectedItem(model.getValueAt(selectedRowIndex, 1).toString());
        input_quantity.setText(model.getValueAt(selectedRowIndex, 2).toString());
        input_weight.setText(model.getValueAt(selectedRowIndex, 3).toString());
//        input_date.setText(model.getValueAt(selectedRowIndex, 4).toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf. parse(model.getValueAt(selectedRowIndex, 4).toString());
        } catch (ParseException ex) {
            Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
        }
        input_date.setDate(date);
        input_crt.setSelectedItem(model.getValueAt(selectedRowIndex, 5).toString());
        
// TODO add your handling code here:
    }//GEN-LAST:event_AllTransactionsTableMouseClicked

    private void ExportToExcelStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportToExcelStockActionPerformed
        FileOutputStream excelFos = null;
        XSSFWorkbook excelJTableExport = null;
        BufferedOutputStream excelBos = null;
        try {
 
            //Choosing Saving Location
            //Set default location to C:\Users\Authentic\Desktop or your preferred location
            JFileChooser excelFileChooser = new JFileChooser(System.getProperty("user.home"));
            //Dialog box title
            excelFileChooser.setDialogTitle("Save As ..");
            //Filter only xls, xlsx, xlsm files
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Files", "xls", "xlsx", "xlsm");
            //Setting extension for selected file names
            excelFileChooser.setFileFilter(fnef);
            int chooser = excelFileChooser.showSaveDialog(null);
            //Check if save button has been clicked
            if (chooser == JFileChooser.APPROVE_OPTION) {
                //If button is clicked execute this code
                excelJTableExport = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExport.createSheet("Table Data Export");
                //Loop through the jtable columns and rows to get its values
                XSSFRow row = excelSheet.createRow(0);
                 for (int c = 0; c < AllStockAvailableTable.getColumnCount(); c++) {
                       XSSFCell cell =  row.createCell(c);
                        cell.setCellValue(AllStockAvailableTable.getColumnName(c).toString());
                       
                       }
                for (int i = 0; i < AllStockAvailableTable.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i+1);
                    for (int j = 0; j < AllStockAvailableTable.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
 
                        excelCell.setCellValue(AllStockAvailableTable.getValueAt(i, j).toString());
                      System.out.println("\n"+AllStockAvailableTable.getValueAt(i, j).toString());
                    }
                }
                excelFos = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBos = new BufferedOutputStream(excelFos);
                excelJTableExport.write(excelBos);
                excelBos.close();
                JOptionPane.showMessageDialog(null, "Exported Successfully");
            }
 
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                if (excelFos != null) {
                    excelFos.close();
                }
                if (excelBos != null) {
                    excelBos.close();
                }
                if (excelJTableExport != null) {
                    excelJTableExport.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
                System.out.println(ex);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_ExportToExcelStockActionPerformed

    private void RefreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshBtnActionPerformed
       String sql = "SELECT Product_ID,Quantity as Available_Stock FROM Transactions Where Product_ID=?;";
        try {
            if(!SearchID.getText().isEmpty()){
            conn=ConnectionManager.Connect();
            pst = conn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(SearchID.getText()));
            rs = pst.executeQuery();
            System.out.print(rs);
            AllStockAvailableTable.setModel(DbUtils.resultSetToTableModel(rs));
            conn.close(); 
            }
            else{
                JOptionPane.showMessageDialog(null, "Please Enter a valid ID to search");
                StockTableUpdate();
            }
            
        } catch (SQLException ex) {
             Logger.getLogger(Ui.class.getName()).log(Level.SEVERE, null, ex);
             
             
        } // TODO add your handling code here:
    }//GEN-LAST:event_RefreshBtnActionPerformed

    private void SearchIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchIDKeyPressed
    char c = evt.getKeyChar();
      if (!((c >= '0') && (c <= '9') ||
         (c == KeyEvent.VK_BACK_SPACE) ||
         (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_MINUS)))        
      {
        JOptionPane.showMessageDialog(null, "Please Enter Valid ID");
        evt.consume();
      }           // TODO add your handling code here:
    }//GEN-LAST:event_SearchIDKeyPressed

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
                if ("UI".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton About;
    private javax.swing.JButton Add;
    private javax.swing.JTable AllStockAvailableTable;
    private javax.swing.JTable AllTransactionsTable;
    private javax.swing.JLabel Artisan_count;
    private javax.swing.JButton ExportToExcel;
    private javax.swing.JButton ExportToExcelStock;
    private javax.swing.ButtonGroup FilterButtons;
    private javax.swing.JPanel GetTotalWeight;
    private com.toedter.calendar.JDateChooser InsertStartDate;
    private com.toedter.calendar.JDateChooser InsertStopDate;
    private javax.swing.JLabel Order_count;
    private javax.swing.JPanel Purchase_Weights;
    private javax.swing.JLabel Purchase_count;
    private javax.swing.JButton RefreshBtn;
    private javax.swing.JButton RefreshData;
    private javax.swing.JLabel Sale_count;
    private javax.swing.JTextField SearchID;
    private javax.swing.JLabel Total_weight_count;
    private javax.swing.JButton Transactions;
    private javax.swing.JButton Update;
    private javax.swing.JRadioButton ViewALLOrder;
    private javax.swing.JRadioButton ViewAllArtisan;
    private javax.swing.JRadioButton ViewAllSales;
    private javax.swing.JRadioButton ViewAllTransactions;
    private javax.swing.JRadioButton ViewAlllPurchases;
    private javax.swing.JComboBox<String> input_crt;
    private com.toedter.calendar.JDateChooser input_date;
    private javax.swing.JComboBox<String> input_product_id;
    private javax.swing.JTextField input_quantity;
    private javax.swing.JComboBox<String> input_type;
    private javax.swing.JTextField input_weight;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
