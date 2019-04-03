package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.ListaPeliculas;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class VentanaPeliculasCargadas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	

	/**
	 * Create the frame.
	 * @param lPelis 
	 */
	
	public VentanaPeliculasCargadas() {
		
		setResizable(false);
		setTitle("Peliculas Cargadas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		
	
		//Añadir las filas a la tabla desde el hashmap
		
		/*for (Map.Entry<Integer, String> entry : pDatos.entrySet()) {
			
			model.addRow(new Object[]{entry.getValue()});
		    
		}*/
		
		
		
		
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
		
		table.getTableHeader().setPreferredSize(new java.awt.Dimension(30, 30)); //altura del header
		table.getTableHeader().setFont(new Font("Tahoma", 1, 16)); //letra del header
		table.getTableHeader().setReorderingAllowed(false); //para no cambiar las columnas de orden

		table.setVisible(true);
		
		JLabel lblPeliculas = new JLabel("PELICULAS: ");
		lblPeliculas.setFont(new Font("Tahoma", Font.BOLD, 21));
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
							.addComponent(lblPeliculas)
							.addGap(65))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPeliculas)
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
	
	public void añadirPelis(ArrayList<String> pDatos) {
		
		for (int i=0; i<pDatos.size(); i++) {
			String cadena=pDatos.get(i);
			int cont=0;
			char c=cadena.charAt(cont);
			while(c!='"') {
				c=cadena.charAt(cont);
				cont++;
			}
			int j=cont+1;
			c=cadena.charAt(cont+1);
			while(c!='(') {
				c=cadena.charAt(j);
				j++;
			}
			
			String titulo=cadena.substring(cont, j-1);
			model.addRow(new Object[]{titulo});
			
			
		}
	}
}
