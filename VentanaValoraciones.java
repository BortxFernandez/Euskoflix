package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
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
import Modelo.Valoracion;

public class VentanaValoraciones extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	
	

	/**
	 * Create the frame.
	 */
	public VentanaValoraciones() {
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
		
		model.addColumn("USUARIO");
		model.addColumn("PELICULA");
		model.addColumn("NOTA");
		
	
		//Añadir las filas a la tabla desde el hashmap
		
		/*for (Entry<Integer, Valoracion> entry : pDatos.entrySet()) {
			Valoracion unaV=entry.getValue();
			Integer idPeli =unaV.getId();
			String nombrePeli=ListaPeliculas.getListaPeliculas().getNombre(idPeli);
			double nota =unaV.getNota();
			model.addRow(new Object[]{entry.getKey(), nombrePeli, nota });
		}
		*/
		
		
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
		
		JLabel lblValoraciones = new JLabel("TAGS: ");
		lblValoraciones.setFont(new Font("Tahoma", Font.BOLD, 21));
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
							.addComponent(lblValoraciones)
							.addGap(65))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblValoraciones)
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
	
	public void añadirValoraciones(ArrayList<String> pDatos) {
		
		for (int k=0; k<pDatos.size(); k++) {
			 String cadena=pDatos.get(k);
			 int i=0;          
	         char c=cadena.charAt(i);
	         while(c!=',') {
	        	  c=cadena.charAt(i);
	        	  i++;  
	         }
	         String idStringU=cadena.substring(0,i-1);
	         int idU=Integer.parseInt(idStringU);
	         i=i+1;
	         int j=i;
	         c=cadena.charAt(i);
	         while(c!=',') {
	        	 c=cadena.charAt(i);
	        	 i++;  
	         }
	         String idStringP=cadena.substring(j,i-1);
	         int idP=Integer.parseInt(idStringP);
	         String notaString=cadena.substring(i);
	         double nota= Double.parseDouble(notaString);
	         String nombrePeli=ListaPeliculas.getListaPeliculas().getNombre(idP);
	         model.addRow(new Object[]{idU, nombrePeli, nota });
		}
		
		  
	}

}
