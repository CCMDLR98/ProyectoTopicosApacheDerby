

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.omg.Messaging.SyncScopeHelper;




public class C1 extends JFrame implements ActionListener
{
	static final String CONTROLADOR = "";
	private static final String URL = "jdbc:derby:Producto";
	private static final String USUARIO = "Medrano";
	private static final String PW		= "123456";
	static final String CONSULTA_VACIA="SELECT * FROM Producto WHERE idCodigo= 0";
	  static final String CONSULTA_PREDETERMINADA = "SELECT * FROM Producto";
	  static final String CONSULTA_PREDETERMINADA2 = "SELECT cantidad from producto";
	   
	private ConsultasProducto consultasProducto;
	private ResultSetTableModel modeloTabla;
		//MENU
	private JMenu jmArchivo,jmAyuda;
		//OPCIONES DE MENU
	private JMenuItem jmiActualizarC,jmiEditarC,jmiConsultarC,jmiImprimir,jmiSalir;
	private JMenuItem jmiAcercaDe;
		//Barra de Menu
	private JMenuBar jmBar;
		//Tabla
	JTable tabla;
	DefaultTableModel registro;
		//Campos de Texto
	JTextField	txtCodigo,txtCantidad,txtUnidadDMedida,txtDescripcion,txtPrecio,txtImporte;
		//Variable Boolean
	Boolean Activo;
	 	//IMPORTE
	String p1,i1;
	Double total,precio,impuesto;
		//ARCHIVOS
	JFileChooser MiArchivo;
	
	public C1 ()
	 {
	 	super("CATALOGOS PAPELERIA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//ESTABLECE CONEXION E INSTRUCCIONES 
		consultasProducto = new ConsultasProducto();
		setLayout(new BorderLayout());
		Componentes();
	 }
	private void Componentes()
	{
		Menu();
		MiPanelY miPanel = new MiPanelY();
		miPanel.setPreferredSize(new Dimension(50,50));
		add( miPanel,BorderLayout.SOUTH);
		Tabla();
	}
	private void Menu()
	{
		jmBar = new JMenuBar();
			jmArchivo = new JMenu("Archivo");
			jmArchivo.setMnemonic('A');
				jmiActualizarC = new JMenuItem("Actualizar Catalogo");
				jmiActualizarC.setMnemonic('A');
				jmiActualizarC.addActionListener(this);
				jmArchivo.add(jmiActualizarC);
				
				jmiEditarC = new JMenuItem("Editar Catalogo");
				jmiEditarC.setMnemonic('e');
				jmiEditarC.addActionListener(this);
				jmArchivo.add(jmiEditarC);
				
				jmiConsultarC = new JMenuItem("Consultar Catalogo");
				jmiConsultarC.setMnemonic('C');
				jmiConsultarC.addActionListener(this);
				jmArchivo.add(jmiConsultarC);
				
				jmiImprimir = new JMenuItem("Imprimir");
				jmiImprimir.setMnemonic('I');
				jmiImprimir.addActionListener(this);
				jmArchivo.add(jmiImprimir);
				
				jmiSalir = new JMenuItem("Salir");
				jmiSalir.setMnemonic('S');
				jmArchivo.add(jmiSalir);
				jmiSalir.addActionListener(this);
				
		   jmAyuda = new JMenu("Ayuda");
		   jmAyuda.setMnemonic('Y');
			   	jmiAcercaDe = new JMenuItem("Acerca De...");
			   	jmiAcercaDe.setMnemonic('A');
				jmAyuda.add(jmiAcercaDe);
				jmiAcercaDe.addActionListener(this);
		   
		jmBar.add(jmArchivo);
		jmBar.add(jmAyuda);
		
			setJMenuBar(jmBar);
	}
	class MiPanelY extends JPanel
	{
		public MiPanelY()
		{
			txtCodigo = new JTextField(10);
			txtCodigo.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) { 
			        if (txtCodigo.getText().length() >= 10)
			            e.consume(); 
			    }  
			});
			txtCantidad = new JTextField( 10 );
			txtCantidad.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) { 
			    	char p = e.getKeyChar();
			    	if(!(Character.isDigit(p))||(p==KeyEvent.VK_SPACE)||
			    			(p==KeyEvent.VK_DELETE))
			    		e.consume();
			        if (txtCantidad.getText().length() >=10 )
			            e.consume(); 
			    }  
			});
			txtUnidadDMedida = new JTextField(15);
			txtUnidadDMedida.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) { 
			        if (txtUnidadDMedida.getText().length() >= 15 )
			            e.consume(); 
			    }  
			});
			txtDescripcion= new JTextField(25);
			txtDescripcion.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) { 
			        if (txtDescripcion.getText().length() >= 40 )
			            e.consume(); 
			    }  
			});
			txtPrecio = new JTextField(10);
			txtPrecio.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) { 
			    	char p = e.getKeyChar();
			    	if(!(Character.isDigit(p))||(p==KeyEvent.VK_SPACE)||
			    			(p==KeyEvent.VK_DELETE))
			    		e.consume();
			        if (txtPrecio.getText().length() >= 10)
			            e.consume(); 
			    }  
			});
			txtImporte = new JTextField(10);
			txtImporte.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			    	char p = e.getKeyChar();
			    	if(!(Character.isDigit(p))||(p==KeyEvent.VK_SPACE)||
			    			(p==KeyEvent.VK_DELETE))
			    		e.consume();
			        if (txtImporte.getText().length() >= 10)
			            e.consume(); 
			    }  
			});
			add( new JLabel( "CODIGO: " ) );
			add( txtCodigo );
			txtCodigo.setEditable(false);
			add( new JLabel( "CANTIDAD: " ) );
			add( txtCantidad );
			add( new JLabel( "UNIDAD DE MEDIDA: " ) );
			add( txtUnidadDMedida );
			add( new JLabel( "DESCRIPCION: " ) );
			add( txtDescripcion );
			add( new JLabel( "PRECIO: " ) );
			add( txtPrecio );
			add( new JLabel( "IMPORTE: "));
			add( txtImporte);	
		}
	}
	private void Tabla() 
	{
		try {
			modeloTabla = new ResultSetTableModel(CONTROLADOR,URL,
					USUARIO,PW,CONSULTA_VACIA);
			String[] columnNames = {"CODIGO","CANTIDAD","UNIDAD DE MEDIDA","DESCRIPCION","PRECIO","IMPORTE","ACTIVO"};
			Object[][] datos = {};
			registro = new DefaultTableModel(datos,columnNames);
			tabla = new JTable(modeloTabla){
				private static final long serialVerionUID = 1L;
				public boolean isCellEditable(int fila, int colum) 
				{
					return false;
				}
			}; 
			   tabla.addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseClicked(java.awt.event.MouseEvent evt) {
		            	jTable1MouseClicked(evt);
		            }
		        });
			JScrollPane jmiScrollPane = new JScrollPane(tabla);
			getContentPane().add(jmiScrollPane, BorderLayout.CENTER);
			add(jmiScrollPane);
			}catch(Exception e)
			{
					
			}
		 
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jmiAcercaDe||e.getSource()==jmiAcercaDe)
		{
			JOptionPane.showMessageDialog(null,"Medrnao de la Rocha Carlos Cesar NC:16171419", "Datos del Autor",JOptionPane.WARNING_MESSAGE);
		}
		if(e.getSource()==jmiConsultarC || e.getSource()==jmiConsultarC)
		{
					try 
           		 {
             		  modeloTabla.establecerConsulta( CONSULTA_PREDETERMINADA );
           		 }
           		 catch ( SQLException excepcionSql2 ) 
           		 {
            		   JOptionPane.showMessageDialog( null, 
              		    excepcionSql2.getMessage(), "Error en base de datos", 
                		  JOptionPane.ERROR_MESSAGE );
              		 modeloTabla.desconectarDeBaseDatos();
              		 System.exit( 1 );
           		 }
		}
		if(e.getSource()==jmiImprimir || e.getSource()==jmiImprimir)
		{
			Imprimir(tabla, "CATALOGO", "PAPELERIA", true);
		}
		if(e.getSource()==jmiSalir||e.getSource()==jmiSalir)
		{
			System.exit(NORMAL);
		}
		if(e.getSource()==jmiActualizarC||e.getSource()==jmiActualizarC)
		{
			int result = consultasProducto.addProducto(txtCantidad.getText(), txtUnidadDMedida.getText(), txtDescripcion.getText()
					, txtPrecio.getText(), txtImporte.getText());
			if(result == 1)
				  JOptionPane.showMessageDialog( this, "Se agrego producto",
				            "Se agrego persona", JOptionPane.PLAIN_MESSAGE );
				      else
				         JOptionPane.showMessageDialog( this, "No se agrego producto!",
				            "Error", JOptionPane.PLAIN_MESSAGE );
			try 
            {
               modeloTabla.establecerConsulta( CONSULTA_PREDETERMINADA );
            }
            catch ( SQLException excepcionSql2 ) 
            {
               JOptionPane.showMessageDialog( null, 
                  excepcionSql2.getMessage(), "Error en base de datos", 
                  JOptionPane.ERROR_MESSAGE );
               modeloTabla.desconectarDeBaseDatos();

               System.exit( 1 );
            }
			
		}
		if(e.getSource()==jmiEditarC || e.getSource()==jmiEditarC)
		{
			int result = consultasProducto.editProducto(txtCantidad.getText(), txtUnidadDMedida.getText(), txtDescripcion.getText()
					, txtPrecio.getText(), txtImporte.getText(),txtCodigo.getText());
			if(result == 1)
				  JOptionPane.showMessageDialog( this, "Se edito Producto!",
				            "Se agrego persona", JOptionPane.PLAIN_MESSAGE );
				      else
				         JOptionPane.showMessageDialog( this, "No se edito!",
				            "Error", JOptionPane.PLAIN_MESSAGE );
			try 
            {
               modeloTabla.establecerConsulta( CONSULTA_PREDETERMINADA );
            }
            catch ( SQLException excepcionSql2 ) 
            {
               JOptionPane.showMessageDialog( null, 
                  excepcionSql2.getMessage(), "Error en base de datos", 
                  JOptionPane.ERROR_MESSAGE );
               modeloTabla.desconectarDeBaseDatos();
               System.exit( 1 );
            }
			txtCodigo.setText("");txtCantidad.setText("");txtUnidadDMedida.setText("");txtDescripcion.setText("");
			txtPrecio.setText("");txtImporte.setText("");
			
		}
	
	}
	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) 
	 {                                     
	       int fila = tabla.getSelectedRow();
	       txtCodigo.setText(modeloTabla.getValueAt(fila, 0).toString());
	       txtCantidad.setText(modeloTabla.getValueAt(fila, 1).toString());
	       txtUnidadDMedida.setText(modeloTabla.getValueAt(fila, 2).toString());
	       txtDescripcion.setText(modeloTabla.getValueAt(fila, 3).toString());
	       txtPrecio.setText(modeloTabla.getValueAt(fila, 4).toString());
	       txtImporte.setText(modeloTabla.getValueAt(fila, 5).toString());
	       int result = consultasProducto.actProducto(txtCodigo.getText());
			try 
           {
              modeloTabla.establecerConsulta( CONSULTA_PREDETERMINADA );
           }
           catch ( SQLException excepcionSql2 ) 
           {
              JOptionPane.showMessageDialog( null, 
                 excepcionSql2.getMessage(), "Error en base de datos", 
                 JOptionPane.ERROR_MESSAGE );
              modeloTabla.desconectarDeBaseDatos();
              System.exit( 1 );
           }
	   }
	public void Imprimir(JTable jTable, String header, String footer, boolean showPrintDialog){        
        boolean fitWidth = true;        
        boolean interactive = true;
        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
        try {boolean complete = jTable.print(mode,new MessageFormat(header), new MessageFormat(footer),
                    showPrintDialog,null,interactive);       
        	} catch (PrinterException pe) {JOptionPane.showMessageDialog(jTable, 
                    "Print fail (Fallo de impresión): " + pe.getMessage(), 
                    "Print result (Resultado de la impresión)",JOptionPane.ERROR_MESSAGE);
        }
    }
}


	
