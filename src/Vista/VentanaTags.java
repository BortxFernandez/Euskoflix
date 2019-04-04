package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.javatuples.Pair;

import Modelo.ListaPeliculas;
import Modelo.Tag;

public class VentanaTags extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */

	public VentanaTags() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		model = new DefaultTableModel()
		{
			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) 
			{
			    return false;
			}
		};
	  
		//Añadir las columnas de la tabla	
		
		model.addColumn("PELICULA");
		model.addColumn("VECES");
		model.addColumn("DESCRIPCION");
		
		
		
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(table);
		
		table = new JTable(model);
		table.setRowSelectionAllowed(false); //no poder seleccionar filas
		table.setRowHeight(25); //anchura de las filas
		scrollPane.setViewportView(table);
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setBackground(UIManager.getColor("Slider.background"));
			
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		
		
		table.getTableHeader().setPreferredSize(new java.awt.Dimension(30, 30)); //altura del header
		table.getTableHeader().setFont(new Font("Tahoma", 1, 16)); //letra del header
		table.getTableHeader().setReorderingAllowed(false); //para no cambiar las columnas de orden

		table.setVisible(true);
		
		JLabel lblTags = new JLabel("TAGS: ");
		lblTags.setFont(new Font("Tahoma", Font.BOLD, 21));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblTags)
							.addGap(65))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTags)
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		//Centrar
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension frameSize = getSize(); //TamaÃ±o del frame actual (ancho x alto)
	    if (frameSize.height > screenSize.height) {
	        frameSize.height = screenSize.height;
	    }
	    if (frameSize.width > screenSize.width) {
	        frameSize.width = screenSize.width;
	    }
	    setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		
	}
	
	public void añadirTags(ArrayList<String> pDatos){
		int ind=0;
		String anterior=null;
		int idAnterior=0;
		int n=1;
		
		
		while(pDatos.size()>ind) {
	          int i=0;
	          char c;          
	          c=pDatos.get(ind).charAt(i);
	          while(c!=';') {
	        	  c=pDatos.get(ind).charAt(i);
	        	  i++;  
	          }
	          String idS=pDatos.get(ind).substring(0,i-1);
	          int idActual=Integer.parseInt(idS);        
	          i=i+1;
	          int j=i;

	          String desc=pDatos.get(ind).substring(j-1);
	          if (desc.equals(anterior)){
	        	  n++;
	          }
	          else
	          {
	        	  String nombre = ListaPeliculas.getListaPeliculas().getNombre(idAnterior);
		          model.addRow(new Object[]{nombre, n, anterior});
		          n=1;
		          
	          }
	          
	          
	          ind ++;
	          anterior=desc;
	          idAnterior = idActual;
	          
	         
	
		//Añadir las filas a la tabla desde el hashmap
		
		/*for (Entry<Integer, Tag> entry : pDatos.entrySet()) {
			
			Integer idPeli=entry.getKey();
			String nombre=ListaPeliculas.getListaPeliculas().getNombre(idPeli);
			Tag unTag = entry.getValue();
			model.addRow(new Object[]{nombre, unTag.getNVeces(), unTag.getDesc()});
		  */  
		}
	}

}